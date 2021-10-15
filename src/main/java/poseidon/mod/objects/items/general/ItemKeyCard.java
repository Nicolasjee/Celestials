package poseidon.mod.objects.items.general;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;

public class ItemKeyCard extends ItemBase {

	public ItemKeyCard(String name) {
		super(name, 1, false);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound()) {
			getNBT(stack);
		}
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Codes") && entityIn instanceof EntityPlayer) {
			
			if(((EntityPlayer)entityIn).getHeldItemMainhand().getItem() == ItemInit.KEYCARD) {
				int code = stack.getTagCompound().getIntArray("Codes")[0];
				if(code != 0) ((EntityPlayer)entityIn).sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + "Keycode: " + TextFormatting.WHITE + code, new Object[0]), true);
			}
			
		}
		

	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(player.getHeldItemMainhand().getItem() == ItemInit.KEYCARD && player.getHeldItemMainhand().hasTagCompound()) {
			NBTTagCompound c = player.getHeldItemMainhand().getTagCompound();
			c.setIntArray("Codes", new int[] {c.getIntArray("Codes")[0],pos.getX(), pos.getY(), pos.getZ(), player.dimension});
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	private void getNBT(ItemStack stack) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setIntArray("Codes", new int[] {0,0,0,0,0});
		stack.setTagCompound(nbt);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
		if(stack.hasTagCompound()) {
			
			NBTTagCompound nbt = stack.getTagCompound();
			
			if(nbt.hasKey("Codes")) {
				
				int[] a = nbt.getIntArray("Codes");
				int code = a[0];
				int x = a[1];
				int y = a[2];
				int z = a[3];
				
				if(code != 0) tooltip.add("KeyCode: " + TextFormatting.WHITE + Integer.toString(code));
				if(x != 0 && y != 0 && z != 0) tooltip.add(TextFormatting.GRAY + "Saved Coords" + TextFormatting.WHITE + " - " + TextFormatting.GOLD + "X" + TextFormatting.WHITE + ": " + TextFormatting.GRAY + Integer.toString(x)
																								+ TextFormatting.WHITE + ", "  + TextFormatting.GOLD + "Y" + TextFormatting.WHITE + ": " + TextFormatting.GRAY + Integer.toString(y)
																								+ TextFormatting.WHITE + ", "  + TextFormatting.GOLD + "Z" + TextFormatting.WHITE + ": " + TextFormatting.GRAY + Integer.toString(z));
				
			}
			
		}
		
	}
	
}
