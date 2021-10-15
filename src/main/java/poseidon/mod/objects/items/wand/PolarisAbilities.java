package poseidon.mod.objects.items.wand;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.networking.MessageChoke;
import poseidon.mod.networking.MessageParticle;
import poseidon.mod.objects.entities.CustomFireball;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class PolarisAbilities {

	public static void choke(EntityPlayer playerIn, World world) {
		RayTraceResult mov = Utilities.getMouseOverExtended(360.0F);
    	
    	if(mov != null) {
    		if(mov.entityHit != null && mov.entityHit.hurtResistantTime == 0) {
    			if(mov.entityHit != playerIn) {

    				Main.network.sendToServer(new MessageChoke(mov.entityHit.getEntityId()));
    				if(!world.isRemote) ParticleUtil.trail((WorldServer) world, playerIn);
    			}
    		}
    	}
	}
	
	public static void fireball(EntityPlayer playerIn, World worldIn) {
		if(!worldIn.isRemote) {
			Vec3d vector = playerIn.getLook(0.5F);
			
	
			CustomFireball entity4 = new CustomFireball(worldIn, playerIn, vector.x * 25D, vector.y * 25D, vector.z * 25D, 20);
			entity4.posY += playerIn.getEyeHeight();
	        entity4.posX = playerIn.posX;
	        entity4.posZ = playerIn.posZ;
	        
	        worldIn.spawnEntity(entity4);
		}
	}
	
	public static void force(EntityPlayer player) {
		RayTraceResult mov = Utilities.getMouseOverExtended(360.0F);
      	
       	if(mov != null) {
       		if(mov.entityHit != null && mov.entityHit.hurtResistantTime == 0) {
       			if(mov.entityHit != player) {
       				Main.network.sendToServer(new MessageParticle(mov.entityHit.getEntityId()));
       			}
       		}
       	}
	}
	
	public static void ground(EntityPlayer player, World worldIn) {
		ModAbilities.earth2(player, worldIn);
	}
	
	public static void lightning(EntityPlayer playerIn, World worldIn) {
		RayTraceResult pos = playerIn.rayTrace(100,20);	
		double x = pos.getBlockPos().getX();
		double y = pos.getBlockPos().getY();
		double z = pos.getBlockPos().getZ();
		Entity bolt = new EntityLightningBolt(worldIn, x, y, z, false);
		worldIn.addWeatherEffect(bolt);
		worldIn.createExplosion(playerIn, x, y, z, 15.0F, true);
	}

	public static void snap(EntityPlayer player, World world) {
		ModAbilities.selfDestruction(0, player, world);
	}
	
	public static void teleport(EntityPlayer playerIn, World worldIn) {
		RayTraceResult pos = playerIn.rayTrace(100,0);
		Random rand = new Random();
		double x = pos.getBlockPos().getX();
		double y = pos.getBlockPos().getY() + 1;
		double z = pos.getBlockPos().getZ();
		playerIn.setPosition(x, y, z);
		playerIn.serverPosX = (long) x;
		playerIn.serverPosY = (long) y;
		playerIn.serverPosZ = (long) z;
	
		Main.proxy.playSoundBlock(new BlockPos(x,y,z), worldIn, SoundsHandler.TELEPORT, 1.0F, 1.0F);
		if(!worldIn.isRemote) ParticleUtil.teleportParticles((WorldServer) worldIn, new BlockPos(x,y,z), playerIn);
		
	}
	
	public static void tnt(EntityPlayer playerIn, World worldIn) {
		if(!worldIn.isRemote) {
			RayTraceResult pos = playerIn.rayTrace(100,0);
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();
			EntityLivingBase igniter = (EntityLivingBase) playerIn;
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
	
}
