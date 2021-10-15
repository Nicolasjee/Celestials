package poseidon.mod.util.helpers;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class CoH {

	public static double v(int q, BlockPos pos, int i) {
		
		Random rand = new Random();
		double d0 = (double)pos.getX();
        double d1 = (double)pos.getY() + 1.0D;
        double d2 = (double)pos.getZ();
        double d3 = (double)(0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);
        
		if(q == 1) { // X neg , Z pos
			if(i == 1) return d0 + 0.0D;
			if(i == 2) return d1 + 0.0D;
			if(i == 3) return d2 + 0.5D;
		}
		
		if(q == 2) {
			if(i == 1) return d0 + 0.5D;
			if(i == 2) return d1 + 0.0D;
			if(i == 3) return d2 + 1.0D;
		}
		
		if(q == 3) {
			if(i == 1) return d0 + 1.0D;
			if(i == 2) return d1 + 0.0D;
			if(i == 3) return d2 + 0.5D;
			return 0.0D;
		}
		
		if(q == 4) {
			if(i == 1) return d0 + 0.5D;
			if(i == 2) return d1 + 0.0D;
			if(i == 3) return d2 + 0.0D;
			System.out.println("Exception in CoH.v(); q = 4, i != 1,2,3");
			return 0.0D;
		}
		
		System.out.println("Exception in v method (CoH.class). q was not 1,2,3 or 4");
		return 0.0D;
        
	}

	public static double m(BlockPos pos, int i) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 1.2D;
		double z = pos.getZ() + 0.5D;
		
		if(i == 1) return x;
		if(i == 2) return y;
		if(i == 3) return z;
		
		System.out.println("expection CoH.m() i != 1,2,3");
		return 0.0D;
		
		
		
	}

	public static double k(int q, BlockPos pos, int i) {
		
		Random rand = new Random();
		double d0 = (double)pos.getX() - (double)(rand.nextFloat() * 0.1F);
        double d1 = (double)pos.getY() - (double)(rand.nextFloat() * 0.1F) + 1.0D;
        double d2 = (double)pos.getZ() - (double)(rand.nextFloat() * 0.1F);
        double d3 = (double)(0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);
        
		if(q == 1) {
			if(i==1) return d0 + 1.0D;
			if(i==2) return d1 + 0.5D;
			if(i==3) return d2 + 0.5D;
		}
		
		if(q == 2) {
			if(i==1) return d0 + 0.5D;
			if(i==2) return d1 + 0.5D;
			if(i==3) return d2 + 0.0D;
		}
		
		if(q == 3) {
			if(i==1) return d0 + 0.0D;
			if(i==2) return d1 + 0.5D;
			if(i==3) return d2 + 0.5D;
		}
		
		if(q == 4) {
			if(i==1) return d0 + 0.5D;
			if(i==2) return d1 + 0.5D;
			if(i==3) return d2 + 1.0D;
		}
		
		return 0.0D;
		
	}

	public static double l(int q, BlockPos pos, int i) {
		
		Random rand = new Random();
		double x = (double)pos.getX();
        double y = (double)pos.getY();
        double z = (double)pos.getZ();
		
		if(q == 1) {
			switch(i) {
				case 1: return x + 1.0D;
				case 2: return y;
				case 3: return z + 1.0D;
			}
		}
		
		if(q == 2) {
			switch(i) {
				case 1: return x + 0.5D;
				case 2: return y;
				case 3: return z + 1.0D;
			}
		}
		
		if(q == 3) {
			switch(i) {
				case 1: return x;
				case 2: return y;
				case 3: return z + 1.0D;
			}
		}
		
		if(q == 4) {
			switch(i) {
				case 1: return x + 1.0D;
				case 2: return y;
				case 3: return z + 0.5D;
			}
		}
		
		if(q == 5) {
			switch(i) {
				case 1: return x + 0.5D;
				case 2: return y;
				case 3: return z + 0.5D;
			}
		}
		
		if(q == 6) {
			switch(i) {
				case 1: return x;
				case 2: return y;
				case 3: return z + 0.5D;
			}
		}
		
		if(q == 7) {
			switch(i) {
				case 1: return x + 1.0D;
				case 2: return y;
				case 3: return z;
			}
		}
		
		if(q == 8) {
			switch(i) {
				case 1: return x + 0.5D;
				case 2: return y;
				case 3: return z;
			}
		}
		
		if(q == 9) {
			switch(i) {
				case 1: return x;
				case 2: return y;
				case 3: return z;
			}
		}
		
		return 0.0D;
		
	}

	public static double p(double height, double length) {
		
		return Math.sqrt((height*height) + (length * length));
		
	}
	
	public static double n(int q, BlockPos pos, int i) {
		
		Random rand = new Random();
		double x = (double)pos.getX();
        double y = (double)pos.getY() + 2.0D;
        double z = (double)pos.getZ();
		
        if(q == 1) {
        	if(i == 1) return x;
        	if(i == 2) return y;
        	if(i == 3) return z + 1.0D;
        }
        
        if(q == 2) {
        	if(i == 1) return x + 1.0D;
        	if(i == 2) return y;
        	if(i == 3) return z + 1.0D;
        }
        
        if(q == 3) {
        	if(i == 1) return x + 1.0D;
        	if(i == 2) return y;
        	if(i == 3) return z;
        }
        
        if(q == 4) {
        	if(i == 1) return x;
        	if(i == 2) return y;
        	if(i == 3) return z;
        }
        
        System.out.println("Exception caught in CoH.n(). Integer q was not 1,2,3 or 4");
        
        return 0.0D;
        
	}

	public static double getMiddle(EnumFacing f, int i, BlockPos pos) {
		
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		if(f == EnumFacing.NORTH) {
			z = z + 1.0D;
			x = x + 0.5D;
			if(i == 1) return x;
			if(i == 2) return y - 0.5D;
			if(i == 3) return z;
		}
		
		if(f == EnumFacing.EAST) {
			z = z + 0.5D;
			x = x + 1.0D;
			if(i == 1) return x;
			if(i == 2) return y - 0.5D;
			if(i == 3) return z;
		}
		
		if(f == EnumFacing.SOUTH) {
			if(i == 1) return x + 0.5D;
			if(i == 2) return y - 0.5D;
			if(i == 3) return z;
		}
		
		if(f == EnumFacing.WEST) {
			if(i == 1) return x + 1.0D;
			if(i == 2) return y - 0.5D;
			if(i == 3) return z + 0.5D;
		}
		
		return 0.0D;
	}

	/**
	 * This function will return the middle (+0.5D y) of the given blockpos in a plus shape.
	 * 
	 * 			P	
	 * 
	 * 	   P	M    P
	 * 
	 *			P  
	 * 
	 * q = quadrant:  1: right, 2: up, 3: left, 4: down
	 * pos = BlockPos used to find x, y, z and modify
	 * i: 1,2 or 3. x, y or z
	 */
	public static double q(int q, BlockPos pos, int i) {
		
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		if(q == 1) {
			if(i == 1) return x + 1.0D;
			if(i == 2) return y + 0.5D;
			if(i == 3) return z + 0.5D;
		}
		
		if(q == 2) {
			if(i == 1) return x + 0.5D;
			if(i == 2) return y + 0.5D;
			if(i == 3) return z + 0.0D;
		}
		
		if(q == 3) {
			if(i == 1) return x + 0.0D;
			if(i == 2) return y +  0.5D;
			if(i == 3) return z + 0.5D;
		}
		
		if(q == 4) {
			if(i == 1) return x +  0.5D;
			if(i == 2) return y + 0.5D;
			if(i == 3) return z + 1.0D;
		}
		
		System.out.println("Exception in CoH#v(). Q was not 1,2,3 or 4: " + q + ", i: " + i);
		return 0.0D;
	}
	
}
