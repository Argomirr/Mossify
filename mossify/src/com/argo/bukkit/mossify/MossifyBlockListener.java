
package com.argo.bukkit.mossify;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class MossifyBlockListener extends BlockListener {
    private static Mossify plugin;
    private final int searchDepth = 2;

    public MossifyBlockListener(Mossify instance) {
	plugin = instance;
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
	Block block = event.getBlockPlaced();
	if(block.getType() == Material.COBBLESTONE) {
	    int x = block.getX();
	    int y = block.getY();
	    int z = block.getZ();
	    World world = block.getWorld();

	    outerloop: for(int ix = x-searchDepth; ix <= x+searchDepth; ix++) {
		for(int iy = y-searchDepth; iy <= y+searchDepth; iy++) {
		    for(int iz = z-searchDepth; iz <= z+searchDepth; iz++) {
			if(world.getBlockTypeIdAt(ix, iy, iz) == 8 || world.getBlockTypeIdAt(ix, iy, iz) == 9) {
			    Random r = new Random();
			    long timeout = new Long(30 + r.nextInt(131));
//			    System.out.println("Got a cobby block near water! Replacing in " + timeout);

			    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new BlockReplacer(world, x, y, z), timeout*20);
			    
			    break outerloop;
			}
		    }
		}
	    }
	}
    }

}

class BlockReplacer implements Runnable {
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