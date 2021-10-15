package poseidon.mod.test.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;

public class TileEntityTest extends TileEntity implements ITickable, ILog {
	
	public int tick, tickCycle;
	
	public boolean hasTickCycle = false;
	public boolean init = false;
	public boolean hasInstructions = false;
	
	public double x1, y1, z1, xP, zP;
	public double a, b, m, dX;
	
	//start double counter
	public double s = 0.0D;

	@Override
	public void update() {
		
		if(!world.isRemote) return;
		if(!hasTickCycle) tickCycle = TileHelper.getTicks(50, 120, this);
		if(!init) TileHelper.init(this);
		if(!hasInstructions) TileHelper.setInstructions(this);
		
		if(hasTickCycle && init && hasInstructions && world.isRemote) 
			run();
		
		
		
		tick ++;
		if(tick == tickCycle) {
			log("Cycle change");
			hasTickCycle = false;
			hasInstructions = false;
			this.x1 = x1 + s;
			this.z1 = z1 + m*s;
			tick = 0;
			//reset();
		}
		
	}
	
	/**
	 * Method for drawing the particles
	 */
	private void run() {
		
		//if(tick % 20 == 0) log("Spawn at X: " + (x1 + s) + ", Y: " + (y1 +(a*s*s)+(b*s)) + ", Z: " + (z1 +m*s));
		s = tick*(dX/tickCycle);	
		this.world.spawnParticle(EnumParticleTypes.END_ROD, x1 + s, y1 + (a*s*s)+(b*s), z1+m*s, 0d, 0d, 0d);
		CustomEndRod rod = new CustomEndRod(this.world, x1 - xP, y1, z1 - zP, 100, 16712704);
		CustomEndRod rod2 = new CustomEndRod(this.world, x1 + xP, y1, z1 + zP, 100, 16712704);
		Main.proxy.particle(this.world, rod2);
		Main.proxy.particle(this.world, rod);
	}
	

	
}
