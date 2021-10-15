package poseidon.mod.objects.block.netherreactor.nethercore.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityItemAir extends TileEntity implements ITickable {
	
	public int location = 0;
	public Item i1;
	public Item i2;
	public Item i3;
	public Item i4;
	
	public List<Entity> collided = new ArrayList<Entity>();

	public int function = 0;
	
	public boolean found1 = false;
	public boolean found2 = false;
	public boolean found3 = false;
	public boolean found4 = false;
	
	public boolean remove = false;
	
	BlockPos[] neighbours = new BlockPos[] {pos.south(), pos.north(), pos.east(), pos.west()};
	
	int tick = 0;
	
	@Override
	public void update() {
		
		if(function == 1) {
		
			double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
			
			List<Entity> list = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(x, y - 1.0D, z, x + 1.0D, y + 2.0D, z + 1.0D));
			List<Item> item = new ArrayList<Item>();
			
			//Getting an item list from all the entityItems in LIST
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i) instanceof EntityItem) {
					item.add(((EntityItem)list.get(i)).getItem().getItem());
				}	
			}
		
			//Removing variables that were set to true in a previous list.
			if(list.size() == item.size()) {
				for(int i = 0; i < list.size(); i++) {
					
					if(found1 && !item.contains(i1)) found1 = false;
					if(found2 && !item.contains(i2)) found2 = false;
					if(found3 && !item.contains(i3)) found3 = false;
					if(found4 && !item.contains(i4)) found4 = false;
					
				}
			}
			
			for(int i = 0; i < list.size(); i++) {
				
				if(list.get(i) instanceof EntityItem) {
					
					EntityItem eItem = (EntityItem) list.get(i);
					if(eItem.getItem().getItem() == i1) found1 = true;
					if(eItem.getItem().getItem() == i2) found2 = true;
					if(eItem.getItem().getItem() == i3) found3 = true;
					if(eItem.getItem().getItem() == i4) found4 = true;
					
				}
				
			}
			
			if(remove) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setDead();
					remove = false;
				}
			}
			
		}
		
		if(function == 2) {
			
			double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
			
			if(tick % 15 == 0) {
				for(int i = 0; i < neighbours.length; i++) {
					
					if(world.getBlockState(neighbours[i]) == Blocks.AIR.getDefaultState()) {
						
						world.setBlockState(neighbours[i], Blocks.QUARTZ_ORE.getDefaultState());
						
					}
					
				}
			}
			
			tick++;
			
		}
		
		//Light placer function
		if(function == 3) {
			
			return;
			
		}
	
		if(function == 0) this.world.destroyBlock(this.pos, false);
		
	}
	
	
	

}
