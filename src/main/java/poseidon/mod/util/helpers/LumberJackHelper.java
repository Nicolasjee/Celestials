package poseidon.mod.util.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LumberJackHelper {
	
	static IBlockState state = Blocks.LOG.getDefaultState();
	static IBlockState state2 = Blocks.LOG2.getDefaultState();
	
	static boolean repeat = true;
	static List<BlockPos> g = new ArrayList<BlockPos>();

	public static void destroyLogs(World world, BlockPos pos) {
		
		if(!world.isRemote) {
			
			//Log above:
			boolean isTree = isTree(world, pos);
			if(!isTree) return;
			
			List<BlockPos> log = getLog(world, pos);
			BlockPos topLog = getTop(world, pos);
			
			List<BlockPos> list1 = addPos(world, topLog);
			List<BlockPos> list2 = new ArrayList<BlockPos>();
			
			for(int i = 0; i < list1.size(); i++) {
				list2 = addPos(world, list1.get(i));
			}
			
			g.addAll(list1);
			g.addAll(list2);
			g.addAll(log);
			
			for(int i = 0; i < g.size(); i++) {
				world.destroyBlock(g.get(i), false);
			}
			
		}
		
	}
	
	public static BlockPos getTop(World world, BlockPos pos) {
		
		int i = 0;
		
		while(world.getBlockState(pos.up(i)) == state || world.getBlockState(pos.up(i)) == state2) {
			i++;
		}
		
		return pos.up(i);
		
	}
	
	public static List<BlockPos> getLog(World world, BlockPos pos) {
		
		int i = 1;
		List<BlockPos> log = new ArrayList<BlockPos>();
		log.add(pos);
		
		while(world.getBlockState(pos.up(i)) == state || world.getBlockState(pos.up(i)) == state2) {

			log.add(pos.up(i));
			
			i++;
		}
		
		return log;
		
	}
	
	public static List<BlockPos> addPos(World world, BlockPos pos) {
		

		BlockPos[] a = new BlockPos[] {pos.up(), pos.down(), pos.north(), pos.south(), pos.east(), pos.west(),
				 pos.north().east(), pos.north().west(), pos.south().east(), pos.south().west()};
		
		List<BlockPos> newL = new ArrayList<BlockPos>();
		
		for(int i = 0; i < a.length; i++) {
			IBlockState b = world.getBlockState(a[i]);
			if(b == state || b == state2) newL.add(a[i]);
		}

		return newL;
		
	}
	
	public static boolean isTree(World world, BlockPos pos) {
		int i = 1;
		while(world.getBlockState(pos.up(i)) == state || world.getBlockState(pos.up(i)) == state2) {
			System.out.println("(processing: " + i + ")" + world.getBlockState(pos.up(i)));
			i++;
		}
		
		System.out.println("i: " + i + "= " + world.getBlockState(pos.up(i)));
		
		if(world.getBlockState(pos.up(i)).getBlock() == Blocks.LEAVES ||
		   world.getBlockState(pos.up(i)).getBlock() == Blocks.LEAVES2) {
			
			return true;
			
			} else if(world.getBlockState(pos.up(i)) != Blocks.LEAVES.getDefaultState() && world.getBlockState(pos.up(i)) != Blocks.LEAVES2.getDefaultState()) {
				
				return false;
		}
		
			else return false;
	}

	
}
