
package co.tyec.layeredTestingExamples.runSql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yorta01 on 3/15/2016.
 */

/**
 * Add <code>@Rule RunSqlWatcher runSqlWatcher = new RunSqlWatcher(connection);</code>
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface RunSql
{

    public String beforeSqlFile() default "";

    public String beforeSql() default "";

    public String afterSqlFile() default "";

    public String afterSql() default "";
}
