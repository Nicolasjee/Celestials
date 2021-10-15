package poseidon.mod.util.helpers;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;

public class BlockConnectUtil {

	public static void connect(double[] f, double[] t, WorldServer server, EnumParticleTypes type, int amount) {
		
		int loopSize = 0;
		
		if(f.length % 3 == 0 && t.length % 3 == 0 && f.length == t.length) {
			loopSize = f.length / 3;
		}
		
		if(loopSize == 0) System.out.println("Loopsize == 0??");
		
		for(int i = 1; i < loopSize+1; i++) {
			
			int tick = 0;
			
			double dX = -x(i, f) + x(i, t);
			double dZ = -z(i, f) + z(i, t);
			double dY = -y(i, f) + y(i, t);
			
			double xStep = dX / amount;
			double zStep = dZ / amount;
			double yStep = dY / amount;
			
			while(tick < amount) {
				server.spawnParticle(type, true, x(i, f) + xStep * tick, y(i, f) + yStep * tick, z(i, f) + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
				tick++;
			}
		}
		
		
	}
	
	public static double x(int num, double[] t) {
		return t[(num*3)-3];
		
			//System.out.println("Num was not applicable:" + num + " returned first element" );
			//return t[0];
		
	}
	
	public static double y(int num, double[] t) {
		return t[(num*3)-2];
		/*
		if(num == 1) return t[1];
		if(num == 2) return t[4];
		if(num == 3) return t[7];
		if(num == 4) return t[10];
		if(num == 5) return t[13];

		else {
			System.out.println("Num was not applicable:" + num + " returned first element" );
			return t[1];
		}*/
	}

	
	/*
	 * BlockConnectUtil.connect(new double[] {
													   pos.north(10).east(10).getX() - 1.0D , pos.north(10).east(10).up(6).getY(), pos.north(10).east(10).getZ() + 1.0D,
													   pos.south(10).east(10).getX() + 1.0D, pos.south(10).east(10).up(6).getY(), pos.south(10).east(10).getZ() + 1.0D,
													   pos.south(10).west(10).getX() + 1.0D, pos.south(10).west(10).up(6).getY(), pos.south(10).west(10).getZ() - 1.0D,
													   pos.north(10).west(10).getX() - 1.0D, pos.north(10).west(10).up(6).getY(), pos.north(10).west(10).getZ() + 1.0D,
												
													   
													   pos.north(10).east(10).getX() - 1.0D , pos.north(10).east(10).up(6).getY(), pos.north(10).east(10).getZ() + 1.0D,
													   pos.south(10).east(10).getX() + 1.0D, pos.south(10).east(10).up(6).getY(), pos.south(10).east(10).getZ() + 1.0D,
													   pos.south(10).west(10).getX() + 1.0D, pos.south(10).west(10).up(6).getY(), pos.south(10).west(10).getZ() - 1.0D,
													   pos.north(10).west(10).getX() - 1.0D, pos.north(10).west(10).up(6).getY(), pos.north(10).west(10).getZ() + 1.0D,
													   
													   pos.north(10).east(10).getX() - 1.0D , pos.north(10).east(10).getY(), pos.north(10).east(10).getZ() + 1.0D,
													   pos.south(10).east(10).getX() + 1.0D, pos.south(10).east(10).getY(), pos.south(10).east(10).getZ() + 1.0D,
													   pos.south(10).west(10).getX() + 1.0D, pos.south(10).west(10).getY(), pos.south(10).west(10).getZ() - 1.0D,
													   pos.north(10).west(10).getX() - 1.0D, pos.north(10).west(10).getY(), pos.north(10).west(10).getZ() + 1.0D,

				},
														
									     new double[] {pos.south(10).east(10).getX() + 1.0D, pos.south(10).east(10).up(6).getY(), pos.south(10).east(10).getZ() + 1.0D,
									    		 	   pos.south(10).west(10).getX() + 1.0D, pos.south(10).west(10).up(6).getY(), pos.south(10).west(10).getZ() - 1.0D,
												       pos.north(10).west(10).getX() - 1.0D, pos.north(10).west(10).up(6).getY(), pos.north(10).west(10).getZ() + 1.0D,
												       pos.north(10).east(10).getX() - 1.0D , pos.north(10).east(10).up(6).getY(), pos.north(10).east(10).getZ() + 1.0D,
												       
												       pos.north(10).east(10).getX() - 1.0D , pos.north(10).east(10).getY(), pos.north(10).east(10).getZ() + 1.0D,
													   pos.south(10).east(10).getX() + 1.0D, pos.south(10).east(10).getY(), pos.south(10).east(10).getZ() + 1.0D,
													   pos.south(10).west(10).getX() + 1.0D, pos.south(10).west(10).getY(), pos.south(10).west(10).getZ() - 1.0D,
													   pos.north(10).west(10).getX() - 1.0D, pos.north(10).west(10).getY(), pos.north(10).west(10).getZ() + 1.0D,
													   
													   pos.south(10).east(10).getX() + 1.0D, pos.south(10).east(10).getY(), pos.south(10).east(10).getZ() + 1.0D,
									    		 	   pos.south(10).west(10).getX() + 1.0D, pos.south(10).west(10).getY(), pos.south(10).west(10).getZ() - 1.0D,
												       pos.north(10).west(10).getX() - 1.0D, pos.north(10).west(10).getY(), pos.north(10).west(10).getZ() + 1.0D,
												       pos.north(10).east(10).getX() - 1.0D , pos.north(10).east(10).getY(), pos.north(10).east(10).getZ() + 1.0D,
													   
													   
									    		       }, 
									     (WorldServer)worldIn, EnumParticleTypes.END_ROD, 80);
			}

	 */
	
	public static double z(int num, double[] z) {
		return z[(num*3)-1];
		/*if(num == 1) return z[2];
		if(num == 2) return z[5];
		if(num == 3) return z[8];
		if(num == 4) return z[11];
		if(num == 5) return z[14];
		else {
			System.out.println("Num was not applicable:" + num + " returned first element" );
			return z[0];
		}*/
	}
	
}
