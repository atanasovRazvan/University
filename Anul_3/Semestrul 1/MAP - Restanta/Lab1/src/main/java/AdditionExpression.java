import java.util.List;

public class AdditionExpression extends ComplexExpression {

    public AdditionExpression(List<ComplexNumber> args) {
        super(Operation.ADDITION, args);
    }

    @Override
    public ComplexNumber executeOneOperation(ComplexNumber number1, ComplexNumber number2) {
        return number1.adunare(number2);
    }

}
