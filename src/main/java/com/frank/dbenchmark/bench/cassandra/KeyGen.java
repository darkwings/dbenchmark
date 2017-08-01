package com.frank.dbenchmark.bench.cassandra;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ftorriani
 */
public class KeyGen {

    private static final AtomicInteger key = new AtomicInteger( 0 );

    public static int next() {
        return key.incrementAndGet();
    }
}
