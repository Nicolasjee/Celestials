package poseidon.mod.objects.block.netherreactor.nethercore.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorHelper;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorSpawner;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class TileEntityNetherReactor extends TileEntity implements IInventory, ITickable {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);
	private String customName;
	int tick = 0;
	List<BlockPos> wrongPosses = new ArrayList<BlockPos>();
	List<BlockPos> groundSpaces = new ArrayList<BlockPos>();
	List<String> names = new ArrayList<String>();
	EntityLivingBase base;
	public boolean active = false;
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "Nether Reactor";
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
		for(ItemStack stack : this.inventory) {
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
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		//this.d = compound.getInteger("progress");
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		ItemStackHelper.saveAllItems(compound, this.inventory);
		//compound.setInteger("progress", this.d);
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public void update() {
		
		if(world.isRemote) return;
		
		ItemStack leftUp = this.inventory.get(0);
		ItemStack rightUp = this.inventory.get(1);
		ItemStack leftDown = this.inventory.get(2);
		ItemStack rightDown = this.inventory.get(3);
		ItemStack middle = this.inventory.get(4);
		

		if(leftUp.getItem() == Items.BLAZE_ROD && 
		   rightUp.getItem() == Items.MAGMA_CREAM && 
		   leftDown.getItem() == Items.GHAST_TEAR && 
		   rightDown.getItem() == Items.GLOWSTONE_DUST &&
		   middle.getItem() == Items.ENDER_EYE) {
			
			boolean environment = isEnvironmentClear();
			boolean ground = isGroundValid();
			boolean structure = isStructureValid();
			boolean dimension = dimension();
			
			//Background events
			if(tick % 200 == 0) {
				selectEntity();
				if(base != null) shootEntity();
			}
			
			if(!environment && tick % 60 == 0) highlightBlocks(0);
			if(!structure && tick % 60 == 0) highlightBlocks(1);
			if(!ground && tick % 60 == 0) highlightBlocks(2);
			
			if(environment && structure && ground && dimension && !active) {

				//NetherReactorHelper.setSinkholes(pos, world);
				//set();
				//Main.proxy.playSoundBlock(pos, world, SoundsHandler.OPENING, 1.0F, 1.0F);
				//world.setBlockState(pos.down(), BlockInit.NETHER_SPAWNER.getDefaultState());
				world.setBlockState(pos.down(2), BlockInit.NETHER_PARTICLE_HANDLER.getDefaultState());
				world.setBlockState(pos.down(3), BlockInit.NETHER_CORRUPTION_HANDLER.getDefaultState());
				world.setBlockState(pos, BlockInit.NETHER_REACTOR_CORE_ACTIVATED.getDefaultState());
				active = true;
				
			}
			
		}
		
		tick++;
		
	}
	
	private void set() {
		Iterable<BlockPos> getAll = pos.getAllInBox(pos.down(2).north(8).east(8), pos.up(5).south(8).west(8));
		Iterable<BlockPos> air = pos.getAllInBox(pos.down().north(7).east(7), pos.up(4).south(7).west(7));
		List<BlockPos> set = new ArrayList<BlockPos>();
		
		for(BlockPos i : getAll) {
			set.add(i);
		}
		
		for(BlockPos i : air) {
			set.remove(i);
		}
		
		for(int i = 0; i < set.size(); i++) {
			world.setBlockState(set.get(i), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
		}
	}

	private boolean dimension() {
		WorldServer server = (WorldServer)world;
		int id = server.provider.getDimension();
		
		if(id == 0) return true;
		else return false;
	}
	
	private boolean isGroundValid() {
		
		Iterable<BlockPos> ground = pos.getAllInBox(pos.down(2).north(18).east(18), pos.down(2).south(18).west(18));
		int mistakes = 0;
		groundSpaces.clear();
		
		for(BlockPos b : ground) 
			if(world.getBlockState(b) == Blocks.AIR.getDefaultState()) {
				groundSpaces.add(b);
				mistakes++;
			}
		
		if(mistakes > 0) {
			return false;
		}
		else return true;
		
	}
	
	private void hitOrMiss(BlockPos pos, double spread) {
		BlockPos[] surr = new BlockPos[] {pos.north(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down()};
		int neigh = 0;
		
		for(int i = 0; i < surr.length; i++) {
			if(world.getBlockState(surr[i]) != Blocks.AIR.getDefaultState()) {
				neigh++;
			}
		}
		
		if(neigh <= 5) { ParticleUtil.highlightBlock((WorldServer)world, pos, spread, EnumParticleTypes.END_ROD);}
	}
	
	private void highlightBlocks(int j) {
		
		if(j == 0) {
			
			//TODO HELP!!!
			if(wrongPosses.size() < 20) {
				for(int i = 0; i < wrongPosses.size(); i++) {
					hitOrMiss(wrongPosses.get(i), 0.25D);
					//ParticleUtil.highlightBlock((WorldServer)world, wrongPosses.get(i), 0.25D);
					System.out.println("Wrongposs:" + wrongPosses.get(i) + ", size: " + wrongPosses.size());
				}
				
			} 
			if(wrongPosses.size() >= 20) {
				
				double lowX = wrongPosses.get(0).getX();
				double lowY = wrongPosses.get(0).getY();
				double lowZ = wrongPosses.get(0).getZ();
				
				double highX = wrongPosses.get(1).getX();
				double highY = wrongPosses.get(1).getY();
				double highZ = wrongPosses.get(1).getZ();
				
				for(int i = 0; i < wrongPosses.size(); i++) {
					
					BlockPos pos = wrongPosses.get(i);
					double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
					
					if(x < lowX) lowX = x;
					if(y < lowY) lowY = y;
					if(z < lowZ) lowZ = z;
					
					if(x > highX) highX = x;
					if(y > highY) highY = y;
					if(z > highZ) highZ = z;
					
					
					
				}
				
				double[] starting = NetherReactorHelper.getSelectionPosses(new BlockPos(lowX, lowY, lowZ), new BlockPos(highX, highY, highZ), 1);
				double[] ending = NetherReactorHelper.getSelectionPosses(new BlockPos(lowX, lowY, lowZ), new BlockPos(highX, highY, highZ), 2);
				
				NetherReactorSpawner.NetherReactorHighlightningUtil(starting, ending, (WorldServer)world);
				/*
				for(int i = 0; i < wrongPosses.size(); i++) {
					hitOrMiss(wrongPosses.get(i), 0.5D);
				}
				*/
				//System.out.println("am: " + wrongPosses.size() + "Lowest: " + lowX + ", " + lowY + ", " + lowZ + "; Highest: " + highX + ", " + highY + ", " + highZ);
				
			}
			
		}
		
		if(j == 1) {
			
		}
		
		if(j == 2) {
			
			if(groundSpaces.size() < 20) 			
			for(int i = 0; i < groundSpaces.size(); i++) {
				
				ParticleUtil.cross((WorldServer) world, groundSpaces.get(i).up());
				
			}
			
			else {
				
				for(int i = 0; i < groundSpaces.size(); i++) {
					
					ParticleUtil.sides(groundSpaces.get(i), (WorldServer)world, EnumParticleTypes.END_ROD);
					
				}
				
			}
			
		}
		
	}
	
	private boolean isStructureValid() {
		
		int mistakes = 0;
		
		BlockPos pillar1 = pos.down().north().east();
		BlockPos pillar2 = pillar1.west(2);
		BlockPos pillar3 = pillar2.south(2);
		BlockPos pillar4 = pillar3.east(2);
		
		BlockPos[] nether = new BlockPos[] {pillar1.up(), pillar1, pillar1.up(2),
				                            pillar2.up(), pillar2, pillar2.up(2),
											pillar3.up(), pillar3, pillar3.up(2),
											pillar4.up(), pillar4, pillar4.up(2) };
		
		BlockPos[] cobble = new BlockPos[] {pos.down(), pos.down().north(), pos.down().south(), pos.down().east(), pos.down().west(),
											pos.up(), pos.up().north(), pos.up().south(), pos.up().east(), pos.up().west()};
		
		for(int i = 0; i < nether.length; i++) {
			if(!(world.getBlockState(nether[i]) == BlockInit.NETHER_STONE.getDefaultState())) {
				ParticleUtil.sides(nether[i], (WorldServer)world, EnumParticleTypes.FLAME);
				mistakes++;
			}
		}
		
		for(int i = 0; i < cobble.length; i++) {
			if(!(world.getBlockState(cobble[i]) == Blocks.COBBLESTONE.getDefaultState())) {
				ParticleUtil.sides(cobble[i], (WorldServer)world, EnumParticleTypes.FLAME);
				mistakes++;
			}
		}

		if(mistakes == 0) return true;
		else return false;
	}
	
	private boolean isEnvironmentClear() {
		
		Iterable<BlockPos> e = pos.getAllInBox(pos.north(18).east(18).down(), pos.south(18).west(18).up(10));
		Iterable<BlockPos> s = pos.getAllInBox(pos.north().east().down(), pos.south().west().up());
		wrongPosses.clear();
		List<BlockPos> listS = new ArrayList<BlockPos>();
		int mistakes = 0;
		
		for(BlockPos b : s) {
			listS.add(b);
		}
		
		for(BlockPos b : e) { 
			if(!listS.contains(b) && !wrongPosses.contains(b)) {
				
				if(!(world.getBlockState(b) == Blocks.AIR.getDefaultState() || world.getBlockState(b) == BlockInit.MOZESAIR.getDefaultState())) {
					if(!isNormieBlock(b)) wrongPosses.add(b);
					mistakes++;
				}
				
			} 
		}
			

		if(mistakes == 0) return true;
		else
		return false;
	}
	
	private boolean isNormieBlock(BlockPos pos) {
		Block s = world.getBlockState(pos).getBlock();
		if(s == Blocks.SNOW_LAYER || s == Blocks.TALLGRASS || s == Blocks.FIRE || s == Blocks.RED_FLOWER || s == Blocks.YELLOW_FLOWER) {
			world.destroyBlock(pos, false);
			return true;
		}
		return false;
	}
	
	private boolean setTime() {
		if(tick % 20 == 0) return true;
		else return false;
	}

	private void selectEntity() {
		
		List<EntityLivingBase> list = Utilities.getEntitiesSurroundings(world, pos);
		if(list.size() > 0) base = (EntityLivingBase) list.get(rand(list.size(), 0));
		
	}
	
	private void shootEntity() {
		if(base instanceof EntityMob) base.setVelocity(-base.getLookVec().x * 10, 0, -base.getLookVec().z * 10);
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	private int rand(int M, int m) {
		Random rn = new Random();
		int maximum = M;
		int minimum = m;
		int range = maximum - minimum;		
		int b = rn.nextInt(range) + minimum;
		return b;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	public String getGuiID() {
		return "psm:drawbridge";
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
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
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

	@Override
	public int getFieldCount() {
		return 0;
	}
	




	
}
