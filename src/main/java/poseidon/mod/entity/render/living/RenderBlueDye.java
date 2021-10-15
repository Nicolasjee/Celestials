package poseidon.mod.entity.render.living;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.entity.entities.EntityBlueDye;
import poseidon.mod.entity.entities.EntityDemon;
import poseidon.mod.entity.model.ModelBlueDye;
import poseidon.mod.util.Reference;

public class RenderBlueDye extends RenderLiving<EntityBlueDye> {
	
public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/bluedye.png");

	public RenderBlueDye(RenderManager manager) {
		super(manager, new ModelBlueDye(), 0.1F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBlueDye entity) {
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityBlueDye entityLiving, float a, float b, float c) {
		super.applyRotations(entityLiving , a, b, c);
	}
}
