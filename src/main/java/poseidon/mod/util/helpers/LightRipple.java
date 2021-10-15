package poseidon.mod.util.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightRipple {

	/**
	 * Type 1: Cross
	 * Type 2: Circle
	 */
	public static void execute(World world, BlockPos pos, int type) {
		
		if(type == 1) {
			
			cross(world, pos);
			
		}
		
	}
	
	
	private static void cross(World world, BlockPos pos) {
		
		List<BlockPos> rippleCoords = new ArrayList<BlockPos>();
		
		for(int i = 0; i < 7; i++) {
			
			rippleCoords.add(pos.east(i));
			rippleCoords.add(pos.north(i));
			rippleCoords.add(pos.west(i));
			rippleCoords.add(pos.east(i));
			
		}
		
	}
	
}
