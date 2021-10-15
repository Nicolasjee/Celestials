package poseidon.mod.util.handlers.registry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import poseidon.mod.objects.block.crystalsplitter.ContainerSplitter;
import poseidon.mod.objects.block.crystalsplitter.GuiSplitter;
import poseidon.mod.objects.block.crystalsplitter.TileEntitySplitter;
import poseidon.mod.objects.block.drawbridge.ContainerDrawbridge;
import poseidon.mod.objects.block.drawbridge.GuiDrawbridge;
import poseidon.mod.objects.block.drawbridge.TileEntityDrawbridge;
import poseidon.mod.objects.block.expansiontable.ContainerExpansionTable;
import poseidon.mod.objects.block.expansiontable.GuiExpansionTable;
import poseidon.mod.objects.block.expansiontable.TileEntityExpansionTable;
import poseidon.mod.objects.block.general.chest.obsidian.ContainerObsidianChest;
import poseidon.mod.objects.block.general.chest.obsidian.GuiObsidianChest;
import poseidon.mod.objects.block.general.chest.obsidian.TileEntityObsidianChest;
import poseidon.mod.objects.block.general.chest.sand.ContainerSandChest;
import poseidon.mod.objects.block.general.chest.sand.GuiSandChest;
import poseidon.mod.objects.block.general.chest.sand.TileEntitySandChest;
import poseidon.mod.objects.block.laser.ContainerLaser;
import poseidon.mod.objects.block.laser.GuiLaser;
import poseidon.mod.objects.block.laser.TileEntityLaser;
import poseidon.mod.objects.block.miners.ContainerMiner;
import poseidon.mod.objects.block.miners.GuiMiner;
import poseidon.mod.objects.block.miners.TileEntityMiner;
import poseidon.mod.objects.block.netherreactor.nethercore.reactor.ContainerNetherReactor;
import poseidon.mod.objects.block.netherreactor.nethercore.reactor.GuiNetherReactor;
import poseidon.mod.objects.block.netherreactor.nethercore.reactor.TileEntityNetherReactor;
import poseidon.mod.objects.block.redstonereceiver.ContainerRedstoneReceiver;
import poseidon.mod.objects.block.redstonereceiver.GuiRedstoneReceiver;
import poseidon.mod.objects.block.redstonereceiver.TileEntityRedstoneReceiver;
import poseidon.mod.objects.block.riftblock.ContainerRiftBlock;
import poseidon.mod.objects.block.riftblock.GuiRiftBlock;
import poseidon.mod.objects.block.riftblock.TileEntityRiftTeleporter;
import poseidon.mod.objects.block.summoner.extended.ContainerSummoner;
import poseidon.mod.objects.block.summoner.extended.GuiSummoner;
import poseidon.mod.objects.block.summoner.extended.TileEntitySummoner;
import poseidon.mod.objects.block.teslagenerator.ContainerTeslaGenerator;
import poseidon.mod.objects.block.teslagenerator.GuiTeslaGenerator;
import poseidon.mod.objects.block.teslagenerator.TileEntityTeslaGenerator;
import poseidon.mod.util.Reference;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if(id == Reference.GUI_EXPANSION_TABLE) return new ContainerExpansionTable(player.inventory, (TileEntityExpansionTable)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_DRAWBRIDGE) return new ContainerDrawbridge(player.inventory, (TileEntityDrawbridge)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_MINERBLOCK) return new ContainerMiner(player.inventory, (TileEntityMiner)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_TESLA) return new ContainerTeslaGenerator(player.inventory, (TileEntityTeslaGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_REDSTONERECEIVER) return new ContainerRedstoneReceiver(player.inventory, (TileEntityRedstoneReceiver)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_SUMMONER) return new ContainerSummoner(player.inventory, (TileEntitySummoner)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.CHEST_OBSIDIAN) return new ContainerObsidianChest(player.inventory, (TileEntityObsidianChest)world.getTileEntity(new BlockPos(x,y,z)), player);
		if(id == Reference.GUI_LASER) return new ContainerLaser(player.inventory, (TileEntityLaser)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_SPLITTER) return new ContainerSplitter(player.inventory, (TileEntitySplitter)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_SANDCHEST) return new ContainerSandChest(player.inventory, (TileEntitySandChest)world.getTileEntity(new BlockPos(x,y,z)), player);
		if(id == Reference.RIFT_BLOCK) return new ContainerRiftBlock(player.inventory, (TileEntityRiftTeleporter)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUINETHER) return new ContainerNetherReactor(player.inventory, (TileEntityNetherReactor)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
		
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if(id == Reference.GUI_EXPANSION_TABLE) return new GuiExpansionTable(player.inventory, (TileEntityExpansionTable)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_DRAWBRIDGE) return new GuiDrawbridge(player.inventory, (TileEntityDrawbridge)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_MINERBLOCK) return new GuiMiner(player.inventory, (TileEntityMiner)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_TESLA) return new GuiTeslaGenerator(player.inventory, (TileEntityTeslaGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_REDSTONERECEIVER) return new GuiRedstoneReceiver(player.inventory, (TileEntityRedstoneReceiver)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_SUMMONER) return new GuiSummoner(player.inventory, (TileEntitySummoner)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.CHEST_OBSIDIAN) return new GuiObsidianChest(player.inventory, (TileEntityObsidianChest)world.getTileEntity(new BlockPos(x,y,z)), player);
		if(id == Reference.GUI_LASER) return new GuiLaser(player.inventory, (TileEntityLaser)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_SPLITTER) return new GuiSplitter(player.inventory, (TileEntitySplitter)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUI_SANDCHEST) return new GuiSandChest(player.inventory, (TileEntitySandChest)world.getTileEntity(new BlockPos(x,y,z)), player);
		if(id == Reference.RIFT_BLOCK) return new GuiRiftBlock(player.inventory, (TileEntityRiftTeleporter)world.getTileEntity(new BlockPos(x,y,z)));
		if(id == Reference.GUINETHER) return new GuiNetherReactor(player.inventory, (TileEntityNetherReactor)world.getTileEntity(new BlockPos(x,y,z)));
		
		return null;
	}
	
}
