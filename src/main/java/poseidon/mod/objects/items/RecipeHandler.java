package poseidon.mod.objects.items;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.usable.ItemHealWart;
import poseidon.mod.objects.items.usable.ItemSummoningKey;
import poseidon.mod.objects.items.wands.ItemFlyStaff;
import poseidon.mod.objects.items.wands.ItemJupitersWand;
import poseidon.mod.objects.items.wands.ItemMozesStaff;
import poseidon.mod.objects.items.wands.ItemWayPoint;
import poseidon.mod.objects.tools.teslainfuser.ItemTeslaInfuser;
import poseidon.mod.objects.tools.teslainfuser.TeslaUpgrader;
import poseidon.mod.util.Utilities;

public class RecipeHandler {
	
	public static void registerCrafting() {
		
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:shieldblock"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.SHIELDBLOCK),
				new Object[] {"SAS", "SBS", "SOS", 'A', Items.QUARTZ, 'B', Items.REDSTONE, 'O', Item.getItemFromBlock(Blocks.OBSIDIAN), 'S', Item.getItemFromBlock(Blocks.STONE)});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:shieldblocksynch"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.SHIELDSYNCH),
				new Object[] {"SAS", "S S", "SOS", 'A', Items.QUARTZ, 'O', Item.getItemFromBlock(Blocks.OBSIDIAN), 'S', Item.getItemFromBlock(Blocks.STONE)});
		GameRegistry.addShapelessRecipe(new ResourceLocation("psm:keycard"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.KEYCARD), 
				new Ingredient[] {Ingredient.fromItem(Items.PAPER), Ingredient.fromItem(Items.GOLD_INGOT), Ingredient.fromItem(Items.REDSTONE)});
		
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:block_saver"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.BLOCK_SAVER),
				new Object[] {"SDS", "DMD", "SDS", 'S', Items.QUARTZ, 'D', Items.DIAMOND, 'M', Items.MAP});
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:velocity_changer"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.VELOCITYCHANGER), 
				new Object[] {" D ", "FSF", "DSD", 'D', Items.DIAMOND, 'F', Items.FEATHER, 'S', Items.STICK});//
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:mozes_staff"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.MOZES_STAFF), 
				new Object[] {"PSP", "PSP", "PSP", 'S', Items.STICK, 'P', Item.getItemFromBlock(Blocks.SPONGE)});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:chronomorfos"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.TIME_CHANGER), 
				new Object[] {"DID", "IOI", "DID", 'D', Items.DIAMOND, 'I', Items.IRON_INGOT, 'O', Items.CLOCK});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:mirror"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.MIRROR), 
				new Object[] {"GGG", "IDI", "III", 'D', Items.DIAMOND, 'I', Items.IRON_INGOT, 'G', Item.getItemFromBlock(Blocks.GLASS)});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:speed_upgrade"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.SPEED_UPGRADE), 
				new Object[] {"ISI", "RPR", "ISI", 'S', Item.getItemFromBlock(Blocks.STONE), 'I', Items.IRON_INGOT, 'P', Items.SUGAR, 'R', Items.REDSTONE});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:lava_upgrade"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.LAVA_UPGRADE), 
				new Object[] {"ISI", "RPR", "ISI", 'S', Item.getItemFromBlock(Blocks.STONE), 'I', Items.IRON_INGOT, 'P', Items.MAGMA_CREAM, 'R', Items.REDSTONE});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:reach_upgrade"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.REACH_UPGRADE), 
				new Object[] {"ISI", "RPR", "ISI", 'S', Item.getItemFromBlock(Blocks.STONE), 'I', Items.IRON_INGOT, 'P', Items.GLOWSTONE_DUST, 'R', Items.REDSTONE});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:empty_charm"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.EMPTY_CHARM), 
				new Object[] {"SIS", "I I", "SIS", 'S', Item.getItemFromBlock(Blocks.STONE), 'I', Items.IRON_INGOT});
//		GameRegistry.addShapedRecipe(new ResourceLocation("psm:concentrated_nether_wart"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.CONCENTRATED_NETHER_WART), 
//				new Object[] {"BRB", "GIG", "GIG", 'R', Items.GOLD_INGOT, 'B', Items.BLAZE_POWDER, 'G', Item.getItemFromBlock(Blocks.GLASS), 'I', Items.NETHER_WART});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:energy_inductor_on"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.TESLA_INFUSER), 
				new Object[] {" C ", " C ", "BIB", 'I', ItemInit.CHARGED_FLUX_BATTERY, 'C', Items.DIAMOND, 'B', Items.ENDER_EYE});

