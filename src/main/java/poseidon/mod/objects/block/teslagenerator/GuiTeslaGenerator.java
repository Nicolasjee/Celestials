package poseidon.mod.objects.block.teslagenerator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.Reference;

public class GuiTeslaGenerator extends GuiContainer implements TeslaProperties {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/flux_inductor.png");
	private final InventoryPlayer player;
	private final TileEntityTeslaGenerator table;
	
	public GuiTeslaGenerator(InventoryPlayer player, TileEntityTeslaGenerator tile) {
		super(new ContainerTeslaGenerator(player, tile));
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
		String tileName = this.table.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 5, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 100 + 2, 4210752);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F,  1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int k = this.getCharge(4);
		if(k != 20) this.drawTexturedModalRect(this.guiLeft + 118, this.guiTop + 37 + 10 - k, 177, 0, 4, 1 + k);
		if(k == 20) this.drawTexturedModalRect(this.guiLeft + 118, this.guiTop + 37 + 11 - k, 177, 0, 4, 1 + k);
		
		if((table.slot(1).getItem() == table.chargeItem.getItem() && table.slot(0).isEmpty()) && table.charge < table.maxCharge && table.slot(1).hasTagCompound() && table.slot(1).getTagCompound().hasKey("Durability") && table.slot(1).getTagCompound().getInteger("Durability") > 0) {
			this.drawTexturedModalRect(this.guiLeft + 118, this.guiTop + 23, 177, 13, 5, 7);
			powerDraw(table.progress);powerDraw(table.progress2);powerDraw(table.progress3);powerDraw(table.progress4);powerDraw(table.progress5); powerDraw(table.progress6);
			powerDraw(table.progress7);
			powerDraw(table.progress8); powerDraw(table.progress9);
			powerDrawUnder(table.progress);powerDrawUnder(table.progress2);powerDrawUnder(table.progress3);powerDrawUnder(table.progress4);powerDrawUnder(table.progress5); powerDrawUnder(table.progress6);
			powerDrawUnder(table.progress7); powerDrawUnder(table.progress8); powerDrawUnder(table.progress9);
		}
		
		if(!(table.slot(1).getItem() == table.chargeItem.getItem() && (table.slot(0).isEmpty()) || table.charge == table.maxCharge || (table.slot(1).hasTagCompound() && table.slot(1).getTagCompound().hasKey("Durability") && !(table.slot(1).getTagCompound().getInteger("Durability") > 0)))) {
			this.drawTexturedModalRect(this.guiLeft + 118, this.guiTop + 23, 177, 20, 5, 7);
		}
		
		if(table.charge == table.maxCharge) this.drawTexturedModalRect(this.guiLeft + 117, this.guiTop + 33, 176, 30, 25, 19);
		
		if(table.slot(0).getItem() == ItemInit.TESLA_INFUSER) {
			int dur = table.itemDur;
			int i = getMath(dur);
			this.drawTexturedModalRect(this.guiLeft + 68, this.guiTop + 60, 176, 28, 40 - i, 1);
		}

	}
	
	private int getMath(int dur) {
		int barDivide = durability / 40; // elke pixel == 20 durability: 40 PIXELS
		int sub = dur % barDivide;
		int dur2 = dur - sub;
		return dur2 / barDivide;
	}
	
	private void powerDraw(int i) {
		//11 3 5 9 1
		if(i <= 10) this.drawTexturedModalRect(this.guiLeft + 139 - i, this.guiTop + 39, 								176, 27, 1, 1);
		if(i > 10 && i <= 13) this.drawTexturedModalRect(this.guiLeft + 139 - 10, this.guiTop + 39 + 11 - i, 			176, 27, 1, 1);
		if(i > 13 && i <= 18) this.drawTexturedModalRect(this.guiLeft + 139 - 11, this.guiTop + 39 + 11 + 1 - i,	 	176, 27, 1, 1);
		if(i > 18 && i <= 26) this.drawTexturedModalRect(this.guiLeft + 127 + 19 - i, this.guiTop + 39 - 6,	 			176, 27, 1, 1);
		if(i > 26 && i <= 28) this.drawTexturedModalRect(this.guiLeft + 120 + 27 - i, this.guiTop + 39 - 5,	 			176, 27, 1, 1);
	}
	
	private void powerDrawUnder(int i) {
		if(i <= 10) this.drawTexturedModalRect(this.guiLeft + 139 - i, this.guiTop + 45, 								176, 27, 1, 1);
		if(i > 10 && i <= 13) this.drawTexturedModalRect(this.guiLeft + 139 - 10, this.guiTop + 45 - 11 + i, 			176, 27, 1, 1);
		if(i > 13 && i <= 18) this.drawTexturedModalRect(this.guiLeft + 139 - 11, this.guiTop + 45 - 12 + i,	 	176, 27, 1, 1);
		if(i > 18 && i <= 26) this.drawTexturedModalRect(this.guiLeft + 127 + 19 - i, this.guiTop + 45 + 6,	 			176, 27, 1, 1);
		if(i > 26 && i <= 28) this.drawTexturedModalRect(this.guiLeft + 120 + 27 - i, this.guiTop + 45 + 5,	 			176, 27, 1, 1);
	}
	
	private int getCharge(int pixels) {
		int i = this.table.getCharge();
		int q = 200;
		// i = m <=> i - m = divisable by q
		int m = i % q;
		return (i - m) / q;

	}
	

	
	
}
