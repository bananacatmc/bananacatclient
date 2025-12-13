/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;

public class NoHurtCamMod extends Mod {

    public NoHurtCamMod() {
        super(
                "NoHurtCam",
                "Removes the camera shake effect when you take damage.",
                Type.Visual
        );
    }
}
