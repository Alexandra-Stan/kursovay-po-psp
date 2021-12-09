package model;

public class Discount {
    private int id;
    private String name;
    private String surname;
    private String dnumber;
    private String shopName;

    public Discount(int id, String name, String surname, String dnumber, String shopName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dnumber = dnumber;
        this.shopName = shopName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dnumber='" + dnumber + '\'' +
                ", shopName='" + shopName + '\'' +
                '}';
    }
}
