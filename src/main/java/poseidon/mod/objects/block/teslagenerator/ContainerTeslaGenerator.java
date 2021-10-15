package poseidon.mod.objects.block.teslagenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import poseidon.mod.objects.block.expansiontable.ExpansionTableRecipes;

public class ContainerTeslaGenerator extends Container {

	private final TileEntityTeslaGenerator tileentity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;
	
	public ContainerTeslaGenerator(InventoryPlayer player, TileEntityTeslaGenerator table) {
		this.tileentity = table;
		this.addSlotToContainer(new SlotTeslaGenerator(tileentity, 0, 80, 34));
		this.addSlotToContainer(new Slot(tileentity, 1, 143, 35));
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y*18));
			}
		}
		
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x *18, 142));
		}
		
	}
	

	@Override
	public void addListener(IContainerListener listener) 
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
	}

	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		System.out.println("Slot: " + slot + ", Index: " + index);
		
		if(slot != null && slot.getHasStack()) {
			
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			System.out.println("slot != null & slot has a stack : " + stack1);
			
			if(index == 3) {
				
				if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
				System.out.println("index ?= 3:" + (index == 3) + ", merge?: " + (!this.mergeItemStack(stack1, 4, 40, true)));
				
			}
			else if(index != 2 && index != 1 && index != 0) {
				
				Slot slot1 = (Slot)this.inventorySlots.get(index + 1);
				System.out.println("index != 0,1,2 -> index: " + index + ", other slot: " + slot1 + ", num: " + (index + 1));				
				if(!ExpansionTableRecipes.getInstance().getExpansionTableResult(stack1, slot1.getStack()).isEmpty()) {
					if(!this.mergeItemStack(stack1, 0, 2, false)) {
						return ItemStack.EMPTY;
					}
					else if(index >= 4 && index < 31) {
						if(!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
					}
					else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false)) {
						return ItemStack.EMPTY;
					}
				}

			}
			
			else if(!this.mergeItemStack(stack1, 4, 40, false)) {
				return ItemStack.EMPTY;
			}
			
			if(stack1.isEmpty()) {
				
				slot.putStack(ItemStack.EMPTY);
				
				} else {
				
				slot.onSlotChanged();

			}
			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
	
	
}
