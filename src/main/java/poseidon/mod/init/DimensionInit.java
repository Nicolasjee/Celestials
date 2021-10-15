package poseidon.mod.init;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import poseidon.mod.world.dimension.heaven.DimensionHeaven;

public class DimensionInit {

	public static final DimensionType HEAVEN = DimensionType.register("Heaven", "_heaven", 2, DimensionHeaven.class, false);
	
	public static void registerDimension() {
		//DimensionManager.registerDimension(2, HEAVEN);
	}
	
}
