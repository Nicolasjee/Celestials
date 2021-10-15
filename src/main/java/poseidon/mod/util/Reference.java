package poseidon.mod.util;

import net.minecraft.nbt.NBTTagCompound;

public class Reference {
	
	public static final String MODID = "psm";
	public static final String NAME = "Small Additions";
	public static final String VERSION = "1.0";
	public static final String CLIENT = "poseidon.mod.proxy.ClientProxy";
	public static final String COMMON = "poseidon.mod.proxy.CommonProxy";
	
	public static final int ENTITY_CENTAUR = 120;
	public static final int ENTITY_STEVE = 121;
	public static final int ENTITY_LUCIFER = 122;
	public static final int ENTITY_OLD_WIZARD = 123;
	public static final int ENTITY_DRUGDEALER = 124;
	public static final int ENTITY_DEMON = 125;
	public static final int ENTITY_CCREEPER = 126;
	public static final int ENTITY_CASTIEL = 127;
	
	public static final int GUI_SINTERING_FURNACE = 0;
	public static final int GUI_EXPANSION_TABLE = 1;
	public static final int GUI_DRAWBRIDGE = 2;
	public static final int GUI_MINERBLOCK = 3;
	public static final int GUI_TESLA = 4;
	public static final int GUI_REDSTONERECEIVER = 5;
	public static final int GUI_SUMMONER = 6;
	
	public static final int CHEST_OBSIDIAN = 7;
	public static final int GUI_LASER = 8;
	public static final int GUI_SPLITTER = 9;
	public static final int GUI_SANDCHEST = 10;
	public static final int RIFT_BLOCK = 11;
	public static final int GUINETHER = 12;
	
	public static int maxLevelPerception = 200;
	public static int soundLoopPerception = maxLevelPerception + 400;
	public static int elytraBoostModifier = 2;
	
	public static boolean hasKey(String key, NBTTagCompound nbt) {
		if(nbt.hasKey(key) && nbt != null) return true;
		else return false;
	}
	
}
