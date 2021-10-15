package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import poseidon.mod.client.commands.util.SetTimeDay;
import poseidon.mod.util.Reference;

public class CommandGetNBT extends CommandBase {
	
private final List<String> aliases = Lists.newArrayList(Reference.MODID, "getnbt");
	
	@Override
	public String getName() {
		return "getnbt";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/getnbt - gets the nbt of the item for extra properties";
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

			
			SetTimeDay.setDay(player, 1);
			
			
		}

	}
	
	
}
