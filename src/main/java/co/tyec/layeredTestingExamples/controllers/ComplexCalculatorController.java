
package co.tyec.layeredTestingExamples.controllers;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_HTML;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.tyec.layeredTestingExamples.services.ComplexCalculatorService;

/**
 * Created by yorta01 on 2/8/2016.
 */
@Path("calc")
@Produces(TEXT_PLAIN)
public class ComplexCalculatorController
{

    @Inject
    ComplexCalculatorService calculatorService;

    @GET
    public String get()
    {
        return "Invalid. Use:\n"//
                        + "/calc/add/1/2\n"//
                        + "/calc/subtract/1/2\n"//
                        + "/calc/multiply/1/2\n"//
                        + "/calc/divide/1/2\n";
    }

    @GET
    @Path("add/{x}/{y}")
    public String add(@PathParam(value = "x") Integer x, @PathParam(value = "y") Integer y)
    {
        return Integer.toString(calculatorService.add(x, y));
    }

    @GET
    @Path("subtract/{x}/{y}")
    public String subtract(@PathParam(value = "x") Integer x, @PathParam(value = "y") Integer y)
    {
        return Integer.toString(calculatorService.subtract(x, y));
    }

    @GET
    @Path("multiply/{x}/{y}")
    public String multiply(@PathParam(value = "x") Integer x, @PathParam(value = "y") Integer y)
    {
        return Integer.toString(calculatorService.multiply(x, y));
    }

    @GET
    @Path("divide/{x}/{y}")
    public String divide(@PathParam(value = "x") Integer x, @PathParam(value = "y") Integer y)
    {
        return Integer.toString(calculatorService.divide(x, y));
    }

}
