package poseidon.mod.objects.block.netherreactor.nethercore.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.netherreactor.TileEntitySpawnerNether;
import poseidon.mod.objects.block.netherreactor.nethercore.handlers.TileEntityNetherCorruption;

public class NetherReactorWallHelper {

	public static List<BlockPos> addToList(List<BlockPos> list1, List<BlockPos> list2) {
		for(int i = 0; i < list1.size(); i++) {
			list2.add(list1.get(i));
		}
		return list2;
	}
	
	public static List<BlockPos> removeFromList(List<BlockPos> minus, List<BlockPos> list) {
		
		List<BlockPos> ret = new ArrayList<BlockPos>();
		
		for(int i = 0; i < list.size(); i++) {
			if(!minus.contains(list.get(i))) ret.add(list.get(i));
		}
		
		return ret;
	}
	
	public static List<BlockPos> structureWalls(BlockPos core, int type, int outWards, boolean checkPositions, int heightStart, int height) {
		
		List<BlockPos> list = new ArrayList<BlockPos>();
		int heightSet = height;
		int heightZero = heightStart;
		
		if(!checkPositions) {
			heightSet = 1;
			heightZero = 0;
		}
		
		if(type == 0) {
		
			Iterable<BlockPos> coords = core.getAllInBox(core.down().north(outWards).east(outWards).up(heightZero), core.up(heightSet).north(outWards).west(outWards));
			Iterable<BlockPos> coords2 = core.getAllInBox(core.down().north(outWards).east(outWards).up(heightZero), core.up(heightSet).south(outWards).east(outWards));
			Iterable<BlockPos> coords3 = core.getAllInBox(core.down().west(outWards).south(outWards).up(heightZero), core.up(heightSet).south(outWards).east(outWards));
			Iterable<BlockPos> coords4 = core.getAllInBox(core.down().west(outWards).south(outWards).up(heightZero), core.up(heightSet).west(outWards).north(outWards));
			
			//Puts them in a list
			for(BlockPos pos : coords) {
				list.add(pos);
			}
			for(BlockPos pos : coords2) {
				list.add(pos);
			}
			for(BlockPos pos : coords3) {
				list.add(pos);
			}
			for(BlockPos pos : coords4) {
				list.add(pos);
			}
			
			return list;
			
		}
		
		
		// inner walls
		if(type == 1) {
			
			//Gets blocks
			//up from first block removed. up in second block set to 1
			//The wall function for the inner walls doesn't need all blocks. -> Works with height adjustment. This method is less lagier
			Iterable<BlockPos> coords = core.getAllInBox(core.down().north(outWards).east(outWards).up(heightZero), core.up(heightSet).north(outWards).west(outWards));
			Iterable<BlockPos> coords2 = core.getAllInBox(core.down().north(outWards).east(outWards).up(heightZero), core.up(heightSet).south(outWards).east(outWards));
			Iterable<BlockPos> coords3 = core.getAllInBox(core.down().west(outWards).south(outWards).up(heightZero), core.up(heightSet).south(outWards).east(outWards));
			Iterable<BlockPos> coords4 = core.getAllInBox(core.down().west(outWards).south(outWards).up(heightZero), core.up(heightSet).west(outWards).north(outWards));
			
			//Puts them in a list
			for(BlockPos pos : coords) {
				list.add(pos);
			}
			for(BlockPos pos : coords2) {
				list.add(pos);
			}
			for(BlockPos pos : coords3) {
				list.add(pos);
			}
			for(BlockPos pos : coords4) {
				list.add(pos);
			}
			
			return list;
			
		}
		
		if(type == 2) {
			
			Iterable<BlockPos> coords = core.getAllInBox(core.down().north(outWards).west(outWards).up(heightSet), core.down().up(heightSet).east(outWards).south(outWards));
			
			for(BlockPos pos : coords) {
				list.add(pos);
			}
			
			return list;
			
		}
		
		//floor
		if(type == 3) {
			
			Iterable<BlockPos> coords = core.getAllInBox(core.down(2).north(outWards).west(outWards), core.down(2).east(outWards).south(outWards));
		
			for(BlockPos pos : coords) {
				list.add(pos);
			}
			
			return list;
			
		}
		
		return list;
		
	}
	
