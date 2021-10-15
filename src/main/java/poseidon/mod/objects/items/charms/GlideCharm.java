package poseidon.mod.objects.items.charms;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.tools.ToolHoe;
import poseidon.mod.util.ParticleUtil;

public class GlideCharm extends ToolHoe {

	NBTTagCompound nbt;
	int i = 0;
	
	public GlideCharm(String name, ToolMaterial tool) {
		super(name, tool);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.PASS;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ItemStack held = playerIn.getHeldItem(handIn);
		
		if(!held.hasTagCompound()) {
			getNBT(held);
		}
		
		if(held.hasTagCompound()) {
			
			if(held.getTagCompound().hasKey("Activated")) {
				
				if(held.getTagCompound().getBoolean("Activated") == true) {
					held.getTagCompound().setBoolean("Activated", false);
					return new ActionResult(EnumActionResult.SUCCESS, held);
				}
				
				if(held.getTagCompound().getBoolean("Activated") == false) {
					held.getTagCompound().setBoolean("Activated", true);
					return new ActionResult(EnumActionResult.SUCCESS, held);
				}
			}
			
			return new ActionResult(EnumActionResult.FAIL, held);
			
		}
		
		return new ActionResult(EnumActionResult.FAIL, held);
		
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn != null && entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			
			ItemStack activeItem = player.getHeldItemMainhand();
			ItemStack activeItems = player.getHeldItemOffhand();
			
			if(activeItem.getItem() == stack.getItem() || activeItems.getItem() == stack.getItem()) {
				
				if(!worldIn.isRemote) ParticleUtil.glide((WorldServer) worldIn, player);
				
			}
			
			
			
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
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		
		if(stack.hasTagCompound()) {
			
			if(stack.getTagCompound().getBoolean("Activated") == true) {
				return true;
			}
			
			if(stack.getTagCompound().getBoolean("Activated") == false) {
				return false;
			}
			
			return false;
			
		}
		
		return false;
		
	}
	
	private void getNBT(ItemStack stack) {
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} 
		if(nbt.hasKey("Activated")) {
			nbt.setBoolean("Activated", nbt.getBoolean("Activated"));
			} else {
			nbt.setBoolean("Activated", false);
		}
		stack.setTagCompound(nbt);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.WHITE + "Immunity to fall damage");

	}



}
