package com.frank.dbenchmark.bench.cassandra;

import com.datastax.driver.core.Session;
import com.frank.dbenchmark.model.App;

/**
 * @author ftorriani
 */
public class AppRepository {

    private static final String TABLE_NAME = "app";

    private static final String TABLE_NAME_BY_TITLE = TABLE_NAME + "ByTitle";

    private Session session;

    public AppRepository( Session session ) {
        this.session = session;
    }

    public void createTable() {
        StringBuilder sb =
                new StringBuilder( "CREATE TABLE IF NOT EXISTS " ).
                        append( TABLE_NAME ).
                        append( "(" ).append( "id text PRIMARY KEY, " ).
                        append( "creator text," ).
                        append( "description text)" );

        final String query = sb.toString();
        session.execute( query );
    }

    public void insertApp( App app ) {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).
                append("(id, creator, description) ").append("VALUES ('").
                append(app.getId()).append("', '").
                append(app.getCreator()).append("', '").
                append(app.getDescription()).append("');");

        final String query = sb.toString();
        session.execute(query);
    }
}