//		GameRegistry.addShapelessRecipe(new ResourceLocation("psm:chiseled_stonebricks"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.CHISELED_STONEBRICK), 
//				new Ingredient[] {Ingredient.fromItem(Item.getItemFromBlock(Blocks.STONEBRICK)), Ingredient.fromItem(Item.getItemFromBlock(Blocks.STONEBRICK)), Ingredient.fromItem(Item.getItemFromBlock(Blocks.STONEBRICK)), Ingredient.fromItem(Item.getItemFromBlock(Blocks.STONEBRICK))});//
//		GameRegistry.addShapedRecipe(new ResourceLocation("psm:alpha_cobblestone"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.ALPHA_COBBLESTONE), 
//				new Object[] {"III", "III", "III", 'I', Item.getItemFromBlock(Blocks.COBBLESTONE)});
//		GameRegistry.addShapedRecipe(new ResourceLocation("psm:castle_brick"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.CASTLE_BRICK), 
//				new Object[] {" I ", "IOI", " I ", 'I', Items.DIAMOND, 'O', Items.ENDER_EYE});//
//		GameRegistry.addShapedRecipe(new ResourceLocation("psm:chiseled_stone"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.CHISELED_STONE), 
//				new Object[] {" I ", "IOI", " I ", 'I', Items.DIAMOND, 'O', Items.ENDER_EYE});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:drawbridge"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.DRAWBRIDGE), 
				new Object[] {"RSS", "RPD", "RSS", 'S', Item.getItemFromBlock(Blocks.STONE), 'P', Item.getItemFromBlock(Blocks.PISTON), 'D', Item.getItemFromBlock(Blocks.DISPENSER), 'R', Items.REDSTONE});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:miner"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.MINERBLOCK), 
				new Object[] {"DDI", "DRI", "DDI", 'D', Blocks.OBSIDIAN, 'R', Item.getItemFromBlock(Blocks.REDSTONE_BLOCK), 'I', Items.DIAMOND});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:tesla_inductor"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.TESLAINDUCTOR), 
				new Object[] {" I ", " I ", "OIO", 'I', Items.DIAMOND, 'O', Items.ENDER_EYE});//
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:rift"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.RIFT), 
				new Object[] {"  R", "   ", "   ", 'R', Items.DIAMOND});//
		
		
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:stasis"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.STASIS), 
				new Object[] {" I ", "IOI", " I ", 'I', Items.DIAMOND, 'O', Items.ENDER_EYE});
		

		GameRegistry.addShapelessRecipe(new ResourceLocation("psm:elytron_seeds"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.ELYTRON_SEEDS, 1),
				new Ingredient[] {Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 4)), Ingredient.fromItem(Items.FEATHER), Ingredient.fromItem(Items.WHEAT_SEEDS)});

		//TODO Needs fix in recipe. Wing is useless and too tedious to craft. -> Removed from Aiolon Crystal recipe.
		//new use might be creating the elytra.
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:time_changer"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.TIME_CHANGER),
				new Object[] {" D ", "LKL", " D ", 'L', Items.LEATHER, 'K', Items.CLOCK, 'D', Items.DIAMOND});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:upgrade_module"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.UPGRADE_MODULE),
				new Object[] {" Q ", "QDQ", " Q ", 'Q', Items.QUARTZ, 'D', Items.DIAMOND});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:nether_stone"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.NETHER_STONE),
				new Object[] {"ORO", "RDR", "ORO", 'D', Item.getItemFromBlock(Blocks.COBBLESTONE), 'O', ItemInit.OBSIDIAN_INGOT, 'R',  Items.QUARTZ});
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:nether_reactor_core"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(BlockInit.NETHER_REACTOR_CORE),
				new Object[] {"ODO", "ODO", "ODO", 'O', ItemInit.OBSIDIAN_INGOT, 'D',  Items.DIAMOND});

		
