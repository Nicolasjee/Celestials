package poseidon.mod.objects.items.general.test;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import poseidon.mod.Main;
import poseidon.mod.objects.items.general.ItemBase;

public class PushStaff extends ItemBase {
	
	public PushStaff(String name, boolean e) {
		super(name, 1,e);
		setCreativeTab(Main.ARISTOISITEMS);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase wielder) {
		Vec3d look2 = wielder.getLookVec().normalize();
		Vec3d look3 = target.getLookVec().normalize();
		Vec3d look = look3.rotateYaw(+180.0F);

		
		double knockback = 10.5;
		target.addVelocity(look.x * knockback, look.y * knockback * 10, look.z * knockback);
		return true;
	}
	
	

}
