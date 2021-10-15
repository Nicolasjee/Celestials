package poseidon.mod.objects.items.general.test;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import poseidon.mod.objects.entities.Spit;
import poseidon.mod.objects.items.general.ItemBase;
import poseidon.mod.objects.items.wand.ModAbilities;


public class ParticleItem extends ItemBase {

	double t;
	
	public RayTraceResult objectMouseOver;
	Random rand = new Random();
	
	public ParticleItem(String name, int size, boolean e) {
		super(name, size, e);
	}
	
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
//			if(!worldIn.isRemote) {
//			
//			RayTraceResult pos = player.rayTrace(100,20);
//			double x = pos.getBlockPos().getX();
//			double y = pos.getBlockPos().getY();
//			double z = pos.getBlockPos().getZ();
//			WandAbility.inferno2(0, player, worldIn);
//
//		}
		
		ItemStack getStack = player.getHeldItem(handIn);
		int slot = player.inventory.getSlotFor(getStack);
		
		if(slot == 0) {particle(player, worldIn);}
		if(slot == 1) {particle2(player, worldIn);}
		if(slot == 2) {particle3(player, worldIn);}
		if(slot == 3) {particle4(player, worldIn);}
		if(slot == 4) {particle5(player, worldIn);}
		if(slot == 5) {particle6(player, worldIn);}
		if(slot == 6) {particle7(player, worldIn);}
		
