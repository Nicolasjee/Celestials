package poseidon.mod.test.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import poseidon.mod.objects.block.general.BlockBase;

public class BlockTest extends BlockBase implements ITileEntityProvider {

	public BlockTest(String name) {
		super(name, Material.ROCK, 1.0F, 1.0F, 1.0F, 1);
		//String name, Material material, float light, float hard, float res, int harvest
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTest();
	}
	
}
