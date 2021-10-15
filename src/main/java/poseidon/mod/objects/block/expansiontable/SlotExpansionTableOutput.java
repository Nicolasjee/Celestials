package poseidon.mod.objects.block.expansiontable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotExpansionTableOutput extends Slot {
	
	private final EntityPlayer player;
	private int removeCount;
	
	public SlotExpansionTableOutput(EntityPlayer pl, IInventory inv, int i, int x, int y) {
		super(inv, i, x, y);
		this.player = pl;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		this.onCrafting(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}
	
	@Override
	public ItemStack decrStackSize(int amount) {
		if(this.getHasStack()) this.removeCount += Math.min(amount,  this.getStack().getCount());
		return super.decrStackSize(amount);
	}
	
}
