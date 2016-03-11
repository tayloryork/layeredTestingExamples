
package co.tyec.layeredTestingExamples.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yorta01 on 2/8/2016.
 */
public class CalculatorEventDao
{

    Connection connection;

    CalculatorEventDao(Connection connection)
    {
        this.connection = connection;
    }

    public List<CalculatorEvent> getAllCalculatorEvents() throws SQLException
    {
        ArrayList<CalculatorEvent> result = new ArrayList<CalculatorEvent>();
        Statement statement = connection.createStatement();
        String getQuery = "SELECT * FROM calculator_events ORDER by id";
        ResultSet resultSet = statement.executeQuery(getQuery);

        while (resultSet.next())
        {
            long id = resultSet.getLong(1);
            String operator = resultSet.getString(2);
            Number operandA = Double.parseDouble(resultSet.getString(3));
            Number operandB = Double.parseDouble(resultSet.getString(4));
            CalculatorEvent calculatorEvent = new CalculatorEvent(id, operator, operandA, operandB);
            System.out.println("Found Calculator Event (" + id + "): " + (calculatorEvent != null ? calculatorEvent.toString() : "null"));
            result.add(calculatorEvent);
        }

        return result;
    }

    public CalculatorEvent getCalculatorEvent(long idArg) throws SQLException
    {
        Statement statement = connection.createStatement();
        String getQuery = "SELECT * FROM calculator_events WHERE id = " + idArg;
        ResultSet resultSet = statement.executeQuery(getQuery);

        CalculatorEvent calculatorEvent = null;
        while (resultSet.next())
        {

            long id = resultSet.getLong(1);
            String operator = resultSet.getString(2);
            Number operandA = Double.parseDouble(resultSet.getString(3));
            Number operandB = Double.parseDouble(resultSet.getString(4));
            calculatorEvent = new CalculatorEvent(id, operator, operandA, operandB);
        }

        System.out.println("Found Calculator Event (" + idArg + "): " + (calculatorEvent != null ? calculatorEvent.toString() : "null"));

        return calculatorEvent;
    }
}
