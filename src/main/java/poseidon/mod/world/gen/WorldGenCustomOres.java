package poseidon.mod.world.gen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import poseidon.mod.init.BlockInit;

public class WorldGenCustomOres implements IWorldGenerator {

	/*private WorldGenerator platinum_ore;
	private WorldGenerator petrolium_ore;
	private WorldGenerator aluminium_ore;*/
	private WorldGenerator nether_crate_res;
	private WorldGenerator nether_crate_arm;
	private WorldGenerator nether_crate_con;
	private WorldGenerator nether_crate_too;
	
	private WorldGenerator atlas_core;
	
	public WorldGenCustomOres() { // , BlockMatcher.forBlock(Blocks.NETHERRACK) 
		/*
		nether_crate_res = new WorldGenMinable(BlockInit.NETHER_CRATE_RESOURCES.getDefaultState(), 1);
		nether_crate_arm = new WorldGenMinable(BlockInit.NETHER_CRATE_ARMOR.getDefaultState(), 1);
		nether_crate_con = new WorldGenMinable(BlockInit.NETHER_CRATE_CONSUMABLES.getDefaultState(), 1);
		nether_crate_too = new WorldGenMinable(BlockInit.NETHER_CRATE_TOOLS.getDefaultState(), 1);
		atlas_core = new WorldGenMinable(BlockInit.ATLAS_CORE_ORE.getDefaultState(), 5);
		*/
	}
		

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.getDimension()) {
		
		case 0:
		

			break;
			
			
		case 1:
		
			break;
			
			
		case -1:
			/*
			runGenerator(nether_crate_res, world, random, chunkX, chunkZ, 50, 0, 235);
			runGenerator(nether_crate_arm, world, random, chunkX, chunkZ, 50, 0, 235);
			runGenerator(nether_crate_con, world, random, chunkX, chunkZ, 50, 0, 235);
			runGenerator(nether_crate_too, world, random, chunkX, chunkZ, 50, 0, 235);
			runGenerator(atlas_core, world, random, chunkX, chunkZ, 25, 0, 235);
			*/
		 	break;
		
		}
	
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		
		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out of bounds");
		int heightDiff = maxHeight - minHeight + 1;
		
		for(int i = 0; i < chance; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x, y ,z));
		}
		
	}
	
}
