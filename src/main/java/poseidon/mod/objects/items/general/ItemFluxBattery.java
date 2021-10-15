package poseidon.mod.objects.items.general;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class ItemFluxBattery extends ItemBase implements TeslaProperties {

	public ItemFluxBattery(String name, boolean b) {
		super(name, 1, b);
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack, true);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(stack.hasTagCompound()) {
			if(stack.getTagCompound().hasKey("Durability")) {
				if(stack.getTagCompound().getInteger("Durability") <=0 && entityIn instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entityIn;
					
					int slot = player.inventory.getSlotFor(stack);
					player.inventory.removeStackFromSlot(slot);
					ItemStack empty = new ItemStack(ItemInit.EMPTY_FLUX_BATTERY);
					getNBT(empty, false);
					player.inventory.setInventorySlotContents(slot, empty);
					
				}
			}
		}
		if(!stack.hasTagCompound() && stack.getItem() == ItemInit.EMPTY_FLUX_BATTERY) getNBT(stack, false);
		if(!stack.hasTagCompound() && stack.getItem() == ItemInit.CHARGED_FLUX_BATTERY) getNBT(stack, true);
	}
	
	public static void getNBT(ItemStack stack, boolean full) {
		if(!stack.hasTagCompound()) {
			NBTTagCompound nbt = new NBTTagCompound();
			
			if(!nbt.hasKey("Durability")) {
				if(full) nbt.setInteger("Durability", maxDur);
				if(!full) nbt.setInteger("Durability", 0);
				} else {
				nbt.setInteger("Durability", nbt.getInteger("Durability"));
			}
			
			stack.setTagCompound(nbt);
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound()) {
			tooltip.add(TextFormatting.WHITE + "Battery Power: " + TextFormatting.RED + Integer.toString(stack.getTagCompound().getInteger("Durability")));
		}
	}
}
