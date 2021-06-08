import java.util.List;

public class SubstractExpression extends ComplexExpression {


    public SubstractExpression(List<ComplexNumber> args) {
        super(Operation.SUBSTRACTION, args);
    }

    @Override
    public ComplexNumber executeOneOperation(ComplexNumber number1, ComplexNumber number2) {
        return number1.scadere(number2);
    }
}
