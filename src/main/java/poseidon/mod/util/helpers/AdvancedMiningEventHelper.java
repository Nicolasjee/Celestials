package poseidon.mod.util.helpers;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AdvancedMiningEventHelper {

	public static void removeBlocks(EnumFacing facing, World worldIn, BlockPos poss, boolean shouldSmelt, boolean hasLooting, int lootingLvl) {
		double x = poss.getX(); double y = poss.getY(); double z = poss.getZ();
		
		if(!worldIn.isRemote) {
			
			if(facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
				
				BlockPos[] pos = new BlockPos[] { new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x - 1.0D, y, z - 1.0D), new BlockPos(x + 1.0D, y, z + 1.0D),
				                                  new BlockPos(x, y, z + 1.0D), new BlockPos(x, y, z - 1.0D), new BlockPos(x + 1.0D, y, z), new BlockPos(x - 1.0D, y, z),  new BlockPos(x, y, z)};
				
				for(int i = 0; i < pos.length; i++) worldIn.destroyBlock(pos[i], true);
		
				
				} else if(facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
					
					BlockPos[] pos = new BlockPos[] { new BlockPos(x, y + 1.0D, z + 1.0D), new BlockPos(x, y, z + 1.0D), new BlockPos(x, y - 1.0D, z + 1.0D), new BlockPos(x, y + 1.0D, z), new BlockPos(x, y - 1.0D, z),
												      new BlockPos(x, y - 1.0D, z - 1.0D), new BlockPos(x, y, z - 1.0D), new BlockPos(x, y + 1.0D, z - 1.0D), new BlockPos(x, y, z)};
					
					for(int i = 0; i < pos.length; i++) worldIn.destroyBlock(pos[i], true);

				
				} else if(facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
				
				
					BlockPos[] pos = new BlockPos[] { new BlockPos(x + 1.0D, y + 1.0D, z), new BlockPos(x + 1.0D, y, z), new BlockPos(x + 1.0D, y - 1.0D, z), new BlockPos(x, y + 1.0D, z), new BlockPos(x, y - 1.0D, z),
							                                   new BlockPos(x - 1.0D, y - 1.0D, z), new BlockPos(x - 1.0D, y, z), new BlockPos(x - 1.0D, y + 1.0D, z), new BlockPos(x, y, z)};
					
					for(int i = 0; i < pos.length; i++) worldIn.destroyBlock(pos[i], true);
					
			}
			
		}
		
	}
	
}
