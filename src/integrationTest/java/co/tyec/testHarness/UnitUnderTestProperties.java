
package co.tyec.testHarness;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;

/**
 * Created by yorta01 on 2/18/2016.
 */
public class UnitUnderTestProperties
{

    /**
     * There are Multiple ways to start a test: <br/>
     * 1. Running a test method, class, or suite via the IDE <br/>
     * 2. Running a test method, class, or suite via Gradle/Maven 'test' target <br/>
     * <br/>
     * Gradle/Maven Usage: <br/>
     * <code>gradle test -Duut.propfile=hostname1001.properties</code> <br/>
     * -D sets the JVM System Property 'uut.propfile'.  <br/>
     * <br/>
     * When you run via an IDE such as Gradle or Eclipse, you can specify command line arguments and JVM system properties such as
     * uut.propfile.  However, this can be annoying in
     * Eclipse/Intellij when you right click on a unit test and select "Run JUnit" because you do not have a chance to set uut.propfile.
     * The solution to this is to edit the UUTDefaults.properties and set the correct uut.propfile. <br/>
     */
    public static void setupUutPropfile()
    {
        System.out.println("Loading UUTDefaults.properties from classpath:/"
                        + UnitUnderTestProperties.class.getCanonicalName().replace(".", "/") + "/UUTDefaults.properties");
        Properties testHarnessProperties = new Properties();

        InputStream testHarnessDefaultsIS = UnitUnderTestProperties.class.getResourceAsStream("UUTDefaults.properties");
        loadPropFileStreamIntoProperties(testHarnessDefaultsIS, "UUTDefaults.properties", testHarnessProperties);

        String uutPropfile = testHarnessProperties.getProperty("uut.propfile");
        if (uutPropfile == null || uutPropfile.isEmpty())
        {
            Assert.fail("uut.properties has not been set. Initialization failed. Please set uut.properties via java command line -Duut.propfile=filename or add a default to UUTDefaults.properties");
        }
        // We'll allow two locations.
        // classpath:/filename.properties
        // or
        // classpath:/co/tyec/testHarness/filename.properties

        // First try classpath:/co/tyec/testHarness/filename.properties
        InputStream uutPropFileStream = UnitUnderTestProperties.class.getResourceAsStream(uutPropfile);
        if (uutPropFileStream != null)
        {
            System.out.println("Loading uut.propfile from classpath:/" + UnitUnderTestProperties.class.getCanonicalName().replace(".", "/")
                            + "/" + uutPropfile);
        }
        else
        {
            // next try classpath:/filename.properties
            uutPropFileStream = UnitUnderTestProperties.class.getClassLoader().getResourceAsStream(uutPropfile);

            if (uutPropFileStream != null)
            {
                System.out.println("Loading uut.propfile from classpath:/"
                                + UnitUnderTestProperties.class.getCanonicalName().replace(".", "/") + "/" + uutPropfile);
            }
            else
            {
                Assert.fail("The uut.propfile resource [" + uutPropfile + "] was not found on the classpath. Initialization failed.");
            }
        }
        loadPropFileStreamIntoProperties(uutPropFileStream, uutPropfile, testHarnessProperties);
    }

    public static void loadPropFileStreamIntoProperties(InputStream propFileInputStream, String propFilename,
                                                        Properties testHarnessProperties)
    {
        if (propFileInputStream == null)
        {
            Assert.fail(propFilename + " resource was not found on the classpath. Initialization failed!");
        }
        Properties propertiesHandler = new Properties();
        try
        {
            propertiesHandler.load(propFileInputStream);
            for (String name : propertiesHandler.stringPropertyNames())
            {
                String value = propertiesHandler.getProperty(name);
                String orig = System.getProperty(name, testHarnessProperties.getProperty(name));
                if (orig == null || orig.isEmpty())
                {
                    testHarnessProperties.setProperty(name, value);
                    System.out.println(String.format("%s - Set: [%s = %s]", propFilename, name, value));
                }
                else
                {
                    System.out.println(String.format("%s - Ignored: [%s = %s] System value: [%s]", propFilename, name, value, orig));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Assert.fail(propFilename + " resource was found but could not be loaded!");
        }
    }

}
