package joshie.enchiridion;

import static joshie.enchiridion.Enchiridion.instance;
import joshie.enchiridion.api.EnchiridionHelper;
import joshie.enchiridion.library.LibraryRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ECommonProxy {
    public void preInit() {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new EGuiHandler());
        EnchiridionHelper.bookRegistry = LibraryRegistry.INSTANCE;
    }
    
    public void init() {}
    public void postInit() {
        setupClient();
    }

    public void setupClient() {}
}
