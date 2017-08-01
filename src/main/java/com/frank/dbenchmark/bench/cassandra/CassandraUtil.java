package com.frank.dbenchmark.bench.cassandra;

import com.datastax.driver.core.Session;

/**
 * @author ftorriani
 */
public class CassandraUtil {

    public static void createKeyspace( Session session,
                                       String keyspaceName,
                                       String replicationStrategy, int replicationFactor) {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
    }
}
