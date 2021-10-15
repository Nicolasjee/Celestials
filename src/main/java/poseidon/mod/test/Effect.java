package poseidon.mod.test;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;
import static java.lang.Math.*;

public class Effect {

	static boolean run = true;
	public static boolean log = true;
	static int p = 0;
	
	World w;
	double x1, y1, z1;
	int r;
	
	public Effect(double x, double y, double z, World w) {
		
		this.x1 = x;
		this.y1 = y;
		this.z1 = z;
		this.w = w;
		
		this.r = 9;
	}
	
	public void start() {
		for(int i = 0; i < r; i++) {
			
			run3(w, x1, y1, z1);
			
		}
	}
	
	//this code works with full arcs
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
		double b = ((fy - y2) / (fx - x2)) - ((fy - y1) / (fx - x1));
		
		double dX = x1 - x2;
		double k = 100;
		double s = 0.0D;
		
		log("A: " + a + ", B: " + b + ", dX: " + dX);

		CustomEndRod rod = new CustomEndRod(w, x1 - xP, y2, z1 - zP, 100, 16712704);
		CustomEndRod rod2 = new CustomEndRod(w, (x1+x1-xP)/2, fy, z1, 100, 65280);
		Main.proxy.particle(w, rod2);
		Main.proxy.particle(w, rod);
		
		for(int i = 0; i < k; i += 1) {
			
			w.spawnParticle(EnumParticleTypes.END_ROD, x1 + s, (y1+(a*s*s)+(b*s)), z1, 0d, 0d, 0d);
			
			if(p == 5) {
				log("X: " + (x1+s) + ", Y: " + (y1+(a*s*s)+(b*s)) + ", Z: " + z1);
				p = 0;
			}
			p++;
			
			s = i*(dX/k);
			
		}
		
		this.x1 = x1 + dX;
		this.y1 = y2 ;
		this.z1 = z1 + dX;
		
	}

	public static void run2(World w, double x, double y, double z) {
		double xi = x + getInt(3, -3)+0.5D;
		double zi = z + getInt(3, -3)+0.5D;
		double yi = getDouble((int)xi, (int)y, (int)zi, w)+1;
		
		double fx = 0.5D + ((x+xi)/2);
		double fy = 2 + ((y+yi)/2);
		double fz = 0.5D + ((z+zi)/2);
		
		double a = 2*(fy-y)/((x-fx)*(x-fx));
		double b = ((y-fy)*(2*x)) / (Math.pow(x-fx,2));
		
		double dX = x - xi;
		
		double k = 100;
		double p = dX/k;
		
		double s = 0.0D;
		
		log("A: " + a + ", B: " + b + ", dX: " + dX);
		
		
		
		CustomEndRod rod = new CustomEndRod(w, xi, yi, zi, 100, 16712704);
		CustomEndRod rod2 = new CustomEndRod(w, fx, fy, fz, 100, 65280);
		Main.proxy.particle(w, rod2);
		Main.proxy.particle(w, rod);
		
		for(int i = 0; i < k; i += 1) {
			
			w.spawnParticle(EnumParticleTypes.END_ROD, x + s, (y+(a*s*s)+(b*s)), z, 0d, 0d, 0d);
			
			s = i*(dX/k);
			
		}
	}
	
	public void run3(World w, double x1, double y1, double z1) {
		int xP = getInt(3, -3);
		int zP = getInt(3, -3);
		
		double x2 = x1 + xP;
		double z2 = z1 + zP;
		
		double y2 = getDouble((int)x2, (int)y1, (int)z2, w)+1;
		
		double fx = 0.5D + ((x1+x2)/2);
		double fy = 3 + ((y1+y2)/2);
		double fz = 0.5D + ((z1+z2)/2);
		
		double a = (((fy - y1)*(x1-x2))-((y1-y2)*(fx-x2))) / ((fx-x1)*(fx-x2)*(x1-x2));
		double b = ((y1-y2)/(x1-x2)) - ((2*y1)/(pow((x1-x2),2)));
		
		double dX = x1 - x2;
		double k = 100;
		double s = 0.0D;
		
		log("A: " + a + ", B: " + b + ", dX: " + dX);

		CustomEndRod rod = new CustomEndRod(w, x1 - xP, y2, z1 - zP, 100, 16712704);
		CustomEndRod rod2 = new CustomEndRod(w, (x1+x1-xP)/2, fy, z1, 100, 65280);
		Main.proxy.particle(w, rod2);
		Main.proxy.particle(w, rod);
		
		for(int i = 0; i < k; i += 1) {
			
			w.spawnParticle(EnumParticleTypes.END_ROD, x1 + s, (y1+(a*s*s)+(b*s)), z1, 0d, 0d, 0d);
			
			if(p == 5) {
				log("X: " + (x1+s) + ", Y: " + (y1+(a*s*s)+(b*s)) + ", Z: " + z1);
				p = 0;
			}
			p++;
			
			s = i*(dX/k);
			
		}
		
		this.x1 = x1 + dX;
		this.y1 = y2 ;
		this.z1 = z1 + dX;
	}
	
	public static void log(String s) {
		if(log) System.out.println(s);
	}
	
	public static int getInt(int max, int min) {
		Random rand = new Random();
		int range = max - min + 1;	
		int r = rand.nextInt(range) + min;
		if(r == 0) return getInt(max, min);
		if(r == 1 || r == -1) return getInt(max, min);
		return r;
	}
	
	public int getTop() {
		Random rand = new Random();
		int range = 4 - 1 + 1;	
		//return rand.nextInt(range) + 1;
		return 2;
	}
	
	public static double getDouble(int x, int y, int z, World w) {
		
		for(int i = 0; i < 5; i++) {
			if(w.getBlockState(new BlockPos(x,y+i,z)) != Blocks.AIR.getDefaultState() && isTopBlock(x,y+i,z,w))
				return y+i;
			else if(w.getBlockState(new BlockPos(x,y-i,z)) != Blocks.AIR.getDefaultState() && isTopBlock(x,y-i,z,w))
				return y-i;
		}
		
		run = false;
		return 0.0D;
		
	}


	public static boolean isTopBlock(int x, int y, int z, World w) {

		int i = 256-y;
		for(int j = 0; j < i; j++) {
			Block block = w.getBlockState(new BlockPos(x,y+j,z)).getBlock();
			if(block != Blocks.AIR) return false;
			if(j > 20 && block != Blocks.AIR) return true;
		}
		
		return true;
		
	}
	
}
