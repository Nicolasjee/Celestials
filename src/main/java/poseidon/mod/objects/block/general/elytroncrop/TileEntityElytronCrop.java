package poseidon.mod.objects.block.general.elytroncrop;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;

public class TileEntityElytronCrop extends TileEntity implements ITickable {

	public int tick = 0;
	public int cooldown = 40;
	
	@Override
	public void update() {
		
		if(this.world.getBlockState(pos).getBlock() == BlockInit.ELYTRONCROP) {
			
			ElytronCrop crop = (ElytronCrop) this.world.getBlockState(pos).getBlock();
			
			if(crop.isMaxAge(world.getBlockState(pos))) {
				
				if(!world.isRemote && tick < 360) {
					double x = pos.getX() + 0.5D; double y = pos.getY(); double z = pos.getZ() + 0.5D;
					for(int i = 0; i < 4; i++) ParticleUtil.circle(x, y, z, (WorldServer)world, tick + i);
					tick = tick + 4;
				}
				
			}
			
			if(tick == 360) {
				if(cooldown > 0) cooldown--;
				if(cooldown == 0) {
					cooldown = 40;
					tick = 0;
				}
			}

		}
		
	}
	
}
