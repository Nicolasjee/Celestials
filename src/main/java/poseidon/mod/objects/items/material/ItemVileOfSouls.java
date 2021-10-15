package poseidon.mod.objects.items.material;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.entities.CustomXP;
import poseidon.mod.objects.items.general.ItemBase;

public class ItemVileOfSouls extends ItemBase {

	NBTTagCompound nbt;
	int size;
	
	public ItemVileOfSouls(String name, int size, boolean b, int s) {
		super(name, size, b);
		this.size = s;
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}
	
	public void getNBT(ItemStack stack) {
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} //Gets The compound which holds keys!
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", 0);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		if(nbt.hasKey("Activated")) {
			nbt.setBoolean("Activated", nbt.getBoolean("Activated"));
			} else {
			nbt.setBoolean("Activated", false);
		}
		if(nbt.hasKey("Active")) {
			nbt.setInteger("Active", nbt.getInteger("Active"));
			} else {
			nbt.setInteger("Active", 0);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		if(nbt.hasKey("Size")) {
			nbt.setInteger("Size", nbt.getInteger("Size"));
			} else {
			nbt.setInteger("Size", size);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		stack.setTagCompound(nbt);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound()) {
			getNBT(stack);
		}
		
		if(stack.hasTagCompound() && entityIn instanceof EntityPlayer) {
			NBTTagCompound nbt = stack.getTagCompound();
			EntityPlayer player = (EntityPlayer) entityIn;
			
			
			if(nbt.getBoolean("Activated") && nbt.getInteger("Durability") > 0) {
				
				if(!worldIn.isRemote) {
					
					BlockPos pos = player.getPosition();
					player.setHealth(player.getHealth() + 1.0F);
					nbt.setInteger("Active", nbt.getInteger("Active") + 1);
					player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 20, 100));
					
					if(nbt.getInteger("Active") == 10) {
						nbt.setInteger("Active", 0);
						nbt.setInteger("Durability", nbt.getInteger("Durability") - 1);
					}
					
					if(nbt.getInteger("Active") % 2 == 0) {
						CustomXP entity = new CustomXP(worldIn, pos.getX(), pos.getY(), pos.getZ(), 2);
						worldIn.spawnEntity(entity);
					}
					
				}
				
				
				
			}
			
			if(nbt.getInteger("Durability") == 0) {
				nbt.setBoolean("Activated", false);
			}
			
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound())	{
			tooltip.add("Souls: " + TextFormatting.GREEN + Integer.toString(stack.getTagCompound().getInteger("Durability")));
			tooltip.add("Internal power of lost souls will be restored upon intake");
		}
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.getBoolean("Activated") && nbt.getInteger("Durability") > 1) {
				return true;
			} else
			return false;
		}
		return false;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack held = playerIn.getHeldItem(handIn);
		
		if(held.hasTagCompound() && playerIn.isSneaking()) {
			
			NBTTagCompound n = held.getTagCompound();
			
			if(n.getBoolean("Activated")) {	
				n.setBoolean("Activated", false); 
				System.out.println("deactivated");
				return new ActionResult(EnumActionResult.SUCCESS, held);
			}
			
			if(!n.getBoolean("Activated")) { 
				n.setBoolean("Activated", true); 
				System.out.println("activated");
				return new ActionResult(EnumActionResult.SUCCESS, held);
			}
			
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, held);
		
	}
	
	public static boolean hasVile(EntityPlayer player) {
		//Item vile = ItemInit.VILE_OF_SOULS;
		Item heldMain = player.getHeldItemMainhand().getItem();
		Item heldOff = player.getHeldItemOffhand().getItem();
		return false;
		
	}
}
