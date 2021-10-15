package poseidon.mod.objects.items.usable;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import poseidon.mod.client.commands.util.Heal;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemHealWart extends ItemBase {
	
	public ItemHealWart(String name, int size, boolean effect) {
		super(name, size, effect);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		Heal.healPlayer(playerIn, 2);
		stack.shrink(1);
		
		return new ActionResult(EnumActionResult.SUCCESS, stack);
		
	}
	
//	public static void registerRecipe() {
//		GameRegistry.addShapedRecipe(new ResourceLocation("psm:concentrated_nether_wart"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.CONCENTRATED_NETHER_WART),
//				new Object [] {"NNN", "GGG", " P ", 'N', Items.NETHER_WART, 'G', Items.GLOWSTONE_DUST, 'P', Items.GLASS_BOTTLE});
//	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(entityIn != null && entityIn instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) entityIn;
			float health = player.getHealth();
			
			if(health < 5.0F) {
				
				stack.shrink(1);
				player.heal(5.0F);
				
			}
			
		}
		
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.WHITE + "Heals 10 hearts on use");
	}
	
}
