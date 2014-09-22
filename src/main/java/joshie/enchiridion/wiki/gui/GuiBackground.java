package joshie.enchiridion.wiki.gui;

import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

public class GuiBackground extends GuiExtension {
    @Override
    public void draw() {
        glDisable(GL_LIGHTING);
        drawRect(-20, -3, 1044, 0, 0xFFEBECBA);
        drawRect(-20, 40, 1044, 43, 0xFFEBECBA);
        drawRect(275, 43, 278, getHeight(), 0xFFC2C29C);

        /** Side Bars **/
        drawRect(-3, 43, 0, getHeight(), 0xFFC2C29C);
        horizontalGradient(-6, 43, -3, getHeight(), 0, 0xFF6C6C56);
        drawRect(1024, 43, 1027, getHeight(), 0xFFC2C29C);
        horizontalGradient(1027,43, 1030, getHeight(), 0xFF6C6C56, 0);
        drawRect(1019, 43, 1024, getHeight(), 0xEE2A535E);

        /* Top left Blue Bar **/
        drawRect(-23, -3, -20, 43, 0xFFC2C29C);
        verticalGradient(-20, 20, 300, 40, 0xFF2E555E, 0xFF1B2C43);
        verticalGradient(-20, 0, 300, 20, 0xFF1B2C43, 0xFF2E555E);
        drawRect(300, 0, 303, 40, 0xFFC2C29C);

        /* Middle Dark Bar **/
        verticalGradient(303, 20, 700, 40, 0xFF09111E, 0xFF1B2C43);
        verticalGradient(303, 0, 700, 20, 0xFF1B2C43, 0xFF09111E);

        /* Top right Blue Bar **/
        drawRect(1044, -3, 1047, 43, 0xFFC2C29C);
        verticalGradient(700, 20, 1044, 40, 0xFF2E555E, 0xFF1B2C43);
        verticalGradient(700, 0, 1044, 20, 0xFF1B2C43, 0xFF2E555E);
        drawRect(697, 0, 700, 40, 0xFFC2C29C);

        /* Menu Selection */
        //#2A535E
        drawRect(0, 43, 5, getHeight(), 0xEE2A535E);
        horizontalGradient(5, 43, 270, getHeight(), 0xEE0E1924, 0xEE0E1924);
        drawRect(270, 43, 275, getHeight(), 0xEE2A535E);

        glEnable(GL_LIGHTING);
    }

    @Override
    public void clicked(int button) {
        return;
    }

    @Override
    public void release(int button) {
        return;
    }
}
