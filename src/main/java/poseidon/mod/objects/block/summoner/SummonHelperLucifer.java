package poseidon.mod.objects.block.summoner;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.entity.entities.EntityCustomVillager;
import poseidon.mod.entity.entities.EntityDemon;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ModProfessions;

public class SummonHelperLucifer {

	public static BlockPos[] getNetherStone(BlockPos pos) {
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		BlockPos[] netherstone = new BlockPos[] {new BlockPos(x + 2.0D, y - 1.0D ,z + 2.0D), new BlockPos(x + 2.0D, y, z + 2.0D), new BlockPos(x + 2.0D, y + 1.0D ,z + 2.0D),
				 new BlockPos(x - 2.0D, y - 1.0D ,z + 2.0D), new BlockPos(x - 2.0D, y, z + 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z + 2.0D),
				 new BlockPos(x - 2.0D, y - 1.0D ,z - 2.0D), new BlockPos(x - 2.0D, y, z - 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z - 2.0D),
				 new BlockPos(x + 2.0D, y - 1.0D ,z - 2.0D), new BlockPos(x + 2.0D, y, z - 2.0D), new BlockPos(x + 2.0D, y + 1.0D ,z - 2.0D)};
		
		return netherstone;
	}

	public static BlockPos[] getSoulSand(BlockPos pos) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		BlockPos[] soulsand = new BlockPos[] {new BlockPos(x, y - 2.0D, z + 2.0D), new BlockPos(x + 2.0D, y - 2.0D, z), new BlockPos(x + 2.0D, y - 2.0D, z + 1.0D), new BlockPos(x + 1.0D, y - 2.0D, z + 1.0D),
				  new BlockPos(x + 1.0D, y - 2.0D, z + 2.0D), new BlockPos(x - 1.0D, y - 2.0D, z + 1.0D), new BlockPos(x - 1.0D, y - 2.0D, z + 2.0D), new BlockPos(x - 2.0D, y - 2.0D, z + 1.0D),
				  new BlockPos(x - 2.0D, y - 2.0D, z), new BlockPos(x - 2.0D, y - 2.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 2.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 2.0D, z - 2.0D),
				  new BlockPos(x, y - 2.0D, z - 2.0D), new BlockPos(x + 1.0D, y - 2.0D, z - 2.0D), new BlockPos(x + 1.0D, y - 2.0D, z - 1.0D), new BlockPos(x + 2.0D, y - 2.0D, z - 1.0D)};
		return soulsand;
	}
	
	public static int getMistakes(BlockPos[] netherstone, BlockPos[] soulsand, BlockPos quartz, BlockPos[] obs, World worldIn) {
		int mistakes = 0;
		
		for(int i = 0; i < netherstone.length; i++) {
			BlockPos netherStone = netherstone[i];
			IBlockState netherState = worldIn.getBlockState(netherStone);
			
			if(!(netherState.getBlock().getDefaultState() == BlockInit.NETHER_STONE.getDefaultState())) {
				mistakes++;
			}
			
		}
		
		for(int i = 0; i < soulsand.length; i++) {
			BlockPos soulSand = soulsand[i];
			IBlockState soulSandState = worldIn.getBlockState(soulSand);
			
			if(!(soulSandState.getBlock().getDefaultState() == Blocks.SOUL_SAND.getDefaultState())) {
				mistakes++;
			}
			
		}
		
		if(worldIn.getBlockState(quartz).getBlock().getDefaultState() != Blocks.QUARTZ_BLOCK.getDefaultState()) {
			mistakes++;
		}
		
		for(int i = 0; i < obs.length; i++) {
			BlockPos obsidianPosition = obs[i];
			IBlockState obState = worldIn.getBlockState(obsidianPosition);
			
			if(!(obState.getBlock().getDefaultState() == Blocks.OBSIDIAN.getDefaultState())) {
				mistakes++;
			}
		}
		
		return mistakes;
	}

	public static void spawnLucifer(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		if(!worldIn.isRemote) {
			Entity bolt = new EntityLightningBolt(worldIn, x, y, z, false);
			//worldIn.addWeatherEffect(bolt);
			
			spawnEntities(worldIn, pos);
			
			worldIn.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
			
			replaceNetherStone(worldIn, x, y, z);
			
		}
	}

	private static void spawnEntities(World w, BlockPos pos) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		if(!w.isRemote) {
			w.setWorldTime(18000);
			
			EntityCustomVillager vil = new EntityCustomVillager(w);
			vil.setLocationAndAngles(x, y + 2.0D, z, vil.rotationYaw, vil.rotationPitch);
			w.spawnEntity(vil);
			
			EntityWitherSkeleton entity = new EntityWitherSkeleton(w); EntityWitherSkeleton entity1 = new EntityWitherSkeleton(w); EntityWitherSkeleton entity2 = new EntityWitherSkeleton(w); EntityWitherSkeleton entity3 = new EntityWitherSkeleton(w);
			EntityBlaze blaze = new EntityBlaze(w); EntityBlaze blaze1 = new EntityBlaze(w); EntityBlaze blaze2 = new EntityBlaze(w); EntityBlaze blaze3 = new EntityBlaze(w);
			EntityDemon z0 = new EntityDemon(w); EntityDemon z1 = new EntityDemon(w); EntityDemon z2 = new EntityDemon(w); EntityDemon z3 = new EntityDemon(w);
			EntityDemon z4 = new EntityDemon(w); EntityDemon z5 = new EntityDemon(w);EntityDemon z6 = new EntityDemon(w);EntityDemon z7 = new EntityDemon(w);
			EntityDemon z8 = new EntityDemon(w); EntityDemon z9 = new EntityDemon(w); EntityDemon z10 = new EntityDemon(w); EntityDemon z11 = new EntityDemon(w);
			
			entity.setLocationAndAngles(x, y, z + 2.0D, entity.rotationYaw, entity.rotationPitch); entity1.setLocationAndAngles(x, y, z - 2.0D, entity.rotationYaw, entity.rotationPitch);
			entity2.setLocationAndAngles(x - 2.0D, y, z, entity.rotationYaw, entity.rotationPitch); entity3.setLocationAndAngles(x + 2.0D, y, z, entity.rotationYaw, entity.rotationPitch);
			
			blaze.setLocationAndAngles(x + 2.0D, y + 2.0D, z + 2.0D, entity.rotationYaw, entity.rotationPitch); blaze1.setLocationAndAngles(x - 2.0D, y + 2.0D, z - 2.0D, entity.rotationYaw, entity.rotationPitch);
			blaze2.setLocationAndAngles(x - 2.0D, y + 2.0D, z + 2.0D, entity.rotationYaw, entity.rotationPitch); blaze3.setLocationAndAngles(x + 2.0D, y + 2.0D, z - 2.0D, entity.rotationYaw, entity.rotationPitch);
			
			z0.setLocationAndAngles(x + 2.0D, gH(x + 2.0D, z + 3.0D, w), z + 3.0D, z0.rotationYaw, z0.rotationPitch); z1.setLocationAndAngles(x + 3.0D, gH(x + 3.0D, z + 2.0D, w), z + 2.0D, z1.rotationYaw, z1.rotationPitch);
			z2.setLocationAndAngles(x + 3.0D, gH(x + 3.0D, z + 2.0D, w), z + 2.0D, z2.rotationYaw, z2.rotationPitch); z3.setLocationAndAngles(x - 2.0D, gH(x - 2.0D, z + 3.0D, w), z + 3.0D, z3.rotationYaw, z3.rotationPitch);
			z4.setLocationAndAngles(x - 3.0D, gH(x - 3.0D, z + 3.0D, w), z + 3.0D, z4.rotationYaw, z4.rotationPitch); z5.setLocationAndAngles(x - 3.0D, gH(x - 3.0D, z + 3.0D, w), z + 2.0D, z5.rotationYaw, z5.rotationPitch);
			z6.setLocationAndAngles(x - 2.0D, gH(x - 2.0D, z - 3.0D, w), z - 3.0D, z6.rotationYaw, z6.rotationPitch); z7.setLocationAndAngles(x - 3.0D, gH(x - 3.0D, z - 3.0D, w), z - 3.0D, z7.rotationYaw, z7.rotationPitch);
			z8.setLocationAndAngles(x - 3.0D, gH(x - 3.0D, z - 2.0D, w), z - 2.0D, z8.rotationYaw, z8.rotationPitch); z9.setLocationAndAngles(x + 2.0D, gH(x + 2.0D, z - 3.0D, w), z - 3.0D, z9.rotationYaw, z9.rotationPitch);
			z10.setLocationAndAngles(x + 3.0D, gH(x + 3.0D, z - 3.0D, w), z - 3.0D, z10.rotationYaw, z10.rotationPitch); z11.setLocationAndAngles(x + 3.0D, gH(x + 3.0D, z - 2.0D, w), z - 2.0D, z11.prevRotationYaw, z11.prevRotationPitch);
				
				
			w.spawnEntity(entity); w.spawnEntity(entity1); w.spawnEntity(entity2); w.spawnEntity(entity3);
			w.spawnEntity(blaze1); w.spawnEntity(blaze); w.spawnEntity(blaze3); w.spawnEntity(blaze2);
			w.spawnEntity(z0); w.spawnEntity(z1); w.spawnEntity(z2); w.spawnEntity(z3); w.spawnEntity(z4); w.spawnEntity(z5); w.spawnEntity(z6); w.spawnEntity(z7);
			w.spawnEntity(z8); w.spawnEntity(z9); w.spawnEntity(z10); w.spawnEntity(z11);
			
			((EntityLiving)entity).onInitialSpawn(w.getDifficultyForLocation(new BlockPos(entity)), (IEntityLivingData)null);((EntityLiving)entity1).onInitialSpawn(w.getDifficultyForLocation(new BlockPos(entity1)), (IEntityLivingData)null);
			((EntityLiving)entity).onInitialSpawn(w.getDifficultyForLocation(new BlockPos(entity2)), (IEntityLivingData)null);((EntityLiving)entity).onInitialSpawn(w.getDifficultyForLocation(new BlockPos(entity3)), (IEntityLivingData)null);
			
			
		}
	}
	
	private static double gH(double x, double z, World w) {
		return (double) w.getHeight((int)x, (int)z);
	}
	
	private static void replaceNetherStone(World world, double x, double y, double z) {
		BlockPos[] netherstone = new BlockPos[] {new BlockPos(x + 2.0D, y - 1.0D ,z + 2.0D), new BlockPos(x + 2.0D, y, z + 2.0D), new BlockPos(x + 2.0D, y + 1.0D ,z + 2.0D),
				 new BlockPos(x - 2.0D, y - 1.0D ,z + 2.0D), new BlockPos(x - 2.0D, y, z + 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z + 2.0D),
				 new BlockPos(x - 2.0D, y - 1.0D ,z - 2.0D), new BlockPos(x - 2.0D, y, z - 2.0D), new BlockPos(x - 2.0D, y + 1.0D ,z - 2.0D),
				 new BlockPos(x + 2.0D, y - 1.0D ,z - 2.0D), new BlockPos(x + 2.0D, y, z - 2.0D), new BlockPos(x + 2.0D, y + 1.0D ,z - 2.0D)};
		
		for(int i = 0; i < netherstone.length; i++) {
			world.setBlockState(netherstone[i], Blocks.MAGMA.getDefaultState());
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
