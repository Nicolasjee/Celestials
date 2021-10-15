package poseidon.mod.objects.block.netherreactor;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.netherreactor.nethercore.handlers.TileEntityNetherCorruption;
import poseidon.mod.objects.block.netherreactor.nethercore.handlers.TileEntitySpawnerMobsNether;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.CorruptionHelper;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorHelper;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorParticles;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorStructure;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorWallHelper;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;

public class TileEntitySpawnerNether extends TileEntity implements ITickable {

	int tick = 0;
	int realTicks = 0;
	int netherTicks = 0;
	int particleTick = 0;
	int beamRepeat = 0;
	int counter = 0;
	int walls = 0;
	int routine = 0;
	

	int tickAdd = 0;
	int beamAdd = 0;
	//Default 0 counterP
	int counterP = 0;
	int realTicksAdd = 1;
	
	int sum = 0;
	int beamKeep = 0;
	
	boolean init = false;
	List<BlockPos> upperRoof = new ArrayList<BlockPos>();
	
	BlockPos[] f = NetherReactorStructure.chargingPillars(pos.up(), 3); //big
	BlockPos[] t = NetherReactorStructure.chargingPillars(pos.up(), 4); //small
	BlockPos[] g = NetherReactorStructure.chargingPillars(pos.up(), 5);
	
