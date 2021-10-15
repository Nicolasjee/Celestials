package poseidon.mod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import poseidon.mod.objects.block.customsponge.BlockBurningLavaSponge;
import poseidon.mod.objects.block.customsponge.BlockLavaSponge;
import poseidon.mod.objects.block.customsponge.ModAir;
import poseidon.mod.objects.block.customsponge.ModSponge;
import poseidon.mod.objects.block.customsponge.WetModSponge;
import poseidon.mod.objects.block.drawVortex.BlockDraw;
import poseidon.mod.objects.block.drawbridge.BlockDrawbridge;
import poseidon.mod.objects.block.energyshield.BlockEnergyShield;
import poseidon.mod.objects.block.expansiontable.BlockExpansionTable;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.objects.block.general.BlockBounce;
import poseidon.mod.objects.block.general.BlockHidden;
import poseidon.mod.objects.block.general.BlockMagmaDestroy;
import poseidon.mod.objects.block.general.BlockNetherSpawner;
import poseidon.mod.objects.block.general.BlockShield;
import poseidon.mod.objects.block.general.BlockShieldSynch;
import poseidon.mod.objects.block.general.BlockTemperedLava;
import poseidon.mod.objects.block.general.air.BlockShieldAir;
import poseidon.mod.objects.block.general.air.DemonAir;
import poseidon.mod.objects.block.general.air.MozesAir;
import poseidon.mod.objects.block.general.elytroncrop.ElytronCrop;
import poseidon.mod.objects.block.general.lighthelp.BlockLight;
import poseidon.mod.objects.block.general.lighthelp.BlockRipple;
import poseidon.mod.objects.block.general.projector.BlockField;
import poseidon.mod.objects.block.miners.BlockMiner;
import poseidon.mod.objects.block.netherreactor.BlockUnbreakableNetherrack;
import poseidon.mod.objects.block.netherreactor.nethercore.BlockItemAir;
import poseidon.mod.objects.block.netherreactor.nethercore.BlockNetherAir;
import poseidon.mod.objects.block.netherreactor.nethercore.BlockNetherCorruption;
import poseidon.mod.objects.block.netherreactor.nethercore.BlockNetherMobSpawner;
import poseidon.mod.objects.block.netherreactor.nethercore.BlockNetherParticles;
import poseidon.mod.objects.block.netherreactor.nethercore.BlockNetherWart;
import poseidon.mod.objects.block.netherreactor.nethercore.BlockSinkHole;
import poseidon.mod.objects.block.netherreactor.nethercore.crates.NetherCrateBase;
import poseidon.mod.objects.block.netherreactor.nethercore.reactor.BlockNetherReactor;
import poseidon.mod.objects.block.particlehelpers.Particle;
import poseidon.mod.objects.block.particlehelpers.ParticleHelperSummoner;
import poseidon.mod.objects.block.riftblock.BlockRift;
import poseidon.mod.objects.block.riftblock.BlockRiftAir;
import poseidon.mod.objects.block.teslagenerator.BlockTeslaGenerator;
import poseidon.mod.test.blocks.BlockTest;

public class BlockInit {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Defaults:
		//Hardness
		/* Stone: 1.5F
		 * Ore: 3.0F
		 * Cobble: 2.0F
		 * Grass: 0.6F	
		 */
		//Resistance
		/* Stone: 10.0F
		 * Cobble: 10.0F
		 * Ore: 5.0F
		 */
	//Ores
															//Light,hard,res, harvest


//	public static final Block WITCHED_BLOCK = new WitchedBlock("witched_block", Material.ROCK, 0.0F, 3.0F, 4.0F, 2);
	

///	public static final Block EXTRA_TNT = new ExtraTNT("extra_tnt", Material.TNT, 0.0F, 0.1F, 0.1F, 0);
	//public static final Block BlockTeleporter = new BlockTeleporter("blockteleporter");
	//super(name, Material.ROCK, 0.0F, 10.0F, 10.0F, 2);

