package poseidon.mod.objects.block.drawVortex;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.objects.block.general.BlockHidden;

public class BlockDraw extends BlockHidden {

	public BlockDraw(String name) {
		super(name, Material.PISTON, 0.0F, 2.0F, 1.0F, 1);
	}
	

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(worldIn.getTileEntity(pos) instanceof TileEntityDraw && !worldIn.isRemote) {
			TileEntityDraw d = (TileEntityDraw) worldIn.getTileEntity(pos);
			d.active = true;
			System.out.println("Active?: " + d.active);
		}
		
		return true;
	}
		
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {			
		return new TileEntityTest();
	}
	
}
