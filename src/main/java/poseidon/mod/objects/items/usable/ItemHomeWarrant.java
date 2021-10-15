package poseidon.mod.objects.items.usable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemHomeWarrant extends ItemBase {

	public ItemHomeWarrant(String s) {
		super(s, 1, false);
	}
	
	private void getNBT(ItemStack stack) {
		if(!stack.hasTagCompound()) {
			NBTTagCompound nbt = new NBTTagCompound();
			
			nbt.setIntArray("HomeCoords", new int[] {0,0,0,0});
			
			stack.setTagCompound(nbt);
		}
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		if(!stack.hasTagCompound()) getNBT(stack);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound()) getNBT(stack);
	}
	
}
