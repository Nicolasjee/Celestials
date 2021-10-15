package poseidon.mod.objects.items.general.test;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemVelocity extends ItemBase {
	
	public ItemVelocity(String name) {
		super(name, 1, false);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			System.out.println(player.inventory.hasItemStack(stack));
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		
		double camX = player.getLookVec().x;
		double camY = player.getLookVec().y;
		double camZ = player.getLookVec().z;

		double velocityAddedX = camX * 0.5F;
		double velocityAddedY = camY * 0.5F;
		double velocityAddedZ = camZ * 0.5F;

		double x = player.motionX + (camX*1.0F);
		double y = player.motionY + (camY*1.0F);
		double z = player.motionZ + (camZ*1.0F);

		player.setVelocity(x, y, z);
		
		return super.onItemRightClick(worldIn, player, handIn);
	}
	
}
