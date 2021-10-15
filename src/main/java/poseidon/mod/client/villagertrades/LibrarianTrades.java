package poseidon.mod.client.villagertrades;

import java.util.Random;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import poseidon.mod.init.ItemInit;

public class LibrarianTrades implements EntityVillager.ITradeList{
	
	int choice;
	public EntityVillager.PriceInfo priceInfo, priceInfo2, productInfo1, productInfo2;
	
	public LibrarianTrades(int a) {
		this.choice = a;
	}
	
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
		
		priceInfo = new PriceInfo(14, 25); productInfo1 = new PriceInfo(1, 4);
		priceInfo2 = new PriceInfo(1,3);
		
		int price1 = priceInfo.getPrice(random); int price2 = priceInfo2.getPrice(random); int product1 = productInfo1.getPrice(random);
		
	}

}
