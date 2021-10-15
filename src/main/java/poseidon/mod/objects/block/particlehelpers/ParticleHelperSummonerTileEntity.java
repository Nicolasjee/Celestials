package poseidon.mod.objects.block.particlehelpers;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;

public class ParticleHelperSummonerTileEntity extends TileEntity implements ITickable {

	int t;
	int change;
	
	
	@Override
	public Block getBlockType() {
		return BlockInit.PARTICLE_HELPER;
	}
	
	
	
	@Override
	public void update() {
		
		if(t == 0) if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 1);
		if(t == 10) {
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 2);
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX() - 2.0D, pos.getY() - 1.0D, pos.getZ()), 5); //high
		}
		if(t == 20) {
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 3);
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ() + 2.0D), 6); //high of before
		}
		if(t == 30) {
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 4);
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 1); //refresh first
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX() + 2.0D, pos.getY() - 1.0D, pos.getZ()), 7); //high
		}
		
		if(t == 40) {
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 2); //refresh second
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX() - 2.0D, pos.getY() - 1.0D, pos.getZ()), 5); //refresh first + 
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ() - 2.0D), 8);
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 3); //refresh third
			if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 4); //refresh fourth
		}
		
		if(t > 40) {
			if(change < 10) {
				change++;
			}
			if(change == 10) {
				if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 1);
				if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 3);
				if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ() + 2.0D), 6);
				if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 2);
				if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX() - 2.0D, pos.getY() - 1.0D, pos.getZ()), 5);
				if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 2.0D, pos.getZ()), 4);
				if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX() + 2.0D, pos.getY() - 1.0D, pos.getZ()), 7);
				if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ() - 2.0D), 8);
				change = 0;
				
				if(t == 110) if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX() + 2.0D, pos.getY() - 1.0D, pos.getZ()), 5);
				
				if(t > 115) {
					if(!world.isRemote) ParticleUtil.ground((WorldServer) (WorldServer) this.world, new BlockPos(pos.getX() + 2.0D, pos.getY() - 1.0D, pos.getZ()), 5);
					if(!world.isRemote) ParticleUtil.ground((WorldServer) (WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ() - 2.0D), 6);
					if(!world.isRemote) ParticleUtil.ground((WorldServer)  (WorldServer) this.world, new BlockPos(pos.getX() - 2.0D, pos.getY() - 1.0D, pos.getZ()), 7);
					if(!world.isRemote) ParticleUtil.ground((WorldServer) (WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ() + 2.0D), 8);
				}
				
				if(t > 180) {
					if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 1);
					if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 4);
					if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 2);
					if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 3);
				}
			}
		}
		
		if(t == 100) if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX() + 2.0D, pos.getY() - 1.0D, pos.getZ()), 5);
		if(t == 105) if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ() - 2.0D), 6);
		if(t == 110) if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX() - 2.0D, pos.getY() - 1.0D, pos.getZ()), 7);
		if(t == 115) if(!world.isRemote) ParticleUtil.ground((WorldServer) this.world, new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ() + 2.0D), 8);
		
		
		if(t == 180) {
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 1);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 4);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 2);
			if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 3);
		}
		
		if(t == 220) {
			this.world.setBlockToAir(this.pos);
			
		}
		
		t++;
	}
	
}
