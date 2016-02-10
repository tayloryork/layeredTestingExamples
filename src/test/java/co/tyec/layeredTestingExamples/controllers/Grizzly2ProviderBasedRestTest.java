
package co.tyec.layeredTestingExamples.controllers;

import java.io.IOException;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class Grizzly2ProviderBasedRestTest extends JerseyTest
{

    @Override
    protected Application configure()
    {
        return new ResourceConfig(ComplexCalulatorController.class);
    }

    @Test
    public void testAddRest() throws IOException
    {
//        final Response response = target().request().get();
//        Client client = Client.create(new DefaultClientConfig());
//        //WebResource service = client.resource(getBaseURI());
//
////        ClientResponse resp = service.path("layeredTestingExamples").path("calc").path("add").path("1").path("2")
////                        .accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
//        System.out.println("Test Response: " + resp);
//        String text = resp.getEntity(String.class);
//
//        assertEquals(200, resp.getStatus());
//        assertEquals("3", text);

    }
}
