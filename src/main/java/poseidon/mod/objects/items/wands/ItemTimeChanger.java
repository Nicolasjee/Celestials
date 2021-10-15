package poseidon.mod.objects.items.wands;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.items.general.ItemBase;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class ItemTimeChanger extends ItemBase {

	NBTTagCompound nbt;

	final int WAIT = 1;
	final int CLOAKCOST = 1000;
	
	public ItemTimeChanger(String name) {
		super(name, 1, false);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.PASS;
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}

	private void getNBT(ItemStack stack) {
		if (stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
		} // Gets The compound which holds keys!
		if (nbt.hasKey("Activated")) {
			nbt.setBoolean("Activated", nbt.getBoolean("Activated"));
		} else {
			nbt.setBoolean("Activated", false);
		}
		if (nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
		} else {
			nbt.setInteger("Durability", 5000);
		}
		if (nbt.hasKey("Wait")) {
			nbt.setInteger("Wait", nbt.getInteger("Wait"));
		} else {
			nbt.setInteger("Wait", WAIT);
		}
		if (nbt.hasKey("Cooldown")) {
			nbt.setInteger("Cooldown", nbt.getInteger("Cooldown"));
		} else {
			nbt.setInteger("Cooldown", 200);
		}
		if (nbt.hasKey("CooldownSet")) {
			nbt.setInteger("CooldownSet", nbt.getInteger("CooldownSet"));
		} else {
			nbt.setInteger("CooldownSet", 200);
		}
		if (nbt.hasKey("CooldownA")) {
			nbt.setBoolean("CooldownA", nbt.getBoolean("CooldownA"));
		} else {
			nbt.setBoolean("CooldownA", false);
		}
		stack.setTagCompound(nbt);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.hasTagCompound()) {
			if (stack.getTagCompound().getBoolean("Activated")) {
				return true;
			}
			if (!stack.getTagCompound().getBoolean("Activated")) {
				return false;
			}
		}
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound())
			tooltip.add(TextFormatting.GRAY + "Durability: " + TextFormatting.GREEN
					+ Integer.toString(stack.getTagCompound().getInteger("Durability")));
			tooltip.add(TextFormatting.GRAY + "Sneak and right-click for a special ability");
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(!stack.hasTagCompound()) getNBT(stack);
		
		if (stack.hasTagCompound() && entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			NBTTagCompound nbt = stack.getTagCompound();
			
			if(valid(stack) && sufficient(stack)) {

				worldIn.setWorldTime((long) (worldIn.getWorldTime() + 55.0));

				int wait = nbt.getInteger("Wait");
				int dur = nbt.getInteger("Durability");

				if (wait != 0) {
					nbt.setInteger("Wait", wait - 1);
				}

				if (wait == 0) {
					nbt.setInteger("Wait", WAIT);
					nbt.setInteger("Durability", dur - 1);
				}
					
			}
			
		
			
			if(nbt.hasKey("CooldownA")) {
				if(nbt.getBoolean("CooldownA") && nbt.hasKey("Cooldown") && nbt.hasKey("CooldownSet") && durK(stack)) {
							
							int c = nbt.getInteger("Cooldown");
							int d = nbt.getInteger("CooldownSet");
							int wait = nbt.getInteger("Wait");
							boolean b = nbt.getBoolean("CooldownA");
							
							if(c != 0 && !b) b = true;
							if(c != 0) c--;
							player.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + "You are " + TextFormatting.GREEN + "invisible" + TextFormatting.GRAY + " to mobs", new Object[0]), true);
							
							if(c == 0) {
								c = d;
								b = false;
								nbt.setInteger("Durability", nbt.getInteger("Durability") - CLOAKCOST);
								player.sendStatusMessage(new TextComponentTranslation(TextFormatting.RED + "You are no longer " + TextFormatting.DARK_RED + "invisible" + TextFormatting.RED + " to mobs", new Object[0]), true);
							}
							
							nbt.setInteger("Cooldown", c);
							nbt.setInteger("CooldownSet", d);
							nbt.setBoolean("CooldownA", b);
							
						
					
				}
			}
		}

	}
	
	private boolean durK(ItemStack stack) {
		if(!stack.hasTagCompound()) return false;
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			
			if(nbt.hasKey("Durability")) {
				int dur = nbt.getInteger("Durability");
				if(dur > CLOAKCOST) return true;
				else return false;
			}
			
		}
		return false;
	}

	private boolean valid(ItemStack stack) {
		if(!stack.hasTagCompound()) return false;
		else if(stack.hasTagCompound()) {
			
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey("Activated") && nbt.hasKey("CooldownA") && nbt.getBoolean("Activated") && !nbt.getBoolean("CooldownA")) return true;
			else return false;
			
		} else return false;
	}
	
	private boolean sufficient(ItemStack stack) {
		if(!stack.hasTagCompound()) return false;
		if(stack.hasTagCompound()) {
		
			NBTTagCompound nbt = stack.getTagCompound();
			
			if(nbt.hasKey("Durability")) {
				int dur = nbt.getInteger("Durability");
				
				if(dur > 0) return true;
				else if(dur == 0) return false;
				
			}
			
		}
		
		else return false;

		return false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(playerIn.isSneaking()) {
			if(stack.hasTagCompound() && !worldIn.isRemote) 
				if(!stack.getTagCompound().getBoolean("CooldownA")) {
					int d = stack.getTagCompound().getInteger("CooldownSet");
					playerIn.getCooldownTracker().setCooldown(stack.getItem(), d);
					BlockPos pos = playerIn.getPosition();
					worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsHandler.SUBIMPACT, SoundCategory.AMBIENT, 1.0F, 1.0F);
					stack.getTagCompound().setBoolean("CooldownA", true);
				}
		}
		
		if (stack.hasTagCompound() && !playerIn.isSneaking()) {
			if (stack.getTagCompound().hasKey("Activated")) {
				if (stack.getTagCompound().getBoolean("Activated")) {
					stack.getTagCompound().setBoolean("Activated", false);
				} else {
					stack.getTagCompound().setBoolean("Activated", true);
				}
			}
		}

		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
}
