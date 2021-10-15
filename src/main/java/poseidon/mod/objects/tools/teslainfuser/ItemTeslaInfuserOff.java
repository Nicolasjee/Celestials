package poseidon.mod.objects.tools.teslainfuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemTeslaInfuserOff extends ItemBase implements TeslaProperties {

	public ItemTeslaInfuserOff(String name) {
		super(name, 1, false);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack held = playerIn.getHeldItem(handIn);
		
		if(held.hasTagCompound()) {
			
			
			NBTTagCompound nbt = held.getTagCompound();
			ItemStack stack = transferNBT(held);
			
			int dur = TeslaProperties.dur(nbt.getIntArray("DataArray"));
			System.out.println("off -> on  --- Dur: " + dur);
			int slot = playerIn.inventory.getSlotFor(held);
			playerIn.inventory.removeStackFromSlot(playerIn.inventory.getSlotFor(held));
			stack.damageItem(durability - dur, playerIn);
			playerIn.inventory.setInventorySlotContents(slot, stack);
			
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, held);
	}
	
	private ItemStack transferNBT(ItemStack from) {
		
		if(from.hasTagCompound()) {
			
			ItemStack ret = new ItemStack(ItemInit.TESLA_INFUSER);
			NBTTagCompound ndt = new NBTTagCompound();
			NBTTagCompound nbt = from.getTagCompound();
			
			ndt.setIntArray("DataArray", nbt.getIntArray("DataArray"));
			ndt.setBoolean("Activated", false);
			
			ret.setTagCompound(ndt);
			return ret;
		
		}
		
		return from;
		
	}
}
