
package co.tyec.layeredTestingExamples.dao;

/**
 * Created by yorta01 on 3/102016.
 */
public class CalculatorEvent
{

    private final long id;

    private final String operator;

    private final Number operandA;

    private final Number operandB;

    public CalculatorEvent(long id, String operator, Number operandA, Number operandB)
    {
        this.id = id;
        this.operator = operator;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    public long getId()
    {
        return id;
    }

    public String getOperator()
    {
        return operator;
    }

    public Number getOperandA()
    {
        return operandA;
    }

    public Number getOperandB()
    {
        return operandB;
    }

    @Override
    public String toString()
    {
        return String.format("{id: %d, operator: %s, operandA: %f, operandB: %f}", id, operator, operandA, operandB);
    }
}
