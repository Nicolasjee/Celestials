package poseidon.mod.objects.block.redstonereceiverold;

import static poseidon.mod.util.helpers.NBTHelper.toNBT;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.aiolonsynth.TileEntityAiolonSynth;
import poseidon.mod.util.ParticleUtil;

public class TileEntityRedstoneReceiver extends TileEntity implements ITickable{

	public int x,y,z, tick, power;
	public int count = 1;
	public boolean initialized = false;
	
//	@Override
//	public Block getBlockType() {
//		return BlockInit.REDSTONE_RECEIVER;
//	}
	
	@Override
	public void update() {
		
		BlockPos[] next = getSurroundings(this.getPos());
		
		for(int i = 0; i < next.length; i++) {
			
			if(this.world.getBlockState(next[i]) == Blocks.REDSTONE_BLOCK.getDefaultState()) {
				
				this.world.destroyBlock(next[i], false);
				this.power = this.power + 9;
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

	private void noPower(World world, BlockPos pos) {
		if(!world.isRemote) ParticleUtil.noPower((WorldServer)world, pos);
		Main.proxy.playSoundBlock(pos, world, SoundEvents.BLOCK_DISPENSER_DISPENSE, 2, 0.4F);
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
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setTag("initvalues", toNBT(this));
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagCompound initValues = compound.getCompoundTag("initvalues");
		if(initValues != null) {
			this.x = initValues.getInteger("x");
			this.y = initValues.getInteger("y");
			this.z = initValues.getInteger("z");
			this.power = initValues.getInteger("power");
			this.tick = 0;
			initialized = true;
			return;
		}

	}
}
