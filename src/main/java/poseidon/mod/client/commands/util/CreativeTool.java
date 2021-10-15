package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.Utilities;

public class CreativeTool extends CommandEffect {
	
	public CreativeTool() {
		
	}
	
	public static void give(EntityPlayer playerIn, World worldIn) {
		
		InventoryHelper.spawnItemStack(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(ItemInit.KILL_STICK));
		
	
	}
}
