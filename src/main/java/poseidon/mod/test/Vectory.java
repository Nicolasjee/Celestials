package poseidon.mod.test;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import static java.lang.Math.*;

import javax.annotation.Nullable;

public class Vectory {

	public static Vec3d perp(Vec3d on, Vec3d u) {
		Vec3d m = u;
		Vec3d o = on;
		Vec3d ut = u;
		return m.subtract(proj(o,ut));
	}
	
	public static Vec3d proj(Vec3d onto, Vec3d u) {
		Vec3d o = onto;
		Vec3d ut = u;
		return o.scale(o.dotProduct(ut) / o.lengthSquared());
	}
	
	/**
	 * @param p
	 * @param w
	 * @return
	 * 
	 * Spawning by:
	 * 
	 * double xb, yb, zb
	 * These should be the location where you want them.
	 * 
	 *      double xi = xa.x * xb + ya.x * yb + nv.x * zb;
     *      double yi = xa.y * xb + ya.y * yb + nv.y * zb;
     *      double zi = xa.z * xb + ya.z * yb + nv.z * zb;
     *      
     * The new doubles are the new placements following the vectors.
     * To get them in the world, use {@link World#spawnParticle(EnumParticleTypes, double, double, double, double, double, double, int...)}
     * Where you always count the current X, Y, Z of the block / player / entity to the vectors    
	 */
	public static Vec3d[] getVectors(EntityPlayer p, World w) {
		 double radius = 10;

	        
	        Vec3d nv = p.getLookVec().normalize();
	        BlockPos c = p.getPosition();
	        
	        double x = c.getX() + 0.5D;
	        double y = c.getY() + p.getEyeHeight();
	        double z = c.getZ() + 0.5D;
	        
	        double nx = radius * nv.x + x;
	        double ny = radius * nv.y + y;
	        double nz = radius * nv.z + z;

	        Vec3d ya = perp(nv, new Vec3d(0, 1, 0)).normalize();
	        Vec3d xa = ya.crossProduct(nv).normalize();
	        nv.scale(-1);
	        
	        return new Vec3d[] {xa, ya, nv};
	}
	
	public static Vec3d getX(EntityPlayer p, World w, double radius, double h) {
		return getVector('X', p, w, radius,h);
	}
	
	public static Vec3d getY(EntityPlayer p, World w, double radius, double h) {
		return getVector('Y', p, w, radius,h);
	}
	
	public static Vec3d getZ(EntityPlayer p, World w, double radius, double h) {
		return getVector('Z', p, w, radius,h);
	}
	
	public static Vec3d getVector(char type, EntityPlayer p, World w, double radius, double h) {
	        
	        Vec3d nv = p.getLookVec().normalize();
	        BlockPos c = p.getPosition();
	        
	        double x = c.getX();
	        double y = c.getY() + h;
	        double z = c.getZ();
	        
	        
	        Vec3d ya = perp(nv, new Vec3d(0, 1, 0)).normalize();
	        Vec3d xa = ya.crossProduct(nv).normalize();
	       // nv = nv.scale(-1d);
	        
	        if(type == 'X') return xa;
	        if(type == 'Y') return ya;
	        if(type == 'Z') return nv;
	        else {
	        	System.out.println("Type not recognized. Choose X,Y or Z. Passed type was: " + type + "\n Returned z vector by default. lol");
	        	return nv;
	        }
	}
	
	public static void doctorShield(EntityPlayer p, World w) {
		
		double radius = 1/4d;
		
		 Vec3d nv = p.getLookVec().normalize();
	        BlockPos c = p.getPosition();
	        
	        double x = c.getX() + 0.5D;
	        double y = c.getY() + p.getEyeHeight();
	        double z = c.getZ() + 0.5D;
	        
	        //Starting coördinates
	        double nx1 = radius * nv.x + x;
	        double ny1 = radius * nv.y + y;
	        double nz1 = radius * nv.z + z;

	        Vec3d ya = perp(nv, new Vec3d(0, 1, 0)).normalize();
	        Vec3d xa = ya.crossProduct(nv).normalize();
	       
	        
	        for (double theta = 0; theta < 2*PI; theta += (2*PI/360)) {

	            double xb = sin(theta);
	            double yb = cos(theta);
	            double zb = 1;
	            // Coordinates with respect to our basis
	            double xi = xa.x * xb + ya.x * yb + nv.x * zb;
	            double yi = xa.y * xb + ya.y * yb + nv.y * zb;
	            double zi = xa.z * xb + ya.z * yb + nv.z * zb;
	            
	            w.spawnParticle(EnumParticleTypes.END_ROD, x + xi, y + yi, z + zi, 0d, 0d, 0d);
	           
	        }
		
	}
	
	public static void drawInPlane(EntityPlayer p, World w) {

        // We will use these for drawing our parametric curve on the plane:
        //This is how far away we want the plane's origin to be:
        double radius = 10;

        
        Vec3d nv = p.getLookVec().normalize();
        BlockPos c = p.getPosition();
        
        double x = c.getX() + 0.5D;
        double y = c.getY() + p.getEyeHeight();
        double z = c.getZ() + 0.5D;
        
        //Starting coördinates
        double nx = radius * nv.x + x;
        double ny = radius * nv.y + y;
        double nz = radius * nv.z + z;

        Vec3d ya = perp(nv, new Vec3d(0, 1, 0)).normalize();
        Vec3d xa = ya.crossProduct(nv).normalize();
        nv.scale(-1d);
        
        for (double theta = 0; theta < 2*PI; theta += (2*PI/360)) {

            double xb = sin(theta);
            double yb = cos(theta);
            double zb = 1;
            // Coordinates with respect to our basis
            double xi = xa.x * xb + ya.x * yb + nv.x * zb;
            double yi = xa.y * xb + ya.y * yb + nv.y * zb;
            double zi = xa.z * xb + ya.z * yb + nv.z * zb;
            
            w.spawnParticle(EnumParticleTypes.END_ROD, x + xi, y + yi, z + zi, 0d, 0d, 0d);
           
        }
        
        
        //Altijd voor speler zetten
        /*
        double xi = xa.x * cos(PI/4) + ya.x + nv.x * sin(PI/4);
        double yi = xa.y * cos(PI/4) + ya.y + nv.y * sin(PI/4);
        double zi = xa.z * cos(PI/4) + ya.z + nv.z * sin(PI/4);
        
        double xi2 = xa.x * -cos(PI/4) + ya.x + nv.x * sin(PI/4);
        double yi2 = xa.y * -cos(PI/4) + ya.y + nv.y * sin(PI/4);
        double zi2 = xa.z * -cos(PI/4) + ya.z + nv.z * sin(PI/4);
        
        w.spawnParticle(EnumParticleTypes.END_ROD, x + xi, y + yi, z + zi, 0d, 0d, 0d);
        w.spawnParticle(EnumParticleTypes.END_ROD, x + xi2, y + yi2, z + zi2, 0d, 0d, 0d);
       
        */
	}
}
