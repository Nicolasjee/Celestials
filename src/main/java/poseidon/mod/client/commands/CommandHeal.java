package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import poseidon.mod.client.commands.util.Heal;
import poseidon.mod.util.Reference;

public class CommandHeal extends CommandBase {

	private final List<String> aliases = Lists.newArrayList(Reference.MODID, "heal", "h");
	
	@Override
	public String getName() {
		return "heal";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/heal or /h - will restore your hunger and health bar.";
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
			Heal.healPlayer(player, 3);
			
		}

	}
}
