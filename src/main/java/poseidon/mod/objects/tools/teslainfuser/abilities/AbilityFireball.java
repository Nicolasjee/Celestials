package poseidon.mod.objects.tools.teslainfuser.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import poseidon.mod.objects.entities.CustomFireball;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class AbilityFireball implements TeslaProperties {
	
	public static void execute(EntityPlayer playerIn, World worldIn, ItemStack held) {
			
		if(!worldIn.isRemote) {
			Vec3d vector = playerIn.getLook(0.5F);
			
	
			CustomFireball entity4 = new CustomFireball(worldIn, playerIn, vector.x * 25D, vector.y * 25D, vector.z * 25D, 20);
			entity4.posY += playerIn.getEyeHeight();
	        entity4.posX = playerIn.posX;
	        entity4.posZ = playerIn.posZ;
	        
	        worldIn.spawnEntity(entity4);
	        
	        
		}
			
	}
	
}
