package poseidon.mod.objects.block.crystalsplitter;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.objects.block.teslagenerator.TileEntityTeslaGenerator;
import poseidon.mod.util.Reference;

public class BlockSplitter extends BlockBase implements ITileEntityProvider {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockSplitter(String name) {
		super(name, Material.ROCK, 0.0F, 3.0F, 3.0F, 2);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySplitter();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote && !playerIn.isSneaking()) {
			playerIn.openGui(Main.instance, Reference.GUI_SPLITTER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		if(playerIn.isSneaking()) {
			if(worldIn.getTileEntity(pos) instanceof TileEntitySplitter) {
				TileEntitySplitter t = (TileEntitySplitter) worldIn.getTileEntity(pos);
				t.activate = true;
				System.out.println("t:active: " + t.activate);
			}
		}
		
		return true;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		
		IBlockState north = worldIn.getBlockState(pos.north());
		IBlockState south = worldIn.getBlockState(pos.south());
		IBlockState west = worldIn.getBlockState(pos.west());
		IBlockState east = worldIn.getBlockState(pos.east());
		EnumFacing face = (EnumFacing) state.getValue(FACING);
		
		if(face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
		else if(face == EnumFacing.SOUTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.NORTH;
		else if(face == EnumFacing.WEST && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.EAST;
		else if(face == EnumFacing.EAST && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.WEST;
		
		worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
		
	}
	
	/*
	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(active) worldIn.setBlockState(pos, BlockInit.CRYSTAL_SPLITTER.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		else worldIn.setBlockState(pos, BlockInit.CRYSTAL_SPLITTER.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		
		if(tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	*/
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
		
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
		
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntitySplitter tile = (TileEntitySplitter) worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tile);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}
	
	
}
