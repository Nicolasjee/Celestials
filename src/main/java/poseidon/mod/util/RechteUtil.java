package poseidon.mod.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;
import poseidon.mod.networking.MessageParticle;

public class RechteUtil {

	/**
	 * @param from: blockpos from
	 * @param to: player to
	 * @return
	 */
	public static void getX(BlockPos from, EntityPlayer p, World world, WorldServer server, EnumParticleTypes type) {
		
		double x0 = from.getX() + 0.5D;
		double y0 = from.getY() + 1.0D;
		double z0 = from.getZ() + 0.5D;
		
		double x1 = p.posX;
		double y1 = p.posY + 1.5D;
		double z1 = p.posZ;
		
		alter(x0, x1, y0, y1, z0, z1, world, server, type);
		
		//puntenparen maken
		//projectie x/y
		double m1 = rechte('M', x0, x1, y0, y1);
		double m2 = rechte('M', x0, x1, z0, z1);
		double q1 = rechte('Q', x0, x1, y0, y1);
		double q2 = rechte('Q', x0, x1, z0, z1);
		
		//print("Rechte1: " + m1 + "x + " + q1);
		//print("Rechte2: " + m2 + "x +" + q2);
		
		double m = -(m1 + m2);
		double q = q1 + q2;
		
		double i = 0d;
		

	}
	
	private static double rechte(char choice, double x1, double x2, double y1, double y2) {
		
		// y - y1 = (y2-y1) / (x2-x1) * (x - x1)
		
		double ret = 0.0D;
		
		
		
		switch(choice) {
		
			case 'M': 
			
				ret = ((y2 - y1) / (x2 - x1));
				//debug(x1, x2, y1, y2, choice);
				break;
		
			case 'Q':
			
				ret = -(x1 * ((y2 - y1) / (x2 - x1))) + y1;
				//debug(x1, x2, y1, y2, choice);
				break;
			
		}
		
		
		
		return ret;
		
	}
	
	private static void print(String s) {
		System.out.println(s);
	}
	
	private static void debug(double x1, double x2, double y1, double y2, char choice) {
		if(choice == 'M') {
			print("M:\n");
			print("y2 (" + y2 + ") - y1 (" + y1 + ") : " + (y2 - y1));
			print("x2 (" + x2 + ") - x1 (" + x1 + ") : " + (x2 - x1));
			print("gedeeld: " + ((y2 - y1) / (x2 - x1)) + "\n");
			print("\n");
		}
		
		if(choice == 'Q') {
			print("Q\n");
			print("y2 (" + y2 + ") - y1 (" + y1 + ") : " + (y2 - y1));
			print("x2 (" + x2 + ") - x1 (" + x1 + ") : " + (x2 - x1));
			print("gedeeld: " + ((y2 - y1) / (x2 - x1)));
			print("x1 * gedeelde: " + ((y2 - y1) / (x2 - x1)) + " / " + x1 + " = " + x1 * ((y2 - y1) / (x2 - x1)));
			print("+ y1(" + y1 + "): " + (x1 * ((y2 - y1) / (x2 - x1))) + y1);
			print(" en minteken: " + -(x1 * ((y2 - y1) / (x2 - x1))) + y1);
			print("\n");
		}
	}
	
	private static void alter(double x1, double x2, double y1, double y2, double z1, double z2, World w, WorldServer s, EnumParticleTypes type) {
		
		double m = (y2 - y1) / (x2 - x1);
		double m1 = (z2 - z1) / (x2 - x1);
		double d1 = x2 - x1;
		int k = 0;
		double step = 0.0D;
		
		while(k < 100) {
			
			//s.spawnParticle(EnumParticleTypes.END_ROD, true, x1+step, y1+m*step, z1, 1, 0d, 0d, 0d, 0, 0);
			s.spawnParticle(EnumParticleTypes.END_ROD, true, x1+step, y1, z1+m1*step, 1, 0d, 0d, 0d, 0, 0);
			
			step += d1/100;
			k++;
		}

		
		
		double i = 0.0D;
		int j = 0;
		int[] array = {1,2,3,5,6};
		double d = x2 - x1;
		List<Integer> list = new ArrayList<Integer>();
		s.spawnParticle(EnumParticleTypes.END_ROD, true, x1, y1, z1, 1, 0.0d, 0.0D, 0.0D, 0);
		/*while(j < 100) {
			
			s.spawnParticle(EnumParticleTypes.END_ROD, true, x0 + i, y1 + m*i, z1, 1, 0.0d, 0.0D, 0.0D, 0);
			//s.spawnParticle(EnumParticleTypes.END_ROD, true, x0 + i, y1, z1 + m1*i, 1, 0.0d, 0.0D, 0.0D, 0);
			s.spawnParticle(type, true, x0 + i, y1 + m*i, z1 + m1*i, 1, 0.0d, 0.0D, 0.0D, 0);
			
			i += d/100;
			j++;
		}*/
		
		while(j < 100) {
			
			//CustomEndRod rod = new CustomEndRod(w, x0 + i, y1 + m*i, z1 + m1*i, 5);
			//rod.setPosition(x0 + i, y1 + m*i, z1 + m1*i);
			
			//Main.proxy.teleportParticles(w, rod);

			i += d/100;
			j++;
			
			
		}
		
	}
}
