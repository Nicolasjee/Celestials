package poseidon.mod.objects.block.general.tileHelpers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;
import poseidon.mod.util.helpers.BlockConnectUtil;

public class TileEntityShield extends TileEntity implements ITickable {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public int tick = 0;
	public boolean hasCodeLinked = false;
	public int linkedCode = 0;
	private boolean facing = false;
	private EnumFacing f;
	
	public double[] s;
	public double[] t;
	public boolean open = false;
	public int countDown = 100;
	
	//SubOpen is for the two blocks next to the open block.
	//the particles are managed differently && movement
	public boolean subOpen = false;
	
	@Override
	public void update() {
		
		if(!facing) setFacing();
		
		if(!open && !subOpen) {
			if(tick % 5 == 0) {
				//push();
			}
			spawnParticle();
		}
		
		if((open || subOpen) && !world.isRemote) {
			
			//RESET
			if(countDown == 0) {
				replaceAir();
				getTickSynch();
				updateShields();
				open = false;
				subOpen = false;
				countDown = 100;
			}
			
			if(countDown % 10 == 0) checkAir();
			
			//only the parent can connect (both subOpen == neighbours are in this statement) we filter the neighbours with the open boolean
			//edit: subopens don't have access to s, t so they cannot be in this statement! (that uses s and t)
			if(open) BlockConnectUtil.connect(s, t, (WorldServer)world, EnumParticleTypes.FLAME, 20);
			
					
			//Particles above door
			if(tick > 40) spawnParticle();
		}
		
		
		
		
		if(!facing) setFacing();
		
		if(!hasActiveSequence() && tick == 20 && !(open || subOpen)) {
			
			//sets the air above the shield to stop enemies
			getMissingAir();
			tick = 0;
			sendUpdates();
			
		}


		
		if(tick > 60) tick = 0;
		
		tick++;
		if(tick % 15 == 0) sendUpdates();
		if(open || subOpen) countDown--;
	}
	
	private void getTickSynch() {
		List<TileEntityShield> n = new ArrayList<TileEntityShield>();
		if(f == EnumFacing.EAST || f == EnumFacing.WEST) {
			if(world.getBlockState(pos.north(2)) == BlockInit.SHIELDBLOCK.getDefaultState() && world.getTileEntity(pos.north(2)) instanceof TileEntityShield) n.add((TileEntityShield)world.getTileEntity(pos.north(2)));
			if(world.getBlockState(pos.south(2)) == BlockInit.SHIELDBLOCK.getDefaultState() && world.getTileEntity(pos.south(2)) instanceof TileEntityShield) n.add((TileEntityShield)world.getTileEntity(pos.south(2)));
			if(n.size() > 0) {
				
				for(int i = 0; i < n.size(); i++) {
					
					tick = (n.get(i)).tick;
					
				}
				
			}
			return;
		}
		if(f == EnumFacing.NORTH || f == EnumFacing.EAST) {
			if(world.getBlockState(pos.east(2)) == BlockInit.SHIELDBLOCK.getDefaultState() && world.getTileEntity(pos.east(2)) instanceof TileEntityShield) n.add((TileEntityShield)world.getTileEntity(pos.east(2)));
			if(world.getBlockState(pos.west(2)) == BlockInit.SHIELDBLOCK.getDefaultState() && world.getTileEntity(pos.west(2)) instanceof TileEntityShield) n.add((TileEntityShield)world.getTileEntity(pos.west(2)));
			if(n.size() > 0) {
				
				for(int i = 0; i < n.size(); i++) {
					
					tick = (n.get(i)).tick;
					
				}
				
			}
			return;
		}
		
		System.out.println("Tick was not synchronized. Possible because f was not recognized: " + f);
	}
	
	public void updateShields() {
		Iterable<BlockPos> posses = pos.getAllInBox(pos.north(10).east(10).up(6), pos.south(10).west(10).down(6));
		List<BlockPos> list = new ArrayList<BlockPos>();
		List<Integer> ints = new ArrayList<Integer>();
		
		for(BlockPos s : posses) {
			
			if(world.getBlockState(s).getBlock() == BlockInit.SHIELDBLOCK) {
				list.add(s);
			}
			
		}
		
		for(int i = 0; i < list.size(); i++) {
			
			if(world.getTileEntity(list.get(i)) instanceof TileEntityShield) {
				TileEntityShield shield = (TileEntityShield) world.getTileEntity(list.get(i));
				ints.add(shield.tick);
			}
			
		}
		
		for(int i = 0; i < ints.size(); i++) {
			if(i > 0) {
				if(!(ints.get(i) == ints.get(i-1))) {
					synchronise(list);
					return;
				}
			}
		}
		
	}
	
	private void synchronise(List<BlockPos> list) {
		for(int i = 0; i < list.size(); i++) {
			
			if(world.getTileEntity(list.get(i)) instanceof TileEntityShield) {
				TileEntityShield shield = (TileEntityShield) world.getTileEntity(list.get(i));
				shield.tick = 0;
			}
			
		}
	}
	
	
	private void checkAir() {
		for(int i = 1; i < 5; i++) {
			if(world.getBlockState(pos.up(i)) != Blocks.AIR.getDefaultState()) world.setBlockState(pos.up(i), Blocks.AIR.getDefaultState());
		}
	}
	
