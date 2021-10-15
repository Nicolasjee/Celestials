package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import poseidon.mod.objects.entities.CustomFireball;

public class LaunchFireball extends CommandEffect {
	
	public LaunchFireball() {
		
	}
	
	public static void launch(EntityPlayer playerIn) {
		
		World worldIn = playerIn.world;
		
		Vec3d vector = playerIn.getLook(0.5F);
		

				CustomFireball entity4 = new CustomFireball(worldIn, playerIn, vector.x * 25D, vector.y * 25D, vector.z * 25D, 5);
				entity4.posY += playerIn.getEyeHeight();
		        entity4.posX = playerIn.posX;
		        entity4.posZ = playerIn.posZ;
		        
		        worldIn.spawnEntity(entity4);
		
		
	    
		
	}
}
