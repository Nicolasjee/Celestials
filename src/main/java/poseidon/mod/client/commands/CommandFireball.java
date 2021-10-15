package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import poseidon.mod.client.commands.util.Heal;
import poseidon.mod.client.commands.util.LaunchFireball;
import poseidon.mod.util.Reference;

public class CommandFireball extends CommandBase {
	
private final List<String> aliases = Lists.newArrayList(Reference.MODID, "fb", "fireb", "fireball");
	
	@Override
	public String getName() {
		return "fireball";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/fireball or /fireb or /fb - will send a fireball in the direction you're currently looking at";
	}
	
	@Override
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		
		if(!(sender == null) && sender instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) sender;

			
			LaunchFireball.launch(player);
			
			
		}

	}
	
}
