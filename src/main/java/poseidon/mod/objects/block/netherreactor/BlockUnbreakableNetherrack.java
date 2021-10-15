package poseidon.mod.objects.block.netherreactor;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.general.BlockHidden;
import poseidon.mod.objects.block.netherreactor.nethercore.handlers.TileEntityUnbreakableNetherrack;

public class BlockUnbreakableNetherrack extends BlockHidden implements ITileEntityProvider{

	public BlockUnbreakableNetherrack(String name) {
		super(name, Material.ROCK, 0.0F, 999.0F, 999.0F, 1);
		this.setBlockUnbreakable();
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
		if(!world.isRemote) world.setBlockState(pos, BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityUnbreakableNetherrack();
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityUnbreakableNetherrack();
	}


}
