package poseidon.mod.client.commands;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import poseidon.mod.client.commands.util.Teleport;
import poseidon.mod.util.Reference;

public class CommandDimension extends CommandBase {

	private final List<String> aliases = Lists.newArrayList(Reference.MODID, "tpdim", "tpdimension", "teleportdimension", "teleport");
	
	@Override
	public String getName() {
		return "tpdimension";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/tpdimension <id> or /tpdim <id> will teleport you to another dimension. Don't use it though. It's broken and it will let you fall in the void";
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
		if(args.length < 1) return;
		
		String s = args[0];
		int dimensionID;
		
		try {
			
			dimensionID = Integer.parseInt(s);
			
			} catch(NumberFormatException e) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Dimension ID Invalid"));
			return;
		}
		
		if(sender instanceof EntityPlayer) {
			
			Teleport.teleportToDimension((EntityPlayer) sender, dimensionID, sender.getPosition().getX(), sender.getPosition().getY(), sender.getPosition().getZ());
			
		}
	}
	
	public static void getSurroundingsDimension(WorldServer dimension, double x, double y, double z) {

		BlockPos pos1 = new BlockPos(x, y - 1, z);
		
		BlockPos pos2 = new BlockPos(x + 1, y - 1, z+ 1);
		BlockPos pos3 = new BlockPos(x + 1, y - 1, z - 1);
		BlockPos pos4 = new BlockPos(x + 1, y - 1, z);
		
		BlockPos pos5 = new BlockPos(x, y - 1, z + 1);
		BlockPos pos6 = new BlockPos(x, y - 1, z - 1);
		
		BlockPos pos7 = new BlockPos(x - 1, y - 1, z + 1);
		BlockPos pos8 = new BlockPos(x - 1, y - 1, z);
		BlockPos pos9 = new BlockPos(x - 1, y - 1, z - 1);
		
		double i = y - 1;
		
		BlockPos pos10 = new BlockPos(x + 1, i, z + 2);
		BlockPos pos11 = new BlockPos(x + 1, i, z - 2);
		BlockPos pos12 = new BlockPos(x, i, z + 2);
		BlockPos pos13 = new BlockPos(x, i, z - 2);
		BlockPos pos14 = new BlockPos(x - 1, i, z + 2);
		BlockPos pos15 = new BlockPos(x - 1, i, z - 2);
		
		BlockPos pos16 = new BlockPos(x + 2, i, z + 2);
		BlockPos pos17 = new BlockPos(x + 2, i, z + 1);
		BlockPos pos18 = new BlockPos(x + 2, i, z);
		BlockPos pos19 = new BlockPos(x + 2, i, z - 1);
		BlockPos pos20 = new BlockPos(x + 2, i, z - 2);
		
		BlockPos pos21 = new BlockPos(x - 2, i, z + 2);
		BlockPos pos22 = new BlockPos(x - 2, i, z + 1);
		BlockPos pos23 = new BlockPos(x - 2, i, z);
		BlockPos pos24 = new BlockPos(x - 2, i, z - 1);
		BlockPos pos25 = new BlockPos(x - 2, i, z - 2);
		
		//surrounding
		BlockPos pos26 = new BlockPos(x + 2, y, z + 2);
		BlockPos pos27 = new BlockPos(x + 2, y, z + 1);
		BlockPos pos28 = new BlockPos(x + 2, y, z);
		BlockPos pos29 = new BlockPos(x + 2, y, z - 1);
		BlockPos pos30 = new BlockPos(x + 2, y, z - 2);
		
		BlockPos pos31 = new BlockPos(x - 2, y, z + 2);
		BlockPos pos32 = new BlockPos(x - 2, y, z + 1);
		BlockPos pos33 = new BlockPos(x - 2, y, z);
		BlockPos pos34 = new BlockPos(x - 2, y, z - 1);
		BlockPos pos35 = new BlockPos(x - 2, y, z - 2);
		
		BlockPos pos36 = new BlockPos(x + 1, y, z + 2);
		BlockPos pos37 = new BlockPos(x + 1, y, z - 2);
		BlockPos pos38 = new BlockPos(x, y, z + 2);
		BlockPos pos39 = new BlockPos(x, y, z - 2);
		BlockPos pos40 = new BlockPos(x - 1, y, z + 2);
		BlockPos pos41 = new BlockPos(x - 1, y, z - 2);
		
		
			
		if(!dimension.isRemote) {
			dimension.setBlockState(pos1, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos2, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos3, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos4, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos5, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos6, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos7, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos8, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos9, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos10, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos11, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos12, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos13, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos14, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos15, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos16, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos17, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos18, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos19, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos20, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos21, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos22, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos23, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos24, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos25, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos26, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos27, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos28, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos29, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos30, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos31, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos32, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos33, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos34, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos35, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos36, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos37, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos38, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos39, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos40, Blocks.BEDROCK.getDefaultState());
			dimension.setBlockState(pos41, Blocks.BEDROCK.getDefaultState());
		}
	}
	
	
}
