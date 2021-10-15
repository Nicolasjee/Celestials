package poseidon.mod.objects.block.netherreactor.nethercore.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.CorruptionHelper;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.CorruptionType;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorHelper;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorStructure;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorWallHelper;
import poseidon.mod.util.ParticleUtil;

public class TileEntityNetherCorruption extends TileEntity implements ITickable {

	int tick = 0;
	
	int innerwallBuildTick = 0;
	int innerwallBuildTickAdd = 0;
	
	int tick1 = 0;
	int slowTick = 0;
	int tick1Add = 0;
	int corruptionSpreaded = 0;
	int amountCorruption = 0;
	BlockPos usedPos = this.pos;
	List<BlockPos> list = new ArrayList<BlockPos>();
	boolean init = false;
	
	int posx;
	int posy;
	int posz;
	
	int walls = 0;
	int wallsAdded = 0;
	int d = 0;
	
	boolean corruptionAdd = false;
	boolean wallAdd = false;
	boolean wallInit = false;
	boolean wallTwoStart = false;
	boolean wallTwoRoofFloor = false;
	
	public boolean wall1ready = false;
	public static boolean b = false;
	
	boolean disable = false;
	
	int wallCheck = 400;
	int delay = 200;
	
	public List<BlockPos> corePositions = new ArrayList<BlockPos>();
	public List<BlockPos> minus = new ArrayList<BlockPos>();
	public List<BlockPos> innerWalls = new ArrayList<BlockPos>();
	public List<BlockPos> innerWallHeightCheck = new ArrayList<BlockPos>();
	public List<BlockPos> innerSkelet = new ArrayList<BlockPos>();
	public List<BlockPos> innerRoof1 = new ArrayList<BlockPos>();
	public List<BlockPos> outerWalls = new ArrayList<BlockPos>();
	public List<BlockPos> outerRoof1 = new ArrayList<BlockPos>();
	public List<BlockPos> outerRoof2 = new ArrayList<BlockPos>();
	public List<BlockPos> outerFloor = new ArrayList<BlockPos>();
	public List<BlockPos> outerWallsSpread = new ArrayList<BlockPos>();
	
	public List<BlockPos> groundCorruption = new ArrayList<BlockPos>();
	public List<BlockPos> surroundCorruption = new ArrayList<BlockPos>();
	public List<BlockPos> wallBuild = new ArrayList<BlockPos>();

	@Override
	public void update() {

		if(!init) init();
		shouldDestroy();
		if(!wallInit) initWalls();
		
		if(tick >= wallCheck && !world.isRemote && wallAdd) {
			if(innerWalls.size() == 0) {
				wall1ready = true;
			}
		}
		

		if(tick == corruptionSpreaded * 3  && corruptionAdd) {
			
			//Debug
			
			int loop = groundCorruption.size() > 5 ? 5 : groundCorruption.size();
			sendUpdates();
			List<BlockPos> setPos = new ArrayList<BlockPos>();
			for(int i = 0; i < loop; i++) {
				try {
					setPos.add(groundCorruption.get(i));
					groundCorruption.remove(i);
					} catch(Exception e) {
					fillEmpties(groundCorruption);
					corruptionAdd = false;
					groundCorruption.clear();
				}
			}
			
			if(!world.isRemote) CorruptionHelper.setCorruption(setPos, (WorldServer)world, world, usedPos.up(), false);
			
			corruptionSpreaded++;
			
			if(groundCorruption.isEmpty()) {
				corruptionAdd = false;
				fillEmpties(groundCorruption);
				tick1Add = 1;
			}
			
		}
		

		
		/*
		 
		 
		if(tick1 % delay == 0 && !world.isRemote && wallTwoStart && d < 13*3) {
			if(tick1 == 840 && !world.isRemote && !wallTwoRoofFloor) setRoofAndFloor();
			NetherReactorWallHelper.execute((WorldServer)world, this, outerWalls, 2, d);
			d += 4;
			if(tick1 % 100 == 0 && tick < 400) delay -=10;
		}
		
		//Innerwalls build
		if(innerwallBuildTick == wallsAdded * 4 && !world.isRemote && wallAdd) {
			NetherReactorWallHelper.execute((WorldServer)world, this, innerSkelet, 0, 0);
			//NetherReactorWallHelper.randomWallPlacement(world, this);
			wallsAdded++;
		}
		
		//First roof to be placed
		if(tick >= 600 && !world.isRemote && innerRoof1.size() > 0) {
			innerRoof1 = NetherReactorWallHelper.executeRoof((WorldServer)world, innerRoof1, 1);
		}
		
		if(!wallAdd && !corruptionAdd) disable = true;
		*/
		
		tick++;
		slowTick++;
		tick1 += tick1Add;
		
	}
	
