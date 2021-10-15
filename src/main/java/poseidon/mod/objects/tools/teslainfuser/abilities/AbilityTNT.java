package poseidon.mod.objects.tools.teslainfuser.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class AbilityTNT implements TeslaProperties {
	
	private static EntityLivingBase igniter;
	
	public static void execute(EntityPlayer playerIn, World worldIn, ItemStack held) {
		
		if(!worldIn.isRemote) {
			RayTraceResult pos = playerIn.rayTrace(100,0);
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
	
}
