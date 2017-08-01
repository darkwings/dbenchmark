package com.frank.dbenchmark.bench.cassandra;

import com.frank.dbenchmark.model.App;
import com.frank.dbenchmark.model.BlockContainer;
import org.openjdk.jmh.annotations.*;

import java.util.UUID;
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

        String keyspace = "apps";

        keyspaceRepository = new KeyspaceRepository( connector.getSession() );
        keyspaceRepository.createKeyspace( keyspace, "SimpleStrategy", 1 );
        keyspaceRepository.useKeyspace( keyspace );

        appRepository = new AppRepository( connector.getSession(), true );
        blockContainerRepository = new BlockContainerRepository( connector.getSession(), true );

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

        // Simuliamo la scrittura di un App con 2 BlockContainer associati
        // TODO: scrittura in batch
        App app = App.randomNoBlockContainer();
        BlockContainer bl1 = BlockContainer.random();
        BlockContainer bl2 = BlockContainer.random();
        appRepository.insertApp( app );
        blockContainerRepository.insertBlockContainer( bl1, app.getId() );
        blockContainerRepository.insertBlockContainer( bl2, app.getId() );
        return app;
    }
}
