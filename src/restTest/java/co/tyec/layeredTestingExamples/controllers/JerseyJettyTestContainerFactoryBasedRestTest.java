
package co.tyec.layeredTestingExamples.controllers;

import java.io.IOException;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.jetty.JettyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.Test;

import co.tyec.layeredTestingExamples.LayeredTestingExamplesApplication;

public class JerseyJettyTestContainerFactoryBasedRestTest extends JerseyTest
{

    @Override
    protected Application configure()
    {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new LayeredTestingExamplesApplication();
    }


    @Override
    protected TestContainerFactory getTestContainerFactory() {
        return new JettyTestContainerFactory();
    }

    @Test
    public void testAddRest() throws IOException
    {
        Response addResponse = target("calc/add/1/2").request().get();
        String addText = addResponse.readEntity(String.class);
        Assert.assertEquals("3", addText);

        System.out.println("Test Response: " + addText);
    }
}
