package poseidon.mod.objects.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.helpers.tracehelper.TraceSpawner;
import poseidon.mod.util.interfaces.IHasModel;

public class ToolSword extends ItemSword implements IHasModel {
	
	public boolean ef;
	
	public ToolSword(String name, ToolMaterial Material, boolean e) {
		super(Material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		this.ef = e;
		ItemInit.ITEMS.add(this);
		setCreativeTab(Main.ARISTOISITEMS);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		TraceSpawner.execute(playerIn, worldIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this,  0, "inventory");
	}


	
	
}
