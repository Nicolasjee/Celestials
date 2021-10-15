package poseidon.mod.client.commands.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.util.Utilities;

public class KillAll extends CommandEffect {
	
	public KillAll() {
		
	}
	
	public static void dupe(EntityPlayer player) {
		
		BlockPos pos = player.getPosition();
		World worldIn = player.world;

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
                entity.onKillCommand();
                

            }
        }
        
        System.out.println("Killed " + general.size() + " entities.");
		
	}
	
	public static void kill(BlockPos pos, World worldIn) {
		


		int tm = 100000;
		List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),pos.getX() + 20.0D, pos.getY()+ 20.0D, pos.getZ() + 20.0D));

		List<Entity> list1 = worldIn.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),pos.getX() + 20.0D, pos.getY()+ 20.0D, pos.getZ() + 20.0D));
		List<Entity> list2 = worldIn.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),pos.getX() - 20.0D, pos.getY()+ 20.0D, pos.getZ() + 20.0D));
		List<Entity> list3 = worldIn.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),pos.getX() + 20.0D, pos.getY()+ 20.0D, pos.getZ() - 20.0D));
		List<Entity> list4 = worldIn.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),pos.getX() - 20.0D, pos.getY()+ 20.0D, pos.getZ() - 20.0D));
		
		List<Entity> list5 = worldIn.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),pos.getX() + 20.0D, pos.getY()- 20.0D, pos.getZ() + 20.0D));
		List<Entity> list6 = worldIn.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),pos.getX() - 20.0D, pos.getY()- 20.0D, pos.getZ() + 20.0D));
		List<Entity> list7 = worldIn.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),pos.getX() + 20.0D, pos.getY()- 20.0D, pos.getZ() - 20.0D));
		List<Entity> list8 = worldIn.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),pos.getX() - 20.0D, pos.getY()- 20.0D, pos.getZ() - 20.0D));
		
		List<Entity> general = new ArrayList<Entity>();
		general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
		
	    
        for (Entity entity : general){
            if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer && entity instanceof EntityPlayerMP && entity instanceof EntityPlayerSP)){
                entity.onKillCommand();
             
            }
        }
        
        System.out.println("Killed " + general.size() + " entities.");
		
	}
}
