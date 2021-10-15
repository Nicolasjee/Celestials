package poseidon.mod.objects.block.miners;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMiner extends Slot {
	
	public SlotMiner(IInventory inv, int s, int i, int y) {
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
