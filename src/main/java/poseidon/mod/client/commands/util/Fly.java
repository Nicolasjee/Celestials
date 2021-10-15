package poseidon.mod.client.commands.util;

import ibxm.Player;
import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import poseidon.mod.objects.entities.CustomFireball;

public class Fly extends CommandEffect {
	
	public Fly() {
		
	}
	
	public static void initiateFlight(EntityPlayer playerIn) {
		
		playerIn.capabilities.allowFlying = true;
		System.out.println(playerIn.capabilities.allowFlying);
		
		
	}

}
