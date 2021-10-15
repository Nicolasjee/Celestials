package poseidon.mod.objects.block.general;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class IndestructibleBlock extends BlockBase {

	public IndestructibleBlock(String name, float light) {
		super(name, Material.ROCK, light, 999.0F, 999.0F, 1);
		this.setBlockUnbreakable();
	}
	
	
}
