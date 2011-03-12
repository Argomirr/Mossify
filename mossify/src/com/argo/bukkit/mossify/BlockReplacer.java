
package com.argo.bukkit.mossify;

import org.bukkit.Material;
import org.bukkit.World;

public class BlockReplacer implements Runnable {
    private static World world;
    private int x, y, z;

    public BlockReplacer(World bw, int bx, int by, int bz) {
	world = bw;
	x = bx;
	y = by;
	z = bz;
    }

    public void run() {
//	System.out.println("Replacing block...");
	if(world.getBlockTypeIdAt(x, y, z) == 4) { //Double check to make sure the block hasn't changed while we were waiting
	    world.getBlockAt(x, y, z).setType(Material.MOSSY_COBBLESTONE);
	}
    }
}