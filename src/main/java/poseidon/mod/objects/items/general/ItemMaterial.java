package poseidon.mod.objects.items.general;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.wand.WandData;

public class ItemMaterial extends ItemBase {
	
	NBTTagCompound nbt;
	
	public ItemMaterial(String name, int size, boolean e) {
		super(name, size, e);
		setCreativeTab(Main.ARISTOISITEMS);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound()) {
			//tooltip.add("-" + stack.getTagCompound().getString("Spell")+ "-");
		}
	}
}
