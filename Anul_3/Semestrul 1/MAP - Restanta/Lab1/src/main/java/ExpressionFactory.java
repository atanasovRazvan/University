import java.util.ArrayList;
import java.util.List;

public class ExpressionFactory {

    private static final ExpressionFactory INSTANCE = new ExpressionFactory();

    public ExpressionFactory() {
    }

    public static ExpressionFactory getInstance() {
        return INSTANCE;
    }

    public ComplexExpression createExpression(Operation operation, List<ComplexNumber> list){

        if(operation == Operation.ADDITION)
            return new AdditionExpression(list);
        if(operation == Operation.SUBSTRACTION)
            return new SubstractExpression(list);
        if(operation == Operation.MULTIPLICATION)
            return new MultiplyExpression(list);
        if(operation == Operation.DIVISION)
            return new DivideExpression(list);

        return null;

    }

}
