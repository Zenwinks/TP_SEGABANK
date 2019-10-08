package bo;

import java.util.List;

public class Agence {

    private int id;
    private String code;
    private String address;
    private List<Compte> comptes;

    public Agence(){

    }

    public Agence(int id, String code, String address, List<Compte> comptes) {
        this.id = id;
        this.code = code;
        this.address = address;
        this.comptes = comptes;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", comptes=" + comptes +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}
