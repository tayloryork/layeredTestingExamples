package co.tyec.layeredTestingExamples.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jvnet.hk2.annotations.Service;

/**
 * Created by yorta01 on 3/13/2016.
 */
@Service
public class DataSourceConf
{

    private String datasource;
    // Okay, i'm unsure of the whole process right now. but here is the gist
    // Default environment, "dev"
    // "dev" = local h2
    // "prod" = mysql/postgres/oracle etc
    // "mem" = local in memory db....
    //
    // this whole thing just looks like a datasource selector...
    // what else would be in environment
    // what other environment stuff is there besides db.....
    // filesystem....
    // other servers. cloud/scale etc.
    // I would like these to be split up, select one thing at a time.
    // so i dont just want to set a profile.
    // i want to select individual things from the environment.
    // this is just a way to set properties & beans from the command line etc

    // create named pojos? Possibly by using HK2 serviceLocator...
    // they are just property files right....?
    // so this doesn't have to do with binding at all....

    public DataSourceConf()
    {
        this(null);
    }

    public DataSourceConf(String dataSourceArg)
    {
        this.datasource = dataSourceArg;
        System.out.println("Initializing DataSourceConf...");
    }

    public void setDatasource(String datasource)
    {
        this.datasource = datasource;
    }

    public String getUrl()
    {
        return getFromConf("jdbc.url");
    }

    public String getUsername()
    {
        return getFromConf("jdbc.username");
    }

    public String getPassword()
    {
        return getFromConf("jdbc.password");
    }

    private String getFromConf(String propKey)
    {
        Properties dbProperties = new Properties();
        try
        {
            dbProperties.load(getConfInputStream());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("DataSourceConf: Getting Property: " + propKey);
        return dbProperties.getProperty(propKey);
    }

    public InputStream getConfInputStream()
    {
        String defaultDataSourceName = "dev";
        String datasourceName = null;

        if(datasource == null)
        {
            // If this.datasource was not set via setDatasource, get it from System
            datasourceName = System.getProperty("datasource");

            System.out.println("DataSourceConf: datasource System property: " + datasourceName);
            if(datasourceName == null)
            {
                datasourceName = defaultDataSourceName;
                System.out.println("DataSourceConf: setting datasource to default: " + datasourceName);
            }
        }
        else
        {
            // this.datasource is already set, use that.
            datasourceName = datasource;
        }
        /// Example: "dev"
        // /src/main/resources/co/tyec/layeredTestingExamples/datasource_dev.properties

        String filename = "datasource_" + datasourceName + ".properties";
        System.out.println("DataSourceConf: filename: " + filename);

        InputStream configInputStream = DataSourceConf.class.getResourceAsStream(filename);
        System.out.println("DataSourceConf: configInputStream: " + String.valueOf(configInputStream));
        return configInputStream;
    }
}
