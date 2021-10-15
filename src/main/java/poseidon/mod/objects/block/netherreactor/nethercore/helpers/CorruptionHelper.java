package poseidon.mod.objects.block.netherreactor.nethercore.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;

public class CorruptionHelper {

	public static void setCorruption(List<BlockPos> corruption, WorldServer server, World world, BlockPos core, boolean done) {
		
		//Preserveds: blocks that cannot be lava
		List<BlockPos> preserveds = NetherReactorStructure.preserveds(core, done);
		
		//Corruption cannot choose handler blocks: removing them if selected
		for(int i = 0; i < 5; i++) {
			if(corruption.contains(core.down(i))) corruption.remove(core.down(i));
		}
		
		for(int i = 0; i < corruption.size(); i++) {
			
			List<Entity> list = Utilities.getListChangable(world, corruption.get(i), EntityPlayer.class);
			
			
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j) instanceof EntityPlayer) {
					world.setBlockState(corruption.get(i), getCorruptionBlock(true).getDefaultState());
					
				}
				else if(!(list.get(j) instanceof EntityPlayer) || list.isEmpty()) {
					Block getPlaced = getCorruptionBlock(false);

					if(getPlaced.getDefaultState().getMaterial() == Material.LAVA) {
						world.setBlockState(corruption.get(i), getPlaced.getDefaultState());
						world.setBlockState(corruption.get(i).down(), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
					} else world.setBlockState(corruption.get(i), getPlaced.getDefaultState());
					
					
				}
			}
			
			for(int k = 0; k < preserveds.size(); k++) {
				if(world.getBlockState(preserveds.get(k)) != BlockInit.UNBREAKABLE_NETHERRACK) world.setBlockState(preserveds.get(k), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
			}
			
			if(list.isEmpty()) {
				Block getPlaced = getCorruptionBlock(false);

				if(getPlaced.getDefaultState().getMaterial() == Material.LAVA) {
					world.setBlockState(corruption.get(i), getPlaced.getDefaultState());
					world.setBlockState(corruption.get(i).down(), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
				} else  world.setBlockState(corruption.get(i), getPlaced.getDefaultState());
				
			}
			
			//server.setBlockState(corruption.get(i), Blocks.NETHERRACK.getDefaultState());
			ParticleUtil.setCorruption(server, corruption.get(i));
			//ParticleUtil.blockConnect(server, corruption.get(i), core, 0.5D, 0.5D, 0.5D, 0.5D, 1.0D, 0.5D);
			
		}
	}
	
