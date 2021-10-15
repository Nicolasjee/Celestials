package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;

public class Heal extends CommandEffect {
	
	public Heal() {
		
	}
	
	public static void healPlayer(EntityPlayer player, int num) {
		
		if(num == 1) {
			player.heal(player.getMaxHealth() - player.getHealth());
		}
		
		if(num == 2) {
			player.heal(10.0F);
		}
		
		if(num == 3) {
			player.heal(player.getMaxHealth() - player.getHealth());
			player.getFoodStats().setFoodLevel(20);
		}
		
		
	}
	
}
