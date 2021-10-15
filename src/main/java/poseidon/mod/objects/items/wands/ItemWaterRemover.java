package poseidon.mod.objects.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemWaterRemover extends ItemBase {

	public ItemWaterRemover(String name) {
		super(name, 1, false);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack held = playerIn.getHeldItem(handIn);
		
		BlockPos[] pos = getBlocks(playerIn, worldIn);
		setWater(pos, worldIn, playerIn);
		
		return new ActionResult(EnumActionResult.SUCCESS, held);
	}
	
	private BlockPos[] getBlocks(EntityPlayer playerIn, World worldIn) {
		BlockPos pos = playerIn.getPosition();
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		 return new BlockPos[] {new BlockPos(x + 1.0D, y, z), new BlockPos(x + 1.0D, y, z + 1.0D), new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x - 1.0D, y, z),
										  new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x - 1.0D, y, z - 1.0D), new BlockPos(x, y, z + 1.0D), new BlockPos(x, y, z - 1.0D)};
	}
	
	private void setWater(BlockPos[] pos, World world, EntityPlayer player) {
		
		for(int i = 0; i < pos.length; i++) {
			BlockPos p = pos[i];
			if(world.getBlockState(p) == Blocks.WATER.getDefaultState()) {
				world.setBlockState(p, Blocks.AIR.getDefaultState());
			}

		}
		
	}
	
}
