package poseidon.mod.objects.block.netherreactor.nethercore.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorBlockUpdate;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorHelper;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorParticles;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorStructure;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.ReactorStageTime;
import poseidon.mod.util.ParticleUtil;

public class TileEntityNetherParticles extends TileEntity implements ITickable {

	boolean pillar1placed = false;
	boolean pillar2placed = false;
	boolean pillar3placed = false;
	
	boolean init = false;
	
	int tick = 0;
	BlockPos usedPos = this.pos;
	
	@Override
	public void update() {
		
		
		if(!init) init();
		shouldDestroy();

		if(!world.isRemote && tick <= ReactorStageTime.stage0) {
			for(int i = 1; i < 8; i++) {
				NetherReactorParticles.execute((WorldServer)world, this.usedPos, tick + i, 0, 0);
				NetherReactorBlockUpdate.NetherReactorBlock(0, usedPos, tick, world, EnumParticleTypes.FLAME);
			}
		}
		
		//Particles stage1
		if(!world.isRemote && tick >= ReactorStageTime.stage0 && tick <= ReactorStageTime.stage01Sum) {
			for(int i = 1; i < 8; i++) {
				NetherReactorParticles.execute((WorldServer)world, this.usedPos, tick + i, 0, 1);
				NetherReactorBlockUpdate.NetherReactorBlock(1, usedPos, tick, world, EnumParticleTypes.FLAME);
			}
		}
		
		//Particles stage2
		if(!world.isRemote && tick >= ReactorStageTime.stage01Sum && tick < ReactorStageTime.stageSum) {
			for(int i = 1; i < 8; i++) {
				NetherReactorParticles.execute((WorldServer)world, this.usedPos, tick + i, 0, 2);
			}
		}
		
		//Pillar execution
		if(!world.isRemote && tick >= ReactorStageTime.stageSum && tick < ReactorStageTime.stageSum + ReactorStageTime.pillarTime) {
			
			if(!pillar1placed) {
				Main.proxy.playSoundBlock(usedPos, world, SoundEvents.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
				BlockPos[] array1 = NetherReactorStructure.chargingPillars(usedPos.up(), 1);
				
				for(int i = 0; i < array1.length; i++) {
					world.setBlockState(array1[i], Blocks.NETHER_WART_BLOCK.getDefaultState());
					ParticleUtil.drawbridgeAppear((WorldServer)world, array1[i], EnumParticleTypes.LAVA);
				}
				
				NetherReactorHelper.slab(world, usedPos, (WorldServer)world);
				pillar1placed = true;
			}
			
			if(!pillar2placed && tick - ReactorStageTime.stageSum > ReactorStageTime.pillarTime1) {
				Main.proxy.playSoundBlock(usedPos, world, SoundEvents.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
				BlockPos[] array1 = NetherReactorStructure.chargingPillars(usedPos.up(), 2);
				
				for(int i = 0; i < array1.length; i++) {
					world.setBlockState(array1[i], Blocks.NETHER_WART_BLOCK.getDefaultState());
					ParticleUtil.drawbridgeAppear((WorldServer)world, array1[i], EnumParticleTypes.LAVA);
				}
				pillar2placed = true;
			}
			
			if(!pillar3placed && tick - ReactorStageTime.stageSum > ReactorStageTime.pillarTime1 + ReactorStageTime.pillarTime2) {
				Main.proxy.playSoundBlock(usedPos, world, SoundEvents.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
				
				BlockPos[] array1 = NetherReactorStructure.chargingPillars(usedPos.up(), 3);
				BlockPos[] array2 = NetherReactorStructure.mobSpawners(usedPos.up());
				
				for(int i = 0; i < array1.length; i++) {
					world.setBlockState(array1[i], Blocks.NETHER_WART_BLOCK.getDefaultState());
					ParticleUtil.drawbridgeAppear((WorldServer)world, array1[i], EnumParticleTypes.LAVA);
					
				}
				pillar3placed = true;
			}
			
		}
		
		
		
		tick = tick + 6;
	}
	
	private void init() {
		usedPos = this.pos.up();
		init = true;
	}
	
	private void shouldDestroy() {
		if(world.getBlockState(usedPos.up()) != BlockInit.NETHER_REACTOR_CORE_ACTIVATED.getDefaultState()) world.destroyBlock(pos, false);
	}
	
	
}
