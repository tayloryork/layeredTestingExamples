
package co.tyec.layeredTestingExamples.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by yorta01 on 3/10/2016.
 */
public class CalculatorEventDaoTest
{

    static InMemoryDb inMemoryDb = new InMemoryDb();

    static Connection connection = inMemoryDb.getConnection();

    @BeforeClass
    public static void beforeCalculatorEventDaoTestClass() throws SQLException
    {
        inMemoryDb.setupCalculatorEventsTable();
    }

    @Test
    public void testGetById() throws SQLException
    {
        CalculatorEventDao calculatorEventDao = new CalculatorEventDao(connection);
        CalculatorEvent event1 = calculatorEventDao.getCalculatorEvent(1);

        Assert.assertNotNull(event1);
        Assert.assertEquals("add", event1.getOperator());
        Assert.assertEquals(1.0, event1.getOperandA());
        Assert.assertEquals(2.0, event1.getOperandB());
    }

    @Test
    public void testGetAll() throws SQLException
    {
        CalculatorEventDao calculatorEventDao = new CalculatorEventDao(connection);
        List<CalculatorEvent> events = calculatorEventDao.getAllCalculatorEvents();

        Assert.assertNotNull(events);
        Assert.assertEquals(1, events.size());
    }

}
