package poseidon.mod.objects.items.wands;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.Multi;
import poseidon.mod.objects.tools.ToolSword;
import poseidon.mod.util.ParticleUtil;

public class ItemFlyStaff extends Multi {

	boolean warn = true;
	
	public ItemFlyStaff(String name, boolean e) {
		super(name, ItemInit.VELOCITY);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
		if(!worldIn.isRemote) playerIn.fallDistance = 0.0F;
	}
	
	
	private void getNBT(ItemStack stack) {
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		}
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", 0);
		}
		if(nbt.hasKey("Activated")) {
			nbt.setBoolean("Activated", nbt.getBoolean("Activated"));
			} else {
			nbt.setBoolean("Activated", true);
		}
		stack.setTagCompound(nbt);
	}
	
	public static boolean isAmmo(ItemStack ammo, ItemStack ammoStrong, ItemStack ammoMedium, ItemStack ammoWeak) {
		if(ammo.getItem() == ammoStrong.getItem() || ammo.getItem() == ammoMedium.getItem() || ammo.getItem() == ammoWeak.getItem()) return true;
		else return false;
	}

	private float getForce(ItemStack ammoStrong, ItemStack ammoMedium, ItemStack ammoWeak, ItemStack ammo) {
		if(ammo.getItem() == ammoStrong.getItem()) return 9.0F;
		if(ammo.getItem() == ammoMedium.getItem()) return 4.0F;
		if(ammo.getItem() == ammoWeak.getItem()) return 1.0F;
		return 1.0F;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		
		ItemStack heldItem = player.getHeldItemMainhand();
		ItemStack ammo = player.getHeldItemOffhand();
		ItemStack ammoStrong = new ItemStack(ItemInit.ELYTRON_DUST);
		ItemStack ammoMedium = new ItemStack(Items.GUNPOWDER);
		ItemStack ammoWeak = new ItemStack(Items.GLOWSTONE_DUST);
		
		boolean flag = isAmmo(ammo, ammoStrong, ammoMedium, ammoWeak);
		float force = getForce(ammoStrong, ammoMedium, ammoWeak, ammo);


		if(flag) {
			
			if(!player.isElytraFlying()) {
				
			    float yaw = player.rotationYaw;
			    float pitch = player.rotationPitch;
			    float f = 9.0F;
			    double motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * force);
			    double motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * force);
			    double motionY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * (force * ((float) 3 / 4)));
			    player.setVelocity(motionX, motionY, motionZ);
				   	
				   	

			 
				   	
		
				heldItem.damageItem(1, player);
				player.getHeldItemOffhand().shrink(1);
		
			}
			   	
			

			else if(player.isElytraFlying()) {
			
					
				double camX = player.getLookVec().x;
			    double camY = player.getLookVec().y;
			    double camZ = player.getLookVec().z;
				
			    double velocityAddedX = camX * force;
			    double velocityAddedY = camY * force;
			    double velocityAddedZ = camZ * force;
			     
			    double currentVelocityX = player.motionX + velocityAddedX;
			    double currentVelocityY = player.motionY + velocityAddedY;
			    double currentVelocityZ = player.motionZ + velocityAddedZ;
			    
				heldItem.damageItem(1, player);
				player.getHeldItemOffhand().shrink(1);
			    
			    player.setVelocity(currentVelocityX, currentVelocityY, currentVelocityZ);
			    Main.proxy.playSoundBlock(player.getPosition(), worldIn, SoundEvents.ENTITY_FIREWORK_LAUNCH, 1.0F, 1.0F);
		
			}
		}
		
			
		
		return new ActionResult(EnumActionResult.SUCCESS, heldItem);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(entityIn != null && entityIn instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) entityIn;
			if(playerIn.inventory.hasItemStack(stack) && playerIn.getHeldItemMainhand() == stack) {
				if(!worldIn.isRemote) ParticleUtil.velocity((WorldServer) worldIn, playerIn);
			}
			
			
		}
    		
	}
	
	private void trail(EntityPlayer player, World world) {
		
		Vec3d dir = player.getLook(1.0F).normalize();
		BlockPos pos = player.getPosition();
		
		double t = 0;
		double tm = 6000;
		double x = 0;
		double y = 0;
		double z = 0;
		
		
		while(tm > 1) {
			
		
			
			t += 0.5;
			x = dir.x * t + pos.getX();
			y = dir.y * t  + 1.5D + pos.getY();
			z = dir.z * t + pos.getZ();
			pos.add(x,y,z);
			world.spawnParticle(EnumParticleTypes.LAVA, x, y, z, 0, 0, 0);
			pos.subtract(new Vec3i(x,y,z));
		
			
			tm--;
			
		}
		particle3b(player, world, new BlockPos(x, y, z), dir);
		
		
   }
		
	private void particle3b(EntityPlayer player, World world, BlockPos pos, Vec3d dir) {

		BlockPos pos2 = pos;
		
		double t = 0;
		double tm = 6000;
		
		
		while(tm > 1) {
			
		
			
			t += 0.5;
			double x = dir.x * t + pos.getX();
			double y = dir.y * t  + 1.5D + pos.getY();
			double z = dir.z * t + pos.getZ();
			pos.add(x,y,z);
			world.spawnParticle(EnumParticleTypes.LAVA, x, y, z, 0, 0, 0);
			pos.subtract(new Vec3i(x,y,z));
			
			tm--;
			
		}
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.WHITE + "Boosts velocity ");

	}
	
}
