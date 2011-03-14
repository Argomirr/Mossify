
package com.argo.bukkit.mossify;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class MossifyBlockListener extends BlockListener {
    private static Mossify plugin;
    private final int searchDepthWater = 2; //search depth for water

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

	    outerloop: for(int ix = x-searchDepthWater; ix <= x+searchDepthWater; ix++) {
		for(int iy = y-searchDepthWater; iy <= y+searchDepthWater; iy++) {
		    for(int iz = z-searchDepthWater; iz <= z+searchDepthWater; iz++) {
			if(world.getBlockTypeIdAt(ix, iy, iz) == 8 || world.getBlockTypeIdAt(ix, iy, iz) == 9) {
			    Random r = new Random();
			    long timeout = new Long(30 + r.nextInt(131));
//			    System.out.println("Got a cobby block near water! Replacing in " + timeout);

			    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new BlockReplacer(plugin, world, x, y, z, true), timeout*20);
			    
			    break outerloop;
			}
		    }
		}
	    }
	}
    }
}