
package co.tyec.layeredTestingExamples.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import co.tyec.layeredTestingExamples.LteDbBaseTest;
import co.tyec.layeredTestingExamples.runSql.RunSql;
import co.tyec.layeredTestingExamples.runSql.RunSqlWatcher;

/**
 * Created by yorta01 on 3/10/2016.
 */
public class CalculatorEventDaoTest extends LteDbBaseTest
{

    @Rule
    public RunSqlWatcher runSqlWatcher = new RunSqlWatcher(connection);

    @Test
    @RunSql(beforeSqlFile = "before_calculator_events.sql")
    public void testGetById() throws SQLException
    {
        CalculatorEventDao calculatorEventDao = new CalculatorEventDaoImpl();
        calculatorEventDao.setConnection(connection);

        CalculatorEvent event1 = calculatorEventDao.getCalculatorEvent(1);

        Assert.assertNotNull(event1);
        Assert.assertEquals("add", event1.getOperator());
        Assert.assertEquals(1.0, event1.getOperandA());
        Assert.assertEquals(2.0, event1.getOperandB());
    }

    @Test
    @RunSql(beforeSqlFile = "before_calculator_events.sql")
    public void testGetAll() throws SQLException
    {
        CalculatorEventDao calculatorEventDao = new CalculatorEventDaoImpl();
        calculatorEventDao.setConnection(connection);

        List<CalculatorEvent> events = calculatorEventDao.getAllCalculatorEvents();

        Assert.assertNotNull(events);
        Assert.assertEquals(1, events.size());
    }

    /**
     * This was used, but @RunSql is a better way to do this.
     * @throws SQLException
     */
    public static void setupCalculatorEventsTable() throws SQLException
    {
        // create table
        Statement statement = connection.createStatement();
        String createCalculatorEventsTable = "CREATE TABLE calculator_events (id long IDENTITY PRIMARY KEY, operator VARCHAR(20), operandA VARCHAR(20), operandB VARCHAR(20));";
        boolean createSuccess = statement.execute(createCalculatorEventsTable);
        System.out.println("Create Table Success: " + createSuccess);

        // insert data
        statement = connection.createStatement();
        String insertCalculatorEventsTable = "INSERT INTO calculator_events VALUES (1, 'add', '1', '2');";
        int rowsInserted = statement.executeUpdate(insertCalculatorEventsTable);

        System.out.println("Inserted Rows into Table: " + rowsInserted);
        System.out.println();
        if (rowsInserted < 1)
        {
            throw new RuntimeException("Failed to populate calculator_events table");
        }
    }
}
