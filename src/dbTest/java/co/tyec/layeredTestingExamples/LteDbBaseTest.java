
package co.tyec.layeredTestingExamples;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;

import co.tyec.layeredTestingExamples.conf.DataSourceConf;
import co.tyec.layeredTestingExamples.database.LteDatabaseConnectionFactory;

/**
 * Created by yorta01 on 3/15/2016.
 */
public class LteDbBaseTest
{

    protected static DataSourceConf dataSourceConf = new DataSourceConf("test");

    protected static LteDatabaseConnectionFactory lteDatabaseConnectionFactory = new LteDatabaseConnectionFactory(dataSourceConf);

    protected static Connection connection;

    @BeforeClass
    public static void beforeLteDbBaseTestClass() throws SQLException
    {
        connection = lteDatabaseConnectionFactory.provide();
    }

}
