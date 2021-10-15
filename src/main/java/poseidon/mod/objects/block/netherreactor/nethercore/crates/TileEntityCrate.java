package poseidon.mod.objects.block.netherreactor.nethercore.crates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import poseidon.mod.init.ItemInit;

public class TileEntityCrate extends TileEntity implements ITickable {

	public String type = "";
	public Item[] items;
	public List<ItemStack> itemStacks;
	
	public boolean init = false;
	public boolean locked = true;
	public boolean setType = true;
	
	public String ARMOR = "armor";
	public String TOOLS = "tools";
	public String TRANSPORTATION = "transportation";
	public String CONSUMABLES = "consumables";
	public String MATERIALS = "materials";
	public String RESOURCES = "resources";
	public String FARM = "farm";
	public String LEGENDARY = "legendary";
	public String REDSTONE = "redstone";
	
	public String[] stringArray = new String[] {ARMOR, TOOLS, TRANSPORTATION, CONSUMABLES, MATERIALS, RESOURCES, FARM, LEGENDARY, REDSTONE};
	
	@Override
	public void update() {
		if(!init && type != "" && typeExists(type)) {
			items = getItems();
			init = true;
		}
		if(!locked && !world.isRemote) {
			if(init) {
				List<Item> selection = getSelectedItems();
				for(int i = 0; i < selection.size(); i++) {
					Item item = selection.get(i);
					InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(item, getStackSize(item)));
				}
				world.destroyBlock(pos, false);
			}
		}
	}
	
	//Public method to be accessed from other locations
	//setType boolean prevents the NetherCrateBase block to change the type when right clicked.
	public void setType(String typeGiven) {
		if(typeExists(typeGiven)) {
			type = typeGiven;
			items = getItems();
			init = true;
			setType = false;
			return;
		}
		System.out.println("Invalid type: " + typeGiven);
	}
	
	private int getStackSize(Item item) {
		
		if(type == LEGENDARY || type == TOOLS || type == ARMOR) return 1;
		if(type == REDSTONE || type == CONSUMABLES) return getRandom(12, 20);
		
		if(type == TRANSPORTATION) return getRandom(40, 64);
		
		if(type == MATERIALS && new ItemStack(item).isStackable()) {
			return getRandom(3,8);
		} else if(type == MATERIALS && !(new ItemStack(item).isStackable())) {
			return 1;
		}
		
		if(type == RESOURCES) return getRandom(5,12);
		
		if(type == FARM && new ItemStack(item).isStackable()) return getRandom(12,20);
		if(type == FARM && !(new ItemStack(item).isStackable())) return 1;
		
		return 16;
		
	}
	
	private int getRandom(int minimum, int maximum) {
		Random rn = new Random();
		int range = maximum - minimum;		
		int b = rn.nextInt(range) + minimum;
		return b;
	}
	
	private List<Item> getSelectedItems() {
		

		List<Item> selection = new ArrayList<Item>();
		//Adding one. Because a loop goes till loop-1 if i < loop? right?
		int loop = getLoopCount();
		
		if(type == TRANSPORTATION) {
			for(int i = 0; i < items.length; i++) {
				selection.add(items[i]);
			}
			return selection;
		}
		
		//Getting 4 items from a stackable list
		for(int i = 0; i < loop; i++) {
				
			//reducting items.length every i'th time. So we don't get invalid numbers
			Random rand = new Random();
			int length = items.length;
			int range = length - 1;
			int number = rand.nextInt(range) + 1;
			
			while(selection.contains(items[number])) {
				number = rand.nextInt(range) +1;
				System.out.println("Invalid number: " + number + ", loopCount: " + loop + ", type: " + type);
			}
				
			selection.add(items[number]);
				
		}
			
		return selection;
			

	}
	
	//returns an integer that determines how much !different! items will be selected to drop.
	private int getLoopCount() {
		if(type == LEGENDARY) return 1;
		if(type == REDSTONE) return 4;
		if(type == TOOLS) return 3;
		if(type == FARM) return 5;
		if(type == MATERIALS) return 3;
		if(type == TRANSPORTATION) return 2;
		if(type == CONSUMABLES) return 4;
		if(type == ARMOR) return 4;
		if(type == RESOURCES) return 4;
		else {
			System.out.println("Returned default 4");
			return 4;
		}
	}
	
	//Checks if the type has items that are stackable
	private boolean isTypeStackable() {
		if(type == ARMOR || type == TOOLS || type == LEGENDARY) return false;
		else return true;
	}
	
	private boolean typeExists(String type) {
		if(type == ARMOR || type == RESOURCES || type == TOOLS || type == CONSUMABLES || type == REDSTONE ||
		   type == TRANSPORTATION || type == FARM || type == LEGENDARY || type == MATERIALS) return true;
		else return false;
	}
	
	private Item[] getItems() {
		
		if(type == ARMOR) return new Item[] {Items.LEATHER_LEGGINGS, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_HELMET, Items.DIAMOND_BOOTS, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_HELMET, Items.GOLDEN_BOOTS,
											   Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_HELMET, Items.CHAINMAIL_BOOTS, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_LEGGINGS,
											   Items.LEATHER_BOOTS, Items.LEATHER_CHESTPLATE, Items.LEATHER_HELMET, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS};

		if(type == RESOURCES) return new Item[] {Items.REDSTONE, Items.IRON_INGOT, Items.GOLD_INGOT, Items.COAL, Items.FLINT, Items.EMERALD, ItemInit.OBSIDIAN_INGOT};
		
		if(type == TOOLS) return new Item[] {Items.DIAMOND_AXE, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_SWORD,
											   Items.GOLDEN_AXE, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_SWORD,
											   Items.IRON_AXE, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_SWORD,
											   ItemInit.DIAMOND_MULTITOOL, ItemInit.EMERALD_AXE, ItemInit.EMERALD_PICKAXE,
											   ItemInit.EMERALD_SWORD, ItemInit.EMERALD_SPADE, ItemInit.EMERALD_MULTITOOL, ItemInit.OBSIDIAN_MULTITOOL,
											   ItemInit.OBSIDIAN_SPADE, ItemInit.OBSIDIAN_AXE, ItemInit.OBSIDIAN_SWORD, ItemInit.OBSIDIAN_PICKAXE,
											   ItemInit.GOLD_MULTITOOL, ItemInit.IRON_MULTITOOL, ItemInit.STONE_MULTITOOL};
		
		if(type == CONSUMABLES) return new Item[] {Items.APPLE, Items.CAKE, Items.APPLE, Items.BAKED_POTATO, Items.COOKED_BEEF, Items.COOKED_PORKCHOP, Items.BREAD, Items.PUMPKIN_PIE, Items.COOKED_CHICKEN, Items.COOKED_MUTTON, Items.RABBIT_STEW};
	
		if(type == TRANSPORTATION) return new Item[] {Item.getItemFromBlock(Blocks.RAIL), Item.getItemFromBlock(Blocks.GOLDEN_RAIL)};
	
		if(type == FARM) return new Item[] {Items.WHEAT_SEEDS, Item.getItemFromBlock(Blocks.REEDS), Items.BONE, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.POTATO, Items.CARROT};
	
		if(type == LEGENDARY) return new Item[] {ItemInit.STASIS, ItemInit.RIFT, ItemInit.VELOCITYCHANGER, ItemInit.DEPTH_STRIDER_CHARM, ItemInit.FIRE_PROTECTION_CHARM, ItemInit.PROJECTILE_CHARM, ItemInit.FALL_CHARM,
				   								Items.SHULKER_SHELL, Items.TOTEM_OF_UNDYING, Item.getItemFromBlock(Blocks.BEACON), Items.NETHER_STAR, Item.getItemFromBlock(Blocks.DIAMOND_BLOCK)};
	
		if(type == MATERIALS) return new Item[] {Items.GHAST_TEAR, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.EXPERIENCE_BOTTLE, Items.BLAZE_ROD, Item.getItemFromBlock(Blocks.TNT), Items.SPECTRAL_ARROW,
	               Items.GHAST_TEAR, Items.SADDLE, Items.LEATHER, Items.RABBIT_HIDE, Item.getItemFromBlock(Blocks.ANVIL), Items.ARMOR_STAND, Items.DIAMOND_HORSE_ARMOR, Items.IRON_HORSE_ARMOR,
	               Items.GOLDEN_HORSE_ARMOR, Items.MAGMA_CREAM, Items.GOLDEN_CARROT, Items.DRAGON_BREATH, Items.BLAZE_ROD};
		
		if(type == REDSTONE) return new Item[] {Item.getItemFromBlock(Blocks.REDSTONE_BLOCK), Item.getItemFromBlock(Blocks.STONE_BUTTON), Item.getItemFromBlock(Blocks.DISPENSER), Item.getItemFromBlock(Blocks.DAYLIGHT_DETECTOR),
				Item.getItemFromBlock(Blocks.REDSTONE_LAMP), Item.getItemFromBlock(Blocks.REDSTONE_TORCH), Item.getItemFromBlock(Blocks.UNPOWERED_COMPARATOR), Item.getItemFromBlock(Blocks.UNPOWERED_REPEATER),
				Item.getItemFromBlock(Blocks.STICKY_PISTON)};
		
		
		System.out.println("type not recognized: " + type + ", return an empty item array");
		return new Item[] {};
		
	}
	
}
