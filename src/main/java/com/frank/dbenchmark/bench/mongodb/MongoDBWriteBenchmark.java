package com.frank.dbenchmark.bench.mongodb;

import com.frank.dbenchmark.model.App;
import com.frank.dbenchmark.model.Event;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.openjdk.jmh.annotations.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ftorriani
 */

@State(value = Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
public class MongoDBWriteBenchmark {

    private MongoClient mongoClient;
    private MongoDatabase db;

    private String collectionName = UUID.randomUUID().toString();

    @Setup(Level.Iteration)
    public void setUp() {

        mongoClient = new MongoClient( "localhost", 27017 );
        db = mongoClient.getDatabase( "dbtest" );
    }

    @TearDown(Level.Iteration)
    public void tearDown() {
        mongoClient.close();
    }


    @Benchmark
    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 5, time = 5)
    public Document writeAggregateSingleStatements() {

        MongoCollection coll = db.getCollection( collectionName );
        Document tDocument = App.toDocument();
        coll.insertOne( tDocument );
        return tDocument;
    }

    @Benchmark
    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 5, time = 5)
    public Document writeEvent() {
        MongoCollection coll = db.getCollection( collectionName );
        Document tDocument = Event.toDocument();
        coll.insertOne( tDocument );
        return tDocument;
    }
}
