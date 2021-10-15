package poseidon.mod.objects.tools.teslainfuser.abilities;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class AbilityTeleport implements TeslaProperties {
	
	public static void execute(EntityPlayer playerIn, World worldIn, ItemStack held) {
			


			RayTraceResult pos = playerIn.rayTrace(100,0);
			Random rand = new Random();
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY() + 1;
			double z = pos.getBlockPos().getZ();
			playerIn.attemptTeleport(x, y, z);
			playerIn.serverPosX = (long) x;
			playerIn.serverPosY = (long) y;
			playerIn.serverPosZ = (long) z;
		
			Main.proxy.playSoundBlock(new BlockPos(x,y,z), worldIn, SoundsHandler.TELEPORT, 1.0F, 1.0F);
			if(!worldIn.isRemote) ParticleUtil.teleportParticles((WorldServer) worldIn, new BlockPos(x,y,z), playerIn);
			
		
			
	}
	
}
