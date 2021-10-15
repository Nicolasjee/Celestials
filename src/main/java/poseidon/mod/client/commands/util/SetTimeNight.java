package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SetTimeNight extends CommandEffect {
	
	public SetTimeNight() {
		
	}
	
	public static void setNight(EntityPlayer player, int num) {
		
		
		World world = player.world;
		
		world.setWorldTime(18000);
		
		
	}
}
