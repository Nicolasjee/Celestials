package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GetNBT extends CommandEffect {
	
	public GetNBT() {
		
	}
	
	public static void dupe(EntityPlayer playerIn) {
		
		ItemStack held = playerIn.getHeldItemMainhand();
		Item item = held.getItem();
		
	}
}
