package joshie.enchiridion.items;

import com.google.common.collect.ImmutableList;
import joshie.enchiridion.EClientProxy;
import joshie.enchiridion.data.book.BookRegistry;
import joshie.enchiridion.library.LibraryHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SmartLibrary implements IBakedModel {
    private static IBakedModel library;

    @SubscribeEvent
    public void onCookery(ModelBakeEvent event) {
        event.getModelRegistry().putObject(EClientProxy.libraryItem, this);
    }

    @Override
    @Nonnull
    public ItemOverrideList getOverrides() {
        return LibraryOverride.INSTANCE;
    }

    private static class LibraryOverride extends ItemOverrideList {
        private static LibraryOverride INSTANCE = new LibraryOverride();
        private ItemStack broken = new ItemStack(Items.ENCHANTED_BOOK);

        private ItemModelMesher mesher;

        public LibraryOverride() {
            super(ImmutableList.of());
        }

        @Override
        @Nonnull
        public IBakedModel handleItemState(@Nonnull IBakedModel originalModel, ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
            IBakedModel ret;
            //Setup
            if (mesher == null) mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
            library = mesher.getModelManager().getModel(EClientProxy.library);
            if (stack.getItemDamage() == 0) { //If we're a book
                ret = mesher.getModelManager().getModel(BookRegistry.INSTANCE.getModelLocation(stack));
            } else {
                ItemStack book = LibraryHelper.getClientLibraryContents().getCurrentBookItem();
                ret = book.isEmpty() ? library : mesher.getItemModel(book);
            }

            return ret == null ? mesher.getItemModel(broken) : ret;
        }
    }

    /**
     * Redundant crap below :/
     **/
    @Override
    @Nonnull
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        return null;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    @Nonnull
    public TextureAtlasSprite getParticleTexture() {
        return library.getParticleTexture();
    }

    @Override
    @Nonnull
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }
}