
package co.tyec.layeredTestingExamples;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by yorta01 on 2/16/2016.
 */
public class LayeredTestingExamplesApplication extends ResourceConfig
{

    public LayeredTestingExamplesApplication()
    {
        System.out.println("Starting With LayeredTestingExamplesApplication()");

        register(new LayeredTestingExamplesBinder());
        packages("co.tyec.layeredTestingExamples");

        System.out.println("Finished With LayeredTestingExamplesApplication()");
    }

}