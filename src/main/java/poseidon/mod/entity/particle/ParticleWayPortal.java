package poseidon.mod.entity.particle;

import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.world.World;

public class ParticleWayPortal extends ParticlePortal 
{
	public ParticleWayPortal(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed)
	{
		super (world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
		
		this.particleRed = this.particleGreen = this.particleBlue = 0.0F;
	}
}
