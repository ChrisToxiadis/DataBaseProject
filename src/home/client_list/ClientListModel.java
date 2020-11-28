package home.client_list;


public class ClientListModel {
    private String id;
    private String name;
    private String surname;
    private String phone;
    private String country;

    public ClientListModel(String id, String name, String surname, String phone, String country) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}