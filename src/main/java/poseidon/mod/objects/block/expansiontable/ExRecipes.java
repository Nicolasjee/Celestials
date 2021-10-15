package poseidon.mod.objects.block.expansiontable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.usable.ItemSummoningKey;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.objects.tools.teslainfuser.TeslaUpgrader;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;
import poseidon.mod.util.helpers.RandomEnchantmentUtil;

public class ExRecipes implements TeslaProperties {
	
	static boolean getEnch = false;

	public static ItemStack getOuput(ItemStack a, ItemStack b, ItemStack c, World world, BlockPos pos) {
		ItemStack output = ItemStack.EMPTY;
		
		//if(a.getItem() == Item)
		if(a.getItem() == Items.DIAMOND && b.getItem() == Items.BOOK && c.getItem() == Items.DIAMOND) return Utilities.enchantedBook();
		if(a.getItem() == Items.DIAMOND && b.getItem() == ItemInit.UPGRADE_MODULE && c.getItem() == Items.DIAMOND) return upgrade(pos, world);
		if(a.getItem() == ItemInit.UPGRADE_MODULE && b.getItem() == ItemInit.TESLA_INFUSER_OFF && c.getItem() == Items.REDSTONE) return tesla(a, b, world);
		return output;
	}
	
	private static ItemStack upgrade(BlockPos pos, World world) {
		if(energyShieldsAlligned(pos, world)) {
			if(!world.isRemote) {
				ParticleUtil.upgrade((WorldServer)world, pos, pos.north(3), 1.0D);
				ParticleUtil.upgrade((WorldServer)world, pos, pos.east(3), 1.0D);
				ParticleUtil.upgrade((WorldServer)world, pos, pos.west(3), 1.0D);
				ParticleUtil.upgrade((WorldServer)world, pos, pos.south(3), 1.0D);
				
				ParticleUtil.upgrade((WorldServer)world, pos.east(3), pos.north(3), 0.25D);
				ParticleUtil.upgrade((WorldServer)world, pos.south(3), pos.east(3), 0.25D);
				ParticleUtil.upgrade((WorldServer)world, pos.south(3), pos.west(3), 0.25D);
				ParticleUtil.upgrade((WorldServer)world, pos.north(3), pos.west(3), 0.25D);
			}
		}
		ItemStack upgrade = new ItemStack(ItemInit.UPGRADE_MODULE);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("upgradePower", Utilities.getRandomPower());
		upgrade.setTagCompound(nbt);
		Main.proxy.playSoundBlock(pos, world, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.0F);
		return upgrade;
	}
	
	private static boolean energyShieldsAlligned(BlockPos pos, World world) {
		Block block1 = world.getBlockState(pos.north(3)).getBlock();
		Block block2 = world.getBlockState(pos.south(3)).getBlock();
		Block block3 = world.getBlockState(pos.west(3)).getBlock();
		Block block4 = world.getBlockState(pos.east(3)).getBlock();
		
		if(block1 == BlockInit.ENERGY_SHIELD && block2 == BlockInit.ENERGY_SHIELD && block3 == BlockInit.ENERGY_SHIELD && block4 == BlockInit.ENERGY_SHIELD) return true;
		else return false;
	}
	
	private static ItemStack enchantedBook(ItemStack book) {
		
		Random random = new Random();
		ItemStack ret = new ItemStack(Items.ENCHANTED_BOOK);
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.clear();
		int num = 1;
		
        for (Enchantment enchantment : Enchantment.REGISTRY) {
            if (enchantment.type != null) {
                for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); ++i) {
                    items.add(RandomEnchantmentUtil.getEnchantedItemStack(new EnchantmentData(enchantment, enchantment.getMaxLevel())));
                }
            }
        }
		
        num = getInteger(random, items);
        if(num == 99) num = getInteger(random, items);
        
		return items.get(num);
	}
	
	private static int getInteger(Random random, List<ItemStack> items) {
		int num = 1 + random.nextInt(items.size());
		return num;
	}
	

		
	/*
	private static ItemStack addPower(Item items, ItemStack stack) {
		if(stack.getItem() instanceof ItemEnergyInductor && stack.hasTagCompound()) {
			ItemEnergyInductor item = (ItemEnergyInductor) stack.getItem();
			NBTTagCompound nbt = stack.getTagCompound();
			System.out.println("power1: " + nbt.getString("power1"));
			String a1 = nbt.getString("power1");
			String a2 = item.power2;
			String a3 = item.power3;
			String a4 = item.power4;
			if(EnergyInductorProperties.isEqualTo(a1, EMPTY)) {
				System.out.println("true");
				nbt.setString("power1", FORCE);
				item.power1 = FORCE;
			}
			System.out.println("power1: " + nbt.getString("power1"));
			if(a1 != EMPTY && a2 == EMPTY) item.power2 = PUSH;
			
			ItemStack ret = new ItemStack(item);
			NBTTagCompound nbt2 = stack.getTagCompound();
			ret.setTagCompound(nbt2);
			return new ItemStack(item);
		}
		System.out.println("tack");
		return stack;
	}

	public static ItemStack dye(String type) {
		ItemStack dye = ItemStack.EMPTY;
		if(type == "red") dye = new ItemStack(Items.DYE, 1, 1);
		if(type == "blue") dye = new ItemStack(Items.DYE, 1, 4);
		if(type == "green") dye = new ItemStack(Items.DYE, 1, 10);
		
		if(dye == ItemStack.EMPTY) {
			System.out.println("Dye stack is empty!");
			dye = new ItemStack(Items.DYE, 1, 1);
		}
		return dye;
		
	}
	*/

	public static ItemStack fillSummoningKey(int s, ItemStack vile, ItemStack key) {
		ItemStack empty = ItemStack.EMPTY;
		if(vile.hasTagCompound() && key.hasTagCompound()) {
			
			NBTTagCompound nbt = vile.getTagCompound();
			int dur = nbt.getInteger("Durability");
			int keydur = key.getTagCompound().getInteger("Durability");
			int back, take;
			
			if(dur + keydur > 50) {
				take = 50 - keydur;
				back = dur - take;
				} else {
				back = 0;
				take = dur;
			}
			
			nbt.setInteger("Durability", back);
			
			ItemStack ret = new ItemStack(ItemInit.SUMMONING_KEY_NETHER);
			ItemSummoningKey.getNBT(ret);
			
			ret.getTagCompound().setInteger("Durability", keydur + take);
			
			return ret;
			
		}
		return empty;
		
	}

	private static ItemStack lightsaberArrow(ItemStack stack) {
		return stack;
	}

	private static ItemStack tesla(ItemStack a, ItemStack b, World world) {
		return TeslaUpgrader.getItemStack(a, b, world);
	}
	
}