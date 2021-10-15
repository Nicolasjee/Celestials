package poseidon.mod.util.handlers.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import poseidon.mod.util.Reference;

public class LootTableHandler {
		
	public static final ResourceLocation CHEST = LootTableList.register(new ResourceLocation(Reference.MODID, "chest"));
	public static final ResourceLocation CHEST2 = LootTableList.register(new ResourceLocation(Reference.MODID, "chest2"));
	
	
	
}

	
