package com.frank.dbenchmark.bench.cassandra;

import com.datastax.driver.core.Session;
import com.frank.dbenchmark.model.App;
import com.frank.dbenchmark.model.BlockContainer;

/**
 * @author ftorriani
 */
public class BlockContainerRepository {

    private static final String TABLE_NAME = "blockContainer";

    private static final String TABLE_NAME_BY_TITLE = TABLE_NAME + "ByTitle";

    private Session session;

    public BlockContainerRepository( Session session ) {
        this.session = session;
    }

    public void createTable() {
        StringBuilder sb =
                new StringBuilder( "CREATE TABLE IF NOT EXISTS " ).
                        append( TABLE_NAME ).
                        append( "(" ).append( "id text PRIMARY KEY, " ).
                        append( "app_id text," ).
                        append( "description text)" );

        final String query = sb.toString();
        session.execute( query );
    }

    public void insertBlockContainer( BlockContainer bl, String appId ) {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).
                append("(id, app_id, description) ").append("VALUES ('").
                append(bl.getId()).append("', '").
                append(appId).append("', '").
                append(bl.getDescription()).append("');");

        final String query = sb.toString();
        session.execute(query);
    }
}
