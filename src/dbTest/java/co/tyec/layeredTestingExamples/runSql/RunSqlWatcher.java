
package co.tyec.layeredTestingExamples.runSql;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Created by yorta01 on 3/15/2016.
 */
public class RunSqlWatcher extends TestWatcher
{

    private RunSql runSql;

    private RunSqls runSqls;

    private Connection connection;

    public RunSqlWatcher(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void starting(Description description)
    {
        runSql = description.getAnnotation(RunSql.class);
        runSqls = description.getAnnotation(RunSqls.class);

        System.out.println("RunSqlWatcher Starting RunSql: " + runSql);

        ArrayList<String> beforeSqls = getBeforeSqlsList(runSql, runSqls);
        ArrayList<String> beforeSqlFiles = getBeforeSqlFilesList(runSql, runSqls);

        // Run the script first, then the sql
        for (String current : beforeSqlFiles)
        {
            Class<?> testClass = description.getTestClass();
            executeFile(current, testClass);
        }
        for (String current : beforeSqls)
        {
            executeStatement(current);
        }
    }

    private ArrayList<String> getBeforeSqlsList(RunSql runSql, RunSqls runSqls)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (runSql != null)
        {
            list.add(runSql.beforeSql());
        }

        if (runSqls != null)
        {
            String[] beforeSqls = runSqls.beforeSqls();
            if (runSqls != null && beforeSqls != null && beforeSqls.length > 0)
            {
                for (String current : beforeSqls)
                {
                    list.add(current);
                }
            }
        }
        return list;
    }

    private ArrayList<String> getBeforeSqlFilesList(RunSql runSql, RunSqls runSqls)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (runSql != null)
        {
            list.add(runSql.beforeSqlFile());
        }

        if (runSqls != null)
        {
            String[] beforeSqlFiles = runSqls.beforeSqlFiles();
            if (runSqls != null && beforeSqlFiles != null && beforeSqlFiles.length > 0)
            {
                for (String current : beforeSqlFiles)
                {
                    list.add(current);
                }
            }
        }
        return list;
    }

    // after
    private ArrayList<String> getAfterSqlsList(RunSql runSql, RunSqls runSqls)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (runSql != null)
        {
            list.add(runSql.afterSql());
        }
        if (runSqls != null)
        {
            String[] afterSqls = runSqls.afterSqls();
            if (runSqls != null && afterSqls != null && afterSqls.length > 0)
            {
                for (String current : afterSqls)
                {
                    list.add(current);
                }
            }
        }
        return list;
    }

    private ArrayList<String> getAfterSqlFilesList(RunSql runSql, RunSqls runSqls)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (runSql != null)
        {
            list.add(runSql.afterSqlFile());
        }
        if (runSqls != null)
        {
            String[] afterSqlFiles = runSqls.afterSqlFiles();
            if (runSqls != null && afterSqlFiles != null && afterSqlFiles.length > 0)
            {
                for (String current : afterSqlFiles)
                {
                    list.add(current);
                }
            }
        }
        return list;
    }

    @Override
    public void finished(Description description)
    {
        runSql = description.getAnnotation(RunSql.class);
        runSqls = description.getAnnotation(RunSqls.class);

        System.out.println("RunSqlWatcher Finished RunSql: " + runSql);

        ArrayList<String> afterSqls = getAfterSqlsList(runSql, runSqls);
        ArrayList<String> afterSqlFiles = getAfterSqlFilesList(runSql, runSqls);

        // Run the script first, then the sql
        for (String current : afterSqlFiles)
        {
            Class<?> testClass = description.getTestClass();
            executeFile(current, testClass);
        }
        for (String current : afterSqls)
        {
            executeStatement(current);
        }

    }

    private void executeFile(String fileString, Class<?> testClass)
    {
        if (fileString == null || fileString.isEmpty())
        {
            return; // exit
        }
        InputStream inputStream = testClass.getResourceAsStream(fileString);
        if (inputStream == null)
        {
            inputStream = testClass.getClassLoader().getResourceAsStream(fileString);
        }
        if (inputStream == null)
        {
            throw new RuntimeException("RunSqlWatcher: Could not load file: " + fileString);
        }
        // get the file & it's contents
        String fileContents = streamToString(inputStream);

        // execute those contents
        executeStatement(fileContents);
    }

    private String streamToString(InputStream inputStream)
    {
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private boolean executeStatement(String sql)
    {
        if (sql == null || sql.isEmpty())
        {
            return false;
        }
        boolean success = false;
        try
        {
            System.out.println("RunSqlWatcher: Executing SQL: " + sql);
            if (connection != null)
            {
                Statement statement = connection.createStatement();
                success = statement.execute(sql);
            }
            else
            {
                System.err.println("RunSqlWatcher: Executing SQL failed! Connection is null");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println("RunSqlWatcher: Executing SQL Success: " + success);
        return success;
    }
}
