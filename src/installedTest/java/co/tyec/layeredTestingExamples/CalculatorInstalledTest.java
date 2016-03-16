
package co.tyec.layeredTestingExamples;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import co.tyec.layeredTestingExamples.pageObjects.CalculatorPage;
import co.tyec.layeredTestingExamples.pageObjects.LTENavigation;

/**
 * Created by yorta01 on 2/16/2016.
 */
public class CalculatorInstalledTest
{

    private static WebDriver webDriver;

    @AfterClass
    public static void afterMethod()
    {
        if (webDriver != null)
        {
            webDriver.quit();
        }
    }

    @Before
    public void beforeMethod()
    {
        // setup web driver
        if (webDriver == null)
        {
            webDriver = new FirefoxDriver();
        }

        // navigate to page
        LTENavigation lteNavigation = new LTENavigation(webDriver);
        lteNavigation.goToCalculator();
    }

    @Test
    public void testAdd()
    {
        CalculatorPage calculatorPage = new CalculatorPage(webDriver);
        calculatorPage.setOperand1("5");
        calculatorPage.setOperand2("6");
        calculatorPage.clickAdd();
        String addResult = calculatorPage.getResult();

        Assert.assertEquals("Calulator Integration failed...", "11", addResult);
    }

    @Test
    public void testSubtract()
    {
        CalculatorPage calculatorPage = new CalculatorPage(webDriver);
        calculatorPage.setOperand1("5");
        calculatorPage.setOperand2("6");
        calculatorPage.clickSubtract();
        String addResult = calculatorPage.getResult();

        Assert.assertEquals("Calulator Integration failed...", "-1", addResult);
    }

    @Test
    public void testMultiply()
    {
        CalculatorPage calculatorPage = new CalculatorPage(webDriver);
        calculatorPage.setOperand1("5");
        calculatorPage.setOperand2("6");
        calculatorPage.clickMultiply();
        String addResult = calculatorPage.getResult();

        Assert.assertEquals("Calulator Integration failed...", "30", addResult);
    }

    @Test
    public void testDivide()
    {
        CalculatorPage calculatorPage = new CalculatorPage(webDriver);
        calculatorPage.setOperand1("5");
        calculatorPage.setOperand2("6");
        calculatorPage.clickDivide();
        String addResult = calculatorPage.getResult();

        Assert.assertEquals("Calulator Integration failed...", "0", addResult);
    }
}
