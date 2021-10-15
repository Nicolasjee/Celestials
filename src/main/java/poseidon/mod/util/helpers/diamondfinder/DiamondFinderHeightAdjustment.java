package poseidon.mod.util.helpers.diamondfinder;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;

public class DiamondFinderHeightAdjustment {

	public static List<BlockPos> heightAdjustment(BlockPos pos, BlockPos diamond, boolean xF, boolean xSame, boolean zF, boolean zSame, boolean isHigher, boolean isSameLevel, boolean isAboveOrBelow) {
		List<BlockPos> heightRemoval = new ArrayList<BlockPos>();
		
		//Following execution progress: Y + X.
		//Checking if the height needs changing.
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		double xi = diamond.getX(); double yi = diamond.getY(); double zi = diamond.getZ();
		
		BlockPos reference = null;
		
		//Checking various booleans for the right axis alignment with Y adjustment.
		
		if(!isSameLevel) {
			
			if(!xSame) {
					
				//Z WILL NOT CHANGE HERE
				BlockPos localPos = new BlockPos(x + DiamondValueAdder.getValueX(pos, diamond, xF), y + DiamondValueAdder.getValueY(pos, diamond, isHigher), z);
				heightRemoval.add(localPos);
				
				int d = Math.abs((int) y - (int) yi); 
				
				if(isHigher) {
					heightRemoval.add(pos.up(2));
					heightRemoval.add(localPos.up());
				}
				
				if(!isHigher) {
					heightRemoval.add(localPos.up());
					heightRemoval.add(localPos.up(2));
				}
				
				for(int i = 0; i < d; i++) {
					
					if(isHigher) heightRemoval.add(localPos.up(1 + i));
					if(!isHigher) heightRemoval.add(localPos.down(i));
					
				}
				
				
				
				
			} else if(xSame) {
					
				//Grid allignment on X axis.
				//Remove from Z axis as the next function will just continue on Z anyway.
					
				//Picking zSame as the isAboveOrBelow = (zPos == zDiamond) && (xPos == xDiamond)
				if(!zSame) {
					
					BlockPos localPos = new BlockPos(x, y + DiamondValueAdder.getValueY(pos, diamond, isHigher), z + DiamondValueAdder.getValueZ(pos, diamond, zF));
				
					heightRemoval.add(localPos);
					
					int d = Math.abs((int) y - (int) yi); 
					
					if(isHigher) {
						heightRemoval.add(pos.up(2));
						heightRemoval.add(localPos.up());
					}
					
					if(!isHigher) {
						heightRemoval.add(localPos.up());
						heightRemoval.add(localPos.up(2));
					}
					
					for(int i = 0; i < d; i++) {
						
						if(isHigher) heightRemoval.add(localPos.up(1 + i));
						if(!isHigher) heightRemoval.add(localPos.down(i));
						
					}
					
				}
					
					
				//Checking if x and z are the same. = above or below.
				if(isAboveOrBelow) return new ArrayList<BlockPos>();
				//empty list. Height adjustment will be executed in another step.

			}
		
				
		} else if(isSameLevel) {
			
			return new ArrayList<BlockPos>();
			
		}
			
			
		
		return heightRemoval;
	}
	
	public static BlockPos getReferenceBlockHeight(BlockPos pos, BlockPos diamond, boolean xF, boolean xSame, boolean zF, boolean zSame, boolean isHigher, boolean isSameLevel, boolean isAboveOrBelow) {
		List<BlockPos> heightRemoval = new ArrayList<BlockPos>();
		
		//Following execution progress: Y + X.
		//Checking if the height needs changing.
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		double xi = diamond.getX(); double yi = diamond.getY(); double zi = diamond.getZ();
		
		BlockPos reference = null;
		
		//Checking various booleans for the right axis alignment with Y adjustment.
		
		if(!isSameLevel) {
			
			if(!xSame) {
				
				int d = Math.abs((int) y - (int) yi); 
				BlockPos localPos = new BlockPos(x + DiamondValueAdder.getValueX(pos, diamond, xF), y + DiamondValueAdder.getValueY(pos, diamond, isHigher), z);
				
				if(isHigher) return new BlockPos(x + DiamondValueAdder.getValueX(pos, diamond, xF), y + DiamondValueAdder.getValueY(pos, diamond, isHigher) + d, z);
				if(!isHigher) return new BlockPos(x + DiamondValueAdder.getValueX(pos, diamond, xF), y + DiamondValueAdder.getValueY(pos, diamond, isHigher) - d, z);
				
			}
				
				else if(xSame) {
					
				if(!zSame) {
					
					int d = Math.abs((int) y - (int) yi); 
					BlockPos localPos = new BlockPos(x, y + DiamondValueAdder.getValueY(pos, diamond, isHigher), z + DiamondValueAdder.getValueZ(pos, diamond, zF));
					
					if(isHigher) return new BlockPos(x, y + DiamondValueAdder.getValueY(pos, diamond, isHigher) + d, z + DiamondValueAdder.getValueZ(pos, diamond, zF));
					if(!isHigher) return new BlockPos(x, y + DiamondValueAdder.getValueY(pos, diamond, isHigher) - d, z + DiamondValueAdder.getValueZ(pos, diamond, zF));
				}


			}
		
				
		} else if(isSameLevel) 	return pos;
			
		
			
			
		
		return pos;
	}
	

}
