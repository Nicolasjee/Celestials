package poseidon.mod.util.helpers.diamondfinder;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;

public class DiamondValueAdder {

	public static double getValueX(BlockPos pos, BlockPos diamond, boolean x) {
		if(x) return 1.0D;
		if(!x) return -1.0D;
		else {
			System.out.println("Small oopsie here, boolean not correct? : -" + x + "- ?? returned: 1.0D");
			return 1.0D;
		}
	}
	
	public static double getValueZ(BlockPos pos, BlockPos diamond, boolean z) {
		if(z) return 1.0D;
		if(!z) return -1.0D;
		else {
			System.out.println("Small oopsie here, boolean not correct? : -" + z + "- ?? returned: 1.0D");
			return 1.0D;
		}
	}
	
	public static double getValueY(BlockPos pos, BlockPos diamond, boolean y) {
		if(y) return 1.0D;
		if(!y) return -1.0D;
		else {
			System.out.println("Small oopsie here, boolean not correct? : -" + y + "- ?? returned: 1.0D");
			return 1.0D;
		}
	}
	
	public static boolean getHeightDifferenceBoolean(BlockPos pos, BlockPos diamond) {
		double x = pos.getY(); double xi = diamond.getY();
		if(x == xi) return true;
		if(x + 1.0D == xi) return true;
		if(x - 1.0D == xi) return true;
		else return false;
	}
	
	public static List<BlockPos> getMasterList(List<BlockPos>... list2) {
		
		List<BlockPos> collection = new ArrayList<BlockPos>();
		
		for(List<BlockPos> p : list2) {
			collection.addAll(p);
		}
		
		return collection;
	}
	
}
