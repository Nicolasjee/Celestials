package poseidon.mod.objects.block.general;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IHasModel;

public class BlockBase extends Block implements IHasModel {
	
	public BlockBase(String name, Material material, float light, float hard, float res, int harvest) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		
		setLightLevel(light);
		setHardness(hard);
		setResistance(res);
		
		setCreativeTab(Main.ARISTOISITEMS);
		
		setHarvestLevel("pickaxe", harvest);
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	

//
//	@Override
//	public void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
//		
//			double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
//			EntityXPOrb orb = new EntityXPOrb(worldIn, x, y, z, 10000);
//			worldIn.spawnEntity(orb);
//		
//	}
	
	//didn't work without this method.

	/*
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Random rand = new Random();
		double d0 = (double)pos.getX() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double d1 = (double)pos.getY() + 0.55D - (double)(rand.nextFloat() * 0.1F) + 1.0D;
        double d2 = (double)pos.getZ() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double d3 = (double)(0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);

        double i = 0.0D;
        double k = 0.0D;
        
        while(i < 5.0D) {
        	worldIn.spawnParticle(EnumParticleTypes.END_ROD, d0 - i, d1 - k, d2 - i, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
        	i = i + 0.01D;
        	k = k + 0.002D;
        }
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
*/
	
	
	
	private void setDispenser(World worldIn, double x, double y, double z, EntityPlayer playerIn) {
		worldIn.setBlockState(new BlockPos(x, y + 5.0D, z), Blocks.CHEST.getDefaultState());
		Block block = worldIn.getBlockState(new BlockPos(x, y + 5.0D, z)).getBlock();

		TileEntity tile = worldIn.getTileEntity(new BlockPos(x,y+5.0D,z));
		IItemHandler item = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		
		ItemStack spawn = new ItemStack(Items.SPAWN_EGG);
		ResourceLocation id = new ResourceLocation("minecraft:parrot");
		applyEntityIdToItemStack(spawn, id);
		ItemHandlerHelper.giveItemToPlayer(playerIn, spawn);
		
		item.insertItem(1, spawn, false);
		worldIn.setBlockState(new BlockPos(x + 1.0D, y + 5.0D, z), Blocks.REDSTONE_BLOCK.getDefaultState());
		worldIn.setBlockState(new BlockPos(x,y,z), Blocks.OBSIDIAN.getDefaultState());
	}
	
    public static void applyEntityIdToItemStack(ItemStack stack, ResourceLocation entityId) {
        NBTTagCompound nbttagcompound = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setString("id", entityId.toString());
        nbttagcompound.setTag("EntityTag", nbttagcompound1);
        stack.setTagCompound(nbttagcompound);
    }
}