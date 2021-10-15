package poseidon.mod.objects.items.general;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.entities.CustomXP;

public class ItemSpawn extends ItemBase {
	
	public ItemSpawn(String name, int size, boolean e) {
		super(name, size, e);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		
		RayTraceResult pos = playerIn.rayTrace(100,20);
		double x = pos.getBlockPos().getX();
		double y = pos.getBlockPos().getY();
		double z = pos.getBlockPos().getZ();
		
		CustomXP entity = new CustomXP(worldIn, x, y + 1.0D, z, 5000);
		worldIn.spawnEntity(entity);
		
		heldItem.shrink(1);
		
		return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		
	}
}
