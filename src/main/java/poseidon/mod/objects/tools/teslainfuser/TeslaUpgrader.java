package poseidon.mod.objects.tools.teslainfuser;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;

public class TeslaUpgrader implements TeslaProperties {

	public static ItemStack getItemStack(ItemStack module, ItemStack saber, World worldIn) {

		if(module.hasTagCompound() && saber.hasTagCompound()) {
			
			int slot = getNextSlot(saber);
			if(slot == 100) return ItemStack.EMPTY;
			
			String s = module.getTagCompound().getString("upgradePower");
			String t = TeslaProperties.getAbilityString(s);
			int abilityNumber = TeslaProperties.getNumberFromPower(t);
			
			int[] data = saber.getTagCompound().getIntArray("DataArray");
			ItemStack stack = new ItemStack(ItemInit.TESLA_INFUSER_OFF);
			
			return apply(slot, data, abilityNumber, stack);
			
		}
		
		System.out.println("Oof. Input saber was returned. Module.comp(): " + module.hasTagCompound() + ", saber.comp(): " +
							saber.hasTagCompound() + ", module: " + module + ", moduleItem: " + module.getItem() +
							", saber: " + saber + ", saberItem: " + saber.getItem());
		return saber;
		
	}
	
	private static int getNextSlot(ItemStack saber) {
		
		String a1 = TeslaProperties.getAbilityOfIndex(0, saber);
		String a2 = TeslaProperties.getAbilityOfIndex(1, saber);
		String a3 = TeslaProperties.getAbilityOfIndex(2, saber);
		String a4 = TeslaProperties.getAbilityOfIndex(3, saber);
		
		if(a1 != EMPTY && a2 != EMPTY && a3 != EMPTY && a4 != EMPTY) return 100;
		if(a1 == EMPTY && a2 == EMPTY && a3 == EMPTY && a4 == EMPTY) return 0;
		if(a1 != EMPTY && a2 == EMPTY && a3 == EMPTY && a4 == EMPTY) return 1;
		if(a1 != EMPTY && a2 != EMPTY && a3 == EMPTY && a4 == EMPTY) return 2;
		if(a1 != EMPTY && a2 != EMPTY && a3 != EMPTY && a4 == EMPTY) return 3;
		else return 100;
	}
	
	private static ItemStack apply(int slot, int[] a, int abilityNumber, ItemStack stack) {
		
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("Activated", false);
		
		if(slot == 0) nbt.setIntArray("DataArray", new int[] {abilityNumber, a[1], a[2], a[3], a[4], a[5]});
		if(slot == 1) nbt.setIntArray("DataArray", new int[] {a[0], abilityNumber, a[2], a[3], a[4], a[5]});
		if(slot == 2) nbt.setIntArray("DataArray", new int[] {a[0], a[1], abilityNumber, a[3], a[4], a[5]});
		if(slot == 3) nbt.setIntArray("DataArray", new int[] {a[0], a[1], a[2], abilityNumber, a[4], a[5]});
		
		stack.setTagCompound(nbt);
		stack.setItemDamage(durability - a[5]);
		
		return stack;
	}
	
}
