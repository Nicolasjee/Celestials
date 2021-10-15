package poseidon.mod.objects.block.particlehelpers;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.summoner.extended.TileEntitySummoner;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class TileEntityParticle extends TileEntity implements ITickable{

	public int y, x, z;
	int tick, tick2;
	int change = 0; double c2 = 0;
	int i = 1;
	boolean initialized = false;
	int t = 10;
	
	@Override
	public Block getBlockType() {
		return BlockInit.PARTICLE_HELPER;
	}
	
	
	
	@Override
	public void update() {

		BlockPos pos = this.pos.down();
		
		if(tick == 0) Main.proxy.playSoundBlock(pos, world, SoundsHandler.OPENING, 1.0F, 1.0F);
		
		if(tick == 25) {
			
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 1);
		}
		if(tick == 60 + t) if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 4);
		
		if(tick == 30 + t) if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 3);
		if(tick == 35 + t) if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 4);
		
		if(tick == 55 + t) {
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() - 4.0D), pos, (WorldServer) world, 2);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 1);
		}
		if(tick == 45 + t) {
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() - 4.0D), pos, (WorldServer) world, 3);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 3);
		}
		
		if(tick == 50 + t) {
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 1);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 1);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 4);
		}
		if(tick == 55 + t) {
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 2);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 3);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() - 4.0D), pos, (WorldServer) world, 2);
		}

		if(tick > 60) {
			change++;
			if(change > 15) {
				change = 0;
				previous(1);
			}
		}
		
		if(tick == 70) {
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 1);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 4);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 2);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 3);
		}
		
		if(tick > 70) {
			c2++;
			if(c2 > 30) {
				c2 = 0;
				previous(2);
			}
		}
		
		if(tick == 90) {
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 5);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 6);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() - 4.0D), pos, (WorldServer) world, 7);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 8);
		}

		if(tick == 120) {
			//Main.proxy.playSoundBlock(pos, (WorldServer) world, SoundsHandler.MARAUDER, 1.0F, 1.0F);
			
			if(world.getTileEntity(pos) instanceof TileEntitySummoner) {
				TileEntitySummoner tile = (TileEntitySummoner) world.getTileEntity(pos);
				ItemStack upgrade = new ItemStack(ItemInit.UPGRADE_MODULE);
				
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString("upgradePower", Utilities.getRandomPower());
				upgrade.setTagCompound(nbt);
	
				tile.setSlot(1, upgrade);
			}
			
			this.world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
		}
		
		tick++;
	}
	

	private void previous(int i) {
		BlockPos pos = this.pos.down();
		if(i == 1) {
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 1);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 4);
			
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 3);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 4);
			
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() - 4.0D), pos, (WorldServer) world, 2);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() - 4.0D), pos, (WorldServer) world, 3);
			
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 1);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 2);
	
		}
		
		if(i == 2) {
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 1);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 4);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 2);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 3);
		}
		
		if(i == 3) {
			
		}
	}
	
}
