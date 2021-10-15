package poseidon.mod.objects.items.general.test;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemCoalRemover extends ItemBase {
	
	public ItemCoalRemover(String name) {
		super(name, 1, false);
	}


	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!(worldIn.getBlockState(pos) == Blocks.COAL_ORE.getDefaultState())) return EnumActionResult.FAIL;
		
		if(!worldIn.isRemote) {
			List<BlockPos> coal = checkForCoal(worldIn, pos);
			List<BlockPos> coalExpanded = new ArrayList<BlockPos>();
			List<BlockPos> coalExpanded2 = new ArrayList<BlockPos>();
			List<BlockPos> coalExpanded3 = new ArrayList<BlockPos>();
			
			for(int i = 0; i < coal.size(); i++) {
				coalExpanded = addPos(worldIn, coal.get(i), coalExpanded);
			}
			
			for(int i = 0; i < coalExpanded.size(); i++) {
				coalExpanded2 = addPos(worldIn, coalExpanded.get(i), coalExpanded2);
			}
			
			for(int i = 0; i < coalExpanded2.size(); i++) {
				coalExpanded3 = addPos(worldIn, coalExpanded2.get(i), coalExpanded3);
			}
			
			List<BlockPos> general = new ArrayList<BlockPos>();
			general.addAll(coal); general.addAll(coalExpanded); general.addAll(coalExpanded2); general.addAll(coalExpanded3);
			worldIn.destroyBlock(pos, true);
			
			for(int i = 0; i < general.size(); i++) {
				worldIn.destroyBlock(general.get(i), true);
			}
		}
		return EnumActionResult.SUCCESS;
	}

	private List<BlockPos> checkForCoal(World world, BlockPos pos) {
		
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		List<BlockPos> list = new ArrayList<BlockPos>();
		
		BlockPos[] array = new BlockPos[]{new BlockPos(x + 1.0D, y, z), new BlockPos(x + 1.0D, y, z + 1.0D), new BlockPos(x - 1.0D, y, z), new BlockPos(x - 1.0D, y, z - 1.0D),
										  new BlockPos(x, y, z + 1.0D), new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x, y, z - 1.0D), new BlockPos(x - 1.0D, y, z - 1.0D),
										  new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z), new BlockPos(x + 1.0D, y - 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z), 
										  new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x, y - 1.0D, z + 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z - 1.0D), 
										  new BlockPos(x, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z + 1.0D), 
										  new BlockPos(x + 1.0D, y + 1.0D, z), new BlockPos(x + 1.0D, y + 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z), 
										  new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D), new BlockPos(x, y + 1.0D, z + 1.0D), new BlockPos(x + 1.0D, y + 1.0D, z - 1.0D), 
										  new BlockPos(x, y + 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z + 1.0D),};
		
		for(int i = 0; i < array.length; i++) {
			if(world.getBlockState(array[i]) == Blocks.COAL_ORE.getDefaultState()) list.add(array[i]);
		}
		
		return list;
		
	}
	
	private List<BlockPos> addPos(World world, BlockPos pos, List<BlockPos> coalExpanded) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		BlockPos[] coal = new BlockPos[] {new BlockPos(x + 1.0D, y, z), new BlockPos(x + 1.0D, y, z + 1.0D), new BlockPos(x - 1.0D, y, z), new BlockPos(x - 1.0D, y, z - 1.0D),
										  new BlockPos(x, y, z + 1.0D), new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x, y, z - 1.0D), new BlockPos(x - 1.0D, y, z - 1.0D),
										  new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z), new BlockPos(x + 1.0D, y - 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z), 
										  new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x, y - 1.0D, z + 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z - 1.0D), 
										  new BlockPos(x, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z + 1.0D), 
										  new BlockPos(x + 1.0D, y + 1.0D, z), new BlockPos(x + 1.0D, y + 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z), 
										  new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D), new BlockPos(x, y + 1.0D, z + 1.0D), new BlockPos(x + 1.0D, y + 1.0D, z - 1.0D), 
										  new BlockPos(x, y + 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z + 1.0D),};
		
		for(int i = 0; i < coal.length; i++) {
			if(world.getBlockState(coal[i]) == Blocks.COAL_ORE.getDefaultState()) {
				coalExpanded.add(coal[i]);
			}
		}
		
		return coalExpanded;
		
	}
}
