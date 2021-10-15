package poseidon.mod.world.gen.generators;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.storage.loot.LootTableList;
import poseidon.mod.util.interfaces.IStructure;

public class GenerateLoot implements IStructure {

	
	static int r = 0;
	
	public static void setLoot(World world, BlockPos pos, Template template) {
		
		PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null).setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
		Random rand = new Random();
		Random r2 = new Random();
		Map<BlockPos, String> map = template.getDataBlocks(pos, settings);

		for(Entry<BlockPos, String> entry : map.entrySet()) {
			System.out.println("reached something: " + ("chest".equals(entry.getValue())));
			if ("chest".equals(entry.getValue())) {
                BlockPos blockpos2 = entry.getKey();
                System.out.println("reached");
                world.setBlockState(blockpos2.up(), Blocks.AIR.getDefaultState(), 3);
                TileEntity tileentity = world.getTileEntity(blockpos2);

                if (tileentity instanceof TileEntityChest) {
                    ((TileEntityChest)tileentity).setLootTable(LootTableList.ENTITIES_WITCH, rand.nextLong());
                }
            }
			
            if ("br".equals(entry.getValue())) {
                BlockPos blockpos2 = entry.getKey();
                world.setBlockState(blockpos2.up(), Blocks.AIR.getDefaultState(), 3);
                TileEntity tileentity = world.getTileEntity(blockpos2);
                
                if (tileentity instanceof TileEntityBrewingStand) {
               	
               	 int i  =1+r2.nextInt(3);
               	 
               	 for (int j=0;j<i;j++) {
               		 
               		
               		 r = r2.nextInt(9);
               		
               		 ItemStack pot=null;
               	 switch(r) {
               	 case 0:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_HEALING);}
               	 break;
               	 
               	 case 1:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_FIRE_RESISTANCE);}
               	 break;
               	 
               	 case 2:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_LEAPING);}
               	 break;
               	 
               	 case 3:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_HARMING);}
               	 break;
               	 
               	 
               	 case 4:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_STRENGTH);}
               	 break;
               	 
               	 case 5:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_SWIFTNESS);};
               	 
               	 case 6:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_WATER_BREATHING);}
               	 break;
               	 
               	 case 7:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_REGENERATION);}
               	 break;
               	 
               	 case 8:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_POISON);}
               	 break;
               	 
               	 case 9:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_NIGHT_VISION);}
               	 break;
               	 
               	 
               	 
            
               	 }
               	 
               	 ((TileEntityBrewingStand)tileentity).setInventorySlotContents(j,pot);
               	 }
                }
               	 
                    
                }
            }
		
		
	}
	
}
