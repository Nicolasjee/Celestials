package poseidon.mod.networking;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class MessageHelper {

	public static void stop(Entity entity) {
		if(entity instanceof EntityLivingBase) {
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1000, 100));
		}
		
		if(entity instanceof EntityArrow) {
        	EntityArrow a = (EntityArrow) entity;
        	a.setVelocity(0,0,0);
		}
	}
	
	
	
}
