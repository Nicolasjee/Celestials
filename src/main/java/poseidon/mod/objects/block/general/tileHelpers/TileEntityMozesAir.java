package poseidon.mod.objects.block.general.tileHelpers;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import poseidon.mod.init.BlockInit;

public class TileEntityMozesAir extends TileEntity implements ITickable {
	
	private int tick = 0;
	
	@Override
	public void update() {
		
		if(tick == 100) {
			BlockPos[] con = new BlockPos[] {pos.north(), pos.east(), pos.west(), pos.south()};
			int c = 0;
			for(int i = 0; i < con.length; i++) {
				if(world.getBlockState(con[i]) == Blocks.WATER.getDefaultState() || world.getBlockState(con[i]) == BlockInit.MOZESAIR.getDefaultState()) {
					c++;
				}
			}
			if(c > 1) this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
			else if(c < 2) this.world.setBlockState(this.pos, Blocks.WATER.getDefaultState());
		}
		
		tick++;
	}
	
	

}
