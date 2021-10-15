package poseidon.mod.world.gen;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.IWorldGenerator;
import poseidon.mod.objects.block.general.chest.obsidian.TileEntityObsidianChest;
import poseidon.mod.world.gen.generators.WorldGenStructure;
import scala.actors.threadpool.Arrays;

public class WorldGenCustomStructures implements IWorldGenerator {

	public static final WorldGenStructure ARMOURY = new WorldGenStructure("armoury");
	public static final WorldGenStructure NETHER_CASTLE_BIG = new WorldGenStructure("nethercastlebig");
	public static final WorldGenStructure NETHER_CASTLE = new WorldGenStructure("nethertower");
	//public static final WorldGenStructure ARMOURYE = new WorldGenStructure("armourye");
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
			case 2:
				break;
			case 1:
				break;
			case 0:

				break;
			case -1:
				
				
				
				break;
				
		}
	}
	
	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes) {
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

		
		if(world.getWorldType() != WorldType.FLAT) {
			if(classesList.contains(biome)) {
				if(random.nextInt(chance) == 0) {
					generator.generate(world, random, pos);
				}
			}
		}
		
	}
	
	
	public static boolean canSpawnHere(Template template, World world, BlockPos posAboveGround) {
		int zwidth = template.getSize().getZ();
		int xwidth = template.getSize().getX();
		
		// check all the corners to see which ones are replaceable
		boolean corner1 = isCornerValid(world, posAboveGround);
		boolean corner2 = isCornerValid(world, posAboveGround.add(xwidth, 0, zwidth));
		
		// if Y > 20 and all corners pass the test, it's okay to spawn the structure
		return posAboveGround.getY() > 31 && corner1 && corner2;
	}
	
	public static boolean isCornerValid(World world, BlockPos pos){
		int variation = 3;
		int highestBlock = getGroundFromAbove(world, pos.getX(), pos.getZ());
		
		if (highestBlock > pos.getY() - variation && highestBlock < pos.getY() + variation)
			return true;
				
		return false;
	}
	
	public static int getGroundFromAbove(World world, int x, int z){
		int y = 255;
		boolean foundGround = false;
		while(!foundGround && y-- >= 31)
		{
			Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround =  blockAt == Blocks.WATER||blockAt == Blocks.FLOWING_WATER||blockAt == Blocks.GRASS || blockAt == Blocks.SAND || blockAt == Blocks.SNOW || blockAt == Blocks.SNOW_LAYER || blockAt == Blocks.GLASS||blockAt == Blocks.MYCELIUM;
		}

		return y;
	}
	
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0) {
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}
	
	public static BlockPos getChest(World world, Template template, PlacementSettings settings, BlockPos pos) {
		
		Map<BlockPos, String> map = template.getDataBlocks(pos, settings);
		Random rand = new Random();
		
		for (Entry<BlockPos, String> entry : map.entrySet()) {
            if ("obsidian_chest".equals(entry.getValue())) {
            	System.out.println("ool");
            	
            	if(world.getTileEntity(entry.getKey()) instanceof TileEntityObsidianChest) {
            		TileEntityObsidianChest c = (TileEntityObsidianChest) world.getTileEntity(entry.getKey());
            		c.setLootTable(LootTableList.CHESTS_ABANDONED_MINESHAFT, rand.nextLong());
            	}
            	
            	return entry.getKey();
            }
		}
		
		System.out.println("No chest");
		return pos;
		
	}
	
}
