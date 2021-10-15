package poseidon.mod.objects.tools.teslainfuser.abilities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class AbilityChange implements TeslaProperties {

	public static void activate(ItemStack stack, EntityPlayer player, String type) {
		if(stack.hasTagCompound()) {
			
			int[] a = stack.getTagCompound().getIntArray("DataArray");
			NBTTagCompound nbt = new NBTTagCompound();
			
			nbt.setIntArray("DataArray", a);
			nbt.setBoolean("Activated", !stack.getTagCompound().getBoolean("Activated"));
			
			System.out.println("Activated to " + nbt.getBoolean("Activated"));
			
			if(!nbt.getBoolean("Activated")) {
				player.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + type + " Ability has been " + TextFormatting.RED + "deactivated.", new Object[0]), true);
			}
			
			stack.setTagCompound(nbt);
		}
	}
	
	public static List<Entity> getVicinity(World worldIn, BlockPos pos, EntityPlayer player) {
		List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));

		List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
		List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
		List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
		List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
		
		List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
		List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
		List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
		List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
		
		List<Entity> general = new ArrayList<Entity>();
		general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
		return general;
	}
	
}
