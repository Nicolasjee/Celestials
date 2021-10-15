package poseidon.mod.objects.tools.teslainfuser.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class AbilityLightning implements TeslaProperties {
	
	public static void execute(EntityPlayer playerIn, World worldIn, ItemStack held) {
		
		RayTraceResult pos = playerIn.rayTrace(100,0);
		double x = pos.getBlockPos().getX();
		double y = pos.getBlockPos().getY();
		double z = pos.getBlockPos().getZ();
		Entity bolt = new EntityLightningBolt(worldIn, x, y, z, false);
		worldIn.addWeatherEffect(bolt);
		worldIn.createExplosion(playerIn, x, y, z, 30.0F, true);
		playerIn.getCooldownTracker().setCooldown(held.getItem(), 20);	
		
		
	}
	
}
