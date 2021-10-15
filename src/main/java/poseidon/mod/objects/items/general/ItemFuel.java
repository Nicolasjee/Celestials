package poseidon.mod.objects.items.general;

import net.minecraft.item.ItemStack;
import poseidon.mod.init.ItemInit;

public class ItemFuel extends ItemBase {

	public ItemFuel(String name, int size, boolean f) {
		super(name, size, f);
	}
	
	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		return 2600;
	}
		
}
