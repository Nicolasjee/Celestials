package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import poseidon.mod.objects.entities.CustomFireball;

public class EnderPearl extends CommandEffect {
	
	public EnderPearl() {
		
	}
	
	public static void fire(EntityPlayer playerIn) {
		
		World worldIn = playerIn.world;
		
		if(!worldIn.isRemote) {
			
			RayTraceResult pos = playerIn.rayTrace(100,20);
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();

			playerIn.attemptTeleport(x, y, z);
			
		}
		
	}
}
