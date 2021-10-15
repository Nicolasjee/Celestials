package poseidon.mod.util.handlers;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.EnchantmentInit;
import poseidon.mod.init.EntityInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.init.PotionInit;
import poseidon.mod.networking.MessageExtendedReachAttack;
import poseidon.mod.networking.MessageGeneral;
import poseidon.mod.objects.block.general.chest.obsidian.RenderObsidianChest;
import poseidon.mod.objects.block.general.chest.obsidian.TileEntityObsidianChest;
import poseidon.mod.objects.block.general.chest.sand.RenderSandChest;
import poseidon.mod.objects.block.general.chest.sand.TileEntitySandChest;
import poseidon.mod.objects.items.wands.ItemFlyStaff;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Reference;
import poseidon.mod.util.Utilities;
import poseidon.mod.util.handlers.registry.CommandHandler;
import poseidon.mod.util.handlers.registry.GuiHandler;
import poseidon.mod.util.handlers.registry.RenderHandler;
import poseidon.mod.util.handlers.registry.SoundsHandler;
import poseidon.mod.util.handlers.registry.TileEntityHandler;
import poseidon.mod.util.helpers.TorchPlaceUtil;
import poseidon.mod.util.helpers.diamondfinder.DiamondFinder;
import poseidon.mod.util.interfaces.IExtendedReach;
import poseidon.mod.util.interfaces.IHasModel;
import poseidon.mod.world.gen.WorldGenCustomOres;
import poseidon.mod.world.gen.WorldGenCustomStructures;

@EventBusSubscriber
public class RegistryHandler implements TeslaProperties {
	
