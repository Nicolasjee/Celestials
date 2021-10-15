package poseidon.mod.objects.block.netherreactor.nethercore;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.TileEntityItemAir;
import poseidon.mod.util.interfaces.IHasModel;

public class BlockItemAir extends Block implements IHasModel, ITileEntityProvider {

	public BlockItemAir(String name) {
		super(Material.AIR);
		setUnlocalizedName(name);
		setRegistryName(name);

		this.setTickRandomly(true);
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		//WandAbility.earth3(worldIn, pos);
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {

		if(worldIn.getTileEntity(pos) instanceof TileEntityItemAir) {
			TileEntityItemAir a = (TileEntityItemAir) worldIn.getTileEntity(pos);
			
			a.function = 4;
		}
		
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		TileEntity tile = worldIn.getTileEntity(pos);
		
		if(tile != null && tile instanceof TileEntityItemAir) {
			TileEntityItemAir t = (TileEntityItemAir) worldIn.getTileEntity(pos);
			
			if(t.function == 4 && !worldIn.isRemote) {

						entityIn.setVelocity(0.0D, 0.0F, 0.0D);

				
			}
			
			//Light spawner for corridors
			if(t.function == 3 && !worldIn.isRemote) {
				if(entityIn instanceof EntityPlayer) {
					System.out.println("Entity: " + entityIn.getName() + " has collided with item air and has become a glowstone block");
					for(int i = 0; i < 20; i++) {
						if(worldIn.getBlockState(pos.up(i)) == BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState()) {
							worldIn.setBlockState(pos.up(i), Blocks.GLOWSTONE.getDefaultState());
							return;
						}
					}
					
					worldIn.setBlockState(pos.down(), Blocks.GLOWSTONE.getDefaultState());
					worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
				
					
				}
			}
			
			if(t.function == 1 && !worldIn.isRemote) {
				if(entityIn instanceof EntityItem) {
					if(entityIn.posX > pos.getX() + 0.25D && entityIn.posX < pos.getX() + 0.75D) {
						if(entityIn.posZ > pos.getZ() + 0.25D && entityIn.posZ < pos.getZ() + 0.75D) {
							entityIn.setVelocity(0.0D, 0.0F, 0.0D);
						}
					}
				}
			}
		}
	}
	
	
	private boolean isIngredient(EntityItem item, TileEntityItemAir tile) {
		Item i = item.getItem().getItem();
		if(i == tile.i1) return true;
		else return false;
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

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityItemAir();
	}
	
	
}
