package poseidon.mod.objects.block.teslagenerator;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class TileEntityTeslaGenerator extends TileEntity implements IInventory, ITickable, TeslaProperties {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	private String customName;
	public int charge = 0;
	public int maxCharge = 2200;
	int tick = 0;
	public static boolean transfer = false;
	public static boolean canContinue = false;
	public int progress = 0;
	public int progress2 = 0;
	public int progress3 = 0;
	public int progress4 = 0;
	public int progress5 = 0;
	public int progress6 = 0;
	public int progress7 = 0;
	public int progress8 = 0;
	public int progress9 = 0;
	public boolean start = false;
	public boolean start2 = false;
	public boolean start3 = false;
	public boolean start4 = false;
	public boolean start5 = false;
	public boolean start6 = false;
	public boolean start7 = false;
	public boolean start8 = false;
	public boolean start9 = false;
	public ItemStack chargeItem = new ItemStack(ItemInit.CHARGED_FLUX_BATTERY);
	int tick2 = 0;
	int d = 0;
	public int itemDur;
	public boolean isCharging = (slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty());
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "Tesla Infuser";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack : this.inventory)
		{
			if(!stack.isEmpty()) return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return (ItemStack)this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		if(stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		
		if(index == 0 && index + 1 == 1 && !flag) {
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.charge = compound.getInteger("charge");
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("charge", this.charge);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	
	public boolean isBurning() {
		return false;
	}
	
	public static boolean transfer(IInventory inv) {
		return transfer;
	}
	
	public void update() {
		
		if(tick == 20 && this.charge < maxCharge && (slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && slot(1).hasTagCompound() && slot(1).getTagCompound().hasKey("Durability")) {
			
			NBTTagCompound nbt = slot(1).getTagCompound();
			if(nbt.hasKey("Durability")) {
				int dur = nbt.getInteger("Durability");
				int plus = 200;
				
				if(dur > 0 && dur >= plus) {
					
					if(charge < maxCharge && charge > maxCharge - plus) plus = maxCharge - charge;
					charge = charge + plus;
					tick = 0;
					if(tick2 == 2) tick2 = 0;
					tick2++;
					sendUpdates();
					nbt.setInteger("Durability", dur - plus);
					
				}
			}
		}
		
		if(d == 5) {
			load();
			d = 0;
		}
		
		
		if(this.charge < maxCharge && (slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty())) {
			
			
			checkProgress();
		
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick == 0 && !start) start = true;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick == 6 && !start2) start2 = true;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick == 12 && !start3) start3 = true;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick == 18 && !start4) start4 = true;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick == 4 && tick2 == 1 && !start5) start5 = true;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick == 10 && tick2 == 1 && !start6) start6 = true;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick == 16 && tick2 == 1 && !start7) start7 = true;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick == 2 && tick2 == 2 && !start8) start8 = true;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick == 8 && tick2 == 2 && !start9) {
				start9 = true;
				tick2 = 0;
			}
			
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick % 2 == 0 && start) progress++;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick % 2 == 0 && start2) progress2++;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick % 2 == 0 && start3) progress3++;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick % 2 == 0 && start4) progress4++;
			
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick % 2 == 0 && start5) progress5++;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick % 2 == 0 && start6) progress6++;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick % 2 == 0 && start7) progress7++;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick % 2 == 0 && start8) progress8++;
			if((slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) && tick % 2 == 0 && start9) progress9++;
		
		}
		
		if(d > 20 || tick > 20) {
			tick = 0;
			d = 0;
		}
	
		if(slot(0).getItem() == ItemInit.TESLA_INFUSER) d++;
		if(slot(1).getItem() == chargeItem.getItem() && slot(0).isEmpty()) tick++;
	}
	
	public void load() {
		ItemStack stack = slot(0);
		if(isValid(stack)) {
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey("DataArray")) {
				
				int[] a = nbt.getIntArray("DataArray");
				int dur = TeslaProperties.dur(a);
				
				this.itemDur = dur;
				int plus = 5;
				
				if(dur < durability && dur > durability - plus) plus = 1;
				
				if(!(this.charge <= 0) && dur < TeslaProperties.durability) {
					
					stack.setItemDamage(durability - dur - plus);
					stack.getTagCompound().setIntArray("DataArray", new int[] {a[0], a[1], a[2], a[3], a[4], a[5] + plus});
					this.charge = this.charge - plus*2;
					this.itemDur = stack.getItemDamage();
					
				}
			}
		}
	}
	
	public void checkProgress() {
		if(this.progress >= 28) progress = 0;
		if(this.progress2 >= 28) progress2 = 0;
		if(this.progress3 >= 28) progress3 = 0;
		if(this.progress4 >= 28) progress4 = 0;
		if(this.progress5 >= 28) progress5 = 0;
		if(this.progress6 >= 28) progress6 = 0;
		if(this.progress7 >= 28) progress7 = 0;
		if(this.progress8 >= 28) progress8 = 0;
		if(this.progress9 >= 28) progress9 = 0;
	}
	
	
	public int getCookTime(ItemStack input1, ItemStack input2) {
		return 0;
	}
	
	private boolean isValid(ItemStack s) {
		if(s.hasTagCompound()) return true;
		else return false;
	}
	
	private NBTTagCompound nbt(ItemStack s) {
		if(isValid(s)) return s.getTagCompound();
		else return new NBTTagCompound();
	}
	
	public ItemStack slot(int i) {
		return this.inventory.get(i);
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}


	public String getGuiID() {
		return "psm:flux_generator";
	}

	public int getCharge() {
		return this.charge;
	}

	public void setCharge(int value) {
		this.charge = value;
	}

	public boolean getCharging() {
		return this.isCharging;
	}

	@Override
	public int getFieldCount() {
		return 1;
	}

	@Override
	public void clear() 
	{
		this.inventory.clear();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}


	private void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
		markDirty();
	}
	
	private IBlockState getState() {
		return world.getBlockState(pos);
	}
	
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}

	
	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}
	



}
