package poseidon.mod.objects.block.general.tileHelpers;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import poseidon.mod.entity.entities.EntityDemon;

public class TileEntityLight extends TileEntity implements ITickable {
	
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
		
		if(!world.isRemote) {
			WorldServer server = (WorldServer) world;
			double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
			server.spawnParticle(EnumParticleTypes.BARRIER, x, y + 1.0D, z, 1, 0.0D, 0.0D, 0.0D, 0, 0);
		}
		
		tick++;
	}
	
	

}
