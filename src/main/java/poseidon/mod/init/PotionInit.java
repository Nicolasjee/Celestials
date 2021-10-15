package poseidon.mod.init;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import poseidon.mod.objects.potion.BadJujuPotion;

public class PotionInit {

	public static final Potion BADJUJU = new BadJujuPotion("infection", false, 16711680, 0, 0);
	public static final Potion TELEPORT = new BadJujuPotion("teleport", false, 16711680, 0, 0);
	
	public static final PotionType BADJUJU_POTION = new PotionType("infection", new PotionEffect[] {new PotionEffect(BADJUJU, 400)}).setRegistryName("infection");
	public static final PotionType LONG_BADJUJU_POTION = new PotionType("infection", new PotionEffect[] {new PotionEffect(BADJUJU, 230)}).setRegistryName("long_infection");
	
	public static final PotionType TELEPORT_POTION = new PotionType("teleport", new PotionEffect[] {new PotionEffect(TELEPORT, 20)}).setRegistryName("teleport");
	public static final PotionType LONG_TELEPORT_POTION = new PotionType("teleport", new PotionEffect[] {new PotionEffect(TELEPORT, 20)}).setRegistryName("long_teleport");
	
	
	public static void registerPotions() {
		registryPotion(BADJUJU_POTION, LONG_BADJUJU_POTION, BADJUJU);
		registryPotion(TELEPORT_POTION, LONG_TELEPORT_POTION, TELEPORT);
		//registerPotionMixes();
	}
	
	public static void registryPotion(PotionType defPot, PotionType longPot, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defPot);
		ForgeRegistries.POTION_TYPES.register(longPot);
	}
	
	private static void registerPotionMixes() {
		PotionHelper.addMix(BADJUJU_POTION,  Items.REDSTONE, LONG_BADJUJU_POTION);
		PotionHelper.addMix(PotionTypes.AWKWARD, ItemInit.WARRANT, BADJUJU_POTION);
	}
}
