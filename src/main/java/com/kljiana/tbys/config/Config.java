package com.kljiana.tbys.config;

import com.kljiana.tbys.TBYS;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TBYS.ModID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static ForgeConfigSpec configSpec;
    public static ForgeConfigSpec.BooleanValue isCancelAttack, isCancelShot, attackAmbient, attackVisible, attackShowIcon, shotAmbient, shotVisible, shotShowIcon;
    public static ForgeConfigSpec.ConfigValue<String> attackEffect, shotEffect;
    public static ForgeConfigSpec.ConfigValue<Integer> attackDuration, attackAmplifier, shotDuration, shotAmplifier;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Attack");
        isCancelAttack = builder.define("isCancelAttack", true);
        attackEffect = builder.define("attackEffect", "minecraft:weakness");
        attackDuration = builder.define("effectDuration", 20);
        attackAmplifier = builder.define("effectAmplifier", 0);
        attackAmbient = builder.define("effectAmbient", false);
        attackVisible = builder.define("effectVisible", true);
        attackShowIcon = builder.define("effectShowIcon", true);

        builder.pop();
        builder.push("Shot");
        isCancelShot = builder.define("isCancelBowShot", true);
        shotEffect = builder.define("shotEffect", "minecraft:weakness");
        shotDuration = builder.define("shotDuration", 20);
        shotAmplifier = builder.define("shotAmplifier", 0);
        shotAmbient = builder.define("effectAmbient", false);
        shotVisible = builder.define("effectVisible", true);
        shotShowIcon = builder.define("effectShowIcon", true);
        configSpec = builder.build();
    }
}
