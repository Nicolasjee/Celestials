package poseidon.mod.objects.items.charms;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.tools.ToolHoe;
import poseidon.mod.util.ParticleUtil;

public class FallCharm extends ToolHoe {

	public FallCharm(String name, ToolMaterial tool) {
		super(name, tool);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		if(entityIn != null && entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;

			if(player.getHeldItemMainhand().getItem() == stack.getItem() || player.getHeldItemOffhand().getItem() == stack.getItem()) {
				
				ParticleUtil.glide((WorldServer) worldIn, player);
				
			}
		}
		
	}
	
}
