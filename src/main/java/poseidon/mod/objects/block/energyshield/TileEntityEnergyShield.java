package poseidon.mod.objects.block.energyshield;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;

public class TileEntityEnergyShield extends TileEntity implements ITickable {
	
						//   |     |     |     |     
	int[] coords = new int[] {0,0,0,0,0,0,0,0,0};
						//   |     |     |     |     
	int[] occupied = new int[] {0,0,0};
	int[] emitter = new int[] {0,0,0};
	int tick = 0;
	
	@Override
	public void update() {

		
		if(validArrays() && tick == 20 && !this.world.isRemote) {
			
				if(notNull(1) && emitter[0] == 1) ParticleUtil.energyShield((WorldServer)this.world, this.pos, new BlockPos(coords[0], coords[1], coords[2]));
				if(notNull(2) && emitter[1] == 1) ParticleUtil.energyShield((WorldServer)this.world, this.pos, new BlockPos(coords[3], coords[4], coords[5]));
				if(notNull(3) && emitter[2] == 1) ParticleUtil.energyShield((WorldServer)this.world, this.pos, new BlockPos(coords[6], coords[7], coords[8]));
			
			
			tick = 0;
			
		}
		
		tick++;
		
	}
	
	private void checkCoords() {
		if(coordsLegit()) {
			BlockPos check = new BlockPos(coords[0], coords[1], coords[2]);
			if(!(world.getBlockState(check).getBlock() == BlockInit.ENERGY_SHIELD)) {
				coords = new int[] {0,0,0};
			}
		}
	}
	
	private boolean coordsLegit() {
		if(!(coords.length > 0)) {
			try {
				System.out.println("Coords0: " + coords[0]);
			} catch(Exception e) {
				System.out.println("Does not exist?");
			}
		}
		
		return false;
	}
	
	private boolean notNull(int index) {
		List<Boolean> test = new ArrayList<Boolean>();
		
		if(index == 1) {
			if(coords[0] == 0) test.add(false);
			if(coords[1] == 0) test.add(false);
			if(coords[2] == 0) test.add(false);
			else test.add(true);

		}
		
		if(index == 2) {
			if(coords[3] == 0) test.add(false);
			if(coords[4] == 0) test.add(false);
			if(coords[5] == 0) test.add(false);
			else test.add(true);
		}
		
		if(index == 3) {
			if(coords[5] == 0) test.add(false);
			if(coords[7] == 0) test.add(false);
			if(coords[8] == 0) test.add(false);
			else test.add(true);
		}
		
		if(test.contains(false)) return false;
		else return true;
	}
	
	private boolean validArrays() {
		if(coords.length == 9 && occupied.length == 3 && emitter.length == 3) return true;
		else return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.coords = compound.getIntArray("Coordinates");
		this.occupied = compound.getIntArray("Occupied");
		this.emitter = compound.getIntArray("ParticleEmitter");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setIntArray("Coordinates", coords);
		compound.setIntArray("Occupied", this.occupied);
		compound.setIntArray("ParticleEmitter", this.emitter);
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
