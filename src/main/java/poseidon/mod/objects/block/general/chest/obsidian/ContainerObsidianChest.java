package poseidon.mod.objects.block.general.chest.obsidian;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerObsidianChest extends Container{

	private final int numRows;
	private final TileEntityObsidianChest chestIn;
	
	public ContainerObsidianChest(InventoryPlayer playerInv, TileEntityObsidianChest chestInv, EntityPlayer player) {
		this.chestIn = chestInv;
		this.numRows = chestInv.getSizeInventory() /9;
		
		for(int i = 0; i < this.numRows; i++) {
			for(int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(chestInv, j+ i*9, 8 + j*18, 18 + 18 *i));
			}
		}
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(playerInv, x + y*9 + 9, 8 + x*18, 85 + y * 18));
			}
		}
		
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x *18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.chestIn.isUsableByPlayer(playerIn);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		chestIn.closeInventory(playerIn);
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
 
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
 
            if (index < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return ItemStack.EMPTY;
            }
 
            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
 
        return itemstack;
    }

	public TileEntityObsidianChest getChestInventory() {
		return this.chestIn;
	}
	
}
