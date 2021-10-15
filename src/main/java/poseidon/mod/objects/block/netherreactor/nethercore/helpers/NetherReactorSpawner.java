package poseidon.mod.objects.block.netherreactor.nethercore.helpers;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class NetherReactorSpawner {

	public static void stageZeroParticles(double[] f, double[] t, WorldServer server, int tick, int stage) {
		
		for(int i = 1; i < 5; i++) {
			
			double dX = -x(i, f) + x(i, t);
			double dZ = -z(i, f) + z(i, t);
			double dY = -y(i, f) + y(i, t);
			
			double xStep = dX / ReactorStageTime.stage0;
			double zStep = dZ / ReactorStageTime.stage0;
			double yStep = dY / ReactorStageTime.stage0;
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * tick, y(i, f) + yStep * tick, z(i, f) + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
			
		}
		
		
	}
	
	public static void stageOneParticles(double[] f, double[] t, WorldServer server, int tick, int stage) {
		
		tick = tick - stage*ReactorStageTime.stage0; 
		
		for(int i = 1; i < 5; i++) {
			
			double dX = -x(i, f) + x(i, t);
			double dZ = -z(i, f) + z(i, t);
			double dY = -y(i, f) + y(i, t);
			
			double xStep = dX / ReactorStageTime.stage1;
			double zStep = dZ / ReactorStageTime.stage1;
			double yStep = dY / ReactorStageTime.stage1;
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * tick, y(i, f) + yStep * tick, z(i, f) + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
			
		}
		
		
	}
	
	public static void stageOnebParticles(double[] f, double[] t, WorldServer server, int tick, int stage) {
		
		tick = tick - stage*ReactorStageTime.stage0; 
		
		for(int i = 1; i < 5; i++) {
			
			double dX = x(i, f) - x(i, t);
			double dZ = z(i, f) - z(i, t);
			double dY = y(i, f) - y(i, t);
			
			double xStep = dX / ReactorStageTime.stage1;
			double zStep = dZ / ReactorStageTime.stage1;
			double yStep = dY / ReactorStageTime.stage1;
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * tick, y(i, f) + yStep * tick, z(i, f) + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
			
		}
		
		
	}
	
	public static void stageTwoParticles(double[] f, double[] t, WorldServer server, int tick, int index) {
		
		tick = tick - ReactorStageTime.stage0 - ReactorStageTime.stage1;
		
		if(index == 1 || index == 2) {
			for(int i = 1; i < 5; i++) {
				
				double dX = -x(i, f) + x(i, t);
				double dZ = -z(i, f) + z(i, t);
				
				double xStep = dX / ReactorStageTime.stage2;
				double zStep = dZ / ReactorStageTime.stage2;
				
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * tick, y(i, f) + 0.1D, z(i, f) + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
				
			}
		}
		
		if(index == 4) {
			for(int i = 1; i < 5; i++) {
				
				double dX = -x(i, f) + x(i, t);
				double dZ = -z(i, f) + z(i, t);
				double dY = -y(i, f) + y(i, t);
				
				double xStep = dX / ReactorStageTime.stage2;
				double zStep = dZ / ReactorStageTime.stage2;
				double yStep = dY / ReactorStageTime.stage2;
				
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * tick, y(i, f) + yStep *tick, z(i, f) + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
				
			}
		}	
		
		
		if(index == 3) {
			for(int i = 1; i < 17; i++) {
				
				double dX = -x(i, f) + x(i, t);
				double dZ = -z(i, f) + z(i, t);
				
				double xStep = dX / ReactorStageTime.stage2;
				double zStep = dZ / ReactorStageTime.stage2;
				
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * tick, y(i, f) + 0.1D, z(i, f) + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
				
			}
		}
		
	}
	
	public static void stage4Particles(WorldServer server, double[] f, double[] t, int tick) {
		for(int i = 1; i < 13; i++) {
			
			double dX = -x(i, f) + x(i, t);
			double dY = -y(i, f) + y(i ,t);
			double dZ = -z(i, f) + z(i, t);
			
			double xStep = dX / 100;
			double yStep = dY / 100;
			double zStep = dZ / 100;
			
			
			for(int j = 0; j < 101; j++) {
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * j, y(i, f) + yStep *j, z(i, f) + j*zStep, 1, 0.0D, 0.0D, 0.0D, 0);
			}
			
		}
	}
