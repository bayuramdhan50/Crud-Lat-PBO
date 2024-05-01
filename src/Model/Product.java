package Model;

public class Product {
    private String productName;
    private double price;
    private int quantityAvailable;
    private String description;

    // Constructor
    public Product(String productName, double price, int quantityAvailable, String description) {
        this.productName = productName;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.description = description;
    }

    // Getter dan setter untuk setiap atribut
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}