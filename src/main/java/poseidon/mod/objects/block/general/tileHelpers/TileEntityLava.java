package poseidon.mod.objects.block.general.tileHelpers;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import poseidon.mod.util.ParticleUtil;

public class TileEntityLava extends TileEntity implements ITickable {
	
	private int tick = 0;
	private int countdown = 0;
	public int steps = 0;
	public boolean killSwitch = false;
	
	@Override
	public void update() {
		
		if(tick == 20) {
			if(!world.isRemote) {
				//((WorldServer)world).setBlockState(pos, Blocks.LAVA.getDefaultState());
				ParticleUtil.cross((WorldServer)world, pos);
				tick = 0;
			}
		}
		
		if(killSwitch) {
			if(countdown == 30) ((WorldServer)world).setBlockState(pos, Blocks.AIR.getDefaultState());
			countdown++;
		}
		
		tick++;
	}
	
	
}
