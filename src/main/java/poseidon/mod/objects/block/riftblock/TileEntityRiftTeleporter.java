package poseidon.mod.objects.block.riftblock;

import static poseidon.mod.util.helpers.TileEntityHelper.toNBT;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.WorldServer;
import poseidon.mod.util.ParticleUtil;

public class TileEntityRiftTeleporter extends TileEntity implements ITickable {
	
	private int tick = 0;
	private int count = 0;
	public int x = 0;
	public int y = 0;
	public int z = 0;
	int dimension = 0;
	private boolean s = false;
	public boolean init = false;
	public boolean wait = false;
	public boolean send = false;
	public boolean isPowered = false;
	
	@Override
	public void update() {
		
		isPowered = isPowered();
		
		if(!world.isRemote && !wait && isPowered) {
			

			ParticleUtil.riftOpening((WorldServer)world, this.pos.up(), EnumParticleTypes.DRAGON_BREATH);
			/*if(!s) tick = tick +4;
			if(s) tick = tick - 4;*/
			if(tick == 50) sendUpdates();
			
			
		}
		
		if(send) {
			sendUpdates();
			send = false;
		}
		//use this line to create a never lasting opening circle that goes in the sky
		//if(tick == 360) tick = 0;
		if(tick >= 360 && !wait) { // s true
			wait = true;
			tick = 0;
		}
		if(tick <= 1 && !wait) {
			wait = true;
			tick = 0;
		}
		
		if(wait && tick < 30) {
			if(!s) if(!world.isRemote) world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, false, ((double)x) + 0.5D, ((double)y) + 3.6D, ((double)z) + 0.5D, 0.0D, 0.0D, 0.0D);
			if(s) if(!world.isRemote) world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, false, ((double)x) + 0.5D, ((double)y) + 1.0D, ((double)z) + 0.5D, 0.0D, 0.0D, 0.0D);
		}
		
		if(tick == 30 && wait) {
			if(!s) {
				wait = false;
				s = true;
				tick = 360;
				return;
			}
			
			if(s) {
				wait = false;
				s = false;
				tick = 0;
				return;
			}
			
		}
		
		tick++;
	}
	
	private boolean isPowered() {
		if(world.isBlockPowered(pos)) return true;
		else return false;
	}
	

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.x = compound.getInteger("xCoord");
		this.y = compound.getInteger("yCoord");
		this.z = compound.getInteger("zCoord");
		this.dimension = compound.getInteger("dimension");
		this.init = compound.getBoolean("init");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setInteger("xCoord", this.x);
		compound.setInteger("yCoord", this.y);
		compound.setInteger("zCoord", this.z);
		compound.setInteger("dimension", this.dimension);
		compound.setBoolean("init", this.init);
		return compound;
	}
	
	private IBlockState getState() {
		return world.getBlockState(pos);
	}
	
	public void sendUpdates() {
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
