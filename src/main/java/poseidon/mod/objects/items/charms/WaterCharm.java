package poseidon.mod.objects.items.charms;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.ToolHoe;

public class WaterCharm extends ToolHoe {
	
	int i = 0;
	
	public WaterCharm(String name, ToolMaterial tool) {
		super(name, tool);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.FAIL;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn != null && entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			

			if(player.isInWater()) {
						
					float yaw = player.rotationYaw;
					float pitch = player.rotationPitch;
					
					double motionX = (double)((-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) / 4 ));
					double motionZ = (double)((MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) /4 ));
						    
					player.setVelocity(motionX, player.motionY, motionZ);
						    
					if(i < 5) {
						stack.damageItem(1, player);
						i++;
					}
					if(i >= 5 && i < 11) {
						i++;
					}
					if(i == 11) {
						i = 0;
					}
					
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.getItem() == ItemInit.DEPTH_STRIDER_CHARM) {
			tooltip.add(TextFormatting.WHITE + "Provides speed in water");
		}
	}
}
