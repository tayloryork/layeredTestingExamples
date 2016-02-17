
package co.tyec.layeredTestingExamples.services;

import javax.inject.Singleton;

/**
 * Created by yorta01 on 2/8/2016.
 */
@Singleton
public class ComplexCalculatorService
{

    public ComplexCalculatorService()
    {
        System.out.println("Instantiated ComplexCalcuatorService");
    }

    public int add(int x, int y)
    {
        return x + y;
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
