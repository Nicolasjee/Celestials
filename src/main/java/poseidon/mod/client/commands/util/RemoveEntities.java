package poseidon.mod.client.commands.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RemoveEntities extends CommandEffect {
	
	public RemoveEntities() {
		
	}
	
	public static void remove(EntityPlayer player) {
		
		BlockPos pos = player.getPosition();
		World worldIn = player.world;

		int tm = 100000;
		List<Entity> general = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.posX - 30.0D, player.posY + 10.0D, player.posZ - 30.0D, player.posX + 30.0D, player.posY - 10.0D, player.posZ + 30.0D));

	    double d0 = (player.getEntityBoundingBox().minX + player.getEntityBoundingBox().maxX) / 2.0D;
	    double d1 = (player.getEntityBoundingBox().minZ + player.getEntityBoundingBox().maxZ) / 2.0D;

	    
        for (Entity entity : general) {
            if (entity instanceof EntityItem && !(entity instanceof EntityPlayer && entity instanceof EntityPlayerMP && entity instanceof EntityPlayerSP)) {
               entity.onKillCommand();
            }
        }
        
        System.out.println("Killed " + general.size() + " entities.");
		
	}

}
