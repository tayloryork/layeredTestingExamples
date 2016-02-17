
package co.tyec.layeredTestingExamples.controllers;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;


public class StandaloneGrizzlyBasedRestTest
{

    static final URI BASE_URI = getBaseURI();

    HttpServer server;

    private static URI getBaseURI()
    {
        return UriBuilder.fromUri("http://localhost/").port(9998).build();
    }

//    @Before
//    public void startServer() throws IOException
//    {
//        System.out.println("Starting grizzly...");
//
//        Injector injector = Guice.createInjector(new ServletModule()
//        {
//
//            @Override
//            protected void configureServlets()
//            {
//                //                bind(new TypeLiteral<Dao<String>>()
//                //                {
//                //                }).to(TestDao.class);
//            }
//        });
//
//        ResourceConfig rc = new PackagesResourceConfig("co.tyec.layeredTestingExamples");
//        IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc, injector);
//        server = GrizzlyServerFactory.createHttpServer(BASE_URI + "layeredTestingExamples/", rc, ioc);
//
//        System.out.println(String.format("Try out %s{app_name}\nHit enter to stop it...",
//                                         BASE_URI,
//                                         BASE_URI));
//    }

    @After
    public void stopServer()
    {
        server.stop();
    }

//    @Test
//    public void testAddRest() throws IOException
//    {
//        Client client = Client.create(new DefaultClientConfig());
//        WebResource service = client.resource(getBaseURI());
//
//        ClientResponse resp = service.path("layeredTestingExamples").path("calc").path("add").path("1").path("2").accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
//        System.out.println("Test Response: " + resp);
//        String text = resp.getEntity(String.class);
//
//        assertEquals(200, resp.getStatus());
//        assertEquals("3", text);
//
//    }

//    /**
//     * Leave this for verifying grizzly is working correctly.
//     *
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception
//    {
//        StandaloneGrizzlyBasedRestTest test = new StandaloneGrizzlyBasedRestTest();
//        test.startServer();
//        System.in.read(); // hit enter to stop the controllers
//        test.server.stop();
//    }

}
