package com.frank.dbenchmark.bench.cassandra;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.frank.dbenchmark.model.App;
import com.frank.dbenchmark.model.Event;

/**
 * @author ftorriani
 */
public class EventRepository {

    private static final String TABLE_NAME = "event";

    private Session session;

    private PreparedStatement stmt;

    public EventRepository( Session session ) {
        this.session = session;
    }

    public void createTable() {
        StringBuilder sb =
                new StringBuilder( "CREATE TABLE IF NOT EXISTS " ).
                        append( TABLE_NAME ).
                        append( "(" ).append( "id text PRIMARY KEY, " ).
                        append( "type text," ).
                        append( "time bigint)" );

        final String query = sb.toString();
        session.execute( query );

        stmt = session.prepare( new StringBuilder( "INSERT INTO " ).append( TABLE_NAME ).
                append( "(id, type, time) " ).append( "VALUES (?,?,?)" ).toString() );
    }

    public void insertEvent( Event app ) {
//        if ( prepared ) {
        BoundStatement bound = stmt.bind();
        bound.setString( 0, app.getId() );
        bound.setString( 1, app.getType() );
        bound.setLong( 2, app.getTime() );
        session.execute( bound );
//        }
//        else {
//            StringBuilder sb = new StringBuilder( "INSERT INTO " ).append( TABLE_NAME ).
//                    append( "(id, type, time) " ).append( "VALUES ('" ).
//                    append( app.getId() ).append( "', '" ).
//                    append( app.getType() ).append( "', " ).
//                    append( app.getTime() ).append( ");" );
//
//            final String query = sb.toString();
//            session.execute( query );
//        }
    }

//    public void prepare() {
//        stmt = session.prepare( new StringBuilder( "INSERT INTO " ).append( TABLE_NAME ).
//                append( "(id, type, time) " ).append( "VALUES (?,?,?)" ).toString() );
//    }
}
