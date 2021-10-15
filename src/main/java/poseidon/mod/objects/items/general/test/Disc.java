package poseidon.mod.objects.items.general.test;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IHasModel;

public class Disc extends ItemRecord implements IHasModel
{
	
	SoundEvent sound;
  
	public Disc(String name, SoundEvent e) {
		super(name, e);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		setMaxStackSize(1);
		sound = e;
		ItemInit.ITEMS.add(this);
	}
	
	
	
	@Override
	public SoundEvent getSound() {
		return this.sound;
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
