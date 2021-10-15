package poseidon.mod.client.keybinds;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Keybinds {
	
	public static KeyBinding elytraBoost;
	public static final String elytraboost = "Velocity Boost";
	
	public static KeyBinding changeAbility;
	public static final String changeability = "Change Ability";
	
	public static KeyBinding choke;
	public static final String chokeString = "Push Entity";
	
	public static KeyBinding elytraFree;
	public static final String elytrafree = "Elytra Boost";
	
	public static KeyBinding diamondFinder;
	public static final String diamondfinder = "Diamond Find";
	
	public static void register() {
		
		elytraFree = new KeyBinding(elytrafree, 46, "key.categories.movement");
		ClientRegistry.registerKeyBinding(elytraFree);
		
		elytraBoost = new KeyBinding(elytraboost, 47, "key.categories.movement");
		ClientRegistry.registerKeyBinding(elytraBoost);
		
		changeAbility = new KeyBinding(changeability, 57, "key.categories.miscellaneous");
		ClientRegistry.registerKeyBinding(changeAbility);
		
		choke = new KeyBinding(chokeString, 67, "key.categories.miscellaneous");
		ClientRegistry.registerKeyBinding(choke);
		
		diamondFinder = new KeyBinding(diamondfinder, 78, "key.categories.miscellaneous");
		ClientRegistry.registerKeyBinding(diamondFinder);
	}
	
}
