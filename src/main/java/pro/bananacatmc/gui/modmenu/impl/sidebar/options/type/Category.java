/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.gui.modmenu.impl.sidebar.options.type;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.option.Option;
import pro.bananacatmc.gui.modmenu.impl.Panel;
import pro.bananacatmc.gui.modmenu.impl.sidebar.options.Options;
import pro.bananacatmc.helpers.render.Helper2D;

public class Category extends Options {

    public Category(Option option, Panel panel, int y) {
        super(option, panel, y);
    }

    @Override
    public void renderOption(int mouseX, int mouseY) {
        BananaCat.INSTANCE.fontHelper.size20.drawString(
                option.getName(),
                panel.getX() + 20,
                panel.getY() + panel.getH() + getY() + 6,
                0x90ffffff
        );
        Helper2D.drawRectangle(panel.getX() + 20, panel.getY() + panel.getH() + getY() + 20, panel.getW() - 40, 1, 0x40ffffff);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {

    }
}
