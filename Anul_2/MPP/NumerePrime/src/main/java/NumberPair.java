public class NumberPair {

    private Integer number1;
    private Integer number2;

    public NumberPair(Integer number1, Integer number2){
        this.number1 = number1;
        this.number2 = number2;
    }

    public Integer getCMMDC(){

        Integer nr1 = this.number1, nr2 = this.number2;
        int rest;
        while(nr2 > 0){
            rest = nr1 % nr2;
            nr1 = nr2;
            nr2 = rest;
        }
        return nr1;

    }

}
