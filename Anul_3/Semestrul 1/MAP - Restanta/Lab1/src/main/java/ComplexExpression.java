import java.util.List;

public abstract class ComplexExpression {

    private Operation operation;
    private List<ComplexNumber> args;

    public ComplexExpression(Operation operation, List<ComplexNumber> args) {
        this.operation = operation;
        this.args = args;
    }

    public abstract ComplexNumber executeOneOperation(ComplexNumber number1, ComplexNumber number2);

    public ComplexNumber execute(){
        ComplexNumber complexNumber = this.args.get(0);
        for(int i = 1; i < args.size(); i ++)
            complexNumber = this.executeOneOperation(complexNumber, this.args.get(i));
        return complexNumber;
    }

}
