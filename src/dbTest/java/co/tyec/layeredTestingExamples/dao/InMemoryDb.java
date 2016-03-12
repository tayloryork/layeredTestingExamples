package co.tyec.layeredTestingExamples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by yorta01 on 3/10/2016.
 */
public class InMemoryDb
{

    Connection connection = null;

    public Connection getConnection()
    {
        try
        {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
            System.out.println(
                    "Connection Established: " + connection.getMetaData().getDatabaseProductName() + "/" + connection.getCatalog());

        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public void setupCalculatorEventsTable() throws SQLException
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
        if(rowsInserted < 1)
        {
            throw new RuntimeException("Failed to populate calculator_events table");
        }
    }
}