//	
//	public static void stage5Particles(WorldServer server, double[] f, double[] t, int tick) {
//		
//		if(f.length == t.length) {
//			int smaller = f.length / 3;
//			for(int i = 1; i < smaller + 1; i++) {
//				
//				double dX = -x(i, f) + x(i, t);
//				double dY = -y(i, f) + y(i ,t);
//				double dZ = -z(i, f) + z(i, t);
//				
//				double xStep = dX / 50;
//				double yStep = dY / 50;
//				double zStep = dZ / 50;
//	
//				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * (tick), y(i, f) + yStep *(tick), z(i, f) + (tick)*zStep, 1, 0.0D, 0.0D, 0.0D, 0);
//				
//			}
//		}
//	}
//	
//	public static void stage6Particles(WorldServer server, double[] f, double[] t, int tick) {
//		tick = tick - 50;
//		if(f.length == t.length) {
//			int smaller = f.length / 3;
//			for(int i = 1; i < smaller + 1; i++) {
//				
//				double dX = -x(i, f) + x(i, t);
//				double dY = -y(i, f) + y(i ,t);
//				double dZ = -z(i, f) + z(i, t);
//				
//				double xStep = dX / 25;
//				double yStep = dY / 25;
//				double zStep = dZ / 25;
//	
//				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * (tick), y(i, f) + yStep *(tick), z(i, f) + (tick)*zStep, 1, 0.0D, 0.0D, 0.0D, 0);
//				
//			}
//		}
//	}
	
	public static void stage10(WorldServer server, double[] f, double[] t, int tick, int tickMinus) {
		
	}
	
	public static void stageUniversal(WorldServer server, double[] f, double[] t, int tick, int tickMinus) {
		int q = 100;
		tick = tick - tickMinus;
		tick = tick*4;
		if(f.length == t.length) {
			int smaller = f.length / 3;
			for(int i = 1; i < smaller + 1; i++) {
				
				double dX = -x(i, f) + x(i, t);
				double dY = -y(i, f) + y(i ,t);
				double dZ = -z(i, f) + z(i, t);
				
				double xStep = dX / q;
				double yStep = dY / q;
				double zStep = dZ / q;
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * (tick), y(i, f) + yStep *(tick), z(i, f) + (tick)*zStep, 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * (tick-1), y(i, f) + yStep *(tick-1), z(i, f) + (tick-1)*zStep, 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * (tick-2), y(i, f) + yStep *(tick-2), z(i, f) + (tick-2)*zStep, 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * (tick-3), y(i, f) + yStep *(tick-3), z(i, f) + (tick-3)*zStep, 1, 0.0D, 0.0D, 0.0D, 0);
			}
		}
	}
	
	
	public static void netherReactorConnect(WorldServer server, BlockPos f, BlockPos t, int tick) {
		
		double x1 = f.getX() + 0.5D; double y1 = f.getY() + 1.0D; double z1 = f.getZ() + 0.5D;
		double x2 = t.getX() + 0.5D; double y2 = t.getY() + 1.0D; double z2 = t.getZ() + 0.5D;
		
		tick *= 2;
		
		double dX = - x1 + x2;
		double dZ = - z1 + z2;
		double dY = - y1 + y2;
		
		double xStep = dX / 100;
		double zStep = dZ / 100;
		double yStep = dY / 100;
		
		server.spawnParticle(EnumParticleTypes.END_ROD, true, x1 + xStep * tick, y1 + yStep * tick, z1  + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
		server.spawnParticle(EnumParticleTypes.END_ROD, true, x1 + xStep * (tick-1), y1 + yStep * (tick-1), z1  + zStep * (tick-1), 1, 0.0D, 0.0D, 0.0D, 0);
		
	}
	
	public static void netherConnection(WorldServer server, BlockPos f, BlockPos t) {
		double x1 = f.getX() + 0.5D; double y1 = f.getY() + 1.0D; double z1 = f.getZ() + 0.5D;
		double x2 = t.getX() + 0.5D; double y2 = t.getY() + 1.0D; double z2 = t.getZ() + 0.5D;
		
		double dX = - x1 + x2;
		double dZ = - z1 + z2;
		double dY = - y1 + y2;
		
		double xStep = dX / 100;
		double zStep = dZ / 100;
		double yStep = dY / 100;
		
		for(int tick = 0; tick < 101; tick++) {
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x1 + xStep * tick, y1 + yStep * tick, z1  + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
		}
	}
	
	public static double x(int num, double[] t) {
		if(num == 1) return t[0];
		if(num == 2) return t[3];
		if(num == 3) return t[6];
		if(num == 4) return t[9];
		if(num == 5) return t[12];
		if(num == 6) return t[15];
		if(num == 7) return t[18];
		if(num == 8) return t[21];
		if(num == 9) return t[24];
		if(num == 10) return t[27];
		if(num == 11) return t[30];
		if(num == 12) return t[33];
		if(num == 13) return t[36];
		if(num == 14) return t[39];
		if(num == 15) return t[42];
		if(num == 16) return t[45];
		else {
			System.out.println("Num was not applicable:" + num + " returned first element" );
			return t[0];
		}
	}
	
	public static double y(int num, double[] t) {
		if(num == 1) return t[1];
		if(num == 2) return t[4];
		if(num == 3) return t[7];
		if(num == 4) return t[10];
		if(num == 5) return t[13];
		if(num == 6) return t[16];
		if(num == 7) return t[19];
		if(num == 8) return t[22];
		if(num == 9) return t[25];
		if(num == 10) return t[28];
		if(num == 11) return t[31];
		if(num == 12) return t[34];
		if(num == 13) return t[37];
		if(num == 14) return t[40];
		if(num == 15) return t[43];
		if(num == 16) return t[46];
		else {
			System.out.println("Num was not applicable:" + num + " returned first element" );
			return t[1];
		}
	}

	public static double z(int num, double[] z) {
		if(num == 1) return z[2];
		if(num == 2) return z[5];
		if(num == 3) return z[8];
		if(num == 4) return z[11];
		if(num == 5) return z[14];
		if(num == 6) return z[17];
		if(num == 7) return z[20];
		if(num == 8) return z[23];
		if(num == 9) return z[26];
		if(num == 10) return z[29];
		if(num == 11) return z[32];
		if(num == 12) return z[35];
		if(num == 13) return z[38];
		if(num == 14) return z[41];
		if(num == 15) return z[44];
		if(num == 16) return z[47];
		else {
			System.out.println("Num was not applicable:" + num + " returned first element" );
			return z[0];
		}
	}

	public static void NetherReactorHighlightningUtil(double[] f, double[] t, WorldServer server) {
		
		for(int i = 1; i < 13; i++) {
			
			int tick = 0;
			
			double dX = -x(i, f) + x(i, t);
			double dZ = -z(i, f) + z(i, t);
			double dY = -y(i, f) + y(i, t);
			
			double xStep = dX / ReactorStageTime.connectionParticles;
			double zStep = dZ / ReactorStageTime.connectionParticles;
			double yStep = dY / ReactorStageTime.connectionParticles;
			
			while(tick < ReactorStageTime.connectionParticles) {
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x(i, f) + xStep * tick, y(i, f) + yStep * tick, z(i, f) + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
				tick++;
			}
		}
		
		
	}
	

}
