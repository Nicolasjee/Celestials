package poseidon.mod.objects.block.general.chest.sand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import poseidon.mod.util.Reference;

public class TileEntitySandChest extends TileEntityLockableLoot implements ITickable {

	private NonNullList<ItemStack> contents = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
	public int numPlayersUsing, ticksSinceSync;
	public float lidAngle, prevLidAngle;
	public boolean locked = true;
	
	@Override
	public int getSizeInventory() { return 27; }
	
	@Override
	public int getInventoryStackLimit() { return 64; }
	
	@Override
	public boolean isEmpty() {
		for(ItemStack stack : this.contents) {
			if(!stack.isEmpty()) return false;
		}
		
		return true;
	}
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "Sand Chest";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.contents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if(!this.checkLootAndRead(compound)) ItemStackHelper.loadAllItems(compound, contents);
		if(compound.hasKey("CustomName", 8)) this.customName = compound.getString("CustomName");
		this.locked = compound.getBoolean("locked");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if(!this.checkLootAndWrite(compound)) ItemStackHelper.saveAllItems(compound, contents);
		if(compound.hasKey("CustomName", 8)) compound.setString("CustomName", this.customName);
		compound.setBoolean("locked", this.locked);
		return compound;
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) { return new ContainerSandChest(playerInventory, this, playerIn); }
	
	@Override
	public String getGuiID() { return Reference.MODID + ":sand_chest"; }
	
	@Override
	protected NonNullList<ItemStack> getItems() { return this.contents; }
	
	@Override
    public void update() {
        if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + pos.getX() + pos.getY() + pos.getZ()) % 200 == 0) {
            this.numPlayersUsing = 0;
            float f = 5.0F;
 
            for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)((float)pos.getX() - 5.0F), (double)((float)pos.getY() - 5.0F), (double)((float)pos.getZ() - 5.0F), (double)((float)(pos.getX() + 1) + 5.0F), (double)((float)(pos.getY() + 1) + 5.0F), (double)((float)(pos.getZ() + 1) + 5.0F)))) {
                if (entityplayer.openContainer instanceof ContainerSandChest) {
                    if (((ContainerSandChest)entityplayer.openContainer).getChestInventory() == this) {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }
       
        this.prevLidAngle = this.lidAngle;
        float f1 = 0.1F;
 
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            double d1 = (double)pos.getX() + 0.5D;
            double d2 = (double)pos.getZ() + 0.5D;
            this.world.playSound((EntityPlayer)null, d1, (double)pos.getY() + 0.5D, d2, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        }
 
        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
            float f2 = this.lidAngle;
 
            if (this.numPlayersUsing > 0) {
                this.lidAngle += 0.1F;
            } else {
                this.lidAngle -= 0.1F;
            }
 
            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }
 
            float f3 = 0.5F;
 
            if (this.lidAngle < 0.5F && f2 >= 0.5F) {
                double d3 = (double)pos.getX() + 0.5D;
                double d0 = (double)pos.getZ() + 0.5D;
                this.world.playSound((EntityPlayer)null, d3, (double)pos.getY() + 0.5D, d0, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }
 
            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }      
    }
	
	@Override
	public void openInventory(EntityPlayer player) {
		++this.numPlayersUsing;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
		this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
		--this.numPlayersUsing;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
		this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
	}
	
}
