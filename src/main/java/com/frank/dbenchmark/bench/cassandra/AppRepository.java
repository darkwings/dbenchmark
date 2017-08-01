package com.frank.dbenchmark.bench.cassandra;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.frank.dbenchmark.model.App;

/**
 * @author ftorriani
 */
public class AppRepository {

    private static final String TABLE_NAME = "app";

    private static final String TABLE_NAME_BY_TITLE = TABLE_NAME + "ByTitle";

    private Session session;

    private boolean prepared = false;
    private PreparedStatement stmt;

    public AppRepository( Session session ) {
        this( session, false );
    }

    public AppRepository( Session session, boolean prepared ) {
        this.session = session;
        this.prepared = prepared;
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

        if ( prepared ) {
            System.out.println("AppRepository: Prepare Statement");
            prepare();
        }
    }

    public void insertApp( App app ) {
        if ( prepared ) {
            BoundStatement bound = stmt.bind();
            bound.setString( 0, app.getId() );
            bound.setString( 1, app.getCreator() );
            bound.setString( 2, app.getDescription() );
            session.execute( bound );
        }
        else {
            StringBuilder sb = new StringBuilder( "INSERT INTO " ).append( TABLE_NAME ).
                    append( "(id, creator, description) " ).append( "VALUES ('" ).
                    append( app.getId() ).append( "', '" ).
                    append( app.getCreator() ).append( "', '" ).
                    append( app.getDescription() ).append( "');" );

            final String query = sb.toString();
            session.execute( query );
        }
    }

    public void prepare() {
        stmt = session.prepare( new StringBuilder( "INSERT INTO " ).append( TABLE_NAME ).
                append( "(id, creator, description) " ).append( "VALUES (?,?,?)" ).toString() );
    }
}
