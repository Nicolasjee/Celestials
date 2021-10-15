package poseidon.mod.objects.tools.teslainfuser;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.wand.ModAbilities;
import poseidon.mod.objects.tools.ToolSword;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityChange;
import poseidon.mod.objects.tools.teslainfuser.abilities.AbilityMiner;

public class ItemTeslaInfuser extends ToolSword implements TeslaProperties {

	int tick = 0;
	
	public ItemTeslaInfuser(String name, ToolMaterial t) {
		super(name, t, false);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(!stack.hasTagCompound()) getNBT(stack);
		
		if(entityIn instanceof EntityPlayer && stack.hasTagCompound()) {
			
			EntityPlayer player = (EntityPlayer) entityIn;
			NBTTagCompound nbt = stack.getTagCompound();
			int focus = TeslaProperties.focus(nbt.getIntArray("DataArray"));
			
			if(player.getHeldItemMainhand().getItem() == stack.getItem() && stack.hasTagCompound() && hasPowersAttached(stack) && focusHasAbility(stack) && !worldIn.isRemote) {
				
				String s = TeslaProperties.getAbilityOfIndex(focus, stack);
				
				
				if(s == STOP && nbt.getBoolean("Activated")) {
					
					if(tick == 20) {
						
						player.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + STOP + " Ability is " + TextFormatting.GREEN + "activated", new Object[0]), true);
						subtractDurBy(STOPCOST, stack);
						stack.damageItem(STOPCOST, player);
						
						ModAbilities.stopArrow(1, player, worldIn);
						if(!AbilityChange.getVicinity(worldIn, player.getPosition(), player).isEmpty()) {
							ModAbilities.freezeEntities(0, player, worldIn);
						}
						
						tick = 0;
						
					}
					
					tick++;
					
				}
				
			}
		}
	}
	
	private void getNBT(ItemStack stack) {
		if(!stack.hasTagCompound()) {
			
			NBTTagCompound nbt = new NBTTagCompound();
		
			//Ability, Ability, Ability, Ability, Focus, Durability
			nbt.setIntArray("DataArray", new int[] {0,0,0,0,0,durability});
			nbt.setBoolean("Activated", false);
			
			
			stack.setTagCompound(nbt);
			
		}
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}

	public static void registerRecipe() {

	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack held = playerIn.getHeldItem(handIn);
		
		if(isValid(held) && playerIn.isSneaking()) {
			
			NBTTagCompound nbt = held.getTagCompound();

			int focus = TeslaProperties.focus(nbt.getIntArray("DataArray"));
			int durability = TeslaProperties.dur(nbt.getIntArray("DataArray"));
			String ability = TeslaProperties.getPowerFromNumber(nbt.getIntArray("DataArray")[focus]);
			
			TeslaAbilities.execute(held, playerIn, worldIn);
			
			if(ability == CLOAK || ability == PUSH || ability == STOP) {
				playerIn.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + ability + " Ability is " + TextFormatting.GREEN + "activated", new Object[0]), true);
			}
			
			return new ActionResult(EnumActionResult.SUCCESS, held);
		}
		
		if(isValid(held) && !playerIn.isSneaking()) {

			ItemStack off = transferNBT(held);
			int slot = playerIn.inventory.getSlotFor(held);
			System.out.println("on -> off  --- Dur: " + held.getTagCompound().getIntArray("DataArray")[5]);
			playerIn.inventory.removeStackFromSlot(playerIn.inventory.getSlotFor(held));
			playerIn.inventory.setInventorySlotContents(slot, off);
			
			if(TeslaProperties.getAbilityOfFocus(held) == ELYTRA) {
				TeslaAbilities.execute(held, playerIn, worldIn);
			}
			
			return new ActionResult(EnumActionResult.SUCCESS, held);
			
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, held);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		
		if(attacker instanceof EntityPlayer && stack.hasTagCompound()) {
			EntityPlayer player = (EntityPlayer) attacker;
			NBTTagCompound nbt = stack.getTagCompound();
			stack.damageItem(HITCOST, player);
			int[] a = nbt.getIntArray("DataArray");
			int dur = TeslaProperties.dur(a);
			nbt.setIntArray("DataArray", new int[] {a[0], a[1], a[2], a[3], a[4], dur - HITCOST});
			if(dur < HITCOST) return false;
			else return true;
		}
		else return false;
		
	}
	
	private boolean isValid(ItemStack stack) {
		if(stack.hasTagCompound() && stack.getItem() == ItemInit.TESLA_INFUSER) return true;
		else return false;
	}
	
	private ItemStack transferNBT(ItemStack from) {
		
		ItemStack off = new ItemStack(ItemInit.TESLA_INFUSER_OFF);
		
		if(from.hasTagCompound()) {
			
			NBTTagCompound nbt = from.getTagCompound();
			NBTTagCompound ndt = new NBTTagCompound();
			
			int[] ab = nbt.getIntArray("DataArray");
			ndt.setIntArray("DataArray", ab);

			boolean b = nbt.getBoolean("Activated");
			ndt.setBoolean("Activated", false);
			
			off.setTagCompound(ndt);
			return off;
		}
		System.out.println("NBT has not been transfered");
		return off;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		TeslaTooltip.writeTooltip(stack, tooltip);
	}
	
	private void subtractDurBy(int dur, ItemStack stack) {
		if(stack.hasTagCompound()) {
			int[] d = stack.getTagCompound().getIntArray("DataArray");
			stack.getTagCompound().setIntArray("DataArray", new int[] {d[0], d[1], d[2], d[3], d[4], d[5] - dur});
		}
	}

	private boolean hasPowersAttached(ItemStack stack) {
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey("DataArray")) {
				int[] data = nbt.getIntArray("DataArray");
				if(data[0] == 0 && data[1] == 1 && data[2] == 2 && data[3] == 3) return false;
				else return true;
			}
		}
		System.out.println("Stack didn't have a compound so false was returned.");
		return false;
	}
	
	private boolean focusHasAbility(ItemStack stack) {
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey("DataArray")) {
				int[] d = nbt.getIntArray("DataArray");
				if(TeslaProperties.getAbilityOfFocus(stack) != EMPTY) return true;
				else return false;
			}
		}
		return false;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack stack = player.getHeldItem(hand);
		
		if(isValid(stack) && player.isSneaking()) {
			
			float x = (float) player.getLookVec().x;
		    float y = (float) player.getLookVec().y;
		    float z = (float) player.getLookVec().z;
			
			if(TeslaProperties.getAbilityOfFocus(stack) == MINER) AbilityMiner.execute(player, worldIn, stack, pos, player.getHorizontalFacing().getFacingFromVector(x, y, z));
			
			return EnumActionResult.SUCCESS;
		}
		
		return EnumActionResult.PASS;
		
	}
	
}
