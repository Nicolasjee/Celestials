package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FireResistance extends CommandEffect {
	
	public FireResistance() {
		
	}
	
	public static void activate(EntityPlayer player, int num) {
		
		
		World world = player.world;
		
		player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1000000, 100));
		
		
	}
	
}
