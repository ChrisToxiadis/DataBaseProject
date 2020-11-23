package home.transaction;


public class TransactionModel {
    private String brand;
    private String model;
    private String color;
    private String condition;
    private String name;
    private String surname;
    private String date;
    private String price;
    
    
    
    
    
    public TransactionModel(String brand, String model, String color, String condition, String name, String surname, String date, String price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.condition = condition;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.price = price;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
}