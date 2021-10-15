package poseidon.mod.objects.block.energyshield;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import poseidon.mod.objects.block.general.BlockBase;

public class BlockEnergyShield extends BlockBase implements ITileEntityProvider {

	public BlockEnergyShield(String name) {
		super(name, Material.ROCK, 0.0F, 10.0F, 10.0F, 2);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityEnergyShield();
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {

		Item needed = Items.STICK;
		
		if(entityIn instanceof EntityPlayer && worldIn.getTileEntity(pos) instanceof TileEntityEnergyShield && ((EntityPlayer)entityIn).getHeldItemMainhand().getItem() == needed) {
			
			TileEntityEnergyShield shield = (TileEntityEnergyShield) worldIn.getTileEntity(pos);
			EntityPlayer playerIn = (EntityPlayer) entityIn;
			RayTraceResult ray = playerIn.rayTrace(100,0);
			
			BlockPos hit = ray.getBlockPos();
			
			if(worldIn.getTileEntity(hit) != null && worldIn.getTileEntity(hit) instanceof TileEntityEnergyShield) {
				
				TileEntityEnergyShield hitShield = (TileEntityEnergyShield) worldIn.getTileEntity(hit);
				
				List<Integer> copy = getCoordsCopy(shield);
				
				if(noDuplicates(copy, hit)) {	
					int index = getNewIndex(shield);
					setNewIndexCoords(shield, hit, index);
				}
					
			}
		}
	}
	
	private boolean noDuplicates(List<Integer> list, BlockPos pos) {

		int x1 = list.get(0); int x2 = list.get(3); int x3 = list.get(6);
		int y1 = list.get(1); int y2 = list.get(4); int y3 = list.get(7);
		int z1 = list.get(2); int z2 = list.get(5); int z3 = list.get(8);
		
		int x = pos.getX(); int y = pos.getY(); int z = pos.getZ();
		
		if(x1 == x && y1 == y && z1 == z) return false;
		if(x2 == x && y2 == y && z2 == z) return false;
		if(x3 == x && y3 == y && z3 == z) return false;
		else return true;
		
	}
	
	private List<Integer> getCoordsCopy(TileEntityEnergyShield e) {
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < e.coords.length; i++) {
			list.add(e.coords[i]);
		}
		
		return list;
	}
	
	private void setNewIndexCoords(TileEntityEnergyShield e, BlockPos pos, int index) {
		
		int x = pos.getX(); int y = pos.getY(); int z = pos.getZ();
		
		int[] oA = getOriginal(e);
		int[] o = getOriginalOccupied(e);
		int[] m = getOriginalEmitter(e);
		
		if(index == 1) {
			
			e.coords = new int[] {x, y, z, oA[3], oA[4], oA[5], oA[6], oA[7], oA[8]};
			e.occupied = new int[] {1, o[1], o[2]};
			e.emitter = new int[] {1, m[1], m[2]};
			
		}
		
		if(index == 2) {
			
			e.coords = new int[] {oA[0], oA[1], oA[2], x, y, z, oA[6], oA[7], oA[8]};
			e.occupied = new int[] {o[0], 1, o[2]};
			e.emitter = new int[] {m[0], 1, m[2]};
			
		}
		
		if(index == 3) {
			
			e.coords = new int[] {oA[0], oA[1], oA[2], oA[3], oA[4], oA[5],  x, y, z};
			e.occupied = new int[] {o[0], o[1], 1};
			e.emitter = new int[] {m[0], m[1], 1};
			
		}
		
	}
	
	private int[] getOriginalEmitter(TileEntityEnergyShield b) {
		int[] s = b.emitter;
		return new int[] {s[0], s[1], s[2]};
	}
	
	private int[] getOriginalOccupied(TileEntityEnergyShield b) {
		int[] s = b.occupied;
		return new int[] {s[0], s[1], s[2]};
	}
	
	private int[] getOriginal(TileEntityEnergyShield b) {
		
		int[] s = b.coords;
		return new int[] {s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8]};
		
	}
	
	private int getNewIndex(TileEntityEnergyShield s) {
		
		int[] occupied = s.occupied;
		
		if(occupied[0] == 0) return 1;
		if(occupied[1] == 0) return 2;
		if(occupied[2] == 0) return 3;
		if(occupied[2] == 1 && occupied[0] == 1) return 1;
		return 1;
		
	}
	
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEnergyShield();
	}
	
}
