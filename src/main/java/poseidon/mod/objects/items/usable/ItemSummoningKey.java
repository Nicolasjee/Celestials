package poseidon.mod.objects.items.usable;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.summoner.Summoner;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemSummoningKey extends ItemBase {

	
	
	public ItemSummoningKey(String name, int size, boolean effect) {
		super(name, size, effect);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		
		
	}
	
	public static void getNBT(ItemStack stack) {
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} //Gets The compound which holds keys!
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", 0);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		stack.setTagCompound(nbt);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
//		if(stack.getItem() == ItemInit.SUMMONING_KEY_HEAVEN) tooltip.add("Summon a heavenly entity");
		//if(stack.getItem() == ItemInit.SUMMONING_KEY_MAGIC) tooltip.add("Summons a wizard");

	}
	
	public static void registerRecipes() {

		
	}
}
