package poseidon.mod.objects.block.general.tileHelpers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.helpers.BlockConnectUtil;

public class TileEntitySynchronisor extends TileEntity implements ITickable {

	public boolean activated = false;
	public boolean redstoneActive = false;
	public int tick = 0;
	public int countdown = 0;
	
	@Override
	public void update() {

		//RedstoneActive check
		updateRedstoneActive();
		
		if(redstoneActive && tick % 20 == 0) {
			//update blocks in vicinity
			updateShields();
		}
		
		if(activated && !world.isRemote) {

			highlightValidArea();
			
			//reset
			if(countdown == 100) {
				
				activated = false;
				countdown = 0;
				
			}
			
			countdown++;
		}
		
		if(tick % 40 == 0) {
			sendUpdates();
			tick = 0;
		}
		
		
		tick++;
		
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
	
	public void updateRedstoneActive() {
		redstoneActive = world.isBlockPowered(pos);
	}
	
	public void highlightValidArea() {
		Iterable<BlockPos> shields = pos.getAllInBox(pos.north(10).east(10).up(6), pos.south(10).west(10).down(6));
		List<BlockPos> list = new ArrayList<BlockPos>();
		
		for(BlockPos s : shields) {
			
			if(world.getBlockState(s).getBlock() == BlockInit.SHIELDBLOCK) {
				list.add(s);
			}
			
		}

			
			//highlighting block
			if(tick % 8 == 0) {
				for(int i = 0; i < list.size(); i++) {
					ParticleUtil.highlightBlock((WorldServer)world, list.get(i), 0.2D, EnumParticleTypes.END_ROD);
				}
			}
			
			//NORTH: X++
			//EAST: Z--
			//SOUTH: X--
			//WEST: Z++

			//highlighting area
			BlockConnectUtil.connect(new double[] {
												   pos.north(10).east(10).getX(), pos.north(10).east(10).up(6).getY(), pos.north(10).east(10).getZ() - 1.0D,
												   pos.south(10).east(10).getX(), pos.south(10).east(10).up(6).getY(), pos.south(10).east(10).getZ(),
												   pos.south(10).west(10).getX(), pos.south(10).west(10).up(6).getY(), pos.south(10).west(10).getZ() + 1.0D,
												   pos.north(10).west(10).getX(), pos.north(10).west(10).up(6).getY(), pos.north(10).west(10).getZ(),
												   
												   pos.north(10).east(10).getX(), pos.north(10).east(10).up(6).getY(), pos.north(10).east(10).getZ() - 1.0D,
												   pos.south(10).east(10).getX(), pos.south(10).east(10).up(6).getY(), pos.south(10).east(10).getZ(),
												   pos.south(10).west(10).getX(), pos.south(10).west(10).up(6).getY(), pos.south(10).west(10).getZ() + 1.0D,
												   pos.north(10).west(10).getX(), pos.north(10).west(10).up(6).getY(), pos.north(10).west(10).getZ(),
												   
												   pos.north(10).east(10).getX(), pos.north(10).east(10).up(0).getY(), pos.north(10).east(10).getZ() - 1.0D,
												   pos.south(10).east(10).getX(), pos.south(10).east(10).up(0).getY(), pos.south(10).east(10).getZ(),
												   pos.south(10).west(10).getX(), pos.south(10).west(10).up(0).getY(), pos.south(10).west(10).getZ() + 1.0D,
												   pos.north(10).west(10).getX(), pos.north(10).west(10).up(0).getY(), pos.north(10).west(10).getZ(),
			},
													
								     new double[] {pos.south(10).east(10).getX(), pos.south(10).east(10).up(6).getY(), pos.south(10).east(10).getZ(),
								    		 	   pos.south(10).west(10).getX(), pos.south(10).west(10).up(6).getY(), pos.south(10).west(10).getZ() + 1.0D,
											       pos.north(10).west(10).getX(), pos.north(10).west(10).up(6).getY(), pos.north(10).west(10).getZ(),
											       pos.north(10).east(10).getX(), pos.north(10).east(10).up(6).getY(), pos.north(10).east(10).getZ() - 1.0D,
											       
											       pos.north(10).east(10).getX(), pos.north(10).east(10).up(0).getY(), pos.north(10).east(10).getZ() - 1.0D,
												   pos.south(10).east(10).getX(), pos.south(10).east(10).up(0).getY(), pos.south(10).east(10).getZ(),
												   pos.south(10).west(10).getX(), pos.south(10).west(10).up(0).getY(), pos.south(10).west(10).getZ() + 1.0D,
												   pos.north(10).west(10).getX(), pos.north(10).west(10).up(0).getY(), pos.north(10).west(10).getZ(),
												   
												   pos.south(10).east(10).getX(), pos.south(10).east(10).up(0).getY(), pos.south(10).east(10).getZ(),
												   pos.south(10).west(10).getX(), pos.south(10).west(10).up(0).getY(), pos.south(10).west(10).getZ() + 1.0D,
												   pos.north(10).west(10).getX(), pos.north(10).west(10).up(0).getY(), pos.north(10).west(10).getZ(),
												   pos.north(10).east(10).getX(), pos.north(10).east(10).up(0).getY(), pos.north(10).east(10).getZ() - 1.0D,
												   
												   
								    		       }, 
								     (WorldServer)world, EnumParticleTypes.END_ROD, 80);
		
		
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
