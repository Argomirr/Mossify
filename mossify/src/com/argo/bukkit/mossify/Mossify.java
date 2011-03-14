
package com.argo.bukkit.mossify;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Mossify extends JavaPlugin {
    private final MossifyBlockListener blockListener = new MossifyBlockListener(this);

    public void onEnable() {
	PluginManager pm = getServer().getPluginManager();
	//pm.registerEvent(Type.BLOCK_PLACED, blockListener, Priority.Normal, this);
	pm.registerEvent(Type.BLOCK_PLACED, blockListener, Priority.Normal, this);

	PluginDescriptionFile pdf = this.getDescription();
	System.out.println(pdf.getName() + " revision " + pdf.getVersion() + " succesfully loaded.");
    }
    
    public void onDisable() {
	PluginDescriptionFile pdf = this.getDescription();
	System.out.println(pdf.getName() + " revision " + pdf.getVersion() + " succesfully disabled.");
    }
}