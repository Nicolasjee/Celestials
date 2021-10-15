package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import poseidon.mod.init.ItemInit;

public class ShieldCode extends CommandEffect {
	
	public ShieldCode() {
		
	}
	
	public static void set(EntityPlayer playerIn, int code) {
		
		ItemStack held = playerIn.getHeldItemMainhand();
		
		if(held.getItem() == ItemInit.KEYCARD) {
			
			if(held.hasTagCompound()) {
				
				execute(playerIn, held, held.getTagCompound(), code);
				
			} else if(!held.hasTagCompound()) {
				
				getNBT(held);
				if(held.hasTagCompound()) execute(playerIn, held, held.getTagCompound(), code);
				
			}
			
		}

		
	
	}
	
	public static void getNBT(ItemStack stack) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setIntArray("Codes", new int[] {0,0,0,0,0});
		stack.setTagCompound(nbt);
	}
	
	public static void execute(EntityPlayer player, ItemStack stack, NBTTagCompound nbt, int code) {
		
		if(nbt.hasKey("Codes")) {
			
			nbt.setIntArray("Codes", new int[] {code, 0,0,0, 0});
			
		}
		
	}
	
}
