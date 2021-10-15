package poseidon.mod.objects.items.usable;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.items.general.ItemBase;
import poseidon.mod.util.ParticleUtil;

public class ItemStasis extends ItemBase {

	NBTTagCompound nbt;
	
	public ItemStasis(String name, int size, boolean effect) {
		super(name, size, effect);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Stronger than the totem of undying.");
	}

	
	
}