	public static final Block NETHER_REACTOR_CORE = new BlockNetherReactor("nether_reactor_core", Material.ROCK, 1.0F, 3.0F, 5.0F, 1);
	public static final Block NETHER_REACTOR_CORE_ACTIVATED = new BlockHidden("nether_reactor_core_activated", Material.ROCK, 1.0F, 3.0F, 5.0F, 1);
	public static final Block NETHER_CRATE = new NetherCrateBase("nether_crate", Material.WOOD, 0.0F, 1.0F, 1.0F, 0);
	public static final Block NETHER_STONE = new BlockBase("nether_stone", Material.ROCK, 0.0F, 1.0F, 1.0F, 0);
	public static final Block NETHER_SPAWNER = new BlockNetherSpawner("netherreactor_handler");
	public static final Block NETHER_PARTICLE_HANDLER = new BlockNetherParticles("netherreactor_particles");
	public static final Block NETHER_CORRUPTION_HANDLER = new BlockNetherCorruption("netherreactor_corruption");
	public static final Block NETHER_MOB_SPAWNER = new BlockNetherMobSpawner("nether_spawner");
	public static final Block NETHER_GLOWSTONE = new BlockHidden("netherreactor_glowstone", Material.GLASS, 1.0F, 35.0F, 35.0F, 0);
	public static final Block NETHER_GLOWSTONE_OFF = new BlockHidden("netherreactor_glowstone_off", Material.GLASS, 0.0F, 35.0F, 35.0F, 0);
	public static final Block NETHER_ROOF_AIR_REPLACER = new BlockNetherAir("block_replacer");
	public static final Block RIFT_BLOCK = new BlockRift("rift_block");
	public static final Block RIFT_AIR = new BlockRiftAir("rift_air");
	public static final Block UNBREAKABLE_NETHERRACK = new BlockUnbreakableNetherrack("unbreakable_netherrack");
	public static final Block ITEM_AIR = new BlockItemAir("item_air");
	public static final Block SINKHOLE = new BlockSinkHole("sinkhole", Material.ROCK, 1000.0F, 9999.0F, 5.0F, 1);
	
	
	public static final Block NETHER_WART_BLOCK1 = new BlockNetherWart("netherreactor_wart1", Material.WOOD, 0.0F, 1000.0F, 9999.0F, 2);
	public static final Block NETHER_WART_BLOCK2 = new BlockNetherWart("netherreactor_wart2", Material.WOOD, 0.0F, 1000.0F, 9999.0F, 2);
	public static final Block NETHER_WART_BLOCK3 = new BlockNetherWart("netherreactor_wart3", Material.WOOD, 0.0F, 1000.0F, 9999.0F, 2);
	public static final Block NETHER_WART_BLOCK4 = new BlockNetherWart("netherreactor_wart4", Material.WOOD, 0.0F, 1000.0F, 9999.0F, 2);
	public static final Block NETHER_WART_BLOCK5 = new BlockNetherWart("netherreactor_wart5", Material.WOOD, 0.0F, 1000.0F, 9999.0F, 2);
	
	/*
	public static final Block DARK_BRICK = new BlockBase("dark_brick", Material.ROCK, 0.0F, 2.0F, 4.0F, 1);
	public static final Block DIORITE_BRICK = new BlockBase("diorite_brick", Material.ROCK, 0.0F, 2.0F, 4.0F, 1);
	public static final Block CHISELED_STONEBRICK = new BlockBase("chiseled_stonebricks", Material.ROCK, 0.0F, 2.0F, 4.0F, 1);
	public static final Block ALPHA_COBBLESTONE = new BlockBase("alpha_cobblestone", Material.ROCK, 0.0F, 2.0F, 4.0F, 1);
	public static final Block CASTLE_BRICK = new BlockBase("castle_brick", Material.ROCK, 0.0F, 2.0F, 4.0F, 1);
	public static final Block CHISELED_ANDESITE = new BlockBase("chiseled_andesite", Material.ROCK, 0.0F, 2.0F, 4.0F, 1);
	public static final Block CHISELED_STONE = new BlockBase("chiseled_stone", Material.ROCK, 0.0F, 2.0F, 4.0F, 1);
	*/
	
