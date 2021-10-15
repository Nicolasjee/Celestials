package poseidon.mod.objects.block.aiolonsynth;

import static poseidon.mod.util.helpers.TileEntityHelper.toNBT;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

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
import poseidon.mod.objects.block.redstonereceiver.TileEntityRedstoneReceiver;
import poseidon.mod.util.ParticleUtil;

public class TileEntityAiolonSynth extends TileEntity implements ITickable {
	
	public int progress, power, tick, necessaryPower;
	public boolean active = false;
	public boolean transfer = false;
	public boolean free = false;
	final int price = 18; 
	public int speed;
	
	@Override
	public void update() {
		
		BlockPos pos = this.pos;
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		List<BlockPos> ores = getConvertables(pos, this.world);
		free = freeArea(this.world, pos);
		necessaryPower = ores.size() * price;
		speed = ores.size();
		
		if(!active && tick == 20 && hasRedstoneReceiver()) {
			if(!world.isRemote) ParticleUtil.inactive((WorldServer)world, pos);
			//System.out.println("Active: " + active + ", free: " + free + ", hasre: " + hasRedstoneReceiver());
		}
		
		if(tick == 50 && active && free) {
			System.out.println("1");
			if(hasRedstoneReceiver()) {

				TileEntity s = this.world.getTileEntity(pos.up());
				//System.out.println("2: " + s + ", " + (s instanceof TileEntityRedstoneReceiver));
				
				if(s instanceof TileEntityRedstoneReceiver) {
					
					TileEntityRedstoneReceiver t = (TileEntityRedstoneReceiver) s;
					//System.out.println("3");
					if(t.power >= speed && this.power <= necessaryPower) {
						
						if(!world.isRemote) t.power -= 1 * speed;
						this.power += 1 * speed;
						this.transfer = true;
						sendUpdates();
						this.tick = 0;
						//System.out.println("(speed:" + speed + ") - trans: " + this.power+ " to : " + this.necessaryPower);
						edgeParticles(this.world, pos.down(), "am: " + Double.toString((double)(down(ores.size()) * this.power)), (down(ores.size()) * this.power));
						//edgeParticles(this.world, pos.down(), "mid");
					}
					
					
				}
				
			}
		}
		
//		if(tick == 49 && free && active && hasRedstoneReceiver()) edgeParticles(this.world, pos.down(), "am: " + Double.toString((double)(down(ores.size()) * this.power)), (down(ores.size()) * this.power));

		
		
		if(tick > 141) {
			tick = 0;
		}
		
		if(this.power >= necessaryPower && tick == 100 && free && active) {
			
			System.out.println("stp: " + this.power);
			this.power = power - necessaryPower;
			sendUpdates();//without these all "done" in ClientProxy were executed. But it executed every 50 ticks.
			//this.world.setBlockState(new BlockPos(this.getPos().getX() + 1.0D, this.getPos().getY(), this.getPos().getZ()), BlockInit.AIOLON_AIR.getDefaultState());
			Main.proxy.playSoundBlock(pos, world, SoundEvents.BLOCK_CHORUS_FLOWER_GROW, 1.0F, 1.0F);

			
		}

		
		tick++;
		
	}

	
	private boolean hasRedstoneReceiver() {
		//if(this.world.getBlockState(this.pos.up()) == BlockInit.REDSTONE_RECEIVER.getDefaultState()) return true;
		 return false;
	}
	
	private void edgeParticles(World world, BlockPos pos, String s, double am) {
		if(!world.isRemote) ParticleUtil.aiolonEdge((WorldServer) world, pos, s, am, world);
		//ParticleUtil.aiolonEdge(pos, s, am, world);
	}
	
	private void otherParticles(World world, BlockPos pos, String s) {
		if(!world.isRemote) ParticleUtil.aiolonDone((WorldServer) world, pos,  getValids(this.pos, this.world), "3");
	}
	
