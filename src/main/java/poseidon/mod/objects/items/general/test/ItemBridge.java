package poseidon.mod.objects.items.general.test;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.objects.items.general.ItemBase;
import poseidon.mod.objects.items.wand.BlockHelper;

public class ItemBridge extends ItemBase {

	NBTTagCompound nbt;
	
	public static ItemCoalRemover item;
	
	public ItemBridge(String name) {
		super(name, 1, false);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack held = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote && held.hasTagCompound()) {
			
			double[] coords = getCoords(held);
			double x1 = coords[0]; double y1 = coords[1]; double z1 = coords[2];
			if(!(x1 != 0 && y1 != 1 && z1 != 1)) return new ActionResult(EnumActionResult.SUCCESS, held);
			
			RayTraceResult pos = playerIn.rayTrace(100,20);
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();
			
			if((x1 == x || z1 == z) && y == y1) {
				if(x1 == x && z1 != z && y == y1) BlockHelper.putBridge("x", worldIn, playerIn, coords, new double[] {x,y,z});
				if(x1 != x && z1 == z && y == y1) BlockHelper.putBridge("z", worldIn, playerIn, coords, new double[] {x,y,z});
			}
			
			
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, held);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(!stack.hasTagCompound()) getNBT(stack);
		
	}
	
	private double[] getCoords(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		double x = nbt.getDouble("Coordinatesx");
		double y = nbt.getDouble("Coordinatesy");
		double z = nbt.getDouble("Coordinatesz");
		return new double[] {x,y,z};
	}
	private void getNBT(ItemStack stack) {
		
		if(!stack.hasTagCompound()) {
			nbt = new NBTTagCompound();
		}
		if(nbt.hasKey("Coordinatesx")) {
			nbt.setDouble("Coordinatesx", nbt.getDouble("Coordinatesx"));
			} else {
			nbt.setDouble("Coordinatesx", 0);
		}
		if(nbt.hasKey("Coordinatesy")) {
			nbt.setDouble("Coordinatesy", nbt.getDouble("Coordinatesy"));
			} else {
			nbt.setDouble("Coordinatesy", 1);
		}
		if(nbt.hasKey("Coordinatesz")) {
			nbt.setDouble("Coordinatesz", nbt.getDouble("Coordinatesz"));
			} else {
			nbt.setDouble("Coordinatesz", 1);
		}

		stack.setTagCompound(nbt);
		}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		player.sendStatusMessage(new TextComponentTranslation(TextFormatting.BLUE + "Position: " + TextFormatting.RESET + 
																TextFormatting.DARK_BLUE + "[ " + TextFormatting.RESET +
																TextFormatting.GOLD + "X" + TextFormatting.RESET +
																TextFormatting.WHITE + ":" + TextFormatting.RESET + 
																TextFormatting.YELLOW + x + TextFormatting.RESET +
																TextFormatting.GRAY + "," + TextFormatting.RESET +
																TextFormatting.GOLD + "Y" + TextFormatting.RESET +
																TextFormatting.WHITE + ":" + TextFormatting.RESET +
																TextFormatting.YELLOW + y + TextFormatting.RESET +
																TextFormatting.GRAY + "," + TextFormatting.RESET +
																TextFormatting.GOLD + "Z" + TextFormatting.RESET + 
																TextFormatting.WHITE + ":" + TextFormatting.RESET + 
																TextFormatting.YELLOW + z + TextFormatting.RESET +
																TextFormatting.DARK_BLUE + " ]", new Object[0]), true
		);

	
		
		ItemStack stack = player.getHeldItem(hand);
		if(!stack.hasTagCompound()) {
			getNBT(stack);
			} else {
			stack.getTagCompound().setDouble("Coordinatesx", x);
			stack.getTagCompound().setDouble("Coordinatesy", y);
			stack.getTagCompound().setDouble("Coordinatesz", z);
		}
		
		
		return EnumActionResult.SUCCESS;
	}
	
}
