package poseidon.mod.entity.entities;

import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityBlueDye extends EntitySilverfish {

	public EntityBlueDye(World world) {
		super(world);
	}
	
	@Override
	public boolean isAIDisabled() {
		return false;
	}
	
	@Override
	protected void updateAITasks() {
		
		super.updateAITasks();
	}
	
	@Override
	protected void initEntityAI() {
		
		this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] { EntityPigZombie.class }));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
		
		super.initEntityAI();
	}

	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		
		World worldIn = this.world;
		BlockPos pos = this.getPosition();
		
		if(!worldIn.isRemote) {
			
			WorldServer server = (WorldServer) worldIn;
			EnumParticleTypes type = EnumParticleTypes.END_ROD;
			
			double pi = Math.PI;
			double i = 0.0D;
			double iStep = (pi*2)/180;
			
			double x = pos.getX() + 0.5D; double y = pos.getY(); double z = pos.getZ() + 0.5D;
			
			while(i < pi*2) {
				server.spawnParticle(type, true, x + 0.5*Math.cos(i), y + 0.1D, z + 0.5*Math.sin(i), 1, 0.0D, 0.0D, 0.0D, 0);
				i = i + iStep;
			}
			
		}
		
		return super.attackEntityFrom(source, amount);
	}


	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PARROT_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_PARROT_HURT;
	}
	
	
	
}
