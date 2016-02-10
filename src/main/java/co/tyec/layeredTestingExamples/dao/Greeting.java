
package co.tyec.layeredTestingExamples.dao;

/**
 * Created by yorta01 on 2/8/2016.
 */
public class Greeting
{

    private final long id;

    private final String content;

    public Greeting(long id, String content)
    {
        this.id = id;
        this.content = content;
    }

    public long getId()
    {
        return id;
    }

    public String getContent()
    {
        return content;
    }

}
