package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import poseidon.mod.client.commands.util.ShieldCode;
import poseidon.mod.util.Reference;

public class CommandShield extends CommandBase {
	
private final List<String> aliases = Lists.newArrayList(Reference.MODID, "shield");
	
	@Override
	public String getName() {
		return "shield";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/shield <number> - will give the keycard the given code to walk through the magnet shield";
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
		
		if(args.length < 1) return;
		
		String s = args[0];
		int id;
		
		try {
			
			id = Integer.parseInt(s);
			
			} catch(NumberFormatException e) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Dimension ID Invalid"));
			return;
		}
		
		if(!(sender == null) && sender instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) sender;

			
			ShieldCode.set(player, id);
			
			
		}

	}
	
}
