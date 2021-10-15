package poseidon.mod.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.helpers.RandomEnchantmentUtil;

public class Utilities implements TeslaProperties {
	
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER);
	
    public static Item setItemName(Item parItem, String parItemName)
    {
        parItem.setRegistryName(parItemName);
        parItem.setUnlocalizedName(parItemName);
        return parItem;
    }
    
    public static void statusMessage(EntityPlayer player, String s, TextFormatting t) {
    	player.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + "Keycode: " + t + s, new Object[0]), true);
    }
    
    public static boolean isShovelMaterial(IBlockState state) {
    	if(EFFECTIVE_ON.contains(state.getBlock())) return true;
    	else return false;
    }
    
    public static void status(EntityPlayer player, String s, TextFormatting t) {
    	player.sendStatusMessage(new TextComponentTranslation(t + s, new Object[0]), true);
    }
    
	public static ItemStack enchantedBook() {
		
		Random random = new Random();
		ItemStack ret = new ItemStack(Items.ENCHANTED_BOOK);
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.clear();
		int num = 1;
		
        for (Enchantment enchantment : Enchantment.REGISTRY) {
            if (enchantment.type != null) {
                for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); ++i) {
                    items.add(RandomEnchantmentUtil.getEnchantedItemStack(new EnchantmentData(enchantment, enchantment.getMaxLevel())));
                }
            }
        }
		
        num = getInteger(random, items);
        if(num == 99) num = getInteger(random, items);
        
		return items.get(num);
	}
	
	private static int getInteger(Random random, List<ItemStack> items) {
		int num = 1 + random.nextInt(items.size());
		return num;
	}
	
    
    public static boolean canStack(ItemStack stack) {
    	Item item = stack.getItem();
    	int size = item.getItemStackLimit();
    	if(size == 1) return false;
    	else return true;
    }
	
    public static boolean isSeed(ItemStack stack) {
    	if(stack.getItem() == Items.BEETROOT_SEEDS || stack.getItem() == Items.MELON_SEEDS || stack.getItem() == Items.PUMPKIN_SEEDS || stack.getItem() == Items.WHEAT_SEEDS) return true;
    	else return false;
    }
    
    public static boolean isMeat(ItemStack stack) {
    	if(stack.getItem() == Items.COOKED_BEEF || stack.getItem() == Items.COOKED_CHICKEN || stack.getItem() == Items.COOKED_FISH || stack.getItem() == Items.COOKED_MUTTON || stack.getItem() == Items.COOKED_PORKCHOP || stack.getItem() == Items.COOKED_RABBIT || stack.getItem() == Items.ROTTEN_FLESH)
    		return true;
    	else return false;
    }
    
    public static int getRandom(int max, int min) {
    	Random rn = new Random();
		int maximum = max;
		int minimum = min;
		int range = maximum - minimum + 1;		
		return rn.nextInt(range) + minimum;
    	
    }
    
    public static String getRandomPower() {
    	
    	Random rn = new Random();
		int maximum = abilities.length * 10;
		int minimum = 1;
		int range = maximum - minimum + 1;		
		int r =  rn.nextInt(range) + minimum;
    	
    	Random rn2 = new Random();
		int maximum2 = 9;
		int minimum2 = 1;
		int range2 = maximum - minimum + 1;		
		int b = rn.nextInt(range) + minimum;
		
		int c = maximum / abilities.length;
    	
		for(int i = 0; i < abilities.length; i++) {
			//debug System.out.println("i: " + i + ", r = " + r + ", c: " + c + ", (" + r + " > " + c*i + " && " + r + " < " + c*(i+1));
			if(r > c*i && r <= c*(i+1)) return abilities[i];
		}
    	
    	return abilities[b];
    	
    }
    
    public static RayTraceResult getBlockTarget(World world, EntityPlayer player) {
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + (double) (world.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
		Vec3d vec3 = new Vec3d(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 50;
		Vec3d vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		return world.rayTraceBlocks(vec3, vec31, true, false, false);
	}
	
	public static RayTraceResult getMouseOverExtended(float dist)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Entity theRenderViewEntity = mc.getRenderViewEntity();
        AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(
                theRenderViewEntity.posX - 0.5D,
                theRenderViewEntity.posY - 0.0D,
                theRenderViewEntity.posZ - 0.5D,
                theRenderViewEntity.posX + 0.5D,
                theRenderViewEntity.posY + 1.5D,
                theRenderViewEntity.posZ + 0.5D);
        RayTraceResult returnMOP = null;
        if (mc.world != null)
        {
            double var2 = dist;
            returnMOP = theRenderViewEntity.rayTrace(var2, 0);
            double calcdist = var2;
            Vec3d pos = theRenderViewEntity.getPositionEyes(0);
            var2 = calcdist;
            if (returnMOP != null)
            {
                calcdist = returnMOP.hitVec.distanceTo(pos);
            }

            Vec3d lookvec = theRenderViewEntity.getLook(0);
            Vec3d var8 = pos.addVector(lookvec.x * var2, lookvec.y * var2, lookvec.z * var2);
            Entity pointedEntity = null;
            float var9 = 1.0F;
            List<Entity> list = mc.world.getEntitiesWithinAABBExcludingEntity(theRenderViewEntity,
                    theViewBoundingBox.grow(lookvec.x * var2, lookvec.y * var2, lookvec.z * var2).expand(var9, var9, var9));
            double d = calcdist;

            for (Entity entity : list)
            {
                if (entity.canBeCollidedWith())
                {
                    float bordersize = entity.getCollisionBorderSize();
                    AxisAlignedBB aabb = new AxisAlignedBB(entity.posX - entity.width / 2, entity.posY, entity.posZ - entity.width / 2,
                            entity.posX + entity.width / 2, entity.posY + entity.height, entity.posZ + entity.width / 2);
                    aabb.expand(bordersize, bordersize, bordersize);
                    RayTraceResult mop0 = aabb.calculateIntercept(pos, var8);

                    if (aabb.contains(pos))
                    {
                        if (0.0D < d || d == 0.0D)
                        {
                            pointedEntity = entity;
                            d = 0.0D;
                        }
                    }
                    else if (mop0 != null)
                    {
                        double d1 = pos.distanceTo(mop0.hitVec);

                        if (d1 < d || d == 0.0D)
                        {
                            pointedEntity = entity;
                            d = d1;
                        }
                    }
                }
            }

            if (pointedEntity != null && (d < calcdist || returnMOP == null))
            {
                returnMOP = new RayTraceResult(pointedEntity);
            }

        }
        return returnMOP;
    }
	
	public static List<BlockPos> getSpongeBlocks(BlockPos pos) {
		List<BlockPos> bubble = new ArrayList<BlockPos>();
		double i = pos.getX(); double j = pos.getY(); double k = pos.getZ();
		
		//up
		for(int x = -3; x <= 3; x++) { for(int y = 0; y <= 2; y++) { for(int z = -3; z <= 3; z++) {
		bubble.add(new BlockPos(i + (double) x, j + (double) y, k + (double) z));}}}
		
		//down
		for(int x = -3; x <= 3; x++) { for(int y = 0; y >= -2; y--) { for(int z = -3; z <= 3; z++) {
		bubble.add(new BlockPos(i + (double) x, j + (double) y, k + (double) z));}}}
		
		//up
		for(int x = -2; x <= 2; x++) { for(int z = -2; z <= 2; z++) {
		bubble.add(new BlockPos(i + (double) x, j + 3.0D, k + (double) z));}}
		
		//down
		for(int x = -2; x <= 2; x++) { for(int z = -2; z <= 2; z++) {
		bubble.add(new BlockPos(i + (double) x, j - 3.0D, k + (double) z));}}

		
		for(int x = -1; x <= 1; x++) { for(int z = -1; z <= 1; z++) {
		bubble.add(new BlockPos(i + (double) x, j + 4.0D, k + (double) z));}}
		
		//down
		for(int x = -1; x <= 1; x++) { for(int z = -1; z <= 1; z++) {
		bubble.add(new BlockPos(i + (double) x, j - 4.0D, k + (double) z));}}
		
		
		return bubble;
	}
	
	public static boolean hasLightSaber(EntityPlayer player) {

		return false;
	}

	public static boolean hasKey(ItemStack stack, String key) {
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(key)) return true;
		else return false;
	}

	public static List<Entity> getList(World worldIn, EntityPlayer player, double reach) {
		
		List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX, player.posY, player.posZ));

		List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + reach, player.posY+ 2.0D, player.posZ + reach));
		List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - reach, player.posY+ 2.0D, player.posZ + reach));
		List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + reach, player.posY+ 2.0D, player.posZ - reach));
		List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - reach, player.posY+ 2.0D, player.posZ - reach));
		
		List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + reach, player.posY- 2.0D, player.posZ + reach));
		List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - reach, player.posY- 2.0D, player.posZ + reach));
		List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + reach, player.posY- 2.0D, player.posZ - reach));
		List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - reach, player.posY- 2.0D, player.posZ - reach));
		
		List<Entity> general = new ArrayList<Entity>();
		general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
		
		return general;

	}
	
	public static List<Entity> getNetherList(World worldIn, BlockPos player, double reach) {
		List<Entity> list1 = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getX(), player.getY(), player.getZ(),player.getX() + reach, player.getY()+ 2.0D, player.getZ() + reach));
		List<Entity> list2 = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getX(), player.getY(), player.getZ(),player.getX() - reach, player.getY()+ 2.0D, player.getZ() + reach));
		List<Entity> list3 = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getX(), player.getY(), player.getZ(),player.getX() + reach, player.getY()+ 2.0D, player.getZ() - reach));
		List<Entity> list4 = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getX(), player.getY(), player.getZ(),player.getX() - reach, player.getY()+ 2.0D, player.getZ() - reach));
		
		List<Entity> list5 = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getX(), player.getY(), player.getZ(),player.getX() + reach, player.getY(), player.getZ() + reach));
		List<Entity> list6 = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getX(), player.getY(), player.getZ(),player.getX() - reach, player.getY(), player.getZ() + reach));
		List<Entity> list7 = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getX(), player.getY(), player.getZ(),player.getX() + reach, player.getY(), player.getZ() - reach));
		List<Entity> list8 = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getX(), player.getY(), player.getZ(),player.getX() - reach, player.getY(), player.getZ() - reach));
		
		List<Entity> general = new ArrayList<Entity>();
		general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
		
		return general;
	}
	
	public static List<Entity> getListChangable(World worldIn, BlockPos pos, Class<? extends Entity> target) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		List<Entity> general = worldIn.getEntitiesWithinAABB(target, new AxisAlignedBB(x, y - 1.0D, z, x + 1.0D, y+ 3.0D, z + 1.0D));		
		return general;
	}
	
	public static List<EntityLivingBase> getEntitiesSurroundings(World worldIn, BlockPos pos) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		List<EntityLivingBase> general = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(x + 20.0D, y + 10.0D, z + 20.0D, x - 20.0D, y - 2.0D, z - 20.0D));		
		return general;
	}
	
	public static boolean isInHand(ItemStack stack, EntityPlayer player) {
		return player.getHeldItemMainhand().getItem() == stack.getItem();
	}

	/**
	 * NBTTagCompound mustn't be null. Check before calling this method
	 * UPDATE: Nevermind, even if you check for null, it will still give you a nullpointer
	 */
	public static boolean hasKeys(ItemStack stack, String[] keys) {
		
		if(!stack.hasTagCompound()) return false;
		NBTTagCompound nbt = stack.getTagCompound();
		int k = 0;
		
		for(int i = 0; i < keys.length; i++) {
			if(!nbt.hasKey(keys[i])) k++;
		}
		
		if(k == 0) return true;
		else return false;
	}
	
	public static void printAll(boolean a1, boolean a2, boolean a3, boolean a4, String a, String b, String c, String d, int f1) {
		System.out.println(a1 + ", " + a2 + ", " + a3 + ", " + a4 + ", " + a + ", " + b + ", " + c + ", " + d + ", " + f1 + ", ");
	}
	
	
}
	
	

