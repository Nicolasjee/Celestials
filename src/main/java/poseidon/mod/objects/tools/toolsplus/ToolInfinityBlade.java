package poseidon.mod.objects.tools.toolsplus;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
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
import poseidon.mod.objects.tools.ToolSword;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class ToolInfinityBlade extends ToolSword {

	//TODO Walking on water & lava + raytraceresult remove entity
	//TODO Velocity change when keybind
	
	public ToolInfinityBlade(String name, ToolMaterial mat, boolean b) {
		super(name, mat, b);
	}
	

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		BlockPos pos = playerIn.getPosition(); double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		worldIn.playSound(x, y, z, SoundsHandler.WILLAH, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		EntityPlayer living = (EntityPlayer) entityIn;
		BlockPos pos = living.getPosition();
		if (living.onGround && living.getHeldItemMainhand().getItem() == stack.getItem()) {
            float f = (float)Math.min(16, 2 + 2);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);

            for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f))) {
                if (blockpos$mutableblockpos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double)(f * f)) {
                    blockpos$mutableblockpos.setPos(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
                    IBlockState iblockstate = world.getBlockState(blockpos$mutableblockpos);

                    if (iblockstate.getMaterial() == Material.AIR) {
                        IBlockState iblockstate1 = world.getBlockState(blockpos$mutableblockpos1);

                        if (iblockstate1.getMaterial() == Material.LAVA && (iblockstate1.getBlock() == net.minecraft.init.Blocks.LAVA || iblockstate1.getBlock() == net.minecraft.init.Blocks.FLOWING_LAVA) && ((Integer)iblockstate1.getValue(BlockLiquid.LEVEL)).intValue() == 0 && world.mayPlace(Blocks.MAGMA, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Entity)null)) {
                            world.setBlockState(blockpos$mutableblockpos1, Blocks.MAGMA.getDefaultState());
                            living.setHealth(living.getHealth() + 1.0F);
                            System.out.println("Armor: "+ living.inventory.armorItemInSlot(2));
                            world.scheduleUpdate(blockpos$mutableblockpos1.toImmutable(), Blocks.MAGMA, MathHelper.getInt(living.getRNG(), 60, 120));
                        }
                        
                        if (iblockstate1.getMaterial() == Material.WATER && (iblockstate1.getBlock() == net.minecraft.init.Blocks.WATER || iblockstate1.getBlock() == net.minecraft.init.Blocks.FLOWING_WATER) && ((Integer)iblockstate1.getValue(BlockLiquid.LEVEL)).intValue() == 0 && world.mayPlace(Blocks.FROSTED_ICE, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Entity)null)){
                        	world.setBlockState(blockpos$mutableblockpos1, Blocks.FROSTED_ICE.getDefaultState());
                        	world.scheduleUpdate(blockpos$mutableblockpos1.toImmutable(), Blocks.FROSTED_ICE, MathHelper.getInt(living.getRNG(), 60, 120));
                        }
                    }
                }
            }
        }
		
		if(stack.hasTagCompound() && living.isSneaking()) {
			
			NBTTagCompound nbt = stack.getTagCompound();
			
			if(nbt.hasKey("Charge")) {
				
			}
			
		}
		
	
		
	}
	
	@Nonnull
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {

		ItemStack stack = player.getHeldItem(hand);
		ItemStack torches = new ItemStack(Item.getItemFromBlock(Blocks.TORCH));
		
		if(player.inventory.hasItemStack(torches)) {
			ItemStack ammo = ItemStack.EMPTY;
			
			for(int i = 0; i < 36; i ++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if(check.getItem() == torches.getItem()) {
					ammo = check;
				}
			}
			ammo.shrink(1);
			return placeTorch(player, world, pos, hand, side, stack);
		}
		
		return EnumActionResult.FAIL;
	}
	

	private EnumActionResult placeTorch(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, ItemStack stack) {

		if(player.isSwingInProgress) return EnumActionResult.PASS;
		player.swingArm(hand);

		if(world.isRemote)
			return EnumActionResult.SUCCESS;

		if(!player.canPlayerEdit(pos, side, stack))
			return EnumActionResult.PASS;

		Block blockAttemptingPlacement = Blocks.TORCH;
		if(blockAttemptingPlacement == null)
			return EnumActionResult.FAIL;

		Block blockTargetted = world.getBlockState(pos).getBlock();
		BlockPos placeBlockAt = pos;

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

	private IBlockState attemptSide(World world, BlockPos pos, EnumFacing side, Block block, EntityPlayer player, EnumHand hand) {
		return block.getStateForPlacement(world, pos, side, pos.getX(), pos.getY(), pos.getZ(), 0, player, hand);
	}

	//a longer ranged version of "getMovingObjectPositionFromPlayer" basically
	private RayTraceResult getBlockTarget(World world, EntityPlayer player) {
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

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
		ItemStack ist = player.getHeldItem(hand);
		ItemStack torches = new ItemStack(Item.getItemFromBlock(Blocks.TORCH));

		if(player instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) player;
			//if(!world.isRemote) ParticleUtil.circle(p.posX, p.posY, p.posZ, (WorldServer) world);
		}
		
		//calls onItemUse so all of the functionality we'd normally have to do preventative checks on gets handled there.
		if(player.isSneaking() && player.inventory.hasItemStack(torches) && player.getHeldItemOffhand().getItem() == Item.getItemFromBlock(Blocks.TORCH)) {
			ItemStack ammo = ItemStack.EMPTY;
			
			for(int i = 0; i < 36; i ++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if(check.getItem() == torches.getItem()) {
					ammo = check;
				}
			}
			ammo.shrink(1);
			RayTraceResult mop = this.getBlockTarget(world, player);
			if(mop != null && mop.typeOfHit == RayTraceResult.Type.BLOCK) {
				placeTorch(player, world, mop.getBlockPos(), hand, mop.sideHit, ist);
			}
		}
		
		if(player.isSneaking() && player.getHeldItemOffhand().getItem() != Item.getItemFromBlock(Blocks.TORCH)) {
			ModAbilities.attackAll(5, player, world);
			player.getCooldownTracker().setCooldown(ist.getItem(), 50);
		}
		
		if(!player.isSneaking()) {
			ModAbilities.attackAll(1, player, world);
			player.getCooldownTracker().setCooldown(ist.getItem(), 20);
		}
		
		return super.onItemRightClick(world, player, hand);
	}


	private boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, IBlockState torchBlockState) {
		if(!world.setBlockState(pos, torchBlockState, 3))
			return false;
		if(torchBlockState.getBlock() == Blocks.TORCH) {
			//noinspection deprecation
			Blocks.TORCH.neighborChanged(torchBlockState, world, pos, torchBlockState.getBlock(), pos);
			Blocks.TORCH.onBlockPlacedBy(world, pos, torchBlockState, player, stack);
		}

		return true;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		

		
		return false;
	}
	
}
