package poseidon.mod.entity.render.living;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.entity.entities.EntityCustomVillager;
import poseidon.mod.entity.entities.EntityDemon;
import poseidon.mod.entity.model.ModelDemon;
import poseidon.mod.util.Reference;

public class RenderCustomVillager extends RenderLiving<EntityCustomVillager> {
		
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/lucifer.png");
	
	public RenderCustomVillager(RenderManager manager) {
		super(manager, new ModelDemon(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityCustomVillager entity) {
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityCustomVillager entityLiving, float a, float b, float c) {
		super.applyRotations(entityLiving , a, b, c);
	}
	
}
