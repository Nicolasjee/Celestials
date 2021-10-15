package poseidon.mod.entity.render.living;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.entity.entities.EntitySwarmZombie;
import poseidon.mod.entity.model.ModelSwarmZombie;
import poseidon.mod.util.Reference;

public class RenderSwarmZombie extends RenderLiving<EntitySwarmZombie> {
		
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/swarmzombie.png");
	
	public RenderSwarmZombie(RenderManager manager) {
		super(manager, new ModelSwarmZombie(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntitySwarmZombie entity) {
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntitySwarmZombie entityLiving, float a, float b, float c) {
		super.applyRotations(entityLiving , a, b, c);
	}
	
}
