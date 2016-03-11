
package co.tyec.layeredTestingExamples.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import co.tyec.testFramework.SystemUnderTestProperties;

import com.paulhammant.ngwebdriver.NgWebDriver;

/**
 * Created by yorta01 on 2/16/2016.
 */
public class LTENavigation
{

    private final WebDriver webDriver;

    String hostname = SystemUnderTestProperties.get("hostname");

    String port = SystemUnderTestProperties.get("port");

    String baseUrl = SystemUnderTestProperties.get("baseUrl");

    public LTENavigation(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public LTENavigation goToIndex()
    {
        String url = baseUrl + "index.html";
        System.out.println("Navigating to: " + url);
        webDriver.get(url);

        return this;
    }

    public LTENavigation goToCalculator()
    {
        String url = baseUrl + "calculator.html";
        System.out.println("Navigating to: " + url);
        webDriver.get(url);

        NgWebDriver ngWebDriver = new NgWebDriver((JavascriptExecutor) webDriver);
        ngWebDriver.waitForAngularRequestsToFinish();

        return this;
    }
}
