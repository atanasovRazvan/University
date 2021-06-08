public class Main {

    public static void main(String[] args) {
        ExpressionParser expressionParser = new ExpressionParser();

        try {
            ComplexExpression expression = expressionParser.parse(args);
            ComplexNumber result = expression.execute();
            System.out.println("Result: " + result.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
