package poseidon.mod.objects.tools.teslainfuser.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import poseidon.mod.Main;
import poseidon.mod.networking.MessageParticle;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.Utilities;

public class AbilityElytra implements TeslaProperties {

	public static void execute(EntityPlayer player, ItemStack stack) {
			

			double camX = player.getLookVec().x;
		    double camY = player.getLookVec().y;
		    double camZ = player.getLookVec().z;
			
		    double velocityAddedX = camX * 2;
		    double velocityAddedY = camY * 2;
		    double velocityAddedZ = camZ * 2;
		     
		    double currentVelocityX = player.motionX + velocityAddedX;
		    double currentVelocityY = player.motionY + velocityAddedY;
		    double currentVelocityZ = player.motionZ + velocityAddedZ;

		    player.setVelocity(currentVelocityX, currentVelocityY, currentVelocityZ);
		    
		  
		
	}
}
