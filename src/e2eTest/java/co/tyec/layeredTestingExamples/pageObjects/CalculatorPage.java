package co.tyec.layeredTestingExamples.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

/**
 * Created by yorta01 on 2/16/2016.
 */
public class CalculatorPage
{

    private final WebDriver webDriver;

    By operand1By = By.name("x");

    By operand2By = By.name("y");

    By addButton = By.name("Add");

    By subtractButton = By.name("Subtract");

    By multiplyButton = By.name("Multiply");

    By divideButton = By.name("Divide");

    By resultBy = ByAngular.binding("result");

    public CalculatorPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public void setOperand1(String text)
    {
        WebElement el = webDriver.findElement(operand1By);
        el.clear();
        el.sendKeys(text);
    }

    public void setOperand2(String text)
    {
        WebElement el = webDriver.findElement(operand2By);
        el.clear();
        el.sendKeys(text);
    }

    public void clickAdd()
    {
        webDriver.findElement(addButton).click();
        waitForAngular();
    }

    public void clickSubtract()
    {
        webDriver.findElement(subtractButton).click();
        waitForAngular();
    }

    public void clickMultiply()
    {
        webDriver.findElement(multiplyButton).click();
        waitForAngular();
    }

    public void clickDivide()
    {
        webDriver.findElement(divideButton).click();
        waitForAngular();
    }

    public String getResult()
    {
        NgWebDriver ngWebDriver = new NgWebDriver((JavascriptExecutor) webDriver);
        ngWebDriver.waitForAngularRequestsToFinish();

        WebElement el = webDriver.findElement(resultBy);
        String resultText = el.getText();

        return resultText;
    }

    private void waitForAngular()
    {
        // Oddly enough, NgWebDriver doesn't set this.
        webDriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);

        new NgWebDriver((JavascriptExecutor) webDriver).waitForAngularRequestsToFinish();
    }
}
