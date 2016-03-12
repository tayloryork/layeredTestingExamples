
package co.tyec.layeredTestingExamples.database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.flywaydb.core.Flyway;
import org.h2.tools.Server;

/**
 * Created by yorta01 on 3/11/2016.
 */
@WebListener
public class H2FlywayServletContextListener implements ServletContextListener
{

    public static String jdbcUrl = "jdbc:h2:./h2db/test";

    public static String jdbcUsername = "sa";

    public static String jdbcPassword = "";

    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        try
        {
            // Start H2
            Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();

            // Initialize H2 Driver
            Class.forName("org.h2.Driver");

            // Create/update tables
            Flyway flyway = new Flyway();
            flyway.setDataSource(jdbcUrl, jdbcUsername, jdbcPassword);
            flyway.migrate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        try
        {
            // Stop H2
            Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}