
package co.tyec.layeredTesting.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.paulhammant.ngwebdriver.NgWebDriver;

/**
 * Created by yorta01 on 2/16/2016.
 */
public class LTENavigation
{

    private final WebDriver webDriver;

    public LTENavigation(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public LTENavigation goToIndex(){
        webDriver.get("http://localhost:8888/LayeredTestingExamples/index.html");

        return this;
    }


    public LTENavigation goToCalculator(){
        webDriver.get("http://localhost:8888/LayeredTestingExamples/calculator.html");

        NgWebDriver ngWebDriver = new NgWebDriver((JavascriptExecutor) webDriver);
        ngWebDriver.waitForAngularRequestsToFinish();

        return this;
    }
}
