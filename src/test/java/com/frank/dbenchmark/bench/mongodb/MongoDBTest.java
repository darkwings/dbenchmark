package com.frank.dbenchmark.bench.mongodb;

import com.frank.dbenchmark.model.Event;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.mongodb.client.model.Filters.*;

/**
 * @author ftorriani
 */
public class MongoDBTest {

    private MongoClient mongoClient;
    private MongoDatabase db;

    @Before
    public void setUp() {
        mongoClient = new MongoClient( "localhost", 27017 );
        db = mongoClient.getDatabase( "dbtest" );
    }

    @After
    public void tearDown() {
        mongoClient.close();
    }

    @Test
    public void writeThenRead() {

        MongoCollection coll = db.getCollection( "test" );
        Document tDocument = Event.toDocument();
        coll.insertOne( tDocument );

        String id = (String) tDocument.get( "id" );

        Document myDoc = (Document) coll.find( eq( "id", id ) ).first();
        System.out.println( myDoc.toJson() );
    }
}
