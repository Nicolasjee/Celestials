package poseidon.mod.test;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;

public class ExtendEffect extends Effect {

	public ExtendEffect(double x, double y, double z, World w) {
		super(x, y, z, w);
	}
	
	@Override
	public void start() {
		while(r > 0) {
			run3(w,x1,y1,z1);
			r -= 1;
		}
	}

	//this might not work anymore :(
	@Override
	public void run() {
		
		int xP = getInt(3, -3);
		int zP = getInt(3, -3);
		double x2 = x1 + xP;
		double z2 = z1 + zP;
		double y2 = getDouble((int)x2, (int)y1, (int)z2, w)+1;
		
		double fx = 0.5D + ((x1+x2)/2);
		double fy = 3 + ((y1+y2)/2);
		double fz = 0.5D + ((z1+z2)/2);
		
		double a = (fy - y1) / ((fx-x1)*(fx-x2));
		double b = ((fy - y2) / (fx - x2)) - ((fy - y2) / (fx - x2));
		
		double dX = x1 - x2;
		double k = 100;
		double s = 0.0D;
		
		double m = (x2 - x1) / (z2 - z1);
		
		log("A: " + a + ", B: " + b + ", dX: " + dX);

		CustomEndRod rod = new CustomEndRod(w, x2 - xP, y2, z1, 100, 16712704);
		CustomEndRod rod2 = new CustomEndRod(w, (x1+x1-xP)/2, fy, z1, 100, 65280);
		Main.proxy.particle(w, rod2);
		Main.proxy.particle(w, rod);
		
		for(int i = 0; i < k; i += 1) {
			
			w.spawnParticle(EnumParticleTypes.END_ROD, x1 + s, (y1+(a*s*s)+(b*s)), z1+m*s, 0d, 0d, 0d);
			
			if(p == 5) {
				log("X: " + (x1+s) + ", Y: " + (y1+(a*s*s)+(b*s)) + ", Z: " + z1);
				p = 0;
			}
			p++;
			
			s = i*(dX/k);
			
		}
		
		this.x1 = x1 - xP;
		this.y1 = y2;
		this.z1 = z1 - zP;
		
		
		
	}
	
}
