package poseidon.mod.entity.render.living;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.entity.entities.EntityExperienceCreeper;
import poseidon.mod.entity.model.ModelExperienceCreeper;
import poseidon.mod.util.Reference;

public class RenderExperienceCreeper extends RenderLiving<EntityExperienceCreeper> {
		
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/experiencecreeper.png");
	
	public RenderExperienceCreeper(RenderManager manager) {
		super(manager, new ModelExperienceCreeper(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityExperienceCreeper entity) {
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityExperienceCreeper entityLiving, float a, float b, float c) {
		super.applyRotations(entityLiving , a, b, c);
	}
	
}
