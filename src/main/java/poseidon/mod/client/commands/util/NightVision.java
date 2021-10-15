package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class NightVision extends CommandEffect {
	
	public NightVision() {
		
	}
	
	public static void activate(EntityPlayer player, int num) {
		
		
		World world = player.world;
		
		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1000000, 100));
		
		
	}
	
}
