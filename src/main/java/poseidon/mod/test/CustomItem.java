package poseidon.mod.test;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.init.ItemInit;

public class CustomItem extends Item {

	public CustomItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer p, EnumHand h) {
		
		BlockPos pos = p.getPosition();
		double x = pos.getX() + 5.0D;
		double y = pos.getY();
		double z = pos.getZ();
		
		double m = 0.4D;
		double q = 3D;
		
		for(double i = 0; i < 5.0D; i += 0.1D) {
			
			for(m = -10d; m < 10d; m += 0.1D) {
				w.spawnParticle(EnumParticleTypes.END_ROD, x + i, y + i*m + q, z, 0d, 0d, 0d);
				w.spawnParticle(EnumParticleTypes.FLAME, x, y, z, i, m*i, 0d);
			}
		}
	
		
		return super.onItemRightClick(w, p, h);
	
	
	}
	
	private void cirkel(BlockPos pos, World world) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		for(double i = 0; i < 10.0D; i += 0.1D) {
			world.spawnParticle(EnumParticleTypes.END_ROD, Math.pow(i-x, 2), y, Math.pow(i-z,2), 0d, 0d, 0d);
		}
		
	}
	
}
