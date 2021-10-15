package poseidon.mod.objects.block.miners;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.objects.block.expansiontable.ExpansionTableRecipes;

public class ContainerMiner extends Container {

	private final TileEntityMiner tileentity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;
	
	public ContainerMiner(InventoryPlayer player, TileEntityMiner table) {
		this.tileentity = table;
		this.addSlotToContainer(new Slot(tileentity, 0, 81, 34));
		this.addSlotToContainer(new SlotMiner(tileentity, 1, 145, 10));
		this.addSlotToContainer(new SlotMiner(tileentity, 2, 145, 35));
		this.addSlotToContainer(new SlotMiner(tileentity, 3, 145, 61));
		
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
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.burnTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.currentBurnTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.totalCookTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}
		
		this.cookTime = this.tileentity.getField(2);
		this.burnTime = this.tileentity.getField(0);
		this.currentBurnTime = this.tileentity.getField(1);
		this.totalCookTime = this.tileentity.getField(3);
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
