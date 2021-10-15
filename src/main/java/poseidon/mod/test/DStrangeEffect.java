package poseidon.mod.test;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;
import poseidon.mod.util.Utilities;

public class DStrangeEffect {

	//A = Asin(i)
	public static void exe(World w, double x, double y, double z, EntityPlayer p, double a) {
		
		Vec3d xa = Vectory.getX(p, w, 10d, 1.5d);
		Vec3d ya = Vectory.getY(p, w, 10d, 1.5d);
		Vec3d nv = Vectory.getZ(p, w, 10d, 1.5d);
		double eH = p.getEyeHeight();
		
		for(double i = 0; i < 2*PI; i += 0.2D) {
			
			int r = Utilities.getRandom(100,1);
			CustomEndRod rod;
			
	        double nx1 = 1.5d * nv.x + x;
	        double ny1 = 1.5d * nv.y + y;
	        double nz1 = 1.5d * nv.z + z;
			
            double xb = a*cos(i);
            double yb = a*sin(i);
            double zb = 1;
			
            double xi = xa.x * xb + ya.x * yb + nv.x * zb;
            double yi = xa.y * xb + ya.y * yb + nv.y * zb;
            double zi = xa.z * xb + ya.z * yb + nv.z * zb;
			
            if(r < 50) rod = new CustomEndRod(w, nx1 + xi, ny1 + yi + eH, nz1 + zi, 30, 0d, 0d, 0d, CustomEndRod.ORANGE_DARK);
			else rod = new CustomEndRod(w, nx1 + xi, ny1 + yi+ eH, nz1 + zi, 30, 0d, 0d, 0d, CustomEndRod.ORANGE_LIGHT);
			
			Main.proxy.particle(w, rod);
			
			if(Utilities.getRandom(100, 1) < 30) {
				
				spark(w, nx1 + xi, ny1 + yi+ eH, nz1 + zi, i, a, xa, ya, nv);
				
			}
			
			
		}
		
		
	}
	
	
	private static void spark(World w, double x, double y, double z, double i, double a, Vec3d xa, Vec3d ya, Vec3d nv) {
		
		System.out.println("Debug\n:"
							+ "i: " + i + ", (+ PI/2) = " + (i+PI/2)
							+ "/nsin(i): " + sin(i + PI/2) + ", cos(i) =" + cos(i+PI/2)+
							"/n ");
		
		double xMult = a*cos(i + (PI/2))/4d;
		double yMult = a*sin(i + (PI/2))/4d;
		double xSpeed = xa.x * xMult + ya.x * yMult + nv.x;
		double ySpeed = xa.x * xMult + ya.x * yMult + nv.x;
		
		CustomEndRod rod = new CustomEndRod(w, x, y, z, 10, xMult, yMult, 0d, CustomEndRod.ORANGE_SPARK);
		((WorldServer)w).spawnParticle(EnumParticleTypes.FLAME, x, y, z, a*cos(i + (PI/2))/4d, a*sin(i + (PI/2))/4d, 0d);
		Main.proxy.particle(w, rod);
		
	}
	
	public static void shield(World w, double x, double y, double z, EntityPlayer p, double a) {
		
		Vec3d xa = Vectory.getX(p, w, 10d, 1.5d);
		Vec3d ya = Vectory.getY(p, w, 10d, 1.5d);
		Vec3d nv = Vectory.getZ(p, w, 10d, 1.5d);
		double eH = p.getEyeHeight();
		
		for(double i = 0; i < 2*PI; i += 0.2D) {
			
			int r = Utilities.getRandom(100,1);
			CustomEndRod rod;
			
			//Starting coords
						//radius
	        double nx1 = cos(PI/4) * nv.x + x;
	        double ny1 = sin(PI/4) * nv.y + y;
	        double nz1 = nv.z + z;
			
            double xb = a*cos(i);
            double yb = a*sin(i);
            double zb = 1;
			
            double xi = xa.x * xb + ya.x * yb + nv.x * zb;
            double yi = xa.y * xb + ya.y * yb + nv.y * zb;
            double zi = xa.z * xb + ya.z * yb + nv.z * zb;
			
			if(r < 50) rod = new CustomEndRod(w, nx1 + xi, ny1 + yi + eH, nz1 + zi, 30, 0d, 0d, 0d, CustomEndRod.ORANGE_DARK);
			else rod = new CustomEndRod(w, nx1 + xi, ny1 + yi+ eH, nz1 + zi, 30, 0d, 0d, 0d, CustomEndRod.ORANGE_LIGHT);
			
			Main.proxy.particle(w, rod);
			
			if(Utilities.getRandom(100, 1) < 70) {
				
				spark(w, nx1 + xi, ny1 + yi+ eH, nz1 + zi, i, a, xa, ya, nv);
				
			}
			
			
			
		}
		
        double xi = xa.x * cos(PI/4) + ya.x + nv.x * sin(PI/4);
        double yi = xa.y * cos(PI/4) + ya.y + nv.y * sin(PI/4);
        double zi = xa.z * cos(PI/4) + ya.z + nv.z * sin(PI/4);
        
        double xi2 = xa.x * -cos(PI/4) + ya.x + nv.x * sin(PI/4);
        double yi2 = xa.y * -cos(PI/4) + ya.y + nv.y * sin(PI/4);
        double zi2 = xa.z * -cos(PI/4) + ya.z + nv.z * sin(PI/4);
        
        w.spawnParticle(EnumParticleTypes.END_ROD, x + xi, y + yi, z + zi, 0d, 0d, 0d);
        w.spawnParticle(EnumParticleTypes.END_ROD, x + xi2, y + yi2, z + zi2, 0d, 0d, 0d);
	}
	
}
