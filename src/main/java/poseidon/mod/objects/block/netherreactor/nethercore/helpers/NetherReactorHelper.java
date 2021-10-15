package poseidon.mod.objects.block.netherreactor.nethercore.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.general.tileHelpers.TileEntityBounce;
import poseidon.mod.objects.block.netherreactor.nethercore.crates.TileEntityCrate;
import poseidon.mod.objects.block.riftblock.BlockRift;
import poseidon.mod.objects.block.riftblock.TileEntityRiftTeleporter;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;

public class NetherReactorHelper {

	public static double[] getStartingPosses(BlockPos core, int stage, int index) {
		
		double x = core.getX(); double y = core.getY(); double z = core.getZ();
		
		
		
		if(stage == 0) {
			
			//There are 2 indexes. We return without checking because the startings are the same for this one.
			return new double[] {x + 2.0D, y, z + 0.5D, x + 0.5D, y, z + 2.0D, x - 1.0D, y, z + 0.5D, x + 0.5D, y, z - 1.0D};
			
		}
		
		if(stage == 1) {
			//Starting of 1 (1) = ending 0 (1)
			return new double[] {x + 2.0D, y + 1.5D, z + 2.0D, x - 1.0D, y + 1.5D, z + 2.0D, x - 1.0D, y + 1.5D, z - 1.0D, x + 2.0D, y + 1.5D, z - 1.0D};
		}
		
		if(stage == 2) {
											//                        |                      |                      |
			if(index == 1) return new double[] {x + 2.0D, y, z + 0.5D, x + 0.5D, y, z + 2.0D, x - 1.0D, y, z + 0.5D, x + 0.5D, y, z - 1.0D};
			if(index == 2) return new double[] {x + 2.0D, y, z + 2.0D, x - 1.0D, y, z + 2.0D, x - 1.0D, y, z - 1.0D, x + 2.0D, y, z - 1.0D};
			if(index == 3) return new double[] {x + 3.5D, y, z - 1.0D, x + 3.5D, y, z + 2.0D, x + 2.0D, y, z + 3.5D, x - 1.0D, y, z + 3.5D,
												x - 2.5D, y, z + 2.0D, x - 2.5D, y, z - 1.0D, x - 1.0D, y, z - 2.5D, x + 2.0D, y, z - 2.5D,
												
												x + 3.5D, y, z - 1.0D, x + 3.5D, y, z + 2.0D, x + 2.0D, y, z + 3.5D, x - 1.0D, y, z + 3.5D,
												x - 2.5D, y, z + 2.0D, x - 2.5D, y, z - 1.0D, x - 1.0D, y, z - 2.5D, x + 2.0D, y, z - 2.5D};
			if(index == 4) return new double[] {x + 2.0D, y + 3.0D, z + 0.5D, x + 0.5D, y + 3.0D, z + 2.0D, x - 1.0D, y + 3.0D, z + 0.5D, x + 0.5D, y + 3.0D, z - 1.0D};
			if(index == 5) return new double[] {x + 3.5D, y, z - 1.0D, x + 3.5D, y, z + 2.0D, x + 2.0D, y, z + 3.5D, x - 1.0D, y, z + 3.5D,
												x - 2.5D, y, z + 2.0D, x - 2.5D, y, z - 1.0D, x - 1.0D, y, z - 2.5D, x + 2.0D, y, z - 2.5D};
			
		}
		
		if(stage == 3) {
			
			//Starting pos of the third stage is the ending pos of the second stage!
			//index was used in the variable type. Both starting points are the same!
			getEndingPosses(core, stage - 1, index);
			
			
		}
		
		if(stage == 4) {
			return new double[] {x + 5.5D, y + 3.0D, z + 5.5D, x - 4.5D, y + 3.0D, z + 5.5D, x - 4.5D, y + 3.0D, z - 4.5D, x + 5.5D, y + 3.0D, z - 4.5D,
								
								 x + 5.5D, y + 1.0D, z + 0.5D, x + 5.5D, y + 1.0D, z + 0.5D, x + 0.5D, y + 1.0D, z + 5.5D, x + 0.5D, y + 1.0D, z + 5.5D, 
								 x - 4.5D, y + 1.0D, z + 0.5D, x - 4.5D, y + 1.0D, z + 0.5D, x + 0.5D, y + 1.0D, z - 4.5D, x + 0.5D, y + 1.0D, z - 4.5D};
		}
		
		if(stage == 5) {
			
			if(index == 1) return new double[] {x + 2.0D, y + 2.0D, z + 2.0D, x + 2.0D, y + 2.0D, z + 2.0D,
					             x - 1.0D, y + 2.0D, z + 2.0D, x - 1.0D, y + 2.0D, z + 2.0D,
					             x - 1.0D, y + 2.0D, z - 1.0D, x - 1.0D, y + 2.0D, z - 1.0D,
					             x + 2.0D, y + 2.0D, z - 1.0D, x + 2.0D, y + 2.0D, z - 1.0D,};
			if(index == 2) return new double[] {             
					             x + 2.0D, y + 2.0D, z + 2.0D, x - 1.0D, y + 2.0D, z + 2.0D, x - 1.0D, y + 2.0D, z - 1.0D, x + 2.0D, y + 2.0D, z - 1.0D,
					             x + 2.0D, y - 1.0D, z + 2.0D, x - 1.0D, y - 1.0D, z + 2.0D, x - 1.0D, y - 1.0D, z - 1.0D, x + 2.0D, y - 1.0D, z - 1.0D,
					             
					             x + 2.0D, y - 0.9D, z + 2.0D, x + 2.0D, y - 0.9D, z + 2.0D, x - 1.0D, y - 0.9D, z + 2.0D, x - 1.0D, y - 0.9D, z + 2.0D,
					             x - 1.0D, y - 0.9D, z - 1.0D, x - 1.0D, y - 0.9D, z - 1.0D, x + 2.0D, y - 0.9D, z - 1.0D, x + 2.0D, y - 0.9D, z - 1.0D
			};
			
		}
		
		if(stage == 6) {
			if(index == 1) return new double[] {x + 2.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z - 1.0D, x + 2.0D, y + 0.5D, z - 1.0D,
												x + 2.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z - 1.0D, x + 2.0D, y + 0.5D, z - 1.0D,
												x + 2.0D, y + 2.0D, z + 0.5D, x + 0.5D, y + 2.0D, z + 2.0D, x - 1.0D, y + 2.0D, z + 0.5D, x + 0.5D, y + 2.0D, z - 1.0D,
												x + 2.0D, y - 0.9D, z + 0.5D, x + 0.5D, y - 0.9D, z + 2.0D, x - 1.0D, y - 0.9D, z + 0.5D, x + 0.5D, y - 0.9D, z - 1.0D};
			if(index == 2) return new double[] {x + 2.0D, y + 2.0D, z + 0.5D, x + 0.5D, y + 2.0D, z + 2.0D, 
					 x + 0.5D, y + 2.0D, z - 1.0D, x - 1.0D, y + 2.0D, z + 0.5D,};
		}
		
		if(stage == 7) {
			return new double[] {x + 2.0D, y + 0.5D, z + 1.0D, x + 2.0D, y + 0.5D, z, x + 1.0D, y + 0.5D, z + 2.0D, x, y + 0.5D, z + 2.0D, 
								 x - 1.0D, y + 0.5D, z + 1.0D, x - 1.0D, y + 0.5D, z, x, y + 0.5D, z - 1.0D, x + 1.0D, y + 0.5D, z - 1.0D,
								 x + 2.0D, y + 1.0D, z + 0.5D, x + 0.5D, y + 1.0D, z + 2.0D, x - 1.0D, y + 1.0D, z + 0.5D, x + 0.5D, y + 1.0D, z - 1.0D};
		}
		
		if(stage == 8) {
			return new double[] { x + 5.0D, y, z + 0.5D, x + 0.5D, y, z + 5.0D, x - 4.0D, y, z + 0.5D, x + 0.5D, y, z - 4.0D};
		}
		
		if(stage == 9) {
			return new double[] {x + 0.5D, y, z + 5.0D, x - 4.0D, y, z + 0.5D, x + 0.5D, y, z - 4.0D, x + 5.0D, y, z + 0.5D};
		}
		
		if(stage == 10) {
			return new double[] {x + 0.5D, y, z + 5.5D, x - 4.5D, y, z + 0.5D, x + 0.5D, y, z - 4.5D, x + 5.5D, y, z + 0.5D, 
					x - 4.0D, y + 2.0D, z - 4.0D,x - 4.0D, y + 2.0D, z - 4.0D,x + 5.0D, y + 2.0D, z - 4.0D, x + 5.0D, y + 2.0D, z - 4.0D,x + 5.0D, y + 2.0D, z + 5.0D,
					x + 5.0D, y + 2.0D, z + 5.0D, x - 4.0D, y + 2.0D, z + 5.0D, x - 4.0D, y + 2.0D, z + 5.0D};
		}
	
		System.out.println("Oof: " + stage + ", <- stage not found so returned empty array!");
		return new double[] {};
		
	}
	
