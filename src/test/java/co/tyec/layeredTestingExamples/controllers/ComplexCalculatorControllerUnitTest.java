
package co.tyec.layeredTestingExamples.controllers;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.tyec.layeredTestingExamples.dao.CalculatorEventDao;
import co.tyec.layeredTestingExamples.services.ComplexCalculatorService;

@RunWith(MockitoJUnitRunner.class)
public class ComplexCalculatorControllerUnitTest
{

    @Mock
    CalculatorEventDao calculatorEventDao;

    @InjectMocks
    ComplexCalculatorService complexCalculatorService;

    ComplexCalculatorController complexCalculatorController;

    @Before
    public void setupMethods()
    {
        complexCalculatorController = new ComplexCalculatorController();
        complexCalculatorController.calculatorService = complexCalculatorService;
    }

    @Test
    public void testAddRest() throws IOException
    {
        String addText = Integer.toString(complexCalculatorService.add(1, 2));
        Assert.assertEquals("3", addText);

        addText = complexCalculatorController.add(1, 2);
        Assert.assertEquals("3", addText);
    }

    @Test
    public void testSubtractRest() throws IOException
    {
        String addText = Integer.toString(complexCalculatorService.subtract(1, 2));
        Assert.assertEquals("-1", addText);

        addText = complexCalculatorController.subtract(5, 2);
        Assert.assertEquals("3", addText);
    }
}
