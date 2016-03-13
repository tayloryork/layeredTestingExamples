
package co.tyec.layeredTestingExamples.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.glassfish.jersey.spi.Contract;

/**
 * Created by yorta01 on 2/8/2016.
 */
@Contract
public interface CalculatorEventDao
{
    public List<CalculatorEvent> getAllCalculatorEvents() throws SQLException;

    public CalculatorEvent getCalculatorEvent(long idArg) throws SQLException;

    public CalculatorEvent addEvent(String operator, int operandA, int operandB) throws SQLException;

    void setConnection(Connection connection);
}