	public static double[] getEndingPosses(BlockPos core, int stage, int index) {
		
		double x = core.getX(); double y = core.getY(); double z = core.getZ();
		
		if(stage == 0) {
			
			//Endings of 0 (1) are the same. We take the block right of the starting point.

			if(index == 1) return new double[] {x + 2.0D, y + 1.5D, z + 2.0D, x - 1.0D, y + 1.5D, z + 2.0D, x - 1.0D, y + 1.5D, z - 1.0D, x + 2.0D, y + 1.5D, z - 1.0D};
			if(index == 2) return new double[] {x + 2.0D, y + 1.5D, z - 1.0D, x + 2.0D, y + 1.5D, z + 2.0D, x - 1.0D, y + 1.5D, z + 2.0D, x - 1.0D, y + 1.5D, z - 1.0D};
			
		}
		
		if(stage == 1) {
			 
			if(index == 1) return new double[] {x + 2.0D, y + 3.0D, z + 0.5D, x + 0.5D, y + 3.0D, z + 2.0D, x - 1.0D, y + 3.0D, z + 0.5D, x + 0.5D, y + 3.0D, z - 1.0D};
			if(index == 2) return new double[] {x + 0.5D, y + 3.0D, z + 2.0D, x - 1.0D, y + 3.0D, z + 0.5D, x + 0.5D, y + 3.0D, z - 1.0D, x + 2.0D, y + 3.0D, z + 0.5D};
			
			
		}
		
		if(stage == 2) {
											//                        |                      |                      |
			if(index == 1) return new double[] {x + 5.0D, y, z + 0.5D, x + 0.5D, y, z + 5.0D, x - 4.0D, y, z + 0.5D, x + 0.5D, y, z - 4.0D};
			if(index == 2) return new double[] {x + 5.0D, y, z + 5.0D, x - 4.0D, y, z + 5.0D, x - 4.0D, y, z - 4.0D, x + 5.0D, y, z - 4.0D};
			if(index == 3) return new double[] {x + 5.0D, y, z - 4.0D, x + 5.0D, y, z + 5.0D, x + 5.0D, y, z + 5.0D, x - 4.0D, y, z + 5.0D,
												x - 4.0D, y, z + 5.0D, x - 4.0D, y, z - 4.0D, x - 4.0D, y, z - 4.0D, x + 5.0D, y, z - 4.0D,
												x + 5.0D, y, z + 0.5D, x + 5.0D, y, z + 0.5D, x + 0.5D, y, z + 5.0D,  x + 0.5D, y, z + 5.0D,
												x - 4.0D, y, z + 0.5D, x - 4.0D, y, z + 0.5D, x + 0.5D, y, z - 4.0D,  x + 0.5D, y, z - 4.0D};
			if(index == 4) return new double[] {x + 5.0D, y + 0.1D, z + 0.5D, x + 0.5D, y + 0.1D, z + 5.0D, x - 4.0D, y + 0.1D , z + 0.5D, x + 0.5D, y + 0.1D, z - 4.0D};
			
		}
		
		if(stage == 3) {
			
			if(index == 1) return new double[] {x + 4.0D, y, z + 2.0D, x + 0.5D, y, z + 4.0D, x - 3.0D, y, z + 0.5D, x + 0.5D, y, z - 3.0D};
			
			
		}
		
		if(stage == 4) {
			return new double[] {x + 2.0D, y + 3.0D, z + 2.0D, x - 1.0D, y + 3.0D, z + 2.0D, x - 1.0D, y + 3.0D, z - 1.0D, x + 2.0D, y + 3.0D, z - 1.0D,
								 x + 2.0D, y + 3.0D, z + 2.0D, x + 2.0D, y + 3.0D, z - 1.0D, x + 2.0D, y + 3.0D, z + 2.0D, x - 1.0D, y + 3.0D, z + 2.0D,
								 x - 1.0D, y + 3.0D, z + 2.0D, x - 1.0D, y + 3.0D, z - 1.0D, x - 1.0D, y + 3.0D, z - 1.0D, x + 2.0D, y + 3.0D, z - 1.0D
								 };
		}
		
		if(stage == 5) {
			if(index == 1) return new double[] {x + 2.0D, y + 2.0D, z + 0.5D, x + 0.5D, y + 2.0D, z + 2.0D, 
								 x + 0.5D, y + 2.0D, z + 2.0D, x - 1.0D, y + 2.0D, z + 0.5D,
								 x - 1.0D, y + 2.0D, z + 0.5D, x + 0.5D, y + 2.0D, z - 1.0D,
								 x + 0.5D, y + 2.0D, z - 1.0D, x + 2.0D, y + 2.0D, z + 0.5D};
			if(index == 2) return new double[] {

								 x + 2.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z - 1.0D, x + 2.0D, y + 0.5D, z - 1.0D,
								 x + 2.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z - 1.0D, x + 2.0D, y + 0.5D, z - 1.0D,
								 
								 x + 0.5D, y - 0.9D, z + 2.0D, x + 2.0D, y - 0.9D, z + 0.5D, x + 0.5D, y - 0.9D, z + 2.0D, x - 1.0D, y - 0.9D, z + 0.5D,
								 x - 1.0D, y - 0.9D, z + 0.5D, x + 0.5D, y - 0.9D, z - 1.0D, x + 0.5D, y - 0.9D, z - 1.0D, x + 2.0D, y - 0.9D, z + 0.5D
			};
		}
		
		if(stage == 6) {
//			if(index == 1) return new double[] {x + 2.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z - 1.0D, x + 2.0D, y + 0.5D, z - 1.0D,
//												x + 2.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z + 2.0D, x - 1.0D, y + 0.5D, z - 1.0D, x + 2.0D, y + 0.5D, z - 1.0D,
//					x + 2.0D, y + 2.0D, z + 0.5D, x + 0.5D, y + 2.0D, z + 2.0D, x - 1.0D, y + 2.0D, z + 0.5D, x + °.5D, y + 2.0D, z - 1.0D,
//					x + 2.0D, y - 0.9D, z + 0.5D, x + 0.5D, y - 0.9D, z + 2.0D, x - 1.0D, y - 0.9D, z + 0.5D, x + 0.5D, y - 0.9D, z - 1.0D};
//			
			if(index == 1) return new double[] {x + 1.0D, y + 0.5D, z + 2.0D, x, y + 0.5D, z + 2.0D, x, y + 0.5D, z - 1.0D, x + 1.0D, y + 0.5D, z - 1.0D,
												x + 2.0D, y + 0.5D, z + 1.0D, x - 1.0D, y + 0.5D, z + 1.0D, x - 1.0D, y + 0.5D, z, x + 2.0D, y + 0.5D, z,
												x + 2.0D, y + 1.0D, z + 0.5D, x + 0.5D, y + 1.0D, z + 2.0D, x - 1.0D, y + 1.0D, z + 0.5D, x + 0.5D, y + 1.0D, z - 1.0D,
												x + 1.0D, y - 0.9D, z + 0.5D, x + 0.5D, y - 0.9D, z + 1.0D, x, y - 0.9D, z + 0.5D, x + 0.5D, y - 0.9D, z};
			if(index == 2) return new double[] {x + 0.5D, y + 4.0D, z + 0.5D,x + 0.5D, y + 4.0D, z + 0.5D, x + 0.5D, y + 4.0D, z + 0.5D, x + 0.5D, y + 4.0D, z + 0.5D};
			
		}
		
		if(stage == 7) {
			y = y - 1.0D;
			return new double[] {x + 5.0D, y + 1.0D, z + 0.5D, x + 5.0D, y + 1.0D, z + 0.5D,
								 x + 0.5D, y + 1.0D, z + 5.0D, x + 0.5D, y + 1.0D, z + 5.0D,
								 x - 4.0D, y + 1.0D, z + 0.5D, x - 4.0D, y + 1.0D, z + 0.5D,
								 x + 0.5D, y + 1.0D, z - 4.0D, x + 0.5D, y + 1.0D, z - 4.0D,
								 x + 5.0D, y + 1.0D, z + 0.5D, 
								 x + 0.5D, y + 1.0D, z + 5.0D,
								 x - 4.0D, y + 1.0D, z + 0.5D,
								 x + 0.5D, y + 1.0D, z - 4.0D,};
		}
		
		if(stage == 8) {
			return new double[] {x + 0.5D, y, z + 5.0D, x - 4.0D, y, z + 0.5D, x + 0.5D, y, z - 4.0D, x + 5.0D, y, z + 0.5D};
			
		}
		
		if(stage == 9) {
			return new double[] {x - 4.5D, y + 1.0D, z - 4.5D, x + 5.5D, y + 1.0D, z - 4.5D, x + 5.5D, y + 1.0D, z + 5.5D, x - 4.5D, y + 1.0D, z + 5.5D};
		}
		
		if(stage == 10) {
			
			/*
			 * 			return new double[] {x + 0.5D, y, z + 5.D, x - 4.0D, y, z + 0.5D, x + 0.5D, y, z - 4.0D, x + 5.0D, y, z + 0.5D, 
					x - 4.5D, y + 1.0D, z - 4.5D, x + 5.5D, y + 1.0D, z - 4.5D, x + 5.5D, y + 1.0D, z + 5.5D, x - 4.5D, y + 1.0D, z + 5.5D};
			 */
			y = y + 3.5D;
			return new double[] {x + 0.5D, y, z + 1.0D, x, y, z + 0.5D, x + 0.5D, y, z, x + 1.0D, y, z + 0.5D,
								 x + 0.5D, y, z, x, y, z + 0.5D, 
								 x + 0.5D, y, z, x + 1.0D, y, z + 0.5D, 
								 x + 1.0D, y, z + 0.5D, x + 0.5D, y, z + 1.0D, 
								 x + 0.5D, y, z + 1.0D, x, y, z + 0.5D};
		}
	
		
		System.out.println("Oof: " + stage + ", <- stage not found so returned empty array!");
		return new double[] {};
		
	}
	
