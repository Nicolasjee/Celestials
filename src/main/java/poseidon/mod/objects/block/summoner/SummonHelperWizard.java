package poseidon.mod.objects.block.summoner;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.entity.entities.EntityDemon;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ModProfessions;

public class SummonHelperWizard {

	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
	
	public static void spawnWizard(World w, BlockPos pos) {
		if(!w.isRemote) {
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();
			EntityVillager vil = new EntityVillager(w, 8);
			vil.setLocationAndAngles(x, y + 2.0D, z, vil.rotationYaw, vil.rotationPitch);
			w.spawnEntity(vil);
			
			vil.setProfession(ModProfessions.reader);
			
			//w.setBlockState(pos, Blocks.GLASS.getDefaultState());
			//replacePillars(w, x, y, z);
		}
	}
	
	public static BlockPos[] getGlowstone(BlockPos pos) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		BlockPos[] netherstone = new BlockPos[] {new BlockPos(x + 2.0D, y + 2.0D ,z + 2.0D), new BlockPos(x + 2.0D, y + 2.0D, z - 2.0D), new BlockPos(x - 2.0D, y + 2.0D ,z + 2.0D), 
												new BlockPos(x - 2.0D, y + 2.0D ,z - 2.0D)};
		
		return netherstone;
	}

	public static BlockPos[] getPillar(BlockPos pos) {
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		BlockPos[] netherstone = new BlockPos[] {new BlockPos(x + 2.0D, y - 1.0D ,z + 2.0D), new BlockPos(x + 2.0D, y, z + 2.0D), new BlockPos(x + 2.0D, y + 1.0D ,z + 2.0D),
				 new BlockPos(x - 2.0D, y - 1.0D ,z + 2.0D), new BlockPos(x - 2.0D, y, z + 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z + 2.0D),
				 new BlockPos(x - 2.0D, y - 1.0D ,z - 2.0D), new BlockPos(x - 2.0D, y, z - 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z - 2.0D),
				 new BlockPos(x + 2.0D, y - 1.0D ,z - 2.0D), new BlockPos(x + 2.0D, y, z - 2.0D), new BlockPos(x + 2.0D, y + 1.0D ,z - 2.0D)};
		
		return netherstone;
	}

	public static BlockPos[] getDiorite(BlockPos pos) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		BlockPos[] diorite = new BlockPos[] {new BlockPos(x, y - 2.0D, z + 2.0D), new BlockPos(x + 2.0D, y - 2.0D, z), new BlockPos(x + 2.0D, y - 2.0D, z + 1.0D), new BlockPos(x + 1.0D, y - 2.0D, z + 1.0D),
				  new BlockPos(x + 1.0D, y - 2.0D, z + 2.0D), new BlockPos(x - 1.0D, y - 2.0D, z + 1.0D), new BlockPos(x - 1.0D, y - 2.0D, z + 2.0D), new BlockPos(x - 2.0D, y - 2.0D, z + 1.0D),
				  new BlockPos(x - 2.0D, y - 2.0D, z), new BlockPos(x - 2.0D, y - 2.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 2.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 2.0D, z - 2.0D),
				  new BlockPos(x, y - 2.0D, z - 2.0D), new BlockPos(x + 1.0D, y - 2.0D, z - 2.0D), new BlockPos(x + 1.0D, y - 2.0D, z - 1.0D), new BlockPos(x + 2.0D, y - 2.0D, z - 1.0D)};
		return diorite;
	}
	
	public static int getMistakes(BlockPos[] netherstone, BlockPos[] soulsand, BlockPos quartz, BlockPos[] obs, BlockPos[] glow, World worldIn) {
		int mistakes = 0;
		
		for(int i = 0; i < netherstone.length; i++) {
			BlockPos netherStone = netherstone[i];
			IBlockState netherState = worldIn.getBlockState(netherStone);
			
			if(!(netherState.getBlock().getDefaultState() == Blocks.CONCRETE.getDefaultState().withProperty(COLOR, EnumDyeColor.WHITE))) {
				System.out.println("mistake1: " + netherstone[i]);
				mistakes++;
			}
			
		}
		
		for(int i = 0; i < glow.length; i++) {
			BlockPos netherStone = glow[i];
			IBlockState netherState = worldIn.getBlockState(netherStone);
			
			if(!(netherState.getBlock().getDefaultState() == Blocks.GLOWSTONE.getDefaultState())) {
				System.out.println("mistake2: " + netherstone[i]);
				mistakes++;
			}
			
		}
		
		for(int i = 0; i < soulsand.length; i++) {
			BlockPos soulSand = soulsand[i];
			IBlockState soulSandState = worldIn.getBlockState(soulSand);
			
			if(!(soulSandState.getBlock().getDefaultState() == Blocks.CONCRETE_POWDER.getDefaultState().withProperty(COLOR, EnumDyeColor.WHITE))) {
				mistakes++;
			}
			
		}
		
		if(worldIn.getBlockState(quartz).getBlock().getDefaultState() != Blocks.STONEBRICK.getDefaultState()) {
			mistakes++;
		}
		
		for(int i = 0; i < obs.length; i++) {
			BlockPos obsidianPosition = obs[i];
			IBlockState obState = worldIn.getBlockState(obsidianPosition);
			
			if(!(obState.getBlock().getDefaultState() == Blocks.CONCRETE_POWDER.getDefaultState().withProperty(COLOR, EnumDyeColor.WHITE))) {
				mistakes++;
			}
		}
		
		return mistakes;
	}

	private static double gH(double x, double z, World w) {
		return (double) w.getHeight((int)x, (int)z);
	}
	
	private static void replacePillars(World world, double x, double y, double z) {
		BlockPos[] netherstone = new BlockPos[] {new BlockPos(x + 2.0D, y - 1.0D ,z + 2.0D), new BlockPos(x + 2.0D, y, z + 2.0D), new BlockPos(x + 2.0D, y + 1.0D ,z + 2.0D),
				 new BlockPos(x - 2.0D, y - 1.0D ,z + 2.0D), new BlockPos(x - 2.0D, y, z + 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z + 2.0D),
				 new BlockPos(x - 2.0D, y - 1.0D ,z - 2.0D), new BlockPos(x - 2.0D, y, z - 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z - 2.0D),
				 new BlockPos(x + 2.0D, y - 1.0D ,z - 2.0D), new BlockPos(x + 2.0D, y, z - 2.0D), new BlockPos(x + 2.0D, y + 1.0D ,z - 2.0D)};
		
		for(int i = 0; i < netherstone.length; i++) {
			world.setBlockState(netherstone[i], Blocks.STAINED_GLASS.getDefaultState().withProperty(COLOR, EnumDyeColor.WHITE));
		}
	}

	public static void checkForOpenSpaces(World worldIn, BlockPos pos) {
		
		double x = pos.getX() + 2.0D;
		double y = pos.getY();
		double z = pos.getZ() + 2.0D;
		int openSpace = 0;
		
		List<BlockPos> space = new ArrayList<BlockPos>();
		List<BlockPos> space2 = new ArrayList<BlockPos>();
		List<BlockPos> space3 = new ArrayList<BlockPos>();
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 20; j++) {
				space.add(new BlockPos(x - (double) i, y - 1.0D, z - (double) j));
				space2.add(new BlockPos(x - (double) i, y, z - (double) j));
				space3.add(new BlockPos(x - (double) i, y + 1.0D, z - (double) j));
			}
		}
		
		List<BlockPos> list = removeBlocks(space, pos, 1);
		List<BlockPos> list2 = removeBlocks(space2, pos, 2);
		List<BlockPos> list3 = removeBlocks(space3, pos, 3);
		
		for(int i = 0; i < list.size(); i++) {
			BlockPos check = space.get(i);
			IBlockState state = worldIn.getBlockState(check);
			Block block = state.getBlock();
			
			if(!(block.getDefaultState() == Blocks.AIR.getDefaultState())) {
				
				worldIn.setBlockState(check, Blocks.AIR.getDefaultState());
					
					
				
			}
		}
		
		for(int i = 0; i < list2.size(); i++) {
			BlockPos check2 = space2.get(i);
			IBlockState state2 = worldIn.getBlockState(check2);
			Block block2 = state2.getBlock();
			
			if(!(block2.getDefaultState() == Blocks.AIR.getDefaultState())) {
				
				worldIn.setBlockState(check2, Blocks.AIR.getDefaultState());
					
					
				
			}
		}
		
		for(int i = 0; i < list3.size(); i++) {
			BlockPos check2 = space3.get(i);
			IBlockState state2 = worldIn.getBlockState(check2);
			Block block2 = state2.getBlock();
			
			if(!(block2.getDefaultState() == Blocks.AIR.getDefaultState())) {
				
				worldIn.setBlockState(check2, Blocks.AIR.getDefaultState());
					
					
				
			}
		}
	}
	
	private static List<BlockPos> removeBlocks(List<BlockPos> pos, BlockPos posi, int layer) {
		List<BlockPos> list = pos;
		
		double x = posi.getX();
		double y = posi.getY();
		double z = posi.getZ();
		
		if(layer == 3) {
			BlockPos[] netherstoneTopLayer = new BlockPos[] {new BlockPos(x + 2.0D, y + 1.0D ,z + 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z + 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z - 2.0D), new BlockPos(x + 2.0D, y + 1.0D ,z - 2.0D)};
			for(int i = 0; i < netherstoneTopLayer.length; i++) {
				list.remove(netherstoneTopLayer[i]);
			}
			return list;
		}
		
		if(layer == 2) {
			BlockPos[] netherStonemiddle = new BlockPos[] {new BlockPos(x + 2.0D, y, z + 2.0D), new BlockPos(x - 2.0D, y, z + 2.0D), new BlockPos(x - 2.0D, y, z - 2.0D), new BlockPos(x + 2.0D, y, z - 2.0D)};
			for(int i = 0; i < netherStonemiddle.length; i++) {
				list.remove(netherStonemiddle[i]);
			}
			list.remove(new BlockPos(x,y,z));
			return list;
		}
		
		if(layer == 1) {
			BlockPos[] netherstoneLow = new BlockPos[] {new BlockPos(x + 2.0D, y - 1.0D ,z + 2.0D),new BlockPos(x - 2.0D, y -1.0D ,z + 2.0D),new BlockPos(x - 2.0D, y -1.0D ,z - 2.0D),new BlockPos(x + 2.0D, y - 1.0D ,z - 2.0D)};
			for(int i = 0; i < netherstoneLow.length; i++) {
				list.remove(netherstoneLow[i]);
			}
			list.remove(new BlockPos(x, y - 1.0D, z));
			return list;
		}
		
		return list;
	}
	
}
