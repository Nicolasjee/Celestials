package poseidon.mod.objects.tools.toolsplus;

import java.util.List;
import java.util.Random;

import it.unimi.dsi.fastutil.Stack;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IHasModel;

public class MiningDrill extends ItemPickaxe implements IHasModel {
	
	double x1, x2, x3, x4, x5, x6, x7, x8, y1, y2, y3, y4, y5, y6, y7, y8, z1, z2, z3, z4, z5, z6, z7, z8;

	public MiningDrill(String name, ToolMaterial drill) {
		super(drill);
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setCreativeTab(Main.ARISTOISITEMS);
		ItemInit.ITEMS.add(this);
	}


	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand handIn, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		//ItemStack itemstack5 = new ItemStack(ItemInit.MINING_DRILL);

		System.out.println("facing: " + facing.getOpposite());
		Random rand = new Random();

		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		BlockPos poso = new BlockPos(x, y, z);
		
		if(!worldIn.isRemote) {
			//destroyBlocks(worldIn, x, y, z, playerIn, poso, itemstack, facing);
		}
		
		return EnumActionResult.SUCCESS;
		
	}
		
	
	private void destroyBlocks2(World worldIn, double x, double y, double z, EntityPlayer playerIn, BlockPos poso, ItemStack stack) {
		Random rand = new Random();
		RayTraceResult pos = playerIn.rayTrace(5,20);
		
		int x1 = pos.getBlockPos().getX();
		int y1 = pos.getBlockPos().getY();
		int z1 = pos.getBlockPos().getZ();
		
		int x2 = x1 - 1;
		int z2 = z1 - 1;
		int y2 = y1;
		
		int x3 = x1 - 1;
		int z3 = z1;
		int y3 = y1;
		
		int x4 = x1 - 1;
		int z4 = z1 + 1;
		int y4 = y1;
		
		int x5 = x1;
		int z5 = z1 + 1;
		int y5 = y1;
		
		int x6 = x1;
		int z6 = z1 - 1;
		int y6 = y1;
		
		int x7 = x1 + 1;
		int z7 = z1 - 1;
		int y7 = y1;
		
		int x8 = x1 + 1;
		int z8 = z1;
		int y8 = y1;
		
		int x9 = x1 + 1;
		int z9 = z1 + 1;
		int y9 = y1;
		
		BlockPos pos1 = new BlockPos(x1, y1, z1);
		BlockPos pos2 = new BlockPos(x2, y1, z2);
		BlockPos pos3 = new BlockPos(x3, y1, z3);
		BlockPos pos4 = new BlockPos(x4, y1, z4);
		BlockPos pos5 = new BlockPos(x5, y1, z5);
		BlockPos pos6 = new BlockPos(x6, y1, z6);
		BlockPos pos7 = new BlockPos(x7, y1, z7);
		BlockPos pos8 = new BlockPos(x8, y1, z8);
		BlockPos pos9 = new BlockPos(x9, y1, z9);
		int a = 1;
		BlockPos pos10 = new BlockPos(x1, y1 - a, z1);
		BlockPos pos11 = new BlockPos(x2, y1 - a, z2);
		BlockPos pos12 = new BlockPos(x3, y1 - a, z3);
		BlockPos pos13 = new BlockPos(x4, y1 - a, z4);
		BlockPos pos14 = new BlockPos(x5, y1 - a, z5);
		BlockPos pos15 = new BlockPos(x6, y1 - a, z6);
		BlockPos pos16 = new BlockPos(x7, y1 - a, z7);
		BlockPos pos17 = new BlockPos(x8, y1 - a, z8);
		BlockPos pos18 = new BlockPos(x9, y1 - a, z9);
		a = 2;
		BlockPos pos19 = new BlockPos(x1, y1 - a, z1);
		BlockPos pos20 = new BlockPos(x2, y1 - a, z2);
		BlockPos pos21 = new BlockPos(x3, y1 - a, z3);
		BlockPos pos22 = new BlockPos(x4, y1 - a, z4);
		BlockPos pos23 = new BlockPos(x5, y1 - a, z5);
		BlockPos pos24 = new BlockPos(x6, y1 - a, z6);
		BlockPos pos25 = new BlockPos(x7, y1 - a, z7);
		BlockPos pos26 = new BlockPos(x8, y1 - a, z8);
		BlockPos pos27 = new BlockPos(x9, y1 - a, z9);
		a = 3;
		BlockPos pos28 = new BlockPos(x1, y1 - a, z1);
		BlockPos pos29 = new BlockPos(x2, y1 - a, z2);
		BlockPos pos30 = new BlockPos(x3, y1 - a, z3);
		BlockPos pos31 = new BlockPos(x4, y1 - a, z4);
		BlockPos pos32 = new BlockPos(x5, y1 - a, z5);
		BlockPos pos33 = new BlockPos(x6, y1 - a, z6);
		BlockPos pos34 = new BlockPos(x7, y1 - a, z7);
		BlockPos pos35 = new BlockPos(x8, y1 - a, z8);
		BlockPos pos36 = new BlockPos(x9, y1 - a, z9);
		a = 4;
		BlockPos pos37 = new BlockPos(x1, y1 - a, z1);
		BlockPos pos38 = new BlockPos(x2, y1 - a, z2);
		BlockPos pos39 = new BlockPos(x3, y1 - a, z3);
		BlockPos pos40 = new BlockPos(x4, y1 - a, z4);
		BlockPos pos41 = new BlockPos(x5, y1 - a, z5);
		BlockPos pos42 = new BlockPos(x6, y1 - a, z6);
		BlockPos pos43 = new BlockPos(x7, y1 - a, z7);
		BlockPos pos44 = new BlockPos(x8, y1 - a, z8);
		BlockPos pos45 = new BlockPos(x9, y1 - a, z9);
		a = 5;
		BlockPos pos46 = new BlockPos(x1, y1 - a, z1);
		BlockPos pos47 = new BlockPos(x2, y1 - a, z2);
		BlockPos pos48 = new BlockPos(x3, y1 - a, z3);
		BlockPos pos49 = new BlockPos(x4, y1 - a, z4);
		BlockPos pos50 = new BlockPos(x5, y1 - a, z5);
		BlockPos pos51 = new BlockPos(x6, y1 - a, z6);
		BlockPos pos52 = new BlockPos(x7, y1 - a, z7);
		BlockPos pos53 = new BlockPos(x8, y1 - a, z8);
		BlockPos pos54 = new BlockPos(x9, y1 - a, z9);
		a = 6;
		BlockPos pos55 = new BlockPos(x1, y1 - a, z1);
		BlockPos pos56 = new BlockPos(x2, y1 - a, z2);
		BlockPos pos57 = new BlockPos(x3, y1 - a, z3);
		BlockPos pos58 = new BlockPos(x4, y1 - a, z4);
		BlockPos pos59 = new BlockPos(x5, y1 - a, z5);
		BlockPos pos60 = new BlockPos(x6, y1 - a, z6);
		BlockPos pos61 = new BlockPos(x7, y1 - a, z7);
		BlockPos pos62 = new BlockPos(x8, y1 - a, z8);
		BlockPos pos63 = new BlockPos(x9, y1 - a, z9);
		
		
		BlockPos[] blockPosArray = {pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9, pos10, pos11, pos12, pos13, pos14, pos15, pos16, pos17, pos18, pos19, pos20,
									pos21, pos22, pos23, pos24, pos25, pos26, pos27, pos28, pos29, pos30, pos31, pos32, pos33, pos34, pos35, pos36, pos37, pos38, pos39,
									pos40, pos41, pos42, pos43, pos44, pos45, pos46, pos47, pos48, pos49, pos50, pos51, pos52, pos53, pos54, pos55, pos56, pos57, pos58,
									pos59, pos60, pos61, pos62, pos63};
		int i;
		for(i = 0; i < 63; i++) {
			worldIn.destroyBlock(blockPosArray[i], true);
		}
		stack.damageItem(1, playerIn);
		int dura = stack.getTagCompound().getInteger("Durability");
		int durabil = dura - 1;
		if(durabil == 0) {
			stack.getTagCompound().setInteger("Durability", 0);
		}
		stack.getTagCompound().setInteger("Durability", durabil);
	}
	
	
	private void destroyBlocks(World worldIn, double x, double y, double z, EntityPlayer playerIn, BlockPos poso, ItemStack stack, EnumFacing facing, boolean hasLooting) {
		
		if(facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
			
			BlockPos[] pos = new BlockPos[] { new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x - 1.0D, y, z - 1.0D), new BlockPos(x + 1.0D, y, z + 1.0D),
			                                  new BlockPos(x, y, z + 1.0D), new BlockPos(x, y, z - 1.0D), new BlockPos(x + 1.0D, y, z), new BlockPos(x + 1.0D, y, z)};
			
			for(int i = 0; i < pos.length; i++) {
				worldIn.destroyBlock(pos[i], true);
				if(hasLooting) {
					
				}
			}
	
			
			} else if(facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
				
				BlockPos[] pos = new BlockPos[] { new BlockPos(x, y + 1.0D, z + 1.0D), new BlockPos(x, y, z + 1.0D), new BlockPos(x, y - 1.0D, z + 1.0D), new BlockPos(x, y + 1.0D, z), new BlockPos(x, y - 1.0D, z),
											      new BlockPos(x, y - 1.0D, z - 1.0D), new BlockPos(x, y, z - 1.0D), new BlockPos(x, y + 1.0D, z - 1.0D)};
				
				for(int i = 0; i < pos.length; i++) worldIn.destroyBlock(pos[i], true);

			
			} else if(facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			
			
				BlockPos[] pos = new BlockPos[] { new BlockPos(x + 1.0D, y + 1.0D, z), new BlockPos(x + 1.0D, y, z), new BlockPos(x + 1.0D, y - 1.0D, z), new BlockPos(x, y + 1.0D, z), new BlockPos(x, y - 1.0D, z),
						                                   new BlockPos(x - 1.0D, y - 1.0D, z), new BlockPos(x - 1.0D, y, z), new BlockPos(x - 1.0D, y + 1.0D, z)};
				
				for(int i = 0; i < pos.length; i++) worldIn.destroyBlock(pos[i], true);
				
		}
		
		
	}
		
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this,  0, "inventory");
	}
}
























