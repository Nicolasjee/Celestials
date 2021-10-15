package poseidon.mod.client.villagertrades;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import poseidon.mod.init.ItemInit;

public class WizardTrades implements ITradeList{
	
	int choice;
	public EntityVillager.PriceInfo priceInfo, priceInfo2, productInfo1, productInfo2;

	public WizardTrades(int c) {
		this.choice = c;
	}
	
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
		priceInfo = new PriceInfo(2, 5); productInfo1 = new PriceInfo(20, 35);
		priceInfo2 = new PriceInfo(1,3);
		
		int price1 = priceInfo.getPrice(random); int price2 = priceInfo2.getPrice(random); int product1 = productInfo1.getPrice(random);
		
			//new ItemStack(Items.GOLD_INGOT, price1)
		
		
	}
	
	private ItemStack getSpawnEgg(String r) {
		ItemStack spawn = new ItemStack(Items.SPAWN_EGG);
		final ResourceLocation id = new ResourceLocation(r);
		applyEntityIdToItemStack(spawn, id);
		return spawn;
	}
	
	private void setDispenser(World worldIn, double x, double y, double z, EntityPlayer playerIn) {
		worldIn.setBlockState(new BlockPos(x, y + 5.0D, z), Blocks.CHEST.getDefaultState());
		Block block = worldIn.getBlockState(new BlockPos(x, y + 5.0D, z)).getBlock();

		TileEntity tile = worldIn.getTileEntity(new BlockPos(x,y+5.0D,z));
		IItemHandler item = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		
		ItemStack spawn = new ItemStack(Items.SPAWN_EGG);
		final ResourceLocation id = new ResourceLocation("minecraft:parrot");
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
