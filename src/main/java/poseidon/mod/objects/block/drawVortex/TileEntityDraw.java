package poseidon.mod.objects.block.drawVortex;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;
import static java.lang.Math.*;

public class TileEntityDraw extends TileEntity implements ITickable{

	public double p = 1.0D;
	public double i = 0.0D;
	public int mult = 16;
	public double js = ((2*Math.PI) / (360*mult));


	int tick = 1;
	boolean active = false;
	
	double A = 1.0D;
	
	double Aadd = 0.1D/100;
	
	double x = 0.0D;
	double xAdd = (PI*2)/360;
	double hAdd = 0.1D/100;
	
	double h = 0.1D;
	int counter = 0;
	
	double y = this.pos.getY();
	double z = this.pos.getZ();
	
	@Override
	public void update() {
		
		if(!this.world.isRemote && active) {
			
			while(x < Math.PI*20) {
				((WorldServer)world).spawnParticle(EnumParticleTypes.END_ROD, true, pos.getX() + A*sin(x), pos.getY() + h + 1.0D, pos.getZ() + A*cos(x), 1, 0.0D, 0.0D, 0.0D, 0);
				x = counter*xAdd;
				h = counter*hAdd;
				
				if(x % (xAdd * 360) == 0) {
					A += Aadd;
					if(x == Math.PI*10) Aadd = -0.3D;
					if(x == Math.PI*20) {
						reset();
						active = false;
					}
				}
				counter++;
			}
			
		}
		
		//faster--> p should be changed too.
		//faster();
		//rechte();
		//colorVortex();
		
		world.spawnParticle(EnumParticleTypes.END_ROD, (tick*0.01D) + x, y + tick*2*0.01D, z, 0.0D, 0.0D, 0.0D);
		if(tick % 20 == 0) sendUpdates();
		tick++;
		//tick++;
	}
	
	private void rechte() {
		
		world.spawnParticle(EnumParticleTypes.END_ROD, (tick*0.01D) + x, y + tick*2*0.01D, z, 0.0D, 0.0D, 0.0D);
		tick++;
	}
	
	/*
	 * Turn off manual tick in #update()
	 */
	
	double j = (Math.PI*2) / 360;
	
	private void circle() {
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		for(int i = 0; i < 12; i++) {
			
			double verp = (PI/6)*i;
			world.spawnParticle(EnumParticleTypes.END_ROD, p*sin(j*tick + verp) + x, y, p*cos(j*tick + verp) + z, 0.0D, 0.0D, 0.0D);
			
		}
		
		p = p + 0.02D;
		
	}
	
	private void faster() {
		
		if(js*tick < 10*Math.PI) {

			double x = pos.getX() + 0.5D;
			double y = pos.getY();
			double z = pos.getZ() + 0.5D;
				
			for(int k = 0; k < 7; k++) {
				
				for(int i = 0; i < 12; i++) {
					
					double verp = (PI/6)*i;
					world.spawnParticle(EnumParticleTypes.END_ROD, p*sin(js*((tick)) + verp) + x, y, p*cos(js*((tick)) + verp) + z, 0.0D, 0.0D, 0.0D);
					
				}
				
				tick += k;
				p = p + 0.02D;
				
			}

		}
		
	}
	
	private void colorVortex() {
		if(j*tick < 4*Math.PI) {

			
			double x = this.pos.getX() + 0.5D;
			double y = this.pos.getY();
			double z = this.pos.getZ() + 0.5D;
				
			for(int i = 0; i < 12; i++) {
				double verp = (PI/6)*i;
				CustomEndRod rod = new CustomEndRod(world, p*sin(j*(tick) + verp) + x, y, p*cos(j*(tick) + verp) + z,1000, 0, 0, 0, tick*100);
				Main.proxy.particle(world, rod);
			}
			
			p = p + 0.05D;
			
		}
	}
	
	private void vortex() {
		if(!this.world.isRemote && tick == 1 && i < 4*Math.PI) {
			for(int k = 0; k < 4; k++) {
				WorldServer server = (WorldServer)world;
				double x = this.pos.getX() + 0.5D;
				double y = this.pos.getY();
				double z = this.pos.getZ() + 0.5D;
				server.spawnParticle(EnumParticleTypes.END_ROD, true, p*Math.sin(i) + x,					y, 	p*Math.cos(i) + z, 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, p*Math.sin(i + 2*(Math.PI / 3)) + x, 	y, 	p*Math.cos(i + 2*(Math.PI / 3)) + z, 1, 0.0D, 0.0D, 0.0D, 0);
				server.spawnParticle(EnumParticleTypes.END_ROD, true, p*Math.sin(i + 4*(Math.PI / 3)) + x, 	y, 	p*Math.cos(i + 4*(Math.PI / 3)) + z, 1, 0.0D, 0.0D, 0.0D, 0);
				p = p + 0.05D;
				i = j*(tick+k);
			}
		}
	}
	
	private void vortexThickLines() {
		if(j*tick < 4*Math.PI) {

			
			double x = this.pos.getX() + 0.5D;
			double y = this.pos.getY();
			double z = this.pos.getZ() + 0.5D;
				
			for(int k = 0; k < 7; k++) {
				for(int i = 0; i < 12; i++) {
					double verp = (PI/6)*i;
					world.spawnParticle(EnumParticleTypes.END_ROD, p*sin(j*((tick+k)) + verp) + x, y, p*cos(j*((tick+k)) + verp) + z, 0.0D, 0.0D, 0.0D);
				}
			}
			p = p + 0.05D;
			
		}
	}
	
	private void reset() {
		x = 0;
		h = 0.1D;
		A = 0.0D;
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
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);

		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

	}

}
