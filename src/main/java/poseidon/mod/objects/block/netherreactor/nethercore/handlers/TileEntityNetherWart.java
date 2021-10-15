package poseidon.mod.objects.block.netherreactor.nethercore.handlers;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.ParticleUtil;

public class TileEntityNetherWart extends TileEntity implements ITickable {

	int brokenlevel;
	int tick;
	
	boolean b = false;
	
	@Override
	public void update() {
		
		if(!b) init();
		
		if(tick % brokenlevel*4 == 0 && !world.isRemote) particle();
	
		tick++;
	}
	
	private void init() {
		Block block = world.getBlockState(pos).getBlock();
		
		if(block == BlockInit.NETHER_WART_BLOCK1) brokenlevel = 5;
		if(block == BlockInit.NETHER_WART_BLOCK2) brokenlevel = 4;
		if(block == BlockInit.NETHER_WART_BLOCK3) brokenlevel = 3;
		if(block == BlockInit.NETHER_WART_BLOCK4) brokenlevel = 2;
		if(block == BlockInit.NETHER_WART_BLOCK5) brokenlevel = 1;
	}
	
	private void particle() {
		
		if(brokenlevel == 1) {
			ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos, EnumParticleTypes.FLAME);
		}
		
		if(brokenlevel == 2) {
			ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos, EnumParticleTypes.SMOKE_NORMAL);
			ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos, EnumParticleTypes.FLAME);
		}
		
		if(brokenlevel == 3) {
			ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos, EnumParticleTypes.SMOKE_NORMAL);
			ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos, EnumParticleTypes.FLAME);
		}
		
		if(brokenlevel == 4) {
			ParticleUtil.drawbridgeAppear((WorldServer) world, this.pos, EnumParticleTypes.SMOKE_NORMAL);
		}
		
		
	}
	
}