	@Override
	public void update() {
		
		shouldDestroy();
		if(!init) init();
		
		//Setting mob spawners when adding the pillars
		if(realTicks == 720 && !world.isRemote) {
			
			BlockPos[] array2 = NetherReactorStructure.mobSpawners(pos.up());
			
			for(int i = 0; i < array2.length; i++) {
				((WorldServer)world).setBlockState(array2[i], BlockInit.NETHER_MOB_SPAWNER.getDefaultState());
				System.out.println("Hello World");
				if(world.getTileEntity(array2[i]) instanceof TileEntitySpawnerMobsNether) {
					TileEntitySpawnerMobsNether n = (TileEntitySpawnerMobsNether) world.getTileEntity(array2[i]);
					n.spawns = new String[] {"zombie", "skely", "pig", "blaze"};
					n.spawnRate = 6;
					n.delay = 90;
				}
				
			}
		}
		
		
		//beams
		if(realTicks >= 500 && realTicks < 720 && !world.isRemote) {
			
			for(int i = 0;  i < f.length; i++) {
				NetherReactorHelper.connectBlocks(g[i], f[i], (WorldServer)world);
			
			}
			//NetherReactorHelper.setParticleBeams(realTicks, world, realTicks, f, t);

		}
		
		if(realTicks == 720) {
			
			if(world.getTileEntity(pos.down(2)) instanceof TileEntityNetherCorruption) {
				TileEntityNetherCorruption n = (TileEntityNetherCorruption) world.getTileEntity(pos.down(2));
				
				if(n.wall1ready) {
					tickAdd = 6;
				}
			}
			
		}

		
		//Activates pillars around structure
		if(realTicks >= 720 && !world.isRemote && tickAdd != 0) {
			
			BlockPos[] array = NetherReactorStructure.downWardsLava(pos.up());
			ParticleUtil.sideDownNether(array, (WorldServer)world, tick);
			
			
		}
		
		if(tick > 200 && tickAdd != 0 && !world.isRemote) {
			tickAdd = 0;
			counterP = 1;
			NetherReactorHelper.slab(world, pos, (WorldServer) world);
		}
		
		//Starts after the pillars have finished in the particle handler
		if(counterP == 1 && counter == 0 && !world.isRemote) {
			NetherReactorParticles.execute((WorldServer)world, this.pos, tick, 0, 4);
		}
		
		if(counter > 0 && counter < 50 && !world.isRemote) {
			NetherReactorParticles.execute((WorldServer)world, this.pos.up(), counter, 0, 5);
		}
		
		if(counter >= 50 && counter < 75 && !world.isRemote) {
			NetherReactorParticles.execute((WorldServer)world, this.pos.up(), counter, 50, 6);
		}
		
		if(counter >= 75 && counter < 100 && !world.isRemote) {
			world.setBlockState(pos.up(4), Blocks.GLOWSTONE.getDefaultState());
			NetherReactorParticles.execute((WorldServer)world, this.pos.up(), counter, 75, 7);
		}
		
		if(counter >= 100 && counter < 125 && !world.isRemote) {
			NetherReactorParticles.execute((WorldServer)world, this.pos.up(), counter, 100, 8);
		}
		
		if(counter >= 125 && counter < 150 && !world.isRemote) {
			NetherReactorParticles.execute((WorldServer)world, this.pos.up(), counter, 125, 9);
		}
		
		if(counter >= 150 && counter < 175 && !world.isRemote) {
			NetherReactorParticles.execute((WorldServer)world, this.pos.up(), counter, 150, 10);
		
			BlockPos[] array2 = NetherReactorStructure.mobSpawners2(pos.up());
			for(int i = 0; i < array2.length; i++) {
				((WorldServer)world).setBlockState(array2[i], BlockInit.NETHER_MOB_SPAWNER.getDefaultState());
				
				if(world.getTileEntity(array2[i]) instanceof TileEntitySpawnerMobsNether) {
					TileEntitySpawnerMobsNether n = (TileEntitySpawnerMobsNether) world.getTileEntity(array2[i]);
					n.spawns = new String[] {"creeper", "creeper", "creeper", "creeper"};
					n.spawnRate = 1;
					n.rangeX = 1;
					n.rangeZ = 1;
					n.delay = 10;
				}
			}
			
		}
		
		if(counter == 174) world.setBlockState(pos.up(4), BlockInit.NETHER_GLOWSTONE.getDefaultState());
		
		if(counter == 205 && !world.isRemote) {

			double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
			
			NetherReactorHelper.setItemAir(world, pos);
			counterP = 0;
			counter++;
			NetherReactorHelper.setPortals(world, pos);
			CorruptionHelper.securePreservedSpaces(pos.up(), (WorldServer)world, true);
		}
		
		if(counter == 206 && !world.isRemote) {
			boolean[] b1 = NetherReactorHelper.itemAirBoolean(world, pos, 1);
			boolean[] b2 = NetherReactorHelper.itemAirBoolean(world, pos, 2);
			boolean[] b3 = NetherReactorHelper.itemAirBoolean(world, pos, 3);
			boolean[] b4 = NetherReactorHelper.itemAirBoolean(world, pos, 4);
			
			if(particleTick % 6 == 0) {
				ParticleUtil.quickCircle((WorldServer)world, pos.north(), EnumParticleTypes.END_ROD);
				ParticleUtil.quickCircle((WorldServer)world, pos.south(), EnumParticleTypes.END_ROD);
				ParticleUtil.quickCircle((WorldServer)world, pos.west(), EnumParticleTypes.END_ROD);
				ParticleUtil.quickCircle((WorldServer)world, pos.east(), EnumParticleTypes.END_ROD);
			}
			
			particleTick++;
			
			if(NetherReactorHelper.hasAll(b1, b2, b3, b4)) {
				System.out.println("Found");
				counterP = 1;
				//Closing off the structure
				upperRoof = NetherReactorWallHelper.executeRoof((WorldServer) world, upperRoof, 2);
				NetherReactorHelper.spawnParticles(pos, world);
			}
			
		}
		
		if(counter == 210 && !world.isRemote) {
			NetherReactorHelper.setRemove(world, pos);
		}
		
		if(counter == 219 && !world.isRemote) {
			//removed due to lag? NetherReactorHelper.setAir(world, pos);
			NetherReactorHelper.setDoorsLowerLevel(world, pos);
			NetherReactorHelper.setJumpPads(world, pos);
			NetherReactorHelper.setAirReplacers(world, pos);
			NetherReactorHelper.setLightAirs(world, pos);
			NetherReactorHelper.setCrates(world, pos);
		}
		
		
		

		
		tick = tick + tickAdd;
		//default realTicks++ on
		realTicks += realTicksAdd;
		beamRepeat += beamAdd;
		counter += counterP;
		routine++;
	}
	
	private void init() {
		upperRoof = NetherReactorWallHelper.setInList(pos.getAllInBox(pos.down(2).up(35).north(13).east(13), pos.down(2).up(35).south(13).west(13)));
		init = true;
	}
	
	private void shouldDestroy() {
		if(world.getBlockState(pos.up()) != BlockInit.NETHER_REACTOR_CORE_ACTIVATED.getDefaultState()) world.destroyBlock(pos, false);
	}
	
	
	private IBlockState getState() {
		return world.getBlockState(pos);
	}
	


	
}
