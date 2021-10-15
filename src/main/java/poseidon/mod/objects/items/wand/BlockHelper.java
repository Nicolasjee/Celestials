package poseidon.mod.objects.items.wand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHelper {

	static double x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x29, x30;
	static double z1, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, z13, z14, z15, z16, z17, z18, z19, z20, z21, z22, z23, z24, z25, z26, z29, z30;
	static double y1, y2, y3, y4, y5, y6, y7, y8, y9, y10, y11, y12, y13, y14, y15, y16, y17, y18, y19, y20, y21, y22, y23, y24, y25, y26, y29, y30;
	static List<Double> list = new ArrayList<Double>();
	static List<Double> list2 = new ArrayList<Double>();
	
	public static void earthParticle(EntityPlayer playerIn, World worldIn) {
		
		 Random rand = new Random();
		 double x = playerIn.posX;
		 double y = playerIn.posY;
		 double z = playerIn.posZ;
		 

		 getPos(x, z);
		
		for (int i = 0; i < 1000; ++i)
	    {
			double d0 = rand.nextGaussian() * 0.02D;
	    	double d1 = rand.nextGaussian() * 0.02D;
	    	double d2 = rand.nextGaussian() * 0.02D;
	    	double d3 = 10.0D;
	    	
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x2 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z2 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x2 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z2 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
	    
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x3 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z3 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x3 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z3 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x4 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z4 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x4 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z4 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x5 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z5 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x5 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z5 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x6 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z6 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x6 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z6 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x7 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z7 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x7 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z7 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
	    
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x8 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z8 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x8 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z8 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x9 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z9 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x9 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z9 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x10 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z10 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x10 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z10 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x11 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z11 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x11 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z11 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);

			worldIn.spawnParticle(EnumParticleTypes.PORTAL, x12 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z12 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x12 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z12 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);

			worldIn.spawnParticle(EnumParticleTypes.PORTAL, x13 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z13 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x13 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z13 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);

			worldIn.spawnParticle(EnumParticleTypes.PORTAL, x14 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z14 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x14 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z14 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);

			worldIn.spawnParticle(EnumParticleTypes.PORTAL, x15 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z15 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x15 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z15 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);

			worldIn.spawnParticle(EnumParticleTypes.PORTAL, x16 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z16 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x16 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z16 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);

			worldIn.spawnParticle(EnumParticleTypes.PORTAL, x17 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z17 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x17 + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z17 + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);

	    }
		
	}
	public static void getPos(double x, double z) {
		
		 x2 = x + 3.0D;
		 z2 = z;
		 x3 = x - 3.0D;
		 z3 = z;
		 x4 = x + 2.0D;
		 z4 = z + 2.0D;
		 x5 = x - 2.0D;
		 z5 = z + 2.0D;
		 x6 = x + 1.0D;
		 z6 = z + 3.0D;
		 x7 = x - 1.0D;
	     z7 = z + 3.0D;
	     x8 = x + 2.0D;
	     z8 = z - 2.0D;
	     x9 = x - 2.0D;
	     z9 = z - 2.0D;
	     x10 = x + 1.0D;
	     z10 = z - 3.0D;
	     x11 = x - 1.0D;
	     z11 = z - 3.0D;
	     x12 = x;
	     z12 = z + 3.0D;
	     x13 = x;
	     z13 = z - 3.0D;
	     x14 = x - 3.0D;
	     z14 = z + 1.0D;
	     x15 = x - 3.0D;
	     z15 = z - 1.0D;
	     x16 = x + 3.0D;
	     z16 = z + 1.0D;
	     x17 = x + 3.0D;
	     z17 = z - 1.0D;
	     
	}
	
	
	//
	
	public static void infernoHelper(double x, double y, double z, World worldin) {
		getIPos(x, y, z, worldin);
		
		
	}
	public static void getIPos(double x, double y, double z, World worldIn) {
		  x2 = x + 1.0D;
		  z2 = z;
		  
		  x3 = x + 2.0D;
		  z3 = z;
		  
		  x4 = x;
		  z4 = z + 1.0D;
		  
		  x5 = x + 1.0D;
		  z6 = z + 1.0D;
		  
		  x7 = x + 2.0D;
		  z7 = z + 1.0D;
		  
		  x8 = x;
		  z8 = z + 2.0D;
		  
		  x9 = x + 1.0D;
		  z9 = z + 2.0D;
		  
		  x10 = x + 2.0D;
		  z10 = z + 2.0D;
		//
		  x11 = x - 1.0D;
		  z11 = z;
		  
		  x12 = x - 2.0D;
		  z12 = z;
		  
		  x13 = x - 1.0D;
		  z13 = z + 1.0D;
		  
		  x14 = x - 2.0D;
		  z14 = z + 1.0D;
		  
		  x15 = x - 1.0D;
		  z15 = z + 2.0D;
		  
		  x16 = x - 2.0D;
		  z16 = z + 2.0D;
		//
		  x17 = x + 2.0D;
		  z17 = z - 2.0D;
		  
		  x18 = x + 1.0D;
		  z18 = z - 2.0D;
		  
		  x19 = x;
		  z19 = z - 2.0D;
		  
		  x20 = x - 1.0D;
		  z20 = z - 2.0D;
		  
		  x21 = x - 2.0D;
		  z21 = z - 2.0D;
		//
		  x22 = x + 2.0D;
		  z22 = z - 1.0D;
		  
		  x23 = x + 1.0D;
		  z23 = z - 1.0D;
		  
		  x24 = x;
		  z24 = z - 1.0D;
		  
		  x25 = x - 1.0D;
		  z25 = z - 1.0D;
		  
		  x26 = x - 2.0D;
		  z26 = z - 1.0D;
		  
//		  y = y + 1.0D;
		  
//		  	BlockPos pos26 = new BlockPos(x, y, z);
//			BlockPos pos1 = new BlockPos(x2, y, z2);
//			BlockPos pos2 = new BlockPos(x3, y, z3);
//			BlockPos pos3 = new BlockPos(x4, y, z4);
//			BlockPos pos4 = new BlockPos(x5, y, z5);
//			BlockPos pos5 = new BlockPos(x6, y, z6);
//			BlockPos pos6 = new BlockPos(x7, y, z7);
//			BlockPos pos7 = new BlockPos(x8, y, z8);
//			BlockPos pos8 = new BlockPos(x9, y, z9);
//			BlockPos pos9 = new BlockPos(x10, y, z10);
//			BlockPos pos10 = new BlockPos(x11, y, z11);
//			BlockPos pos11 = new BlockPos(x12, y, z12);
//			BlockPos pos12 = new BlockPos(x13, y, z13);
//			BlockPos pos13 = new BlockPos(x14, y, z14);
//			BlockPos pos14 = new BlockPos(x15, y, z15);
//			BlockPos pos15 = new BlockPos(x16, y, z16);
//			BlockPos pos16 = new BlockPos(x17, y, z17);
//			BlockPos pos17 = new BlockPos(x18, y, z18);
//			BlockPos pos18 = new BlockPos(x19, y, z19);
//			BlockPos pos19 = new BlockPos(x20, y, z20);			
//			BlockPos pos20 = new BlockPos(x21, y, z21);
//			BlockPos pos21 = new BlockPos(x22, y, z22);
//			BlockPos pos22 = new BlockPos(x23, y, z23);
//			BlockPos pos23 = new BlockPos(x24, y, z24);
//			BlockPos pos24 = new BlockPos(x25, y, z25);
//			BlockPos pos25 = new BlockPos(x26, y, z26);
//			
			
		  List<BlockPos> positions = new ArrayList<BlockPos>();
		  List<BlockPos> pos = new ArrayList<BlockPos>();
		  pos.add(new BlockPos(x2, y, z2));
		  pos.add(new BlockPos(x3, y, z3));
		  pos.add(new BlockPos(x4, y, z4));
		  pos.add(new BlockPos(x5, y, z5));
		  pos.add(new BlockPos(x6, y, z6));
		  pos.add(new BlockPos(x7, y, z7));
		  pos.add(new BlockPos(x8, y, z8));
		  pos.add(new BlockPos(x9, y, z9));
		  pos.add(new BlockPos(x10, y, z10));
		  pos.add(new BlockPos(x11, y, z11));
		  pos.add(new BlockPos(x12, y, z12));
		  pos.add(new BlockPos(x13, y, z13));
		  pos.add(new BlockPos(x14, y, z14));
		  pos.add(new BlockPos(x15, y, z15));
		  pos.add(new BlockPos(x16, y, z16));
		  pos.add(new BlockPos(x17, y, z17));
		  pos.add(new BlockPos(x18, y, z18));
		  pos.add(new BlockPos(x19, y, z19));
		  pos.add(new BlockPos(x20, y, z20));
		  pos.add(new BlockPos(x21, y, z21));
		  pos.add(new BlockPos(x22, y, z22));
		  pos.add(new BlockPos(x23, y, z23));
		  pos.add(new BlockPos(x24, y, z24));
		  pos.add(new BlockPos(x25, y, z25));
		  pos.add(new BlockPos(x26, y, z26));
		  
		  for(int j = 0; j < pos.size(); j++) {
			  IBlockState iBlock = worldIn.getBlockState(pos.get(j));
			  Block block = iBlock.getBlock();
			  
			  BlockPos nePo = new BlockPos(pos.get(j).getX(), pos.get(j).getY() + 1, pos.get(j).getZ());
			  BlockPos nePo2 = new BlockPos(pos.get(j).getX(), pos.get(j).getY() + 2, pos.get(j).getZ());
			  
			  if(block == Blocks.AIR.getDefaultState()) {
				  positions.add(pos.get(j));
			  	} else if(worldIn.getBlockState(nePo).getBlock() == Blocks.AIR) {
			  		positions.add(nePo);
				} else if(worldIn.getBlockState(nePo2).getBlock() == Blocks.AIR) {
			  		positions.add(nePo2);
			}
		  }



		  for(int i = 0; i < positions.size(); i++) {
			  
			  worldIn.setBlockState(positions.get(i), Blocks.FIRE.getDefaultState());
			  
		  }
		  
		  

		  
	}
	
	public static void putBridge(String type, World world, EntityPlayer player, double[] start, double[] end) {
		
		double startX = start[0]; 	double startY = start[1]; 	double startZ = start[2];
		double endX = end[0]; 		double endY = end[1];  		double endZ = end[2];
		if(type == "x") {
			
			double distance = startZ - endZ;
			
			if(distance > 0) {
				for(double i = 0.0D; i < distance; i++) {
					world.setBlockState(new BlockPos(startX, startY, startZ - i), Blocks.COBBLESTONE.getDefaultState());
				}
			}
			if(distance < 0) {
				for(double i = 0.0D; i > distance; i--) {
					world.setBlockState(new BlockPos(startX, startY, startZ - i), Blocks.COBBLESTONE.getDefaultState());
					
				}
			}
			
		}
		
		if(type == "z") {
			
			double distance = startX - endX;
			
			if(distance > 0) {
				System.out.println("> 0");
				for(double i = 0.0D; i < distance; i++) {
					System.out.println("place1" + (startX - i) + "y:");
					world.setBlockState(new BlockPos(startX - i, startY, startZ), Blocks.COBBLESTONE.getDefaultState());
					
				}
			}
			if(distance < 0) {
				System.out.println("< 0");
				for(double i = 0.0D; i > distance; i--) {
					System.out.println("place2");
					world.setBlockState(new BlockPos(startX - i, startY, startZ), Blocks.COBBLESTONE.getDefaultState());
					
				}
			}
			
		}
		
	}


}














