package poseidon.mod.client.villagertrades;

import java.util.Random;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class MinerTrades implements ITradeList{

	public EntityVillager.PriceInfo priceInfoTNT, priceInfoLog, priceInfoTNTget, priceInfoGlowstone, priceInfoGlowstone2;
	int choice;
	
	public MinerTrades(int choice) {
		this.choice = choice;
	}
	
	@Override
	public void addMerchantRecipe(IMerchant mernchant, MerchantRecipeList recipeList, Random random) {
		
		priceInfoTNT = new PriceInfo(1, 3); priceInfoTNTget = new PriceInfo(43, 64); priceInfoLog = new PriceInfo(26,64); priceInfoGlowstone = new PriceInfo(5, 15); priceInfoGlowstone2 = new PriceInfo(15, 22);
		int price = priceInfoTNT.getPrice(random); int price11 = priceInfoTNTget.getPrice(random); int price2 = priceInfoLog.getPrice(random); int price3 =priceInfoGlowstone.getPrice(random); int price31 = priceInfoGlowstone2.getPrice(random);
		
		if(choice == 2) {
			recipeList.add(new MerchantRecipe(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_FIRE_RESISTANCE), new ItemStack(Items.DIAMOND, price)));
			recipeList.add(new MerchantRecipe(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_HEALING), new ItemStack(Items.IRON_INGOT, price3)));
		}
		if(choice == 1) {
			recipeList.add(new MerchantRecipe(new ItemStack(Item.getItemFromBlock(Blocks.GLOWSTONE), price3), new ItemStack(Items.GOLD_INGOT, price31)));
			recipeList.add(new MerchantRecipe(new ItemStack(Item.getItemFromBlock(Blocks.LOG), price2), new ItemStack(Items.IRON_INGOT, price2)));
			recipeList.add(new MerchantRecipe(new ItemStack(Item.getItemFromBlock(Blocks.TNT), price), new ItemStack(Items.IRON_INGOT, price11)));
		}
	}
	
}
