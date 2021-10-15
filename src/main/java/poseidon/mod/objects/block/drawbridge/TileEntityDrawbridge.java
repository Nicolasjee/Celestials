package poseidon.mod.objects.block.drawbridge;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.util.ParticleUtil;

public class TileEntityDrawbridge extends TileEntity implements IInventory, ITickable {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	private String customName;
	private int tick;
	public int d = 0;
	public boolean toggle;
	
	@Override
	public String getName() 
	{
		return this.hasCustomName() ? this.customName : "Drawbridge";
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
		this.d = compound.getInteger("progress");
		this.toggle = compound.getBoolean("toggle");
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		ItemStackHelper.saveAllItems(compound, this.inventory);
		compound.setInteger("progress", this.d);
		compound.setBoolean("toggle", this.toggle);
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	

	
	public void update() {
		EnumFacing facing = this.world.getBlockState(this.pos).getValue(BlockDrawbridge.FACING);
		int wait = 10;
		
		/*
		if(this.world.isBlockPowered(this.pos) && tick == 30 && next(facing, this.world, this.pos, d + 1, true, Blocks.AIR.getDefaultState())) 
			
		*/
		if(this.world.isBlockPowered(this.pos) && tick == wait && next(facing, this.world, this.pos, d + 1, true, Blocks.AIR.getDefaultState())) {

			ItemStack stack = null;
			if(this.inventory.get(0) != null) stack = this.inventory.get(0);
			if(stack == null) return;
			
			int count = stack.getCount();
			
			if(stack.getItem() instanceof ItemBlock && count > 0 && d >= 0) {
					
				ItemBlock b = (ItemBlock) stack.getItem();
				IBlockState c = Blocks.AIR.getDefaultState();
				
				d++;
				
				if(isNextPossible(facing, this.world, this.pos, d, true, c)) {
					if(d > 10) Main.proxy.playSoundBlock(this.pos, this.world, SoundEvents.BLOCK_PISTON_EXTEND, 1.0F, 1.0F);
					Main.proxy.playSoundBlock(getResponsibleBlock(facing, this.pos, d), this.world, SoundEvents.BLOCK_PISTON_EXTEND, 1.0F, 1.0F);
				}
				
				
				if(!world.isRemote) {
					if(facing == EnumFacing.NORTH && next(EnumFacing.NORTH, this.world, this.pos, d, true, c)) {
						ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos.north(d), EnumParticleTypes.FIREWORKS_SPARK);
						this.world.setBlockState(this.pos.north(d), b.getBlock().getDefaultState());
					}
					if(facing == EnumFacing.EAST && next(EnumFacing.EAST, this.world, this.pos, d, true, c)) {
						ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos.east(d), EnumParticleTypes.FIREWORKS_SPARK);
						this.world.setBlockState(this.pos.east(d), b.getBlock().getDefaultState());			
					}
					if(facing == EnumFacing.WEST && next(EnumFacing.WEST, this.world, this.pos, d, true, c)) {	
						ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos.west(d), EnumParticleTypes.FIREWORKS_SPARK);
						this.world.setBlockState(this.pos.west(d), b.getBlock().getDefaultState());
					}
					if(facing == EnumFacing.SOUTH && next(EnumFacing.SOUTH, this.world, this.pos, d, true, c)) {
						ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos.south(d), EnumParticleTypes.FIREWORKS_SPARK);
						this.world.setBlockState(this.pos.south(d), b.getBlock().getDefaultState());
					}
					
				}
				
				stack.setCount(count - 1);
				