	public static final SoundEvent PIRATE_CREW = new SoundEvent(new ResourceLocation(Reference.MODID, "item.effects.immigrant")).setRegistryName(new ResourceLocation(Reference.MODID, "item.effects.immigrant"));
	public static int tick = 0;
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
		
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObsidianChest.class, new RenderObsidianChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySandChest.class, new RenderSandChest());
	}
	
	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().registerAll(EnchantmentInit.ENCHANTMENTS.toArray(new Enchantment[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		
		//Main.proxy.registerModel(Item.getItemFromBlock(BlockInit.OBSIDIAN_CHEST), 0);
		//Main.proxy.registerModel(Item.getItemFromBlock(BlockInit.SAND_CHEST), 0);
		for(Item item : ItemInit.ITEMS) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
			
		}
		for(Block block : BlockInit.BLOCKS) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	 	
	@SubscribeEvent
	public static void itemEvent(PlayerEvent.Visibility event) {
		if(event.getEntityPlayer() != null) {
			EntityPlayer player = event.getEntityPlayer();
			if(player.getHeldItemMainhand().getItem() == ItemInit.TIME_CHANGER)	{
				ItemStack stack = player.getHeldItemMainhand();
				if(stack.hasTagCompound()) {
					NBTTagCompound nbt = stack.getTagCompound();
					if(nbt.hasKey("CooldownA")) {
						if(nbt.getBoolean("CooldownA")) {
							event.modifyVisibility(0.00D);
							if(event.isCancelable()) event.setCanceled(true);
						}
					}
				}
			}
			

			
		}
	}
	
	//@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		
		
	    if(event.getEntity() instanceof EntityZombie && !event.getEntity().world.isRemote) {

	    	EntityMob entity = (EntityMob) event.getEntity();
			entity.targetTasks.taskEntries.clear();
			entity.targetTasks.addTask(1, new EntityAINearestAttackableTarget(entity, EntityPig.class, false));
	    	
	    }
	}
	
	//@SubscribeEvent
	public static void updateLiving(LivingUpdateEvent event) {
		
	    if(event.getEntity() instanceof EntityMob && !event.getEntity().world.isRemote) {

	    	EntityMob entity = (EntityMob) event.getEntity();
			
			Set<EntityAITaskEntry> set = entity.targetTasks.taskEntries;
			Iterator<EntityAITaskEntry> it = set.iterator();
			
			while(it.hasNext()) {
				
				Object i = it.next();
				
				if(i instanceof EntityAITaskEntry) {
					EntityAITaskEntry e = (EntityAITaskEntry) i;
					
					if(e.action instanceof EntityAINearestAttackableTarget) {
						EntityAINearestAttackableTarget ai = (EntityAINearestAttackableTarget) e.action;
						
						if(!ai.shouldContinueExecuting()) {
							entity.targetTasks.taskEntries.clear();
							entity.targetTasks.addTask(1, new EntityAINearestAttackableTarget(entity, EntityPig.class, false));
						}
						
					}
					
				}
		
			}
			

	    	
	    }
		
	}
	
	@SubscribeEvent
	public static void attackTarget(LivingSetAttackTargetEvent event) {
		if(event.getEntity() != null && event.getTarget() != null && event.getTarget() instanceof EntityPlayer && event.getEntity() instanceof EntityMob && !event.getEntity().world.isRemote) {
			EntityPlayer player = (EntityPlayer) event.getTarget();
			EntityMob entity = (EntityMob) event.getEntity();
			ItemStack s = ItemStack.EMPTY;
			World worldIn = player.world;
			
			
				int t = 0;
				
			for(int i = 0; i < 36; i ++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if(check.getItem() == ItemInit.TIME_CHANGER) {
					s = check;
					t = 1;
				}
			}
			if(!s.isEmpty()) {
				if(t == 1) {
					if(s.hasTagCompound() && s.getTagCompound().hasKey("CooldownA") && s.getTagCompound().getBoolean("CooldownA")) {
						if(entity instanceof EntityEnderman) entity.setAttackTarget(null);
						entity.setAttackTarget(null);
					}
				}
			}


			
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void jumpEvent(LivingJumpEvent event) {

		if(event.getEntity() != null && event.getEntity() instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) event.getEntity();
			ItemStack held = player.getHeldItemMainhand();
			ItemStack heldOff = player.getHeldItemOffhand();
			boolean hasWarrant = false;
			
//			if(EventHelper.hasBootsWithDoubleJump(player) && player.inventory.armorInventory.get(0).hasTagCompound()) {
//				
//				ItemStack stack = player.inventory.armorInventory.get(0);
//				NBTTagCompound nbt = stack.getTagCompound();
//				
//				if(nbt.hasKey("Cooldown") && nbt.hasKey("hasJumped")) {
//					
//					int c = nbt.getInteger("Cooldown");
//					boolean b = nbt.getBoolean("hasJumped");
//					
//					nbt.setBoolean("hasJumped", true);
//					
//				}
//				
//				
//			}
			
			
			if(held.getItem() == new ItemStack(ItemInit.FALL_CHARM).getItem() || heldOff.getItem() == new ItemStack(ItemInit.FALL_CHARM).getItem()) {
				if(held.hasTagCompound() || heldOff.hasTagCompound()) {	
					if(new ItemStack(ItemInit.FALL_CHARM).getItem() == player.getHeldItemMainhand().getItem()) {		
						ItemStack remove = player.getHeldItemMainhand();		
						if(remove.hasTagCompound()) {			
							if(remove.getTagCompound().getBoolean("Activated")) {			
								remove.getTagCompound().setBoolean("Activated", false);			
							}							
						}						
					}				
					if(new ItemStack(ItemInit.FALL_CHARM).getItem() == player.getHeldItemOffhand().getItem() || hasWarrant) {						
						ItemStack remove = player.getHeldItemOffhand();						
						if(remove.hasTagCompound()) {							
							if(remove.getTagCompound().getBoolean("Activated")) {								
								remove.getTagCompound().setBoolean("Activated", false);								
							}							
						}						
					}					
				}				
			}		


			
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void knockEvent(LivingKnockBackEvent event) {
		if(event.getAttacker() != null && event.getEntity() != null && event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			
			if(EventHelper.hasItem(ItemInit.WARRANT, player)) event.setStrength(0.0F);
			//if(player.getHeldItemMainhand().getItem() == ItemInit.INFINITY_BLADE) event.setStrength(0.0F);
			
			if(event.getAttacker() instanceof EntitySkeleton) {
				ItemStack stack = new ItemStack(ItemInit.PROJECTILE_CHARM);

				
				for(int i = 0; i < 36; i ++) {
					ItemStack check = player.inventory.getStackInSlot(i);
					if(check.getItem() == stack.getItem()) {
						event.setStrength(0.0F);
						check.damageItem(1, player);
					}
				}
			}
			
			
			//Time changer extra
			if(player.getHeldItemMainhand().getItem() == ItemInit.TIME_CHANGER)	{
				ItemStack stack = player.getHeldItemMainhand();
				if(stack.hasTagCompound()) {
					NBTTagCompound nbt = stack.getTagCompound();
					if(nbt.hasKey("CooldownA")) {
						if(nbt.getBoolean("CooldownA")) {
							event.setStrength(0.0F);
							if(event.isCancelable()) event.setCanceled(true);
						}
					}
				}
			}
				

				
			
			
			
		}
	}

	
	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void charmEvent(LivingHurtEvent event) {

		if(event.getEntity() != null && event.getEntity() instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) event.getEntity();
			ItemStack ls = player.getHeldItemMainhand();
			ItemStack fs = player.getHeldItemOffhand();
			
			BlockPos down = player.getPosition().down();
			Block block = player.world.getBlockState(down).getBlock();
			Block block1 = player.world.getBlockState(player.getPosition()).getBlock();

			boolean con = false;
			ItemStack deptCharm = new ItemStack(ItemInit.DEPTH_STRIDER_CHARM);
			ItemStack projCharm = new ItemStack(ItemInit.PROJECTILE_CHARM);
			
			for(int i = 0; i < 36; i ++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if(check.getItem() == projCharm.getItem() && event.getSource().getDamageType() == "arrow") {
					event.setAmount(0.0F);
					check.damageItem(1, player);
				}

				if(((check.getItem() == ItemInit.FALL_CHARM) && event.getSource().getDamageType() == "fall")) {
					event.setAmount(0.0F);
					check.damageItem(10, player);
				}
				
				if(check.getItem() == new ItemStack(ItemInit.FIRE_PROTECTION_CHARM).getItem() && (event.getSource().getDamageType() == "fire" || event.getSource().getDamageType() == "lava")) {
					event.setAmount(0.0F);
					check.damageItem(1, player);
				}
			}

			if(EventHelper.hasItem(ItemInit.WARRANT, player)) event.setAmount(0.0F);
			
			if(event.getSource().getDamageType() == "fall" && ls.hasTagCompound() && ls.getItem() == ItemInit.TESLA_INFUSER) {
			
				NBTTagCompound nbt = ls.getTagCompound();
				
				int damage = 0;
				if(event.getAmount() < 30.0F) damage = FALLLOW;
				if(event.getAmount() > 30.0F && event.getAmount() < 70.0F) damage = (int) (event.getAmount() / 10) * FALLLOW;
				if(event.getAmount() > 70.0F) damage = (int) (event.getAmount() / 10) * FALLMED;
			
				boolean negate = false;
				
				if(nbt.hasKey("DataArray")) {
					if(TeslaProperties.sufficientIntegerDurability(damage, ls)) negate = true;
				}
				
				if(negate) {
					event.setAmount(0.0F);
					int[] s = nbt.getIntArray("DataArray");
					nbt.setIntArray("DataArray", new int[] {s[0], s[1], s[2], s[3], s[4], s[5] - damage});
				}
				
			
				
				//player.getHeldItemMainhand().setItemDamage(player.getHeldItemMainhand().getItemDamage() - damage);
			}
			
			
			
			if(ls.getItem() == ItemInit.VELOCITYCHANGER && ItemFlyStaff.isAmmo(fs, new ItemStack(ItemInit.ELYTRON_DUST), new ItemStack(Items.GUNPOWDER), new ItemStack(Items.GLOWSTONE_DUST))) {
				if(event.getSource().getDamageType() == "fall") {
					event.setAmount(event.getAmount() / 10);
				}
			}
			
			if(player.getHeldItemMainhand().getItem() == ItemInit.TIME_CHANGER)	{
				ItemStack stack = player.getHeldItemMainhand();
				if(stack.hasTagCompound()) {
					NBTTagCompound nbt = stack.getTagCompound();
					if(nbt.hasKey("CooldownA")) {
						if(nbt.getBoolean("CooldownA")) {
							event.setAmount(0.0F);
						}
					}
				}
			}

		    

			
			
		}
	}
	
	@SubscribeEvent
	public static void entityEvent(EntityEvent.CanUpdate e) {
		if(e.getEntity() instanceof EntityItem) {
			if(((EntityItem)e.getEntity()).getItem().getItem() == ItemInit.VELOCITYCHANGER) {
				if(!e.getEntity().world.isRemote) {
					ParticleUtil.quickCircle((WorldServer)e.getEntity().world, e.getEntity().getPosition(), EnumParticleTypes.END_ROD);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void playerEvent(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		BlockPos pos = player.getPosition();
		World worldIn = player.world;
		
		Iterable<BlockPos> posses = pos.getAllInBox(pos.north(10).east(10).up(2), pos.south(10).west(10).down(2));
		
		for(BlockPos check : posses) {
			
			if(worldIn.getBlockState(check) == BlockInit.NETHER_REACTOR_CORE_ACTIVATED.getDefaultState()) {
				
				List<Entity> entities = Utilities.getList(worldIn, player, 10.0D);
					
				for(Entity bip : entities) {
					if(bip instanceof EntityMob && bip instanceof EntityPigZombie) {
						EntityPigZombie zomb = (EntityPigZombie) bip;
						zomb.setAttackTarget(player);
					}
				}
			}
		}
		
		if(player.isPotionActive(PotionInit.TELEPORT)) {
		
			
			
		}	
		
	}
	

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void deathEvent(LivingDeathEvent event) {
		EntityPlayer player = null;
		EntityPlayer player2 = null;
		
		if(event.getEntity() instanceof EntityPlayer) {player = (EntityPlayer) event.getEntity();}
		ItemStack stack = new ItemStack(ItemInit.STASIS);
		
		if(player != null && player instanceof EntityPlayer) {
			if(player.inventory.hasItemStack(stack)) {
				
				int slot = player.inventory.getSlotFor(stack);

				event.isCanceled();
				event.setCanceled(true);
					
					
		
				player.setHealth(player.getMaxHealth());
				player.inventory.getStackInSlot(slot).shrink(1);
				player.addExperience(120);
					
				double d0 = player.getPosition().getX(); double d1 = player.getPosition().getY(); double d2 = player.getPosition().getZ();
					
				if(!player.world.isRemote) {
					Main.proxy.playSoundBlock(player.getPosition(), player.world, SoundsHandler.STASIS, 1.0F, 1.0F);
					ParticleUtil.mirror((WorldServer)player.world, player.getPosition(), EnumParticleTypes.TOTEM);
				}
				
				
			}
			
			if(event.getSource() == DamageSource.LAVA) Main.proxy.playSoundBlock(player.getPosition(), player.world, SoundsHandler.WILLHOT, 1.0F, 1.0F);
			
		}
		
		if(event.getEntity() != null && event.getEntity() instanceof EntityRabbit) {
			if(!event.getEntity().world.isRemote) {
				event.getEntity().entityDropItem(new ItemStack(Items.RABBIT_FOOT), (float) event.getEntity().getYOffset());
			}
		}
		
		if(event.getEntity() != null && event.getEntity() instanceof EntityWitherSkeleton) {
			if(!event.getEntity().world.isRemote) {
				
				Random rand = new Random();
				int num = 1 + rand.nextInt(700);
				Block block = null;
				
				if(num <= 100) block = BlockInit.NETHER_CRATE;
				
				if(block != null) {
					event.getEntity().entityDropItem(new ItemStack(Item.getItemFromBlock(block)), (float) event.getEntity().getYOffset());
				}
			}
		}



		

	}
		
	@SubscribeEvent
	public static void onEntityRightClick(PlayerInteractEvent.EntityInteract event) {
		//System.out.println("Health: " + event.getEntityLiving().getHealth());
		if(event.getEntityPlayer() != null) {
			EntityPlayer player = event.getEntityPlayer();
			
			if(event.getTarget() != null) {
				
				ItemStack stack = player.getHeldItemMainhand();
				
				Entity t = event.getTarget();
				if((stack.getItem() == Items.WHEAT && (t instanceof EntityCow || t instanceof EntitySheep || t instanceof EntityHorse))
						|| (Utilities.isMeat(stack) && (t instanceof EntityWolf))
						|| (Utilities.isSeed(stack) && (t instanceof EntityChicken))
						|| ((stack.getItem() == Items.CARROT || stack.getItem() == Items.POTATO) && (t instanceof EntityPig || t instanceof EntityParrot))) {
					Main.proxy.playSoundBlock(player.getPosition(), event.getWorld(), SoundEvents.ENTITY_PLAYER_BURP, 1.0F, 0.05F);
				}

				


			}	
		}
	}
	
	@SubscribeEvent
	public static void onEntityPickupXP(PlayerPickupXpEvent event) {
		
	}
	
	@SubscribeEvent
	public static void onRight(PlayerInteractEvent.RightClickItem event) {
		
		EntityPlayer player = event.getEntityPlayer();
		int m = Reference.maxLevelPerception;
		int l = Reference.soundLoopPerception;
		
		ItemStack stack = player.getHeldItemMainhand();
		NBTTagList enchlist2 = stack.getEnchantmentTagList();
		
	    for(int i = 0; i < enchlist2.tagCount(); i++) {
	    	
	    	if (enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(EnchantmentInit.ILLUMINATION)) {
	    		TorchPlaceUtil.execute(event.getEntityPlayer(), event.getWorld());
	        }
	    	
	    	if(enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(EnchantmentInit.PERCEPTION)) {
	    		//if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Level")) {
	    			
	    			NBTTagCompound nbt = stack.getTagCompound();
	    			
	    				
	    				BlockPos pos = player.getPosition();
	    				Iterable<BlockPos> posses = pos.getAllInBox(pos.north(10).east(10).up(6), pos.south(10).west(10).down(6));
	    				
	    				for(BlockPos check : posses) {
	    					if(player.world.getBlockState(check) == Blocks.DIAMOND_ORE.getDefaultState()) {
	    						
	    						DiamondFinder.execute(player.world, player, check);
	    						player.getCooldownTracker().setCooldown(stack.getItem(), 2000);
	    						stack.damageItem(100, player);
	    					}
	    				}
	    				
	    				//nbt.setInteger("Level", 0);
	    				
	    			//}
	    			
	    		
	    	}
	    	
	    }
	    
	    if(stack.getItem() == Item.getItemFromBlock(Blocks.TNT)) {
	    	EntityTNTPrimed tnt = new EntityTNTPrimed(player.world, player.posX, player.posY + 1.0D, player.posZ, player);
	    	player.world.spawnEntity(tnt);
	    	Vec3d vec = player.getLook(0.5F);
	    	tnt.addVelocity(vec.x * 0.6F, vec.y*  0.5F , vec.z* 0.6F);
	    	stack.shrink(1);
	    }
	    
	    
	}
	
	@SubscribeEvent
	public static void onBlockPlace(BlockEvent.PlaceEvent e) {
		
		PropertyBool WET = PropertyBool.create("wet");
		
		Block block = e.getWorld().getBlockState(e.getPos()).getBlock();
		double x = e.getPos().getX(); double y = e.getPos().getY(); double z = e.getPos().getZ();
		World w = e.getWorld();
		EntityPlayer player = e.getPlayer();
	
		if(w.provider.getDimension() == -1 &&  
				e.getState() == Blocks.SPONGE.getDefaultState().withProperty(WET, true)) {
			
			w.setBlockState(e.getPos(), Blocks.SPONGE.getDefaultState());
			if(!w.isRemote) {
				ParticleUtil.sponge((WorldServer) w, e.getPos());
				Main.proxy.playSoundBlock(e.getPos(), e.getWorld(), SoundEvents.BLOCK_LAVA_EXTINGUISH, 1.0F, 1.0F);
			}

				
			
		}
		
	}

	@SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void onEvent(MouseEvent event)
    {
        // ensure custom MouseHelper is active
        Minecraft mc = Minecraft.getMinecraft();
        mc.mouseHelper = Main.instance.mouseHelperAI;
       
       
        if (event.getButton() == 0 && event.isButtonstate()) {
            EntityPlayer thePlayer = mc.player;
            if (thePlayer != null) {
                ItemStack itemstack = thePlayer.getHeldItemMainhand();
                IExtendedReach ieri;
                if (itemstack != null) {
                    if (itemstack.getItem() instanceof IExtendedReach) {
                        ieri = (IExtendedReach) itemstack.getItem();
                    	} else {
                        ieri = null;
                    }

                    if (ieri != null) {
                        float reach = ieri.getReach();
                        RayTraceResult mov = Utilities.getMouseOverExtended(reach);

                        if (mov != null) {
                            if (mov.entityHit != null && mov.entityHit.hurtResistantTime == 0) {
                                if (mov.entityHit != thePlayer) {
                                    Main.network.sendToServer(new MessageExtendedReachAttack(mov.entityHit.getEntityId()));
                                }
                            }
                        }
                    }
                }
                
            }
        }
        
        if(mc.player.getHeldItemMainhand().getItem() == ItemInit.KILL_STICK) {
        	RayTraceResult mov = Utilities.getMouseOverExtended(80.0F);
        	ItemStack held = mc.player.getHeldItemMainhand();
        	EntityPlayer player = mc.player;
        	
        	if(held.hasTagCompound()) {
        		NBTTagCompound nbt = held.getTagCompound();
        		if(nbt.getIntArray("Active")[0] == 1) {
		            if (mov != null) {
		                if (mov.entityHit != null && mov.entityHit.hurtResistantTime == 0) {
		                    if (mov.entityHit != mc.player) {
		                        Main.network.sendToServer(new MessageGeneral(mov.entityHit.getEntityId()));
		                    }
		                }
		            }
        		}
        	}
        }
        
        
    }
	
	public static void preInitRegistries() {
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres() , 10);
		EntityInit.registerEntities();
		RenderHandler.registerEntityRenders();
		
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 1);
		PotionInit.registerPotions();
		EntityInit.registerSpawns();
		
		
		
		//DimensionInit.registerDimension();
	}
	
	public static void initRegistries() {
		SoundsHandler.registerSounds();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public static void postInitRegistries() {

	}
	
	public static void onServerInit(FMLServerStartingEvent event) {
		CommandHandler.register(event);
	}
	
}

