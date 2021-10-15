package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import poseidon.mod.client.commands.util.KillAll;
import poseidon.mod.client.commands.util.RemoveEntities;
import poseidon.mod.util.Reference;

public class CommandRemoveEntities extends CommandBase {
	
private final List<String> aliases = Lists.newArrayList(Reference.MODID, "removeentities", "removee", "ren");
	
	@Override
	public String getName() {
		return "removeentities";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/removeentities - removes all entities (items) in a radius";
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

			
			RemoveEntities.remove(player);
			
			
		}

	}
	
	
}
