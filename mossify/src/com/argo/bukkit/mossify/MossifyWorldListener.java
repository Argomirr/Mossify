//Deprecated unless I don't find a better way to do this...
package com.argo.bukkit.mossify;

import org.bukkit.Chunk;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.WorldListener;

@Deprecated
public class MossifyWorldListener extends WorldListener {

    public MossifyWorldListener(Mossify instance) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onChunkLoaded(ChunkLoadEvent event) {
	Chunk chunky = event.getChunk();
	int bx = chunky.getX() << 4;
	int bz = chunky.getZ() << 4;
	for(int xx = bx; xx < bx+16; xx++) {
	    for(int zz = bz; zz < bz+16; zz++) {
		for(int yy = 0; yy < 128; yy++) {
		    //check for blockType 'n stuff
		}
	    }
	}
    }
}