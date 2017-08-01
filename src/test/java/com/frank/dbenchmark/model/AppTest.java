package com.frank.dbenchmark.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ftorriani
 */
public class AppTest {

    @Test
    public void createApp() {

        App app = App.random();
        assertThat( app ).isNotNull();
        assertThat( app.getBlockContainers() ).hasSize( 10 );
    }

    @Test
    public void asJson() throws JsonProcessingException {
        App app = App.random();

        System.out.println( new ObjectMapper().writeValueAsString( app ) );
    }

}