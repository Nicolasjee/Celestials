package poseidon.mod.objects.tools.teslainfuser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import poseidon.mod.init.ItemInit;

public interface TeslaProperties {
	
	public static final int HITCOST = 1;

	public static final String FORCE = "Force";							public static final int FORCECOST = 15;
	public static final String VELOCITY = "Velocity";					public static final int VELOCITYCOST = 20;
	public static final String SNAP = "Snap";							public static final int SNAPCOST = 200;
	public static final String STOP = "Stop"; 							public static final int STOPCOST = 20;
	// no velocity + y++		
	public static final String CHOKE = "Choke";							public static final int CHOKECOST = 20;
	public static final String GROUNDFORCE = "Groundforce";				public static final int GROUNDFORCECOST = 20;
	public static final String CLOAK = "Cloak";							public static final int CLOAKCOST = 20;
	public static final String PUSH = "Push";							public static final int PUSHCOST = 18;
	public static final String HEAL = "Heal";							public static final int HEALCOST = 5;
	public static final String LIGHTNING = "Lightning";					public static final int LIGHTNINGCOST = 20;
	public static final String FIREBALL = "Fireball";					public static final int FIREBALLCOST = 20;
	public static final String TNT = "Tnt";								public static final int TNTCOST = 45;
	public static final String MINER = "Miner";							public static final int MINERCOST = 5;
	public static final String TELEPORT = "Teleport";					public static final int TELEPORTCOST = 50;
	public static final String ELYTRA = "Elytra Boost";					public static final int ELYTRACOST = 40;
	
	public static final String EMPTY = "Empty";
	public static final int maxDur = 2200; // durability flux battery
	
	public static final double PUSHREACH = 3.0D;
	public static final float CHOKEREACH = 200.0F;
	public static final double STOPARROWREACH = 1.5D;
	public static final int STOPARROWCOST = 1;
	
	public static final int FALLHIGH = 20;
	public static final int FALLMED = 10;
	public static final int FALLLOW = 1;
	
	public static final int durability = 800;
	
	public static final int[] costs = new int[] {FORCECOST, STOPCOST, LIGHTNINGCOST, FIREBALLCOST, TNTCOST, MINERCOST, TELEPORTCOST};
	public static final String[] abilities = new String[] {FORCE, STOP, LIGHTNING, FIREBALL, TNT, MINER, TELEPORT};
	
	/**
	 * Gets cost of a certain ability with the given focus integer.
	 * Focus can only be 0,1,2 or 3.
	 */
	public static int getCost(int focus, ItemStack stack) {
		String getA = getAbilityOfIndex(focus, stack);
		List<String> p = new ArrayList<String>();
		for(int i = 0; i < abilities.length; i++) {
			p.add(abilities[i]);
		}
		if(p.contains(getA)) return costs[p.indexOf(getA)];
		
		System.out.println("Returned 0");

		return 0;
	}
	
	public static int getCostFromString(int focus, String s) {
		List<String> p = new ArrayList<String>();
		for(int i = 0; i < abilities.length; i++) {
			p.add(abilities[i]);
		}
		if(p.contains(s)) return costs[p.indexOf(s)];
		System.out.println("Returned 0. Big oof!");
		return 0;
	}
	
	/**
	 * Only called in the TeslaProperties interface.
	 */
	public static List<String> getPowers() {
		List<String> powerList = new ArrayList<String>();
		for(int i = 0; i < abilities.length; i++) {
			powerList.add(abilities[i]);
		}
		return powerList;
	}

	/**
	 * -Unused- USED  Method.
	 * This method is used to stop NBT from: "a" != "a" but: list.get(i) == "a" (get(i) = "a")
	 */
	public static String getAbilityString(String s) {
		List<String> powerList = new ArrayList<String>();
		for(int i = 0; i < abilities.length; i++) {
			powerList.add(abilities[i]);
		}
		if(powerList.contains(s)) return abilities[powerList.indexOf(s)];
		else return EMPTY;
	}