	public static final Block EXPANSION_TABLE = new BlockExpansionTable("expansion_table");
	public static final Block MOD_AIR = new ModAir("modair");
	public static final Block ELYTRONCROP = new ElytronCrop("elytron_mineral_");
	
	public static final Block PARTICLE_HELPER = new Particle("particle_helper");
	public static final Block PARTICLE_HELPER2 = new ParticleHelperSummoner("particle_helper2");
	
	public static final Block MODSPONGE = new ModSponge("enhanced_sponge");
	public static final Block WET_MODSPONGE = new WetModSponge("wet_enhanced_sponge");
	public static final Block LAVASPONGE = new BlockLavaSponge("lava_sponge");
	public static final Block BURNINGLAVASPONGE = new BlockBurningLavaSponge("burning_lava_sponge");
	
	public static final Block DRAWBRIDGE = new BlockDrawbridge("drawbridge");
	public static final Block MINERBLOCK = new BlockMiner("miner");
	public static final Block MOZESAIR = new MozesAir("mozesair");
	public static final Block DEMONAIR = new DemonAir("demonair");
	public static final Block BLOCKDRAW = new BlockDraw("draw");
	public static final Block LAUNCHPAD = new BlockBounce("launchpad");
	public static final Block ENERGY_SHIELD = new BlockEnergyShield("energy_shield");
	
	public static final Block SHIELDBLOCK = new BlockShield("magnet_shield");
	public static final Block SHIELDAIR = new BlockShieldAir("shield_air");
	public static final Block SHIELDSYNCH = new BlockShieldSynch("shield_synch");
	
	public static final Block TESLAINDUCTOR = new BlockTeslaGenerator("flux_inductor");
	
	//public static final Block LASER = new BlockLaser("laser");
	//public static final Block CRYSTAL_SPLITTER = new BlockSplitter("crystal_splitter");
	public static final Block TEMPERED_LAVA = new BlockTemperedLava("tempered_lava");
	//public static final Block PARTICLE_ROD = new BlockTrap("lol");
	//public static final Block TESLA_GENERATOR
	
	public static final Block FIELD1 = new BlockField("field_projector1", Material.ROCK, 0.0F, 0.0F, 0.0F, 0);
	public static final Block FIELD2 = new BlockField("field_projector2", Material.ROCK, 0.0F, 0.0F, 0.0F, 0);
	public static final Block FIELD3 = new BlockField("field_projector3", Material.ROCK, 0.0F, 0.0F, 0.0F, 0);
	public static final Block FIELD4 = new BlockField("field_projector4", Material.ROCK, 0.0F, 0.0F, 0.0F, 0);
	
	public static final Block MAGMA1 = new BlockMagmaDestroy("magma_destroy1", Material.ROCK, 0.0F, 0.0F, 0.0F, 0);
	public static final Block MAGMA2 = new BlockMagmaDestroy("magma_destroy2", Material.ROCK, 0.0F, 0.0F, 0.0F, 0);
	public static final Block MAGMA3 = new BlockMagmaDestroy("magma_destroy3", Material.ROCK, 0.0F, 0.0F, 0.0F, 0);
	public static final Block MAGMA4 = new BlockMagmaDestroy("magma_destroy4", Material.ROCK, 0.0F, 0.0F, 0.0F, 0);
	public static final Block MAGMA5 = new BlockMagmaDestroy("magma_destroy5", Material.ROCK, 0.0F, 0.0F, 0.0F, 0);
	
	public static final Block LIGHT1 = new BlockLight("block_light1", Material.ROCK, 1.0F, 0.0F, 0.0F, 0);
	public static final Block LIGHT2 = new BlockLight("block_light2", Material.ROCK, 2.0F, 0.0F, 0.0F, 0);
	public static final Block LIGHT3 = new BlockLight("block_light3", Material.ROCK, 4.0F, 0.0F, 0.0F, 0);
	
	public static final Block RIPPLE = new BlockRipple("ripple", Material.ROCK, 4.0F, 0.0F, 0.0F, 0);
	
	public static final Block TEST_TILE = new BlockTest("test");
	//public static final Block OBSIDIAN_CHEST = new ObsidianChest("obsidian_chest");
	
	
}
