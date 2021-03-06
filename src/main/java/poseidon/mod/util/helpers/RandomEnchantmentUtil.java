package poseidon.mod.util.helpers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class RandomEnchantmentUtil {

	public static NBTTagList getEnchantments(ItemStack p_92110_0_) {
        NBTTagCompound nbttagcompound = p_92110_0_.getTagCompound();
        return nbttagcompound != null ? nbttagcompound.getTagList("StoredEnchantments", 10) : new NBTTagList();
    }
	
	public static void addEnchantment(ItemStack p_92115_0_, EnchantmentData stack) {
        NBTTagList nbttaglist = getEnchantments(p_92115_0_);
        boolean flag = true;

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);

            if (Enchantment.getEnchantmentByID(nbttagcompound.getShort("id")) == stack.enchantment) {
                if (nbttagcompound.getShort("lvl") < stack.enchantmentLevel) {
                    nbttagcompound.setShort("lvl", (short)stack.enchantmentLevel);
                }

                flag = false;
                break;
            }
        }

        if (flag) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setShort("id", (short)Enchantment.getEnchantmentID(stack.enchantment));
            nbttagcompound1.setShort("lvl", (short)stack.enchantmentLevel);
            nbttaglist.appendTag(nbttagcompound1);
        }

        if (!p_92115_0_.hasTagCompound()) {
            p_92115_0_.setTagCompound(new NBTTagCompound());
        }

        p_92115_0_.getTagCompound().setTag("StoredEnchantments", nbttaglist);
    }
	
    public static ItemStack getEnchantedItemStack(EnchantmentData p_92111_0_) {
        ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK);
        addEnchantment(itemstack, p_92111_0_);
        
        
        return itemstack;
    }
	
}