	private void removeBlocks(List<BlockPos> randomCorruptionSpread) {
		//Checks if getConvertablesCorruption has any blocks that randomCorruptionSpread also has.
		//If there is a match, getConvertablesCorruption will remove that match from his list.
		//With this mechanism, we will decrease our getConvertablesCorruption list as the corruptionList (list) raises in amount of posses.
		for(int i = 0; i < randomCorruptionSpread.size(); i++) {
			if(groundCorruption.contains(randomCorruptionSpread.get(i))) groundCorruption.remove(randomCorruptionSpread.get(i));
		}
	}
	
	/**
	 * Presets all walls
	 */
	private void initWalls() {
		
		groundCorruption = CorruptionHelper.getCorruptionList(CorruptionType.GROUND, usedPos, world);
		surroundCorruption = CorruptionHelper.getCorruptionList(CorruptionType.SURROUND, usedPos, world);
		wallBuild = CorruptionHelper.getCorruptionList(CorruptionType.WALLRANDOM, usedPos, world);
		//sgetConvertablesCorruptionPlus;
		
		/*
		innerWalls = NetherReactorWallHelper.removeFromList(NetherReactorWallHelper.structureWalls(usedPos.up(), 0, 8, true, 0, 10), NetherReactorWallHelper.structureWalls(usedPos.up(), 0, 9, true, 0, 10));
		innerRoof1 = NetherReactorWallHelper.structureWalls(usedPos.up(), 2, 9, true, 0, 9);
		innerSkelet = NetherReactorWallHelper.addToList(innerWalls, innerRoof1);
		
		outerWalls = NetherReactorWallHelper.structureWalls(usedPos.up(), 1, 13, false, 0, 0);
		outerRoof1 = NetherReactorWallHelper.removeFromList(innerRoof1, NetherReactorWallHelper.structureWalls(usedPos.up(), 2, 13, true, 0, 9));
		outerFloor = NetherReactorWallHelper.removeFromList(NetherReactorWallHelper.structureWalls(usedPos.up(), 3, 9, false, 0, 0), NetherReactorWallHelper.structureWalls(usedPos.up(), 3, 12, false, 0, 0));
		outerWallsSpread = NetherReactorWallHelper.structureWalls(usedPos.up(), 1, 14, false, 0, 0);
		
		//Down 2 to get the height of the jumppads
		outerRoof2 = NetherReactorWallHelper.setInList(pos.getAllInBox(usedPos.down(2).up(22).north(13).east(13), usedPos.down(2).up(22).south(13).west(13)));
		*/
		wallInit = true;
		wallAdd = true;
		
	}
	
	/**
	 *Sets the variables used throughout the program 
	 */
	private void init() {
		//Used pos = block under activated core
		this.usedPos = this.pos.up(2);
		this.amountCorruption = getAmountCorruption();
		corePositions = setCorePositions();
		init = true;
		corruptionAdd = true;
	}
	
	private List<BlockPos> setCorePositions() {
		BlockPos core = usedPos.up();
		List<BlockPos> list = new ArrayList<BlockPos>();
		BlockPos[] corePositions = new BlockPos[] {core.south().east().down(2), core.south().west().down(2), core.north().east().down(2), core.north().west().down(2), core.down(2),
				                                   core.south(5).east(5).down(2), core.south(5).west(5).down(2), core.north(5).east(5).down(2), core.north(5).west(5).down(2)};
		for(int i = 0; i < corePositions.length; i++) {
			list.add(corePositions[i]);
		}
		return list;
	}
	
