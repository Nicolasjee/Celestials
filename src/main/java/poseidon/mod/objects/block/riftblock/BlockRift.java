package poseidon.mod.objects.block.riftblock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.client.commands.util.Teleport;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Reference;
import poseidon.mod.util.interfaces.IHasModel;

public class BlockRift extends Block implements IHasModel, ITileEntityProvider {
	
	public BlockRift(String name) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		
		setLightLevel(0.0F);
		setHardness(2.0F);
		setResistance(5.0F);
		
		setHarvestLevel("pickaxe", 2);
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack held = playerIn.getHeldItemMainhand();
		
		if(worldIn.getTileEntity(pos) instanceof TileEntityRiftTeleporter && isValidItem(held.getItem()) && held.hasTagCompound()) {
			TileEntityRiftTeleporter rift = (TileEntityRiftTeleporter) worldIn.getTileEntity(pos);
			NBTTagCompound nbt = held.getTagCompound();
			
			if(!rift.init && nbt.hasKey("Coords")) {
				int[] a = nbt.getIntArray("Coords");
				rift.x = a[0];
				rift.y = a[1];
				rift.z = a[2];
				rift.dimension = a[3];
				rift.init = true;
				rift.send = true;
				
				worldIn.setBlockState(pos.up(), BlockInit.RIFT_AIR.getDefaultState());
				worldIn.setBlockState(pos.up(2), BlockInit.RIFT_AIR.getDefaultState());
				worldIn.setBlockState(pos.up(3), BlockInit.RIFT_AIR.getDefaultState());
				worldIn.setBlockState(pos.up(4), BlockInit.RIFT_AIR.getDefaultState());
				
				Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_ITEMFRAME_PLACE, 1.0F, 0.3F);
				held.shrink(1);
				
				return true;
			}
		}
		
		if(!worldIn.isRemote && !playerIn.isSneaking()) {
			playerIn.openGui(Main.instance, Reference.RIFT_BLOCK, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	private boolean isValidItem(Item item) {
		if(item == ItemInit.BLOCK_SAVER_END || item == ItemInit.BLOCK_SAVER_OVERWORLD || item == ItemInit.BLOCK_SAVER_NETHER) return true;
		else return false;
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(worldIn.getTileEntity(pos) instanceof TileEntityRiftTeleporter) {
			TileEntityRiftTeleporter rift = (TileEntityRiftTeleporter) worldIn.getTileEntity(pos);
			if(rift.init && !worldIn.isRemote && entityIn instanceof EntityPlayer && rift.isPowered) {
				
				if(rift.dimension == entityIn.dimension) {
					((EntityPlayer)entityIn).setPosition(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					((EntityPlayer)entityIn).attemptTeleport(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					ParticleUtil.riftOpening((WorldServer)worldIn, new BlockPos(rift.x, rift.y + 1.0D, rift.z), EnumParticleTypes.DRAGON_BREATH);
					((EntityPlayer)entityIn).setPositionAndUpdate(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					return;
				}
				
				if(rift.dimension != entityIn.dimension) {
					EntityPlayer player = (EntityPlayer)entityIn;
					EntityPlayerMP mp = (EntityPlayerMP) player;
					MinecraftServer server = player.getEntityWorld().getMinecraftServer();
					WorldServer worldServer = server.getWorld(rift.dimension);
					
					if(worldServer == null || server == null) {
						return;
					}
					
					((EntityPlayer)entityIn).setPosition(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					((EntityPlayer)entityIn).attemptTeleport(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					ParticleUtil.riftOpening((WorldServer)worldIn, new BlockPos(rift.x, rift.y + 1.0D, rift.z), EnumParticleTypes.DRAGON_BREATH);
					
					worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(mp, rift.dimension, new Teleport(worldServer, rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D));
					player.setPositionAndUpdate(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
				}
			}
		}
	}

	
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRiftTeleporter();
	}
	
	
}
