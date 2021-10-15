package poseidon.mod.test;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;
import poseidon.mod.util.ParticleUtil;

public class ParticleSnake {
	
	public double x, y, z;
	public World w;
	public int r;
	public boolean run = true;
	
	public ParticleSnake(double x, double y, double z, World w, int r) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.r = r;
	}

	public void init() {
		
		double xi = x + getInt(3, -3);
		double zi = z + getInt(3, -3);
		double yi = getDouble((int)xi, (int)y, (int)zi, w);
		
		if(!run) {
			run = true;
			System.out.println("Stopped");
			return;
		}
		int top = getTop();
		
		int fy = top + (int)((y+yi)/2);
		int fx = (int)(x+xi)/2;
		int fz = (int)(z+zi)/2;
		
		double a = (fy-y) / ( (fx-xi)*(fx-x) );
		double b = ((fy-yi)/(fx-xi)) - ((fy - y)/(fx - x));
		
		double a1 = ( (fy-yi)-(y-yi) ) / ( (fz-zi)*(fz-z) );
		double b1 = ((fy-yi)/(fz-zi)) - ((fy - y)/(fz - z));
		
		double m = (int) (zi-z)/(xi-x);
		
		CustomEndRod rod = new CustomEndRod(w, xi, yi+2, z, 100, 16712704);
		CustomEndRod rod2 = new CustomEndRod(w, fx, fy, z, 100, 65280);
		Main.proxy.particle(w, rod2);
		Main.proxy.particle(w, rod);
		
		
		double dX = xi - x;
		int k = 100;
		double step = 0.0D;
		
		for(double i = -10*k; i < k*10; i++) {
	
			w.spawnParticle(EnumParticleTypes.END_ROD, x+step, y + (a*step*step) + b*step, z, 0d, 0d, 0d);

				step = i*(dX/k);
			
		}
		
	}
	
	public int getInt(int max, int min) {
		Random rand = new Random();
		int range = max - min + 1;		
		return rand.nextInt(range) + min;
	}
	
	public int getTop() {
		Random rand = new Random();
		int range = 4 - 1 + 1;	
		//return rand.nextInt(range) + 1;
		return 2;
	}
	
	public double getDouble(int x, int y, int z, World w) {
		
		for(int i = 0; i < 5; i++) {
			if(w.getBlockState(new BlockPos(x,y+i,z)) != Blocks.AIR.getDefaultState())
				return y+i;
			else if(w.getBlockState(new BlockPos(x,y-i,z)) != Blocks.AIR.getDefaultState())
				return y-i;
		}
		
		run = false;
		return 0.0D;
		
	}
	
}
