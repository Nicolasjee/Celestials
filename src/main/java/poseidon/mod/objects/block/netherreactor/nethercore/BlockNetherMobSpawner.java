package poseidon.mod.objects.block.netherreactor.nethercore;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import poseidon.mod.objects.block.general.BlockHidden;
import poseidon.mod.objects.block.netherreactor.nethercore.handlers.TileEntitySpawnerMobsNether;

public class BlockNetherMobSpawner extends BlockHidden implements ITileEntityProvider{

	public BlockNetherMobSpawner(String name) {
		super(name, Material.ROCK, 0.0F, 999.0F, 999.0F, 1);
		this.setBlockUnbreakable();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySpawnerMobsNether();
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySpawnerMobsNether();
	}
}
