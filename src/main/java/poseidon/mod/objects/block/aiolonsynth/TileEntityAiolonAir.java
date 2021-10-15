package poseidon.mod.objects.block.aiolonsynth;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;

public class TileEntityAiolonAir extends TileEntity implements ITickable {

	public int tick;
	
	@Override
	public void update() {
	

			if(tick == 1) otherParticles(world, this.getPos(), "Particles called from TileEntity.");
		
			if(tick == 20) this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
		
		tick++;
		
	}
	
	private void otherParticles(World world, BlockPos pos, String s) {
		//if(!world.isRemote) ParticleUtil.aiolonDone((WorldServer) world, pos,  getValids(this.pos, this.world), s);
	}
	
	/*
	private List<Boolean> getValids(BlockPos pos, World world) {
		double x = pos.getX() - 1.0D; double y = pos.getY() - 4.0D; double z = pos.getZ();
		List<Boolean> c = new ArrayList<Boolean>();
		BlockPos[] possibleConvertables =  new BlockPos[] {
						new BlockPos(x + 1.0D, y, z + 1.0D), new BlockPos(x, y, z + 1.0D),
						new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x + 1.0D, y, z),  
						new BlockPos(x, y, z), new BlockPos(x - 1.0D, y, z),
						new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x, y, z - 1.0D), 
						new BlockPos(x - 1.0D, y, z - 1.0D)};
		
		for(int i = 0; i < possibleConvertables.length; i++) {
			if(world.getBlockState(possibleConvertables[i]) == BlockInit.ELYTRON_CRYSTAL_U.getDefaultState()) {
				c.add(true);
				} else {
				c.add(false);
			}
		}
		
		replace();
		
		return c;
	}
	*/
		
	private void replace() {
		double x = pos.getX() - 1.0D; double y = pos.getY() - 4.0D; double z = pos.getZ();
		List<Boolean> c = new ArrayList<Boolean>();
		BlockPos[] possibleConvertables =  new BlockPos[] {
						new BlockPos(x + 1.0D, y, z + 1.0D), new BlockPos(x, y, z + 1.0D),
						new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x + 1.0D, y, z),  
						new BlockPos(x, y, z), new BlockPos(x - 1.0D, y, z),
						new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x, y, z - 1.0D), 
						new BlockPos(x - 1.0D, y, z - 1.0D)};
		for(int i = 0; i < possibleConvertables.length; i++) {
//			if(world.getBlockState(possibleConvertables[i]) == BlockInit.ELYTRON_CRYSTAL_U.getDefaultState()) {
//				world.setBlockState(possibleConvertables[i], BlockInit.ELYTRON_MINERAL.getDefaultState());
//			}
		}
	}
	
}