	public static List<BlockPos> getStructureWalls(BlockPos core, int stage, World world, int addSize, int start, int up, int outWards, boolean b) {
		
		List<BlockPos> ret = new ArrayList<BlockPos>();
		List<BlockPos> walls = new ArrayList<BlockPos>();

		if(b) {
			
				Iterable<BlockPos> coords = core.getAllInBox(core.down().north(outWards).east(outWards).up(start), core.up(up).north(outWards).west(outWards));
				Iterable<BlockPos> coords2 = core.getAllInBox(core.down().north(outWards).east(outWards).up(start), core.up(up).south(outWards).east(outWards));
				
				for(BlockPos pos : coords) {
					ret.add(pos);
				}
				for(BlockPos pos : coords2) {
					ret.add(pos);
				}
	
				
				//remove blocks from list with remove function in class?
				
				int loop = ret.size() >= addSize ? addSize : ret.size();
				
				if(ret.isEmpty() && ret.size() >= addSize) {
					return new ArrayList<BlockPos>();
				}
				
	
	
				for(int i = 0; i < loop; i++) {
					walls.add(ret.get(getRandom(ret)));
				}
		}
		
		else {
			
			Iterable<BlockPos> coords = core.getAllInBox(core.down().north(outWards).east(outWards).up(start), core.north(outWards).west(outWards));
			Iterable<BlockPos> coords2 = core.getAllInBox(core.down().north(outWards).east(outWards).up(start), core.south(outWards).east(outWards));
			
			for(BlockPos pos : coords) {
				ret.add(pos);
			}
			for(BlockPos pos : coords2) {
				ret.add(pos);
			}

			
			//remove duplicates with remove function in class?
			
			int loop = ret.size() >= addSize ? addSize : ret.size();
			
			if(ret.isEmpty() && ret.size() >= addSize) {
				return new ArrayList<BlockPos>();
			}
			


			for(int i = 0; i < loop; i++) {
				walls.add(ret.get(getRandom(ret)));
			}
			
		}
			

		return walls;
		
		
	}
	
	public static List<BlockPos> getStructureWalls2(BlockPos core, int stage, World world, int addSize, int start, int up, int outWards, boolean b) {
		
		List<BlockPos> ret = new ArrayList<BlockPos>();
		List<BlockPos> walls = new ArrayList<BlockPos>();		

		if(b) {
			Iterable<BlockPos> coords3 = core.getAllInBox(core.down().west(outWards).south(outWards).up(start), core.up(up).south(outWards).east(outWards));
			Iterable<BlockPos> coords4 = core.getAllInBox(core.down().west(outWards).south(outWards).up(start), core.up(up).west(outWards).north(outWards));
			
			for(BlockPos pos : coords3) {
				ret.add(pos);
			}
			for(BlockPos pos : coords4) {
				ret.add(pos);
			}
			
			//remove unwnateds
			
			int loop = ret.size() >= addSize ? addSize : ret.size();
			
			if(ret.isEmpty() && ret.size() >= addSize) {
				return new ArrayList<BlockPos>();
			}
			

			for(int i = 0; i < loop; i++) walls.add(ret.get(getRandom(ret)));

		}
		
		else {
			
			Iterable<BlockPos> coords3 = core.getAllInBox(core.down().west(outWards).south(outWards).up(start), core.south(outWards).east(outWards));
			Iterable<BlockPos> coords4 = core.getAllInBox(core.down().west(outWards).south(outWards).up(start), core.west(outWards).north(outWards));
			
			for(BlockPos pos : coords3) {
				ret.add(pos);
			}
			for(BlockPos pos : coords4) {
				ret.add(pos);
			}
			
			//remove unwnateds
			
			int loop = ret.size() >= addSize ? addSize : ret.size();
			
			if(ret.isEmpty() && ret.size() >= addSize) {
				return new ArrayList<BlockPos>();
			}
			

			for(int i = 0; i < loop; i++) walls.add(ret.get(getRandom(ret)));
			
			
		}

		return walls;
		
		
	}
	
