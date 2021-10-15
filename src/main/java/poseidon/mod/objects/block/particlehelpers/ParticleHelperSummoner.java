package poseidon.mod.objects.block.particlehelpers;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import poseidon.mod.objects.block.general.BlockHidden;

public class ParticleHelperSummoner extends BlockHidden {

	public ParticleHelperSummoner(String name) {
		super(name, Material.ROCK, 0.0F, 10.0F, 10.0F, 2);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
		
		@Override
		public TileEntity createTileEntity(World world, IBlockState state) {
			return new ParticleHelperSummonerTileEntity();
		}
		
}
