
package co.tyec.layeredTestingExamples.runSql;

import org.junit.Rule;
import org.junit.Test;

import co.tyec.layeredTestingExamples.LteDbBaseTest;

/**
 * Created by yorta01 on 3/15/2016.
 */
public class RunSqlTest extends LteDbBaseTest
{

    @Rule
    public RunSqlWatcher runSqlWatcher = new RunSqlWatcher(connection);

    @Test
    @RunSql(beforeSqlFile = "someDbScript1.sql", beforeSql = "SELECT 'before sql' FROM dual", afterSqlFile = "someDbScript2.sql", afterSql = "SELECT 'after sql' FROM dual")
    public void test1()
    {
        System.out.println("In test1");
    }

}
