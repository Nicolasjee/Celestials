package poseidon.mod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import poseidon.mod.objects.items.ItemUpgradeModule;
import poseidon.mod.objects.items.charms.CharmBase;
import poseidon.mod.objects.items.general.ItemBase;
import poseidon.mod.objects.items.general.ItemBlockSaver;
import poseidon.mod.objects.items.general.ItemFluxBattery;
import poseidon.mod.objects.items.general.ItemFuel;
import poseidon.mod.objects.items.general.ItemHidden;
import poseidon.mod.objects.items.general.ItemKeyCard;
import poseidon.mod.objects.items.general.ItemMaterial;
import poseidon.mod.objects.items.general.ItemUpgrade;
import poseidon.mod.objects.items.usable.CustomSnowball;
import poseidon.mod.objects.items.usable.ItemBluestoneDust;
import poseidon.mod.objects.items.usable.ItemRift;
import poseidon.mod.objects.items.usable.ItemStasis;
import poseidon.mod.objects.items.usable.ItemSummoningKey;
import poseidon.mod.objects.items.usable.Mirror;
import poseidon.mod.objects.items.wands.ItemBadJuju;
import poseidon.mod.objects.items.wands.ItemFlyStaff;
import poseidon.mod.objects.items.wands.ItemMozesStaff;
import poseidon.mod.objects.items.wands.ItemTimeChanger;
import poseidon.mod.objects.tools.Multi;
import poseidon.mod.objects.tools.MultiStick;
import poseidon.mod.objects.tools.ToolAxe;
import poseidon.mod.objects.tools.ToolHoe;
import poseidon.mod.objects.tools.ToolPickaxe;
import poseidon.mod.objects.tools.ToolSpade;
import poseidon.mod.objects.tools.ToolSword;
import poseidon.mod.objects.tools.teslainfuser.ItemTeslaInfuser;
import poseidon.mod.objects.tools.teslainfuser.ItemTeslaInfuserOff;
import poseidon.mod.objects.tools.toolsplus.ParticleExtend;
import poseidon.mod.util.Reference;

public class ItemInit {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	/*
	 *  WOOD(0, 59, 2.0F, 0.0F, 15),
     *  STONE(1, 131, 4.0F, 1.0F, 5),
     *  IRON(2, 250, 6.0F, 2.0F, 14),
     *  DIAMOND(3, 1561, 8.0F, 3.0F, 10),
     *  GOLD(0, 32, 12.0F, 0.0F, 22);
	 */
	
	//Materials		(name, harvest, uses, eff, damage, enchant)
	public static final ToolMaterial VELOCITY = EnumHelper.addToolMaterial("velocity", 1, 450, 1.4F, 1.0F, 20);
	
	
	public static final ToolMaterial FIRECHARM = EnumHelper.addToolMaterial("firecharm", 0, 2000, 0.0F, 0.0F, 0);
	public static final ToolMaterial FALLCHARM = EnumHelper.addToolMaterial("fallcharm", 0, 10, 0.0F, 0.0F, 0);
	public static final ToolMaterial DEPTCHARM = EnumHelper.addToolMaterial("deptcharm", 0, 1000, 0.0F, 0.0F, 0);
	public static final ToolMaterial PROJCHARM = EnumHelper.addToolMaterial("projcharm", 0, 100, 0.0F, 0.0F, 0);
	public static final ToolMaterial FLIDCHARM = EnumHelper.addToolMaterial("glidcharm", 0, 500, 0.0F, 0.0F, 0);
	public static final ToolMaterial REGENCHARM = EnumHelper.addToolMaterial("regencharm", 0, 200, 0.0F, 0.0F, 0);
	
	public static final ToolMaterial OBSIDIAN_TOOL = EnumHelper.addToolMaterial("obsidian_tool", 1, 600, 5.0F, 2.5F, 14);
	public static final ToolMaterial OBSIDIANHAMMER = EnumHelper.addToolMaterial("obsidian_hammer", 2, 155, 4.0F, 1.0F, 24);
	
