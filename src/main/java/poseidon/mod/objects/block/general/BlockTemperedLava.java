package poseidon.mod.objects.block.general;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import poseidon.mod.objects.block.general.tileHelpers.TileEntityLava;

public class BlockTemperedLava extends BlockHidden {

	public BlockTemperedLava(String name) {
		super(name, Material.ROCK, 4.0F, 0.0F, 0.0F, 0);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityLava();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	

	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean isCollidable() {
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		
		if(entityIn instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) entityIn;
			//entityIn.motionY = 1.0D;
			
			float yaw = player.rotationYaw;
			float pitch = player.rotationPitch;
			
			double motionX = (double)((-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) / 2 ));
			double motionZ = (double)((MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) /2 ));
				    
			player.setVelocity(motionX, 0.1d, motionZ);
			
			TileEntity tile = worldIn.getTileEntity(pos);
			
			if(tile != null && tile instanceof TileEntityLava) {
				
				TileEntityLava lava = (TileEntityLava) tile;
				
				if(lava.steps < 100) lava.steps = lava.steps + 1;
				if(lava.steps == 100) lava.killSwitch = true;
				
			}
			
		}
	}
	
	@Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

	
	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	
}
