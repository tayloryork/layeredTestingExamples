
package co.tyec.testFramework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;

/**
 * Created by yorta01 on 2/18/2016.
 *
 *
 * I don't like that I'm using static methods.  Maybe I can do a DI @Singleton? <br/>
 * We have to have a way to run this in the build multiple times:
 * 1. test
 * 2. restTest
 * 3. integrationTest
 *
 * This is easy for gradle tasks, because we can set the system property.
 * It's more difficult to run from the IDE because the different tests should load different
 * prop files. <br/>
 * This is solvable via beforeClass/initialization methods in UnitBaseTest, RestBaseTest, IntBaseTest, etc.
 *
 */
public class UnitUnderTestProperties
{

    private static Properties uutProperties;

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
    public static void init()
    {
        uutProperties = new Properties();

        try
        {
            loadUutDefaults();
            loadUutPropfile();
        }
        catch (Exception e)
        {
            throw new RuntimeException("UnitUnderTestProperties initalization has failed!", e);
        }
    }

    private static void loadUutDefaults() throws Exception
    {
        System.out.println("Loading UUTDefaults.properties from classpath:/"
                        + UnitUnderTestProperties.class.getCanonicalName().replace(".", "/") + "/UUTDefaults.properties");
        Properties testHarnessProperties = new Properties();

        InputStream testHarnessDefaultsIS = UnitUnderTestProperties.class.getResourceAsStream("UUTDefaults.properties");
        loadPropFileStreamIntoProperties(testHarnessDefaultsIS, "UUTDefaults.properties");
    }

    private static void loadUutPropfile()
    {
        String uutPropfile = uutProperties.getProperty("uut.propfile");
        if (uutPropfile == null || uutPropfile.isEmpty())
        {
            Assert.fail("uut.properties has not been set. Initialization failed. Please set uut.properties via java command line -Duut.propfile=filename or add a default to UUTDefaults.properties");
        }
        // We'll allow two locations.
        // classpath:/filename.properties
        // or
        // classpath:/co/tyec/testFramework/filename.properties

        // First try classpath:/co/tyec/testFramework/filename.properties
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
        loadPropFileStreamIntoProperties(uutPropFileStream, uutPropfile);
    }

    public static void loadPropFileStreamIntoProperties(InputStream propFileInputStream, String propFilename)
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
                String orig = System.getProperty(name, uutProperties.getProperty(name));
                if (orig == null || orig.isEmpty())
                {
                    uutProperties.setProperty(name, value);
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

    public static String get(String name)
    {
        return get(name, "");
    }

    public static String get(String name, String defaultValue)
    {
        if (uutProperties == null)
        {
            init();
        }
        return uutProperties.getProperty(name, defaultValue);
    }

    public static void set(String name, String value)
    {
        if (uutProperties == null)
        {
            init();
        }
        uutProperties.setProperty(name, value);
    }
}
