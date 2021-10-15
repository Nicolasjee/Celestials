package poseidon.mod.client.commands.util;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

public class Rain extends CommandEffect {
	
	public Rain() {
		
	}
	
	public static void set(EntityPlayer player) {
		
		World worldIn = player.world;
		MinecraftServer server = player.getServer();
		
		if(!worldIn.isRemote) {
			
			WorldInfo worldinfo = server.worlds[0].getWorldInfo();
	        worldinfo.setRaining(true);

			
		}
		
		WorldInfo worldinfo = server.worlds[0].getWorldInfo();
        worldinfo.setRaining(true);
		

	}
}
