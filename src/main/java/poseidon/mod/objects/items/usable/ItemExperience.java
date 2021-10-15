package poseidon.mod.objects.items.usable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemExperience extends ItemBase {

	int experience;
	
	public ItemExperience(String name, int size, boolean b, int exp) {
		super(name, size, b);
		this.experience = exp;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack held = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote) {
			playerIn.addExperience(experience);
			held.shrink(1);
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, held);
	}
	
}
