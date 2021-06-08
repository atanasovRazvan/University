package ubb.scs.map.ir.sem1.utils;

public class ArrayExceptions extends Throwable {
    private String mesaj;

    public ArrayExceptions(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getMesaj(){
        return mesaj;
    }
}
