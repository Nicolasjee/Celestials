package poseidon.mod.client.commands.util;

import java.util.List;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class PushEntities  extends CommandEffect {
	
	public PushEntities() {
		
	}
	
	public static void push(EntityPlayer player) {
		
		World worldIn = player.world;
		
		
		List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
		List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
		List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
		List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
		
		List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
		List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
		List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
		List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
		
		
		
		 double d0 = (player.getEntityBoundingBox().minX + player.getEntityBoundingBox().maxX) / 2.0D;
	        double d1 = (player.getEntityBoundingBox().minZ + player.getEntityBoundingBox().maxZ) / 2.0D;

	        for (Entity entity : list1)
	        {
	            if (entity instanceof EntityLivingBase)
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4 * 10.0D, 0.20000000298023224D, d3 / d4 * 10.0D);

	            }
	        }
	        
	        for (Entity entity : list2)
	        {
	            if (entity instanceof EntityLivingBase)
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4 * 10.0D, 0.20000000298023224D, d3 / d4 * 10.0D);

	            }
	        }
	        
	        for (Entity entity : list3)
	        {
	            if (entity instanceof EntityLivingBase)
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4 * 10.0D, 0.20000000298023224D, d3 / d4 * 10.0D);

	            }
	        }
	        
	        for (Entity entity : list4)
	        {
	            if (entity instanceof EntityLivingBase)
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4 * 10.0D, 0.20000000298023224D, d3 / d4 * 10.0D);

	            }
	        }
	        
	        for (Entity entity : list5)
	        {
	            if (entity instanceof EntityLivingBase)
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4 * 10.0D, 0.20000000298023224D, d3 / d4 * 10.0D);

	            }
	        }
	        
	        for (Entity entity : list6)
	        {
	            if (entity instanceof EntityLivingBase)
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4 * 10.0D, 0.20000000298023224D, d3 / d4 * 10.0D);

	            }
	        }
	        
	        for (Entity entity : list7)
	        {
	            if (entity instanceof EntityLivingBase)
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4 * 10.0D, 0.20000000298023224D, d3 / d4 * 10.0D);

	            }
	        }
	        
	        for (Entity entity : list8)
	        {
	            if (entity instanceof EntityLivingBase)
	            {
	                double d2 = entity.posX - d0;
	                double d3 = entity.posZ - d1;
	                double d4 = d2 * d2 + d3 * d3 / 10;
	                entity.addVelocity(d2 / d4 * 10.0D, 0.20000000298023224D, d3 / d4 * 10.0D);

	            }
	        }
		
		
	}
}
