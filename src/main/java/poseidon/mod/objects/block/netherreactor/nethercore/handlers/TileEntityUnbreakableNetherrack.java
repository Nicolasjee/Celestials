package poseidon.mod.objects.block.netherreactor.nethercore.handlers;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;

public class TileEntityUnbreakableNetherrack extends TileEntity implements ITickable {

	int function = 0;
	int tick = 0;
	boolean init = false;
	boolean hasTimer = false;
	int exist = 0;
	public boolean setNoBaseBlock = true;
	
	private void  figure() {
		 if(tick > 90)  if(!world.isRemote) ParticleUtil.figure((WorldServer)world, pos, tick, -1);
	        if(tick < 90)  if(!world.isRemote) ParticleUtil.figure((WorldServer)world, pos, tick, 1);
	        
	        tick += 9;
	        if(tick >= 180) tick =0;
	}
	
	@Override
	public void update() {
		
		if(function == 14) {
			
		}
				
		//decay
		if(function == 1 && !world.isRemote) {
			
			if(!hasTimer) getTimer(250, 750);
			
			BlockPos[] surr = new BlockPos[] {pos.east(), pos.north(), pos.south(), pos.west()};
			int rand = getRand(surr.length);
			BlockPos selected = surr[rand-1];
			
			if(world.getTileEntity(selected) instanceof TileEntityUnbreakableNetherrack) {
				TileEntityUnbreakableNetherrack s = (TileEntityUnbreakableNetherrack) world.getTileEntity(selected);
				s.function = 1;
			}
			
			if(exist == tick && world.getBlockState(pos.down()) != BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState()) world.setBlockState(pos, world.getBlockState(pos.down()));
			
			tick++;
		}
		
		//immediate decay.
		if(function == 11 && !world.isRemote) {
			if(!hasTimer) getTimer(50, 120);
			if(exist == tick) world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
			tick++;
		}
		
		//Destroy above blocks
		if(function == 0 && !world.isRemote) {
			
			IBlockState block = world.getBlockState(this.pos.up(1));
			IBlockState blockUpTwo = world.getBlockState(this.pos.up(2));
			boolean aboveClear = !(blockUpTwo.getBlock().getMaterial(blockUpTwo) == Material.LAVA || blockUpTwo.getBlock() == BlockInit.UNBREAKABLE_NETHERRACK || blockUpTwo.getBlock() == Blocks.AIR || blockUpTwo.getBlock() == BlockInit.ITEM_AIR || blockUpTwo.getBlock() == Blocks.NETHER_WART_BLOCK || blockUpTwo.getBlock() == Blocks.NETHER_BRICK || blockUpTwo.getBlock() == BlockInit.NETHER_CRATE);
			boolean flag = !(block.getBlock().getMaterial(block) == Material.LAVA || block.getBlock() == BlockInit.UNBREAKABLE_NETHERRACK || block.getBlock() == Blocks.AIR || block.getBlock() == BlockInit.ITEM_AIR || block.getBlock() == Blocks.NETHER_WART_BLOCK || block.getBlock() == Blocks.NETHER_BRICK || block.getBlock() == BlockInit.NETHER_CRATE);
			
			if(flag) world.destroyBlock(pos.up(), false);
						
		}
		
	}
	
	private void getTimer(int min, int max) {
		Random rn = new Random();
		int maximum = max;
		int minimum = min;
		int range = maximum - minimum + 1;		
		int b = rn.nextInt(range) + minimum;
		exist = b;
		hasTimer = true;
		System.out.println("Set timer to: " + b);
	}
	
	private int getRand(int length) {
		Random rn = new Random();
		int maximum = 4;
		int minimum = 1;
		int range = maximum - minimum + 1;		
		int b = rn.nextInt(range) + minimum;
		return b;
	}
	
}
