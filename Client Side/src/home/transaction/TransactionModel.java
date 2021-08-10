package home.transaction;


public class TransactionModel {
    private String date;
    private String car_id;
    private String brand;
    private String model;
    private String color;
    private String condition;
    private String price;
    private String customer_id;
    private String name;
    private String surname;
    private String country;

    
    public TransactionModel(String date, String car_id, String brand, String model, String color, String condition, String price, String customer_id, String name, String surname, String country) {
        this.date = date;
        this.car_id = car_id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.condition = condition;
        this.price = price;
        this.customer_id = customer_id;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
    
    
    

    
    
}