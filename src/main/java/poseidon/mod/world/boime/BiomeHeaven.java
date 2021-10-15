package poseidon.mod.world.boime;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomeHeaven extends Biome {
	
	public BiomeHeaven() {
		super(new BiomeProperties("Heaven").setBaseHeight(1.5F).setHeightVariation(1.2F).setTemperature(0.6F).setRainDisabled().setWaterColor(28831));
	}
	
}
