package poseidon.mod.objects.tools.teslainfuser;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

public class TeslaTooltip implements TeslaProperties {

	public static void writeTooltip(ItemStack stack, List<String> list) {
		
		if(stack.hasTagCompound()) {
			
			NBTTagCompound nbt = stack.getTagCompound();
			int[] data = nbt.getIntArray("DataArray");
			int focus = TeslaProperties.focus(data);
			String a1 = TeslaProperties.getAbilityOfIndex(0, stack);
			String a2 = TeslaProperties.getAbilityOfIndex(1, stack);
			String a3 = TeslaProperties.getAbilityOfIndex(2, stack);
			String a4 = TeslaProperties.getAbilityOfIndex(3, stack);
			write(a1, a2, a3, a4, focus, list);
			
		}
		
	}
	
	private static void write(String a1, String a2, String a3, String a4, int focus, List<String> tt) {
		String d = "Allocated power: ";
		if(a1 == EMPTY) tt.add(TextFormatting.GRAY + d + TextFormatting.RED + a1);
		
		if(a1 != EMPTY && focus != 0) tt.add(TextFormatting.GRAY + d + TextFormatting.GREEN + a1);
		else if(a1 != EMPTY && focus == 0) tt.add(TextFormatting.WHITE + d + TextFormatting.GREEN + a1);
		
		if(a2 == EMPTY) tt.add(TextFormatting.GRAY + d + TextFormatting.RED + a2);
		
		if(a2 != EMPTY && focus != 1) tt.add(TextFormatting.GRAY + d + TextFormatting.GREEN + a2);
		else if(a2 != EMPTY && focus == 1) tt.add(TextFormatting.WHITE + d + TextFormatting.GREEN + a2);
		
		if(a3 == EMPTY) tt.add(TextFormatting.GRAY + d + TextFormatting.RED + a3);
		
		if(a3 != EMPTY && focus != 2) tt.add(TextFormatting.GRAY + d + TextFormatting.GREEN + a3);
		else if(a3 != EMPTY && focus == 2) tt.add(TextFormatting.WHITE + d + TextFormatting.GREEN + a3);
		
		if(a4 == EMPTY) tt.add(TextFormatting.GRAY + d + TextFormatting.RED + a4);
		
		if(a4 != EMPTY && focus != 3) tt.add(TextFormatting.GRAY + d + TextFormatting.GREEN + a4);
		else if(a4 != EMPTY && focus == 3) tt.add(TextFormatting.WHITE + d + TextFormatting.GREEN + a4);
	}

	
}
