package poseidon.mod.objects.block.general;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import poseidon.mod.init.ItemInit;

public class BlockMineral extends BlockBase {

	public BlockMineral(String name) {
		super(name, Material.GLASS, 3.0F, 2.0F, 2.0F, 2);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemInit.ELYTRON_DUST;
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 4;
	}
	
}
