package poseidon.mod.objects.items.general.test;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IHasModel;

public class ParticleTest extends Item implements IHasModel {
	
	double x;
	double x1;
	double x2;
	double x3;
	double x4;
	double x5;
	double x6;
	double x7;
	double x8;
	double x9;
	double x10;
	double x11;
	double x12;
	double x13;
	double x14;
	double x15;
	double x16;
	
	double z;
	double z1;
	double z2;
	double z3;
	double z4;
	double z5;
	double z6;
	double z7;
	double z8;
	double z9;
	double z10;
	double z11;
	double z12;
	double z13;
	double z14;
	double z15;
	double z16;
	
	public ParticleTest(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		int code = 1;
		Random rand = new Random();
		EntityPlayer player = (EntityPlayer) entity;
		
		if(code == 1) {
		
			if(!worldIn.isRemote) {

				float width = 0.5F;
				float height = 0.5F;
							
				double d0 = rand.nextGaussian() * 0.02D;
				double d1 = rand.nextGaussian() * 0.02D;
				double d2 = rand.nextGaussian() * 0.02D;
							
				if(rand.nextFloat() > 0.7F){
				 WorldServer worldserver = (WorldServer) worldIn;
				    	worldserver.spawnParticle(EnumParticleTypes.REDSTONE, false,
				player.posX +  (double)(rand.nextFloat() * width * 2.0F) - (double)width,
				player.posY+ 0.5D + (double)(rand.nextFloat() * height),
				player.posZ+ + (double)(rand.nextFloat() * width * 2.0F) - (double)width,
				1,
				1.0D, 1.0D, 1.0D,
				1.0D, new int[0]); 
							}
			
			
			} else {
				
			
				if(!worldIn.isRemote) {

					EntityPlayer player2 = (EntityPlayer) entity;
					BlockPos pos = entity.getPosition();
					
					double x = pos.getX();
					double y = pos.getY();
					double z = pos.getZ();
					
					getCoords(x, y, z);
					
					spawnParticles(worldIn, entity);
				}
			}
		}
	}
	
	private void spawnParticles(World worldIn, Entity entity) {
		Random rand = new Random();
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, x + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, x1 + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY, z1 + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, x2 + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY, z2 + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, x3 + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY, z3 + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, x4 + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY, z4 + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, x5 + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY, z5 + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, x6 + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY, z6 + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, x7 + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY, z7 + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, x8 + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY, z8 + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
	
	}
	
	private void getCoords(double x, double y, double z) {
		x1 = x - 1;
		z1 = z - 1;
		
		x9 = x - 0.5;
		z9 = z - 1;
		
		x2 = x - 1;
		z2 = z;
		
		x10 = x - 0.5;
		z10 = z;
		
		x3 = x - 1;
		z3 = z + 1;
		
		x11 = x - 0.5;
		z11 = z + 1;
		
		x4 = x;
		z4 = z - 1;
		
		x12 = x;
		z12 = z - 0.5;
		
		x5 = x;
		z5 = z +1;
		
		x13 = x;
		z13 = z + 0.5;
		
		x6 = x + 1;
		z6 = z + 1;
		
		x14 = x + 0.5;
		z14 = z + 1;
		
		x7 = x + 1;
		z7 = z;
		
		x15 = x + 0.5;
		z15 = z;
		
		x8 = x + 1;
		z8 = z - 1;
		
		x16 = x + 0.5;
		z16 = z - 1;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this,  0, "inventory");
	}
}
