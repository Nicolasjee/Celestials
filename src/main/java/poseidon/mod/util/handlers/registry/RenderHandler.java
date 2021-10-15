package poseidon.mod.util.handlers.registry;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import poseidon.mod.entity.entities.EntityBlueDye;
import poseidon.mod.entity.entities.EntityDemon;
import poseidon.mod.entity.entities.EntityExperienceCreeper;
import poseidon.mod.entity.entities.EntityHerobrine;
import poseidon.mod.entity.entities.EntitySwarmZombie;
import poseidon.mod.entity.render.living.RenderBlueDye;
import poseidon.mod.entity.render.living.RenderDemon;
import poseidon.mod.entity.render.living.RenderExperienceCreeper;
import poseidon.mod.entity.render.living.RenderHerobrine;
import poseidon.mod.entity.render.living.RenderSwarmZombie;

public class RenderHandler {
	
	public static void registerEntityRenders() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrine.class, new IRenderFactory<EntityHerobrine>() {
			@Override
			public Render<? super EntityHerobrine> createRenderFor(RenderManager manager) {
				return new RenderHerobrine(manager);
			}
		});
		

		RenderingRegistry.registerEntityRenderingHandler(EntityExperienceCreeper.class, new IRenderFactory<EntityExperienceCreeper>() {
			@Override
			public Render<? super EntityExperienceCreeper> createRenderFor(RenderManager manager) {
				return new RenderExperienceCreeper(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBlueDye.class, new IRenderFactory<EntityBlueDye>() {
			@Override
			public Render<? super EntityBlueDye> createRenderFor(RenderManager manager) {
				return new RenderBlueDye(manager);
			}
		});
		
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, new IRenderFactory<EntityDemon>() {
			@Override
			public Render<? super EntityDemon> createRenderFor(RenderManager manager) {
				return new RenderDemon(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntitySwarmZombie.class, new IRenderFactory<EntitySwarmZombie>() {
			@Override
			public Render<? super EntitySwarmZombie> createRenderFor(RenderManager manager) {
				return new RenderSwarmZombie(manager);
			}
		});
	}
	
}
