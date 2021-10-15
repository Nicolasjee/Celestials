package poseidon.mod.objects.block.expansiontable;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotExpansionTable extends Slot {
	
	public SlotExpansionTable(IInventory inv, int s, int i, int y) {
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
