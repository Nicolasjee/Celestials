package poseidon.mod.objects.block.netherreactor.nethercore;

import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.objects.block.netherreactor.nethercore.handlers.TileEntitySinkhole;
import poseidon.mod.util.RechteUtil;

public class BlockSinkHole extends BlockBase implements ITileEntityProvider {

	public BlockSinkHole(String name, Material material, float light, float hard, float res, int harvest) {
		super(name, material, light, hard, res, harvest);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileEntitySinkhole) {
			TileEntitySinkhole h = (TileEntitySinkhole) worldIn.getTileEntity(pos);
			h.on = !h.on;
		}


		
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySinkhole();
	}
	
}
