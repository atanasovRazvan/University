public class ComplexNumber {

    private Integer re, im;

    public ComplexNumber(Integer re, Integer im) {
        this.re = re;
        this.im = im;
    }

    public Integer getRe() {
        return re;
    }

    public void setRe(Integer re) {
        this.re = re;
    }

    public Integer getIm() {
        return im;
    }

    public void setIm(Integer im) {
        this.im = im;
    }

    public ComplexNumber adunare(ComplexNumber number){
        return new ComplexNumber(this.re + number.getRe(), this.im + number.getIm());
    }

    public ComplexNumber scadere(ComplexNumber number){
        return new ComplexNumber(this.re - number.getRe(), this.im - number.getIm());
    }

    public ComplexNumber inmultire(ComplexNumber number){
        return new ComplexNumber(this.re * number.getRe() - this.im * number.getIm(),
                this.re * number.getIm() + this.im * number.getRe());
    }

    public ComplexNumber impartire(ComplexNumber number){
        int nominator = (number.getRe() * number.getRe() + number.getIm() * number.getIm());
        return new ComplexNumber((this.re * number.getRe() + this.im * number.getIm()) / nominator,
                (this.im * number.getRe() - this.re * number.getIm()) / nominator);
    }

    public ComplexNumber conjugatul(){
        return new ComplexNumber(this.re, -this.im);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.re);
        if(this.im >= 0) string.append('+');
        string.append(this.im);
        string.append("*i");
        return string.toString();
    }
}
