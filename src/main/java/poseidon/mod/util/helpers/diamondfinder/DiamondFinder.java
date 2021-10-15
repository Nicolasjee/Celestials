package poseidon.mod.util.helpers.diamondfinder;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;

public class DiamondFinder {
	
	public static void startUp(World world, EntityPlayer player) {
		
		//getBox
		//Check NBT
		
	}
	
	public static void execute(World world, EntityPlayer player, BlockPos diamond) {
		
		BlockPos pos = player.getPosition();
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		
		
		boolean isXfurther = (diamond.getX() > x);
		boolean isXsame = (diamond.getX() == x);
		boolean isZfurther = (diamond.getZ() > z);
		boolean isZsame = (diamond.getZ() == z);
		boolean isHigher = (diamond.getY() > y);
		boolean isSameLevel = DiamondValueAdder.getHeightDifferenceBoolean(pos, diamond);
		boolean isAboveOrBelow = (isXsame && isZsame);
		
		//List of all blocks needing removal.
		List<BlockPos> removals = new ArrayList<BlockPos>();
		
		
		
		//Execution progress: Y - X - Z
		
		//Step 1: hight adjustment.
		//Following the executing progress: Y adjustment with X adjustment.
		List<BlockPos> subExecute1 = subExecute(1, pos, diamond, isXfurther, isXsame, isZfurther, isZsame, isHigher, isSameLevel, isAboveOrBelow);
		
		//Step 2: x adjustment
		//updating pos coordinate.
		pos = DiamondFinderHeightAdjustment.getReferenceBlockHeight(pos, diamond, isXfurther, isXsame, isZfurther, isZsame, isHigher, isSameLevel, isAboveOrBelow);
		List<BlockPos> subExecute2 = subExecute(2, pos, diamond, isXfurther, isXsame, isZfurther, isZsame, isHigher, isSameLevel, isAboveOrBelow);
		
		pos = DiamondFinderAllignment.getReferenceBlock(1, pos, diamond, isXfurther, isXsame);
		List<BlockPos> subExecute3 = subExecute(3, pos, diamond, isXfurther, isXsame, isZfurther, isZsame, isHigher, isSameLevel, isAboveOrBelow);
		
		removals.addAll(subExecute1);
		removals.addAll(subExecute2);
		removals.addAll(subExecute3);
		
		
		System.out.println("Size: " + subExecute1.size() + subExecute2.size());
		
		remove(removals, world);
		
	}
	
	/**
	 * pos argument changes depending on where you are in the progress. (not steps)
	 */
	
	private static List<BlockPos> subExecute(int step, BlockPos pos, BlockPos diamond, boolean xF, boolean xSame, boolean zF, boolean zSame, boolean isHigher, boolean isSameLevel, boolean isAboveOrBelow) {
		List<BlockPos> master = new ArrayList<BlockPos>();
		List<BlockPos> subCollection1 = new ArrayList<BlockPos>();
		
		if(step == 1) subCollection1 = DiamondFinderHeightAdjustment.heightAdjustment(pos, diamond, xF, xSame, zF, zSame, isHigher, isSameLevel, isAboveOrBelow);
		if(step == 2) subCollection1 = DiamondFinderAllignment.allign(1, pos, diamond, xF, xSame);
		if(step == 3) subCollection1 = DiamondFinderAllignment.allign(2, pos, diamond, zF, zSame);
			
			
			
		return subCollection1;

	}
	
	private static void remove(List<BlockPos> r, World w) {
		System.out.println("Size from remove: " + r.size());
		for(int i = 0; i < r.size(); i++) {
			if(!w.isRemote && (w.getBlockState(r.get(i)) != Blocks.DIAMOND_ORE.getDefaultState())) {
				checkSurroundingsOf(r.get(i), w);
				((WorldServer)w).destroyBlock(r.get(i), true);
			}
		}
	}
	
	private static void checkSurroundingsOf(BlockPos p, World w) {
		BlockPos[] surroundings = new BlockPos[] {p.north(), p.east(), p.west(), p.south(), p.up()};
		
		for(int i = 0; i < surroundings.length; i++) {
			if(w.getBlockState(surroundings[i]).getMaterial() == Material.LAVA) {
				((WorldServer)w).setBlockState(p, BlockInit.MOD_AIR.getDefaultState());
			}
		}
		
		if(w.getBlockState(p.down()).getMaterial() == Material.LAVA) {
			((WorldServer)w).setBlockState(p, BlockInit.TEMPERED_LAVA.getDefaultState());
		}
		
	}
	
	
	
}
