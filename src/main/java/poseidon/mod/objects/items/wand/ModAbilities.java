package poseidon.mod.objects.items.wand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.entities.CustomFireball;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class ModAbilities implements TeslaProperties {
	
	private static EntityLivingBase igniter;
	
	public static void wind(int level, EntityPlayer player, World worldIn) {
		
		//Booster
		if(player.isElytraFlying()) {
			
			double camX = player.getLookVec().x;
		    double camY = player.getLookVec().y;
		    double camZ = player.getLookVec().z;
			
		    double velocityAddedX = camX * 2;
		    double velocityAddedY = camY * 2;
		    double velocityAddedZ = camZ * 2;
		     
		    double currentVelocityX = player.motionX + velocityAddedX;
		    double currentVelocityY = player.motionY + velocityAddedY;
		    double currentVelocityZ = player.motionZ + velocityAddedZ;

		    player.setVelocity(currentVelocityX, currentVelocityY, currentVelocityZ);
		    
		}
		
	}
	public static void wind2(int level, EntityPlayer player, World worldIn) {

		if(!player.isElytraFlying()) {
		    float yaw = player.rotationYaw;
		    float pitch = player.rotationPitch;
		    float f = 9.0F;
		    double motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
		    double motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
		    double motionY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
		    player.setVelocity(motionX, motionY, motionZ);
		   	
		}
		
		
	}
	
	
	public static void earth2(EntityPlayer player, World worldIn) {
		
		BlockPos pos = player.getPosition();

			int tm = 100000;
			List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));

			List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			
			List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			
			List<Entity> general = new ArrayList<Entity>();
			general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
			
		    double d0 = (player.getEntityBoundingBox().minX + player.getEntityBoundingBox().maxX) / 2.0D;
		    double d1 = (player.getEntityBoundingBox().minZ + player.getEntityBoundingBox().maxZ) / 2.0D;

		    
	        for (Entity entity : general)
	        {
	            if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer && entity instanceof EntityPlayerMP && entity instanceof EntityPlayerSP))
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4 * 10.0D, 0.30000000298023224D, d3 / d4 * 10.0D);
	                

	            }
	        }
		   
	}
	
	public static void freezeEntities(int level, EntityPlayer player, World worldIn) {
		
		BlockPos pos = player.getPosition();

			int tm = 100000;
			List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));

			List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			
			List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			
			List<Entity> general = new ArrayList<Entity>();
			general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
			
		    double d0 = (player.getEntityBoundingBox().minX + player.getEntityBoundingBox().maxX) / 2.0D;
		    double d1 = (player.getEntityBoundingBox().minZ + player.getEntityBoundingBox().maxZ) / 2.0D;

		    
	        for (Entity entity : general)
	        {
	            if (entity instanceof Entity && !(entity instanceof EntityPlayer && entity instanceof EntityPlayerMP && entity instanceof EntityPlayerSP))
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                
	                if(entity instanceof EntityMob && !(entity instanceof EntityArrow)) {
	                	EntityLivingBase z = (EntityLivingBase) entity;
	                	z.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100, 100));
	                	z.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 100));
	                	entity.setVelocity(-entity.motionX, entity.motionY + 0.092D, -entity.motionZ);
	                }

	                if(entity instanceof EntityLivingBase && !worldIn.isRemote) ParticleUtil.velocity((WorldServer) worldIn, player);
	                
	            }
	        }
		   
	}
	
	public static void heal(int level, EntityPlayer player, World worldIn) {
		
		BlockPos pos = player.getPosition();

			int tm = 100000;
			List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));

			List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			
			List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			
			List<Entity> general = new ArrayList<Entity>();
			general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
			
		    double d0 = (player.getEntityBoundingBox().minX + player.getEntityBoundingBox().maxX) / 2.0D;
		    double d1 = (player.getEntityBoundingBox().minZ + player.getEntityBoundingBox().maxZ) / 2.0D;

		    
	        for (Entity entity : general)
	        {
	            if (entity instanceof Entity && !(entity instanceof EntityPlayer && entity instanceof EntityPlayerMP && entity instanceof EntityPlayerSP))
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                
	                if(entity instanceof EntityPlayerMP || entity instanceof EntityPlayerSP || entity instanceof EntityPlayer) {
	                	EntityLivingBase z = (EntityLivingBase) entity;
	                	z.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
	                }
	                
	            }
	        }
		   
	}
	
	public static void selfDestruction(int level, EntityPlayer player, World worldIn) {
		double t = 0;
		World world = worldIn;
		
		BlockPos pos = player.getPosition();

			int tm = 100000;
			List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));

			List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			
			List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			
			List<Entity> general = new ArrayList<Entity>();
			general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
			
		    double d0 = (player.getEntityBoundingBox().minX + player.getEntityBoundingBox().maxX) / 2.0D;
		    double d1 = (player.getEntityBoundingBox().minZ + player.getEntityBoundingBox().maxZ) / 2.0D;

		    
	        for (Entity entity : general)
	        {

	            if (entity instanceof Entity)
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                worldIn.createExplosion(null, entity.posX, entity.posY, entity.posZ, 0.01F, true);
	                entity.setDead();

	            }
	        }
		   
	}
	
	
	public static void attackAll(int level, EntityPlayer player, World worldIn) {
		double t = 0;
		World world = worldIn;
		
		BlockPos pos = player.getPosition();

			int tm = 100000;
			List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));

			List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			
			List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			
			List<Entity> general = new ArrayList<Entity>();
			general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
			
		    double d0 = (player.getEntityBoundingBox().minX + player.getEntityBoundingBox().maxX) / 2.0D;
		    double d1 = (player.getEntityBoundingBox().minZ + player.getEntityBoundingBox().maxZ) / 2.0D;

		    
	        for (Entity entity : general)
	        {
	            if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer && entity instanceof EntityPlayerMP && entity instanceof EntityPlayerSP))
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                if(level == 1) {
	                	entity.addVelocity(d2 / d4 * 5.0D, 0.10000000298023224D, d3 / d4 * 5.0D);
	                }
	                if(level == 5 && entity instanceof EntityMob) {
	                	player.attackTargetEntityWithCurrentItem(entity);
	                
	                }

	            }
	        }
	}
	
	public static void e(int level, EntityPlayer player, World worldIn) {
		double t = 0;
		World world = worldIn;
		
		BlockPos pos = player.getPosition();

		
		
			int tm = 100000;
			List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));

			List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + PUSHREACH, player.posY+ 2.0D, player.posZ + PUSHREACH));
			List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - PUSHREACH, player.posY+ 2.0D, player.posZ + PUSHREACH));
			List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + PUSHREACH, player.posY+ 2.0D, player.posZ - PUSHREACH));
			List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - PUSHREACH, player.posY+ 2.0D, player.posZ - PUSHREACH));
			
			List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + PUSHREACH, player.posY- 2.0D, player.posZ + PUSHREACH));
			List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - PUSHREACH, player.posY- 2.0D, player.posZ + PUSHREACH));
			List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + PUSHREACH, player.posY- 2.0D, player.posZ - PUSHREACH));
			List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - PUSHREACH, player.posY- 2.0D, player.posZ - PUSHREACH));
			
			List<Entity> general = new ArrayList<Entity>();
			general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
			
		    double d0 = (player.getEntityBoundingBox().minX + player.getEntityBoundingBox().maxX) / 2.0D;
		    double d1 = (player.getEntityBoundingBox().minZ + player.getEntityBoundingBox().maxZ) / 2.0D;

		    
	        for (Entity entity : general)
	        {
	            if (entity instanceof Entity && !(entity instanceof EntityPlayer && entity instanceof EntityPlayerMP && entity instanceof EntityPlayerSP))
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4, 0.08000000298023224D, d3 / d4);


	            }
	        }
	}
	
	public static void stopArrow(int level, EntityPlayer player, World worldIn) {
		double t = 0;
		World world = worldIn;
		
		BlockPos pos = player.getPosition();

			double reach = STOPARROWREACH + 1.0D;
		
			int tm = 100000;
			List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));

			List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + reach, player.posY+ 2.0D, player.posZ + reach));
			List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - reach, player.posY+ 2.0D, player.posZ + reach));
			List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + reach, player.posY+ 2.0D, player.posZ - reach));
			List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - reach, player.posY+ 2.0D, player.posZ - reach));
			
			List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + reach, player.posY- 2.0D, player.posZ + reach));
			List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - reach, player.posY- 2.0D, player.posZ + reach));
			List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + reach, player.posY- 2.0D, player.posZ - reach));
			List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - reach, player.posY- 2.0D, player.posZ - reach));
			
			List<Entity> general = new ArrayList<Entity>();
			general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
			
		    double d0 = (player.getEntityBoundingBox().minX + player.getEntityBoundingBox().maxX) / 2.0D;
		    double d1 = (player.getEntityBoundingBox().minZ + player.getEntityBoundingBox().maxZ) / 2.0D;

		    
	        for (Entity entity : general)
	        {
	            if (entity instanceof Entity && !(entity instanceof EntityPlayer && entity instanceof EntityPlayerMP && entity instanceof EntityPlayerSP))
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	               // entity.addVelocity(d2 / d4 / 6, 0.08000000298023224D, d3 / d4 / 6);
	                if(entity instanceof EntityArrow) {
	                	EntityArrow a = (EntityArrow) entity;
	                	entity.setVelocity(0,0,0);
	                }
	               

	            }
	        }
	}
	
	public static void sky(int level, EntityPlayer player, World worldIn) {
		if(!worldIn.isRemote) {
			
			RayTraceResult pos = player.rayTrace(100,20);
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();
			Entity bolt = new EntityLightningBolt(worldIn, x, y, z, false);
			worldIn.addWeatherEffect(bolt);
			player.getCooldownTracker().setCooldown(player.getActiveItemStack().getItem(), 20);

		}
	}
	public static void sky2(int level, EntityPlayer player, World worldIn) {
		if(!worldIn.isRemote) {
			List<Entity> playerList = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> playerList2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> playerList3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			List<Entity> playerList4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			
			List<Entity> playerList5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> playerList6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> playerList7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			List<Entity> playerList8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			
			
			
			for(int i = 0; i < playerList.size(); i++) {
				Entity entity = playerList.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);

			}
			for(int i = 0; i < playerList2.size(); i++) {
				Entity entity = playerList2.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);

			}
			for(int i = 0; i < playerList3.size(); i++) {
				Entity entity = playerList3.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);

			}
			for(int i = 0; i < playerList4.size(); i++) {
				Entity entity = playerList4.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);

			}
			
			for(int i = 0; i < playerList5.size(); i++) {
				Entity entity = playerList5.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);

			}
			for(int i = 0; i < playerList6.size(); i++) {
				Entity entity = playerList6.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);

			}
			for(int i = 0; i < playerList7.size(); i++) {
				Entity entity = playerList7.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);
			}
			for(int i = 0; i < playerList8.size(); i++) {
				Entity entity = playerList8.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);

			}
			

		
		}
		
		
	}
	
	public static void fire(int level, EntityPlayer player, World worldIn) {
		//WIP
	}
	public static void fire2(int level, EntityPlayer playerIn, World worldIn) {
		if(!worldIn.isRemote) {
			Vec3d vector = playerIn.getLook(0.5F);
			CustomFireball entity = new CustomFireball(worldIn, playerIn, vector.x * 25D, vector.y * 25D, vector.z * 25D, level);
		    entity.posY += playerIn.getEyeHeight();
		    entity.posX = playerIn.posX;
		    entity.posZ = playerIn.posZ;    
		    worldIn.spawnEntity(entity);
		}
	}

	public static void inferno(int level, EntityPlayer player, World world) {
		if(!world.isRemote) {
			
			RayTraceResult post = player.rayTrace(100,20);
			double x1 = post.getBlockPos().getX();
			double y1 = post.getBlockPos().getY();
			double z1 = post.getBlockPos().getZ();
			//
			Vec3d dir = player.getLook(1.0F).normalize();
			BlockPos pos = player.getPosition();
			
			double t = 0;
			double tm = 1000;
			
			
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
			BlockHelper.infernoHelper(x1, y1, z1, world);
		}
	}
	public static void inferno2(int level, EntityPlayer player, World worldIn) {
		if(!worldIn.isRemote) {
			List<Entity> playerList = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> playerList2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> playerList3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			List<Entity> playerList4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			
			List<Entity> playerList5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> playerList6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> playerList7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			List<Entity> playerList8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			Random rand = new Random();
			for(int i = 0; i < playerList.size(); i++) {
				Entity entity = playerList.get(i);
				BlockPos pos = entity.getPosition();
				entity.setFire(100);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
			}
			for(int i = 0; i < playerList2.size(); i++) {
				Entity entity = playerList2.get(i);
				BlockPos pos = entity.getPosition();
				entity.setFire(100);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
			}
			for(int i = 0; i < playerList3.size(); i++) {
				Entity entity = playerList3.get(i);
				BlockPos pos = entity.getPosition();
				entity.setFire(100);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
			}
			for(int i = 0; i < playerList4.size(); i++) {
				Entity entity = playerList4.get(i);
				BlockPos pos = entity.getPosition();
				entity.setFire(100);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
			}
			
			for(int i = 0; i < playerList5.size(); i++) {
				Entity entity = playerList5.get(i);
				BlockPos pos = entity.getPosition();
				entity.setFire(100);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
			}
			for(int i = 0; i < playerList6.size(); i++) {
				Entity entity = playerList6.get(i);
				BlockPos pos = entity.getPosition();
				entity.setFire(100);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
			}
			for(int i = 0; i < playerList7.size(); i++) {
				Entity entity = playerList7.get(i);
				BlockPos pos = entity.getPosition();
				entity.setFire(100);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
			}
			for(int i = 0; i < playerList8.size(); i++) {
				Entity entity = playerList8.get(i);
				BlockPos pos = entity.getPosition();
				entity.setFire(100);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
			}
		
		}
	}
	
	public static void ender(int level, EntityPlayer playerIn, World worldIn) {
		RayTraceResult pos = playerIn.rayTrace(100,20);
		Random rand = new Random();
		double x = pos.getBlockPos().getX();
		double y = pos.getBlockPos().getY() + 1;
		double z = pos.getBlockPos().getZ();
		playerIn.setPosition(x, y, z);
		playerIn.serverPosX = (long) x;
		playerIn.serverPosY = (long) y;
		playerIn.serverPosZ = (long) z;
	
		worldIn.playSound((EntityPlayer)null, x, y, z, SoundsHandler.TELEPORT, SoundCategory.AMBIENT, 1.0F, 1.0F);
	
		for (int i = 0; i < 1000; ++i)
	    {
			double d0 = rand.nextGaussian() * 0.02D;
	    	double d1 = rand.nextGaussian() * 0.02D;
	    	double d2 = rand.nextGaussian() * 0.02D;
	    	double d3 = 10.0D;
	    	
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
	    }
	}
	public static void ender2(int level, EntityPlayer player, World worldIn, double x, double y, double z) {
		player.serverPosX = (long) x;
		player.serverPosY = (long) y;
		player.serverPosZ = (long) z;
	}
		
	public static void nether(int level, EntityPlayer player, World world) {
		
	}
	public static void nether2(int level, EntityPlayer player, World world) {
		
	}
	
	public static void tnt(int level, EntityPlayer playerIn, World worldIn) {
		if(!worldIn.isRemote) {
			RayTraceResult pos = playerIn.rayTrace(100,20);
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();
			EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldIn, x, y, z, igniter);
			EntityTNTPrimed entitytntprimed1 = new EntityTNTPrimed(worldIn, x + 1, y, z + 1, igniter);
			EntityTNTPrimed entitytntprimed2 = new EntityTNTPrimed(worldIn, x + 1, y, z, igniter);
			EntityTNTPrimed entitytntprimed3 = new EntityTNTPrimed(worldIn, x + 1, y, z - 1, igniter);
			EntityTNTPrimed entitytntprimed4 = new EntityTNTPrimed(worldIn, x, y, z + 1, igniter);
			EntityTNTPrimed entitytntprimed5 = new EntityTNTPrimed(worldIn, x, y, z - 1, igniter);
			EntityTNTPrimed entitytntprimed6 = new EntityTNTPrimed(worldIn, x - 1, y, z - 1, igniter);
			EntityTNTPrimed entitytntprimed7 = new EntityTNTPrimed(worldIn, x, y, z - 1, igniter);
			EntityTNTPrimed entitytntprimed8 = new EntityTNTPrimed(worldIn, x + 1, y, z - 1, igniter);
			
	        worldIn.spawnEntity(entitytntprimed1);
	        worldIn.spawnEntity(entitytntprimed);
	        worldIn.spawnEntity(entitytntprimed2);
	        worldIn.spawnEntity(entitytntprimed3);
	        worldIn.spawnEntity(entitytntprimed4);
	        worldIn.spawnEntity(entitytntprimed5);	        
	        worldIn.spawnEntity(entitytntprimed6);
	        worldIn.spawnEntity(entitytntprimed7);
	        worldIn.spawnEntity(entitytntprimed8);
		}
	}
	
	public static void explosion(float level, EntityPlayer playerIn, World worldIn) {
		if(!worldIn.isRemote) {
			
			RayTraceResult pos = playerIn.rayTrace(100,20);
	        
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();
			
			worldIn.createExplosion(null, x, y, z, level, true);
		}
	}
	
	public static void teleport(int level, EntityPlayer playerIn, World worldIn) {
		RayTraceResult pos = playerIn.rayTrace(100,20);
		Random rand = new Random();
		double x = pos.getBlockPos().getX();
		double y = pos.getBlockPos().getY() + 1;
		double z = pos.getBlockPos().getZ();
		playerIn.setPosition(x, y, z);
		playerIn.serverPosX = (long) x;
		playerIn.serverPosY = (long) y;
		playerIn.serverPosZ = (long) z;
	
		worldIn.playSound((EntityPlayer)null, x, y, z, SoundsHandler.TELEPORT, SoundCategory.AMBIENT, 1.0F, 1.0F);
	
		for (int i = 0; i < 1000; ++i)
	    {
			double d0 = rand.nextGaussian() * 0.02D;
	    	double d1 = rand.nextGaussian() * 0.02D;
	    	double d2 = rand.nextGaussian() * 0.02D;
	    	double d3 = 10.0D;
	    	
	    	worldIn.spawnParticle(EnumParticleTypes.PORTAL, x + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D, z + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
			worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x + (double)(rand.nextFloat() * playerIn.width * 2.0F) - (double)playerIn.width - d0 * 10.0D, y + (double)(rand.nextFloat() * (playerIn.height - 1.2D)) - d1 * 10.0D, z + (double)rand.nextFloat() * playerIn.width * 2.0F - (double)playerIn.width - d2 * 10.0D, d0, d1, d2);
	    }
	}
	

	
}
