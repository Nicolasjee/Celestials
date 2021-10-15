package poseidon.mod.proxy;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;
import poseidon.mod.objects.entities.CustomXP;
import poseidon.mod.util.Reference;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class ClientProxy extends CommonProxy {
	
	public double sd = 0.004D;
	public double st = 0.004D;
	public double sp = 0.008D;
	public boolean b = false;
	

	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	@Override
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		Minecraft.getMinecraft().mouseHelper = Main.instance.mouseHelperAI;
		super.preInit(event);
	}
	
//	@Override
//	public void registerModel(Item item, int metadata)  {
//		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
//	}

	@Override
	public void init(FMLInitializationEvent event) {

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@Override
	public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) {
		return (ctx.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayerEntityFromContext(ctx));
	}


	public void generateCharmParticleDepth(Entity theEntity) {
		EntityPlayer playerIn = (EntityPlayer) theEntity;

		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY + 1.0D;
		double z = playerIn.posZ;
		double d0 = rand.nextGaussian() * 0.02D;
		double d1 = rand.nextGaussian() * 0.02D;
		double d2 = rand.nextGaussian() * 0.02D;
		double d3 = 10.0D;

		theEntity.world.spawnParticle(EnumParticleTypes.WATER_DROP,
				x + (double) (rand.nextFloat() * playerIn.width * 2.0F) - (double) playerIn.width - d0 * 10.0D,
				y + (double) (rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D,
				z + (double) rand.nextFloat() * playerIn.width * 2.0F - (double) playerIn.width - d2 * 10.0D, d0, d1,
				d2);

	}

	public void generateCharmParticleFire(Entity theEntity) {
		EntityPlayer playerIn = (EntityPlayer) theEntity;

		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY + 1.0D;
		double z = playerIn.posZ;
		double d0 = rand.nextGaussian() * 0.02D;
		double d1 = rand.nextGaussian() * 0.02D;
		double d2 = rand.nextGaussian() * 0.02D;
		double d3 = 10.0D;

		theEntity.world.spawnParticle(EnumParticleTypes.FLAME,
				x + (double) (rand.nextFloat() * playerIn.width * 2.0F) - (double) playerIn.width - d0 * 10.0D,
				y + (double) (rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D,
				z + (double) rand.nextFloat() * playerIn.width * 2.0F - (double) playerIn.width - d2 * 10.0D, d0, d1,
				d2);

	}

	public void generateCharmParticleProj(Entity theEntity) {
		EntityPlayer playerIn = (EntityPlayer) theEntity;

		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY + 1.0D;
		double z = playerIn.posZ;
		double d0 = rand.nextGaussian() * 0.02D;
		double d1 = rand.nextGaussian() * 0.02D;
		double d2 = rand.nextGaussian() * 0.02D;
		double d3 = 10.0D;

		theEntity.world.spawnParticle(EnumParticleTypes.CRIT,
				x + (double) (rand.nextFloat() * playerIn.width * 2.0F) - (double) playerIn.width - d0 * 10.0D,
				y + (double) (rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D,
				z + (double) rand.nextFloat() * playerIn.width * 2.0F - (double) playerIn.width - d2 * 10.0D, d0, d1,
				d2);

	}

	public void generateCharmParticleGlide(Entity theEntity) {
		EntityPlayer playerIn = (EntityPlayer) theEntity;

		Random rand = new Random();
		double x = playerIn.posX;
		double y = playerIn.posY + 1.0D;
		double z = playerIn.posZ;
		double d0 = rand.nextGaussian() * 0.02D;
		double d1 = rand.nextGaussian() * 0.02D;
		double d2 = rand.nextGaussian() * 0.02D;
		double d3 = 10.0D;

		theEntity.world.spawnParticle(EnumParticleTypes.CLOUD,
				x + (double) (rand.nextFloat() * playerIn.width * 2.0F) - (double) playerIn.width - d0 * 10.0D,
				y + (double) (rand.nextFloat() * (playerIn.height - 2.4D)) - d1 * 10.0D,
				z + (double) rand.nextFloat() * playerIn.width * 2.0F - (double) playerIn.width - d2 * 10.0D, d0, d1,
				d2);

	}

	@Override
	public void playSoundBlock(BlockPos pos, World world, SoundEvent event, float pitch, float vol) {
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), event, SoundCategory.AMBIENT, vol, pitch);
	}
	
	@Override
	public void stasis(BlockPos pos, World world) {
		world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundsHandler.STASIS, SoundCategory.AMBIENT, 1.0F, 1.0F, true);
	}

	//TODO ???
	@Override
	public void dropItem(BlockPos pos, World world, Item item, int amount) {
		
		if(amount < 0) return;
		
		int stacks = amount / 64;
		int left = 0;
		
		if(stacks != 0) left = amount - stacks*64;
		
		if(!world.isRemote) {
			int i = 0;
			while(i < stacks) {
				EntityItem drop = new EntityItem(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, new ItemStack(item, 64));
				world.spawnEntity(drop);
				i++;
			}

			if(left != 0) {
				EntityItem drop2 = new EntityItem(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, new ItemStack(item, left));
				world.spawnEntity(drop2);
			}
		}
		
	}
	


	@Override
	public void spawnExp(World worldIn, BlockPos pos) {
		CustomXP entity = new CustomXP(worldIn, pos.getX(), pos.getY() + 1.0D, pos.getZ(), 5000);
		worldIn.spawnEntity(entity);
	}

	@Override
	public void particle(World worldIn, CustomEndRod rod) {
		Minecraft.getMinecraft().effectRenderer.addEffect(rod);
	}
	
}






















