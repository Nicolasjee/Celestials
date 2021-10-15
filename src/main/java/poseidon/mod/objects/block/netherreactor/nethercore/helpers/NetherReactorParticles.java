package poseidon.mod.objects.block.netherreactor.nethercore.helpers;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class NetherReactorParticles {

	public static void execute(WorldServer server, BlockPos core, int tick, int tickMinus, int stage) {
		
		if(stage == 0) {
			
			double[] startingPoints = NetherReactorHelper.getStartingPosses(core, stage, 1);
			double[] endingPoints = NetherReactorHelper.getEndingPosses(core, stage, 1);
			
			double[] startingPointsSpecial = NetherReactorHelper.getStartingPosses(core, stage, 2);
			double[] endingPointsSpecial = NetherReactorHelper.getEndingPosses(core, stage, 2);
			
			if(startingPoints.length + endingPoints.length == (4*3)*2) {
				
				NetherReactorSpawner.stageZeroParticles(startingPoints, endingPoints, server, tick, 0);
				NetherReactorSpawner.stageZeroParticles(startingPointsSpecial, endingPointsSpecial, server, tick, 0);
				
			}
			return;
			
		}
		
		if(stage == 1) {
			
			double[] startingPoints = NetherReactorHelper.getStartingPosses(core, stage, 1);
			double[] endingPoints = NetherReactorHelper.getEndingPosses(core, stage, 1);
			
			double[] startingPointsSpecial = NetherReactorHelper.getStartingPosses(core, stage, 2);
			double[] endingPointsSpecial = NetherReactorHelper.getEndingPosses(core, stage, 2);
			
			if(startingPoints.length + endingPoints.length == (4*3)*2) {
				
				NetherReactorSpawner.stageOneParticles(startingPoints, endingPoints, server, tick, 1);
				NetherReactorSpawner.stageOneParticles(startingPointsSpecial, endingPointsSpecial, server, tick, 1);
				
				NetherReactorSpawner.stageOnebParticles(startingPoints, endingPoints, server, tick, 1);
				NetherReactorSpawner.stageOnebParticles(startingPointsSpecial, endingPointsSpecial, server, tick, 1);
				
			}
			return;
			
		}
		
		if(stage == 2) {
			
			//These arrays are corresponding with each other.
			double[] startingPoints = NetherReactorHelper.getStartingPosses(core, stage, 1);
			double[] endingPoints = NetherReactorHelper.getEndingPosses(core, stage, 1);
			
			double[] startingPointsSpecial = NetherReactorHelper.getStartingPosses(core, stage, 2);
			double[] endingPointsSpecial = NetherReactorHelper.getEndingPosses(core, stage, 2);
			
			double[] startingPointsSpecial2 = NetherReactorHelper.getStartingPosses(core, stage, 3);
			double[] endingPointsSpecial2 = NetherReactorHelper.getEndingPosses(core, stage, 3);
			
			double[] startingPointsSpecial3 = NetherReactorHelper.getStartingPosses(core, stage, 4);
			double[] endingPointsSpecial3 = NetherReactorHelper.getEndingPosses(core, stage, 4);
			
			if(startingPoints.length + endingPoints.length == (4*3)*2) {
			
				NetherReactorSpawner.stageTwoParticles(startingPoints, endingPoints, server, tick, 1);
				NetherReactorSpawner.stageTwoParticles(startingPointsSpecial, endingPointsSpecial, server, tick, 2);
				NetherReactorSpawner.stageTwoParticles(startingPointsSpecial2, endingPointsSpecial2, server, tick, 3);
				NetherReactorSpawner.stageTwoParticles(startingPointsSpecial3, endingPointsSpecial3, server, tick, 4);
				
			}
			return;
			
		}
		
		if(stage == 4) {
			double[] startingPoints = NetherReactorHelper.getStartingPosses(core, 4, 1);
			double[] endingPoints = NetherReactorHelper.getEndingPosses(core, 4, 1);
			NetherReactorSpawner.stage4Particles(server, startingPoints, endingPoints, tick);
			return;
		}
		

		if(stage == 5) {
			double[] startingPoints = NetherReactorHelper.getStartingPosses(core, 5, 1);
			double[] endingPoints = NetherReactorHelper.getEndingPosses(core, 5, 1);
			
			double[] startingPoints2 = NetherReactorHelper.getStartingPosses(core, 5, 2);
			double[] endingPoints2 = NetherReactorHelper.getEndingPosses(core, 5, 2);
			
				
			NetherReactorSpawner.stageUniversal(server, startingPoints, endingPoints, tick, tickMinus);
			NetherReactorSpawner.stageUniversal(server, startingPoints2, endingPoints2, tick, tickMinus);
			return;
		}
		
		if(stage == 6) {
			double[] startingPoints = NetherReactorHelper.getStartingPosses(core, 6, 1);
			double[] endingPoints = NetherReactorHelper.getEndingPosses(core, 6, 1);
			
			double[] startingPoints2 = NetherReactorHelper.getStartingPosses(core, 6, 2);
			double[] endingPoints2 = NetherReactorHelper.getEndingPosses(core, 6, 2);
			
			
			
				
			NetherReactorSpawner.stageUniversal(server, startingPoints, endingPoints, tick, tickMinus);
			NetherReactorSpawner.stageUniversal(server, startingPoints2, endingPoints2, tick, tickMinus);
			return;
		}
		
			


		double[] startingPoints = NetherReactorHelper.getStartingPosses(core, stage, 1);
		double[] endingPoints = NetherReactorHelper.getEndingPosses(core, stage, 1);
			
				
		NetherReactorSpawner.stageUniversal(server, startingPoints, endingPoints, tick, tickMinus);
		
		
	}
	
}
