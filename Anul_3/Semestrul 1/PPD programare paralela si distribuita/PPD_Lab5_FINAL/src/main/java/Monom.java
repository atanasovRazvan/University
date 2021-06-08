public class Monom {

    private Integer exponent;
    private Integer coefficient;

    public Monom(Integer exponent, Integer coefficient) {
        this.exponent = exponent;
        this.coefficient = coefficient;
    }

    public Integer getExponent() {
        return exponent;
    }

    public void setExponent(Integer exponent) {
        this.exponent = exponent;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }
}
