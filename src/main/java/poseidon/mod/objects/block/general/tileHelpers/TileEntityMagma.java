package poseidon.mod.objects.block.general.tileHelpers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import poseidon.mod.init.BlockInit;

public class TileEntityMagma extends TileEntity implements ITickable {
	
	private int tick = 0;
	
	@Override
	public void update() {
		
    	Random rn = new Random();
		int maximum = 10;
		int minimum = 1;
		int range = maximum - minimum + 1;		
		int c = rn.nextInt(range) + minimum + 20;
		
		
		if(tick == 30) {
			
			world.setBlockState(pos, getBlock().getDefaultState());
			tick = 0;
			
		}
		
		tick++;
	}
	
	

	private Block getBlock() {
		Block block = world.getBlockState(pos).getBlock();
		if(block == BlockInit.MAGMA1) return BlockInit.MAGMA2;
		if(block == BlockInit.MAGMA2) return BlockInit.MAGMA3;
		if(block == BlockInit.MAGMA3) return BlockInit.MAGMA4;
		if(block == BlockInit.MAGMA4) return BlockInit.MAGMA5;
		if(block == BlockInit.MAGMA5) return Blocks.LAVA;
		return Blocks.LAVA;
	}
	
}
