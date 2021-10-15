package poseidon.mod.objects.block.drawVortex;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class TileEntityTest extends TileEntity implements ITickable {
	
	int tick = 0;
	BlockPos pos = getPos();
	double x = pos.getX();
	double y = pos.getY();
	double z = pos.getZ();
	
	double step = 0.01d;
	double m = 0.04d;
	
	@Override
	public void update() {

		world.spawnParticle(EnumParticleTypes.END_ROD, x + tick*step, y + tick*step*m, z, 0.0D, 0.0D, 0.0D, 0);
		
		tick++;
		
	}
	
	
	
}
