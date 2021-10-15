package poseidon.mod.entity.entities;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;

public class EntityExperienceCreeper extends EntityZombie {

	public EntityExperienceCreeper(World worldin) {
		super(worldin);
		this.setSize(0.6F, 1.95F);
		//this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemInit.INFINITY_BLADE));

	}
	
	@Override
	public boolean isAIDisabled() {
		return false;
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
	@Override
	protected ResourceLocation getLootTable() {
		return LootTableHandler.CASTIEL;
	}
	*/
	
	@Override
	protected SoundEvent getAmbientSound() {
		return super.getAmbientSound();
	}

	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		return 120;
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CREEPER_DEATH;
	}
	
	@Override
    protected boolean canDespawn()
    {
        return false;
    }
	
}
