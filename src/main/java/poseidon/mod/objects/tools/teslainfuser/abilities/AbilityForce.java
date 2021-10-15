package poseidon.mod.objects.tools.teslainfuser.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import poseidon.mod.Main;
import poseidon.mod.networking.MessageParticle;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.Utilities;

public class AbilityForce implements TeslaProperties {

	public static void call(ItemStack stack, EntityPlayer player) {

			
			RayTraceResult mov = Utilities.getMouseOverExtended(360.0F);
	      	
	       	if(mov != null) {
	       		if(mov.entityHit != null && mov.entityHit.hurtResistantTime == 0) {
	       			if(mov.entityHit != player) {
	       				Main.network.sendToServer(new MessageParticle(mov.entityHit.getEntityId()));
	       			}
	       		}
	       	}
		
	}
}
