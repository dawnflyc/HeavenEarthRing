package com.github.dawnflyc.heavenearthring.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ModelMudBakedModel implements IBakedModel {

    private IBakedModel existingModel;

    public ModelMudBakedModel(IBakedModel existingModel) {
        this.existingModel = existingModel;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        throw new AssertionError("IForgeBakedModel::getQuads should never be called, only IForgeBakedModel::getQuads");
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        return this.existingModel.getQuads(state, side, rand);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return this.existingModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.existingModel.isGui3d();
    }

    @Override
    public boolean func_230044_c_() {
        return this.existingModel.func_230044_c_();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return true;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return this.existingModel.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return new ModelMudItemOverrideList();
    }

}
