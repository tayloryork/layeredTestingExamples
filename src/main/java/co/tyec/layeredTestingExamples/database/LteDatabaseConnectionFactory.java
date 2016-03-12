
package co.tyec.layeredTestingExamples.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.glassfish.hk2.api.Factory;

/**
 * Created by yorta01 on 3/11/2016.
 */
public class LteDatabaseConnectionFactory implements Factory<Connection>
{
    String jdbcUrl = H2FlywayServletContextListener.jdbcUrl;

    String jdbcUsername = H2FlywayServletContextListener.jdbcUsername;

    String jdbcPassword = H2FlywayServletContextListener.jdbcPassword;

    private Connection connection = null;

    private Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public Connection provide()
    {
        return getConnection();
    }

    @Override
    public void dispose(Connection instance)
    {
        try
        {
            instance.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