	public static List<BlockPos> getListAndRemove(BlockPos[] array, List<BlockPos> added) {
		List<BlockPos> list = new ArrayList<BlockPos>();
		for(int i = 0; i < array.length; i++) {
			if(!added.contains(array[i])) list.add(array[i]);
		}
		return list;
	}

	public static void setParticleBeams(int realTicks, World world, int passedTicks, BlockPos[] f, BlockPos[] t) {
		if(realTicks >= 200 && realTicks < 250) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[3], t[0], passedTicks - 200);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[0], t[1], passedTicks - 200);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[1], t[2], passedTicks - 200);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[2], t[3], passedTicks - 200);
		}
		
		if(realTicks >= 250 && realTicks < 300) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[0], f[0], passedTicks - 250);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[2], f[2], passedTicks - 250);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[1], f[1], passedTicks - 250);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[3], f[3], passedTicks - 250);
		}
		
		if(realTicks >= 300 && realTicks < 350) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[0], t[1], passedTicks - 300);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[1], t[2], passedTicks - 300);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[3], t[0], passedTicks - 300);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[2], t[3], passedTicks - 300);
		}
		
		if(realTicks >= 350 && realTicks < 400) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[1], f[1], passedTicks - 350);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[2], f[2], passedTicks - 350);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[3], f[3], passedTicks - 350);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[0], f[0], passedTicks - 350);
		}
		
		//reached eachothers starts
		if(realTicks >= 400 && realTicks < 450) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[1], t[2], passedTicks - 400);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[3], t[0], passedTicks - 400);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[2], t[3], passedTicks - 400);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[0], t[1], passedTicks - 400);
		}
		
		if(realTicks >= 450 && realTicks < 500) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[0], f[0], passedTicks - 450);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[1], f[1], passedTicks - 450);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[2], f[2], passedTicks - 450);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[3], f[3], passedTicks - 450);
		}
		
		if(realTicks >= 500 && realTicks < 550) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[2], t[3], passedTicks - 500);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[3], t[0], passedTicks - 500);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[0], t[1], passedTicks - 500);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[1], t[2], passedTicks - 500);
		}
		
		if(realTicks >= 550 && realTicks < 600) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[0], f[0], passedTicks - 550);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[1], f[1], passedTicks - 550);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[2], f[2], passedTicks - 550);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[3], f[3], passedTicks - 550);
		}
		
		if(realTicks >= 600 && realTicks < 650) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[1], t[2], passedTicks - 600);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[2], t[3], passedTicks - 600);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[3], t[0], passedTicks - 600);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[0], t[1], passedTicks - 600);
		}
		
		if(realTicks >= 650 && realTicks < 700) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[1], f[1], passedTicks - 650);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[2], f[2], passedTicks - 650);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[3], f[3], passedTicks - 650);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[0], f[0], passedTicks - 650);
		}
		
		if(realTicks >= 700 && realTicks < 750) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[1], t[2], passedTicks - 700);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[2], t[3], passedTicks - 700);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[3], t[0], passedTicks - 700);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, f[0], t[1], passedTicks - 700);
		}
		
		if(realTicks >= 750 && realTicks < 800) {
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[1], f[1], passedTicks - 750);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[2], f[2], passedTicks - 750);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[3], f[3], passedTicks - 750);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[0], f[0], passedTicks - 750);
			
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[1], f[0], passedTicks - 750);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[2], f[1], passedTicks - 750);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[3], f[2], passedTicks - 750);
			NetherReactorSpawner.netherReactorConnect((WorldServer) world, t[0], f[3], passedTicks - 750);
			
		}
		
		
	}
	
	public static void keepConnections(WorldServer world, BlockPos[] f, BlockPos[] t, int choice) {
		if(choice == 1) {
			NetherReactorSpawner.netherConnection((WorldServer) world, t[2], f[2]);
			NetherReactorSpawner.netherConnection((WorldServer) world, t[2], f[1]);
			NetherReactorSpawner.netherConnection((WorldServer) world, t[0], f[0]);
			NetherReactorSpawner.netherConnection((WorldServer) world, t[0], f[3]);
		}
		if(choice == 2) {
			NetherReactorSpawner.netherConnection((WorldServer) world, t[2], f[2]);
			NetherReactorSpawner.netherConnection((WorldServer) world, t[2], f[1]);
			NetherReactorSpawner.netherConnection((WorldServer) world, t[0], f[0]);
			NetherReactorSpawner.netherConnection((WorldServer) world, t[0], f[3]);
			
			NetherReactorSpawner.netherConnection((WorldServer) world, f[2], t[3]);
			NetherReactorSpawner.netherConnection((WorldServer) world, f[0], t[1]);
			NetherReactorSpawner.netherConnection((WorldServer) world, f[3], t[3]);
			NetherReactorSpawner.netherConnection((WorldServer) world, f[1], t[1]);
		}
	}
	
	public static void slab(World world, BlockPos pos, WorldServer world2) {
		world.setBlockState(pos.up(3).east().north(), Blocks.STONE_SLAB.getStateForPlacement(world, pos.up(3).east().north(), EnumFacing.UP, 0.5F, 1.0F, 0.5F, 7, null));
		world.setBlockState(pos.up(3).west().north(), Blocks.STONE_SLAB.getStateForPlacement(world, pos.up(3).west().north(), EnumFacing.UP, 0.5F, 1.0F, 0.5F, 7, null));
		world.setBlockState(pos.up(3).east().south(), Blocks.STONE_SLAB.getStateForPlacement(world, pos.up(3).east().south(), EnumFacing.UP, 0.5F, 1.0F, 0.5F, 7, null));
		world.setBlockState(pos.up(3).south().west(), Blocks.STONE_SLAB.getStateForPlacement(world, pos.up(3).south().west(), EnumFacing.UP, 0.5F, 1.0F, 0.5F, 7, null));
	}
	
	public static void slab2(World world, BlockPos pos, WorldServer world2, int i) {
		if(i == 2)world.setBlockState(pos.up(5), Blocks.STONE_SLAB.getStateForPlacement(world, pos.up(5), EnumFacing.DOWN, 0.5F, 1.0F, 0.5F, 7, null));
		if(i == 1)world.setBlockState(pos.up(4), Blocks.STONE_SLAB.getStateForPlacement(world, pos.up(4), EnumFacing.DOWN, 0.5F, 1.0F, 0.5F, 7, null));
	}
	
	public static void setCrystals(World world, double x, double y, double z) {
		
		EntityEnderCrystal crystla1 = new EntityEnderCrystal(world, x + 0.5D, y + 6.0D, z + 0.5D);
		world.spawnEntity(crystla1);
		
		y = y - 1.0D;
		
		EntityEnderCrystal crystla2 = new EntityEnderCrystal(world, x + 5.5D, y + 5.0D, z + 5.5D);
		world.spawnEntity(crystla2);
		crystla2.setBeamTarget(new BlockPos(x + 0.5D, y + 5.0D, z + 0.5D));
		world.setBlockState(new BlockPos(x + 5.5D, y + 4.0D, z + 5.5D), Blocks.STONE_SLAB.getStateForPlacement(world, new BlockPos(x + 5.5D, y + 2.0D, z + 5.5D), EnumFacing.DOWN, 0.5F, 1.0F, 0.5F, 6, null));
		
		EntityEnderCrystal crystla3 = new EntityEnderCrystal(world, x - 4.5D, y + 5.0D, z + 5.5D);
		world.spawnEntity(crystla3);
		crystla3.setBeamTarget(new BlockPos(x + 0.5D, y + 5.0D, z + 0.5D));
		world.setBlockState(new BlockPos(x - 4.5D, y + 4.0D, z + 5.5D), Blocks.STONE_SLAB.getStateForPlacement(world, new BlockPos(x - 4.5D, y + 2.0D, z + 5.5D), EnumFacing.DOWN, 0.5F, 1.0F, 0.5F, 6, null));
		
		
		EntityEnderCrystal crystla4 = new EntityEnderCrystal(world, x + 5.5D, y + 5.0D, z - 4.5D);
		world.spawnEntity(crystla4);
		crystla4.setBeamTarget(new BlockPos(x + 0.5D, y + 5.0D, z + 0.5D));
		world.setBlockState(new BlockPos(x + 5.5D, y + 4.0D, z - 4.5D), Blocks.STONE_SLAB.getStateForPlacement(world, new BlockPos(x + 5.5D, y + 2.0D, z -4.5D), EnumFacing.DOWN, 0.5F, 1.0F, 0.5F, 6, null));
		
		
		EntityEnderCrystal crystla5 = new EntityEnderCrystal(world, x - 4.5D, y + 5.0D, z - 4.5D);
		world.spawnEntity(crystla5);
		crystla5.setBeamTarget(new BlockPos(x + 0.5D, y + 5.0D, z + 0.5D));
		world.setBlockState(new BlockPos(x - 4.5D, y + 4.0D, z -4.5D), Blocks.STONE_SLAB.getStateForPlacement(world, new BlockPos(x - 4.5D, y + 2.0D, z -4.5D), EnumFacing.DOWN, 0.5F, 1.0F, 0.5F, 6, null));
		
		
	}

	public static void setItemAir(World world, BlockPos pos) {
		world.setBlockState(pos.north(), BlockInit.ITEM_AIR.getDefaultState());
		world.setBlockState(pos.south(), BlockInit.ITEM_AIR.getDefaultState());
		world.setBlockState(pos.east(), BlockInit.ITEM_AIR.getDefaultState());
		world.setBlockState(pos.west(), BlockInit.ITEM_AIR.getDefaultState());
		
		if(world.getTileEntity(pos.north()) instanceof TileEntityItemAir) {
			TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.north());
			a.i1 = Items.GHAST_TEAR;
			a.i2 = Items.NETHER_WART;
			a.i3 = Items.BLAZE_ROD;
			a.i4 = Items.MAGMA_CREAM;
			a.function = 1;
		}
		
		if(world.getTileEntity(pos.south()) instanceof TileEntityItemAir) {
			TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.south());
			a.i1 = Items.GHAST_TEAR;
			a.i2 = Items.NETHER_WART;
			a.i3 = Items.BLAZE_ROD;
			a.i4 = Items.MAGMA_CREAM;
			a.function = 1;
			
		}
		
		if(world.getTileEntity(pos.east()) instanceof TileEntityItemAir) {
			TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.east());
			a.i1 = Items.GHAST_TEAR;
			a.i2 = Items.NETHER_WART;
			a.i3 = Items.BLAZE_ROD;
			a.i4 = Items.MAGMA_CREAM;
			a.function = 1;
		}
		
		if(world.getTileEntity(pos.west()) instanceof TileEntityItemAir) {
			TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.west());
			a.i1 = Items.GHAST_TEAR;
			a.i2 = Items.NETHER_WART;
			a.i3 = Items.BLAZE_ROD;
			a.i4 = Items.MAGMA_CREAM;
			a.function = 1;
		}
		
	}
	
	public static boolean[] itemAirBoolean(World world, BlockPos pos, int i) {
		
		if(i >= 1 && i <= 4) {
			if(i == 1) {
				if(world.getTileEntity(pos.north()) instanceof TileEntityItemAir) {
					TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.north());
					return new boolean[] {a.found1, a.found2, a.found3, a.found4};
				}
			}
			
			if(i == 2) {
				if(world.getTileEntity(pos.south()) instanceof TileEntityItemAir) {
					TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.south());
					return new boolean[] {a.found1, a.found2, a.found3, a.found4};
				}
			}
			
			if(i == 3) {
				if(world.getTileEntity(pos.west()) instanceof TileEntityItemAir) {
					TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.west());
					return new boolean[] {a.found1, a.found2, a.found3, a.found4};
				}
			}
			
			if(i == 4) {
				if(world.getTileEntity(pos.east()) instanceof TileEntityItemAir) {
					TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.east());
					return new boolean[] {a.found1, a.found2, a.found3, a.found4};
				}
			}
		} else {
			System.out.println("i not recognized : " + i + ", sorry returned empty list");
			return new boolean[] {};
		}
		System.out.println("i not recognized : " + i + ", sorry returned empty list");
		return new boolean[] {};
	}
	
	public static boolean hasAll(boolean[] a1, boolean[] a2, boolean[] a3, boolean[] a4) {
		List<Boolean> list = new ArrayList<Boolean>();
		if(a1.length == a2.length && a2.length == a3.length && a3.length == a4.length) {
			for(int i = 0; i < 4; i++) {
				if(a1[i] || a2[i] || a3[i] || a4[i]) list.add(true);
				else list.add(false);
			}
		}
		
		if(list.contains(false)) return false;
		else {
			System.out.println("Returned true?");
			return true;
		}
	}
	
	public static void spawnParticles(BlockPos pos, World world) {
		if(!world.isRemote) {
			ParticleUtil.teleportParticles((WorldServer)world, pos.north());
			ParticleUtil.teleportParticles((WorldServer)world, pos.east());
			ParticleUtil.teleportParticles((WorldServer)world, pos.west());
			ParticleUtil.teleportParticles((WorldServer)world, pos.south());
		}
	}
	
	public static void setAir(World world, BlockPos pos) {
		world.setBlockState(pos.north(), Blocks.AIR.getDefaultState());
		world.setBlockState(pos.south(), Blocks.AIR.getDefaultState());
		world.setBlockState(pos.east(), Blocks.AIR.getDefaultState());
		world.setBlockState(pos.west(), Blocks.AIR.getDefaultState());
	}
	
	public static void setRemove(World world, BlockPos pos) {
		if(world.getTileEntity(pos.north()) instanceof TileEntityItemAir) {
			TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.north());
			a.remove = true;
		}
		
		if(world.getTileEntity(pos.south()) instanceof TileEntityItemAir) {
			TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.south());
			a.remove = true;
			
		}
		
		if(world.getTileEntity(pos.east()) instanceof TileEntityItemAir) {
			TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.east());
			a.remove = true;
		}
		
		if(world.getTileEntity(pos.west()) instanceof TileEntityItemAir) {
			TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(pos.west());
			a.remove = true;
		}
	}
	
	public static void setPortals(World world, BlockPos pos) {
		
		BlockPos[] rifts = new BlockPos[] {pos.north(15).west(15), pos.north(15).east(15), pos.west(15).south(15), pos.east(15).south(15)};
		BlockPos[] riftd = new BlockPos[] {pos.north(6).west(6), pos.north(6).east(6), pos.west(6).south(6), pos.east(6).south(6)};
		
		for(int i = 0; i < rifts.length; i++) {
			
			world.setBlockState(rifts[i], BlockInit.RIFT_BLOCK.getDefaultState());
			world.setBlockState(rifts[i].down(), Blocks.REDSTONE_BLOCK.getDefaultState());
			
			if(world.getTileEntity(rifts[i]) instanceof TileEntityRiftTeleporter) {
				TileEntityRiftTeleporter rift = (TileEntityRiftTeleporter) world.getTileEntity(rifts[i]);
				rift.x = riftd[i].getX();
				rift.y = pos.getY() - 1;
				rift.z = riftd[i].getZ();
				//rift.dimension = world.provider.getDimension();
				rift.init = true;
				rift.send = true;
				
				for(int j = 1; j < 4; j++) {
					world.setBlockState(rifts[i].up(j), BlockInit.RIFT_AIR.getDefaultState());
				}
				
			}
			
		}
		
		
	}
	
	public static void setDoorsLowerLevel(World world, BlockPos pos) {
		
		List<BlockPos> doors = new ArrayList<BlockPos>();
		
		double x = pos.getX(); 
		double y = pos.getY(); 
		double z = pos.getZ();
		
		for(int i = -1; i < 2; i++) {
			
			for(int j = -1; j < 2; j++) {
				
				for(int k = 0; k < 4; k++) {
					
					doors.add(new BlockPos(x + (9*i), y + k, z + (9*j)));
					doors.add(new BlockPos(x + (i*9), y + k, z + (j*9)));
					doors.add(new BlockPos(x + i, y + k, z + (j*9)));
					doors.add(new BlockPos(x + (i*9), y + k, z + j));
					
				}
				
			}
			
		}
		
		for(int i = 0; i < doors.size(); i++) {
			if(world.getBlockState(doors.get(i)) == BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState()) world.destroyBlock(doors.get(i), false);
		}
		
	}
	
	public static void setJumpPads(World world, BlockPos posO) {
		
		BlockPos pos = posO.down();
		BlockPos[] pads = new BlockPos[] {pos.north(11).west(11), pos.north(11).east(11), pos.west(11).south(11), pos.east(11).south(11)};
		
		for(int i = 0; i < pads.length; i++) {
			
			world.setBlockState(pads[i], BlockInit.LAUNCHPAD.getDefaultState());
			double pX = pads[i].getX();
			double pY = pads[i].getY();
			double pZ = pads[i].getZ();
			
			for(int x = -1; x < 2; x++) {
				
				for(int z = -1; z < 2; z++) {
					
					for(int y = 9; y < 14; y++) {
						
						world.destroyBlock(new BlockPos(pX + x, pY + y, pZ + z), false);
						
					}
					
				}
				
			}
			
			if(world.getTileEntity(pads[i]) instanceof TileEntityBounce) {
				TileEntityBounce b = (TileEntityBounce) world.getTileEntity(pads[i]);
				b.strength = 1.8F;
			}
			
		}
		
		
		
		     
		
	}

	public static void setAirReplacers(World world, BlockPos posO) {
		
		BlockPos pos = posO.up(12);
		BlockPos[] airs = new BlockPos[] {pos.north(11).west(11), pos.north(11).east(11), pos.west(11).south(11), pos.east(11).south(11)};
		
		for(int i = 0; i < airs.length; i++) {
			
			world.setBlockState(airs[i], BlockInit.NETHER_ROOF_AIR_REPLACER.getDefaultState());
			
		}
		
	}
	
	public static void setLightAirs(World world, BlockPos pos) {
		
		BlockPos north = pos.north(11);
		BlockPos south = pos.south(11);
		BlockPos west = pos.west(11);
		BlockPos east = pos.east(11);
		
		BlockPos[] array = new BlockPos[] { north, north.east(2), north.east(5), north.east(8), north.west(2), north.west(5), north.west(8),
											south, south.west(2), south.west(5), south.west(8), south.east(2), south.east(5), south.east(8),
											east, east.north(2), east.north(5), east.north(8), east.south(2), east.south(5), east.south(8),
											west, west.south(2), west.south(5), west.south(8), west.north(2), west.north(5), west.north(8)};
		
		for(int i = 0; i < array.length; i++) {
			
			world.setBlockState(array[i], BlockInit.ITEM_AIR.getDefaultState());
			
			if(world.getTileEntity(array[i]) instanceof TileEntityItemAir) {
				TileEntityItemAir a = (TileEntityItemAir) world.getTileEntity(array[i]);
				a.function = 3;
			}
			
		}
		
		
	}
	
	public static void setCrates(World world, BlockPos pos) {
		
		TileEntityCrate t = new TileEntityCrate();
		List<BlockPos> crates = new ArrayList<BlockPos>();
		List<BlockPos> selected = new ArrayList<BlockPos>();
		String[] defaultBoxes = t.stringArray;
		
		BlockPos north = pos.north(10).west(10);
		BlockPos east = pos.north(10).east(10);
		BlockPos west = pos.west(10).south(10);
		BlockPos south = pos.south(10).east(10);
		
		for(int i = 0; i < 25; i++) {
			
			if(i < 20) {
				crates.add(north.east(i));
				crates.add(east.south(i));
				crates.add(west.north(i));
				crates.add(south.west(i));
			}
			crates.add(north.north(2).west(2).east(i));
			crates.add(east.north(2).east(2).south(i));
			crates.add(west.west(2).south(2).north(i));
			crates.add(south.south(2).east(2).west(i));
			
		}
		
		for(int i = 0; i < 20; i++) {
			
			Random rn = new Random();
			int maximum = crates.size() - 1;
			int minimum = 0;
			int range = maximum - minimum + 1;	
			
			int b = rn.nextInt(range) + minimum;
			
			while(selected.contains(crates.get(b))) {
				if(b >=crates.size()) b = 0;
				if(b+7 >=crates.size()) b++;
				else b += 6;
			}
			
			selected.add(crates.get(b));
			
		}
		
		for(int i = 0; i < selected.size(); i++) {
			Random rn = new Random();
			int maximum = defaultBoxes.length-1;
			int minimum = 0;
			int range = maximum - minimum + 1;	
			
			int b = rn.nextInt(range) + minimum;
			
			world.setBlockState(selected.get(i), BlockInit.NETHER_CRATE.getDefaultState());
			
			if(world.getTileEntity(selected.get(i)) instanceof TileEntityCrate) {
				TileEntityCrate c = (TileEntityCrate) world.getTileEntity(selected.get(i));
				c.setType(defaultBoxes[b]);
			}
		}
		
		
		
	}

	public static double[] getSelectionPosses(BlockPos from, BlockPos to, int index) {
		double x = from.getX(); double y = from.getY(); double z = from.getZ();
		double xTo = to.getX(); double yTo = to.getY() + 1.0D; double zTo = to.getZ();
		
		if(index == 1) return new double[] {x + 0.1D, y, z, xTo + 1.0D + 0.1D, y, z, xTo + 1.0D + 0.1D, y, zTo + 1.0D, x + 0.1D, y, z,
											x, yTo, z, xTo + 1.0D, yTo, z, xTo + 1.0D, yTo, zTo + 1.0D, x, yTo, z,
											x + 0.1D, y, z, xTo + 1.0D + 0.1D, y, z, xTo + 1.0D + 0.1D, y, zTo + 1.0D, x + 0.1D, y, zTo + 1.0D,
											
		};
		if(index == 2) return new double[] {xTo + 1.0D + 0.1D, y, z, xTo + 1.0D + 0.1D, y, zTo + 1.0D, x + 0.1D, y, zTo + 1.0D, x + 0.1D, y, zTo + 1.0D,
											xTo + 1.0D, yTo, z, xTo + 1.0D, yTo, zTo + 1.0D, x, yTo, zTo + 1.0D, x, yTo, zTo + 1.0D,
											x + 0.1D, yTo, z, xTo + 1.0D + 0.1D, yTo, z, xTo + 1.0D + 0.1D, yTo, zTo + 1.0D, x + 0.1D, yTo, zTo + 1.0D,
		};
		
		else {
			System.out.println("empty returned");
			return new double[] {};
		}
	}
	
	public static void setSinkholes(BlockPos pos, World world) {
		
		BlockPos n = pos.north(17);
		BlockPos e = pos.east(17);
		BlockPos s = pos.south(17);
		BlockPos w = pos.west(17);
		
		BlockPos[] spaces = new BlockPos[] {n, n.east(5), n.east(10), n.west(5), n.west(10),
					                        e, e.north(5), e.north(10), e.south(5), e.south(10),
					                        s, s.east(5), s.east(10), s.west(5), s.west(10),
					                        w.north(5), w.north(10), w.south(5), w.south(10)};
		
		for(int i = 0; i < spaces.length; i++) {
			world.setBlockState(spaces[i].down(40), BlockInit.SINKHOLE.getDefaultState());
		}
		
	}

	public static void connectBlocks(BlockPos to, BlockPos from, WorldServer server) {
		
		double x1 = from.getX() + 0.5D; double x2 = to.getX() + 0.5D;
		double y1 = from.getY() + 0.5D; double y2 = to.getY() + 0.5D;
		double z1 = from.getZ() + 0.5D; double z2 = to.getZ() + 0.5D;
		
		double dX = x1 - x2;
		double dY = y1 - y2;
		double dZ = z1 - z2;
		
		int amountParticles = 50;
		//int amountParticles = (int) ((Math.abs(dX) + Math.abs(dZ)) / 2)*15;
		//System.out.println("Amount: " + amountParticles + ", dX: " + dX + ", dZ: " + dZ);
		
		double stepX = dX / amountParticles;
		double stepY = dY / amountParticles;
		double stepZ = dZ / amountParticles;
		
		int loopStep = 1;
		int loopStart = 0;
		int loopEnd = amountParticles;
		
		while(loopStart < loopEnd) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x2 + stepX * loopStart, y2 + stepY * loopStart, z2 + stepZ * loopStart, 1, 0.0D, 0.0D, 0.0D, 0);
			
			loopStart += loopStep;
		}
		
		
	}
	
}
