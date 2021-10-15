package poseidon.mod.objects.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.util.Reference;

public class BadJujuPotion extends Potion {
	
	public BadJujuPotion(String name, boolean bad, int c, int iIx, int iIy) {
		super(bad, c);
		setPotionName("effect." + name);
		setIconIndex(iIx, iIy);
		setRegistryName(new ResourceLocation(Reference.MODID + ":" + name));
	}
	

}
