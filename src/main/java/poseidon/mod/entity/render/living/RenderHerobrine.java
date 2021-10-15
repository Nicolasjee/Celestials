package poseidon.mod.entity.render.living;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.entity.entities.EntityHerobrine;
import poseidon.mod.entity.model.ModelHerobrine;
import poseidon.mod.util.Reference;

public class RenderHerobrine extends RenderLiving<EntityHerobrine> {
		
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/herobrine.png");
	
	public RenderHerobrine(RenderManager manager) {
		super(manager, new ModelHerobrine(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityHerobrine entity) {
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityHerobrine entityLiving, float a, float b, float c) {
		super.applyRotations(entityLiving , a, b, c);
	}
	
}
