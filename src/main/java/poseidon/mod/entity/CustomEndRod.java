package poseidon.mod.entity;

import net.minecraft.client.particle.ParticleEndRod;
import net.minecraft.world.World;

public class CustomEndRod extends ParticleEndRod {

	public static final int ORANGE_LIGHT = 16756505;
	public static final int ORANGE_SPARK = 16763238;
	public static final int ORANGE_DARK = 13403136;
	
	public CustomEndRod(World w, double x, double y, double z, int l) {
		super(w, x, y, z, 0d, 0d, 0d);
		this.setMaxAge(l);
		this.setColor(4277576);
	}
	
	public CustomEndRod(World w, double x, double y, double z, int l, int c) {
		super(w, x, y, z, 0d, 0d, 0d);
		this.setMaxAge(l);
		this.setColor(c);
	}
	
	public CustomEndRod(World w, double x, double y, double z, int l, double mx, double my, double mz, int color) {
		super(w, x, y, z, mx, my, mz);
		this.setMaxAge(l);
		this.setColor(color);
	}
	
}
