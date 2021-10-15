package poseidon.mod.objects.block.miners;


import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;

public class TileEntityMinerBlock extends TileEntity implements IInventory, ITickable {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	private String customName;
	private int tick;
	public int fuel = 0;
	public int d = 1;
	public int speed = 30;
	
	@Override
	public Block getBlockType() {
		return BlockInit.MINERBLOCK;
	}
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "Miner";
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
		for(ItemStack stack : this.inventory) { if(!stack.isEmpty()) return false;}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index){return (ItemStack)this.inventory.get(index);}

	@Override
	public ItemStack decrStackSize(int index, int count) {return ItemStackHelper.getAndSplit(this.inventory, index, count);}

	@Override
	public ItemStack removeStackFromSlot(int index) {return ItemStackHelper.getAndRemove(this.inventory, index);}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		if(stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		
		if(index == 0 && index + 1 == 1 && !flag) {
			ItemStack stack1 = (ItemStack)this.inventory.get(index);
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.d = compound.getInteger("progress");
		this.fuel = compound.getInteger("fuel");
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) { 
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		compound.setInteger("progress", this.d);
		compound.setInteger("fuel", this.fuel);
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {return 64;}
	
	

	
	public void update() {
		
		EnumFacing facing = this.world.getBlockState(this.pos).getValue(BlockMiner.FACING);
		
		if(this.inventory.get(0) == null) return;
		if(this.inventory.get(0).isEmpty()) return;
		ItemStack stack = this.inventory.get(0);
		int count = stack.getCount();
		
		boolean hasLavaUpgrade = hasLavaUpgrade();
		boolean hasSpeedUpgrade = hasSpeedUpgrade();
		boolean hasReachUpgrade = hasReachUpgrade();
		
		if(tick == 20) {
			System.out.println("d: " + d);
			System.out.println("s1: " + this.inventory.get(0) + ", s2:" + this.inventory.get(1) + ", s3: " + this.inventory.get(2));
			tick = 0;
		}
		
		if(this.world.isBlockPowered(this.pos) && tick == getSpeed(hasSpeedUpgrade) && hasFuel() && d <= 32) {			

			if(this.fuel == 0) {
				this.fuel = getFuelTime(stack);
				stack.setCount(count - 1);
			}
			//d == 32 stop
			
			if(facing == EnumFacing.NORTH && fuel > 0) {
				destroy(facing, d, hasLavaUpgrade);
				fuel = fuel - 9;
			}
				
			d++;

			
			tick = 0;
			System.out.println("d: " + d);
			sendUpdates();
				
		}

		
		
		if(tick == 50) tick = 0;
		
		tick++;
		
	}
	
	private int getSpeed(boolean a) {
		if(a) return 10;
		else return 30;
	}
	
	private boolean hasLavaUpgrade() {
		if(this.inventory.get(3) != ItemStack.EMPTY && this.inventory.get(3) != null) {
			ItemStack upgrade = this.inventory.get(3);
			if(upgrade.getItem() == ItemInit.LAVA_UPGRADE) return true;
			else return false;
			} else {
			return false;
		}
	}
	
	private boolean hasReachUpgrade() {
		if(this.inventory.get(2) != ItemStack.EMPTY && this.inventory.get(2) != null) {
			ItemStack upgrade = this.inventory.get(2);
			if(upgrade.getItem() == ItemInit.REACH_UPGRADE) return true;
			else return false;
			} else {
			return false;
		}
	}
	
	private boolean hasSpeedUpgrade() {
		if(this.inventory.get(1) != ItemStack.EMPTY && this.inventory.get(1) != null) {
			ItemStack upgrade = this.inventory.get(1);
			if(upgrade.getItem() == ItemInit.SPEED_UPGRADE) return true;
			else return false;
			} else {
			return false;
		}
	}
	
	private void destroy(EnumFacing f, int d, boolean a) {
		
		int l = 0;
		
		if(f == EnumFacing.NORTH) {
			
			BlockPos next = this.pos.north(d);
			BlockPos[] connecting = new BlockPos[] {next.up(), next.down(), next.west(), next.east(), next.east().up(),
													next.east().down(), next.west().up(), next.west().down()};
			
			for(int i = 0; i < connecting.length; i++) {
				if(notAir(connecting[i])) this.world.destroyBlock(connecting[i], true);
				if(this.world.getBlockState(connecting[i]) == Blocks.LAVA.getDefaultState() && a) l++; 
			}
			
			if(l > 0 && a) stopLava(connecting, f);
		}
		
		if(f == EnumFacing.EAST) {
			
			BlockPos next = this.pos.east(d);
			BlockPos[] connecting = new BlockPos[] {next.up(), next.down(), next.north(), next.south(), next.south().up(),
													next.south().down(), next.north().up(), next.north().down()};
			
			for(int i = 0; i < connecting.length; i++) {
				if(notAir(connecting[i])) this.world.destroyBlock(connecting[i], true);
				if(this.world.getBlockState(connecting[i]) == Blocks.LAVA.getDefaultState() && a) l++; 
			}
			
			if(l > 0 && a) stopLava(connecting, f);
			
		}
		
		if(f == EnumFacing.SOUTH) {
			
			BlockPos next = this.pos.south(d);
			BlockPos[] connecting = new BlockPos[] {next.up(), next.down(), next.east(), next.west(), next.west().up(),
													next.west().down(), next.east().up(), next.east().down()};
			
			for(int i = 0; i < connecting.length; i++) {
				if(notAir(connecting[i])) this.world.destroyBlock(connecting[i], true);
				if(this.world.getBlockState(connecting[i]) == Blocks.LAVA.getDefaultState() && a) l++; 
			}
			
			if(l > 0 && a) stopLava(connecting, f);
			
		}
		
		if(f == EnumFacing.WEST) {
			
			BlockPos next = this.pos.west(d);
			BlockPos[] connecting = new BlockPos[] {next.up(), next.down(), next.north(), next.south(), next.south().up(),
													next.south().down(), next.north().up(), next.north().down(), next};
			
			for(int i = 0; i < connecting.length; i++) {
				if(notAir(connecting[i])) this.world.destroyBlock(connecting[i], true);
				if(this.world.getBlockState(connecting[i]) == Blocks.LAVA.getDefaultState() && a) l++; 
			}
			
			if(l > 0 && a) stopLava(connecting, f);
			
		}
		
	}
	
	private void stopLava(BlockPos[] k, EnumFacing f) {
		for(int i = 0; i < k.length; i++) {
			if(this.world.getBlockState(k[i]) == Blocks.AIR.getDefaultState()) this.world.setBlockState(k[i], BlockInit.MOD_AIR.getDefaultState());
		}
	}
	
	private boolean notAir(BlockPos pos) {
		if(this.world.getBlockState(pos) == Blocks.AIR.getDefaultState()) return false;
		else return true;
	}
	
	private boolean hasFuel() {
		if(isFuel(this.inventory.get(0))) return true;
		else return false;
	}
	
	private int getFuelTime(ItemStack stack) {
		if(stack.getItem() == Items.COAL) return 36;
		else if(stack.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK)) return 36*9;
		else if(stack.getItem() == Item.getItemFromBlock(Blocks.COAL_ORE)) return 36 + 18;
		else if(stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) return 18 * 9;
		else if(stack.getItem() == Items.REDSTONE) return 18;
		else if(stack.getItem() == ItemInit.HOT_COALS) return 36;
		return 0;
	}
	
	private boolean isFuel(ItemStack stack) {
		if(stack.getItem() == Items.COAL) return true;
		else if(stack.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK)) return true;
		else if(stack.getItem() == Item.getItemFromBlock(Blocks.COAL_ORE)) return true;
		else if(stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) return true;
		else if(stack.getItem() == Items.REDSTONE) return true;
		else if(stack.getItem() == ItemInit.HOT_COALS) return true;
		else return false;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) { return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	public String getGuiID() {return "psm:miner";}

	@Override
	public int getFieldCount() {return 4;}

	@Override
	public void clear() {this.inventory.clear();}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if(i == 0 && isFuel(stack)) return true;
		else if(i == 1 && stack.getItem() == ItemInit.SPEED_UPGRADE) return true;
		else if(i == 2 && stack.getItem() == ItemInit.LAVA_UPGRADE) return true;
		else if(i == 3 && stack.getItem() == ItemInit.REACH_UPGRADE) return true;
		else return false;
	}

	@Override
	public int getField(int id) {return id;}

	@Override
	public void setField(int id, int value) {}

	private IBlockState getState() {
		return world.getBlockState(pos);
	}
	
	private void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
		markDirty();
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
	

}
