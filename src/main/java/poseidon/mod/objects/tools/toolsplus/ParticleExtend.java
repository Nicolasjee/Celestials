package poseidon.mod.objects.tools.toolsplus;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.entity.entities.EntityDemon;
import poseidon.mod.objects.tools.ToolSword;
import poseidon.mod.test.Effect;
import poseidon.mod.test.ExtendEffect;
import poseidon.mod.test.ParticleSnake;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.interfaces.IHasModel;

public class ParticleExtend extends ToolSword implements IHasModel {
	
	NBTTagCompound nbt;
	boolean active = false;
	Effect effect;
	
	public ParticleExtend(String name, ToolMaterial material, boolean e) {
		super(name, material, e);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this,  0, "inventory");
	}
		
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		if(active && this.effect != null) {
			
			
			
		}
		
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}
	



	@Override
	public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer p, EnumHand handIn) {
		ItemStack heldStack = p.getHeldItem(handIn);
		
		boolean ok = false;


			Effect effect = new Effect(p.posX, p.posY, p.posZ, w);
			
			
			//effect.start();
			ExtendEffect e = new ExtendEffect(p.posX, p.posY, p.posZ, w);
			effect.start();
		
		if(w.isRemote && ok) {
			
			ParticleSnake snake = new ParticleSnake(p.posX, p.posY, p.posZ, w, 10);
			snake.init();
		}

		return new ActionResult(EnumActionResult.SUCCESS, heldStack);
		
	}
	
	private void dna(EntityPlayer p, World w) {
		BlockPos pos = p.getPosition();
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ() + 5.0D;
		
		double yStep = 0.01D;
		double iStep = (2*PI)/360;
		int c = 0;
		for(double i = 0d; i < 2*PI; i += iStep) {
			
			  w.spawnParticle(EnumParticleTypes.END_ROD, true,
			                  cos(i)+x, 
			                  y +i,              
			                  sin(i)+z, 
			                  0d, 0d, 0d);
			  
			  w.spawnParticle(EnumParticleTypes.END_ROD, true,
	                  cos(i + PI)+x, 
	                  y +i,              
	                  sin(i + PI)+z, 
	                  0d, 0d, 0d);
			  
			  
				 if(c%20 == 0) {
					 
					 double dX = x+cos(i) - (x + cos(i+PI));
					 double dZ = z+sin(i) - (z + sin(i+PI));
					 int l = 100;
					 double xStep = dX / l;
					 double zStep = dZ / l;
					 
					 for(int n = 0; n < l; n++) {
						 
						 ParticleUtil.spawn(w, x, y, z);
						 w.spawnParticle(EnumParticleTypes.END_ROD, true, x+cos(i+PI)+xStep*n, y+i, z+sin(i+PI)+zStep*n, 0d, 0d, 0d);
						 
					 }
					 
				 }
				 
				 c++;
			  
			}

	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		//TODO if it has charge: more damage + abilities
		target.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100, 10));
		Random rn = new Random();
		int maximum = 100;
		int minimum = 1;
		int range = maximum - minimum + 1;		
		int randomNum2 = rn.nextInt(range) + minimum;
		
		if(randomNum2 < 60 && target instanceof EntityDemon) target.onKillCommand();
		if(randomNum2 < 5) target.onKillCommand();
		if(randomNum2 >= 5 && randomNum2 < 20) target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 400, 100));
		if(randomNum2 >= 20 && randomNum2 < 40) target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 400, 100));
		if(randomNum2 >= 40 && randomNum2 < 45) target.setFire(100);
		if(randomNum2 >= 45 && randomNum2 < 60) knockB(target, attacker);
		if(randomNum2 >= 50) return true;
		
		stack.damageItem(1, attacker);
		return true;
	}
	
	private void knockB(EntityLivingBase target, EntityLivingBase attacker) {
		
		Vec3d look = attacker.getLookVec().normalize();
		double knockback = 20.5;
		target.addVelocity(look.x * knockback, look.y * knockback * 10, look.z * knockback);
		
	}
	
	private void getNBT(ItemStack stack) {
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} //Gets The compound which holds keys!
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", 0);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)

		stack.setTagCompound(nbt);
	}
	
	
	
}
