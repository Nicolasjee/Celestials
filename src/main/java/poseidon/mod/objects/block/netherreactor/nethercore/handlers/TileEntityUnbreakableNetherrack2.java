package poseidon.mod.objects.block.netherreactor.nethercore.handlers;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.NetherReactorHelper;
import poseidon.mod.util.ParticleUtil;

public class TileEntityUnbreakableNetherrack2 extends TileEntity implements ITickable {

	
		private int counter = 0;
		private int counterP = 1;
		private int particleTick = 0;
		//this was a test block
	    @Override
	    public void update() {
	        

	        
	        
	        if(counter == 0 && !world.isRemote) {

				double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
				NetherReactorHelper.setItemAir(world, pos);
				counterP = 0;
				counter++;
				//NetherReactorHelper.setPortals(world, pos);
				//CorruptionHelper.securePreservedSpaces(pos.up(), (WorldServer)world, true);
			}
			
			if(counter == 1 && !world.isRemote) {
				
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
					counterP = 1;
					//Closing off the structure
					//upperRoof = NetherReactorWallHelper.executeRoof((WorldServer) world, upperRoof, 2);
					NetherReactorHelper.spawnParticles(pos, world);
				}
				
			}
			
			if(counter == 210 && !world.isRemote) {
				NetherReactorHelper.setRemove(world, pos);
			}
			
			if(counter == 219 && !world.isRemote) {
				NetherReactorHelper.setAir(world, pos);
				NetherReactorHelper.setDoorsLowerLevel(world, pos);
				NetherReactorHelper.setJumpPads(world, pos);
				NetherReactorHelper.setAirReplacers(world, pos);
				NetherReactorHelper.setLightAirs(world, pos);
				NetherReactorHelper.setCrates(world, pos);
			}
	        
	        
			counter += counterP;
			
	    }
	    
	
}
