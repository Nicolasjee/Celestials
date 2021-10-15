package poseidon.mod.test.blocks;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import static java.lang.Math.*;

public class TileHelper {

	/**
	 * Decides how many ticks a cycle should take
	 * @return
	 */
	public static int getTicks(int min, int max, TileEntityTest t) {
		
		Random rand = new Random();
		int range = max - min + 1;	
		int r = rand.nextInt(range) + min;
		t.hasTickCycle = true;
		
		return r;
		
	}
	
	public static void init(TileEntityTest t) {
		
		BlockPos pos = t.getPos();
		t.x1 = pos.getX();
		t.y1 = pos.getY();
		t.z1 = pos.getZ();
		t.init = true;
		t.tick = 0;
		
	}
	
	public static void setInstructions(TileEntityTest t) {
		
		World w = t.getWorld();
		double x1 = t.x1;
		double y1 = t.y1;
		double z1 = t.z1;
		
		//need in reset method
		double xP = getInt(3, -3);
		double zP = getInt(3, -3);
		
		t.zP = zP;
		t.xP = xP;
		
		//Coördinates to
		double x2 = x1 + xP;
		double z2 = z1 + zP;
		double y2 = getDouble((int)x2, (int)y1, (int)z2, w)+1;
		
		//Middle coördinates
		double fx = ((x1+x2)/2);
		double top = getInt(3,-3);
		double fy = y1+3;
		double fz = ((z1+z2)/2);
		
		t.a = (fy - y1) / ((fx-x1)*(fx-x2));
		t.b = ((fy - y2) / (fx - x2)) - ((fy - y1) / (fx - x1));
		double b1 = ((fy-y2)/(fx-x2)) - ((9)/((fx-x2)));
		double b2 = ((fy-y2)/(fx-x2)) - (((fy-y1)*((x1-x2)/2)) / (fx-x1));
		double b3 = (y1-y2)-((t.a*(pow(x1,2)-pow(x2,2)))/(x1-x2));
		System.out.println("B: " + t.b);
		System.out.println("B1: " + b1);
		System.out.println("B2: " + b2);
		System.out.println("B3: " + b3);
		System.out.println("fx+x2= " + (fx+x2) + ", and: fx-x2:" + (fx-x2) + ", fx - x1" + (fx-x1));
		
		t.dX = x1 - x2;
		t.m = (x2 - x1) / (z2 - z1);
		
		t.hasInstructions = true;

		
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
			if(w.getBlockState(new BlockPos(x,y+i,z)) != Blocks.AIR.getDefaultState())
				return y+i;
			else if(w.getBlockState(new BlockPos(x,y-i,z)) != Blocks.AIR.getDefaultState())
				return y-i;
		}
		
		return 0.0D;
		
	}
}
