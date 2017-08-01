package com.frank.dbenchmark.model;

import org.bson.Document;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author ftorriani
 */
public class App {

    private String id;
    private String creator;
    private String description;
    private List<BlockContainer> blockContainers;

    public App( String id, String creator, String description, List<BlockContainer> blockContainers ) {
        this.id = id;
        this.creator = creator;
        this.description = description;
        this.blockContainers = blockContainers;
    }

    public String getId() {
        return id;
    }

    public String getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public List<BlockContainer> getBlockContainers() {
        return blockContainers;
    }

    public static App random() {

        List<BlockContainer> containers = BlockContainer.random( 10 );
        return new App( UUID.randomUUID().toString(), "Frank", "descr", containers );
    }

    public static App randomNoBlockContainer() {

        return new App( UUID.randomUUID().toString(),
                "Frank", "descr",
                Collections.emptyList() );
    }

    /**
     * MongoDB
     * @return a MongoDB document
     */
    public static Document toDocument() {
        Document d = new Document( "app",
                new Document().
                        append( "id", UUID.randomUUID().toString() ).
                        append( "creator", "frank" ).
                        append( "description", UUID.randomUUID().toString() ) );
        d.append( "containers",
                new Document().append( "id", UUID.randomUUID().toString() ).
                        append( "description", UUID.randomUUID().toString() ) );
        d.append( "containers",
                new Document().append( "id", UUID.randomUUID().toString() ).
                        append( "description", UUID.randomUUID().toString() ) );
        return d;
    }
}
