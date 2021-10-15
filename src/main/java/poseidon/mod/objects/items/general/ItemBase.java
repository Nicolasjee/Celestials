package poseidon.mod.objects.items.general;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.general.tileHelpers.TileEntityBounce;
import poseidon.mod.util.interfaces.IHasModel;
import scala.util.Random;

public class ItemBase extends Item implements IHasModel {
		
	boolean effect;
	
	public ItemBase(String name, int size, boolean eff) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		setMaxStackSize(size);
		this.effect = eff;
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		if(!stack.hasTagCompound()) {
			getNBT(stack);
		}
		
		if(stack.hasTagCompound() && entityIn instanceof EntityPlayer) {
			
			NBTTagCompound nbt = stack.getTagCompound();
			EntityPlayer player = (EntityPlayer) entityIn;
			
			if(player.isAirBorne && !player.isElytraFlying()) {
				
				if(!nbt.getBoolean("isFalling")) nbt.setBoolean("isFalling", true);
				if(nbt.getBoolean("isFalling")) nbt.setInteger("fallingCount", nbt.getInteger("fallingCount") + 1);
				if(nbt.getInteger("fallingCount") == 40) {
					worldIn.setBlockState(player.getPosition().down(10), BlockInit.LAUNCHPAD.getDefaultState());
					if(worldIn.getTileEntity(player.getPosition().down(10)) instanceof TileEntityBounce) {
						TileEntityBounce b = (TileEntityBounce) worldIn.getTileEntity(player.getPosition().down(10));
						b.strength = 10.0F;
					}
					
				}
				
			}
			
			if(((player.isAirBorne && player.isElytraFlying()) || !player.isAirBorne) && nbt.getBoolean("isFalling")) {
				nbt.setBoolean("isFalling", false);
				nbt.setInteger("fallingCount", 0);
			}

			
		}
		
	}
	
	private void getNBT(ItemStack stack) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("fallingCount", 0);
		nbt.setBoolean("isFalling", false);
		stack.setTagCompound(nbt);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		double[] d = new double[] {playerIn.posX, playerIn.posY, playerIn.posZ};
		
		if(!worldIn.isRemote) {

			int[] lol = new int[] {};
			List<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < 64; i++) {
				Random rand = new Random();
				int range = 3+3+1;
				int r = rand.nextInt(range) -3;

				list.add(r);
				System.out.println(rand.nextInt(range));
			}
			
			
			
//				ParticleSnake snake = new ParticleSnake(d[0], d[1], d[2], worldIn, 5);
//				snake.init();
//				
		//	Effect.run(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ);
				
			
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return effect;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D(){ return true; }
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
