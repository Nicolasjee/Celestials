package poseidon.mod.objects.block.general.lighthelp;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;

public class TileEntityEnlighten extends TileEntity implements ITickable {
	
	private int tick = 0;
	
	@Override
	public void update() {
		
		Block block = getBlock();
		
		if(tick == 40) this.world.setBlockState(this.pos, block.getDefaultState());
		
		if(tick > 41 || tick < -1) tick = 0;
		
		
		tick++;
	}
	
	private Block getBlock() {
		if(world.getBlockState(pos) == BlockInit.LIGHT1.getDefaultState()) return BlockInit.LIGHT2;
		if(world.getBlockState(pos) == BlockInit.LIGHT2.getDefaultState()) return BlockInit.LIGHT3;
		if(world.getBlockState(pos) == BlockInit.LIGHT3.getDefaultState()) return Blocks.AIR;
		return Blocks.AIR;
	}

}
