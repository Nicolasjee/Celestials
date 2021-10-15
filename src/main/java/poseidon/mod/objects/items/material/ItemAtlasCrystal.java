package poseidon.mod.objects.items.material;

import java.util.List;

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

public class ItemAtlasCrystal extends ItemBase {

	public int xp;
	
	public ItemAtlasCrystal(String name, int size, int exp, boolean effect) {
		super(name, size, effect);
		this.xp = exp;
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}
	

	public static void getNBT(ItemStack stack) {
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		}
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", 0);
		}

		stack.setTagCompound(nbt);
	}
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound()) tooltip.add(TextFormatting.GRAY + "Power stored: " + TextFormatting.GREEN + Integer.toString(stack.getTagCompound().getInteger("Durability")) + TextFormatting.WHITE + " /" + TextFormatting.DARK_GREEN + " 100");
	}
	
	
}
