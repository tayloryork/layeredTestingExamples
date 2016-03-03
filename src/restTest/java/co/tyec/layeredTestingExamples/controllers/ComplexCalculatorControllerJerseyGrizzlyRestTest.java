
package co.tyec.layeredTestingExamples.controllers;

import java.io.IOException;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.Test;

import co.tyec.layeredTestingExamples.LayeredTestingExamplesApplication;

public class ComplexCalculatorControllerJerseyGrizzlyRestTest extends JerseyTest
{

    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        forceSet(TestProperties.CONTAINER_PORT, "0");
        return new LayeredTestingExamplesApplication();
    }

    @Override
    protected TestContainerFactory getTestContainerFactory() {
        return new GrizzlyTestContainerFactory();
    }

    @Test
    public void testAddRest() throws IOException
    {
        Response addResponse = target("calc/add/1/2").request().get();
        String addText = addResponse.readEntity(String.class);
        Assert.assertEquals("3", addText);

        System.out.println("Test Response: " + addText);
    }

    @Test
    public void testSubRest() throws IOException
    {
        Response addResponse = target("calc/subtract/3/2").request().get();
        String addText = addResponse.readEntity(String.class);
        Assert.assertEquals("1", addText);

        System.out.println("Test Response: " + addText);
    }
}
