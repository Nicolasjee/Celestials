package poseidon.mod.objects.block.summoner;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.objects.block.summoner.extended.TileEntitySummoner;
import poseidon.mod.util.Reference;

public class Summoner extends BlockBase implements ITileEntityProvider {

	public static boolean activated = false;
	
	public Summoner(String name) {
		super(name, Material.ROCK, 0.0F, 3.0F, 10.0F, 0);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySummoner();
	}
	
//	private boolean hasNoKey(ItemStack stack) {
//		return stack.getItem() != ItemInit.SUMMONING_KEY_MAGIC && stack.getItem() != ItemInit.SUMMONING_KEY_NETHER;
//	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack held = playerIn.getHeldItem(hand);
		
		if(!worldIn.isRemote && worldIn.getTileEntity(pos) instanceof TileEntitySummoner) {
			TileEntitySummoner s = (TileEntitySummoner) worldIn.getTileEntity(pos);
			if(!s.active) playerIn.openGui(Main.instance, Reference.GUI_SUMMONER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		int type;
		int mistakes = 0;
/*
		if(held.getItem() == ItemInit.SUMMONING_KEY_MAGIC && worldIn.getBlockState(pos.down()) == Blocks.STONEBRICK.getDefaultState()) {
			type = 2;
			BlockPos[] pilars = SummonHelperWizard.getPillar(pos);
			BlockPos[] diorite = SummonHelperWizard.getDiorite(pos);
			BlockPos brick = new BlockPos(x,y - 1.0D,z);
			BlockPos[] obs = new BlockPos[] {new BlockPos(x + 1.0D, y - 2.0D, z), new BlockPos(x - 1.0D, y - 2.0D, z), new BlockPos(x, y - 2.0D, z + 1.0D), new BlockPos(x, y - 2.0D, z - 1.0D)};
			BlockPos[] glow = SummonHelperWizard.getGlowstone(pos);
			mistakes = SummonHelperWizard.getMistakes(pilars, diorite, brick, obs, glow, worldIn);
			
			SummonHelperWizard.checkForOpenSpaces(worldIn, pos);

			if(mistakes == 0) SummonHelperWizard.spawnWizard(worldIn, pos);
			
		}*/
		
//		if(held.getItem() == ItemInit.SUMMONING_KEY_HELL && worldIn.getBlockState(pos.down()) == Blocks.QUARTZ_BLOCK.getDefaultState()) {
//			type = 1;
//			BlockPos[] netherstone = SummonHelperLucifer.getNetherStone(pos);
//			BlockPos[] soulsand = SummonHelperLucifer.getSoulSand(pos);
//			BlockPos quartz = new BlockPos(x,y - 1.0D,z);
//			BlockPos[] obs = new BlockPos[] {new BlockPos(x + 1.0D, y - 2.0D, z), new BlockPos(x - 1.0D, y - 2.0D, z), new BlockPos(x, y - 2.0D, z + 1.0D), new BlockPos(x, y - 2.0D, z - 1.0D)};
//			
//			mistakes = SummonHelperLucifer.getMistakes(netherstone, soulsand, quartz, obs, worldIn);
//			
//			SummonHelperLucifer.checkForOpenSpaces(worldIn, pos);
//			
//			if(mistakes == 0) SummonHelperLucifer.spawnLucifer(worldIn, pos, playerIn);
//			
//		}
		
		if(held.getItem() == ItemInit.SUMMONING_KEY_NETHER && worldIn.getBlockState(new BlockPos(x, y - 1.0D, z)).getBlock().getDefaultState() == Blocks.BOOKSHELF.getDefaultState()) {
			
			BlockPos[] bricks = SummonerHelperKnowledge.getStone(worldIn, pos);
			BlockPos[] glow = SummonerHelperKnowledge.getGlowstone(worldIn, pos);
			BlockPos[] ob = SummonerHelperKnowledge.getObb(worldIn, pos);
			BlockPos[] ter = SummonerHelperKnowledge.getCotta(worldIn, pos);
			BlockPos[] neth = SummonerHelperKnowledge.getNether(worldIn, pos);
			
			mistakes = SummonerHelperKnowledge.getMistakes(bricks, glow, ob, ter, neth, worldIn);
			System.out.println("mistakes: " + mistakes);
			if(mistakes == 0) SummonerHelperKnowledge.execute(worldIn, pos, playerIn, neth, held);
			
		}
		
		if(held.getItem() == ItemInit.SUMMONING_KEY_NETHER && worldIn.getBlockState(new BlockPos(x, y - 1.0D, z)).getBlock().getDefaultState() == Blocks.LAPIS_BLOCK.getDefaultState()) {
			
			BlockPos[] bricks = SummonerHelperKnowledgeEnhanced.getStone(worldIn, pos);
			BlockPos[] glow = SummonerHelperKnowledgeEnhanced.getGlowstone(worldIn, pos);
			BlockPos[] ob = SummonerHelperKnowledgeEnhanced.getObb(worldIn, pos);
			BlockPos[] ter = SummonerHelperKnowledgeEnhanced.getCotta(worldIn, pos);
			BlockPos[] neth = SummonerHelperKnowledgeEnhanced.getNether(worldIn, pos);
			BlockPos[] pillars = SummonerHelperKnowledgeEnhanced.getPillars(worldIn, pos);
			BlockPos[] glass = SummonerHelperKnowledgeEnhanced.getGlass(worldIn, pos);
			BlockPos[] quartz = SummonerHelperKnowledgeEnhanced.valueables(worldIn, pos);
			mistakes = SummonerHelperKnowledgeEnhanced.getMistakes(bricks, glow, ob, ter, neth, pillars, glass, worldIn, pos, quartz);
			System.out.println("mistakes: " + mistakes);
			if(mistakes == 0) SummonerHelperKnowledgeEnhanced.execute(worldIn, pos, playerIn, neth, held);
			
		}

		
		return true;
	}
	
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntitySummoner tile = (TileEntitySummoner) worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tile);
		super.breakBlock(worldIn, pos, state);
	}


	
	

	
}
