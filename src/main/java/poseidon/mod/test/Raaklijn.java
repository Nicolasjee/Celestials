package poseidon.mod.test;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;

import static java.lang.Math.*;

public class Raaklijn {

	public static void circle(double x, double y, double z, World w) {
		
		for(double i = 0d; i < 2*PI; i += PI / 2) {
			
			set(x, y, z, i, w);
			//thisisvector(x, z, y, i, w);
			
		}
		double x1 = x + cos(PI/4);
		double z1 = z + sin(PI/4);
		double m = -(x1 - x) / (z1 - z);
		//w.spawnParticle(EnumParticleTypes.END_ROD, x1, y, z1, -0.1D, 0d, m*-0.1D);
		//w.spawnParticle(EnumParticleTypes.FLAME, x + cos(0), y, z, 0d, 0d, 0d);
		
		
	}
	
	public static void set(double x, double y, double z, double i, World w) {
		
		double x1 = x + cos(i);
		double z1 = z + sin(i);

		double m = -(x1 - x) / (z1 - z);
		
		if(i > 0 && i < PI / 2) w.spawnParticle(EnumParticleTypes.END_ROD, x1, y, z1, cos(i + (PI/2))/10D, 0d, sin(i + (PI/2))/10D);
		if(i > PI/2 && i < PI) w.spawnParticle(EnumParticleTypes.END_ROD, x1, y, z1, cos(i + (PI/2))/10D, 0d, sin(i + (PI/2))/10D);
		if(i > PI && i < (3*PI) / 2) w.spawnParticle(EnumParticleTypes.END_ROD, x1, y, z1, cos(i + (PI/2))/10D, 0d, sin(i + (PI/2))/10D);
		if(i > (3*PI) / 2 && i < 2*PI) w.spawnParticle(EnumParticleTypes.END_ROD, x1, y, z1, cos(i + (PI/2))/10D, 0d, sin(i + (PI/2))/10D);
		
	}
	
	private static void spawn(World world, CustomEndRod rod) {
		Main.proxy.particle(world, rod);
	}
	
	private static double cot(double a) {
		return (pow(tan(a), -1));
	}
	
	private static double getComma(double a) {
		System.out.println("a: " + a);
		double b = a;
		if(b > 2300203 || b < -212309283) {
			System.out.println("How did it become infinity? " + b);
			return 0.01D;
		}
		int c = 0;
		
		if(a > 0) {
			while(a > 0.10d && a != 0) {
				System.out.println("1 :" + b + " / 10^" + c + " =" + a);
				a /= 10;
				c++;
			}
			while(a < 0.01d) {
				a *= 10;
				c++;
			}
		}
		
		if(a < 0) {
			while(a > -0.01d) {
				a *= 10;
				c++;
			}
			while(a < -0.10d && a != 0) {
				System.out.println("2 :" + b + " / 10^" + c + " =" + a);
				a /= 10;
				c++;
			}
		}
		return a;
	}
	
	private static void thisisvector(double x1, double z1, double y, double i, World w) {
		double Bx, Bz, norm;
		
		x1 += cos(i);
		z1 += sin(i);
		
		Bx = 1;		//maakt op zich niet uit welke waarde, wordt later toch nog genormeerd
		Bz = -(x1 * Bx) / z1;
		
		norm = sqrt(pow(Bx, 2) + pow(Bz, 2));
		
		Bx /= norm;
		Bz /= norm;
		
		System.out.println(x1 + " " + z1 + " " + Bx + " " + Bz);
		
		w.spawnParticle(EnumParticleTypes.END_ROD, x1, y, z1, Bx/10, 0, Bz/10);
	}
	
}
