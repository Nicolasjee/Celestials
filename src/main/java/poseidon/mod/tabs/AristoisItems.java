package poseidon.mod.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import poseidon.mod.init.ItemInit;

public class AristoisItems extends CreativeTabs {
	
	public AristoisItems(String label) {
		super(label);

	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.GOLD_MULTITOOL);
	}
	
}
