package poseidon.mod.objects.block.particlerods;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import poseidon.mod.objects.block.aiolonsynth.TileEntityAiolonAir;
import poseidon.mod.objects.block.general.BlockBase;

public class ParticleRod extends BlockBase
{

	public ParticleRod(String name) 
	{
		super(name, Material.ROCK, 0.0F, 10.0F, 10.0F, 2);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) 
	{
		return new TileEntityParticleRod();
	}	
}
