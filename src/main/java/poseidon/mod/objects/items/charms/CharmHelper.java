package poseidon.mod.objects.items.charms;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.ParticleUtil;

public class CharmHelper {

	public static void spawnParticles(World world, EntityPlayer player, ItemStack stack) {
		
		if(stack.getItem() == ItemInit.DEPTH_STRIDER_CHARM && !world.isRemote) ParticleUtil.depth((WorldServer) world, player);
		if(stack.getItem() == ItemInit.FIRE_PROTECTION_CHARM && !world.isRemote) ParticleUtil.fire((WorldServer) world, player);
		if(stack.getItem() == ItemInit.FALL_CHARM && !world.isRemote) ParticleUtil.glide((WorldServer) world, player);
		if(stack.getItem() == ItemInit.PROJECTILE_CHARM && !world.isRemote) ParticleUtil.projectile((WorldServer) world, player);
		
		
	}

	public static void glide(EntityPlayer player, ItemStack stack, World worldIn) {
		if(stack.hasTagCompound()) {
			if(player.getHeldItemMainhand().getItem() == stack.getItem() || player.getHeldItemOffhand().getItem() == stack.getItem()) {
				if(stack.getTagCompound().getBoolean("Activated") && player.isAirBorne) {
					BlockPos pos = player.getPosition();
					if(worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ())) == Blocks.AIR.getDefaultState()) {
						float yaw = player.rotationYaw;
					    float pitch = player.rotationPitch;
			
					    Vec3d vec = player.getLookVec();
					    
					    double motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * 0.555F);
					    double motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * 0.555F);
					    double motionY = player.motionY * 0.6F;
					    player.setVelocity(motionX, motionY, motionZ);
					    
						
							stack.damageItem(1, player);
						
						} else if(worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ())) != Blocks.AIR.getDefaultState() && stack.getTagCompound().getBoolean("Activated")) {
						
							stack.getTagCompound().setBoolean("Activated", false);
					}
				}
			}
		}
	}
	
	public static void depthStrider(EntityPlayer player, ItemStack stack, World worldIn) {
		int i = 0;
		if(stack.getItem() == ItemInit.DEPTH_STRIDER_CHARM) {
			if(stack.hasTagCompound() && stack.getTagCompound().getBoolean("Activation")) {
				if(player.isInWater()) {
					
					float yaw = player.rotationYaw;
					float pitch = player.rotationPitch;
					
					double motionX = (double)((-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) / 2 ));
					double motionZ = (double)((MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) /2 ));
						    
					player.setVelocity(motionX, player.motionY, motionZ);

					if(i < 5) {
						stack.damageItem(1, player);
						i++;
					}
					if(i >= 5 && i < 11) {
						i++;
					}
					if(i == 11) {
						i = 0;
					}
					
			}
				} else if(stack.hasTagCompound() && 
						!stack.getTagCompound().getBoolean("Activation") && 
						(player.getHeldItemMainhand().getItem() == stack.getItem() || player.getHeldItemOffhand().getItem() == stack.getItem()) && player.isInWater()) {

					player.sendStatusMessage(new TextComponentTranslation(TextFormatting.WHITE + "Right click the item to activate the charm", new Object[0]), true);
			}
		}
		
		
	}

	public static boolean nbtStack(ItemStack stack) {
		if(stack.getItem() == ItemInit.DEPTH_STRIDER_CHARM || stack.getItem() == ItemInit.FALL_CHARM) return true;
		else return false;
	}
	
	public static boolean isDepth(ItemStack stack) {
		if(stack.getItem() == ItemInit.DEPTH_STRIDER_CHARM) return true;
		else return false;
	}
	
	public static boolean isFall(ItemStack stack) {
		if(stack.getItem() == ItemInit.FALL_CHARM) return true;
		else return false;
	}

	public static String getTool(ItemStack stack) {
		if(stack.getItem() == ItemInit.DEPTH_STRIDER_CHARM) return "Provides speed in water";
		if(stack.getItem() == ItemInit.FALL_CHARM) return "Provides immunity to fall damage";
		if(stack.getItem() == ItemInit.PROJECTILE_CHARM) return "Provides immunity to projectiles";
		if(stack.getItem() == ItemInit.FIRE_PROTECTION_CHARM) return "Provides immunity to lava and fire";
		if(stack.getItem() == ItemInit.REGENERATION_CHARM) return "Regenerates the wielder";
		else return "";
	}
	
}
