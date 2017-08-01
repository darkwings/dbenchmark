package com.frank.dbenchmark.bench.cassandra;

import com.frank.dbenchmark.model.App;
import com.frank.dbenchmark.model.BlockContainer;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author ftorriani
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
public class CassandraWriteBenchmark {

    private CassandraConnector connector;

    private KeyspaceRepository keyspaceRepository;
    private AppRepository appRepository;
    private BlockContainerRepository blockContainerRepository;

    @Setup(Level.Iteration)
    public void setUp() {
        connector = new CassandraConnector();
        connector.connect( "localhost", 9042 );

        keyspaceRepository = new KeyspaceRepository( connector.getSession() );
        appRepository = new AppRepository( connector.getSession() );
        blockContainerRepository = new BlockContainerRepository( connector.getSession() );
        keyspaceRepository.createKeyspace( "apps", "SimpleStrategy", 1 );
        keyspaceRepository.useKeyspace( "apps" );

        appRepository.createTable();
        blockContainerRepository.createTable();
    }

    @TearDown(Level.Iteration)
    public void tearDown() {
        keyspaceRepository.deleteKeyspace( "apps" );
        connector.close();
    }

    @Benchmark
    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 5, time = 5)
    public App process() {

        App app = App.randomNoBlockContainer();
//        BlockContainer bl1 = BlockContainer.random();
//        BlockContainer bl2 = BlockContainer.random();
        appRepository.insertApp( app );
//        blockContainerRepository.insertBlockContainer( bl1, app.getId() );
//        blockContainerRepository.insertBlockContainer( bl2, app.getId() );
        return app;
    }
}
