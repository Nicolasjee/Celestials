package poseidon.mod.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelCastiel extends ModelBase {
    public ModelRenderer PlayerLeftArm;
    public ModelRenderer PlayerHead;
    public ModelRenderer PlayerBody;
    public ModelRenderer PlayerRightArm;
    public ModelRenderer PlayerRightLeg;
    public ModelRenderer PlayerLeftLeg;

    public ModelCastiel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.PlayerHead = new ModelRenderer(this, 0, 0);
        this.PlayerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.PlayerHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.PlayerLeftArm = new ModelRenderer(this, 40, 16);
        this.PlayerLeftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.PlayerLeftArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.PlayerBody = new ModelRenderer(this, 16, 16);
        this.PlayerBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.PlayerBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.PlayerRightArm = new ModelRenderer(this, 32, 48);
        this.PlayerRightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.PlayerRightArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.PlayerRightLeg = new ModelRenderer(this, 16, 48);
        this.PlayerRightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.PlayerRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.PlayerLeftLeg = new ModelRenderer(this, 0, 16);
        this.PlayerLeftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.PlayerLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.PlayerHead.render(f5);
        this.PlayerLeftArm.render(f5);
        this.PlayerBody.render(f5);
        this.PlayerRightArm.render(f5);
        this.PlayerRightLeg.render(f5);
        this.PlayerLeftLeg.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
