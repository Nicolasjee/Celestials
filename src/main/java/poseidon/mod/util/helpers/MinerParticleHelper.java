package poseidon.mod.util.helpers;

import java.util.List;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;

public class MinerParticleHelper {

	public static void spawnParticles(int d, BlockPos target, BlockPos pos, EnumFacing f, World world) {
		
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		
		double xm = 0.001D;
		double i = xm * d;
		double yz = target.getY() - y;
		double m = 0.0D;
		

		if(f == EnumFacing.EAST) east(world, xm, i, yz, m, pos, d);

		
		
	}
	
	
	public static void east(World world, double xm, double i, double yz, double m, BlockPos pos, int d) {
		
		double x = CoH.getMiddle(EnumFacing.EAST, 1, pos);
		double y = CoH.getMiddle(EnumFacing.EAST, 2, pos);
		double z = CoH.getMiddle(EnumFacing.EAST, 3, pos);
		
		for(int a = 0; a < 1000; a++) {
			
			x = (a * i) + x;
			m = yz / d;
			y = m*x +y;
			//Main.proxy.mineBlocks(world, x, y, z);
			
			
		}
		
	}
	
}
