package poseidon.mod.client.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.init.Enchantments;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.init.EnchantmentInit;
import poseidon.mod.util.Reference;

public class EnchantmentExperience extends Enchantment {

	public EnchantmentExperience() {
		super(Rarity.RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		this.setName("experience_boost");
		this.setRegistryName(new ResourceLocation(Reference.MODID + ":experience_boost"));
		EnchantmentInit.ENCHANTMENTS.add(this);
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 20 * enchantmentLevel;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return this.getMinEnchantability(enchantmentLevel) + 10;
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	public int getMinLevel() {
		return 1;
	}
	
	
	
	@Override
	public boolean canApplyTogether(Enchantment ench) {
		if(ench == EnchantmentInit.ADVANCEDMINING) return false;
		else return true;
	}
	
}
