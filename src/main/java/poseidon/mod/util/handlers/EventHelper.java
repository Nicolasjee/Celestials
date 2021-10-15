package poseidon.mod.util.handlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.EnchantmentInit;

public class EventHelper {


	public static boolean hasKeys(ItemStack stack, String[] keys, boolean hT) {
		if(hT) {
			for(int i = 0; i < keys.length; i++) {
				if(!stack.getTagCompound().hasKey(keys[i])) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static void setLava(EntityLivingBase living, World world, BlockPos pos) {
		 float f = (float)Math.min(16, 2 + EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentInit.UNBREAKABLE, living));
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);

         for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f))) {
        	 if (blockpos$mutableblockpos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double)(f * 4 * f)) {
                    blockpos$mutableblockpos.setPos(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
                    IBlockState iblockstate = world.getBlockState(blockpos$mutableblockpos);

                    if (iblockstate.getMaterial() == Material.AIR) {
                        IBlockState iblockstate1 = world.getBlockState(blockpos$mutableblockpos1);

                        if (iblockstate1.getMaterial() == Material.LAVA && (iblockstate1.getBlock() == net.minecraft.init.Blocks.LAVA || iblockstate1.getBlock() == net.minecraft.init.Blocks.FLOWING_LAVA) && ((Integer)iblockstate1.getValue(BlockLiquid.LEVEL)).intValue() == 0 && world.mayPlace(Blocks.MAGMA, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Entity)null)) {
                            world.setBlockState(blockpos$mutableblockpos1, BlockInit.MAGMA1.getDefaultState());
                          // world.scheduleUpdate(blockpos$mutableblockpos1.toImmutable(), (Block) BlockInit.MAGMA1.getDefaultState(), MathHelper.getInt(living.getRNG(), 60, 120));
                    }
                }
            }
        }
	}
	
	public static void setNormalLava(EntityLivingBase l, World world, BlockPos pos) {
		BlockPos p = pos.down();
		BlockPos[] a = new BlockPos[] {p.north(), p.east(), p.south(), p.west(), p.north().east(), p.south().east(), p.south().west(), p.north().west()};
		
		for(int i = 0; i < a.length; i++) {
			if(world.getBlockState(a[i]) == Blocks.LAVA.getDefaultState() || world.getBlockState(a[i]).getMaterial() == Material.LAVA) {
				world.setBlockState(a[i], BlockInit.MAGMA1.getDefaultState()); 
			}
		}
	}
	
	public static void setLight(EntityLivingBase living, World world, BlockPos pos) {
		IBlockState airS = Blocks.AIR.getDefaultState();
		IBlockState light1 = BlockInit.LIGHT1.getDefaultState();
		
		if(world.getBlockState(pos) == airS && isNotLight(pos, world))
			world.setBlockState(pos, light1);
		else if(world.getBlockState(pos) != airS && isNotLight(pos, world) && world.getBlockState(pos.up()) == airS && isNotLight(pos.up(), world)) {
			world.setBlockState(pos, light1);
		}
	}
	
	public static boolean isNotLight(BlockPos pos, World world) {
		IBlockState state1 = world.getBlockState(pos);
		IBlockState state2 = BlockInit.LIGHT1.getDefaultState();
		IBlockState state3 = BlockInit.LIGHT2.getDefaultState();
		IBlockState state4 = BlockInit.LIGHT3.getDefaultState();
		if(state1 == state2 || state1 == state3 || state1 == state4) return false;
		else if(state1 != state2 && state1 != state3 && state1 != state4) return true;
		else return false;
	}
	
	public static boolean isItemInHands(ItemStack stack, EntityPlayer player) {
		return player.getHeldItemMainhand().getItem() == stack.getItem() || player.getHeldItemOffhand().getItem() == stack.getItem();
	}
	
	public static boolean isMain(Item stack, EntityPlayer player) {
		return player.getHeldItemMainhand().getItem() == stack;
	}
	
	public static boolean hasItemStack(ItemStack stack, EntityPlayer player) {
		return player.inventory.hasItemStack(stack);
	}
	
	public static boolean hasItem(Item item, EntityPlayer player) {
		return player.inventory.hasItemStack(new ItemStack(item));
	}
	
	public static boolean hasLightsaber(EntityPlayer player) {
		List<Boolean> checks = new ArrayList<Boolean>();
		

		
		if(checks.contains(true)) return true;
		return false;
	}
	
	public static boolean inventoryContainsItemStacks(ItemStack[] stacks, EntityPlayer player) {
		List<Boolean> checks = new ArrayList<Boolean>();
		
		for(int i = 0; i < stacks.length; i++) {
			if(player.inventory.hasItemStack(stacks[i])) {
				checks.add(i, true);
				} else {
				checks.add(i, false);
			}
		}
		
		if(checks.contains(true)) return true;
		return false;
		
	}
	
	public static int getSlotForStack(ItemStack stack, EntityPlayer player) {
		for(int i = 0; i < 36; i ++) {
			ItemStack check = player.inventory.getStackInSlot(i);
			if(check.getItem() == stack.getItem()) {
				return i;
			}
		}
		return -1;
	}

	public static boolean hasComp(Item i, EntityPlayer player) {
		return (player.getHeldItemMainhand().getItem() == i && player.getHeldItemMainhand().hasTagCompound());
	}

}
