package co.tyec.layeredTestingExamples.services;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jvnet.hk2.annotations.Service;

import co.tyec.layeredTestingExamples.dao.CalculatorEventDao;

/**
 * Created by yorta01 on 2/8/2016.
 */
@Singleton
@Service
public class ComplexCalculatorService
{

    @Inject
    CalculatorEventDao calculatorEventDao;

    public ComplexCalculatorService()
    {
        System.out.println("Instantiated ComplexCalcuatorService");
    }

    public int add(int x, int y)
    {
        int answer = x + y;
        try
        {
            calculatorEventDao.addEvent("add", x, y);
        }
        catch (SQLException e)
        {
            // just log it
            e.printStackTrace();
        }
        return answer;
    }

    public int subtract(int x, int y)
    {
        int answer = x - y;
        try
        {
            calculatorEventDao.addEvent("subtract", x, y);
        }
        catch (SQLException e)
        {
            // just log it
            e.printStackTrace();
        }
        return answer;
    }

    public int multiply(int x, int y)
    {
        int answer = x * y;
        try
        {
            calculatorEventDao.addEvent("multiply", x, y);
        }
        catch (SQLException e)
        {
            // just log it
            e.printStackTrace();
        }
        return answer;
    }

    public int divide(int x, int y)
    {
        try
        {
            calculatorEventDao.addEvent("divide", x, y);
        }
        catch (SQLException e)
        {
            // just log it
            e.printStackTrace();
        }
        if (y == 0)
        {
            throw new RuntimeException("Cannot divide by 0");
        }
        int answer = x / y;
        return answer;
    }
}
