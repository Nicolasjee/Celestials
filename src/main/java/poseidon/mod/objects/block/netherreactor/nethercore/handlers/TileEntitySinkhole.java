package poseidon.mod.objects.block.netherreactor.nethercore.handlers;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.RechteUtil;

public class TileEntitySinkhole extends TileEntity implements ITickable {

	public boolean on = true;
	public boolean off = true;
	public int counter = 1000;
	int lava = 0;
	int tick2 = 0;
	
	@Override
	public void update() {
		


	tick2++;
	
		if(on && off) {
		
			World worldIn = world;	
			double x1 = this.pos.getX();
			double y1 = this.pos.getY();
			double z1 = this.pos.getZ();
			
			List<Entity> playerList = worldIn.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(x1 - 20.0D, y1 - 20.0D, z1 - 20.0D, x1 + 20.0D, y1+ 20.0D, z1 + 20.0D));
	
			for(int i = 0; i < playerList.size(); i++) {
				
				EntityPlayer player = (EntityPlayer) playerList.get(i);
				
				double x2 = player.posX;
				double y2 = player.posY;
				double z2 = player.posZ;							
						
				double m = (y2 - y1) / (x2 - x1);
				double m1 = (z2 - z1) / (x2 - x1);			
				double dX = x2 - x1;
				double k = 0.0D;
				int p = 100;
							
				for(int j = 0; j < p; j++) {

				 world.spawnParticle(EnumParticleTypes.END_ROD, x1+k, y1+m*k, z1+m1*k,0d,0d,0d);		
				 k = j*(dX/p);

				}
								
			}

			

			
		}
			
		/*
		 * world.spawnParticle(EnumParticleTypes.FLAME, x+j, y+m1*j, z+m2*z, 0d, 0d, 0d);
		 */

		
		counter++;
	}
	
	private double teken(double a) {
		
		if(a < 0) return -1.0D;
		if(a > 0) return 1.0D;
		else {
			System.out.println("A == 0");
			return 1.0D;
		}
		
	}
	
	
//	private void spawn() {
//		int eatingParticleCount = 5;
//
//		for (int i = 0; i < eatingParticleCount; ++i) {
//                  Vec3d vec3d = new Vec3d((theEntity.world.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
//                  vec3d = vec3d.rotatePitch(-theEntity.rotationPitch * 0.017453292F);
//                  vec3d = vec3d.rotateYaw(-theEntity.rotationYaw * 0.017453292F);
//                  double d0 = (-theEntity.world.rand.nextFloat()) * 0.6D - 0.3D;
//                  Vec3d vec3d1 = new Vec3d((theEntity.world.rand.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
//                  vec3d1 = vec3d1.rotatePitch(-theEntity.rotationPitch * 0.017453292F);
//                  vec3d1 = vec3d1.rotateYaw(-theEntity.rotationYaw * 0.017453292F);
//                  vec3d1 = vec3d1.addVector(theEntity.posX, theEntity.posY + theEntity.getEyeHeight(), theEntity.posZ);
//
//                  if (stack.getHasSubtypes())
//                  {
//                      theEntity.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z,
//                              Item.getIdFromItem(stack.getItem()), stack.getMetadata());
//                  }
//                  else
//                  {
//                      theEntity.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z,
//                              Item.getIdFromItem(stack.getItem()));
//                  }
//              }
//
//              theEntity.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.5F + 0.5F * theEntity.world.rand.nextInt(2),
//                      (theEntity.world.rand.nextFloat() - theEntity.world.rand.nextFloat()) * 0.2F + 1.0F);
//          }
//      
//  
//	}
	
	//@Override
	public void updates() {
	
		if(counter == 1000) {
		
			if(!world.isRemote) ParticleUtil.kegel(pos.getX() + 0.5D, pos.getY() + 3, pos.getZ() + 0.5D, (WorldServer)world);
			
			counter = 0;
		}
		
		if(lava == 20) {
			if(!world.isRemote) ((WorldServer)world).spawnParticle(EnumParticleTypes.LAVA, true, pos.getX() + 0.5D, pos.getY() + 2, pos.getZ() + 0.5D, 4, 0.0D, 0.0D, 0.0D, 0);
			lava = 0;
		}
		
		counter++;
		lava++;
	}
	
	int tick = 0;
	int speed;
	int depth;
	int topY;
	
	int d = 0;
	
	boolean isSmall;
	boolean init = false;
	
	public void updateOld() {

		if(!init) init();
		
		if(tick % speed == 0 && !world.isRemote) {
			
			if(depth == d) destroy();
			if(isSmall) destroySmall();
			if(!isSmall) {
				destroyBigRandom();
				world.setBlockState(pos.north(5), BlockInit.SINKHOLE.getDefaultState());
				world.destroyBlock(pos, false);
			}
			
			
		}
		
		tick++;
		
	}
	
	private void destroy() {
		world.destroyBlock(pos, false);
	}
	
	private void init() {
		int rand = rand(10, 0);
		
		isSmall = false;
		
		depth = rand(30, 1);
		speed = rand(10, 1);
		
		topY = world.getTopSolidOrLiquidBlock(pos).getY();
		
		init = true;
	}
	
	private int rand(int M, int m) {
		Random rn = new Random();
		int maximum = M;
		int minimum = m;
		int range = maximum - minimum;		
		int b = rn.nextInt(range) + minimum;
		return b;
	}
	
	private void destroyBigRandom() {
		BlockPos temp = new BlockPos(pos.getX(), topY, pos.getZ());
		Iterable<BlockPos> k = temp.getAllInBox(temp.north(2).east(2), temp.south(2).west(2).down(2));

		for(BlockPos e : k) {
			if(rand(10,1) < 4) world.destroyBlock(e, false);
		}
		
		topY -= 1;
		d++;
	}
	
	private void destroyBig() {
		BlockPos temp = new BlockPos(pos.getX(), topY, pos.getZ());
		BlockPos[] destroy = new BlockPos[] {temp.north(), temp.east(), temp.south(), temp.west(),
											 temp.north().east(), temp.north().west(), temp.south().west(), temp.south().east(),
											 temp.north(2), temp.west(2), temp.east(2), temp.south(2),
											 temp.down()};
		
		for(int i = 0; i < destroy.length; i++) {
			world.destroyBlock(destroy[i], false);
		}
		topY -=1;
		d++;
	}
	
	private void destroySmall() {
		world.destroyBlock(new BlockPos(pos.getX(), topY-1, pos.getZ()), false);
		topY -= 1;
		d++;
		System.out.println("Destroyed: " + world.getTopSolidOrLiquidBlock(this.pos));
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
