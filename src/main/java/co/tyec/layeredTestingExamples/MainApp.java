
package co.tyec.layeredTestingExamples;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by yorta01 on 3/14/2016.
 */
public class MainApp
{

    public static void main(String[] args) throws Exception
    {
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/LayeredTestingExamples");
        webapp.setWar("src/main/webapp");

        Server server = new Server(8888);
        server.setHandler(webapp);
        try
        {
            server.start();
            server.join();
        }
        finally
        {
            server.destroy();
        }
    }
}