	private int getAmountCorruption() {
		Iterable<BlockPos> getConvertables = pos.getAllInBox(pos.down(2).south(8).west(8), pos.down(2).north(8).east(8));
		int amount = 0;
		for(BlockPos pos : getConvertables) {
			amount++;
		}
		return amount;
	}
	
	//Seems like this method returns the corruptionlist in a random order.
	private List<BlockPos> corruptionList() {
		//used pos = nether core activated
		BlockPos core = usedPos.up();
		Iterable<BlockPos> it = usedPos.up().getAllInBox(usedPos.up().down(2).south(8).west(8), usedPos.up().down(2).north(8).east(8));
		List<BlockPos> list = new ArrayList<BlockPos>();
		
		for(BlockPos pos: it) {
			if(!corePositions.contains(pos)) list.add(pos);
		}
		
		return list;
	}
	
	private void checkWalls() {
		
		int check = 0;
		int check2 = 0;
		//innerWallHeightCheck looks for the top blocks the walls should reach. If those blocks are all unbreakable netherrack, the wall function will stop, as the wall is completed.		
		for(int i = 0; i < innerWallHeightCheck.size(); i++) {
			BlockPos state = innerWallHeightCheck.get(i);
			if(world.getBlockState(state.down(4)) == BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState()) check++;
			if(world.getBlockState(state) == BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState()) check2++;
			//if(i + 1 >= innerWallHeightCheck.size()) world.setBlockState(innerWallHeightCheck.get(i), Blocks.COBBLESTONE.getDefaultState());
		}
		
		//Two variables that check for progress.
		//check: checks if the an amount of netherblocks have reached atleast 6 blocks (10 blocks in total build up)
		//check2: checks if the walls have reached 10
		
		if(check2 == innerWallHeightCheck.size()) {
			wallAdd = false;
			System.out.println("Walls repair stopped");
		}
		if(check + 10 >= innerWallHeightCheck.size() && !wallTwoStart) {
			wallTwoStart = true;
			//wallsAdded = 0;
			tick1Add = 1;
			System.out.println("Second wall initiated");
		}
		wallCheck += 50;
	}
	
	private void shouldDestroy() {
		if(world.getBlockState(usedPos.up()) != BlockInit.NETHER_REACTOR_CORE_ACTIVATED.getDefaultState()) world.destroyBlock(pos, false);
	}
	 
	/**
	 * This happens when the outer walls are being processed.
	 */
	private void setRoofAndFloor() {
		System.out.println("Outer floor and roof have been placed.");
		for(int i = 0; i < outerFloor.size(); i++) {
			world.setBlockState(outerFloor.get(i), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
		}
		
		for(int i = 0; i < outerRoof1.size(); i++) {
			world.setBlockState(outerRoof1.get(i), BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
		}
		wallTwoRoofFloor = true;
	}
	
	//Function that fills all remaining spots on the main ground. The nether corruption doesn't fill 2...
	private void fillEmpties(List<BlockPos> getConvertablesCorruption2) {
		
		for(int i = 0; i < getConvertablesCorruption2.size(); i++) {
			world.setBlockState(getConvertablesCorruption2.get(i), Blocks.NETHERRACK.getDefaultState());
		}
		
		Iterable<BlockPos> it = usedPos.up().getAllInBox(usedPos.up().down(2).south(8).west(8), usedPos.up().down(2).north(8).east(8));
		
		for(BlockPos pos: it) {
			if(world.getBlockState(pos) != Blocks.NETHERRACK.getDefaultState() && world.getBlockState(pos).getMaterial() != Material.LAVA) {
				if(!world.isRemote) world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
			}
		}
	}
	
	
	
	
	
	
	private IBlockState getState() {
		return world.getBlockState(pos);
	}
	
	private void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
		markDirty();
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	

}
