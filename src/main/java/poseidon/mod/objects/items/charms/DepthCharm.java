package poseidon.mod.objects.items.charms;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.ToolHoe;
import poseidon.mod.util.ParticleUtil;

public class DepthCharm extends ToolHoe {
	
	NBTTagCompound nbt;
	ItemStack stackDepth;
	int sum = 0;
	int i = 0;
	
	public DepthCharm(String name, ToolMaterial tool) {
		super(name, tool);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.PASS;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		if(stack.hasTagCompound()) {
			
			if(stack.getTagCompound().getBoolean("Activation")) {
				return true;
			}
			
			if(!stack.getTagCompound().getBoolean("Activation")) {
				return false;
			}
			
		}
		return false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		if(stack.hasTagCompound()) {
			if(stack.getTagCompound().getBoolean("Activation")) {
				stack.getTagCompound().setBoolean("Activation", false);
				return new ActionResult(EnumActionResult.SUCCESS, stack);
			}
			if(!stack.getTagCompound().getBoolean("Activation")) {
				stack.getTagCompound().setBoolean("Activation", true);
				return new ActionResult(EnumActionResult.SUCCESS, stack);
			}
			
		} else {
		
			return new ActionResult(EnumActionResult.FAIL, stack);
			
		}
		
		return new ActionResult(EnumActionResult.PASS, stack);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {//done
		if(entityIn != null && entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			
			if(player.getHeldItemMainhand().getItem() == stack.getItem() || player.getHeldItemOffhand().getItem() == stack.getItem()) {
				
				if(!worldIn.isRemote) ParticleUtil.depth((WorldServer) worldIn, player);
				//done
			}
			
			if(!stack.hasTagCompound()) {
				getNBT(stack);
				//done
			}
			
			if(stack.hasTagCompound()) 
				if(stack.getTagCompound().getBoolean("Activation") && !player.isInWater())
					stack.getTagCompound().setBoolean("Activation", false);//done
			
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
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}
	
	private void getNBT(ItemStack stack) {
		System.out.println("setting nbt");
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} //Gets The compound which holds keys!
		if(nbt.hasKey("Activation")) {
			nbt.setBoolean("Activation", nbt.getBoolean("Activation"));
			} else {
			nbt.setBoolean("Activation", false);
		}
		stack.setTagCompound(nbt);
	}
		
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.getItem() == ItemInit.DEPTH_STRIDER_CHARM) {
			tooltip.add(TextFormatting.WHITE + "Provides speed in water");
		}

	}
	
	private void getStackDepth(EntityPlayer player, ItemStack stack) {
		for(int i = 0; i < 36; i ++) {
			ItemStack check = player.inventory.getStackInSlot(i);
			if(check.getItem() == stack.getItem()) {
				stackDepth = stack;
				return;
			}
		}
	}
	
}
