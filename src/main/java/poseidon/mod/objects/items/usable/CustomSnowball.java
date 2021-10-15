package poseidon.mod.objects.items.usable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.entities.EntityCustomSnowball;
import poseidon.mod.objects.entities.EntityCustomSnowballExplosive;
import poseidon.mod.objects.entities.EntityCustomSnowballKnockback;
import poseidon.mod.objects.items.general.ItemBase;

public class CustomSnowball extends ItemBase {

	public CustomSnowball(String name) {
		super(name, 64, true);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		
		ItemStack heldItem = player.getHeldItem(handIn);
		Item item = heldItem.getItem();
		
		if(!worldIn.isRemote) {
			
			if(item == ItemInit.EXPLOSIVE_SNOWBALL) {
				 EntityCustomSnowballExplosive entitysnowball = new EntityCustomSnowballExplosive(worldIn, player);
		         entitysnowball.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 0.0F);
		         worldIn.spawnEntity(entitysnowball);
			}
			
			if(item == ItemInit.ICY_SNOWBALL) {
				 EntityCustomSnowball entitysnowball = new EntityCustomSnowball(worldIn, player);
		         entitysnowball.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 0.0F);
		         worldIn.spawnEntity(entitysnowball);
			}
			
	         if(!player.isCreative()) heldItem.shrink(1);
			
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, heldItem);
	}
	
}
