package poseidon.mod.objects.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.expansiontable.ExRecipes;

public class RecipeHelper {
		
	public static void lightsaberGetNBT(ItemStack stack, int dur, String type) {
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} //Gets The compound which holds keys!
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", dur);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		if(nbt.hasKey("Type")) {
			nbt.setString("Type", nbt.getString("Type"));
			} else {
			nbt.setString("Type", type);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		
		stack.setTagCompound(nbt);
	}

	/*
	public static void registerCharms() {
		ItemStack empty = new ItemStack(ItemInit.EMPTY_CHARM);
		
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:projectile_charm"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.PROJECTILE_CHARM),
				new Object[] {" K ", "AEA", " K ", 'A', Items.ARROW, 'E', ItemInit.EMPTY_CHARM, 'K', ItemInit.KNOWLEDGE_BOOK});

		GameRegistry.addShapedRecipe(new ResourceLocation("psm:depth_strider_charm"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.DEPTH_STRIDER_CHARM),
				new Object[] {" K ", "AEA", " K ", 'A', new ItemStack(Items.FISH), 'E', ItemInit.EMPTY_CHARM, 'K', ItemInit.KNOWLEDGE_BOOK});

		GameRegistry.addShapedRecipe(new ResourceLocation("psm:fall_charm"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.FALL_CHARM),
				new Object[] {" K ", "AEA", " K ", 'A', Items.RABBIT_FOOT, 'E', ItemInit.EMPTY_CHARM, 'K', ItemInit.KNOWLEDGE_BOOK});
		
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:regeneration_charm"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.REGENERATION_CHARM),
				new Object[] {" K ", "AEA", " K ", 'A', Items.GHAST_TEAR, 'E', ItemInit.EMPTY_CHARM, 'K', ItemInit.KNOWLEDGE_BOOK});
		
		GameRegistry.addShapedRecipe(new ResourceLocation("psm:fire_protection_charm"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.FIRE_PROTECTION_CHARM),
				new Object[] {" K ", "AEA", " K ", 'A', Items.MAGMA_CREAM, 'E', ItemInit.EMPTY_CHARM, 'K', ItemInit.KNOWLEDGE_BOOK});

		GameRegistry.addShapedRecipe(new ResourceLocation("psm:empty_charm"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.EMPTY_CHARM),
				new Object[] {"BIB", "IOI", "BIB", 'B', ItemInit.BLUESTONE_DUST, 'I', Items.IRON_INGOT, 'O', ItemInit.OBSIDIAN_INGOT});

	}
	*/

	
}
