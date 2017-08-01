package com.frank.dbenchmark.model;

import com.frank.dbenchmark.bench.cassandra.KeyGen;

import java.util.*;

/**
 * @author ftorriani
 */
public class BlockContainer {

    private String id;
    private String description;
    private List<Block> blocks;

    public BlockContainer( String id, String description, List<Block> blocks ) {
        this.id = id;
        this.description = description;
        this.blocks = blocks;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public static List<BlockContainer> random( int howMany ) {
        List<BlockContainer> containers = new ArrayList<BlockContainer>();
        for ( int i = 0;
              i < howMany;
              i++ ) {
            List<Block> blocks = Block.random( 5 );
            BlockContainer c = new BlockContainer( UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(), blocks );
            containers.add( c );
        }
        return containers;
    }

    public static BlockContainer random() {
        BlockContainer c = new BlockContainer( UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), Collections.emptyList() );
        return c;
    }
}
