package poseidon.mod.util;

import static java.lang.Math.*;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.handlers.registry.SoundsHandler;
import poseidon.mod.util.helpers.CoH;

public class ParticleUtil implements TeslaProperties {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static double sd = 0.004D;
	public static double st = 0.004D;
	public static double sp = 0.008D;
	public static boolean b = false;
	public static Random rand = new Random();
	public static double d0 = rand.nextGaussian() * 0.02D;
	public static double d1 = rand.nextGaussian() * 0.02D;
	public static double d2 = rand.nextGaussian() * 0.02D;
	public static double d4 = (rand.nextGaussian() * 1.0D) / 2.0D;
	public static double d3 = 10.0D;
	
	public static void slope(WorldServer server, BlockPos pos, double m, double q) {
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		double step = 1.0D;
		double j = 0.0D;
		
		for(int i = -500; i < 250; i++) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, x + j, m*j + q + y, z, 1, 0d, 0d, 0d, 0);
			
			j = step*i;
			//j += step*i;
		}
		
	}
	
	public static void slope2(WorldServer server, BlockPos pos, double m, double q) {
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		double step = 0.1D;
		double j = 0.0D;
		
		for(int i = 0; i < 500; i++) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, x + j, m*j + q + y, z, 1, 0d, 0d, 0d, 0);
			

			j += step*i;
		}
		
	}
	
	public static void figure(WorldServer server, BlockPos pos, int tick, int f) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		double pi = Math.PI;
		double i = 0.0D;
		double iStep = (pi*2)/180;
		
		for(int j = 1; j < 10; j++) {
			double currentI = (tick+j)*iStep;
			server.spawnParticle(EnumParticleTypes.END_ROD, x + 0.5*cos(currentI), y + 0.5*sin(currentI) + 3.0D, z + 0.5*cos(currentI), 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, x + 0.5*cos(currentI), y + (0.5*sin(currentI)) + 3.0D, z + f*0.5*cos(currentI), 1, 0.0D, 0.0D, 0.0D, 0);
		}
	
		
		
	}
	
	public static void quickCircle(WorldServer server, BlockPos pos, EnumParticleTypes type) {
		
		double pi = Math.PI;
		double i = 0.0D;
		double iStep = (pi*2)/30;
		
		double x = pos.getX() + 0.5D; double y = pos.getY(); double z = pos.getZ() + 0.5D;
		
		while(i < pi*2) {
			server.spawnParticle(type, true, x + 0.5*cos(i), y + 0.1D, z + 0.5*sin(i), 1, 0.0D, 0.0D, 0.0D, 0);
			i = i + iStep;
		}
	}

	public static void torchLogo(WorldServer server, BlockPos pos, EnumParticleTypes type) {
		
		double x = pos.getX() + 0.5D;
		double y = pos.getY();
		double z = pos.getZ() + 0.5D;
		
		double pi = Math.PI;
		
		double step = (pi * 2)/360;
		double yStep = 0.01D;
		double zStep = (pi*2)/360;
		double xStep = (pi*2)/360;
		
		double end = pi * 2;
		double i = 0.0D;
		int count = 0;
		
		double height = 360 * 0.01D;
		double p = 0;
		double pStep = 0.005D;
		
		while(i < end) {
			
			if(yStep * count < height/2) {
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				p += pStep;
			}
			
			if(yStep * count >= height/2) {
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				p -= pStep;
			}
			
			i += step;
			count++;
			
		}
		
	}
	
	public static void riftOpening(WorldServer server, BlockPos pos, EnumParticleTypes type) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY();
		double z = pos.getZ() + 0.5D;
		
		double pi = Math.PI;
		
		double step = (pi * 2)/360;
		double yStep = 0.01D;
		double zStep = (pi*2)/360;
		double xStep = (pi*2)/360;
		
		double end = pi * 2;
		double i = 0.0D;
		int count = 0;
		
		double height = 360 * 0.01D;
		double p = 0;
		double pStep = 0.002D;
		
		while(i < end) {
			
			if(yStep * count < height/2) {
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				p += pStep;
			}
			
			if(yStep * count >= height/2) {
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				p -= pStep;
			}
			
			i += step;
			count++;
			
		}
	}
	
	public static void riftOpeningSteps(WorldServer server, BlockPos pos, EnumParticleTypes type, int tick) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY();
		double z = pos.getZ() + 0.5D;
		
		double pi = Math.PI;
		
		double step = (pi * 2)/360;
		double yStep = 0.01D;
		double zStep = (pi*2)/360;
		double xStep = (pi*2)/360;
		
		double end = pi * 2;
		
		double height = 360 * 0.01D;
		double pStep = 0.003D;

		for(int b = 0; b < 5; b++) {
			tick = tick - b;
			double p = pStep * tick;
			double i = step * tick;
			int count = tick;
			
			if(yStep * tick < height/2) {
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
			}
				
			if(yStep * tick >= height/2) {
				p = (180*pStep) - (tick - 180)*pStep;
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x + p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
			}
		}
			
		
	}
	
	public static void crystal(WorldServer server, BlockPos pos) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY();
		double z = pos.getZ() + 0.5D;
		
		double pi = Math.PI;
		
		double step = (pi * 2)/360;
		double yStep = 0.01D;
		double zStep = (pi*2)/360;
		double xStep = (pi*2)/360;
		
		double end = pi * 2;
		double i = 0.0D;
		int count = 0;
		
		double height = 360 * 0.01D;
		double p = 0;
		double pStep = 0.01D;
		
		while(i < end) {
			
			if(yStep * count < height/2) {
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x + p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x - p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x - p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x + p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				p += pStep;
			}
			
			if(yStep * count >= height/2) {
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x + p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x - p*sin(i), y + yStep*count, z + p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x - p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x + p*sin(i), y + yStep*count, z - p*cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				p -= pStep;
			}
			
			i += step;
			count++;
			
		}
	}
	/*
	public static double sin(double i) {
		return Math.sin(i);
	}
	
	public static double cos(double i) {
		return Math.cos(i);
	}
	*/
	public static void upgrade(WorldServer server, BlockPos pos, BlockPos pos1, double value) {
		
		double x = pos.getX() + 0.5D; double y = pos.getY()+ value; double z = pos.getZ() + 0.5D;
		double xi = pos1.getX()+ 0.5D; double yi = pos1.getY() + 0.25D; double zi = pos1.getZ()+ 0.5D;
		double i = 0.0D;
		
		int p = 200;
		
		double dX = xi - x;
		double dY = yi - y;
		double dZ = zi - z;
		
		double xStep = dX / p;
		double yStep = dY / p;
		double zStep = dZ / p;
		
		double xPlus = 0.0D;
		double yPlus = 0.0D;
		double zPlus = 0.0D;
		
		while(i < p) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x + xPlus, y + yPlus, z + zPlus, 1, 0.0D, 0.0D, 0.0D, 0);
			
			xPlus = xPlus + xStep;
			yPlus = yPlus + yStep;
			zPlus = zPlus + zStep;
			i++;
		}
		
	}
	
	public static void mirror(WorldServer server, BlockPos pos, EnumParticleTypes type) {
		
		double x = pos.getX() + 0.5D; double y = pos.getY(); double z = pos.getZ() + 0.5D;
		
		double pi = Math.PI;
		double i = 0;
		double iStep = (Math.PI *2) /360;
		double ym = 0;
		double ymStep = 2.0D / 180;

		while(i < Math.PI*2) {

			if(i < Math.PI) {
				System.out.println("1: " + x + Math.sin(i) + ", " + (y + 2.0D - ym) + ", z: " + z + Math.cos(i));
				server.spawnParticle(type, true, x + Math.sin(i), y + 2.0D - ym, z + Math.cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - Math.sin(i), y + 2.0D - ym, z - Math.cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x + Math.cos(i), y + 2.0D - ym, z + Math.sin(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - Math.cos(i), y + 2.0D - ym, z - Math.sin(i), 1, 0.0D, 0.0D, 0.0D, 0);
				ym = ymStep + ym;
			}
			
			if(i > Math.PI) {
				server.spawnParticle(type, true, x + Math.sin(i), y + 2.0D - ym, z + Math.cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - Math.sin(i), y + 2.0D - ym, z - Math.cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x + Math.cos(i), y + 2.0D - ym, z + Math.sin(i), 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(type, true, x - Math.cos(i), y + 2.0D - ym, z - Math.sin(i), 1, 0.0D, 0.0D, 0.0D, 0);
				ym = -ymStep + ym;
			}
			//server.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, true, x + Math.sin(i), y + 2.0D - ym, z + x + Math.cos(i), 1, 0.0D, 0.0D, 0.0D, 0);
			i = i + iStep;
			
		}
		
	}

	public static void blockCOnnect(WorldServer server, BlockPos pos1, BlockPos pos2, double xAdd, double yAdd, double zAdd, double xAdd2, double yAdd2, double zAdd2) {
		
		double valueX = xAdd;
		double valueZ = yAdd;
		double valueY = zAdd;
		
		double valueX2 = xAdd2;
		double valueZ2 = yAdd2;
		double valueY2 = zAdd2; 	
		
		double x1 = pos1.getX() + valueX; double x2 = pos2.getX() + valueX2;
		double y1 = pos1.getY() + valueY; double y2 = pos2.getY() + valueY2;
		double z1 = pos1.getZ() + valueZ; double z2 = pos2.getZ() + valueZ2;
		
		double dX = x1 - x2;
		double dY = y1 - y2;
		double dZ = z1 - z2;
		
		int amountParticles = (int) ((Math.abs(dX) + Math.abs(dZ)) / 2)*15;
		//System.out.println("Amount: " + amountParticles + ", dX: " + dX + ", dZ: " + dZ);
		
		double stepX = dX / amountParticles;
		double stepY = dY / amountParticles;
		double stepZ = dZ / amountParticles;
		
		int loopStep = 1;
		int loopStart = 0;
		int loopEnd = amountParticles;
		
		while(loopStart < loopEnd) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x2 + stepX * loopStart, y2 + stepY * loopStart, z2 + stepZ * loopStart, 1, 0.0D, 0.0D, 0.0D, 0);
			
			loopStart += loopStep;
		}
		
	}
	
	public static void energyShield2(WorldServer server, double x1, double y1, double z1, double x2, double y2, double z2) {
		double dX = x1 - x2;
		double dY = y1 - y2;
		double dZ = z1 - z2;
		
		int amountParticles = (int) ((Math.abs(dX) + Math.abs(dZ)) / 2)*15;
		//System.out.println("Amount: " + amountParticles + ", dX: " + dX + ", dZ: " + dZ);
		
		int k = 100;
		
		double stepX = dX / k;
		double stepY = dY / k;
		double stepZ = dZ / k;
		
		int i = 0;
		
		
		while(i < k) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x2 + stepX * i, y2 + stepY * i, z2 + stepZ * i, 1, 0.0D, 0.0D, 0.0D, 0);
			
			i += 1;
		}
		
	}
	
	public static void energyShield(WorldServer server, BlockPos A, BlockPos B) {
			
		double x1 = A.getX() + 0.5D; double x2 = B.getX() + 0.5D;
		double y1 = A.getY() + 0.5D; double y2 = B.getY() + 0.5D;
		double z1 = A.getZ() + 0.5D; double z2 = B.getZ() + 0.5D;
		
		double dX = x1 - x2;
		double dY = y1 - y2;
		double dZ = z1 - z2;
		
		int amountParticles = (int) ((Math.abs(dX) + Math.abs(dZ)) / 2)*15;
		//System.out.println("Amount: " + amountParticles + ", dX: " + dX + ", dZ: " + dZ);
		
		int k = 100;
		
		double stepX = dX / k;
		double stepY = dY / k;
		double stepZ = dZ / k;
		
		int i = 0;
		
		
		while(i < k) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x2 + stepX * i, y2 + stepY * i, z2 + stepZ * i, 1, 0.0D, 0.0D, 0.0D, 0);
			
			i += 1;
		}
		
	}
	
	public static void test(WorldServer server, EntityPlayer player) {
		double posX = player.posX - (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		double posY = player.posY + (double) player.getEyeHeight() - 0.1D;
		double posZ = player.posZ - (double)(MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		float f = 0.4F;
		double motionX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * f);
		double motionZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * f);
		double motionY = (double)(-MathHelper.sin((player.rotationPitch + 2.0F) / 180.0F * (float)Math.PI) * f);
		//server.spawnParticle(EnumParticleTypes.END_ROD, posX + motionX, posY + motionY, posZ + motionZ, player.motionX, player.motionY + 0.1D, player.motionZ);
		server.spawnParticle(EnumParticleTypes.END_ROD, true, posX + Math.cos(((player.rotationYaw / 180.0F * (float)Math.PI) * 0.16F) + 180.0F), posY, posZ + Math.sin(((player.rotationYaw / 180.0F * (float)Math.PI) * 0.16F) + 180.0F), 1, 0.0D, 0.0D, 0.0D, 0.0D, 0);
	}
	
	public static void barrierShield(WorldServer world, BlockPos pos, EnumFacing i, int tick) {
		
		double up = (tick * 0.10) + 1.0D;
		
		if(i == EnumFacing.EAST || i == EnumFacing.WEST) {
			world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + 0.5D, pos.getY() + up, pos.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
			world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + 0.5D, pos.getY() + up, pos.getZ() + 0.5D, 1, 0.0D, 0.0D, 0.0D, 0);
			world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + 0.5D, pos.getY() + up, pos.getZ() + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			//world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + 0.5D, pos.getY() + up, pos.getZ() + 0.25D, 1, 0.0D, 0.0D, 0.0D, 0);
			//world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + 0.5D, pos.getY() + up, pos.getZ() + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
		}
		if(i == EnumFacing.SOUTH || i == EnumFacing.NORTH) {
			world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX(), pos.getY() + up, pos.getZ() + 0.5D, 1, 0.0D, 0.0D, 0.0D, 0);
			world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + 0.5D, pos.getY() + up, pos.getZ() + 0.5D, 1, 0.0D, 0.0D, 0.0D, 0);
			world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + 1.0D, pos.getY() + up, pos.getZ() + 0.5D, 1, 0.0D, 0.0D, 0.0D, 0);
			//world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + 0.25D, pos.getY() + up, pos.getZ() + 0.5D, 1, 0.0D, 0.0D, 0.0D, 0);
			//world.spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + 0.75D, pos.getY() + up, pos.getZ() + 0.5D, 1, 0.0D, 0.0D, 0.0D, 0);
		}
		
	}
	
	public static void sides(BlockPos pos, WorldServer world, EnumParticleTypes type) {
		world.spawnParticle(type, true, pos.getX(), pos.getY(), pos.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
		world.spawnParticle(type, true, pos.getX() + 1.0D, pos.getY(), pos.getZ() + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
		world.spawnParticle(type, true, pos.getX(), pos.getY(), pos.getZ() + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
		world.spawnParticle(type, true, pos.getX() + 1.0D, pos.getY(), pos.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
		world.spawnParticle(type, true, pos.getX(), pos.getY() + 1.0D, pos.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
		world.spawnParticle(type, true, pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ() + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
		world.spawnParticle(type, true, pos.getX(), pos.getY() + 1.0D, pos.getZ() + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
		world.spawnParticle(type, true, pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
	}
	
	public static void sideDownNether(BlockPos[] pos, WorldServer world, int tick) {
		EnumParticleTypes type = EnumParticleTypes.FLAME;
		
		for(int i = 0; i < pos.length; i++) {
			
			BlockPos position = pos[i];
			double x = position.getX(); double y = position.getY() + 1.0D; double z = position.getZ();
			
			double dY = 3.0D;
			double amount = 200;
			double step = dY / 200;
			
			world.spawnParticle(type, true, x, y - step*tick, z, 1, 0.0D, 0.0D, 0.0D, 0);
			world.spawnParticle(type, true, x + 1.0D, y - step*tick, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			world.spawnParticle(type, true, x,y - step*tick, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			world.spawnParticle(type, true, x + 1.0D, y - step*tick, z, 1, 0.0D, 0.0D, 0.0D, 0);
			
		}
		
	}
	
	public static void velocity(WorldServer server, EntityPlayer playerIn) {
		
		double x = playerIn.posX;
		double y = playerIn.posY;
		double z = playerIn.posZ;
		double d4 = 0.0D;
		
		while(d4 == 0.0D || d4 > 0.75D) {
			Random randi = new Random();
			d4 = (randi.nextGaussian() * 1.0D) / 4.5D;
		}
		
		
		server.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, true,
				x + d0,
				0.5D + y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		
		server.spawnParticle(EnumParticleTypes.CLOUD, true,
				x + d0,
				+ 0.5D+ y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		/*
		 * 		server.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, true,
				x + d0,
				0.5D + y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		
		server.spawnParticle(EnumParticleTypes.CLOUD, true,
				x + d0,
				+ 0.5D+ y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		 */
			//	- SPEED																						args
	}
	
	public static void riftUpdate(WorldServer server, EntityPlayer playerIn) {
		
		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY;
		double z = playerIn.posZ;
		
		while(d4 == 0.0D || d4 > 0.75D) {
			Random randi = new Random();
			d4 = (randi.nextGaussian() * 1.0D) / 4.5D;
		}
		
		server.spawnParticle(EnumParticleTypes.PORTAL, true,
				x + d0,
				0.5D + y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		
		server.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, true,
				x + d0,
				+ 0.5D+ y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		
	}

	public static void riftTakeOff(WorldServer server, EntityPlayer playerIn) {
		
		double t = 0;
		double tm = 0;
		while (tm < 4000) {
			BlockPos pos = playerIn.getPosition();
			t += Math.PI / 8;
			for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / 2) {
				double x = 0.3 * (4 * Math.PI - t) * Math.cos(t + phi) + playerIn.posX;
				double y = 0.2 * t + playerIn.posY - 0.5;
				double z = 0.3 * (4 * Math.PI - t) * Math.sin(t + phi) + playerIn.posZ;
				pos.add(x, y, z);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x, y, z, 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x, y + 1.0D, z, 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x, y + 2.0d, z, 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x, y + 3.0d, z, 1, 0.0D, 0.0D, 0.0D, 0);
				pos.subtract(new Vec3i(x, y, z));
			}
			tm++;
			
		}
	}
	
	public static void trail(WorldServer server, EntityPlayer playerIn) {
		
		Vec3d dir = playerIn.getLook(1.0F).normalize();
		BlockPos pos = playerIn.getPosition();

		double t = 0;
		double tm = 6000;
		double x = 0;
		double y = 0;
		double z = 0;

		while (tm > 1) {

			t += 0.5;
			x = dir.x * t + pos.getX();
			y = dir.y * t + 1.5D + pos.getY();
			z = dir.z * t + pos.getZ();
			pos.add(x, y, z);
			server.spawnParticle(EnumParticleTypes.LAVA, true, x, y, z, 1, 0.0D, 0.0D, 0.0D, 0);
			pos.subtract(new Vec3i(x, y, z));

			tm--;

		}
		
	}

	public static void setCorruption(WorldServer server, BlockPos pos) {
		
		double x = pos.getX(); double y = pos.getY() + 1.0D; double z = pos.getZ();
		
		double p = 0.0D;
		double step = 0.04D;
		
		while(p < 1.001D) {
			
			server.spawnParticle(EnumParticleTypes.FLAME, false, x + p, y, z, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.FLAME, false, x, y, z + p, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.FLAME, false, x + p, y, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.FLAME, false, x + 1.0D, y, z + p, 1, 0.0D, 0.0D, 0.0D, 0);
			
			p += step;
		}
	}
	
	public static void fall(WorldServer server, EntityPlayer playerIn) {
		
		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY + 0.5D;
		double z = playerIn.posZ;
		
		while(d4 == 0.0D || d4 > 0.75D) {
			Random randi = new Random();
			d4 = (randi.nextGaussian() * 1.0D) / 4.5D;
		}
		

		server.spawnParticle(EnumParticleTypes.PORTAL, true,
				x + d0,
				y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		
	}
	
	public static void depth(WorldServer server, EntityPlayer playerIn) {
		
		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY + 0.25D;
		double z = playerIn.posZ;
		
		while(d4 == 0.0D || d4 > 0.75D) {
			Random randi = new Random();
			d4 = (randi.nextGaussian() * 1.0D) / 4.5D;
		}
		
		server.spawnParticle(EnumParticleTypes.WATER_DROP, true,
				x + d0,
				y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		
	}

	public static void fire(WorldServer server, EntityPlayer playerIn) {
		
		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY + 0.25D;
		double z = playerIn.posZ;

		while(d4 == 0.0D || d4 > 0.75D) {
			Random randi = new Random();
			d4 = (randi.nextGaussian() * 1.0D) / 4.5D;
		}
		
		
		server.spawnParticle(EnumParticleTypes.FLAME, true,
				x + d0,
				+ 0.5D+ y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		
		
	}
	
	public static void projectile(WorldServer server, EntityPlayer playerIn) {
		
		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY + 0.35D;
		double z = playerIn.posZ;
		
		while(d4 == 0.0D || d4 > 0.75D) {
			Random randi = new Random();
			d4 = (randi.nextGaussian() * 1.0D) / 4.5D;
		}
		
		server.spawnParticle(EnumParticleTypes.CRIT, true,
				x + d0,
				+ 0.5D+ y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		
	}
	
	public static int getRandom() {
		Random rn2 = new Random();
		int maximum2 = 100;
		int minimum2 = 0;
		int range2 = maximum2 - minimum2;
		int b2 = rn2.nextInt(range2) + minimum2;
		return b2;
	}
	
	public static void glide(WorldServer server, EntityPlayer playerIn) {
		
		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY + 1.0D;
		double z = playerIn.posZ;
		
		while(d4 == 0.0D || d4 > 0.75D) {
			Random randi = new Random();
			d4 = (randi.nextGaussian() * 1.0D) / 4.5D;
		}

		server.spawnParticle(EnumParticleTypes.CLOUD, true,
				x + d0,
				y + d0,
				z + d1, 1, d1  + d4
				, d1 + d4, d1 + d4, 0);
		
	}
	
	public static void crown(WorldServer server, EntityPlayer playerIn) {
		
		BlockPos pos = playerIn.getPosition();
		Random rand = new Random();
		BlockPos pos2 = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);
		double d1 = (double)((float)pos2.getX() + (rand.nextFloat()*2)-1);
        double d2 = (double)((float)pos2.getY() + rand.nextFloat());
        double d3 = (double)((float)pos2.getZ() + (rand.nextFloat()*2)-1);
        server.spawnParticle(EnumParticleTypes.REDSTONE, true, d1, d2, d3, 1, 0.0D, 0.0D, 0.0D, 0);
        
	}
	
	public static void sponge(WorldServer server, BlockPos pos) {
		
		Random rand = new Random();
		int i = 0;
		while(i < 40) {
			server.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 1, 0.0D, 0.0D, 0.0D, 0);
			i++;
		}
		
	}
	
	public static void knowledge(BlockPos pos, BlockPos center, WorldServer w, int t) {
		Random rand = new Random();
		double d0 = (double)pos.getX() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double d1 = (double)pos.getY() - (double)(rand.nextFloat() * 0.1F) + 1.0D;
        double d2 = (double)pos.getZ() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double d3 = (double)(0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);
   
        double i = 0.0D;
        double k = 0.0D;
        
        double j = 0.0D;
        
		if(t == 1) {
			while(i < 2.3D) {
	        	w.spawnParticle(EnumParticleTypes.END_ROD, true, d0 - i, d1 - k, d2 - i, 1, 0.0D, 0.0D, 0.0D, 0);
	        	i = i + 0.009D;
	        	k = k + sd;
			}
		}
		
		else if(t == 2) {
			while(i < 2.3D) {
	        	w.spawnParticle(EnumParticleTypes.END_ROD, true, d0 - i, d1 - k, d2 + i, 1, 0.0D, 0.0D, 0.0D, 0);
	        	i = i + 0.009D;
	        	k = k +sd;
			}
		}
		
		else if(t == 3) {
			while(i < 2.3D) {
	        	w.spawnParticle(EnumParticleTypes.END_ROD, true, d0 + i, d1 - k, d2 + i, 1, 0.0D, 0.0D, 0.0D, 0);
	        	i = i + 0.009D;
	        	k = k + sd;
			}
		}
		
		else if(t == 4) {
			while(i < 2.3D) {
		        w.spawnParticle(EnumParticleTypes.END_ROD, true, d0 + i, d1 - k, d2 - i, 1, 0.0D, 0.0D, 0.0D, 0);
		        i = i + 0.009D;
		        k = k + sd;	        
			}
		}
		
		else if(t == 5) {
			while(j < 4.3D) {
		        w.spawnParticle(EnumParticleTypes.END_ROD, true, d0, d1 - k, d2 - j, 1, 0.0D, 0.0D, 0.0D, 0);
		        j = j + 0.009D; 
		        k = k + st;
			}			
		}
		
		else if(t == 6) {
			while(j < 4.3D) {
		        w.spawnParticle(EnumParticleTypes.END_ROD, true, d0 + j, d1 - k, d2, 1, 0.0D, 0.0D, 0.0D, 0);
		        j = j + 0.009D;  
		        k = k + st;
			}			
		}
	
		else if(t == 7) {
			while(j < 4.3D) {
		        w.spawnParticle(EnumParticleTypes.END_ROD, true, d0, d1 - k, d2 + j, 1, 0.0D, 0.0D, 0.0D, 0);
		        j = j + 0.009D;  
		        k = k + st;
			}			
		}

		else if(t == 8) {
			while(j < 4.3D) {
		        w.spawnParticle(EnumParticleTypes.END_ROD, true, d0 - j, d1 - k, d2, 1, 0.0D, 0.0D, 0.0D, 0);
		        j = j + 0.009D;  
		        k = k + st;
			}			
		}
	}

	public static void circle(double x, double y, double z, WorldServer server, int tick) {
		Random rand = new Random();
		double d0 = x;
		double d1 = y;
		double d2 = z;
		
        double i = 0.0D;
        double is = (2*Math.PI) / 180;
        
        double k = 2.0D;
        double l = 2.0D;
        
        double j = 0.0D;
        double js = 0.0045D;
        
    	if(tick > 180) {
        	k = k + (tick-180)*0.1D;
    	}        	
    	
    	
    	i = i + tick*is;
    	j = j + tick*js;
        	
        	server.spawnParticle(EnumParticleTypes.END_ROD, true, (Math.sin(i) / k) + d0, d1 + j, (Math.cos(i) / k) + d2, 1, 0.0D, 0.0D, 0.0D, 0);
        	server.spawnParticle(EnumParticleTypes.END_ROD, true, (-Math.sin(i) / k) + d0, d1 + j, (-Math.cos(i) / k) + d2, 1, 0.0D, 0.0D, 0.0D, 0);
        	

        	

        
	}
	
	public static void witchCapture(double x, double y, double z, WorldServer server) {
		Random rand = new Random();
		double d0 = x;
		double d1 = y;
		double d2 = z;
		
        double i = 0.0D;
        double is = (2*Math.PI) / 360;
        
        double k = 1.0D;
        double l = 1.0D;
        
        double j = 0.0D;
        double js = 0.007D;
        
        while(i < Math.PI *3) {
        	
        	server.spawnParticle(EnumParticleTypes.END_ROD, true, (Math.sin(i) / k) + d0, d1 + j, (Math.cos(i) / k) + d2, 1, 0.0D, 0.0D, 0.0D, 0);
        	server.spawnParticle(EnumParticleTypes.END_ROD, true, (-Math.sin(i) / k) + d0, d1 + j, (-Math.cos(i) / k) + d2, 1, 0.0D, 0.0D, 0.0D, 0);
        	
        	i = i + is;
        	j = j + js;
        	
        	if(i > Math.PI*2) {
	        	k = k + 0.1D;
        	}
        }
	}

	public static void ground(WorldServer server, BlockPos p, int t) {
		
		Random rand = new Random();

		   
        double i = 0.0D;
        double k = 0.0D;
        
        double j = 0.0D;
       
        if(t == 1) {
			while(i < 1.5D) {
		        server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.v(1, p, 1) - i, CoH.v(1, p, 2) + k, CoH.v(1, p, 3), 1, 0.0D, 0.0D, 0.0D, 0);
		        i = i + 0.009D;
		        k = k + sp;
			}
        }
        
        if(t == 2) {
			while(i < 1.5D) {
		        server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.v(2, p, 1), CoH.v(2, p, 2) + k, CoH.v(2, p, 3) + i, 1, 0.0D, 0.0D, 0.0D, 0);
		        i = i + 0.009D;
		        k = k + sp;
			}
        }
        
        if(t == 3) {
			while(i < 1.5D) {
		        server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.v(3, p, 1) + i, CoH.v(3, p, 2) + k, CoH.v(3, p, 3), 1, 0.0D, 0.0D, 0.0D, 0);
		        i = i + 0.009D;
		        k = k + sp;
			}
        }
        
        if(t == 4) {
			while(i < 1.5D) {
		        server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.v(4, p, 1), CoH.v(4, p, 2) + k, CoH.v(4, p, 3) - i, 1, 0.0D, 0.0D, 0.0D, 0);
		        i = i + 0.009D;
		        k = k + sp;
			}
        }
        
        if(t == 5) {
        	while(i < 1.5D) {
		        server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.m(p, 1), CoH.m(p, 2) + k, CoH.m(p, 3) + i, 1, 0.0D, 0.0D, 0.0D, 0);
		        i = i + 0.009D;
		        k = k + sp;
        	}
        }
        
        if(t == 6) {
        	while(i < 1.5D) {
		        server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.m(p, 1) + i, CoH.m(p, 2) + k, CoH.m(p, 3), 1, 0.0D, 0.0D, 0.0D, 0);
		        i = i + 0.009D;
		        k = k + sp;
        	}
        }
        
        if(t == 7) {
        	while(i < 1.5D) {
		        server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.m(p, 1), CoH.m(p, 2) + k, CoH.m(p, 3) - i, 1, 0.0D, 0.0D, 0.0D, 0);
		        i = i + 0.009D;
		        k = k + sp;
        	}
        }
        
        if(t == 8) {
        	while(i < 1.5D) {
		        server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.m(p, 1) - i, CoH.m(p, 2) + k, CoH.m(p, 3), 1, 0.0D, 0.0D, 0.0D, 0);
		        i = i + 0.009D;
		        k = k + sp;
        	}
        }
        
		
		
	}

	public static void aiolonEdge(WorldServer server, BlockPos p, String b, double am, World world) {
		

			Main.proxy.playSoundBlock(p, world, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, 1.0F, 0.4F);
			Random rand = new Random();
			double i = 0.0D;
			int s = 0;
			double inv = 1.0D - am;
			
			while(i < am) {
				
				server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.n(1, p, 1), CoH.n(1, p, 2) - i, CoH.n(1, p, 3), 1, 0.0D, 0.0D, 0.0D, 0, 1, 1, 1);
				server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.n(2, p, 1), CoH.n(2, p, 2) - i, CoH.n(2, p, 3), 1, 0.0D, 0.0D, 0.0D, 0, 1, 0, 1);
				server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.n(3, p, 1), CoH.n(3, p, 2) - i, CoH.n(3, p, 3), 1, 0.0D, 0.0D, 0.0D, 0, 1, 1, 0);
				server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.n(4, p, 1), CoH.n(4, p, 2) - i, CoH.n(4, p, 3), 1, 0.0D, 0.0D, 0.0D, 0, 1, 0, 0);
				
				
				i = i + 0.01D;
				s++;
			}
			
			double j = am;
			while(j < am) {
				server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.n(1, p, 1), CoH.n(1, p, 2) - 1.0D + j, CoH.n(1, p, 3), 1, 0.0D, 0.0D, 0.0D, 0, 0, 1, 1);
				server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.n(2, p, 1), CoH.n(2, p, 2) - 1.0D + j, CoH.n(2, p, 3), 1, 0.0D, 0.0D, 0.0D, 0, 1, 0, 1);
				server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.n(3, p, 1), CoH.n(3, p, 2) - 1.0D + j, CoH.n(3, p, 3), 1, 0.0D, 0.0D, 0.0D, 0, 0, 0, 1);
				server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.n(4, p, 1), CoH.n(4, p, 2) - 1.0D + j, CoH.n(4, p, 3), 1, 0.0D, 0.0D, 0.0D, 0, 1, 1, 1);
				j = j + 0.01D;
			}
			
		
		
	}

	public static void aiolonDone(WorldServer server, BlockPos post, List<Boolean> a, String ad) {
		
		//TODO chorus fruit sound
		Random rand = new Random();
		double d0 = (double)post.getX() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double d1 = (double)post.getY() - (double)(rand.nextFloat() * 0.1F) + 0.001D;
        double d2 = (double)post.getZ() + 0.55D - (double)(rand.nextFloat() * 0.1F);
		
		double xm = 0.0D;
        double ym = 0.0D;
        double zm = 0.0D;

        BlockPos pos = new BlockPos(post.getX() - 1.0D, post.getY(), post.getZ());
		
        double v = CoH.p(3.0D, 0.5D);

		while(xm < 0.5D) {
			
	       	if(a.get(0)) server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.l(1, pos, 1) + xm, d1 - ym, CoH.l(1, pos, 3) + zm, 1, 0.0D, 0.0D, 0.0D, 0);
	       	if(a.get(1)) server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.l(2, pos, 1), d1 - ym, CoH.l(2, pos, 3) + zm, 1, 0.0D, 0.0D, 0.0D, 0);
	       	if(a.get(2)) server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.l(3, pos, 1) - xm, d1 - ym, CoH.l(3, pos, 3) + zm, 1, 0.0D, 0.0D, 0.0D, 0);
	       	if(a.get(3)) server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.l(4, pos, 1) + xm, d1 - ym, CoH.l(4, pos, 3), 1, 0.0D, 0.0D, 0.0D, 0);
	       	if(a.get(4)) server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.l(5, pos, 1), d1 - ym, CoH.l(5, pos, 3), 1, 0.0D, 0.0D, 0.0D, 0);
	       	if(a.get(5)) server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.l(6, pos, 1) - xm, d1 - ym, CoH.l(6, pos, 3), 1, 0.0D, 0.0D, 0.0D, 0);
	       	if(a.get(6)) server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.l(7, pos, 1) + xm, d1 - ym, CoH.l(7, pos, 3) - zm, 1, 0.0D, 0.0D, 0.0D, 0);
	       	if(a.get(7)) server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.l(8, pos, 1), d1 - ym, CoH.l(8, pos, 3) - zm, 1, 0.0D, 0.0D, 0.0D, 0);
	       	if(a.get(8)) server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.l(9, pos, 1) - xm, d1 - ym, CoH.l(9, pos, 3) - zm, 1, 0.0D, 0.0D, 0.0D, 0);
			
	       	
	       	xm = xm + 0.001D;
	       	zm = zm + 0.001D;
	       	ym = ym + (CoH.p(3.0D, 0.5D) / 500);
	       	
	       	
		}
	
		for(int sd = 0; sd < a.size(); sd++) {
			System.out.println("Element: " + a.get(sd));
		}

		
	}

	public static void stasisSound(World world, BlockPos pos) {
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsHandler.STASIS, SoundCategory.AMBIENT, 1.0F, 1.0F);
	}

	public static void noPower(WorldServer server, BlockPos p) {

		server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.v(1, p, 1) - 0.05D, CoH.v(1, p, 2) - 0.5D, CoH.v(1, p, 3), 1, 0.0D, 0.0D, 0.0D, 0);
		server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.v(2, p, 1), CoH.v(2, p, 2) - 0.5D, CoH.v(2, p, 3) + 0.05D, 1, 0.0D, 0.0D, 0.0D, 0);
		server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.v(3, p, 1) + 0.05D, CoH.v(3, p, 2) - 0.5D, CoH.v(3, p, 3), 1, 0.0D, 0.0D, 0.0D, 0);
		server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.v(4, p, 1), CoH.v(4, p, 2) - 0.5D, CoH.v(4, p, 3) - 0.05D, 1, 0.0D, 0.0D, 0.0D, 0);
	}
	
	public static void inactive(WorldServer server, BlockPos p) {

		server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.v(1, p, 1) - 0.05D, CoH.v(1, p, 2) - 0.5D, CoH.v(1, p, 3), 1, 0.0D, 0.0D, 0.0D, 0);
		server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.v(2, p, 1), CoH.v(2, p, 2) - 0.5D, CoH.v(2, p, 3) + 0.05D, 1, 0.0D, 0.0D, 0.0D, 0);
		server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.v(3, p, 1) + 0.05D, CoH.v(3, p, 2) - 0.5D, CoH.v(3, p, 3), 1, 0.0D, 0.0D, 0.0D, 0);
		server.spawnParticle(EnumParticleTypes.REDSTONE, true, CoH.v(4, p, 1), CoH.v(4, p, 2) - 0.5D, CoH.v(4, p, 3) - 0.05D, 1, 0.0D, 0.0D, 0.0D, 0);
	}

	public static void drawbridgeAppear(WorldServer server, BlockPos pos, EnumParticleTypes type) {
		Random random = new Random();
        double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i)
        {
            double d1 = (double)((float)pos.getX() + random.nextFloat());
            double d2 = (double)((float)pos.getY() + random.nextFloat());
            double d3 = (double)((float)pos.getZ() + random.nextFloat());

            if (i == 0 && !server.getBlockState(pos.up()).isOpaqueCube())
            {
                d2 = (double)pos.getY() + 0.0625D + 1.0D;
            }

            if (i == 1 && !server.getBlockState(pos.down()).isOpaqueCube())
            {
                d2 = (double)pos.getY() - 0.0625D;
            }

            if (i == 2 && !server.getBlockState(pos.south()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() + 0.0625D + 1.0D;
            }

            if (i == 3 && !server.getBlockState(pos.north()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() - 0.0625D;
            }

            if (i == 4 && !server.getBlockState(pos.east()).isOpaqueCube())
            {
                d1 = (double)pos.getX() + 0.0625D + 1.0D;
            }

            if (i == 5 && !server.getBlockState(pos.west()).isOpaqueCube())
            {
                d1 = (double)pos.getX() - 0.0625D;
            }

            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
            {
                server.spawnParticle(type, true, d1, d2, d3, 1, 0.0D, 0.0D, 0.0D, 0);
            }
        }
	}

	public static void vortex(WorldServer server, BlockPos pos) {
		double x = pos.getX() + 0.5D; double y = pos.getY(); double z = pos.getZ() + 0.5D;
		
		double p = 1.0D;
		
		double i = 0.0D;
		double j = (2*Math.PI) / 360;
		
		
		while(i < Math.PI * 4) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, p*Math.sin(i) + x, y, p*Math.cos(i) + z, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, p*Math.sin(i + 2*(Math.PI / 3)) + x, y, p*Math.cos(i + 2*(Math.PI / 3)) + z, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, p*Math.sin(i + 4*(Math.PI / 3)) + x, y, p*Math.cos(i + 4*(Math.PI / 3)) + z, 1, 0.0D, 0.0D, 0.0D, 0);
			
			p = p + 0.01D;
			i = i + j;
			
		}
	}
	
	public static void draw(WorldServer server, double x, double y, double z, double i, double p) {
		server.spawnParticle(EnumParticleTypes.END_ROD, true, p*Math.sin(i) + x, y, p*Math.cos(i) + z, 1, 0.0D, 0.0D, 0.0D, 0);
		server.spawnParticle(EnumParticleTypes.END_ROD, true, p*Math.sin(i + 2*(Math.PI / 3)) + x, y, p*Math.cos(i + 2*(Math.PI / 3)) + z, 1, 0.0D, 0.0D, 0.0D, 0);
		server.spawnParticle(EnumParticleTypes.END_ROD, true, p*Math.sin(i + 4*(Math.PI / 3)) + x, y, p*Math.cos(i + 4*(Math.PI / 3)) + z, 1, 0.0D, 0.0D, 0.0D, 0);
	}
	
	public static void laser(WorldServer server, BlockPos pos) {
		double l = Math.sqrt(5.0D);
		double var = 0.0D;
		double yVar = 0.0D;
		double step = 0.01D;
		
		double am = l / step;
		double yStep = 1 / am;
		
		while(var < l) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.q(1, pos.west(3).down(), 1) + var, CoH.q(1, pos.west(3).down(), 2) + yVar, CoH.q(1, pos.west(3).down(), 3), 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.q(2, pos.south(3).down(), 1), CoH.q(2, pos.south(3).down(), 2) + yVar, CoH.q(2, pos.south(3).down(), 3) - var, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.q(3, pos.east(3).down(), 1) - var, CoH.q(3, pos.east(3).down(), 2) + yVar, CoH.q(3, pos.east(3).down(), 3), 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.q(4, pos.north(3).down(), 1), CoH.q(4, pos.north(3).down(), 2) + yVar, CoH.q(4, pos.north(3).down(), 3) + var, 1, 0.0D, 0.0D, 0.0D, 0);
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.q(1, pos.west(3).up(), 1) + var, CoH.q(1, pos.west(3).up(), 2) - yVar, CoH.q(1, pos.west(3).up(), 3), 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.q(2, pos.south(3).up(), 1), CoH.q(2, pos.south(3).up(), 2) - yVar, CoH.q(2, pos.south(3).up(), 3) - var, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.q(3, pos.east(3).up(), 1) - var, CoH.q(3, pos.east(3).up(), 2) - yVar, CoH.q(3, pos.east(3).up(), 3), 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, CoH.q(4, pos.north(3).up(), 1), CoH.q(4, pos.north(3).up(), 2) - yVar, CoH.q(4, pos.north(3).up(), 3) + var, 1, 0.0D, 0.0D, 0.0D, 0);
			
			var = var + step;
			yVar = yVar + yStep;
		}
	}
	
	public static void obsidian(WorldServer server, BlockPos pos) {
		double p = 0.0D;
		double h = 0.0D;
		int c = 0; int d = 0;
		double q = 3.0D;
		while(p < 4*Math.PI) {
			while(h < 1.0D) {
				if(h > 0.7D) q = q + 0.5D;
				server.spawnParticle(EnumParticleTypes.TOTEM, true, pos.getX() + 0.5D, pos.getY() + h, pos.getZ() + 0.5D, 1, 2* Math.sin(p) / q, 0.3D, 2* Math.cos(p) / q, 0);
				h = h + 0.05D;
				c++;
			}
			q = 3;
			p = p + 0.1D;
			h = 0.0D;
			d++;
		}
	}

	public static void teleportParticles(WorldServer server, BlockPos pos, EntityPlayer playerIn) {
		Random rand = new Random();
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		for (int i = 0; i < 1000; ++i) {
			double d0 = rand.nextGaussian() * 0.02D;
	    	double d1 = rand.nextGaussian() * 0.02D;
	    	double d2 = rand.nextGaussian() * 0.02D;
	    	double d3 = 10.0D;
	    	
	    	EntityPlayer player = Minecraft.getMinecraft().player;
	    	
	    	double add1 = (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D;
	    	double add2 = (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D;
	    	double add3 = (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D;

	    	server.spawnParticle(EnumParticleTypes.PORTAL, true, x + add1, y + add2, z + add3, 1, 0.0D, 0.0D, 0.0D, 0, 0);
			server.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, true, x + add1, y + add2, z + add3, 1, 0.0D, 0.0D, 0.0D, 0);
	    }
	}
	
	public static void teleportParticles(WorldServer server, BlockPos pos) {
		Random rand = new Random();
		double x = pos.getX() + 0.5D; double y = pos.getY(); double z = pos.getZ() + 0.5D;
		for (int i = 0; i < 1000; ++i) {
			double d0 = rand.nextGaussian() * 0.02D;
	    	double d1 = rand.nextGaussian() * 0.02D;
	    	double d2 = rand.nextGaussian() * 0.02D;
	    	double d3 = 10.0D;
	    	

	    	
	    	double add1 = (double)(rand.nextFloat() * 0.6D * 2.0F) - (double)0.6D - d0 * 10.0D;
	    	double add2 = (double)(rand.nextFloat() * (2.0D - 2.4D)) - d1 * 5.0D;
	    	double add3 = (double)rand.nextFloat() * 0.6D * 2.0F - (double)0.6D - d2 * 10.0D;

	    	server.spawnParticle(EnumParticleTypes.PORTAL, true, x + add1, y + add2, z + add3, 1, 0.0D, 0.0D, 0.0D, 0, 0);
			server.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, true, x + add1, y + add2, z + add3, 1, 0.0D, 0.0D, 0.0D, 0);
	    }
	}

	public static void customParticle(WorldServer server, double x, double y, double z) {
		server.spawnParticle(EnumParticleTypes.END_ROD, true, x, y, z, 1, 0.0D, 0.0D, 0.0D, 0);
	}
	
	public static void cross(WorldServer server, BlockPos pos) {
		
		double i = 0.0D;
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		while(i < 1.0D) {
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x + i, y, z + i, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x + i, y, z + 1.0D - i, 1, 0.0D, 0.0D, 0.0D, 0);
			
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x, y - 1.0D + i, z + i, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x, y - 1.0D + i, z + 1.0D - i, 1, 0.0D, 0.0D, 0.0D, 0);
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x - i + 1.0D, y - 1.0D + i, z, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x + i, y - 1.0D + i, z, 1, 0.0D, 0.0D, 0.0D, 0);
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x + 1.0D - i, y - 1.0D + i, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x + i, y - 1.0D + i, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x + 1.0D, y - 1.0D + i, z + 1.0D - i, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x + 1.0D, y - 1.0D + i, z + i, 1, 0.0D, 0.0D, 0.0D, 0);
			
			i = i + 0.09D;
		}
		
	}
	
	public static void cross2(WorldServer server, BlockPos pos, EnumParticleTypes type) {
		double i = 0.0D;
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		while(i < 1.0D) {
			server.spawnParticle(type, true, x + i, y, z + i, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x + i, y, z + 1.0D - i, 1, 0.0D, 0.0D, 0.0D, 0);
			
			
			server.spawnParticle(type, true, x, y - 1.0D + i, z + i, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x, y - 1.0D + i, z + 1.0D - i, 1, 0.0D, 0.0D, 0.0D, 0);
			
			server.spawnParticle(type, true, x - i + 1.0D, y - 1.0D + i, z, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x + i, y - 1.0D + i, z, 1, 0.0D, 0.0D, 0.0D, 0);
			
			server.spawnParticle(type, true, x + 1.0D - i, y - 1.0D + i, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x + i, y - 1.0D + i, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			
			server.spawnParticle(type, true, x + 1.0D, y - 1.0D + i, z + 1.0D - i, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x + 1.0D, y - 1.0D + i, z + i, 1, 0.0D, 0.0D, 0.0D, 0);
			
			i = i + 0.2D;
		}
	}
	
	public static void highlightBlock(WorldServer server, BlockPos pos, double step, EnumParticleTypes type) {
		
		double x = pos.getX(); double y = pos.getY() + 1.0D; double z = pos.getZ();
		
		double p = 0.0D;
		
		while(p < 1.001D) {
			
			//top
			server.spawnParticle(type, true, x + p, y, z, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x, y, z + p, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x + p, y, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x + 1.0D, y, z + p, 1, 0.0D, 0.0D, 0.0D, 0);
			
			//bottom
			y -= 1.0D;
			server.spawnParticle(type, true, x + p, y, z, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x, y, z + p, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x + p, y, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x + 1.0D, y, z + p, 1, 0.0D, 0.0D, 0.0D, 0);
			y += 1.0D;
		
			//side N
			server.spawnParticle(type, true, x + 1.0D, y - p, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x + 1.0D, y - p, z, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x, y - p, z + 1.0D, 1, 0.0D, 0.0D, 0.0D, 0);
			server.spawnParticle(type, true, x, y - p, z, 1, 0.0D, 0.0D, 0.0D, 0);
			
			
			p += step;
		}
		
	}
	
	public static void highlightArea(WorldServer server, BlockPos from, BlockPos to) {
		
		double x = from.getX(); double y = from.getY(); double z = from.getZ();
		double xTo = to.getX(); double yTo = to.getY() + 1.0D; double zTo = to.getZ();
										   		// front		front->right   right
		double[] lowShelveStop = new double[] {xTo + 1.0D, y, z, xTo + 1.0D, y, zTo + 1.0D, x, y, zTo + 1.0D, x, y, zTo + 1.0D};
		double[] lowShelveStart = new double[] {x, y, z, xTo + 1.0D, y, z, xTo + 1.0D, y, zTo + 1.0D, x, y, z};
		

		
		
	}

	public static void sphere(WorldServer server, double x, double y, double z) {
		
	}
	
	public static void spawn(World w, double x, double y, double z) {
		w.spawnParticle(EnumParticleTypes.END_ROD, x, y, z, 0d, 0d, 0d);
	}
	
	public static void earthquake(WorldServer server, double x, double y, double z) {
		BlockPos pos = new BlockPos(x,y,z);
		
		Iterable<BlockPos> outer = pos.getAllInBox(new BlockPos(x + 14, y, z + 14), new BlockPos(x - 14, y, z - 14));
		Iterable<BlockPos> inner = pos.getAllInBox(new BlockPos(x + 12, y, z + 12), new BlockPos(x - 12, y, z -12));
		
		List<BlockPos> outerList = new ArrayList<BlockPos>();
		List<BlockPos> innerList = new ArrayList<BlockPos>();
	
		for(BlockPos p : outer) outerList.add(p);
		for(BlockPos p : inner) innerList.add(p);
		
		for(int i = 0; i < innerList.size(); i++) {
			if(outerList.contains(innerList.get(i))) outerList.remove(innerList.get(i));
		}
		
		for(int i = 0; i < innerList.size(); i++) {
			BlockPos p = innerList.get(i);
			server.spawnParticle(EnumParticleTypes.TOWN_AURA, true, (double)((float)p.getX() + rand.nextFloat()), (double)((float)p.getY() + 1.1F), (double)((float)p.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
		}

		
	}
	
	public static void halfEllips(EntityPlayer player, WorldServer server) {
		BlockPos pos = player.getPosition();
		System.out.println("method2é)");
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		double x1;
		double y1;
		double z1;
		
		double a = 2d;
		double b = 3d;
		double c = 7d;
		
		double radius = 2d;
		
		double start = 0.0D;
		double start_2 = 0d;
		double stop = PI*4;
		double add = PI / 180;
		
		while(start < PI) {
			while(start_2 < 2*PI) {
				
				x1 = a*cos(start)*cos(start_2);
				y1 = b*sin(start)*cos(start_2);
				z1 = c*sin(start_2);
				
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x + x1, y + y1, z + z1, 1, 0.0D, 0.0D, 0.0D, 0);
				
				start_2 += add;
			}
			
			start += add;
		}
		
	}
	
	
	public static void ellips(EntityPlayer player, WorldServer server) {
		BlockPos pos = player.getPosition();
		System.out.println("method2é)");
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		double x1;
		double y1;
		double z1;
		
		double a = 2d;	//cirkel heeft a, b, c als gelijke waarden --> ellips:  a, b, c verschillend
		double b = 3d;
		double c = 7d;
		
		double radius = 2d;
		
		double start = 0.0D;
		double start_2 = 0d;
		double stop = PI*4;
		double add = PI / 90;
		
		for(start = 0; start < 2*PI; start += add) {
			for(start_2 = 0; start_2 < add; start_2 += add) {
				
				x1 = a*cos(start)*cos(start_2);
				y1 = b*sin(start)*cos(start_2);
				z1 = c*sin(start_2);
				
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x + x1, y + y1, z + z1, 1, 0.0D, 0.0D, 0.0D, 0);
			}
		}
		
	}

	
	public static void kegel(double x, double y, double z, WorldServer server) {
		

		
		double a = 1d/4; //basis cirkel
		double b = 2d;
		double c = 1d;
		
		double x1;
		double y1;
		double z1;
		
		double radius = 2d;
		
		double start = 0.0D;
		double v = 0d;
		double stop = PI*4;
		double add = PI / 90;
		
		for(start = 0; start < 2*PI; start += add) {
			for(v = 0; v < 2d; v += 0.1D) {
				
				x1 = a*v*cos(start);
				y1 = -c*v;
				z1 = a*v*sin(start);
				
				CustomEndRod rod = new CustomEndRod(server, x + x1, y + y1, z + z1, 1000);
				
				if(y1 < -1) Main.proxy.particle(server, rod);
				
				// -2 (v) < y < -1
				//if(y1 < -1) server.spawnParticle(EnumParticleTypes.REDSTONE, true, x + x1, y + y1, z + z1, 1, 0.0D, 0.0D, 0.0D, 0);
			}
		}
		
	}
	
	public static void portal(EntityPlayer player, WorldServer server) {
		BlockPos pos = player.getPosition();
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		double a = 1d;
		double b = 2d;
		double c = 3d;
		//bij dezelfde a,b,(c) gewone kegel
		
		double x1;
		double y1;
		double z1;
		
		double radius = 2d;
		
		double start = 0.0D;
		double v = 0d;
		double stop = PI*4;
		double add = PI / 90;
		
		for(start = 0; start < 2*PI; start += add) {

				
				x1 = a*cos(start);
				y1 = b*sin(start);
				z1 = c;
				
				server.spawnParticle(EnumParticleTypes.END_ROD, true, x + x1 + 3, y + y1 + 3, z + z1 + 3, 1, 0.0D, 0.0D, 0.0D, 0);
			
		}
		
	}
	
	public static void zandloper() {
		
	}
	
}
