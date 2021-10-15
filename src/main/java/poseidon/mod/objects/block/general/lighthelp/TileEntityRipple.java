package poseidon.mod.objects.block.general.lighthelp;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;

public class TileEntityRipple extends TileEntity implements ITickable {
	
	private int tick = 0;
	private int progress = 1;
	public boolean isParent = false;
	
	@Override
	public void update() {
		
		if(tick == 5 && isParent) {
			
			progress++;
			if(isParent) placeBlocks(progress);
			
			tick = 0; // RESET
		}
		
		if(tick == 7 && !isParent) world.setBlockState(pos, Blocks.AIR.getDefaultState());
		
		
		if(progress == 3 && isParent) world.setBlockState(pos, Blocks.AIR.getDefaultState());
		
		tick++;
	}
	
	
	private void placeBlocks(int i) {
		
		BlockPos[] list = new BlockPos[] {pos.east(i), pos.north(i), pos.west(i), pos.south(i)};
		String[] dir = new String[] {"east", "north", "west", "south"};
		
		for(int j = 0; j < list.length; j++) {
			
			BlockPos p = list[j];
			

				if(isAir(p)) world.setBlockState(p, BlockInit.RIPPLE.getDefaultState());
				
				if(!isAir(p)) {
					if(isAboveAir(p)) {
						world.setBlockState(p.up(), BlockInit.RIPPLE.getDefaultState());
					}
					if(!isAboveAir(p)) {
						if(isPreviousElavated(p, dir[j])) {
							
						}
					}
				}
				
			
			
		}
		
	}
	
	private EnumFacing getFacingFromString(String r) {
		if(r == "east") return EnumFacing.EAST;
		if(r == "north") return EnumFacing.NORTH;
		if(r == "south") return EnumFacing.SOUTH;
		if(r == "west") return EnumFacing.WEST;
		else return EnumFacing.NORTH;
	}
	
	private boolean isAir(BlockPos p) {
		if(world.getBlockState(p) == Blocks.AIR.getDefaultState()) return true;
		else return false;
	}
	
	private boolean isAboveAir(BlockPos p) {
		if(world.getBlockState(p.up()) == Blocks.AIR.getDefaultState()) return true;
		else return false;
	}

	private boolean isPreviousElavated(BlockPos p, String dir) {
		EnumFacing facing = getFacingFromString(dir);
		return false;
	}
	
}
