package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class Tesla extends CommandEffect implements TeslaProperties {
	
	public Tesla() {
		
	}
	
	public static void execute(EntityPlayer playerIn, int i) {
		
		World worldIn = playerIn.world;
		
		ItemStack stack = playerIn.getHeldItemMainhand();
		
		if(stack.getItem() == ItemInit.TESLA_INFUSER_OFF || stack.getItem() == ItemInit.TESLA_INFUSER) {
			
			if(stack.hasTagCompound()) {
				
				NBTTagCompound nbt = stack.getTagCompound();
				
				if(nbt.hasKey("DataArray")) {
					
					
					int[] d = nbt.getIntArray("DataArray");
					
					if(hasFreePower(d)) {
						
						int slot = getSlot(d);
						
						if(slot == 0) nbt.setIntArray("DataArray", new int[] {i, d[1], d[2], d[3], d[4], d[5]});
						if(slot == 1) nbt.setIntArray("DataArray", new int[] {d[0], i, d[2], d[3], d[4], d[5]});
						if(slot == 2) nbt.setIntArray("DataArray", new int[] {d[0], d[1], i, d[3], d[4], d[5]});
						if(slot == 3) nbt.setIntArray("DataArray", new int[] {d[0], d[1], d[2], i, d[4], d[5]});
						
					}
					
					
				}
				
			}
			
		}
		
		if(stack.getItem() == ItemInit.UPGRADE_MODULE) {
			NBTTagCompound nbt = new NBTTagCompound();
			ItemStack stack2 = TeslaProperties.addRandomly(new ItemStack(ItemInit.UPGRADE_MODULE));
			if(stack2.hasTagCompound()) {
				stack.setTagCompound(stack2.getTagCompound());
			}
		}
		
	}
	
	private static boolean hasFreePower(int[] d) {
		
		int sum = d[0] + d[1] + d[2] + d[3];
		
		if(sum < 5) return true;
		else return false;
		
	}
	
	private static int getSlot(int[] d) {
		if(d[0] == 0) return 0;
		if(d[1] == 0) return 1;
		if(d[2] == 0) return 2;
		if(d[3] == 0) return 3;
		else {
			System.out.println("All slots weren't 0");
			return 0;
		}
	}
	
}