	public static final ToolMaterial JUPITER = EnumHelper.addToolMaterial("jupiter", 2, 700, 8.0F, 9.0F, 17);
	public static final ToolMaterial DEMON = EnumHelper.addToolMaterial("demon", 1, 210, 6.5F, 2.0F, 13);
	public static final ToolMaterial ANGEL = EnumHelper.addToolMaterial("angel", 1, 230, 6.5F, 3.0F, 13);
	public static final ToolMaterial DRILL = EnumHelper.addToolMaterial("drill", 2, 900, 6.0F, 2.0F, 4);
	public static final ToolMaterial EMERALD = EnumHelper.addToolMaterial("emerald", 3, 1300, 7.0F, 4.0F, 14);
	public static final ToolMaterial ENERGY = EnumHelper.addToolMaterial("energy", 0, 800, 0.0F, 5.0F, 10);
	public static final ToolMaterial INFINITY = EnumHelper.addToolMaterial("infinity", 0, 800, 0.0F, 10.0F, 10);
	public static final ToolMaterial INFERNO = EnumHelper.addToolMaterial("inferno", 0, 90, 1.0F, 1.0F, 10);
	public static final ToolMaterial CREATIVE_TOOLM = EnumHelper.addToolMaterial("creative", 3, 999999999, 9999999999999.0F, 999999999.0F, 24);
	public static final ToolMaterial TSPEED_UPGRADE = EnumHelper.addToolMaterial("speedupgrade", 0, 96, 0.0F, 0.0F, 0);
	public static final ToolMaterial TLAVA_UPGRADE = EnumHelper.addToolMaterial("lavaupgrade", 0, 192, 0.0F, 0.0F, 0);
	public static final ToolMaterial TREACH_UPGRADE = EnumHelper.addToolMaterial("reachupgrade", 0, 96, 0.0F, 0.0F, 0);
	
	//Multi Tools																	(name, harvest, uses, eff, damage, enchant)
		public static final ToolMaterial WOOD_MULTI = EnumHelper.addToolMaterial("wood_multi", 0, 178, 2.0F, 0.0F, 15);
		public static final ToolMaterial STONE_MULTI = EnumHelper.addToolMaterial("stone_multi", 1, 392, 4.0F, 1.0F, 5);
		public static final ToolMaterial IRON_MULTI = EnumHelper.addToolMaterial("iron_multi", 2, 734, 6.0F, 2.0F, 14);
		public static final ToolMaterial GOLD_MULTI = EnumHelper.addToolMaterial("gold_multi", 0, 120, 12.0F, 0.0F, 22);
		public static final ToolMaterial DIAMOND_MULTI = EnumHelper.addToolMaterial("diamond_multi", 3, 4623, 8.0F, 3.0F, 10);
		public static final ToolMaterial OBSIDIAN_MULTI = EnumHelper.addToolMaterial("obsidian_multi", 2, 2670, 6.0F, 2.0F, 10);
		public static final ToolMaterial EMERALD_MULTI = EnumHelper.addToolMaterial("emerald_multitool", 3, 3560, 13.0F, 4.0F, 12);
		
