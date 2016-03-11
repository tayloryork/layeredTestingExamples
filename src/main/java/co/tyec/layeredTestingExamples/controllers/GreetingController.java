
package co.tyec.layeredTestingExamples.controllers;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.tyec.layeredTestingExamples.dao.Greeting;

/**
 * Created by yorta01 on 2/8/2016.
 */
@Path("greeting")
@Produces(TEXT_PLAIN)
public class GreetingController
{

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @GET
    public String greeting(@PathParam(value = "name") String name)
    {
        if (name == null)
        {
            name = "World";
        }
        return new Greeting(counter.incrementAndGet(), String.format(template, name)).getContent();
    }
}
