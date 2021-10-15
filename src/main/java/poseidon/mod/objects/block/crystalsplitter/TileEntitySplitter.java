package poseidon.mod.objects.block.crystalsplitter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.laser.TileEntityLaser;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class TileEntitySplitter extends TileEntity implements IInventory, ITickable, TeslaProperties {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	public String customName = "Crystal Splitter";
	public int tick = 0;
	public boolean activate= false;
	private Item crystal = ItemInit.HOT_COALS;
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "Crystal Splitter";
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
		
		if(index == 0 && index != 1 && !flag) {
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
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public void update() {

		if(checkForBlocks()) if(checkForBlockContents()) if(activate && slot(0).getItem() == crystal && slot(0).getCount() == 1) {
							execute();
		}
					

		if((!checkForBlocks() || !checkForBlockContents() || slot(0).getItem() != crystal) && activate) activate = false;
	
	}

	public void execute() {
		
		if(tick == 0) Main.proxy.playSoundBlock(pos, world, SoundsHandler.CHARGE, 1.0F, 1.0F);
		if(tick == 80) {
			if(!world.isRemote) {
				WorldServer server = (WorldServer) world;
				double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
				ParticleUtil.laser(server, pos);
			}
			Main.proxy.playSoundBlock(pos, world, SoundsHandler.LAZER, 1.0F, 1.0F);
			activate = false;
			changeBatteries();
			this.inventory.set(0, ItemStack.EMPTY);
			Main.proxy.spawnExp(world, pos);
		}
		
		if(tick > 80) tick = 0;
		
		tick++;
	}
	
	public boolean checkForBlocks() {
		int m = 0;
		double x = this.pos.getX(); double y = this.pos.getY(); double z = this.pos.getZ();
		BlockPos[] lazers = new BlockPos[] {pos.north(3).up(), pos.north(3).down(), pos.south(3).up(), pos.south(3).down(),
						                    pos.east(3).down(), pos.east(3).up(), pos.west(3).up(), pos.west(3).down()};
		
		for(int i = 0; i < lazers.length; i++) {
			//if(!(world.getBlockState(lazers[i]).getBlock() == BlockInit.LASER)) m++;
		}
		
		if(m == 0) return true;
		else return false;
	}
	
	public boolean checkForBlockContents() {
		BlockPos[] lazers = new BlockPos[] {pos.north(3).up(), pos.north(3).down(), pos.south(3).up(), pos.south(3).down(),
                pos.east(3).down(), pos.east(3).up(), pos.west(3).up(), pos.west(3).down()};
		
		List<TileEntityLaser> tiles = new ArrayList<TileEntityLaser>();
		int m = 0;
		
		for(int i = 0; i < lazers.length; i++) {
			if((world.getTileEntity(lazers[i]) instanceof TileEntityLaser)) tiles.add((TileEntityLaser) world.getTileEntity(lazers[i]));
		}
		
		if(tiles.size() != lazers.length) {
			System.out.println("Amount of lazers: " + lazers.length + ", tiles: " + tiles.size());
			return false;
		}
		
		List<ItemStack> stacks = new ArrayList<ItemStack>();
		int dur = 0;
		for(int i = 0; i < tiles.size(); i++) {
			TileEntityLaser s = tiles.get(i);
			if(s.slot(0).getItem() == ItemInit.CHARGED_FLUX_BATTERY) {
				ItemStack stack = s.slot(0);
				if(stack.hasTagCompound()) {
					dur = stack.getTagCompound().getInteger("Durability");
					if(dur != maxDur) m++;
					} else m++;
				
				} else m++;
			dur = 0;
		}
		
	
		if(m == 0) return true;
		else return false;
		
	}
	
	public boolean hasCrystal() {
		if(slot(0).getItem() == crystal) return true;
		else return false;
	}
	
	public void changeBatteries() {
		
		List<TileEntityLaser> tiles = getTiles();
		
		for(int i = 0; i < tiles.size(); i++) {
			TileEntityLaser a = tiles.get(i);
			
			a.setSlot(0, new ItemStack(ItemInit.EMPTY_FLUX_BATTERY));
		}
		
	}
	
	public List<TileEntityLaser> getTiles() {
		BlockPos[] lazers = new BlockPos[] {pos.north(3).up(), pos.north(3).down(), pos.south(3).up(), pos.south(3).down(),
                pos.east(3).down(), pos.east(3).up(), pos.west(3).up(), pos.west(3).down()};
		
		List<TileEntityLaser> tiles = new ArrayList<TileEntityLaser>();
		int m = 0;
		
		for(int i = 0; i < lazers.length; i++) {
			if((world.getTileEntity(lazers[i]) instanceof TileEntityLaser)) tiles.add((TileEntityLaser) world.getTileEntity(lazers[i]));
		}

		return tiles;

	}
	
	
	
	
	
	public ItemStack slot(int i) {
		return this.inventory.get(0);
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
		return "psm:crystal_splitter";
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
