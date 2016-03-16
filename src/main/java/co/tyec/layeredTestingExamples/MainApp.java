package co.tyec.layeredTestingExamples;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.ProtectionDomain;

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

        webapp.setWar(getWebAppLocation());
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

    private static String getWebAppLocation()
    {
        String webappLocation = null;
        ProtectionDomain protectionDomain = MainApp.class.getProtectionDomain();
        URL location = protectionDomain.getCodeSource().getLocation();
        if(inWarFile()){
            webappLocation = location.getFile();
        } else {
            webappLocation = "src/main/webapp";
        }
        return webappLocation;
    }

    private static boolean inWarFile()
    {
        ProtectionDomain protectionDomain = MainApp.class.getProtectionDomain();
        URL location = protectionDomain.getCodeSource().getLocation();
        File externalForm = null;
        try
        {
            externalForm = new File(location.toURI());
        }
        catch(URISyntaxException e)
        {
            externalForm = new File(location.getFile());
        }

        System.out.println("toExternalForm: " + location.toExternalForm());
        System.out.println("isDirectory: " + externalForm.isDirectory());

        return externalForm.isDirectory();
    }
}