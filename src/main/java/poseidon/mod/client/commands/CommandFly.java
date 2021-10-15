package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import poseidon.mod.client.commands.util.Fly;
import poseidon.mod.util.Reference;

public class CommandFly extends CommandBase {

private final List<String> aliases = Lists.newArrayList(Reference.MODID, "fly");
	
	@Override
	public String getName() {
		return "fly";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/fly - will give you the ability to fly";
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
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
	
		
		
		if(!(sender == null) && sender instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) sender;
			Fly.initiateFlight(player);
			
		}

	}
	
}
