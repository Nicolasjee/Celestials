package poseidon.mod.util.handlers.registry;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import poseidon.mod.Main;
import poseidon.mod.util.Reference;
import poseidon.mod.world.village.MapGenVillageDrug;
import poseidon.mod.world.village.VillageHouseDrug;
import poseidon.mod.world.village.travellingmerchant.MapGenVillageMerchant;
import poseidon.mod.world.village.travellingmerchant.VillageHouseMerchant;

public class VillagerInit {
	
	public static void registerVillags() {
		MapGenStructureIO.registerStructure(MapGenVillageDrug.Start.class, Reference.MODID + ":drug_camp");
		MapGenStructureIO.registerStructureComponent(VillageHouseDrug.class, Reference.MODID + ":drug_house");
		MapGenStructureIO.registerStructure(MapGenVillageMerchant.Start.class, Reference.MODID + ":merchant_hut");
		MapGenStructureIO.registerStructureComponent(VillageHouseMerchant.class, Reference.MODID + ":merchant_hut");
		VillagerRegistry.instance().registerVillageCreationHandler(Main.CLOUD_VILLAGE_HANDLER);
		VillagerRegistry.instance().registerVillageCreationHandler(Main.MERCHANT_VILLAGE);
	}
	
	public static void registerVillages() {
		System.out.println("Villages stopped");
	}

}
