package poseidon.mod.objects.block.general.tileHelpers;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import poseidon.mod.entity.entities.EntityDemon;

public class TileEntityDemonAir extends TileEntity implements ITickable {
	
	private int tick = 0;
	
	@Override
	public void update() {
		
		if(tick == 20) {
			if(!world.isRemote) {
				double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
				EntityDemon d = new EntityDemon(world);
				EntityDemon d1 = new EntityDemon(world);
				d.setLocationAndAngles(x, y, z, d.rotationYaw, d.rotationPitch);
				d1.setLocationAndAngles(x, y, z, d1.rotationYaw, d1.rotationPitch);
				
				this.world.spawnEntity(d);
				this.world.spawnEntity(d1);
				
				this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
			}
		}
		
		tick++;
	}
	
	

}
