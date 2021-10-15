package poseidon.mod.objects.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IHasModel;

public class ArmourBase extends ItemArmor implements IHasModel {
	
	int total;
	
	public ArmourBase(String name, ArmorMaterial material, int renderIndexIn, EntityEquipmentSlot equipmentslot) {
		super(material, renderIndexIn, equipmentslot);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		ItemInit.ITEMS.add(this);
	}
	
	
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "intentory");
	}
	
}
