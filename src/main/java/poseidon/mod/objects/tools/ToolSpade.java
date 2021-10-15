package poseidon.mod.objects.tools;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IHasModel;

public class ToolSpade extends ItemSpade implements IHasModel {

	public ToolSpade(String name, ToolMaterial Material) {
		super(Material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		ItemInit.ITEMS.add(this);
		setCreativeTab(Main.ARISTOISITEMS);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this,  0, "inventory");
	}
	
	
}
