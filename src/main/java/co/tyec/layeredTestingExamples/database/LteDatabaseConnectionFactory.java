
package co.tyec.layeredTestingExamples.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import co.tyec.layeredTestingExamples.conf.DataSourceConf;

/**
 * Created by yorta01 on 3/11/2016.
 */
public class LteDatabaseConnectionFactory implements Factory<Connection>
{

    DataSourceConf dataSourceConf;

    private Connection connection = null;

    @Inject
    public LteDatabaseConnectionFactory(DataSourceConf dataSourceConf)
    {
        this.dataSourceConf = dataSourceConf;
    }

    private Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                String jdbcUrl = dataSourceConf.getUrl();
                String jdbcUsername = dataSourceConf.getUsername();
                String jdbcPassword = dataSourceConf.getPassword();

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
