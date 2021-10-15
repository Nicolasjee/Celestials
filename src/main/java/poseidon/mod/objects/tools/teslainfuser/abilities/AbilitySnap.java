package poseidon.mod.objects.tools.teslainfuser.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import poseidon.mod.objects.items.wand.ModAbilities;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class AbilitySnap implements TeslaProperties {

	public static void call(ItemStack stack, EntityPlayer player, World world) {
		
		ModAbilities.selfDestruction(0, player, world);
		
	}
	
}
