package poseidon.mod.objects.items.usable;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.handlers.registry.LootTableHandler;
import poseidon.mod.util.interfaces.IHasModel;

public class ItemKey extends Item implements IHasModel {

	public ItemKey(String s, int z, boolean b) {
		setUnlocalizedName(s);
		setRegistryName(s);
		setCreativeTab(Main.ARISTOISITEMS);
		ItemInit.ITEMS.add(this);
	}

	private void getNBT(ItemStack s) {
		if(!s.hasTagCompound()) {
			NBTTagCompound nbt = new NBTTagCompound();
			
			nbt.setIntArray("Coords", new int[] {0,0,0,0});
			
			s.setTagCompound(nbt);
		}
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound()) {
			getNBT(stack);
		}
		
		if(stack.hasTagCompound() && entityIn instanceof EntityPlayer) {
			int[] s = stack.getTagCompound().getIntArray("Coords");
			int x = s[0];
			int y = s[1];
			int z = s[2];
			int ac = s[3];
			
			EntityPlayer playerIn = (EntityPlayer) entityIn;
			
			if(ac == 1) {
				
				if(!worldIn.isRemote) ParticleUtil.obsidian((WorldServer)worldIn, new BlockPos((double)x, (double)y, (double)z));

				BlockPos pos = new BlockPos((double) x, (double) y, (double) z);
				worldIn.setBlockState(pos, Blocks.CHEST.getDefaultState());
				
				if(worldIn.getTileEntity(pos) instanceof TileEntityChest) {
					Random rand = new Random();
					
					TileEntityChest cs = (TileEntityChest) worldIn.getTileEntity(pos);
					//this works: cs.setLootTable(LootTableList.CHESTS_ABANDONED_MINESHAFT, rand.nextLong());
					cs.setLootTable(LootTableHandler.CHEST2, rand.nextLong());
				}
				
				stack.getTagCompound().setIntArray("Coords", new int[] {0,0,0,0});
			}
		}
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
