package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import poseidon.mod.util.Utilities;

public class Dupe extends CommandEffect {
	
	public Dupe() {
		
	}
	
	public static void dupe(EntityPlayer playerIn) {
		
		ItemStack held = playerIn.getHeldItemMainhand();
		World worldIn = playerIn.world;
		boolean isSixteen = false;
		
		if(held.isStackable()) {
			
			int size = held.getCount();
			
			if(held.getMaxStackSize() == 16) isSixteen = true;
			else isSixteen = false;
			
			if(size*2 > 64 && !isSixteen) {
				
				int extra = size*2 - 64;
				ItemStack duped = new ItemStack(held.getItem());
				duped.setCount(extra);
				held.setCount(64);
				InventoryHelper.spawnItemStack(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, duped);
				return;
				
			}
			
			if(size*2 <= 64 && !isSixteen) {
				
				held.setCount(size*2);
				return;
				
			}
			
			if(isSixteen && size*2 > 16) {
				
				int extra = size*2 - 16;
				ItemStack duped = new ItemStack(held.getItem());
				duped.setCount(extra);
				held.setCount(16);
				InventoryHelper.spawnItemStack(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, duped);
				return;
				
				
			}
			
			if(size *2 <= 16 && isSixteen) {
				held.setCount(size*2);
				return;
			}
			
		}
		
	}
}
