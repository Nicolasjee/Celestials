package poseidon.mod.objects.block.general;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.general.tileHelpers.TileEntityBounce;
import poseidon.mod.util.interfaces.IHasModel;

public class BlockBounce extends Block implements IHasModel {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockBounce(String name) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		
		setLightLevel(0.0F);
		setHardness(2.0F);
		setResistance(5.0F);
		
		setHarvestLevel("pickaxe", 2);
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	public BlockBounce(String name, TileEntityBounce tile) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		
		setLightLevel(0.0F);
		setHardness(2.0F);
		setResistance(5.0F);
		
		setHarvestLevel("pickaxe", 2);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		TileEntity tile = worldIn.getTileEntity(pos);
		ItemStack stack = playerIn.getHeldItemMainhand();
		
		if(tile != null && tile instanceof TileEntityBounce) {
	
				TileEntityBounce b = (TileEntityBounce) tile;
				if(b.strength < 1.0F) b.strength = 1.0F;
				if(b.strength > 10.0F) b.strength = 1.0F;
				
				if(!playerIn.isSneaking()) b.strength = b.strength + 0.1F;
				if(playerIn.isSneaking() && !isHitMiddle(hitY) && !worldIn.isRemote) {
					playerIn.sendStatusMessage(new TextComponentTranslation("", new Object[0]), true);
					b.strength = b.strength - 0.1F;
				}
				if(playerIn.isSneaking() && isHitMiddle(hitY) && !worldIn.isRemote) playerIn.sendStatusMessage(new TextComponentTranslation(TextFormatting.WHITE + "Jump Strength: " + TextFormatting.GREEN + Float.toString(b.strength), new Object[0]), true);

		}
		
		
		
		return true;
	}
	
	@Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {

            entityIn.fall(fallDistance, 0.0F);
        
    }
	
	public boolean isHitMiddle(float y) {
		
		if(y != 1.0D) return true;
		else return false;
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		Random rand = new Random();
		TileEntity tile = worldIn.getTileEntity(pos);
		
		if(tile != null && tile instanceof TileEntityBounce) {
			
			TileEntityBounce b = (TileEntityBounce) tile;
			float s = b.strength;
			entityIn.setVelocity(entityIn.motionX, s, entityIn.motionZ);
			
		}
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityBounce();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
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
	
	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(active) worldIn.setBlockState(pos, BlockInit.LAUNCHPAD.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		else worldIn.setBlockState(pos, BlockInit.LAUNCHPAD.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		
		if(tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	
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
