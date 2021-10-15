package poseidon.mod.util.helpers;

import net.minecraft.nbt.NBTTagCompound;
import poseidon.mod.objects.block.aiolonsynth.TileEntityAiolonSynth;
import poseidon.mod.objects.block.drawbridge.TileEntityDrawbridge;
import poseidon.mod.objects.block.redstonereceiverold.TileEntityRedstoneReceiver;

public class TileEntityHelper {

	public static NBTTagCompound toNBT(Object o) {
		if(o instanceof TileEntityRedstoneReceiver) {
			return writeReceiver((TileEntityRedstoneReceiver)o);
		}
		if(o instanceof TileEntityAiolonSynth) {
			return writeAiolonSynth((TileEntityAiolonSynth)o);
		}
		if(o instanceof TileEntityDrawbridge) {
			return writeDrawbridge((TileEntityDrawbridge)o);
		}
		return null;
	}
	
	private static NBTTagCompound writeReceiver(TileEntityRedstoneReceiver o) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("y", o.y);
		compound.setInteger("x", o.x);
		compound.setInteger("z", o.z);
		compound.setInteger("power", o.power);
		return compound;
	}
	
	private static NBTTagCompound writeAiolonSynth(TileEntityAiolonSynth o) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setBoolean("active", o.active);
		compound.setInteger("progress", o.progress);
		compound.setInteger("power", o.power);
		return compound;
	}
	
	private static NBTTagCompound writeDrawbridge(TileEntityDrawbridge o) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setBoolean("toggle", o.toggle);
		compound.setInteger("progress", o.d);
		return compound;
	}
	
}
