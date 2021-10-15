package poseidon.mod.objects.items.general;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;

public class ItemBlockSaver extends ItemBase {

	public ItemBlockSaver(String name, int size, boolean b) {
		super(name, size, b);
		if(!(this == ItemInit.BLOCK_SAVER_END || this == ItemInit.BLOCK_SAVER_NETHER || this == ItemInit.BLOCK_SAVER_OVERWORLD)) setCreativeTab(Main.ARISTOISITEMS);
	}
	
	public void getNBT(ItemStack stack) {
		if(!stack.hasTagCompound()) {
			
			NBTTagCompound nbt = new NBTTagCompound();
			
			nbt.setIntArray("Coords", new int[] {0,0,0,0});
			
			stack.setTagCompound(nbt);
			
		}
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound()) getNBT(stack);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand handIn,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack held = playerIn.getHeldItem(handIn);
		

			
			if(held.hasTagCompound() && !worldIn.isRemote) {
				int[] b = new int[] {pos.getX(), pos.getY(), pos.getZ(), playerIn.dimension};
				
				if(playerIn.dimension == 0) {
					ItemStack s = new ItemStack(ItemInit.BLOCK_SAVER_OVERWORLD);
					getNBT(s);
					s.getTagCompound().setIntArray("Coords", b);
					setInInventory(worldIn, playerIn, held, s);
				}
				
				if(playerIn.dimension == -1) {
					ItemStack s = new ItemStack(ItemInit.BLOCK_SAVER_NETHER);
					getNBT(s);
					s.getTagCompound().setIntArray("Coords", b);
					setInInventory(worldIn, playerIn, held, s);
				}
				
				if(playerIn.dimension == 1) {
					ItemStack s = new ItemStack(ItemInit.BLOCK_SAVER_END);
					getNBT(s);
					s.getTagCompound().setIntArray("Coords", b);
					setInInventory(worldIn, playerIn, held, s);
				}
				Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_ITEMFRAME_ADD_ITEM, 1.0F, 0.3F);
			}
			
		
		
		return super.onItemUse(playerIn, worldIn, pos, handIn, facing, hitX, hitY, hitZ);
	}
	
	private void setInInventory(World worldIn, EntityPlayer playerIn, ItemStack held, ItemStack s) {
		
		int slot = playerIn.inventory.getSlotFor(held);
		int size = held.getCount();
		
		double x = playerIn.posX;
		double y = playerIn.posY;
		double z = playerIn.posZ;
		
		if(size == 1) {
			
			held.shrink(1);
			playerIn.inventory.setInventorySlotContents(slot, s);
			
			} else {
		
			held.shrink(1);
			InventoryHelper.spawnItemStack(worldIn, x, y, z, s);
				
		}
		
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound()) {
			
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey("Coords")) {
				
				int[] a = nbt.getIntArray("Coords");
				int x = a[0];
				int y = a[1];
				int z = a[2];
				
				if(x != 0 && y != 0 && z != 0) tooltip.add(TextFormatting.GRAY + "Saved Coords" + TextFormatting.WHITE + " - " + TextFormatting.GOLD + "X" + TextFormatting.WHITE + ": " + TextFormatting.GRAY + Integer.toString(x)
				+ TextFormatting.WHITE + ", "  + TextFormatting.GOLD + "Y" + TextFormatting.WHITE + ": " + TextFormatting.GRAY + Integer.toString(y)
				+ TextFormatting.WHITE + ", "  + TextFormatting.GOLD + "Z" + TextFormatting.WHITE + ": " + TextFormatting.GRAY + Integer.toString(z));
				
			}
			
		}
	}
}