    	return new ActionResult(EnumActionResult.SUCCESS, new ItemStack(this));
    }
    /*
    public void onClick(World worldIn, EntityPlayer player, EnumHand hand) {
    	if(objectMouseOver.entityHit != null) {
    		Entity entity = objectMouseOver.entityHit;
    		entity.onKillCommand();
    	}
    }
    */


	private void particle4(EntityPlayer playerIn, World worldIn) {
		
        Spit entitysnowball = new Spit(worldIn, playerIn);
        entitysnowball.shoot(playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.5F, 0.1F);
        worldIn.spawnEntity(entitysnowball);
        
        Vec3d dir = playerIn.getLook(1.0F).normalize();
		BlockPos pos = playerIn.getPosition();
		
		double t = 0;
		double tm = 1000;
		
		
		while(tm > 2) {
			
		
			
			t += 0.5;
			double x = dir.x * t + pos.getX();
			double y = dir.y * t  + 1.5D + pos.getY();
			double z = dir.z * t + pos.getZ();
			pos.add(x,y,z);
			worldIn.spawnParticle(EnumParticleTypes.LAVA, x, y, z, 0, 0, 0);
			pos.subtract(new Vec3i(x,y,z));
			tm--;
		}
		
	}
	
	private void particle7(EntityPlayer player, World world) {
		double t = 0;
		double tm = 0;
		while(tm < 4000) {
		BlockPos pos = player.getPosition();
		t += Math.PI /8;
		for(double phi = 0; phi <= 2*Math.PI; phi += Math.PI/2) {
			double x = 0.3*(4*Math.PI - t) * Math.cos(t + phi) + player.posX;
			double y = 0.2*t + player.posY - 0.5;
			double z = 0.3 * (4*Math.PI - t) * Math.sin(t + phi) + player.posZ;
			pos.add(x,y,z);
			world.spawnParticle(EnumParticleTypes.END_ROD, x, y, z, 0, 0, 0);
			world.spawnParticle(EnumParticleTypes.END_ROD, x, y + 1.0, z, 0, 0, 0);
			world.spawnParticle(EnumParticleTypes.END_ROD, x, y + 2.0, z, 0, 0, 0);
			world.spawnParticle(EnumParticleTypes.END_ROD, x, y + 3.0, z, 0, 0, 0);
			pos.subtract(new Vec3i(x,y,z));
		}
		tm++;
		}
		
	}
	
	//helix - stasis?
	private void particle6(EntityPlayer player, World world) {
		
		double phi = 0;
		double tm = 1;
		double t2 = 0;
		double t3 = 1;
		while (tm < 100000) {
			
			if(t3 * 500 == tm) {
			
				phi += Math.PI/8;
				double x, y, z;
				BlockPos pos = player.getPosition();
				
				for(double t = 0; t <= 2*Math.PI; t += Math.PI / 16) {
					for(double i = 0; i <= 1; i += 1) {
						x = 0.3 * (2*Math.PI - t)*0.5*Math.cos(t+phi+i*Math.PI) + player.posX;
						y = 0.5 * t+ player.posY;
						z = 0.3 * (2*Math.PI - t) * 0.5 * Math.sin(t+phi+i*Math.PI) + player.posZ;
						pos.add(x,y,z);
						world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x, y, z, 0, 0, 0);
						pos.subtract(new Vec3i(x,y,z));
					}
				}
				t2 = t2 + 0.1 * Math.PI;
				for(double theta = 0; theta <= 2*Math.PI; theta = theta + Math.PI/8) {
					double x2 = t2 * Math.cos(theta) + player.posX;
					double y2 = Math.exp(-0.1 * t2) * Math.sin(t2) + player.posY;
					double z2 = t2 * Math.sin(theta) + player.posZ;
					world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x2, y2, z2, 0, 0, 0);
					
					theta = theta + Math.PI / 32;
					x2= t2*Math.cos(theta) + player.posX;
					y2 = 2*Math.exp(-0.1*t2) * Math.sin(t2) + 1.5 + player.posY;
					z2 = t2*Math.sin(theta) + player.posZ;
					world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x2, y2, z2, 0, 0, 0);

				}
				t3++;
			}
			
			tm++;
		}
	
		while(tm < 100000) {
			t2 = t2 + 0.1 * Math.PI;
			for(double theta = 0; theta <= 2*Math.PI; theta = theta + Math.PI/8) {
				double x = t * Math.cos(theta) + player.posX;
				double y = Math.exp(-0.1 * t) * Math.sin(t) + player.posY;
				double z = t * Math.sin(theta) + player.posZ;
				world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x, y, z, 0, 0, 0);
				
				theta = theta + Math.PI / 32;
				x= t*Math.cos(theta) + player.posX;
				y = 2*Math.exp(-0.1*t) * Math.sin(t) + 1.5 + player.posY;
				z = t*Math.sin(theta) + player.posZ;
				world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x, y, z, 0, 0, 0);

			}
		}
		
		
	}
	
	//sphere but it isn't a sphere
	private void particle5(EntityPlayer player, World world) {
		
		BlockPos pos = player.getPosition();
		double tm = 0;
		double phi =0;
		
		while(tm < 100) {
			
			phi += (double) (Math.PI / 10);
			
			for(double theta = 0; theta <= 4*Math.PI; theta += Math.PI/20) {
			
				double r = 1.5;
				double x = r * Math.cos(theta) * Math.sin(phi)+ player.posX;
				double y = r * Math.cos(phi) + 1.5 + player.posY;
				double z = r * Math.cos(theta) * Math.sin(phi) + player.posZ;
				
				pos.add(x,y,z);
				world.spawnParticle(EnumParticleTypes.TOTEM, x, y, z, 0, 0, 0);
				pos.subtract(new Vec3i(x,y,z));
				
			}
			tm++;
//			pos.add(x,y,z);
//			
//			pos.subtract(new Vec3i(x,y,z));
//			
		}
		
		
	}
	
	//trail
	private void particle3(EntityPlayer player, World world) {
		
		Vec3d dir = player.getLook(1.0F).normalize();
		BlockPos pos = player.getPosition();
		
		double t = 0;
		double tm = 6000;
		double x = 0;
		double y = 0;
		double z = 0;
		
		
		while(tm > 1) {
			
		
			
			t += 0.5;
			x = dir.x * t + pos.getX();
			y = dir.y * t  + 1.5D + pos.getY();
			z = dir.z * t + pos.getZ();
			pos.add(x,y,z);
			world.spawnParticle(EnumParticleTypes.LAVA, x, y, z, 0, 0, 0);
			pos.subtract(new Vec3i(x,y,z));
		
			
			tm--;
			
		}
		particle3b(player, world, new BlockPos(x, y, z), dir);
		
		
   }
		
	private void particle3b(EntityPlayer player, World world, BlockPos pos, Vec3d dir) {

		BlockPos pos2 = pos;
		
		double t = 0;
		double tm = 6000;
		
		
		while(tm > 1) {
			
		
			
			t += 0.5;
			double x = dir.x * t + pos.getX();
			double y = dir.y * t  + 1.5D + pos.getY();
			double z = dir.z * t + pos.getZ();
			pos.add(x,y,z);
			world.spawnParticle(EnumParticleTypes.LAVA, x, y, z, 0, 0, 0);
			pos.subtract(new Vec3i(x,y,z));
			
			tm--;
			
		}
	}



	//flame helix?
	private void particle2(EntityPlayer player, World world) {
		
		double x1 = player.posX;
		double y2 = player.posY;
		double z2 = player.posZ;
		BlockPos pos = new BlockPos(x1, y2, z2);
		double t = 0;
		double r= 1;
		double tm = 100;
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		
		while(tm > 1) {
			t = t + Math.PI / 16;
			x = r * Math.cos(t) + x1;
			y = t + y2 - 0.5D;
			z = r * Math.sin(t) + z2;
			pos.add(x, y, z);
			world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
			pos.subtract(new Vec3i(x, y, z));
			tm--;
		}

	}
    
	
	
	//radar?
	private void particle(EntityPlayer entity, World world) {
		
		double t = 0;
		BlockPos pos = entity.getPosition();

			int tm = 100000;
			while(tm > 1) {
				t = t + 0.1 * Math.PI;
				for(double theta = 0; theta <= 2*Math.PI; theta = theta + Math.PI/8) {
					double x = t * Math.cos(theta) + entity.posX;
					double y = Math.exp(-0.1 * t) * Math.sin(t) + entity.posY;
					double z = t * Math.sin(theta) + entity.posZ;;
					pos.add(x,y,z);
					world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x, y, z, 0, 0, 0);
					pos.subtract(new Vec3i(x,y,z));
					
					theta = theta + Math.PI / 32;
					x= t*Math.cos(theta) + entity.posX;
					y = 2*Math.exp(-0.1*t) * Math.sin(t) + 1.5 + entity.posY;
					z = t*Math.sin(theta) + entity.posZ;
					pos.add(x,y,z);
					world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x, y, z, 0, 0, 0);
					
					tm--;
				}
			}
		
	}
	
}
