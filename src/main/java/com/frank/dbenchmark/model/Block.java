package com.frank.dbenchmark.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ftorriani
 */
public class Block {

    private String id;
    private List<Widget> widgets;

    public Block( String id, List<Widget> widgets ) {
        this.id = id;
        this.widgets = widgets;
    }

    public String getId() {
        return id;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public static List<Block> random( int howMany ) {
        List<Block> blocks = new ArrayList<>();

        for ( int i = 0;
              i < howMany;
              i++ ) {

            List<Widget> widgets = Widget.random( 5 );

            Block bl = new Block( UUID.randomUUID().toString(), widgets );

            blocks.add( bl );
        }
        return blocks;
    }
}
