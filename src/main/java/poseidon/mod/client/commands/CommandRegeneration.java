package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import poseidon.mod.client.commands.util.Regeneration;
import poseidon.mod.util.Reference;

public class CommandRegeneration extends CommandBase {
	
private final List<String> aliases = Lists.newArrayList(Reference.MODID, "regeneration", "rg", "regen");
	
	@Override
	public String getName() {
		return "regeneration";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/regeneration or /regen or /rg will give you the regeneration potion effect for unlimited amount of time";
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

			
			Regeneration.activate(player, 1);
			
			
		}

	}
}
