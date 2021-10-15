package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import poseidon.mod.client.commands.util.SummonPowerfullLightning;
import poseidon.mod.util.Reference;

public class CommandLightningPowerfull extends CommandBase {
	
private final List<String> aliases = Lists.newArrayList(Reference.MODID, "lnall", "lightningall");
	
	@Override
	public String getName() {
		return "lightningall";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/lightningall or /lnall - will lightning all entities around you.";
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

			
			SummonPowerfullLightning.summon(player);
			
			
		}

	}
	
}
