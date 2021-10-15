package poseidon.mod.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.items.wand.ModAbilities;
import poseidon.mod.util.ParticleUtil;

public class TorchPlaceUtil {

	public static void execute(EntityPlayer player, World world) {
		
		ItemStack ist = player.getHeldItemMainhand();
		ItemStack torches = new ItemStack(Item.getItemFromBlock(Blocks.TORCH));
		
		//calls onItemUse so all of the functionality we'd normally have to do preventative checks on gets handled there.
		if(player.inventory.hasItemStack(torches)) {
			ItemStack ammo = ItemStack.EMPTY;
			
			for(int i = 0; i < 36; i ++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if(check.getItem() == torches.getItem()) {
					ammo = check;
				}
			}
			ammo.shrink(1);
			RayTraceResult mop = getBlockTarget(world, player);
			if(mop != null && mop.typeOfHit == RayTraceResult.Type.BLOCK) {
				placeTorch(player, world, mop.getBlockPos(), EnumHand.MAIN_HAND, mop.sideHit, ist);
				player.getCooldownTracker().setCooldown(ist.getItem(), 2);
			}
		}
		

	}
	
	private static IBlockState attemptSide(World world, BlockPos pos, EnumFacing side, Block block, EntityPlayer player, EnumHand hand) {
		return block.getStateForPlacement(world, pos, side, pos.getX(), pos.getY(), pos.getZ(), 0, player, hand);
	}
	
	private static EnumActionResult placeTorch(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, ItemStack stack) {
		if(player.isSwingInProgress) return EnumActionResult.PASS;
		player.swingArm(hand);

		if(world.isRemote) return EnumActionResult.PASS;

		if(!player.canPlayerEdit(pos, side, stack))
			return EnumActionResult.PASS;

		Block blockAttemptingPlacement = Blocks.TORCH;
		if(blockAttemptingPlacement == null)
			return EnumActionResult.FAIL;

		Block blockTargetted = world.getBlockState(pos).getBlock();
		BlockPos placeBlockAt = pos.up();

		if(blockTargetted == Blocks.SNOW) {
			side = EnumFacing.UP;
			} else if(blockTargetted != Blocks.VINE && blockTargetted != Blocks.TALLGRASS && blockTargetted != Blocks.DEADBUSH && !blockTargetted.isReplaceable(world, pos)) {
			placeBlockAt = pos.offset(side);
		}

		if(blockAttemptingPlacement.canPlaceBlockAt(world, placeBlockAt)) {
			if(world.mayPlace(blockAttemptingPlacement, placeBlockAt, false, side, player)) {
				if(!player.capabilities.isCreativeMode) {
					
				}
				IBlockState torchBlockState = attemptSide(world, placeBlockAt, side, blockAttemptingPlacement, player, hand);
				if(placeBlockAt(stack, player, world, placeBlockAt, torchBlockState)) {
					System.out.println("Here");
					blockAttemptingPlacement.onBlockAdded(world, placeBlockAt, torchBlockState);
					double gauss = 0.5D + world.rand.nextFloat() / 2;
					world.spawnParticle(EnumParticleTypes.SPELL_MOB, placeBlockAt.getX() + 0.5D, placeBlockAt.getY() + 0.5D, placeBlockAt.getZ() + 0.5D, gauss, gauss, 0.0F);
					SoundType soundType = blockAttemptingPlacement.getSoundType(torchBlockState, world, placeBlockAt, player);
					world.playSound(null, placeBlockAt, soundType.getPlaceSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
				}
			}
		}
		return EnumActionResult.SUCCESS;
	}
	
	private static RayTraceResult getBlockTarget(World world, EntityPlayer player) {
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + (double) (world.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
		Vec3d vec3 = new Vec3d(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 50;
		Vec3d vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		return world.rayTraceBlocks(vec3, vec31, true, false, false);
	}
	
	private static boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, IBlockState torchBlockState) {
		if(!world.setBlockState(pos, torchBlockState, 3))
			return false;
		if(torchBlockState.getBlock() == Blocks.TORCH) {
			//noinspection deprecation
			Blocks.TORCH.neighborChanged(torchBlockState, world, pos, torchBlockState.getBlock(), pos);
			Blocks.TORCH.onBlockPlacedBy(world, pos, torchBlockState, player, stack);
		}

		return true;
	}
	
}
