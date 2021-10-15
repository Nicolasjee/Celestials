package poseidon.mod.util.handlers.registry;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import poseidon.mod.client.commands.CommandDay;
import poseidon.mod.client.commands.CommandDimension;
import poseidon.mod.client.commands.CommandDupe;
import poseidon.mod.client.commands.CommandEnderPearl;
import poseidon.mod.client.commands.CommandFeed;
import poseidon.mod.client.commands.CommandFireResistance;
import poseidon.mod.client.commands.CommandFireball;
import poseidon.mod.client.commands.CommandFly;
import poseidon.mod.client.commands.CommandHeal;
import poseidon.mod.client.commands.CommandKillAll;
import poseidon.mod.client.commands.CommandKillStick;
import poseidon.mod.client.commands.CommandLightning;
import poseidon.mod.client.commands.CommandLightningPowerfull;
import poseidon.mod.client.commands.CommandMore;
import poseidon.mod.client.commands.CommandNight;
import poseidon.mod.client.commands.CommandNightVision;
import poseidon.mod.client.commands.CommandPushEntities;
import poseidon.mod.client.commands.CommandRain;
import poseidon.mod.client.commands.CommandRegeneration;
import poseidon.mod.client.commands.CommandRemoveEntities;
import poseidon.mod.client.commands.CommandRemoveGrass;
import poseidon.mod.client.commands.CommandRemovePotionEffects;
import poseidon.mod.client.commands.CommandShield;
import poseidon.mod.client.commands.CommandSun;
import poseidon.mod.client.commands.CommandTNT;
import poseidon.mod.client.commands.CommandTesla;

public class CommandHandler {

	public static void register(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandDimension());
		event.registerServerCommand(new CommandHeal());
		event.registerServerCommand(new CommandFireball());
		event.registerServerCommand(new CommandFly());
		event.registerServerCommand(new CommandLightning());
		event.registerServerCommand(new CommandLightningPowerfull());
		event.registerServerCommand(new CommandRemovePotionEffects());
		event.registerServerCommand(new CommandEnderPearl());
		event.registerServerCommand(new CommandNight());
		event.registerServerCommand(new CommandNightVision());
		event.registerServerCommand(new CommandDay());
		event.registerServerCommand(new CommandRemoveGrass());
		event.registerServerCommand(new CommandPushEntities());
		event.registerServerCommand(new CommandFeed());
		event.registerServerCommand(new CommandRegeneration());
		event.registerServerCommand(new CommandFireResistance());
		event.registerServerCommand(new CommandRain());
		event.registerServerCommand(new CommandSun());
		event.registerServerCommand(new CommandTNT());
		event.registerServerCommand(new CommandTesla());
		event.registerServerCommand(new CommandDupe());
		event.registerServerCommand(new CommandMore());
		event.registerServerCommand(new CommandKillStick());
		event.registerServerCommand(new CommandShield());
		event.registerServerCommand(new CommandKillAll());
		event.registerServerCommand(new CommandRemoveEntities());
	}
	
}
