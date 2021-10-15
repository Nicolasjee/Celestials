package poseidon.mod.objects.block.netherreactor.nethercore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.objects.block.general.chest.obsidian.TileEntityObsidianChest;
import poseidon.mod.objects.block.netherreactor.ReactorSpawns;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class NetherReactor extends BlockBase {

	public NetherReactor(String name, Material material, float light, float hard, float res, int harvest) {
		super(name, material, light, hard, res, harvest);
	}

	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                
            	if (i > -2 && i < 2 && j == -1) {
                    j = 2;
                }

                if (rand.nextInt(16) == 0) {
                    for (int k = 0; k <= 1; ++k) {
                        BlockPos blockpos = pos.add(i, k, j);



                       worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double)pos.getX() + 0.5D, (double)pos.getY() + 2.0D, (double)pos.getZ() + 0.5D, (double)((float)i + rand.nextFloat()) - 0.5D, (double)((float)k - rand.nextFloat() - 1.0F), (double)((float)j + rand.nextFloat()) - 0.5D);
                        
                    }
                }
            }
        }
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack held = playerIn.getHeldItem(hand);
		
//		if(!worldIn.isRemote && held.getItem() != ItemInit.SUMMONING_KEY_NETHER) {
//			playerIn.openGui(Main.instance, Reference.GUI_NETHER, worldIn, pos.getX(), pos.getY(), pos.getZ());
//			return true;
//		}
		
		if(playerIn.isSneaking()) {
			setStructure(worldIn, pos);
			return true;
		}
		
		int mistakes = 0;


		
		List<BlockPos> underLayer = new ArrayList<BlockPos>();
		List<BlockPos> underLayer2 = new ArrayList<BlockPos>();
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();

		underLayer.add(new BlockPos(x, y - 1.0D, z));
		underLayer.add(new BlockPos(x + 1.0D, y - 1.0D, z));
		underLayer2.add(new BlockPos(x + 1.0D, y - 1.0D, z - 1.0D));
		underLayer2.add(new BlockPos(x + 1.0D, y - 1.0D, z + 1.0D));
		underLayer.add(new BlockPos(x, y - 1.0D, z + 1.0D));
		underLayer.add(new BlockPos(x, y - 1.0D, z - 1.0D));
		underLayer2.add(new BlockPos(x - 1.0D, y - 1.0D, z + 1.0D));
		underLayer2.add(new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D));
		underLayer.add(new BlockPos(x - 1.0D, y - 1.0D, z));

		for (int i = 0; i < underLayer.size(); i++) {
			IBlockState iBlock = worldIn.getBlockState(underLayer.get(i));
			Block block = iBlock.getBlock();

			if (!(block.getDefaultState() == Blocks.COBBLESTONE.getDefaultState())) {
				mistakes++;

			}
		}

		for (int k = 0; k < underLayer2.size(); k++) {
			IBlockState iBlock = worldIn.getBlockState(underLayer2.get(k));
			Block block = iBlock.getBlock();

			if (!(block.getDefaultState() == BlockInit.NETHER_STONE.getDefaultState())) {
				mistakes++;
			}
		}

		List<BlockPos> layer = new ArrayList<BlockPos>();
		layer.add(new BlockPos(x + 1.0D, y, z - 1.0D));
		layer.add(new BlockPos(x + 1.0D, y, z + 1.0D));
		layer.add(new BlockPos(x - 1.0D, y, z - 1.0D));
		layer.add(new BlockPos(x - 1.0D, y, z + 1.0D));

		layer.add(new BlockPos(x + 1.0D, y + 1.0D, z - 1.0D));
		layer.add(new BlockPos(x + 1.0D, y + 1.0D, z + 1.0D));
		layer.add(new BlockPos(x - 1.0D, y + 1.0D, z + 1.0D));
		layer.add(new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D));

		for (int j = 0; j < layer.size(); j++) {
			IBlockState iBlock = worldIn.getBlockState(layer.get(j));
			Block block = iBlock.getBlock();

			if (!(block.getDefaultState() == BlockInit.NETHER_STONE.getDefaultState())) {
				mistakes++;
			}
		}

		List<BlockPos> topLayer = new ArrayList<BlockPos>();

		topLayer.add(new BlockPos(x, y + 1.0D, z));
		topLayer.add(new BlockPos(x + 1.0D, y + 1.0D, z));
		topLayer.add(new BlockPos(x, y + 1.0D, z + 1.0D));
		topLayer.add(new BlockPos(x, y + 1.0D, z - 1.0D));
		topLayer.add(new BlockPos(x - 1.0D, y + 1.0D, z));

		for (int l = 0; l < topLayer.size(); l++) {
			IBlockState iBlock = worldIn.getBlockState(topLayer.get(l));
			Block block = iBlock.getBlock();

			if (!(block.getDefaultState() == Blocks.COBBLESTONE.getDefaultState())) {
				mistakes++;
			}
		}

		if (mistakes != 0) {
			if(!worldIn.isRemote) playerIn.sendMessage(new TextComponentString("Wrong shape!"));
		}

		if (mistakes == 0 && !worldIn.isRemote) {
			int openSpaces = checkforOpenSpace(worldIn, pos, playerIn);

			if (openSpaces == 0) {

				int groundSpaces = checkForGround(worldIn, pos, playerIn);

				if (groundSpaces == 0) {
					//placeBlocks(worldIn, pos);

					//Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY() + 2.0D, pos.getZ(), false);
					//worldIn.addWeatherEffect(bolt);

					//transform(worldIn, pos);
					//placeLoot(worldIn, pos);
					
					//Main.proxy.playSoundBlock(pos, worldIn, SoundsHandler.NETHERREACTOR, 1.0F, 1.5F);
					worldIn.setBlockState(pos.down(), BlockInit.NETHER_SPAWNER.getDefaultState());
					worldIn.setBlockState(pos.down(2), BlockInit.NETHER_PARTICLE_HANDLER.getDefaultState());
					worldIn.setBlockState(pos.down(3), BlockInit.NETHER_CORRUPTION_HANDLER.getDefaultState());
					worldIn.setBlockState(pos, BlockInit.NETHER_REACTOR_CORE_ACTIVATED.getDefaultState());
				}

				if (groundSpaces != 0)
					playerIn.sendMessage(new TextComponentString("There must be a 10x10 area around the reactor"));
			}
		}

		return true;
	}

	private int checkForGround(World worldIn, BlockPos pos, EntityPlayer player) {
		double x = pos.getX() + 10.0D;
		double y = pos.getY() - 2.0D;
		double z = pos.getZ() + 10.0D;
		int openSpace = 0;

		List<BlockPos> space = new ArrayList<BlockPos>();

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				space.add(new BlockPos(x - (double) i, y, z - (double) j));
			}
		}

		List<BlockPos> list = removeBlocks(space, pos);

		for (int i = 0; i < list.size(); i++) {
			BlockPos check = space.get(i);
			IBlockState state = worldIn.getBlockState(check);
			Block block = state.getBlock();

			if (block.getDefaultState() == Blocks.AIR.getDefaultState()) {
				worldIn.setBlockState(check, Blocks.NETHERRACK.getDefaultState());
			}
		}

		return openSpace;
	}

	private int checkforOpenSpace(World worldIn, BlockPos pos, EntityPlayer player) {
		double x = pos.getX() + 10.0D;
		double y = pos.getY() - 1.0D;
		double z = pos.getZ() + 10.0D;
		int openSpace = 0;

		List<BlockPos> space = new ArrayList<BlockPos>();
		List<BlockPos> space2 = new ArrayList<BlockPos>();

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				space.add(new BlockPos(x - (double) i, y, z - (double) j));
				space2.add(new BlockPos(x - (double) i, y + 1.0D, z - (double) j));
			}
		}

		List<BlockPos> list = removeBlocks(space, pos);
		List<BlockPos> list2 = removeBlocks(space2, pos);

		for (int i = 0; i < list.size(); i++) {
			BlockPos check = space.get(i);
			IBlockState state = worldIn.getBlockState(check);
			Block block = state.getBlock();

			if (!(block.getDefaultState() == Blocks.AIR.getDefaultState())) {

				worldIn.setBlockState(check, Blocks.AIR.getDefaultState());

			}
		}

		for (int i = 0; i < list2.size(); i++) {
			BlockPos check2 = space2.get(i);
			IBlockState state2 = worldIn.getBlockState(check2);
			Block block2 = state2.getBlock();

			if (!(block2.getDefaultState() == Blocks.AIR.getDefaultState())) {

				worldIn.setBlockState(check2, Blocks.AIR.getDefaultState());

			}
		}

		return openSpace;

	}

	private void transform(World worldIn, BlockPos pos) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();

		BlockPos[] positionsAir = new BlockPos[] { new BlockPos(x + 1.0D, y, z), new BlockPos(x, y, z + 1.0D),
				new BlockPos(x - 1.0D, y, z), new BlockPos(x, y, z - 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z),
				new BlockPos(x, y - 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z),
				new BlockPos(x, y - 1.0D, z - 1.0D) };
		BlockPos[] positionsNether = new BlockPos[] { new BlockPos(x, y + 1.0D, z), new BlockPos(x + 1.0D, y + 1.0D, z),
				new BlockPos(x, y + 1.0D, z + 1.0D), new BlockPos(x, y + 1.0D, z - 1.0D),
				new BlockPos(x - 1.0D, y + 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D),
				new BlockPos(x + 1.0D, y + 1.0D, z + 1.0D), new BlockPos(x + 1.0D, y + 1.0D, z - 1.0D),
				new BlockPos(x - 1.0D, y + 1.0D, z), new BlockPos(x + 1.0D, y, z + 1.0D),
				new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x - 1.0D, y, z + 1.0D),
				new BlockPos(x - 1.0D, y, z - 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z + 1.0D),
				new BlockPos(x + 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z + 1.0D),
				new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D) };

		for (int i = 0; i < positionsAir.length; i++) {

			BlockPos air = positionsAir[i];
			worldIn.setBlockState(air, Blocks.AIR.getDefaultState());

		}

		for (int i = 0; i < positionsNether.length; i++) {

			BlockPos brick = positionsNether[i];
			worldIn.setBlockState(brick, Blocks.NETHER_BRICK.getDefaultState());

		}

	}

	private List<BlockPos> removeBlocks(List<BlockPos> list, BlockPos pos) {

		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();

		list.remove(new BlockPos(x, y - 1.0D, z));
		list.remove(new BlockPos(x + 1.0D, y - 1.0D, z));
		list.remove(new BlockPos(x + 1.0D, y - 1.0D, z - 1.0D));
		list.remove(new BlockPos(x + 1.0D, y - 1.0D, z + 1.0D));
		list.remove(new BlockPos(x, y - 1.0D, z + 1.0D));
		list.remove(new BlockPos(x, y - 1.0D, z - 1.0D));
		list.remove(new BlockPos(x - 1.0D, y - 1.0D, z + 1.0D));
		list.remove(new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D));
		list.remove(new BlockPos(x - 1.0D, y - 1.0D, z));
		list.remove(new BlockPos(x + 1.0D, y, z - 1.0D));
		list.remove(new BlockPos(x + 1.0D, y, z + 1.0D));
		list.remove(new BlockPos(x - 1.0D, y, z - 1.0D));
		list.remove(new BlockPos(x - 1.0D, y, z + 1.0D));
		list.remove(new BlockPos(x, y + 1.0D, z));
		list.remove(new BlockPos(x + 1.0D, y + 1.0D, z));
		list.remove(new BlockPos(x + 1.0D, y + 1.0D, z - 1.0D));
		list.remove(new BlockPos(x + 1.0D, y + 1.0D, z + 1.0D));
		list.remove(new BlockPos(x, y + 1.0D, z + 1.0D));
		list.remove(new BlockPos(x, y + 1.0D, z - 1.0D));
		list.remove(new BlockPos(x - 1.0D, y + 1.0D, z + 1.0D));
		list.remove(new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D));
		list.remove(new BlockPos(x - 1.0D, y + 1.0D, z));

		return list;
	}

	private void placeLoot(World worldIn, BlockPos pos) {
		double x = pos.getX();
		double y = pos.getY() - 2.0D;
		double z = pos.getZ();

		BlockPos[] netherrack = new BlockPos[] { new BlockPos(x, y + 1.0D, z - 5.0D),
				new BlockPos(x - 2.0D, y, z - 6.0D), new BlockPos(x - 2.0D, y, z - 7.0D),
				new BlockPos(x - 1.0D, y, z - 7.0D), new BlockPos(x, y + 1.0D, z - 7.0D),
				new BlockPos(x + 1.0D, y + 1.0D, z - 7.0D), new BlockPos(x + 7.0D, y + 1.0D, z - 1.0D),
				new BlockPos(x + 8.0D, y + 1.0D, z - 1.0D), new BlockPos(x + 6.0D, y + 1.0D, z + 2.0D),
				new BlockPos(x + 7.0D, y + 1.0D, z + 3.0D), new BlockPos(x + 8.0D, y + 1.0D, z + 2.0D),
				new BlockPos(x + 1.0D, y, z + 7.0D), new BlockPos(x + 2.0D, y, z + 7.0D),
				new BlockPos(x, y + 1.0D, z + 9.0D), new BlockPos(x - 6.0D, y + 1.0D, z + 2.0D),
				new BlockPos(x - 4.0D, y + 1.0D, z + 6.0D), new BlockPos(x - 4.0D, y + 1.0D, z - 4.0D),
				new BlockPos(x - 5.0D, y + 1.0D, z - 4.0D), new BlockPos(x - 7.0D, y + 1.0D, z + 1.0D),
				new BlockPos(x - 8.0D, y + 1.0D, z), new BlockPos(x - 7.0D, y + 1.0D, z - 1.0D),
				new BlockPos(x - 8.0D, y, z - 1.0D), new BlockPos(x - 8.0D, y + 1.0D, z + 1.0D) };

		BlockPos[] netherbrick = new BlockPos[] { new BlockPos(x + 7.0D, y + 1.0D, z + 2.0D),
				new BlockPos(x + 6.0D, y, z + 3.0D), new BlockPos(x + 2.0D, y, z + 6.0D),
				new BlockPos(x + 3.0D, y + 1.0D, z + 6.0D), new BlockPos(x, y + 1.0D, z + 8.0D),
				new BlockPos(x + 1.0D, y, z + 8.0D), new BlockPos(x + 2.0D, y + 1.0D, z + 9.0D),
				new BlockPos(x + 7.0D, y, z), new BlockPos(x + 6.0D, y, z + 1.0D) };

		BlockPos[] soulsand = new BlockPos[] { new BlockPos(x, y, z - 6.0D), new BlockPos(x + 1.0D, y, z - 6.0D),
				new BlockPos(x + 1.0D, y, z - 5.0D), new BlockPos(x + 2.0D, y, z - 5.0D), new BlockPos(x + 6.0D, y, z),
				new BlockPos(x + 6.0D, y, z + 1.0D), new BlockPos(x + 7.0D, y, z + 1.0D),
				new BlockPos(x + 2.0D, y, z + 7.0D), new BlockPos(x + 3.0D, y, z + 6.0D), new BlockPos(x - 6.0D, y, z),
				new BlockPos(x - 6.0D, y, z - 1.0D), new BlockPos(x - 7.0D, y, z + 2.0D) };

		BlockPos[] crates_armor = new BlockPos[] { new BlockPos(x, y + 2.0D, z - 7.0D),
				new BlockPos(x + 5.0D, y + 1.0D, z - 5.0D), new BlockPos(x + 3.0D, y + 2.0D, z + 6.0D),
				new BlockPos(x + 1.0D, y + 1.0D, z + 9.0D), new BlockPos(x - 5.0D, y + 1.0D, z + 6.0D),
				new BlockPos(x - 6.0D, y + 2.0D, z + 2.0D), new BlockPos(x - 1.0D, y + 1.0D, z - 6.0D),
				new BlockPos(x - 5.0D, y + 2.0D, z - 4.0D), new BlockPos(x - 5.0D, y + 1.0D, z - 5.0D),
				new BlockPos(x - 5.0D, y + 1.0D, z + 7.0D), new BlockPos(x + 4.0D, y + 1.0D, z + 7.0D),
				new BlockPos(x + 8.0D, y + 2.0D, z), new BlockPos(x, y + 2.0D, z - 5.0D),
				new BlockPos(x - 5.0D, y + 1.0D, z - 3.0D), new BlockPos(x + 5.0D, y + 2.0D, z + 2.0D),
				new BlockPos(x, y + 2.0D, z + 8.0D), new BlockPos(x + 3.0D, y + 1.0D, z + 8.0D),
				new BlockPos(x + 7.0D, y + 1.0D, z), new BlockPos(x + 4.0D, y + 1.0D, z - 5.0D),
				new BlockPos(x - 4.0D, y + 1.0D, z - 5.0D), new BlockPos(x + 2.0D, y + 2.0D, z + 9.0D),
				new BlockPos(x + 2.0D, y + 1.0D, z - 6.0D),
				new BlockPos(x + 8.0D, y + 1.0D, z + 1.0D), new BlockPos(x, y + 1.0D, z + 7.0D),
				new BlockPos(x - 6.0D, y + 1.0D, z + 6.0D), new BlockPos(x - 5.0D, y + 1.0D, z - 4.0D), new BlockPos(x - 8.0D, y + 2.0D, z - 1.0D),
				new BlockPos(x + 7.0D, y + 1.0D, z - 2.0D), new BlockPos(x - 1.0D, y + 1.0D, z + 8.0D),
				new BlockPos(x - 4.0D, y + 1.0D, z + 7.0D), new BlockPos(x - 7.0D, y + 1.0D, z - 2.0D),
				new BlockPos(x + 7.0D, y + 2.0D, z + 2.0D), new BlockPos(x + 3.0D, y + 1.0D, +8.0D),
				new BlockPos(x + 1.0D, y + 1.0D, z),
				new BlockPos(x - 1.0D, y + 1.0D, z), new BlockPos(x, y + 1.0D, z - 1.0D),
				new BlockPos(x, y + 1.0D, z + 1.0D) };

		double k = y + 1.0D;
		BlockPos[] chests = new BlockPos[] {new BlockPos(x + 3.0D, k, z),new BlockPos(x, k, z + 3.0D), new BlockPos(x - 3.0D, k, z), new BlockPos(x, k, z - 3.0D)};

		for (int i = 0; i < netherrack.length; i++) {
			BlockPos netherack = netherrack[i];
			worldIn.setBlockState(netherack, Blocks.NETHERRACK.getDefaultState());
		}

		for (int i = 0; i < netherbrick.length; i++) {
			BlockPos netherbrickk = netherbrick[i];
			worldIn.setBlockState(netherbrickk, Blocks.NETHER_BRICK.getDefaultState());
		}

		for (int i = 0; i < soulsand.length; i++) {
			BlockPos soul = soulsand[i];
			worldIn.setBlockState(soul, Blocks.SOUL_SAND.getDefaultState());
		}

		for (int i = 0; i < crates_armor.length; i++) {
			BlockPos armor = crates_armor[i];
			worldIn.setBlockState(armor, BlockInit.NETHER_CRATE.getDefaultState());
		}


		getRandomSpawnerLocations(x,y,z, pos, worldIn);
		worldIn.setBlockState(pos.down(), BlockInit.BLOCKDRAW.getDefaultState());

		
		Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_ENDERDRAGON_DEATH, 1.0F, 1.0F);
		spawnCreatures();
		
		
		
	}
	
	private void getRandomSpawnerLocations(double x, double y, double z, BlockPos pos, World world) {
		
		BlockPos[] array = new BlockPos[] {new BlockPos(x + 7.0D, y + 1.0D, z + 2.0D),
				new BlockPos(x + 6.0D, y, z + 3.0D), new BlockPos(x + 2.0D, y, z + 6.0D),
				new BlockPos(x + 3.0D, y + 1.0D, z + 6.0D), new BlockPos(x, y + 1.0D, z + 8.0D),
				new BlockPos(x + 1.0D, y, z + 8.0D), new BlockPos(x + 2.0D, y + 1.0D, z + 9.0D),
				new BlockPos(x + 7.0D, y, z), new BlockPos(x + 6.0D, y, z + 1.0D), new BlockPos(x, y + 1.0D, z - 5.0D),
				new BlockPos(x - 2.0D, y, z - 6.0D), new BlockPos(x - 2.0D, y, z - 7.0D),
				new BlockPos(x - 1.0D, y, z - 7.0D), new BlockPos(x, y + 1.0D, z - 7.0D),
				new BlockPos(x + 1.0D, y + 1.0D, z - 7.0D), new BlockPos(x + 7.0D, y + 1.0D, z - 1.0D),
				new BlockPos(x + 8.0D, y + 1.0D, z - 1.0D), new BlockPos(x + 6.0D, y + 1.0D, z + 2.0D),
				new BlockPos(x + 7.0D, y + 1.0D, z + 3.0D), new BlockPos(x + 8.0D, y + 1.0D, z + 2.0D),
				new BlockPos(x + 1.0D, y, z + 7.0D), new BlockPos(x + 2.0D, y, z + 7.0D),
				new BlockPos(x, y + 1.0D, z + 9.0D), new BlockPos(x - 6.0D, y + 1.0D, z + 2.0D),
				new BlockPos(x - 4.0D, y + 1.0D, z + 6.0D), new BlockPos(x - 4.0D, y + 1.0D, z - 4.0D),
				new BlockPos(x - 5.0D, y + 1.0D, z - 4.0D), new BlockPos(x - 7.0D, y + 1.0D, z + 1.0D),
				new BlockPos(x - 8.0D, y + 1.0D, z), new BlockPos(x - 7.0D, y + 1.0D, z - 1.0D),
				new BlockPos(x - 8.0D, y, z - 1.0D), new BlockPos(x - 8.0D, y + 1.0D, z + 1.0D)};
		
		
		List<BlockPos> list = new ArrayList<BlockPos>();
		List<BlockPos> quad1 = getQuadrant(1, array, pos);
		List<BlockPos> quad2 = getQuadrant(2, array, pos);
		List<BlockPos> quad3 = getQuadrant(3, array, pos);
		List<BlockPos> quad4 = getQuadrant(4, array, pos);
		
		for(int i = 0; i < 4; i++) {
			if(i == 0) {
				try{
					int random = getNum(quad1.size());
					list.add(quad1.get(random));
					} catch(Exception e) {
					System.out.println("Random is not applicable");
				}
			}
			if(i == 1) {
				try{
					int random = getNum(quad2.size());
					list.add(quad2.get(random));
					} catch(Exception e) {
					System.out.println("Random is not applicable");
				}
			}
			if(i == 2) {
				try{
					int random = getNum(quad3.size());
					list.add(quad3.get(random));
					} catch(Exception e) {
					System.out.println("Random is not applicable");
				}
			}
			if(i == 3) {
				try{
					int random = getNum(quad4.size());
					list.add(quad4.get(random));
					} catch(Exception e) {
					System.out.println("Random is not applicable");
				}
			}
		}
		
		for(int i = 0; i < list.size(); i++) {
			world.setBlockState(list.get(i), BlockInit.NETHER_MOB_SPAWNER.getDefaultState());
		}
		
	}
	
	private BlockPos getPos(int i, BlockPos[] array, BlockPos pos) {	
		
		List<BlockPos> quad1 = getQuadrant(1, array, pos);
		List<BlockPos> quad2 = getQuadrant(2, array, pos);
		List<BlockPos> quad3 = getQuadrant(3, array, pos);
		List<BlockPos> quad4 = getQuadrant(4, array, pos);
		
		if(i == 0) {
			
			int random = getNum(quad1.size());
			try {
				return quad1.get(random);
				} catch(Exception e) {
				System.out.println("Not possible");
			}
			
		}
		
		if(i == 1) {
			
			int random = getNum(quad2.size());
			try {
				return quad2.get(random);
				} catch(Exception e) {
				System.out.println("Not possible");
			}
			
		}
		
		if(i == 2) {
			
			int random = getNum(quad3.size());
			try {
				return quad3.get(random);
				} catch(Exception e) {
				System.out.println("Not possible");
			}
			
		}
		
		if(i == 3) {
			
			int random = getNum(quad4.size());
			try {
				return quad4.get(random);
				} catch(Exception e) {
				System.out.println("Not possible");
			}
			
		}
		
		System.out.println("returned 0,0,0");
		return new BlockPos(0,0,0);
	}
	
	private List<BlockPos> getQuadrant(int quad, BlockPos[] array, BlockPos core) {
		double dX = core.getX(); double dZ = core.getZ();
		List<BlockPos> list = new ArrayList<BlockPos>();
		for(int i = 0; i < array.length; i++) {
			
			BlockPos pos = array[i];
			double x = pos.getX(); double z = pos.getZ();
			
			if(quad == 1 && x < dX && z < dZ) list.add(array[i]);
			if(quad == 2 && x >= dX && z < dZ) list.add(array[i]);
			if(quad == 3 && x > dX && z >= dZ) list.add(array[i]);
			if(quad == 4 && x < dX && z > dZ) list.add(array[i]);
			
		}

		return list;
		
	}
	
	private void printArray(List<BlockPos> array) {
		for(int i = 0; i < array.size(); i++) {
			System.out.println("array: " + array.get(i));
		}
	}
	
	public static void setStructure(World world, BlockPos pos) {
		
		BlockPos pillar1 = pos.down().north().east();
		BlockPos pillar2 = pillar1.west(2);
		BlockPos pillar3 = pillar2.south(2);
		BlockPos pillar4 = pillar3.east(2);
		
		BlockPos[] nether = new BlockPos[] {pillar1.up(), pillar1, pillar1.up(2),
				                            pillar2.up(), pillar2, pillar2.up(2),
											pillar3.up(), pillar3, pillar3.up(2),
											pillar4.up(), pillar4, pillar4.up(2) };
		
		BlockPos[] cobble = new BlockPos[] {pos.down(), pos.down().north(), pos.down().south(), pos.down().east(), pos.down().west(),
											pos.up(), pos.up().north(), pos.up().south(), pos.up().east(), pos.up().west()};
		
		for(int i = 0; i < nether.length; i++) {
			world.setBlockState(nether[i], BlockInit.NETHER_STONE.getDefaultState());
		}
		
		for(int i = 0; i < cobble.length; i++) {
			world.setBlockState(cobble[i], Blocks.COBBLESTONE.getDefaultState());
		}
		
	}

	private int getNum(int length) {
		Random rn = new Random();
		int maximum = length;
		int minimum = 0;
		int range = maximum - minimum + 1;		
		return rn.nextInt(range) + minimum;	
	}
	
	private void spawnCreatures() {
		
	}

	private void placeBlocks(World worldIn, BlockPos pos) {
		double x = pos.getX();
		double y = pos.getY() - 2.0D;
		double z = pos.getZ();

		BlockPos[] positionsN = new BlockPos[] { new BlockPos(x, y, z - 1.0D), new BlockPos(x + 3.0D, y, z - 3.0D),
				new BlockPos(x, y, z - 2.0D), new BlockPos(x + 1.0D, y, z - 2.0D), new BlockPos(x + 3.0D, y, z - 2.0D),
				new BlockPos(x + 3.0D, y, z - 1.0D), new BlockPos(x - 3.0D, y, z - 2.0D),
				new BlockPos(x - 4.0D, y, z - 1.0D), new BlockPos(x - 2.0D, y, z - 1.0D),
				new BlockPos(x - 4.0D, y, z - 1.0D), new BlockPos(x - 3.0D, y, z), new BlockPos(x - 3.0D, y, z + 1.0D),
				new BlockPos(x - 4.0D, y, z + 1.0D), new BlockPos(x - 1.0D, y, z + 2.0D),
				new BlockPos(x - 2.0D, y, z + 2.0D), new BlockPos(x - 1.0D, y, z + 3.0D),
				new BlockPos(x - 2.0D, y, z + 3.0D), new BlockPos(x - 3.0D, y, z + 3.0D),
				new BlockPos(x - 4.0D, y, z + 3.0D), new BlockPos(x - 3.0D, y, z + 4.0D), new BlockPos(x, y, z + 4.0D),
				new BlockPos(x + 1.0D, y, z + 4.0D), new BlockPos(x + 2.0D, y, z + 3.0D),
				new BlockPos(x + 2.0D, y, z + 2.0D), new BlockPos(x + 3.0D, y, z + 3.0D),
				new BlockPos(x + 3.0D, y, z + 4.0D), new BlockPos(x + 4.0D, y, z + 2.0D),
				new BlockPos(x + 3.0D, y, z + 1.0D), new BlockPos(x + 3.0D, y, z), new BlockPos(x + 4.0D, y, z),
				new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x + 2.0D, y, z - 1.0D),
				new BlockPos(x + 5.0D, y, z - 1.0D), new BlockPos(x, y, z - 3.0D), new BlockPos(x - 1.0D, y, z - 4.0D),
				new BlockPos(x + 1.0D, y, z - 2.0D), new BlockPos(x + 1.0D, y, z - 4.0D),
				new BlockPos(x + 2.0D, y, z - 4.0D), new BlockPos(x - 2.0D, y, z - 3.0D) };

		BlockPos[] positionsL = new BlockPos[] { new BlockPos(x, y, z + 3.0D), new BlockPos(x + 1.0D, y, z + 3.0D),
				new BlockPos(x + 3.0D, y, z + 2.0D), new BlockPos(x + 4.0D, y, z + 3.0D),
				new BlockPos(x - 3.0D, y, z + 4.0D), new BlockPos(x - 2.0D, y, z + 1.0D),
				new BlockPos(x - 2.0D, y, z - 2.0D), new BlockPos(x + 2.0D, y, z - 2.0D),
				new BlockPos(x + 4.0D, y, z - 2.0D), new BlockPos(x + 4.0D, y, z - 1.0D) };

		for (int i = 0; i < positionsN.length; i++) {

			BlockPos netherrack = positionsN[i];
			worldIn.setBlockState(netherrack, Blocks.NETHERRACK.getDefaultState());

		}

		for (int i = 0; i < positionsL.length; i++) {

			BlockPos lava = positionsL[i];
			worldIn.setBlockState(lava, Blocks.LAVA.getDefaultState());

		}

	}


}
