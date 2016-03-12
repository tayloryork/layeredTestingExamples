
package co.tyec.layeredTestingExamples.services;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jvnet.hk2.annotations.Service;

import co.tyec.layeredTestingExamples.MyTestClass;
import co.tyec.layeredTestingExamples.dao.CalculatorEventDao;

/**
 * Created by yorta01 on 2/8/2016.
 */
@Singleton
@Service
public class ComplexCalculatorService
{
    @Inject
    MyTestClass myTestClass;

    @Inject
    CalculatorEventDao calculatorEventDao;

    public ComplexCalculatorService()
    {
        System.out.println("Instantiated ComplexCalcuatorService");
    }

    public int add(int x, int y)
    {
        System.out.println("From ComplexCalculatorService, myTestClass: " + myTestClass.toString());
        int answer = x + y;
        try
        {
            calculatorEventDao.addEvent("add", x, y);
        }
        catch(SQLException e)
        {
            // just log it
            e.printStackTrace();
        }
        return answer;
    }

    public int subtract(int x, int y)
    {
        return x - y;
    }

    public int multiply(int x, int y)
    {
        return x * y;
    }

    public int divide(int x, int y)
    {
        if (y == 0)
        {
            throw new RuntimeException("Cannot divide by 0");
        }
        return x / y;
    }
}