	/**
	 * Gets the string ability at a certain index of the array.
	 * int i can only be 0,1,2 or 3.
	 */
	public static String getAbilityOfIndex(int i, ItemStack stack) {
		if(i > 3 || i < 0) {
			System.out.println("Exception: passed parameter was out of bounds: " + i + ", the integer can only be 0,1,2 or 3. This method returned EMPTY as string.");
			return EMPTY;
		}
		if(!stack.hasTagCompound()) {
			System.out.println("Exception: itemstack didn't have a tagCompound: " + stack.hasTagCompound() + ", returned string is EMPTY."); 
			return EMPTY;
		}
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			int powerOfArray = nbt.getIntArray("DataArray")[i];
			return getPowerFromNumber(powerOfArray);
		}
		
		System.out.println("Exception: something is not right. i = " + i + ", stack: " + stack + ", hasTag: " + stack.hasTagCompound());

		
		return EMPTY;
	}
	
	/**
	 * Returns the string of an ability on the current focus.
	 */
	public static String getAbilityOfFocus(ItemStack stack) {
		if(!stack.hasTagCompound()) {
			System.out.println("Exception: itemstack didn't have a tagCompound: " + stack.hasTagCompound() + ", returned string is EMPTY."); 
			return EMPTY;
		}
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			int focus = focus(nbt.getIntArray("DataArray"));
			int powerOfArray = nbt.getIntArray("DataArray")[focus];
			return getPowerFromNumber(powerOfArray);
		}
		

		System.out.println("Exception: something is not right." + " stack: " + stack + ", hasTag: " + stack.hasTagCompound());

		
		return EMPTY;
	}
	
	public static boolean doesPowerExists(String s) {
		List<String> sd = getPowers();
		if(sd.contains(s)) return true;
		return false;
	}
	
	public static int focus(int[] a) {
		return a[4];
	}
	
	public static int dur(int[] a) {
		return a[5];
	}

	public static String getPowerFromNumber(int i) {
		if(i == 0) {
			System.out.println("i was 0");
			return "";
		}
		if(i == 1) return FORCE;
		if(i == 2) return STOP;
		if(i == 3) return LIGHTNING;
		if(i == 4) return FIREBALL;
		if(i == 5) return TNT;
		if(i == 6) return MINER;
		if(i == 7) return TELEPORT;
		else {
			//DEBUG
			System.out.println("Exception: empty string has returned because int i was too high or too low. i = " + i);
			return EMPTY;
		}
	}
	
	public static int getNumberFromPower(String s) {
		if(s == FORCE) return 1;
		if(s == STOP) return 2;
		if(s == LIGHTNING) return 3;
		if(s == FIREBALL) return 4;
		if(s == TNT) return 5;
		if(s == MINER) return 6;
		if(s == TELEPORT) return 7;
		else {
			System.out.println("Exception: integer 100 has returned because s was not found. s = " + s);
			return 100;
		}
	}

	public static boolean sufficientDurability(String s, int d, int f) {
		
		if(doesPowerExists(s)) {
			
			int c = getCostFromString(f, s);
			
			if(c > d) return false;
			else return true;
				
			
			} else {
			System.out.println("Somthing wrong...");
			return false;
		}
		
	}
	
	public static boolean sufficientIntegerDurability(int d, ItemStack stack) {
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("DataArray")) {
			
			NBTTagCompound nbt = stack.getTagCompound();
			int[] c = nbt.getIntArray("DataArray");
			int s = dur(c);
			
			if(d > s) return false;
			else return true;
				
			
			} else {
			System.out.println("Somthing wrong...");
			return false;
		}
		
	}
	
	public static ItemStack addRandomly(ItemStack stack) {
		
		if(stack.getItem() == ItemInit.UPGRADE_MODULE) {
			
			Random rand = new Random();
			int maximum = abilities.length;
			int minimum = 1;
			int range = maximum - minimum + 1;		
			String power = getPowerFromNumber(rand.nextInt(range) + minimum);
			
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setString("upgradePower", power);
			
			stack.setTagCompound(nbt);
			
			return stack;
			
		}
		
		System.out.println("Given stack was unable to get a power. The item must be the upgrade module (" + ItemInit.UPGRADE_MODULE + ").");
		
		return stack;
		
	}
}

