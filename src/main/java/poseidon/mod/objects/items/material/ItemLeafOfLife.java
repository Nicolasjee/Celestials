package poseidon.mod.objects.items.material;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemLeafOfLife extends ItemBase {

	public ItemLeafOfLife(String name, int s, boolean b) {
		super(name, s, b);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(worldIn.getBlockState(pos) == Blocks.DIRT.getDefaultState()) {
			worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
		}
		
		if(worldIn.getBlockState(pos) == Blocks.DEADBUSH) {
			worldIn.setBlockState(pos.down(), Blocks.GRASS.getDefaultState());
			worldIn.setBlockState(pos, Blocks.SAPLING.getDefaultState());
		}
		
		if(worldIn.getBlockState(pos) == Blocks.COBBLESTONE.getDefaultState()) {
			worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
		}
		
		if(worldIn.getBlockState(pos) == Blocks.SAND.getDefaultState()) {
			worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
		}
		
		if(worldIn.getBlockState(pos) == Blocks.SAPLING.getDefaultState()) {
			int slot = player.inventory.getSlotFor(player.getHeldItem(hand));
			ItemStack stack = player.getHeldItem(hand);
			ItemDye.applyBonemeal(stack, worldIn, pos);
			player.inventory.add(slot, stack);
		}
		
		return EnumActionResult.SUCCESS;
		
	}
	
	
	
}
