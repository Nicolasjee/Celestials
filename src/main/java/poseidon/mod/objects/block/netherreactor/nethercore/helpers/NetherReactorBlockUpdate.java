package poseidon.mod.objects.block.netherreactor.nethercore.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.util.ParticleUtil;

public class NetherReactorBlockUpdate {
	
	public static void NetherReactorBlock(int stage, BlockPos pos, int tick, World world, EnumParticleTypes type) {
		
		if(stage == 0) {
			//Setting x to the step.
			double x = ((-NetherReactorHelper.getStartingPosses(pos, 0, 1)[1] + NetherReactorHelper.getEndingPosses(pos, 0, 1)[1]) / ReactorStageTime.stage0);
			if(x*tick > 1.0D - x*2 && x*tick < 1.0D + x*4) {
				for(int i = 0; i < NetherReactorStructure.underLayer(1, pos.up()).length; i++) {
					BlockPos check = NetherReactorStructure.underLayer(1, pos.up())[i];
					world.setBlockState(check, Blocks.NETHER_BRICK.getDefaultState());
					ParticleUtil.drawbridgeAppear((WorldServer)world, check, type);
				}
				
				for(int i = 0; i < NetherReactorStructure.underLayer(2, pos.up()).length; i++) {
					BlockPos check = NetherReactorStructure.underLayer(2, pos.up())[i];
					world.setBlockState(check, Blocks.AIR.getDefaultState());
					ParticleUtil.drawbridgeAppear((WorldServer)world, check, type);
				}
				Main.proxy.playSoundBlock(pos, world, SoundEvents.ITEM_FIRECHARGE_USE, 1.0F, 0.1F);
			}
		}
		
		if(stage == 1) {
			
			double x = ((-NetherReactorHelper.getStartingPosses(pos, 1, 1)[1] + NetherReactorHelper.getEndingPosses(pos, 1, 1)[1]) / ReactorStageTime.stage1);
			
			tick = tick - stage*ReactorStageTime.stage0; 
			
			//transforming second layer if the particles have reached 0.5D blocks (first particles reach 1.5D so 0.5D + 1.5D = 2.0D = new block)
			if(x*tick > 0.5D - x*2 && x*tick < 0.5D + x*4) {
				
				for(int i = 0; i < NetherReactorStructure.layer(1, pos.up()).length; i++) {
					BlockPos check = NetherReactorStructure.layer(1, pos.up())[i];
					world.setBlockState(check, Blocks.NETHER_BRICK.getDefaultState());
					ParticleUtil.drawbridgeAppear((WorldServer)world, check, type); 	
				}
				Main.proxy.playSoundBlock(pos, world, SoundEvents.ITEM_FIRECHARGE_USE, 1.0F, 0.1F);
				
			}
			
			if(x*tick >= 1.5D - x*5 && x*tick < 1.5D) {
				
				for(int i = 0; i < NetherReactorStructure.upperLayer(1, pos.up()).length; i++) {
					BlockPos check = NetherReactorStructure.upperLayer(1, pos.up())[i];
					
					world.setBlockState(check, Blocks.NETHER_BRICK.getDefaultState());
					ParticleUtil.drawbridgeAppear((WorldServer)world, check, type);
				}
				
				for(int i = 0; i < NetherReactorStructure.upperLayer(2, pos.up()).length; i++) {
					BlockPos check = NetherReactorStructure.upperLayer(2, pos.up())[i];
					world.setBlockState(check, Blocks.NETHER_BRICK.getDefaultState());
					ParticleUtil.drawbridgeAppear((WorldServer)world, check, type);
				}
				Main.proxy.playSoundBlock(pos, world, SoundEvents.ITEM_FIRECHARGE_USE, 1.0F, 0.1F);
				
			}
			
		}
		
		
	}
	
	
}
