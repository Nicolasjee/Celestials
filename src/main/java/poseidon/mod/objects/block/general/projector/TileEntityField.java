package poseidon.mod.objects.block.general.projector;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import poseidon.mod.init.BlockInit;

public class TileEntityField extends TileEntity implements ITickable {
	
	private int tick = 0;
	
	@Override
	public void update() {
		
		Block block = getBlock();
		
		if(tick == 40) this.world.setBlockState(this.pos, block.getDefaultState());
		
		tick++;
	}
	
	private Block getBlock() {
		if(world.getBlockState(pos) == BlockInit.FIELD4.getDefaultState()) return BlockInit.FIELD3;
		if(world.getBlockState(pos) == BlockInit.FIELD3.getDefaultState()) return BlockInit.FIELD2;
		if(world.getBlockState(pos) == BlockInit.FIELD2.getDefaultState()) return BlockInit.FIELD1;
		if(world.getBlockState(pos) == BlockInit.FIELD1.getDefaultState()) return Blocks.AIR;
		return Blocks.AIR;
	}

}
