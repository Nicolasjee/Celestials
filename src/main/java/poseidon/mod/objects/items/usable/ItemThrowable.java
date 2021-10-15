package poseidon.mod.objects.items.usable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import poseidon.mod.objects.entities.EntityCustomSnowball;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemThrowable extends ItemBase {

	public ItemThrowable(String name, int size, boolean b) {
		super(name, size, b);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		if (!worldIn.isRemote)
        {
			System.out.println("sl");
            EntityCustomSnowball entitysnowball = new EntityCustomSnowball(worldIn, playerIn);
            entitysnowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitysnowball);
        }

		
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
	
}
