
package co.tyec.layeredTestingExamples.database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.flywaydb.core.Flyway;
import org.h2.tools.Server;

import co.tyec.layeredTestingExamples.conf.DataSourceConf;

/**
 * Created by yorta01 on 3/11/2016.
 */
@WebListener
public class H2FlywayServletContextListener implements ServletContextListener
{

    DataSourceConf dataSourceConf = new DataSourceConf();

    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        try
        {

            String jdbcUrl = dataSourceConf.getUrl();//"jdbc:h2:./h2db/test";

            String jdbcUsername = dataSourceConf.getUsername();//"sa";

            String jdbcPassword = dataSourceConf.getPassword();//"";

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