	public static void setNetherWalls(WorldServer world, List<BlockPos> list) {
		
		for(int i = 0; i < list.size(); i++) {
			world.setBlockState(list.get(i), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
		}
		
	}
	
	public static int getRandom(List<BlockPos> ret) {
		Random rn2 = new Random();
		int maximum2 = ret.size();
		int minimum2 = 0;
		int range2 = maximum2 - minimum2;
		int b2 = rn2.nextInt(range2) + minimum2;
		return b2;
	}
	
	public static List<BlockPos> getRandomWalls(List<BlockPos> walls, int count) {
		List<BlockPos> retWalls = new ArrayList<BlockPos>();
		
		for(int i = 0; i < count; i++) {
			retWalls.add(walls.get(getRandom(walls)));
		}
		
		return retWalls;
	}
	
	public static void execute(World world, TileEntityNetherCorruption n, List<BlockPos> walls, int type, int d) {

		//Adding one by one.
		
		if(type == 0) {
			BlockPos pos = n.getPos();
			
			int count = walls.size() >= 4 ? 4 : walls.size();
			
			List<BlockPos> innerWalls = getRandomWalls(walls, count);
			
			for(int i = 0; i < innerWalls.size(); i++) {
				world.setBlockState(innerWalls.get(i), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
				n.innerSkelet.remove(innerWalls.get(i));
			}
			
		}
		
		if(type == 1) {

			BlockPos pos = n.getPos();
			
			List<BlockPos> innerWalls = getRandomWalls(walls,1);
	
			
			//Looping through all selected blocks.
			//Getting Y and changing accordingly
			for(int i = 0; i < innerWalls.size(); i++) {
				
				BlockPos place = innerWalls.get(i);
				
				double x = place.getX();
				double y = place.getY() + 2.0D;
				double z = place.getZ();
				
				double yCheck = pos.getY();
				double up = 0.0D;
				
				while(world.getBlockState(new BlockPos(x, yCheck + up, z)) == BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState() && up < 10) {
					up = up + 1.0D;
				}
				
				world.setBlockState(new BlockPos(x, yCheck + up, z), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
				
				
			}
		}
		
		//Adding systematically. Big chunks at a time.
		if(type == 2) {
			
			for(int i = 0; i < walls.size(); i++) {
				
				world.setBlockState(walls.get(i).up(d), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
				world.setBlockState(walls.get(i).up(d+1), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
				world.setBlockState(walls.get(i).up(d+2), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
				//world.setBlockState(pos.up(d+1), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
				
			}
			
		}
		
		
	}
	
	public static List<BlockPos> executeRoof(World world, List<BlockPos> list, int type) {
		
		List<BlockPos> place = new ArrayList<BlockPos>();
		
		//Decides how much blocks will be put in the place queue
		//Default is 4. If list does not contain 4 or ore elements, the amount will be set to the list size.
		if(type == 1) {
			int loop = list.size() >= 4 ? 4 : list.size();
			
			for(int i = 0; i < loop; i++) {
				BlockPos add = list.get(getRandom(list));
				world.setBlockState(add, BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
				list.remove(add);
			}
		}
		
		if(type == 2) {
			
			for(int i = 0; i < list.size(); i++) {
				world.setBlockState(list.get(i), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
			}
			
		}
		
		return list;
		
	}
	
	public static List<BlockPos> setInList(Iterable<BlockPos> list) {
		List<BlockPos> ret = new ArrayList<BlockPos>();
		
		for(BlockPos pos : list) {
			ret.add(pos);
		}
		
		return ret;
	}
	
	/*
	public static void randomWallPlacement(World world, TileEntitySpawnerNether n) {
		
		List<BlockPos> minus = n.minus;
		BlockPos pos = n.getPos();
		
		List<BlockPos> netherStructure3 = NetherReactorWallHelper.getStructureWalls(pos.up(), 0, world, minus, 1, 0, 15, 15, true);
		List<BlockPos> netherStructure4 = NetherReactorWallHelper.getStructureWalls2(pos.up(), 0, world, minus, 1, 0, 15, 15, true);
		
		List<BlockPos> collection = new ArrayList<BlockPos>();
		collection.addAll(netherStructure4);
		collection.addAll(netherStructure3);
		
		//Adding all blockposses to other
		minus.addAll(collection);
		n.minus = minus;
		
		for(int i = 0; i < collection.size(); i++) {
			world.setBlockState(collection.get(i), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
		}
		
		
	}
	*/
	
	
}
