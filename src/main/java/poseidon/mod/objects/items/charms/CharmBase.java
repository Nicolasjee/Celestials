package poseidon.mod.objects.items.charms;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.ToolHoe;

public class CharmBase extends ToolHoe {

	static boolean active = false;
	
	public CharmBase(String name, ToolMaterial tool) {
		super(name, tool);
		if(this == ItemInit.DEPTH_STRIDER_CHARM) ItemInit.ITEMS.add(this);
		if(this == ItemInit.FALL_CHARM) ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		if(stack.getItem() == ItemInit.DEPTH_STRIDER_CHARM) getNBT(stack);
		if(stack.getItem() == ItemInit.FALL_CHARM) getNBT(stack);
	}
	
	private void getNBT(ItemStack stack) {
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) nbt = stack.getTagCompound();
		else nbt = new NBTTagCompound();
		
		if(stack.getItem() == ItemInit.DEPTH_STRIDER_CHARM) {
			if(nbt.hasKey("Activation")) nbt.setBoolean("Activation", nbt.getBoolean("Activation"));
			else nbt.setBoolean("Activation", false);
		}
		
		if(stack.getItem() == ItemInit.FALL_CHARM) {
			if(nbt.hasKey("Activated")) nbt.setBoolean("Activated", nbt.getBoolean("Activated"));
			else nbt.setBoolean("Activated", false);
		}
		
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.PASS;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) { 
		if(CharmHelper.isDepth(stack) && stack.hasTagCompound() && stack.getTagCompound().hasKey("Activated")) return stack.getTagCompound().getBoolean("Activated");
		else if(CharmHelper.isFall(stack) && stack.hasTagCompound() && stack.getTagCompound().hasKey("Activation")) return stack.getTagCompound().getBoolean("Activation");
		else return false;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) entityIn;
			ItemStack held = player.getHeldItemMainhand();
			
			CharmHelper.spawnParticles(worldIn, player, player.getHeldItemMainhand());
			
			if(held.getItem() == ItemInit.DEPTH_STRIDER_CHARM) CharmHelper.depthStrider(player, held, worldIn);
			if(CharmHelper.isDepth(held) && held.hasTagCompound() && held.getTagCompound().getBoolean("Activation") && !player.isInWater()) held.getTagCompound().setBoolean("Activation", false);
			if(CharmHelper.nbtStack(held) && !held.hasTagCompound()) getNBT(stack);
			if(stack.getItem() == ItemInit.FIRE_PROTECTION_CHARM && (player.isInLava() || player.isBurning())) player.setFire(0);
			
			if(!worldIn.isRemote && held.getItem() == ItemInit.REGENERATION_CHARM) {
				
				if(player.getHealth() != player.getMaxHealth()) {
					player.setHealth(player.getHealth() + 1.0F);
					player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 1, 20));
					
					if(player.getHeldItemOffhand().getItem() == stack.getItem())player.getHeldItemOffhand().damageItem(1,player);
					if(player.getHeldItemMainhand().getItem() == stack.getItem()) player.getHeldItemMainhand().damageItem(1, player);
				}
			}
			
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack held = playerIn.getHeldItemMainhand();
		NBTTagCompound nbt;
		
		if(CharmHelper.isFall(held) && held.hasTagCompound() && held.getTagCompound().hasKey("Activated")) held.getTagCompound().setBoolean("Activated", !held.getTagCompound().getBoolean("Activated"));
		if(CharmHelper.isDepth(held) && held.hasTagCompound() && held.getTagCompound().hasKey("Activation")) held.getTagCompound().setBoolean("Activation", !held.getTagCompound().getBoolean("Activation"));
		
		
		return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.WHITE + CharmHelper.getTool(stack));
	}
	
}