				tick = 0;
				System.out.println("(up) d: " + d);
				sendUpdates();
			}
			
		}
		

		
			
		if(!this.world.isBlockPowered(this.pos) && tick == wait && d != 0 && !world.isRemote) {			
			
			
			ItemStack stack = null;
			if(this.inventory.get(0) != null) stack = this.inventory.get(0);

			int count = stack.getCount();
			if(stack.isEmpty()) stack = new ItemStack(Item.getItemFromBlock(getFirst(facing, this.pos, this.world).getBlock()));
			System.out.println("stack: " + stack + ", isBlock? " + (stack.getItem() instanceof ItemBlock) + ", count: " + count + ", next: " + getFirst(facing, this.pos, this.world));
			
			if(stack.getItem() instanceof ItemBlock && count >= 0) {
				
				
				ItemBlock b = (ItemBlock) stack.getItem();
				IBlockState c = b.getBlock().getDefaultState();
				
				if(isNextPossible(facing, this.world, this.pos, d, false, c)) {
					if(d > 10) Main.proxy.playSoundBlock(this.pos, this.world, SoundEvents.BLOCK_PISTON_CONTRACT, 1.0F, 1.0F);
					Main.proxy.playSoundBlock(getResponsibleBlock(facing, this.pos, d), this.world, SoundEvents.BLOCK_PISTON_CONTRACT, 1.0F, 1.0F);
				}
				
				
				if(!world.isRemote) {
					if(d == 1 && next(facing, this.world, this.pos, d, false, Blocks.AIR.getDefaultState())) return; 
					
					if(facing == EnumFacing.NORTH && next(EnumFacing.NORTH, this.world, this.pos, d, false, c)) 
						this.world.destroyBlock(this.pos.north(d), false);
					if(facing == EnumFacing.EAST && next(EnumFacing.EAST, this.world, this.pos, d, false, c)) 
						this.world.destroyBlock(this.pos.east(d), false);
					if(facing == EnumFacing.WEST && next(EnumFacing.WEST, this.world, this.pos, d, false, c)) 
						this.world.destroyBlock(this.pos.west(d), false);
					if(facing == EnumFacing.SOUTH && next(EnumFacing.SOUTH, this.world, this.pos, d, false, c)) 
						this.world.destroyBlock(this.pos.south(d), false);
				}
				
				stack.setCount(count + 1);
				
				d--;
				tick = 0;
				System.out.println("(down) d: " + d);
				sendUpdates();
			}
			
				
		}
		
		if(tick == 50) tick = 0;
		
		tick++;
		
	}
	
	private BlockPos getResponsibleBlock(EnumFacing facing, BlockPos pos, int d) {
		
		if(facing == EnumFacing.NORTH) return pos.north(d);
		if(facing == EnumFacing.SOUTH) return pos.south(d);
		if(facing == EnumFacing.WEST) return pos.west(d);
		if(facing == EnumFacing.EAST) return pos.east(d);
		else return pos;
		
	}
	
	private boolean next(EnumFacing e, World world, BlockPos pos, int d, boolean t, IBlockState b) {
		
		if(t) {
			if(e == EnumFacing.NORTH) 
				if(world.getBlockState(this.pos.north(d)) == b) return true;
				else return false;
			
			if(e == EnumFacing.SOUTH)
				if(world.getBlockState(this.pos.south(d)) == b) return true;
				else return false;
			
			if(e == EnumFacing.WEST)
				if(world.getBlockState(this.pos.west(d)) == b) return true;
				else return false;
	
			if(e == EnumFacing.EAST)
				if(world.getBlockState(this.pos.east(d)) == b) return true;
				else return false;
		}
		
		if(!t) {
			if(e == EnumFacing.NORTH) 
				if(world.getBlockState(this.pos.north(d)) == b) return true;
				else return false;
			
			if(e == EnumFacing.SOUTH)
				if(world.getBlockState(this.pos.south(d)) == b) return true;
				else return false;
			
			if(e == EnumFacing.WEST)
				if(world.getBlockState(this.pos.west(d)) == b) return true;
				else return false;
	
			if(e == EnumFacing.EAST)
				if(world.getBlockState(this.pos.east(d)) == b) return true;
				else return false;
		}
		return false;
	}
	
	private boolean isNextPossible(EnumFacing e, World world, BlockPos pos, int d, boolean t, IBlockState b) {
		
		if(t) {
			if(e == EnumFacing.NORTH) 
				if(world.getBlockState(this.pos.north(d)) == b) return true;
				else return false;
			
			if(e == EnumFacing.SOUTH)
				if(world.getBlockState(this.pos.south(d)) == b) return true;
				else return false;
			
			if(e == EnumFacing.WEST)
				if(world.getBlockState(this.pos.west(d)) == b) return true;
				else return false;
	
			if(e == EnumFacing.EAST)
				if(world.getBlockState(this.pos.east(d)) == b) return true;
				else return false;
		}
		
		if(!t) {
			if(e == EnumFacing.NORTH) 
				if(world.getBlockState(this.pos.north(d)) == b) return true;
				else return false;
			
			if(e == EnumFacing.SOUTH)
				if(world.getBlockState(this.pos.south(d)) == b) return true;
				else return false;
			
			if(e == EnumFacing.WEST)
				if(world.getBlockState(this.pos.west(d)) == b) return true;
				else return false;
	
			if(e == EnumFacing.EAST)
				if(world.getBlockState(this.pos.east(d)) == b) return true;
				else return false;
		}
		return false;
	}
	
	private IBlockState getFirst(EnumFacing e, BlockPos pos, World world) {
		System.out.println("pos: " + pos.getX() + ", " + pos.getZ() + ", facing: " + e);
		
		if(e == EnumFacing.NORTH) 
			return world.getBlockState(this.pos.north());
		
		if(e == EnumFacing.SOUTH)
			return world.getBlockState(this.pos.south());
		
		if(e == EnumFacing.WEST)
			return world.getBlockState(this.pos.west());

		if(e == EnumFacing.EAST)
			return world.getBlockState(this.pos.east());
		
		return world.getBlockState(this.pos.north());
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
		return "psm:drawbridge";
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
