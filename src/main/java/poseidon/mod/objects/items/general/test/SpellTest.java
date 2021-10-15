package poseidon.mod.objects.items.general.test;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;
import poseidon.mod.objects.items.wand.ModAbilities;

public class SpellTest extends ItemBase {

	public SpellTest(String name, int size, boolean e) {
		super(name, size, e);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		int slot = playerIn.inventory.getSlotFor(stack);
		System.out.println(slot);
		
		if(slot == 0) { ModAbilities.wind(1, playerIn, worldIn);}
		if(slot == 1) { ModAbilities.wind2(1, playerIn, worldIn); }
		//if(slot == 2) { ModAbilities.earth(1, playerIn, worldIn); }
		if(slot == 3) { ModAbilities.earth2(playerIn, worldIn); }
		if(slot == 4) { ModAbilities.explosion(1, playerIn, worldIn); }
		if(slot == 5) { ModAbilities.inferno(1, playerIn, worldIn); }
		if(slot == 6) { ModAbilities.inferno2(1, playerIn, worldIn); }
		if(slot == 7) { ModAbilities.fire(1, playerIn, worldIn); }
		if(slot == 8) { ModAbilities.fire2(1,playerIn, worldIn); }
		
		
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
	
}
