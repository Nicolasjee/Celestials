package poseidon.mod.objects.block.teslagenerator;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class SlotTeslaGenerator extends Slot {
	
	public SlotTeslaGenerator(IInventory inv, int s, int i, int y) {
		super(inv, s, i, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return true;
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
	
}
