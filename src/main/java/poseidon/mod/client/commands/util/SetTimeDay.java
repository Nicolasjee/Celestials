package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SetTimeDay extends CommandEffect {
	
	public SetTimeDay() {
		
	}
	
	public static void setDay(EntityPlayer player, int num) {
		
		
		World world = player.world;
		
		world.setWorldTime(0);
		
		
	}
}
