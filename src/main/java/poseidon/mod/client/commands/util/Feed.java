package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class Feed extends CommandEffect {
	
	public Feed() {
		
	}
	
	public static void feed(EntityPlayer player) {
		
		World worldIn = player.world;
		
		if(!worldIn.isRemote) {
			
			player.getFoodStats().setFoodLevel(20);
			
		}
		
	}
}
