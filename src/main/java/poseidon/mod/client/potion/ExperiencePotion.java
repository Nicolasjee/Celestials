package poseidon.mod.client.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.util.Reference;

public class ExperiencePotion extends Potion {

	public ExperiencePotion(String name, boolean isBadPotion, int colour, int iconIndexX, int iconIndexY) {
		super(isBadPotion, colour);
		setPotionName("effect." + name);
		setIconIndex(iconIndexX, iconIndexY);
		setRegistryName(new ResourceLocation(Reference.MODID + ":" + name));
	}
	
	@Override
	public boolean hasStatusIcon() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + "textures/gui/potion_effects.png"));
		return true;
	}
	
	
	
}