	private void replaceAir() {
		for(int i = 1; i < 6; i++) {
			System.out.println("State: " +world.getBlockState(pos.up(i)) );
			if(world.getBlockState(pos.up(i)) != Blocks.AIR.getDefaultState()) {
				world.setBlockState(pos.up(i), BlockInit.SHIELDAIR.getDefaultState());
				System.out.println("State Replaced to: " +world.getBlockState(pos.up(i)) );
				if(!world.isRemote) ParticleUtil.sides(pos.up(i), (WorldServer)world, EnumParticleTypes.TOTEM);
			}
		}
	}

	private void setFacing() {
		f = world.getBlockState(pos).getValue(FACING);
	}
	
	private boolean valid(EntityPlayer entityIn) {
		sendUpdates();
		if(!world.isRemote) System.out.println("Open:" + open + ", " + subOpen);
		if(!world.isRemote && (open || subOpen)) {
			System.out.println("Opened");
			return false;
		}
		if(!open && !subOpen && !(hasMatchingKeyCode(entityIn, pos, world))) {
			System.out.println("Closed and no valid card" + ", bb: " + open + ", sub:" + subOpen);
			return true;
		}
		if(!open && !subOpen && hasMatchingKeycard(entityIn) && hasMatchingKeyCode(entityIn, pos, world)) {
			System.out.println("Closed and has matching card");
			return false;
		}
		
		return false;
	}
	
	private boolean hasMatchingKeycard(Entity entity) {
		if(entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack stack = player.getHeldItemMainhand();
			
			if(stack.hasTagCompound()) {
				return true;
			}
			
		}
		
		return false;
	}
	
	private boolean hasMatchingKeyCode(Entity entity, BlockPos pos, World world) {
		
		if(entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack stack = player.getHeldItemMainhand();
			TileEntity te = world.getTileEntity(pos);
			
			if(te instanceof TileEntityShield && stack.getItem() == ItemInit.KEYCARD && stack.hasTagCompound()) {
				
				TileEntityShield shield = (TileEntityShield) te;
				NBTTagCompound nbt = stack.getTagCompound();
				
				if(nbt.hasKey("Codes") && shield.hasCodeLinked) {
					
					System.out.println("PSM: ShieldHas: " + shield.hasCodeLinked + ", keycode: " + shield.linkedCode + ", keycard: " + nbt.getInteger("Keycode"));
					if(shield.linkedCode == nbt.getIntArray("Codes")[0]) return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	private boolean hasActiveSequence() {
		for(int i = 1; i < 6; i++) {
			if(world.getBlockState(pos.up(i)) != BlockInit.SHIELDAIR.getDefaultState() && world.getBlockState(pos.up(i)) == Blocks.AIR.getDefaultState()) return false;
		}
		return true;
	}
	
	private void getMissingAir() {
		
		for(int i = 1; i < 6; i++) {
			if(world.getBlockState(pos.up(i)) != BlockInit.SHIELDAIR.getDefaultState() && world.getBlockState(pos.up(i)) == Blocks.AIR.getDefaultState()) {
				world.setBlockState(pos.up(i), BlockInit.SHIELDAIR.getDefaultState());
				if(!world.isRemote) ParticleUtil.sides(pos.up(i), (WorldServer)world, EnumParticleTypes.END_ROD);
				return;
			}
		}
		
	}
	
	private void spawnParticle() {
		
		if(!world.isRemote) {
			if(world.getBlockState(pos).getBlock() == BlockInit.SHIELDBLOCK) {
				
				EnumFacing facing = world.getBlockState(pos).getValue(FACING);
				
				for(int i = 0; i < 20; i++) {
					if(tick == 5*i) ParticleUtil.barrierShield((WorldServer)world, pos, facing, tick);
				}
			}
		}
		
	}

	private void push() {
		List<Entity> s = Utilities.getListChangable(world, pos, Entity.class);
		for(int i = 0; i < s.size(); i++) {
			if(s.get(i) instanceof EntityPlayer && valid((EntityPlayer)s.get(i))) velocityFacing(s.get(i), f);
		} 
	}
	
	private void velocityFacing(Entity entityIn, EnumFacing f) {
		if(f == EnumFacing.WEST) entityIn.setVelocity(-1.30F, entityIn.motionY*1.02F, -entityIn.motionZ*1.04F);
		if(f == EnumFacing.EAST) entityIn.setVelocity(1.30F, entityIn.motionY*1.02F, -entityIn.motionZ*1.04F);
		if(f == EnumFacing.SOUTH) entityIn.setVelocity(-entityIn.motionX*1.04F, entityIn.motionY*1.02F, 1.3F);
		if(f == EnumFacing.NORTH) entityIn.setVelocity(-entityIn.motionX*1.04F, entityIn.motionY*1.02F, -1.3F);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.linkedCode = compound.getInteger("code");
		this.hasCodeLinked = compound.getBoolean("hasCode");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setInteger("code", this.linkedCode);
		compound.setBoolean("hasCode", this.hasCodeLinked);
		return compound;
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