//		GameRegistry.addShapedRecipe(new ResourceLocation("psm:wing"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.WING),
//				new Object[] {""});
		
		ItemWayPoint.registerRecipe();
		//RecipeHelper.registerCharms();
		ItemJupitersWand.registerRecipe();
		ItemSummoningKey.registerRecipes();
		ItemMozesStaff.registerRecipe();
		ItemTeslaInfuser.registerRecipe();
		
		//parts

		registerToolCrafting(ItemInit.OBSIDIAN_HAMMER, ItemInit.OBSIDIAN_PICKAXE, ItemInit.OBSIDIAN_AXE, ItemInit.OBSIDIAN_SPADE, ItemInit.OBSIDIAN_HOE, ItemInit.OBSIDIAN_SWORD,
							 ItemInit.EMERALD_PICKAXE, ItemInit.EMERALD_AXE, ItemInit.EMERALD_SPADE, ItemInit.EMERALD_HOE, ItemInit.EMERALD_SWORD,
							 ItemInit.DIAMOND_MULTITOOL, ItemInit.GOLD_MULTITOOL, ItemInit.IRON_MULTITOOL, ItemInit.WOOD_MULTITOOL, ItemInit.STONE_MULTITOOL, 
							 ItemInit.OBSIDIAN_MULTITOOL, ItemInit.EMERALD_MULTITOOL
							 );
		registerArmourCrafting();
	}
	
	public static void registerSmelting() {

		GameRegistry.addSmelting(Blocks.OBSIDIAN, new ItemStack(ItemInit.OBSIDIAN_INGOT), 2.0F);
//		GameRegistry.addSmelting(Blocks.STONEBRICK, new ItemStack(BlockInit.DARK_BRICK), 8.0F);
//		GameRegistry.addSmelting(new ItemStack(Blocks.STONE, 1, 4), new ItemStack(BlockInit.DIORITE_BRICK), 8.0F);
	}

	private static void registerToolCrafting( Item obham,
		               	   	  	 	 	     Item obsidianpick, Item obsidianaxe, Item obsidianspade, Item obsidianhoe, Item obsidiansword,
											 Item emeraldpick, Item emeraldaxe, Item emeraldspade, Item emeraldhoe, Item emeraldsword,
											 Item diamondmult, Item goldmult, Item ironmult, Item woodmult, Item stonemult, Item obsmult, Item ememult
											 ) {
		
		GameRegistry.addShapedRecipe(new ResourceLocation("" + diamondmult.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(diamondmult), 
				new Object[]{"XXX", "XIX", "XIX", 'X', Items.DIAMOND, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + goldmult.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(goldmult), 
				new Object[]{"XXX", "XIX", "XIX", 'X', Items.GOLD_INGOT, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + ironmult.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(ironmult), 
				new Object[]{"XXX", "XIX", "XIX", 'X', Items.IRON_INGOT, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + woodmult.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(woodmult), 
				new Object[]{"XXX", "XIX", "XIX", 'X', Item.getItemFromBlock(Blocks.PLANKS), 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + stonemult.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(stonemult), 
				new Object[]{"XXX", "XIX", "XIX", 'X', Item.getItemFromBlock(Blocks.COBBLESTONE), 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + obsmult.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(obsmult), 
				new Object[]{"XXX", "XIX", "XIX", 'X', ItemInit.OBSIDIAN_INGOT, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + ememult.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(ememult), 
				new Object[]{"XXX", "XIX", "XIX", 'X', Items.EMERALD, 'I', Items.STICK});


		
		GameRegistry.addShapedRecipe(new ResourceLocation("" + obsidianpick.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(obsidianpick), 
				new Object[]{"XXX", " I ", " I ", 'X', ItemInit.OBSIDIAN_INGOT, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + obsidianaxe.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(obsidianaxe), 
				new Object[]{" XX", " IX", " I ", 'X', ItemInit.OBSIDIAN_INGOT, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + obsidianspade.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(obsidianspade), 
				new Object[]{" X ", " I ", " I ", 'X', ItemInit.OBSIDIAN_INGOT, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + obsidianhoe.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(obsidianhoe),
				new Object[]{" XX", " I ", " I ", 'X', ItemInit.OBSIDIAN_INGOT, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + obsidiansword.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(obsidiansword),
				new Object[]{" X ", " X ", " I ", 'X', ItemInit.OBSIDIAN_INGOT, 'I', Items.STICK});
		
		GameRegistry.addShapedRecipe(new ResourceLocation("" + obham.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(obham),
				new Object[]{"XXX", " P ", " I ", 'P', Items.DIAMOND_PICKAXE, 'X', ItemInit.OBSIDIAN_INGOT, 'I', Items.IRON_INGOT});
		

		GameRegistry.addShapedRecipe(new ResourceLocation("" + emeraldpick.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(emeraldpick), 
				new Object[]{"XXX", " I ", " I ", 'X', Items.EMERALD, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + emeraldaxe.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(emeraldaxe), 
				new Object[]{" XX", " IX", " I ", 'X', Items.EMERALD, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + emeraldspade.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(emeraldspade), 
				new Object[]{" X ", " I ", " I ", 'X', Items.EMERALD, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + emeraldhoe.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(emeraldhoe),
				new Object[]{" XX", " I ", " I ", 'X', Items.EMERALD, 'I', Items.STICK});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + emeraldsword.getRegistryName()), new ResourceLocation("psm:aristois_tools"), new ItemStack(emeraldsword),
				new Object[]{" X ", " X ", " I ", 'X', Items.EMERALD, 'I', Items.STICK});
		
		
	}
	
	private static void registerArmourCrafting(){


		
	}
	
	
}
