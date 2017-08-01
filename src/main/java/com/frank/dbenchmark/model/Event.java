package com.frank.dbenchmark.model;

import org.bson.Document;

import java.util.UUID;

/**
 * @author ftorriani
 */
public class Event {

    private final String id;
    private final String type;
    private final Long time;

    public Event( String id, String type ) {
        this.id = id;
        this.type = type;
        this.time = System.currentTimeMillis();
    }

    public static Event random() {
        return new Event( UUID.randomUUID().toString(),
                UUID.randomUUID().toString() );
    }

    public static Document toDocument() {
        return new Document().append( "id", UUID.randomUUID().toString() ).
                append( "type", UUID.randomUUID().toString() ).
                append( "date", System.currentTimeMillis() );
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Long getTime() {
        return time;
    }
}
