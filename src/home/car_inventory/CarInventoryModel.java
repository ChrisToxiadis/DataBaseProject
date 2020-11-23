package home.car_inventory;

public class CarInventoryModel {
   // private int id;
    private String brand;
    private String model;
    private String color;
    private String price;
    private String condition;
    
    
    
    public CarInventoryModel(){
        
    }

    public CarInventoryModel(String brand, String model, String color, String price, String condition) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
        this.condition = condition;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    
}