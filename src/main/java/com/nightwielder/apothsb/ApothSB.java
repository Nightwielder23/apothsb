package com.nightwielder.apothsb;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod(ApothSB.MOD_ID)
public class ApothSB {
    public static final String MOD_ID = "apothsb";

    public ApothSB() {
        ModList mods = ModList.get();
        if (mods != null && mods.isLoaded("apotheosis") && mods.isLoaded("sophisticatedbackpacks")) {
            MinecraftForge.EVENT_BUS.register(new GemTagInjector());
        }
    }
}
