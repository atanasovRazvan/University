package model;

public class Client {

    private Long id;
    private String name;
    private Integer fidelityGrade;
    private Integer age;


    public Client(String name, Integer fidelityGrade, Integer age) {
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.age = age;
    }

    public Client(){}


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFidelityGrade() {
        return fidelityGrade;
    }

    public void setFidelityGrade(Integer fidelityGrade) {
        this.fidelityGrade = fidelityGrade;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
