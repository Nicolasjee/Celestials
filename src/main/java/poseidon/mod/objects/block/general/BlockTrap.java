package poseidon.mod.objects.block.general;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.objects.block.particlerods.TileEntityParticleRod;

public class BlockTrap extends BlockBase implements ITileEntityProvider {

	public BlockTrap(String name) {
		super(name, Material.ROCK, 0.0F, 2.0F, 2.0F, 1);
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityParticleRod();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(worldIn.getTileEntity(pos) instanceof TileEntityParticleRod) {
			
			TileEntityParticleRod rod = (TileEntityParticleRod) worldIn.getTileEntity(pos);
			rod.choice = rod.choice + 1;
			
			if(rod.choice == 9) rod.choice = 1;
			if(rod.choice == 0) rod.choice = 1;
			
			return true;
			
		}
		
		return true;
	}
	
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		
		
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityParticleRod();
	}
	
}
