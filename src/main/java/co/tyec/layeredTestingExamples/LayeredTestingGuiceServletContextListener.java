
package co.tyec.layeredTestingExamples;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * User: Renato
 */
public class LayeredTestingGuiceServletContextListener extends GuiceServletContextListener
{

    @Override
    protected Injector getInjector()
    {
        final ResourceConfig rc = new PackagesResourceConfig("co.tyec.layeredTestingExamples");

        return Guice.createInjector(new ServletModule()
        {

            @Override
            protected void configureServlets()
            {
                // This does the auto-magic binding of classes under the package named above
                for (Class<?> resource : rc.getClasses())
                {
                    System.out.println("Binding resource: " + resource.getName());
                    bind(resource);
                }

                serve("/rest/*").with(GuiceContainer.class);
            }
        });
    }
}
