import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpressionParser {

    public ComplexExpression parse(String[] args) throws Exception {

        List<ComplexNumber> complexList = new ArrayList<>();
        String operation = null;
        if(args.length > 1) operation = args[1];

        for(int i = 0; i < args.length; i ++){

            String string = args[i];
            if(string.equals("'*'"))
                string = "*";
            if((i%2 == 0 && !string.matches("-?[0-9]*([+-][0-9]*[*]i)?")) || (i%2 == 1 && !string.matches("[-+/*]")))
                throw new Exception("This expression is not valid");

            if(i%2 == 0){
                // Complex number

                //Real
                Integer re=0, state = 1, startAt = 0;
                if(args[i].charAt(0) == '-') {state = -1; startAt = 1;}
                for(; args[i].charAt(startAt) <= 57 &&  args[i].charAt(startAt) >= 48 ; startAt ++)
                    re = re * 10 + (args[i].charAt(startAt)-48);
                re = re * state;

                //Imaginary
                Integer im = 0;
                state = 1;
                if(args[i].charAt(startAt) == '-') state = -1;
                for(++startAt; args[i].charAt(startAt)<=57 && args[i].charAt(startAt)>=48; startAt++)
                    im = im * 10 + (args[i].charAt(startAt)-48);
                im = im * state;

                ComplexNumber complexNumber = new ComplexNumber(re, im);
                complexList.add(complexNumber);

            }

            if(i%2 == 1){
                // Operation
                if(!args[i].equals(operation)) throw new Exception("This expression is not valid");
            }

        }

        Operation op = null;
        if(operation.equals("'*'")) operation = "*";
        switch(Objects.requireNonNull(operation)){
            case "+": op = Operation.ADDITION; break;
            case "-": op = Operation.SUBSTRACTION; break;
            case "/": op = Operation.DIVISION; break;
            case "*": op = Operation.MULTIPLICATION;
        }

        ExpressionFactory expressionFactory = ExpressionFactory.getInstance();
        return expressionFactory.createExpression(op, complexList);

    }

}
