package joshie.enchiridion.lib;

import net.minecraftforge.fml.common.Loader;

public class EInfo {
    public static final String JAVAPATH = "joshie.enchiridion.";
    public static final String MODID = "enchiridion";
    public static final String MODNAME = "Enchiridion";
    public static final String INITIALS = "E";
    public static final String VERSION = "@VERSION@";
    public static final String ACCEPTED_MC_VERSIONS = "[1.12,1.13)";
    public static final String DEPENDENCIES = "after:guideapi";
    public static final String GUI_FACTORY_CLASS = "joshie.enchiridion.gui.config.GuiFactory";
    public static final String TEXPATH = "enchiridion:textures/books/";

    public static final boolean IS_GUIDEAPI_LOADED = Loader.isModLoaded("guideapi");
}