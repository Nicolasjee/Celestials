package poseidon.mod.util;

import java.util.Random;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.IForgeRegistry;
import poseidon.mod.client.villagertrades.LuciferTrades;
import poseidon.mod.client.villagertrades.MinerTrades;
import poseidon.mod.client.villagertrades.WizardTrades;

@ObjectHolder(Reference.MODID)
public class ModProfessions {

	public static final VillagerProfession sicario = null;
	public static final VillagerProfession miner = null;
	public static final VillagerProfession reader = null;
	public static final VillagerProfession trestop = null;


	public static VillagerCareer drug_buyer;
	public static VillagerCareer provider;
	public static VillagerCareer wizard;
	public static VillagerCareer dragonslayer;

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void onEvent(final RegistryEvent.Register<VillagerProfession> event) {
			final IForgeRegistry<VillagerProfession> registry = event.getRegistry();

			// DEBUG
			System.out.println("Registering villager professions");

			registry.register(new VillagerProfession(Reference.MODID + ":sicario",
					Reference.MODID + ":textures/entity/sicario.png",
					Reference.MODID + ":textures/entity/sicario.png"));
			
			registry.register(new VillagerProfession(Reference.MODID + ":miner",
					Reference.MODID + ":textures/entity/miner.png",
					Reference.MODID + ":textures/entity/miner.png"));

			registry.register(new VillagerProfession(Reference.MODID + ":reader",
					Reference.MODID + ":textures/entity/reader.png",
					Reference.MODID + ":textures/entity/reader.png"));
			registry.register(new VillagerProfession(Reference.MODID + ":trestop",
					Reference.MODID + ":textures/entity/trestop.png",
					Reference.MODID + ":textures/entity/trestop.png"));
		}

	}

	public static void associateCareersAndTrades()
    {
        // DEBUG

        drug_buyer = (new VillagerCareer(sicario, "drug_buyer"))
                .addTrade(1, new TradeWeedForEmerald());
        provider = (new VillagerCareer(miner, "miner")).addTrade(1, new MinerTrades(1)).addTrade(2, new MinerTrades(2));
        wizard = (new VillagerCareer(reader, "reader")).addTrade(1, new WizardTrades(2));
        dragonslayer = (new VillagerCareer(trestop, "kingofhell")).addTrade(1, new LuciferTrades(1));
        
    }
	
	public static class TradeSoulsForSeals implements ITradeList {
		
		public EntityVillager.PriceInfo priceInfo, priceInfo2;
		
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
			
			priceInfo = new PriceInfo(1, 3); priceInfo2 = new PriceInfo(24,37);
			int price = priceInfo.getPrice(random); int price2 = priceInfo2.getPrice(random);
			

			
		}
	}

	public static class TradeWeedForEmerald implements ITradeList {
		
		public EntityVillager.PriceInfo priceInfo, priceInfo2;
		
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
			
			priceInfo = new PriceInfo(1, 3); priceInfo2 = new PriceInfo(24,37);
			int price = priceInfo.getPrice(random); int price2 = priceInfo2.getPrice(random);
			
			}
	}


}
