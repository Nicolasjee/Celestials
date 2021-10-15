package poseidon.mod.objects.tools.toolsplus;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import poseidon.mod.objects.tools.ToolSword;

public class UniversalSword extends ToolSword {
	
	Random rand = new Random();
	NBTTagCompound nbt;
	
	private boolean criticalHit = false;
	private boolean poisonHit = false;
	private boolean slownessHit = false;
	private boolean noSpecial = false;
	private boolean onFire = false;
	private boolean knockB = false;
	
	public UniversalSword(String name, ToolMaterial mat, boolean e) {
		super(name, mat,e );
	}
	
	
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		
		getRandom();
		
		Random rn = new Random();
		int maximum = 100;
		int minimum = 1;
		int range = maximum - minimum + 1;		
		int randomNum2 = rn.nextInt(range) + minimum;
		
		if(randomNum2 < 5) {
			criticalHit = true;
			target.onKillCommand();
		}
		if(randomNum2 >= 5 && randomNum2 < 20) {
			poisonHit = true;
			 target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 400, 100));
		}
		if(randomNum2 >= 20 && randomNum2 < 40) {
			slownessHit = true;
			target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 400, 100));
		}
		if(randomNum2 >= 40 && randomNum2 < 45) {
			onFire = true;
			target.setFire(100);
		}
		if(randomNum2 >= 45 && randomNum2 < 60) {
			knockB = true;
			knockB(target, attacker);
		}
		
		if(randomNum2 <= 50) {
			noSpecial = true;
		}
		
		
		return true;
	}
	
	private void knockB(EntityLivingBase target, EntityLivingBase attacker) {
		
		Vec3d look = attacker.getLookVec().normalize();
		double knockback = 20.5;
		target.addVelocity(look.x * knockback, look.y * knockback * 10, look.z * knockback);
		
	}
	
	private void getRandom() {
		Random rn = new Random();
		int maximum = 100;
		int minimum = 1;
		int range = maximum - minimum + 1;		
		int randomNum2 = rn.nextInt(range) + minimum;
		
		if(randomNum2 < 5) {
			criticalHit = true;
		}
		if(randomNum2 >= 5 && randomNum2 < 15) {
			poisonHit = true;
		}
		if(randomNum2 >= 15 && randomNum2 < 30) {
			slownessHit = true;
		}
		if(randomNum2 >= 30 && randomNum2 < 40) {
			onFire = true;
		}
		if(randomNum2 >= 40 && randomNum2 < 50) {
			knockB = true;
		}
		
		if(randomNum2 <= 50) {
			noSpecial = true;
		}
		
	}

	
	
	
}
