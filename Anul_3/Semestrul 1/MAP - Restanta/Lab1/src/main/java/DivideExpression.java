import java.util.List;

public class DivideExpression extends ComplexExpression {


    public DivideExpression(List<ComplexNumber> args) {
        super(Operation.DIVISION, args);
    }

    @Override
    public ComplexNumber executeOneOperation(ComplexNumber number1, ComplexNumber number2) {
        return number1.impartire(number2);
    }
}
