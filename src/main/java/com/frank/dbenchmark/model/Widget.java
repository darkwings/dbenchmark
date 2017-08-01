package com.frank.dbenchmark.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author ftorriani
 */
public class Widget {

    private String id;
    private int width;
    private int height;
    private int posX;
    private int posY;

    public Widget( String id, int width, int height, int posX, int posY ) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public static List<Widget> random( int howMany ) {
        List<Widget> widgets = new ArrayList<>();
        for ( int i = 0;
              i < howMany;
              i++ ) {
            Random r = new Random();
            Widget c = new Widget( UUID.randomUUID().toString(),
                    r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt() );
            widgets.add( c );
        }
        return widgets;
    }
}
