package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import poseidon.mod.client.commands.util.RemoveGrass;
import poseidon.mod.client.commands.util.SetTimeDay;
import poseidon.mod.client.commands.util.Teleport;
import poseidon.mod.util.Reference;

public class CommandRemoveGrass extends CommandBase {
	
private final List<String> aliases = Lists.newArrayList(Reference.MODID, "removegrass", "remgrass", "remg");
	
	@Override
	public String getName() {
		return "removegrass";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "removegrass <radius> [example: radius = 20 -> 20x20 area around player]";
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
		
		if(args.length < 1 || args.length > 1) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Missing or invalid arguments"));
			return;
		}
		
		String s = args[0];
		int radius;
		
		try {
			
			radius = Integer.parseInt(s);
			
			} catch(NumberFormatException e) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Dimension ID Invalid"));
			return;
		}
		
		if(radius > 40) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid radius. Max: 40"));
			return;
		}
		
		if(radius < 10) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid radius. Min: 10"));
			return;
		}
		
		if(sender instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) sender;
			RemoveGrass.remove(player, radius);
			
		}

	}
	
}
