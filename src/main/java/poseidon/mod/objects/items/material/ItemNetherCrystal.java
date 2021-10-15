package poseidon.mod.objects.items.material;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemNetherCrystal extends ItemBase {

	NBTTagCompound nbt;
	
	public ItemNetherCrystal(String name, boolean e) {
		super(name, 64, e);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(!stack.hasTagCompound()) {
			getNBT(stack);
		}
		
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;

			if(player.getHeldItemMainhand().getItem() == stack.getItem() || player.getHeldItemOffhand().getItem() == stack.getItem()) {
				
			}	
		}
	}
	
	private void getNBT(ItemStack stack) {
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} //Gets The compound which holds keys!
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", 0);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		if(nbt.hasKey("Activated")) {
			nbt.setBoolean("Activated", nbt.getBoolean("Activated"));
			} else {
			nbt.setBoolean("Activated", true);
		}
		stack.setTagCompound(nbt);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	}

	
}
