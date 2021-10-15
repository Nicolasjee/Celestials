package poseidon.mod.objects.items.general.test;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemDiamondCompass extends ItemBase {

	public ItemDiamondCompass(String name) {
		super(name, 1, false);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		findDiamonds(worldIn, playerIn.getPosition());
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	private void findDiamonds(World world, BlockPos pos) {
		
		BlockPos[] array = new BlockPos[] {
				
				
				
		};
		
		List<BlockPos> list = new ArrayList<BlockPos>();
		
		for(int i = 0; i < 5; i++) {
			
			list.add(pos.east(i));
			list.add(pos.north(i));
			list.add(pos.west(i));
			list.add(pos.south(i));
			list.add(pos.east(i).north(i));
			
		}
		
	}
	
	private void getBox(World world, BlockPos pos) {
		
		Iterable<BlockPos> posses = pos.getAllInBox(pos.north(10).east(10).up(2), pos.south(10).west(10).down(2));
		
		for(BlockPos check : posses) {
			
			if(world.getBlockState(check) == Blocks.DIAMOND_ORE.getDefaultState()) {
				
				digTunnel(world, pos, check);
				
			}
			
		}
		
	}
	
	private void digTunnel(World world, BlockPos pos, BlockPos diamond) {
								//diamond x > pos x?
		boolean isXfurther = isXfurther(diamond, pos);
		boolean isZfurther = isZfurther(diamond, pos);
		boolean isHigher = (diamond.getY() > pos.getY());
		boolean isSameLevel = (diamond.getY() == pos.getY());
		
		List<BlockPos> removes = new ArrayList<BlockPos>();
		
		//step one: hight adjustment.
		BlockPos heightAdjust = new BlockPos(getValueX(isXfurther, pos, diamond), getValueY(isHigher, pos, diamond), getValueZ(isZfurther, pos, diamond));
		
		removes.add(heightAdjust);
		removes.add(heightAdjust.up()); //Tunnel.
		removes.add(pos.up(2)); //tunnel.
		
		/*
		 * [S]tone, [T]unnel, [x]Player, [H]eightAdjust
		 *  S S S S
		 * 	T H S S
		 *  x T S S
		 *  x S S S
		 * 
		 */
		
		
		//x diamond > x player => oosten
		//x diamond < x player => westen
		//z diamond > z player => zuiden
		//z diamond < z player => noorden
		
		
	}
	
	
	
	private static double getValueZ(boolean isZfurther, BlockPos pos, BlockPos diamond) {
		if(isZfurther) return pos.getZ() + 1.0D;
		else if(!isZfurther) return pos.getZ() - 1.0D;
		else return 1.0D;
	}
	
	private static double getValueY(boolean isHigher, BlockPos pos, BlockPos diamond) {
		if(isHigher) return pos.getY() + 1.0D;
		else if(!isHigher) return pos.getY() - 1.0D;
		else return 1.0D;
	}
	
	private static double getValueX(boolean isXfurther, BlockPos pos, BlockPos diamond) {
		if(isXfurther) return pos.getX() + 1.0D;
		else if(!isXfurther) return pos.getX() - 1.0D;
		else return 1.0D;
	}
	
	private boolean isXfurther(BlockPos diamond, BlockPos player) {
		if(diamond.getX() > player.getX()) return true;
		else if(diamond.getX() < player.getX()) return false;
		return true;
	}

	private boolean isZfurther(BlockPos diamond, BlockPos player) {
		if(diamond.getZ() > player.getZ()) return true;
		else if(diamond.getZ() < player.getZ()) return false;
		return false;
	}
	
}
