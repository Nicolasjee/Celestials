package poseidon.mod.objects.items.usable;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemFallDamageWarding extends ItemBase {

	public ItemFallDamageWarding(String name, int size, boolean b) {
		super(name, size, b);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.WHITE + "Grants immunity to fall damage");
	}
	
}
