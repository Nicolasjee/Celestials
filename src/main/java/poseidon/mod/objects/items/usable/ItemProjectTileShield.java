package poseidon.mod.objects.items.usable;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemProjectTileShield extends ItemBase {
	
	public ItemProjectTileShield(String name, int size, boolean e) {
		super(name, size, e);
	}
	

	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.WHITE + "Grants immunity to arrows");
	}
}
