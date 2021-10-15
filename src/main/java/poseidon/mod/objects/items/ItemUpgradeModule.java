package poseidon.mod.objects.items;

import java.util.List;
import java.util.Random;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class ItemUpgradeModule extends ItemBase implements TeslaProperties {

	public ItemUpgradeModule(String name) {
		super(name, 1, false);
	}
	
	
	private void getNBT(ItemStack stack) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("upgradePower", EMPTY);
		stack.setTagCompound(nbt);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey("upgradePower")) {
				String s = nbt.getString("upgradePower");
				if(s == EMPTY) tooltip.add(TextFormatting.GRAY + "Allocated Power: " + TextFormatting.RED + "none");
				if(s != EMPTY) tooltip.add(TextFormatting.GRAY + "Allocated Power: " + TextFormatting.GREEN + s);
			}
		}
	}
	
}
