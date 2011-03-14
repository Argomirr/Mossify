
package com.argo.bukkit.mossify;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;

public class BlockReplacer implements Runnable {
    private static World world;
    private static Mossify plugin;
    private static final int searchDepth = 1; //search depth for spreading moss
    private static final double spreadChance = 0.3;

    private int x, y, z;
    private boolean doSpread;

    public BlockReplacer(Mossify instance, World bw, int bx, int by, int bz, boolean spread) {
	world = bw;
	x = bx;
	y = by;
	z = bz;
	plugin = instance;
	doSpread = spread;
    }

    public void run() {
//	System.out.println("Replacing block...");

	if(world.getBlockTypeIdAt(x, y, z) == 4) { //Double check to make sure the block hasn't changed while we were waiting
	    world.getBlockAt(x, y, z).setType(Material.MOSSY_COBBLESTONE);

	    //Check for nearby cobblestone blocks to spread to
	    Random r = new Random();
	    if(doSpread) {
		for(int ix = x-searchDepth; ix <= x+searchDepth; ix++) {
		    for(int iy = y-searchDepth; iy <= y+searchDepth; iy++) {
			for(int iz = z-searchDepth; iz <= z+searchDepth; iz++) {
			    if(world.getBlockTypeIdAt(ix, iy, iz) == 4) {
				if(r.nextDouble() <= spreadChance){
				    long timeout = new Long(30 + r.nextInt(131));
//				    System.out.println("Nearby cob block found. Spreading in " + timeout);
				    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new BlockReplacer(plugin, world, ix, iy, iz, true), timeout*20);
				}
			    }
			}
		    }
		}
	    }
	}
    }
}