package poseidon.mod.objects.tools.teslainfuser.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.networking.MessageChoke;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;

public class AbilityChoke implements TeslaProperties {

	public static void execute(EntityPlayer playerIn, ItemStack stack, World world) {
		
    	RayTraceResult mov = Utilities.getMouseOverExtended(360.0F);
    	
    	if(mov != null) {
    		if(mov.entityHit != null && mov.entityHit.hurtResistantTime == 0) {
    			if(mov.entityHit != playerIn) {

    				Main.network.sendToServer(new MessageChoke(mov.entityHit.getEntityId()));
    				if(!world.isRemote) ParticleUtil.trail((WorldServer) world, playerIn);
    			}
    		}
    	}
		
   
    	
	}
	
}
