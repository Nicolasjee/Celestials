package poseidon.mod.objects.tools.toolsplus;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import poseidon.mod.objects.tools.ToolSword;

public class ToolButcher extends ToolSword {

	public ToolButcher(String name, ToolMaterial tool) {
		super(name, tool, false);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		// TODO Auto-generated method stub
		return super.hitEntity(stack, target, attacker);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
	}
	
}
