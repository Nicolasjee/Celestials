package poseidon.mod.util.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DiamondFinderUtil {

	public static void execute(World world, BlockPos pos, BlockPos diamond) {
		
		
		
	}
	
	public static void digTunnel(World world, BlockPos pos, BlockPos diamond) {
		
				//diamond x > pos x?
		boolean isXfurther = isXfurther(diamond, pos);
		boolean isZfurther = isZfurther(diamond, pos);
		boolean isHigher = (diamond.getY() > pos.getY());
		boolean isSameLevel = (diamond.getY() == pos.getY());
		System.out.println(isXfurther + "," + isZfurther + "," + isHigher + ", " + isSameLevel);
		
		List<BlockPos> removes = new ArrayList<BlockPos>();
		
		//step one: hight adjustment.
		
		List<BlockPos> hightAdjust = hightAdjustment(world, pos, diamond, isHigher, isSameLevel, isXfurther, isZfurther);
		System.out.println(hightAdjust.size());
		
		for(int i = 0; i < hightAdjust.size(); i++) {
			world.destroyBlock(hightAdjust.get(i), false);
		}
		
		//x diamond > x player => oosten
		//x diamond < x player => westen
		//z diamond > z player => zuiden
		//z diamond < z player => noorden
		
		
	}
	
	//Adjusts height of tunnel + adds the next block closer to it
	public static List<BlockPos> hightAdjustment(World world, BlockPos pos, BlockPos diamond, boolean isHigher, boolean isSameLevel, boolean xF, boolean zF) {
		
		List<BlockPos> list = new ArrayList<BlockPos>();
		double x = pos.getX(); double y = pos.getZ(); double z = pos.getZ();
		double xd = diamond.getX(); double yd = diamond.getY(); double zd = diamond.getZ();
		
		if(!isSameLevel) {
			//Creating next block.
			BlockPos nextBlock = new BlockPos(x + getValueX(xF), y + getValueY(isHigher), z + getValueZ(zF));
			
			//This list will get all the blocks needed to make a tunnel from (nextBlock)
			List<BlockPos> comp = removeHeightDifference(nextBlock, pos, isHigher);
			list.addAll(comp);
		}
		
		
		return list;
	}
	
	public static List<BlockPos> removeHeightDifference(BlockPos nextBlock, BlockPos pos, boolean isHigher) {
		List<BlockPos> list = new ArrayList<BlockPos>();
		if(isHigher) list.add(pos.up(2));
		if(!isHigher) {
			list.add(nextBlock.up(1)); 
			list.add(nextBlock.up(2));
		}
		return list;
	}
	
	public static double getValueZ(boolean xF) {
		if(xF) return +1.0D;
		else if(!xF) return -1.0D;
		else {
			System.out.println("Small oopsie here. xF was neither true or false??");
			return 1.0D;
		}
	}
	
	public static double getValueX(boolean xF) {
		if(xF) return +1.0D;
		else if(!xF) return -1.0D;
		else {
			System.out.println("Small oopsie here. xF was neither true or false??");
			return 1.0D;
		}
	}
	
	public static double getValueY(boolean xF) {
		if(xF) return +1.0D;
		else if(!xF) return -1.0D;
		else {
			System.out.println("Small oopsie here. xF was neither true or false??");
			return 1.0D;
		}
	}
		
	public static boolean isXfurther(BlockPos diamond, BlockPos player) {
		if(diamond.getX() > player.getX()) return true;
		else if(diamond.getX() < player.getX()) return false;
		return true;
	}
		
	public static boolean isZfurther(BlockPos diamond, BlockPos player) {
		if(diamond.getZ() > player.getZ()) return true;
		else if(diamond.getZ() < player.getZ()) return false;
		return false;
	}
	
}