	private List<BlockPos> getConvertables(BlockPos pos, World world) {
		double x = pos.getX(); double y = pos.getY() - 4.0D; double z = pos.getZ();
		List<BlockPos> c = new ArrayList<BlockPos>();
		BlockPos[] possibleConvertables =  new BlockPos[] {new BlockPos(x - 1.0D, y, z), new BlockPos(x + 1.0D, y, z),
							   new BlockPos(x - 1.0D, y, z - 1.0D), new BlockPos(x - 1.0D, y, z + 1.0D),
							   new BlockPos(x + 1.0D, y, z + 1.0D), new BlockPos(x + 1.0D, y, z - 1.0D),
							   new BlockPos(x, y, z - 1.0D), new BlockPos(x, y, z + 1.0D), new BlockPos(x, y, z)};
		
		for(int i = 0; i < possibleConvertables.length; i++) {
//			if(world.getBlockState(possibleConvertables[i]) == BlockInit.ELYTRON_CRYSTAL_U.getDefaultState()) {
//				c.add(possibleConvertables[i]);
//			}
		}
		
		return c;
		
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
			this.power = initValues.getInteger("power");
			this.progress = initValues.getInteger("progress");
			this.active = initValues.getBoolean("active");
			this.tick = 0;
			return;
		}
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
	
	private List<Boolean> getValids(BlockPos pos, World world) {
		double x = pos.getX(); double y = pos.getY() - 4.0D; double z = pos.getZ();
		List<Boolean> c = new ArrayList<Boolean>();
		BlockPos[] possibleConvertables =  new BlockPos[] {
						new BlockPos(x + 1.0D, y, z + 1.0D), new BlockPos(x, y, z + 1.0D),
						new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x + 1.0D, y, z),  
						new BlockPos(x, y, z), new BlockPos(x - 1.0D, y, z),
						new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x, y, z - 1.0D), 
						new BlockPos(x - 1.0D, y, z - 1.0D)};
		
		for(int i = 0; i < possibleConvertables.length; i++) {
//			if(world.getBlockState(possibleConvertables[i]) == BlockInit.ELYTRON_CRYSTAL_U.getDefaultState()) {
//				c.add(true);
//				} else {
//				c.add(false);
//			}
		}
		
		return c;
	}

	private boolean freeArea(World w, BlockPos p) {
		List<BlockPos> a = new ArrayList<BlockPos>();
		int c = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 3; k++) {
					a.add(new BlockPos(p.getX() - 1.0D + (double) i, p.getY() - 3.0D + (double) k, p.getZ() - 1.0D + (double) j));
				}
			}
		}
		
		 a.add(new BlockPos(p.getX() + 1.0D, p.getY(), p.getZ() + 1.0D));
		a.add(new BlockPos(p.getX() - 1.0D, p.getY(), p.getZ())); a.add(new BlockPos(p.getX(), p.getY(), p.getZ() + 1.0D));
		a.add(new BlockPos(p.getX() - 1.0D, p.getY(), p.getZ() + 1.0D)); a.add(new BlockPos(p.getX() + 1.0D, p.getY(), p.getZ() - 1.0D));
		a.add(new BlockPos(p.getX(), p.getY(), p.getZ() - 1.0D)); a.add(new BlockPos(p.getX() - 1.0D, p.getY(), p.getZ() - 1.0D));
		
		for(int b = 0; b < a.size(); b++) {
			if(w.getBlockState(a.get(b)) != Blocks.AIR.getDefaultState()) {
				c++;
			}
		}
		
//		if(w.getBlockState(new BlockPos(p.getX() + 1.0D, p.getY(), p.getZ())) != Blocks.AIR.getDefaultState()
//				&& w.getBlockState(new BlockPos(p.getX() + 1.0D, p.getY(), p.getZ())) != BlockInit.AIOLON_AIR.getDefaultState()) {
//			c++;
//		}
		
		if(c == 0) 
			return true;
		else return false;
	}

	private double down(int size) {
		//System.out.println("down: " + (1.0D / ((double) size * (double) price)) + ", am: " + ((1.0D / ((double) size * (double) price)) * this.price));
		return  1.0D / ((double) size * (double) price);
	}
	
}
