package poseidon.mod.objects.items;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;

public class ItemImmigrant extends ItemRecord {

	public ItemImmigrant(String name, SoundEvent soundIn) {
		super(name, soundIn);
		
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		
		ItemInit.ITEMS.add(this);
	}

}
