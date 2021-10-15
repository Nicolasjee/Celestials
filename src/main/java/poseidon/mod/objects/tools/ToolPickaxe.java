package poseidon.mod.objects.tools;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IHasModel;

public class ToolPickaxe extends ItemPickaxe implements IHasModel {

	public ToolPickaxe(String name, ToolMaterial Material) {
		super(Material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setCreativeTab(Main.ARISTOISITEMS);
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this,  0, "inventory");
	}
	
	
}
