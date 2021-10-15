package poseidon.mod.objects.items.general;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IHasModel;

public class ItemEffect extends Item implements IHasModel {
	
	public ItemEffect(String name, int size) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		setMaxStackSize(size);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D(){ return true; }
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	
}
