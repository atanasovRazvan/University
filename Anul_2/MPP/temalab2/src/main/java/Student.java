public class Student {

    public Integer nrMatricol;
    public Integer varsta;
    public String nume, prenume, CNP;

    public Student(Integer nrMatricol, String nume, String prenume, Integer varsta, String CNP) {
        this.nrMatricol = nrMatricol;
        this.varsta = varsta;
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
    }

    public Integer getNrMatricol() {
        return nrMatricol;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCNP() {
        return CNP;
    }

}
