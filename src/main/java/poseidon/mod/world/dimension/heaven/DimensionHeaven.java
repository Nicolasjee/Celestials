package poseidon.mod.world.dimension.heaven;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import poseidon.mod.init.BiomeInit;
import poseidon.mod.init.DimensionInit;

public class DimensionHeaven extends WorldProvider {
	
	public DimensionHeaven() {
		this.biomeProvider = new BiomeProviderSingle(BiomeInit.HEAVEN);
	}
	
	@Override
	public DimensionType getDimensionType() {
		return DimensionInit.HEAVEN;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorHeaven(world, true, world.getSeed(), world.getSpawnPoint());
	}
	
	@Override
	public boolean canRespawnHere() {
		return false;
	}
	
	@Override
	public boolean isSurfaceWorld() {
		return true;
	}
	
}
