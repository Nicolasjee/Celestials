package poseidon.mod.objects.block.general.chest.obsidian;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.Reference;
import poseidon.mod.util.handlers.registry.LootTableHandler;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class ObsidianChest extends BlockContainer {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public ObsidianChest(String name) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
	
		setHardness(3.0F);
		setResistance(3.0F);
		
		this.setBlockUnbreakable();
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
	}
	
	/*
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(worldIn.getTileEntity(pos) instanceof TileEntityObsidianChest) {
			TileEntityObsidianChest chest = (TileEntityObsidianChest) worldIn.getTileEntity(pos);
			
			if(!chest.locked && !worldIn.isRemote) {
				playerIn.openGui(Main.instance, Reference.CHEST_OBSIDIAN, worldIn, pos.getX(), pos.getY(), pos.getZ());	
				return true;
			}
			
			if(chest.locked && !chest.activated && playerIn.getHeldItemMainhand().getItem() == ItemInit.OBSIDIAN_KEY) {
				ItemStack stack = playerIn.getHeldItemMainhand();
				playerIn.getHeldItemMainhand().shrink(1);
				
				double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
				Main.proxy.playSoundBlock(pos, worldIn, SoundsHandler.LOCK, 1.0F, 1.0F);
				chest.activated = true;
				
				if(stack.hasTagCompound()) {
					stack.getTagCompound().setIntArray("Coords", new int[] {pos.getX(), pos.getY(), pos.getZ(), 1});
				}
				
				
				return true;
			}
			
			if(chest.locked && !chest.activated && !(playerIn.getHeldItemMainhand().getItem() == ItemInit.OBSIDIAN_KEY)) {
				double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
				Main.proxy.playSoundBlock(pos, worldIn, SoundsHandler.LOCKED, 1.0F, 1.0F);
				return true;
			}
			
			
		}
		return true;
		
	}
	*/
	
	private void drawParticles(World world, BlockPos pos, EnumFacing f) {
		
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityObsidianChest t = (TileEntityObsidianChest) worldIn.getTileEntity(pos);
		if(!t.locked) InventoryHelper.dropInventoryItems(worldIn, pos, t);
		super.breakBlock(worldIn, pos, state);
	}
	
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if(stack.hasDisplayName()) {
			TileEntity t = worldIn.getTileEntity(pos);
			
			if(t instanceof TileEntityObsidianChest) {
				
				((TileEntityObsidianChest)t).setCustomName(stack.getDisplayName());
				
				
				
				
			}
			
		}

	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityObsidianChest();
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
