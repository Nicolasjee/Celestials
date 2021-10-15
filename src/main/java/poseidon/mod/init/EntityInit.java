package poseidon.mod.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import poseidon.mod.Main;
import poseidon.mod.entity.entities.EntityBlueDye;
import poseidon.mod.entity.entities.EntityCustomVillager;
import poseidon.mod.entity.entities.EntityDemon;
import poseidon.mod.entity.entities.EntityExperienceCreeper;
import poseidon.mod.entity.entities.EntityHerobrine;
import poseidon.mod.entity.entities.EntitySwarmZombie;
import poseidon.mod.util.Reference;

public class EntityInit {
	
	public static void registerEntities() {
		registerEntity("demon", EntityDemon.class, Reference.ENTITY_DEMON, 50, 3000000, 900000);
		registerEntity("herobrine", EntityHerobrine.class, Reference.ENTITY_CENTAUR, 50, 3000000, 9000000);
		registerEntity("blue_dye", EntityBlueDye.class, Reference.ENTITY_CASTIEL, 50, 3093151, 000000);
		registerEntity("swarmzombie", EntitySwarmZombie.class, Reference.ENTITY_DRUGDEALER, 50, 7800000, 1000000);
		//registerEntity("customvillager", EntityCustomVillager.class, Reference.ENTITY_CENTAUR, 50, 3000000, 9000000);
		registerEntity("experience_creeper", EntityExperienceCreeper.class, Reference.ENTITY_CCREEPER, 50, 2000000, 6000000);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), 
				entity, name, id, Main.instance, range, 1, true, color1, color2);
	}
	
	public static void registerSpawns() {
		//EntityRegistry.addSpawn(EntityDemon.class, 20, 1,7, EnumCreatureType.AMBIENT, Biomes.TAIGA, Biomes.DESERT);
		//EntityRegistry.addSpawn(EntityDemon.class, 20, 1, 3, EnumCreatureType.MONSTER, Biomes.HELL);
		//EntityRegistry.addSpawn(EntitySwarmZombie.class, 30, 5, 18, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.SAVANNA, Biomes.JUNGLE, Biomes.ICE_MOUNTAINS, Biomes.ICE_PLAINS, Biomes.DESERT);
		//EntityRegistry.addSpawn(EntityExperienceCreeper.class, 30, 1, 6, EnumCreatureType.MONSTER, Biomes.ICE_PLAINS, Biomes.BEACH, Biomes.SAVANNA, Biomes.DESERT, Biomes.FOREST, Biomes.TAIGA);
	}
}
