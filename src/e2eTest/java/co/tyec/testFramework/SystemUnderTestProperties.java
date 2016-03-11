
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
public class SystemUnderTestProperties
{

    private static Properties sutProperties;

    /**
     * There are Multiple ways to start a test: <br/>
     * 1. Running a test method, class, or suite via the IDE <br/>
     * 2. Running a test method, class, or suite via Gradle/Maven 'test' target <br/>
     * <br/>
     * Gradle/Maven Usage: <br/>
     * <code>gradle test -Dsut.propfile=hostname1001.properties</code> <br/>
     * -D sets the JVM System Property 'sut.propfile'.  <br/>
     * <br/>
     * When you run via an IDE such as Gradle or Eclipse, you can specify command line arguments and JVM system properties such as
     * sut.propfile.  However, this can be annoying in
     * Eclipse/Intellij when you right click on a unit test and select "Run JUnit" because you do not have a chance to set sut.propfile.
     * The solution to this is to edit the SutDefaults.properties and set the correct sut.propfile. <br/>
     */
    public static void init()
    {
        sutProperties = new Properties();

        try
        {
            loadSutDefaults();
            loadSutPropfile();
        }
        catch (Exception e)
        {
            throw new RuntimeException("SystemUnderTestProperties initalization has failed!", e);
        }
    }

    private static void loadSutDefaults() throws Exception
    {
        System.out.println("Loading SutDefaults.properties from classpath:/"
                        + SystemUnderTestProperties.class.getCanonicalName().replace(".", "/") + "/SutDefaults.properties");
        Properties testHarnessProperties = new Properties();

        InputStream testHarnessDefaultsIS = SystemUnderTestProperties.class.getResourceAsStream("SutDefaults.properties");
        loadPropFileStreamIntoProperties(testHarnessDefaultsIS, "SutDefaults.properties");
    }

    private static void loadSutPropfile()
    {
        String sutPropfile = sutProperties.getProperty("sut.propfile");
        if (sutPropfile == null || sutPropfile.isEmpty())
        {
            Assert.fail("sut.properties has not been set. Initialization failed. Please set sut.properties via java command line -Dsut.propfile=filename or add a default to SutDefaults.properties");
        }
        // We'll allow two locations.
        // classpath:/filename.properties
        // or
        // classpath:/co/tyec/testFramework/filename.properties

        // First try classpath:/co/tyec/testFramework/filename.properties
        InputStream sutPropFileStream = SystemUnderTestProperties.class.getResourceAsStream(sutPropfile);
        if (sutPropFileStream != null)
        {
            System.out.println("Loading sut.propfile from classpath:/" + SystemUnderTestProperties.class.getCanonicalName().replace(".", "/")
                            + "/" + sutPropfile);
        }
        else
        {
            // next try classpath:/filename.properties
            sutPropFileStream = SystemUnderTestProperties.class.getClassLoader().getResourceAsStream(sutPropfile);

            if (sutPropFileStream != null)
            {
                System.out.println("Loading sut.propfile from classpath:/"
                                + SystemUnderTestProperties.class.getCanonicalName().replace(".", "/") + "/" + sutPropfile);
            }
            else
            {
                Assert.fail("The sut.propfile resource [" + sutPropfile + "] was not found on the classpath. Initialization failed.");
            }
        }
        loadPropFileStreamIntoProperties(sutPropFileStream, sutPropfile);
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
                String orig = System.getProperty(name, sutProperties.getProperty(name));
                if (orig == null || orig.isEmpty())
                {
                    sutProperties.setProperty(name, value);
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
        if (sutProperties == null)
        {
            init();
        }
        return sutProperties.getProperty(name, defaultValue);
    }

    public static void set(String name, String value)
    {
        if (sutProperties == null)
        {
            init();
        }
        sutProperties.setProperty(name, value);
    }
}
