package poseidon.mod.world.gen;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import poseidon.mod.util.interfaces.IStructure;

public class SubWorldGen extends WorldGenerator implements IStructure {
	 Random r2 =new Random();
		
	  
	    int r;
		@Override
		public boolean generate(World world, Random rand, BlockPos position) {

			WorldServer worldserver = (WorldServer) world;
			MinecraftServer minecraftserver = world.getMinecraftServer();
			TemplateManager templatemanager = worldServer.getStructureTemplateManager();
			//Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MODID, "lucifer_house"));
			
			double x = position.getX(); double y = position.getY(); double z = position.getZ();
			BlockPos pos = new BlockPos(x + 2.0D, y + 1.0D, z + 2.0D);
			world.setBlockState(pos, Blocks.CHEST.getDefaultState());
			TileEntity tileentity = world.getTileEntity(pos);
			((TileEntityChest)tileentity).setLootTable(LootTableList.CHESTS_END_CITY_TREASURE, rand.nextLong());
			
			EntityZombie z1 = new EntityZombie(world);
			z1.setLocationAndAngles(x, y, z, z1.rotationYaw, z1.rotationPitch);
			world.spawnEntity(z1);
			((EntityLiving)z1).onInitialSpawn(world.getDifficultyForLocation(position), (IEntityLivingData)null);
			
			return true;
			
		}
		
		
		public void addLoot(World world) {
			
			
			
			
			
		}
}
