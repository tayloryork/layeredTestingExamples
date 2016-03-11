
package co.tyec.layeredTestingExamples;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

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
        bind(new ComplexCalculatorService()).to(ComplexCalculatorService.class);
    }
}
