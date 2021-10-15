package poseidon.mod.entity.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import poseidon.mod.init.BlockInit;
import poseidon.mod.util.handlers.registry.LootTableHandler;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class EntityDemon extends EntityZombie {

	public EntityDemon(World worldin) {
		super(worldin);
		this.setSize(0.6F, 1.95F);
		setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
	}
	
	
	
	@Override
	public void onAddedToWorld() {
		System.out.println("Entity Added");
		if(!this.world.isRemote) {
			setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.GOLDEN_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
			//this.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), I);
		}
		
		if(this.world.isRemote) {
			setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
		}
		
	}
	

	@Override
	protected boolean shouldBurnInDay() {
		return false;
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}
	
	

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIZombieAttack(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.tasks.addTask(9, new EntityAIAvoidEntity<EntityVillager>(this, EntityVillager.class, 2.0F, 1.0F, 1.0F));

		this.applyEntityAI();
	}
	
	@Override
	protected boolean canEquipItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return super.canEquipItem(stack);
	}

	@Override
	protected void applyEntityAI() {
		this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] { EntityPigZombie.class }));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
	}

	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {

		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));

	}
	
	@Override
	public void onDeath(DamageSource cause) {
		//this.world.setBlockState(this.getPosition(), BlockInit.DEMONAIR.getDefaultState());
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return LootTableHandler.CHEST;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

	}

	@Override
	public float getEyeHeight() {
		return 1.0F;
	}
	/*
	 * @Override protected ResourceLocation getLootTable() { return
	 * LootTableHandler.CASTIEL; }
	 */

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WITHER_AMBIENT;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundsHandler.WILLHOT;
	}

	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		return 5;
	}


	@Override
	protected SoundEvent getFallSound(int heightIn) {
		if (heightIn == 1)
			return SoundsHandler.WILLAH;
		return SoundsHandler.WILLAH;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsHandler.DEMONDEAD;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_BLAZE_HURT;
	}

}