	public static final ArmorMaterial COPPER_ARMOUR = EnumHelper.addArmorMaterial("copper_armour", Reference.MODID + ":copper", 15, new int[]{2, 5, 6, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F);	
	
	
	//Tools
	public static final Item WOOD_MULTITOOL = new Multi("wood_multitool", WOOD_MULTI);
	public static final Item STONE_MULTITOOL = new Multi("stone_multitool", STONE_MULTI);
	public static final Item IRON_MULTITOOL = new Multi("iron_multitool", IRON_MULTI);
	public static final Item GOLD_MULTITOOL = new Multi("gold_multitool", GOLD_MULTI);
	public static final Item DIAMOND_MULTITOOL = new Multi("diamond_multitool", DIAMOND_MULTI);
	
	//public static final Item TEST_ITEM = new Testing("test_item");
	
	//TODO Handle for tools: increase stats.. -> in expansion table -> nbt change (vanilla tools too) -> event checks for block breaks ??? or change toolmat? 
	
	public static final Item OBSIDIAN_MULTITOOL = new Multi("obsidian_multitool", OBSIDIAN_MULTI);
	public static final Item EMERALD_MULTITOOL = new Multi("emerald_multitool", EMERALD_MULTI);
	public static final Item OBSIDIAN_SWORD = new ToolSword("obsidian_sword", OBSIDIAN_TOOL, false);
	public static final Item OBSIDIAN_PICKAXE = new ToolPickaxe("obsidian_pickaxe", OBSIDIAN_TOOL);	
	public static final Item OBSIDIAN_AXE = new ToolAxe("obsidian_axe", OBSIDIAN_TOOL);
	public static final Item OBSIDIAN_SPADE = new ToolSpade("obsidian_spade", OBSIDIAN_TOOL);
	public static final Item OBSIDIAN_HOE = new ToolHoe("obsidian_hoe", OBSIDIAN_TOOL);
	public static final Item EMERALD_SWORD = new ToolSword("emerald_sword", EMERALD, false);
	public static final Item EMERALD_PICKAXE = new ToolPickaxe("emerald_pickaxe", EMERALD);
	public static final Item EMERALD_AXE = new ToolAxe("emerald_axe", EMERALD);
	public static final Item EMERALD_SPADE = new ToolSpade("emerald_spade", EMERALD);
	public static final Item EMERALD_HOE = new ToolHoe("emerald_hoe", EMERALD);
	public static final Item OBSIDIAN_HAMMER = new ParticleExtend("obsidian_hammer", OBSIDIANHAMMER, false);
	
	//public static final Item DEMONIC_KNIFE = new ToolDemonicKnife("demon_knife", DEMON);
	//public static final Item WAYPOINT = new ItemWayPoint("checkpoint_marker", 1, false);
	public static final Item VELOCITYCHANGER = new ItemFlyStaff("velocity_changer", false);
	public static final Item MOZES_STAFF = new ItemMozesStaff("mozes_staff", 1, false);
	public static final Item TIME_CHANGER = new ItemTimeChanger("chronomorfos");
	
	public static final Item BADJUJU = new ItemBadJuju("badjuju");
	public static final Item KEYCARD = new ItemKeyCard("keycard");
	
	public static final Item STASIS = new ItemStasis("stasis", 16, false);
	public static final Item RIFT = new ItemRift("rift_pearl", false);
	public static final Item ELYTRON_DUST = new ItemBase("elytron_crystal", 64, false); // elytra ammo

	public static final Item KILL_STICK = new MultiStick("kill_stick", CREATIVE_TOOLM);
	public static final Item MIRROR = new Mirror("mirror", 1);
	
	
	public static final Item ICY_SNOWBALL = new CustomSnowball("icy_snowball");
	public static final Item EXPLOSIVE_SNOWBALL = new CustomSnowball("explosive_snowball");
	public static final Item ELYTRON_SEEDS = new ItemBluestoneDust("elytron_seeds", 64, false);

	public static final Item BLOCK_SAVER = new ItemBlockSaver("block_saver", 64, false);
	public static final Item BLOCK_SAVER_OVERWORLD = new ItemBlockSaver("block_saver_overworld", 1, true);
	public static final Item BLOCK_SAVER_NETHER = new ItemBlockSaver("block_saver_nether", 1, true);
	public static final Item BLOCK_SAVER_END = new ItemBlockSaver("block_saver_end", 1, true);

	public static final Item WARRANT = new ItemHidden("warrant", 1, false);
	//public static final Item SEALS = new ItemBase("seal", 64, true);
	//public static final Item KNOWLEDGE_BOOK = new ItemKnowledgeBook("knowledge_book", 64, false); //Chance to fix anvils 3/4? && 
	//public static final Item SUMMONING_KEY_MAGIC = new ItemSummoningKey("summoning_key_magic", 1, false);
	public static final Item SUMMONING_KEY_NETHER = new ItemSummoningKey("summoning_key_nether", 1, false);

	//public static final Item BLUESTONE_DUST = new ItemBluestoneDust("bluestone_dust", 64, false);
	public static final Item OBSIDIAN_INGOT = new ItemMaterial("obsidian_ingot", 64, false);
	public static final Item HOT_COALS = new ItemFuel("hot_coals", 64, false);
	//public static final Item OBSIDIAN_KEY = new ItemKey("obsidian_key", 64, false);
	//public static final Item SAND_KEY = new ItemKey("desert_key", 64, false);
	
	public static final Item SPEED_UPGRADE = new ItemUpgrade("speed_upgrade", TSPEED_UPGRADE);
	public static final Item REACH_UPGRADE = new ItemUpgrade("reach_upgrade", TREACH_UPGRADE);
	public static final Item LAVA_UPGRADE = new ItemUpgrade("lava_upgrade", TLAVA_UPGRADE);
	
	public static final Item EMPTY_CHARM = new ItemBase("empty_charm", 64, false);
	public static final Item FIRE_PROTECTION_CHARM = new CharmBase("fire_protection_charm", FIRECHARM);
	public static final Item PROJECTILE_CHARM = new CharmBase("projectile_protection_charm", PROJCHARM);
	public static final Item DEPTH_STRIDER_CHARM = new CharmBase("depth_strider_charm", DEPTCHARM);
	public static final Item FALL_CHARM = new CharmBase("slowfall_charm", FIRECHARM);
	public static final Item REGENERATION_CHARM = new CharmBase("regeneration_charm", REGENCHARM);
	
	//public static final Item record = new Disc("customrecord", SoundsHandler.BADISLAND);
	public static final Item TESLA_INFUSER = new ItemTeslaInfuser("tesla_infuser", ENERGY);
	public static final Item TESLA_INFUSER_OFF = new ItemTeslaInfuserOff("tesla_infuser_off");
	
	public static final Item UPGRADE_MODULE = new ItemUpgradeModule("upgrade_module");
	public static final Item CHARGED_FLUX_BATTERY = new ItemFluxBattery("charged_flux_battery", true);
	public static final Item EMPTY_FLUX_BATTERY = new ItemFluxBattery("empty_flux_battery", false);

}
