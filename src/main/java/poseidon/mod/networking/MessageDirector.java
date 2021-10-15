package poseidon.mod.networking;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.helpers.tracehelper.TraceSpawner;

public class MessageDirector implements TeslaProperties {

	public static void tesla(EntityPlayer player, Entity entity) {
		
		ItemStack stack = player.getHeldItemMainhand();
		String s = TeslaProperties.getAbilityOfFocus(stack);
		
		if(s == STOP) {
			MessageHelper.stop(entity);
		}
		
	}
	
	public static void kill(EntityPlayer player , Entity entity) {
		entity.onKillCommand();
	}
	
	public static void smartKill(EntityPlayer player, Entity entity, WorldServer server) {
		
		boolean sneaking = player.isSneaking();
		BlockPos pos = player.getPosition();
		TraceSpawner.drawLines(getBlockPosArray(player),
								getEntityArray(entity),
								pos, server, player);
		
	}
	
	private static List<BlockPos> getBlockPosArray(EntityPlayer player) {
		List<BlockPos> list = new ArrayList<BlockPos>();
		list.add(player.getPosition());
		return list;
	}
	
	private static List<Entity> getEntityArray(Entity entity) {
		List<Entity> list = new ArrayList<Entity>();
		list.add(entity);
		return list;
	}
	
}
