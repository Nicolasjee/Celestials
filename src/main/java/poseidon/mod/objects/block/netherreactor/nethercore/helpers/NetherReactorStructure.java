package poseidon.mod.objects.block.netherreactor.nethercore.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.objects.block.netherreactor.TileEntitySpawnerNether;

public class NetherReactorStructure {

	public static BlockPos corruptionManager(BlockPos c) {
		return c.down(3);
	}
	
	/**
	 * @param kind : 1 nether stone
	 * 				 2 cobble
	 */
	public static BlockPos[] underLayer(int kind, BlockPos pos) {
		if(kind == 1) return new BlockPos[] {pos.down().north().east(), pos.down().north().west(), pos.down().south().east(), pos.down().south().west()};
		else if(kind == 2) return new BlockPos[] {pos.down().north(), pos.down().south(), pos.down().east(), pos.down().west()};
		else {
			System.out.println("Underlayer returned empty. Kind was not applicable: " + kind);
			return new BlockPos[] {};
		}
	}
	
	/**
	 * @param kind : 1 nether stone
	 * 				 2 cobble
	 */
	public static BlockPos[] layer(int kind, BlockPos pos) {
		if(kind == 1) return new BlockPos[] {pos.north().east(), pos.north().west(), pos.south().east(), pos.south().west()};
		else if(kind == 2) return new BlockPos[] {};
		else {
			System.out.println("layer returned empty, Kind was not applicable: " + kind);
			return new BlockPos[] {};
		}
	}
	
	public static BlockPos[] upperLayer(int kind, BlockPos pos) {
		if(kind == 1) return new BlockPos[] {pos.north().east().up(), pos.north().west().up(), pos.south().east().up(), pos.south().west().up()};
		else if(kind == 2) return new BlockPos[] {pos.north().up(), pos.south().up(), pos.east().up(), pos.west().up(), pos.up()};
		else {
			System.out.println("upperLayer returned empty. Kind was not applicable: " + kind);
			return new BlockPos[] {};
		}
	}
	
	/**
	 * 
	 * @param pos must be the location of the nether core.
	 * @param charge
	 * @return
	 */
	public static BlockPos[] chargingPillars(BlockPos pos, int charge) {
		if(charge == 1) return new BlockPos[] {pos.north(5).down(), pos.south(5).down(), pos.east(5).down(), pos.west(5).down(), pos.north(5).east(5).down(), pos.north(5).west(5).down(), pos.south(5).west(5).down(), pos.south(5).east(5).down()};
		if(charge == 2) return new BlockPos[] {pos.north(5).east(5), pos.north(5).west(5), pos.south(5).west(5), pos.south(5).east(5)};
		if(charge == 3) return new BlockPos[] {pos.north(5).east(5).up(), pos.south(5).east(5).up(), pos.south(5).west(5).up(), pos.north(5).west(5).up()};
		if(charge == 4) return new BlockPos[] {pos.north(5).down(), pos.east(5).down(), pos.south(5).down(), pos.west(5).down()};
		if(charge == 5) return new BlockPos[] {pos.north(8).east(8).down(2), pos.south(8).east(8).down(2), pos.south(8).west(8).down(2), pos.north(8).west(8).down(2)};
		else {
			System.out.println("charge was not applicable, a empty list has returned: " + charge);
			return new BlockPos[] {};
		}
	}
	
	public static BlockPos[] mobSpawners(BlockPos pos) {
		BlockPos p = pos.down(2); 
		return new BlockPos[] {p.north(5).east(5), p.north(5).west(5), p.south(5).west(5), p.south(5).east(5)};
	}
	
	public static BlockPos[] mobSpawners2(BlockPos pos) {
		BlockPos p = pos.down(2); 
		return new BlockPos[] {p.north(11).east(2), p.north(11).west(2), p.south(11).west(2), p.south(11).east(2),
							   p.north(2).east(11), p.east(11).south(2), p.west(11).north(2), p.west(11).south(2)};
	}
	
	public static BlockPos[] downWardsLava(BlockPos pos) {
		return new BlockPos[] {pos.north(5).east(5).up(), pos.north(5).west(5).up(), pos.south(5).west(5).up(), pos.south(5).east(5).up()};
	}
	
	
	//Update: added Iterable<BlockPos> in constructor to prevent it from loading every time.
	public static List<BlockPos> getCorruption(BlockPos core, List<BlockPos> list2, World world, List<BlockPos> getConvertables) {
		
		//DEBUG: System.out.println("Getcorruption added blocks to list are: " + list2.size());
		List<BlockPos> ret = new ArrayList<BlockPos>();
		
		Random rn = new Random();
		int maximum = getConvertables.size();
		int minimum = 0;
		int range = maximum - minimum;		
		int b = rn.nextInt(range) + minimum;
		ret.add(getConvertables.get(b));
		
		return ret;
		
	}
	
	public static List<BlockPos> preserveds(BlockPos c, boolean done) {

		List<BlockPos> trans = new ArrayList<BlockPos>();
		if(done) {
			trans.add(c.down(2).north());
			trans.add(c.down(2).south());
			trans.add(c.down(2).east());
			trans.add(c.down(2).west());
		}
		trans.add(c.down(2).north(6).east(6));
		trans.add(c.down(2).north(6).west(5));
		trans.add(c.down(2).south(5).east(6));
		trans.add(c.down(2).south(5).west(5));
		
		return trans;
	}
	
	public static List<BlockPos> roof1(BlockPos core) {
		
		Iterable<BlockPos> it = core.getAllInBox(core.south(9).west(9).up(8), core.north(9).east(9).up(8));
		List<BlockPos> list = new ArrayList<BlockPos>();
		
		for(BlockPos t : it) {
			list.add(t);
		}
		
		return list;
		
	}

	
	
	public static void shaper(BlockPos core, World world) {
		
		
		
	}
	
}
