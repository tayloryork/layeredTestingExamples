
package co.tyec.layeredTestingExamples;

import java.lang.annotation.Annotation;
import java.sql.Connection;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import co.tyec.layeredTestingExamples.dao.CalculatorEventDao;
import co.tyec.layeredTestingExamples.dao.CalculatorEventDaoImpl;
import co.tyec.layeredTestingExamples.database.LteDatabaseConnectionFactory;
import co.tyec.layeredTestingExamples.services.ComplexCalculatorService;

/**
 * Created by yorta01 on 2/16/2016.
 */
public class LayeredTestingExamplesBinder extends AbstractBinder
{
    @Override
    protected void configure()
    {
        System.out.println("Configuring Application Binder");
        bind(ComplexCalculatorService.class).to(ComplexCalculatorService.class);

        bind(CalculatorEventDaoImpl.class).to(CalculatorEventDao.class);
        bind(MyTestClass.class).to(MyTestClass.class);

        bindFactory(LteDatabaseConnectionFactory.class).to(Connection.class);
    }
}
