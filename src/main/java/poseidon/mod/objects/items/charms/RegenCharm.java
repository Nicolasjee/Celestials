package poseidon.mod.objects.items.charms;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.objects.tools.ToolHoe;

public class RegenCharm extends ToolHoe {

	public RegenCharm(String name, ToolMaterial tool) {
		super(name, tool);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.PASS;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.WHITE + "Regenerates the wielder");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn != null && entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			
			if(player.getHeldItemMainhand().getItem() == stack.getItem() || player.getHeldItemOffhand().getItem() == stack.getItem()) {
				
				if(!worldIn.isRemote) {
					
					if(player.getHealth() != player.getMaxHealth()) {
						player.setHealth(player.getHealth() + 1.0F);
						player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 1, 20));
						
						if(player.getHeldItemOffhand().getItem() == stack.getItem())player.getHeldItemOffhand().damageItem(1,player);
						if(player.getHeldItemMainhand().getItem() == stack.getItem()) player.getHeldItemMainhand().damageItem(1, player);
					}
				}
				
			}
		
		}
		
	}
	
}
