package poseidon.mod.client.villagertrades;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import poseidon.mod.init.ItemInit;

public class LuciferTrades implements ITradeList{
	
	int choice;
	public EntityVillager.PriceInfo priceInfo, priceInfo2, productInfo1, productInfo2;

	public LuciferTrades(int c) {
		this.choice = c;
	}
	
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
		
		priceInfo = new PriceInfo(4, 7); productInfo1 = new PriceInfo(1, 5);
		priceInfo2 = new PriceInfo(1,3);
		
		int price1 = priceInfo.getPrice(random); int price2 = priceInfo2.getPrice(random); int product1 = productInfo1.getPrice(random);
		
			

		
		
	}
	
	private static ItemStack getTileEntity(String r) {
		ResourceLocation res = new ResourceLocation(r);
		TileEntityMobSpawner mob = new TileEntityMobSpawner();
		mob.getSpawnerBaseLogic().setEntityId(res);
		Block block = mob.getWorld().getBlockState(mob.getPos()).getBlock();
		
		return new ItemStack(Item.getItemFromBlock(block));
	}
	
	private static ItemStack getVile(int size, ItemStack stack) {
		
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} //Gets The compound which holds keys!
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", size);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		if(nbt.hasKey("Activated")) {
			nbt.setBoolean("Activated", nbt.getBoolean("Activated"));
			} else {
			nbt.setBoolean("Activated", false);
		}
		if(nbt.hasKey("Active")) {
			nbt.setInteger("Active", nbt.getInteger("Active"));
			} else {
			nbt.setInteger("Active", 0);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		if(nbt.hasKey("Size")) {
			nbt.setInteger("Size", nbt.getInteger("Size"));
			} else {
			nbt.setInteger("Size", size);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		stack.setTagCompound(nbt);
		
		return stack;
	}
	
}
