/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;

public class ScrollTooltipsMod extends Mod {

    public ScrollTooltipsMod() {
        super(
                "ScrollTooltips",
                "Makes long tooltips which go offscreen, scrollable.",
                Type.Tweaks
        );
    }
}
