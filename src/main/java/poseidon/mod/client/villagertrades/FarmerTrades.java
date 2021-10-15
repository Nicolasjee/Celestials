package poseidon.mod.client.villagertrades;

import java.util.Random;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import poseidon.mod.init.ItemInit;

public class FarmerTrades implements EntityVillager.ITradeList {


	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {

		//recipeList.add(new MerchantRecipe(new ItemStack(ItemInit.PACK_OF_WEED), new ItemStack(Items.EMERALD, 2)));
		
	
		
	}
	
}
