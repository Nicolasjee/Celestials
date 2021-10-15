package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import poseidon.mod.objects.entities.CustomFireball;

public class SummonTNT  extends CommandEffect {
	
	public SummonTNT() {
		
	}
	
	public static void summon(EntityPlayer player) {
		
		World worldIn = player.world;
		
		if(!worldIn.isRemote) {
			
			RayTraceResult pos = player.rayTrace(100,20);
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();
			
			EntityLivingBase igniter = player;
			EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldIn, x, y, z, igniter );
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
