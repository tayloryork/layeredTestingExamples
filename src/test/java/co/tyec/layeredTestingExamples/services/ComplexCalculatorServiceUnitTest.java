
package co.tyec.layeredTestingExamples.services;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import co.tyec.layeredTestingExamples.dao.CalculatorEventDao;

/**
 * Created by yorta01 on 2/17/2016.
 */
public class ComplexCalculatorServiceUnitTest
{

    @Test
    public void addUnitTest()
    {
        ComplexCalculatorService complexCalculatorService = new ComplexCalculatorService();
        complexCalculatorService.calculatorEventDao = Mockito.mock(CalculatorEventDao.class);

        int result = complexCalculatorService.add(4, 9);

        Assert.assertEquals((4 + 9), result);
    }

    @Test
    public void divideUnitTest()
    {
        ComplexCalculatorService complexCalculatorService = new ComplexCalculatorService();
        complexCalculatorService.calculatorEventDao = Mockito.mock(CalculatorEventDao.class);

        Assert.assertEquals((100 / 2), complexCalculatorService.divide(100, 2));
    }

    @Test(expected = RuntimeException.class)
    public void divideByZeroTest()
    {
        ComplexCalculatorService complexCalculatorService = new ComplexCalculatorService();
        complexCalculatorService.calculatorEventDao = Mockito.mock(CalculatorEventDao.class);

        complexCalculatorService.divide(100, 0);
    }

}
