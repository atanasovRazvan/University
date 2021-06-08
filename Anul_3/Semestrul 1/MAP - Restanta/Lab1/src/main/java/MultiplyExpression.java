import java.util.List;

public class MultiplyExpression extends ComplexExpression {


    public MultiplyExpression(List<ComplexNumber> args) {
        super(Operation.MULTIPLICATION, args);
    }

    @Override
    public ComplexNumber executeOneOperation(ComplexNumber number1, ComplexNumber number2) {
        return number1.inmultire(number2);
    }
}
