package poseidon.mod.objects.block.general.chest.sand;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.teslagenerator.TileEntityTeslaGenerator;
import poseidon.mod.util.Reference;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class SandChest extends BlockContainer {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public SandChest(String name) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(worldIn.getTileEntity(pos) instanceof TileEntitySandChest) {
			TileEntitySandChest chest = (TileEntitySandChest) worldIn.getTileEntity(pos);
			
			if(!chest.locked && !worldIn.isRemote) {
				playerIn.openGui(Main.instance, Reference.GUI_SANDCHEST, worldIn, pos.getX(), pos.getY(), pos.getZ());	
				return true;
			}
			
//			if(chest.locked && playerIn.getHeldItemMainhand().getItem() == ItemInit.SAND_KEY) {
//				playerIn.getHeldItemMainhand().shrink(1);
//				double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
//				worldIn.playSound(x, y, z, SoundsHandler.LOCK, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
//				chest.locked = false;
//				System.out.println("Facing: " + facing);
//				drawParticles(worldIn, pos, facing);
//				return true;
//			}
			
			if(chest.locked) {
				double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
				worldIn.playSound(x, y, z, SoundsHandler.LOCKED, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
				return true;
			}
			
			
		}
		return true;
		
	}
	
	private void drawParticles(World world, BlockPos pos, EnumFacing f) {
		
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntitySandChest t = (TileEntitySandChest) worldIn.getTileEntity(pos);
		if(!t.locked) InventoryHelper.dropInventoryItems(worldIn, pos, t);
		
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if(stack.hasDisplayName()) {
			TileEntity t = worldIn.getTileEntity(pos);
			
			if(t instanceof TileEntitySandChest) {
				
				((TileEntitySandChest)t).setCustomName(stack.getDisplayName());
				
				
				
				
			}
			
		}

	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySandChest();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
}