	//Called from the main handler. Making sure the item air blocks have a solid block underneath
	public static void securePreservedSpaces(BlockPos core, WorldServer world, boolean done) {
		List<BlockPos> preserveds = NetherReactorStructure.preserveds(core, done);
		for(int k = 0; k < preserveds.size(); k++) {
			if(world.getBlockState(preserveds.get(k)) != BlockInit.UNBREAKABLE_NETHERRACK) world.setBlockState(preserveds.get(k), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
		}
	}
	
	public static Block getCorruptionBlock(boolean isPlayerAbove) {
    	Random rn = new Random();
		int maximum = 100;
		int minimum = 1;
		int range = maximum - minimum + 1;		
		int b = rn.nextInt(range) + minimum;
		if(isPlayerAbove) return BlockInit.UNBREAKABLE_NETHERRACK;
		if(b >= 15 && !isPlayerAbove) return BlockInit.UNBREAKABLE_NETHERRACK;
		else if(b < 15 && !isPlayerAbove) return Blocks.LAVA;
		return BlockInit.UNBREAKABLE_NETHERRACK;
	}
	
	public static List<BlockPos> getCorruptionList(CorruptionType type, BlockPos usedPos, World world) {
		
		List<BlockPos> back = new ArrayList<BlockPos>();
		
		if(type == CorruptionType.GROUND) {
			BlockPos core = usedPos.up();
			Iterable<BlockPos> it = usedPos.up().getAllInBox(usedPos.up().down(2).south(14).west(14), usedPos.up().down(2).north(14).east(14));
			List<BlockPos> list = new ArrayList<BlockPos>();//List to add (it) iterable to
			List<BlockPos> randomizedList = new ArrayList<BlockPos>(); //copy of list, except the blocks are randomized place
			List<BlockPos> strippedList = stripCorruption(usedPos); //a list that reaches from 14-20 outside. stripped from some blocks
			
			for(BlockPos pos: it) {
				if(!setCorePositions(usedPos).contains(pos)) list.add(pos);
			}
			
			list.addAll(strippedList);
			
			while(list.size() > 0) {
				Random rn = new Random();
				int maximum = list.size();
				int minimum = 0;
				int range = maximum - minimum;		
				int b = rn.nextInt(range) + minimum;
				randomizedList.add(list.get(b));
				list.remove(list.get(b));
			}
			
			return randomizedList;
		}
		
		if(type == CorruptionType.WALLRANDOM && !world.isRemote) {
			
			return getRandomWall(usedPos, (WorldServer) world);
			
		}
		
		return back;
		
	}
	
	private static List<BlockPos> setCorePositions(BlockPos usedPos) {
		BlockPos core = usedPos.up();
		List<BlockPos> list = new ArrayList<BlockPos>();
		BlockPos[] corePositions = new BlockPos[] {core.south().east().down(2), core.south().west().down(2), core.north().east().down(2), core.north().west().down(2), core.down(2),
				                                   core.south(5).east(5).down(2), core.south(5).west(5).down(2), core.north(5).east(5).down(2), core.north(5).west(5).down(2)};
		for(int i = 0; i < corePositions.length; i++) {
			list.add(corePositions[i]);
		}
		return list;
	}
	
	private static List<BlockPos> stripCorruption(BlockPos usedPos) {
		
		Iterable<BlockPos> listCopy = usedPos.up().getAllInBox(usedPos.up().down(2).south(20).west(20), usedPos.up().down(2).north(20).east(20));
		Iterable<BlockPos> listMinus = usedPos.up().getAllInBox(usedPos.up().down(2).south(14).west(14), usedPos.up().down(2).north(14).east(14));
		
		List<BlockPos> listCopyList = new ArrayList<BlockPos>();
		List<BlockPos> listMinusList = new ArrayList<BlockPos>();
		
		for(BlockPos p : listCopy) listCopyList.add(p);
		for(BlockPos p : listMinus) listMinusList.add(p);
		
		//removing listMinus from listCopy
		for(int i = 0; i < listMinusList.size(); i++) {
			if(listCopyList.contains(listMinusList.get(i))) listCopyList.remove(listMinusList.get(i));
			//if big list has a block from listMinus ==> remove listMinus.get   from  ListCopy
		}

		//now removing some blocks from listCopyList
		for(int i = 0; i < listCopyList.size(); i++) {
			if(!getTrueOrFalse()) listCopyList.remove(i); 
		}
		
		return listCopyList;
	}
	
	private static int getRandom(int size) {
		Random rn = new Random();
		int maximum = size;
		int minimum = 0;
		int range = maximum - minimum;		
		int b = rn.nextInt(range) + minimum;
		return b;
	}
	
	private static boolean getTrueOrFalse() {
		Random rn = new Random();
		int maximum = 100;
		int minimum = 0;
		int range = maximum - minimum;		
		int b = rn.nextInt(range) + minimum;
		
		if(b < 80) return false;
		else return true;
	}

	private static List<BlockPos> remove(List<BlockPos> selectionList, BlockPos used, int x, int z) {
		
		for(int i = 0; i < selectionList.size(); i++) {
			BlockPos pos = selectionList.get(i);
			if((pos.getX() != used.getX() + x && pos.getZ() != used.getZ() + z)) selectionList.remove(i);
		}
		
		return selectionList;
	}
	
	private static List<BlockPos> getRandomWall(BlockPos pos, WorldServer server) {
		
		List<BlockPos> randomWall = new ArrayList<BlockPos>();
		
		//Selection of blocks we're working with
		Iterable<BlockPos> selection = pos.getAllInBox(pos.south(16).west(16), pos.north(16).east(16));
		List<BlockPos> selectionList = new ArrayList<BlockPos>();
		for(BlockPos p : selection) selectionList.add(p);
		
		//Dividing them in three parts.
		List<BlockPos> outer = remove(selectionList, pos, 13, 13);
		List<BlockPos> middle = remove(selectionList, pos, 12, 12);
		List<BlockPos> inner = remove(selectionList, pos, 11, 11);
		
		for(int i = 0; i < outer.size(); i++) ParticleUtil.highlightBlock(server, outer.get(i), 0.2D, EnumParticleTypes.END_ROD);
		for(int i = 0; i < middle.size(); i++) ParticleUtil.highlightBlock(server, middle.get(i), 0.2D, EnumParticleTypes.FLAME);
		for(int i = 0; i < inner.size(); i++) ParticleUtil.highlightBlock(server, inner.get(i), 0.2D, EnumParticleTypes.FIREWORKS_SPARK);
		
		
		
		return randomWall;
	
	}
	
}
