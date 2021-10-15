package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import poseidon.mod.init.ItemInit;

public class More extends CommandEffect {
	
	public More() {
		
	}
	
	public static void give(EntityPlayer playerIn) {
		
		
		ItemStack stack = playerIn.getHeldItemMainhand();
		
		if(!stack.isStackable()) {
			InventoryHelper.spawnItemStack(playerIn.world, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(stack.getItem()));
		}
		
	}
}
