package poseidon.mod.objects.block.redstonereceiver;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
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
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.aiolonsynth.TileEntityAiolonSynth;
import poseidon.mod.util.ParticleUtil;

public class TileEntityRedstoneReceiver extends TileEntity implements IInventory, ITickable {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private String customName;
	private int tick;
	public int power = 0;
	public boolean toggle;
	
	@Override
	public String getName() 
	{
		return this.hasCustomName() ? this.customName : "Redstone Receiver";
	}

	@Override
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() 
	{
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() 
	{
		for(ItemStack stack : this.inventory)
		{
			if(!stack.isEmpty()) return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return (ItemStack)this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{

		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
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
		this.power = compound.getInteger("power");
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		ItemStackHelper.saveAllItems(compound, this.inventory);
		compound.setInteger("power", this.power);
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	private BlockPos[] getSurroundings(BlockPos pos) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		return new BlockPos[] {new BlockPos(x + 1.0D, y, z), new BlockPos(x + 1.0D, y, z + 1.0D), 
							   new BlockPos(x, y, z + 1.0D), new BlockPos(x - 1.0D, y, z), 
							   new BlockPos(x - 1.0D, y, z - 1.0D), new BlockPos(x - 1.0D, y, z + 1.0D), 
							   new BlockPos(x, y, z - 1.0D), new BlockPos(x + 1.0D, y, z - 1.0D),
							   
							   new BlockPos(x + 1.0D, y + 1.0D, z), new BlockPos(x + 1.0D, y + 1.0D, z + 1.0D), 
							   new BlockPos(x, y + 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z), 
							   new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z + 1.0D), 
							   new BlockPos(x, y + 1.0D, z - 1.0D), new BlockPos(x + 1.0D, y + 1.0D, z - 1.0D),
							   new BlockPos(x, y + 1.0D, z),
							   
							   new BlockPos(x + 1.0D, y - 1.0D, z), new BlockPos(x + 1.0D, y - 1.0D, z + 1.0D), 
							   new BlockPos(x, y - 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z), 
							   new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z + 1.0D), 
							   new BlockPos(x, y - 1.0D, z - 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z - 1.0D),
							   new BlockPos(x, y - 1.0D, z)
		};	
	}
	
	public void update() {

		BlockPos[] next = getSurroundings(this.getPos());
		
		if(tick == 40 && !slotsAreEmpty()) {
			for(int i = 0; i < this.getSizeInventory(); i++) {
				if(doesSlotContainRedstone(i)) { 
					this.power = this.power + getPower(this.inventory.get(i));
					this.inventory.get(i).shrink(1);
				}
				sendUpdates();
			
			}
		}
		
		if(tick == 50) {
			
			//if(world.getBlockState(getPos().down()) == BlockInit.AIOLON_SYNTH.getDefaultState()) {
				
				if(world.getTileEntity(getPos().down()) != null && world.getTileEntity(getPos().down()) instanceof TileEntityAiolonSynth) {
					TileEntityAiolonSynth a = (TileEntityAiolonSynth) world.getTileEntity(getPos().down());
					
					if(a.necessaryPower != a.power && this.power > a.speed) {
						sendUpdates();
					}
					
					if(a.necessaryPower != a.power && this.power < a.speed) {
						noPower(this.world, this.getPos());
					}
					
				}
			//}
			//System.out.println("R: " + this.power);
			tick = 0;
		}
		
		
	
		tick++;
		
	}
	
	private int getPower(ItemStack stack) {
		if(stack.getItem() == Items.REDSTONE) return 3;
		if(stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) return 27;
		else return 0;
	}
	
	private int slotSize(int i) {
		return this.inventory.get(i).getCount();
	}
	
	private boolean slotSizeCheck(int i) {
		if(this.inventory.get(i).getCount() == 1) return false;
		else return true;
	}
	
	private boolean doesSlotContainRedstone(int i) {
		if(this.inventory.get(i).getItem() == Items.REDSTONE || this.inventory.get(i).getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) return true;
		else return false;
	}

	private boolean slotsAreEmpty() {
		if(this.inventory.get(0) == ItemStack.EMPTY && this.inventory.get(1) == ItemStack.EMPTY && this.inventory.get(2) == ItemStack.EMPTY) return true;
		else return false;
	}
	
	private void noPower(World world, BlockPos pos) {
		if(!world.isRemote) ParticleUtil.noPower((WorldServer)world, pos);
		Main.proxy.playSoundBlock(pos, world, SoundEvents.BLOCK_DISPENSER_DISPENSE, 2, 0.4F);
	}

	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	public String getGuiID() 
	{
		return "psm:redstonereceiver";
	}

	@Override
	public int getFieldCount() 
	{
		return 1;
	}

	@Override
	public void clear() 
	{
		this.inventory.clear();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
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
