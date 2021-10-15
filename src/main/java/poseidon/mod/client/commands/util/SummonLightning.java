package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import poseidon.mod.objects.entities.CustomFireball;

public class SummonLightning  extends CommandEffect {
	
	public SummonLightning() {
		
	}
	
	public static void summon(EntityPlayer player) {
		
		World worldIn = player.world;
		
		if(!worldIn.isRemote) {
			
			RayTraceResult pos = player.rayTrace(100,20);
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();
			Entity bolt = new EntityLightningBolt(worldIn, x, y, z, false);
			worldIn.addWeatherEffect(bolt);
			
		}
		
	}
}
