package poseidon.mod.client.commands.util;

import java.util.Collection;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RemovePotion extends CommandEffect {
	
	public RemovePotion() {
		
	}
	
	public static void remove(EntityPlayer player) {
		
		World worldIn = player.world;
		
		player.clearActivePotions();
		
	}
}
