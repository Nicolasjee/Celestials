package poseidon.mod.objects.block.riftblock;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.util.Reference;

public class GuiRiftBlock extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/rift_block.png");
	private final InventoryPlayer player;
	private final TileEntityRiftTeleporter table;
	
	public GuiRiftBlock(InventoryPlayer player, TileEntityRiftTeleporter tile) {
		super(new ContainerRiftBlock(player, tile));
		this.player = player;
		this.table = tile;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	    this.drawDefaultBackground();
	    super.drawScreen(mouseX, mouseY, partialTicks);
	    this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		
		if(!this.table.init) {
			
			this.fontRenderer.drawString("No Saved Coordinates", this.guiLeft - 120, this.guiTop - 27, 4210752);
		}
		
		if(this.table.init) {
			this.fontRenderer.drawString("Saved Coordinates", this.guiLeft - 110, this.guiTop - 27, 4210752);
			
			int y = 10;
			int dY = 14;
			
			int init = 135;
			int start = init;
			int colon = 9;
			int preCoord = 5;
			int aftCoord = 28;
			int next = 15;
			int sum = colon + preCoord + aftCoord + next;
			
			this.fontRenderer.drawString("X", this.guiLeft - start, this.guiTop - y, 26368);
			this.fontRenderer.drawString(":", this.guiLeft - start + colon, this.guiTop - y, 16777215);
			this.fontRenderer.drawString(Integer.toString(this.table.x), this.guiLeft - start + colon + preCoord, this.guiTop - y, 6776681);
			this.fontRenderer.drawString("   ", this.guiLeft - start + colon + preCoord + aftCoord, this.guiTop - y, 16777215);
			start = init - sum;
			
			this.fontRenderer.drawString("Y", this.guiLeft - start, this.guiTop - y, 26368);
			this.fontRenderer.drawString(":", this.guiLeft - start + colon, this.guiTop - y, 16777215);
			this.fontRenderer.drawString(Integer.toString(this.table.y), this.guiLeft - start + colon + preCoord, this.guiTop - y, 6776681);
			this.fontRenderer.drawString("   ", this.guiLeft - start + colon + preCoord + aftCoord, this.guiTop - y, 16777215);
			start = init - sum*2;
			
			this.fontRenderer.drawString("Z", this.guiLeft - start, this.guiTop - y, 26368);
			this.fontRenderer.drawString(":", this.guiLeft - start + colon, this.guiTop - y, 16777215);
			this.fontRenderer.drawString(Integer.toString(this.table.z), this.guiLeft - start + colon + preCoord, this.guiTop - y, 6776681);
			
			if(this.table.dimension == 1) {
				this.fontRenderer.drawString("The End", this.guiLeft - 110, this.guiTop - y + dY, 11118888);
			}
			
			if(this.table.dimension == 0) {
				this.fontRenderer.drawString("Overworld", this.guiLeft - init + sum - 7, this.guiTop - y + dY, 3835206);
			}

			if(this.table.dimension == -1) {
				this.fontRenderer.drawString("Nether", this.guiLeft - init + sum -1, this.guiTop - y + dY, 16272442);
			}
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F,  1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		


	}
	
}
