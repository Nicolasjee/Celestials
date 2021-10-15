package poseidon.mod.objects.block.redstonereceiver;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class SlotRedstoneReceiver extends Slot {
	
	public SlotRedstoneReceiver(IInventory inv, int s, int i, int y) {
		super(inv, s, i, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if(stack.getItem() instanceof ItemBlock) return true;
		else return false;
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
	
}
