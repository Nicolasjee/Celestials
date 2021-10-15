package poseidon.mod.util.helpers.diamondfinder;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;

public class DiamondFinderAllignment {

	public static List<BlockPos> allign(int b, BlockPos pos, BlockPos diamond, boolean xzF, boolean xzSame) {
		List<BlockPos> additions = new ArrayList<BlockPos>();
		
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		double xi = diamond.getX(); double yi = diamond.getY(); double zi = diamond.getZ();
		
		System.out.println("Arrived. Pos: " + x + ", " + y + ", " + z);
		
		if(b == 1) {
			if(xzSame) return additions;
			
			if(!xzSame) {
	
				int d = Math.abs(((int) xi - (int) x));
				
				double vX = DiamondValueAdder.getValueX(pos, diamond, xzF);
				
				System.out.println("Values: " + d + ", vX: " + vX);
				
				//Looping until xi = x
				for(int i = 1; i <= d; i++) {
					
					System.out.println("Added: " + (x + vX) + ", y: " + y + ", z: " + z);
					additions.add(new BlockPos(x + (vX * i), y, z));
					additions.add(new BlockPos(x + (vX * i), y + 1.0D, z));
						
				}
					
				
				
			}
		}
		
		if(b == 2) {
			
			if(xzSame) return additions;
			
			if(!xzSame) {
	
				int d = Math.abs(((int) zi - (int) z));
				
				double vX = DiamondValueAdder.getValueZ(pos, diamond, xzF);
				
				System.out.println("Values: " + d + ", vX: " + vX);
				
				//Looping until xi = x
				for(int i = 1; i <= d; i++) {
					
					System.out.println("Added: " + (x) + ", y: " + y + ", z: " + (z + vX));
					additions.add(new BlockPos(x, y, z + (vX * i)));
					additions.add(new BlockPos(x, y + 1.0D, z + (vX * i)));
						
				}
					
				
				
			}
			
		}
		
		return additions;
	}
	
	public static BlockPos getReferenceBlock(int b, BlockPos pos, BlockPos diamond, boolean xzF, boolean xzSame) {
		
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		double xi = diamond.getX(); double yi = diamond.getY(); double zi = diamond.getZ();
		
		System.out.println("Arrived. Pos: " + x + ", " + y + ", " + z);
		
		if(b == 1) {
			if(xzSame) return pos;
			
			if(!xzSame) {
	
				int d = Math.abs(((int) xi - (int) x)) - 1;
				
				double vX = DiamondValueAdder.getValueX(pos, diamond, xzF);
				
				//Looping until xi = x
				for(int i = 1; i <= d; i++) {
					
					if(i == d) return new BlockPos(x + (vX * i), y,z);
						
				}
					
				
				
			}
		}
		System.out.println("Returned pos on update");
		return pos;
		
	}
	
}
