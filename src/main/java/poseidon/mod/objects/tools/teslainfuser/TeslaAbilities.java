package poseidon.mod.objects.tools.teslainfuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityChange;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityChoke;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityElytra;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityFireball;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityForce;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityGroundForce;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityLightning;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityMiner;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilitySnap;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityTNT;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityTeleport;

public class TeslaAbilities implements TeslaProperties {

	public static void execute(ItemStack stack, EntityPlayer player, World world) {
		
		if(stack.hasTagCompound()) {
			
			NBTTagCompound nbt = stack.getTagCompound();
			int[] data = nbt.getIntArray("DataArray");
			int focus = TeslaProperties.focus(data);
			int dur = TeslaProperties.dur(data);
			String s = TeslaProperties.getAbilityOfFocus(stack);
			
			if(TeslaProperties.doesPowerExists(s) && TeslaProperties.sufficientDurability(s, dur, focus)) {
				
				if(s == FORCE) AbilityForce.call(stack, player);
				if(s == STOP) AbilityChange.activate(stack, player, STOP);
				if(s == LIGHTNING) AbilityLightning.execute(player, world, stack);
				if(s == TNT) AbilityTNT.execute(player, world, stack);
				if(s == FIREBALL) AbilityFireball.execute(player, world, stack);
				if(s == TELEPORT) AbilityTeleport.execute(player, world, stack);
				
				//Rework.
				/*
				 * Groundforce useless. Removed
				 * Snap too powerful. Removed
				 * Velocity already connected with another tool. Removed
				 * Choke is a cheap version of stop. To be relocated?			
				 * Push can be used in a block. To be relocated.				SHIELD BLOCK
				 * Cloak will be used as an item. To be relocated. 				CHRONOMORFOS
				 */
				
			}
			
			stack.damageItem(TeslaProperties.getCost(focus, stack), player);
			nbt.setIntArray("DataArray", new int[] {data[0], data[1], data[2], data[3], data[4], data[5] - TeslaProperties.getCost(focus, stack)});
			
		}
		
	}
	
}
