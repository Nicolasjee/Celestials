package poseidon.mod.entity.entities;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import poseidon.mod.util.ModProfessions;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class EntityCustomVillager extends EntityVillager {

	public EntityCustomVillager(World worldin) {
		super(worldin);
		this.setSize(0.6F, 1.95F);
		this.setProfession(9);
		this.setProfession(ModProfessions.reader);
		

	}

	
	@Override
	public boolean isAIDisabled() {
		return false;
	}
	

	
	

    
    
    

	
	@Override
	protected boolean canEquipItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return super.canEquipItem(stack);
	}



	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {

		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));

	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

	}

	@Override
	public float getEyeHeight() {
		return 1.0F;
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
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		// TODO Auto-generated method stub
		return livingdata;
	}


	@Override
	protected boolean canDespawn() {
		return false;
	}
}
