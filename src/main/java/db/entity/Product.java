package db.entity;

public class Product {
    private int idproducts;
    private String name;
    private int quantity;
    private double weight;
    private boolean tonnage;
    private double price;
    public int getIdproducts() {return idproducts;}

    public void setIdproducts(int idproducts) {this.idproducts = idproducts;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public double getWeight() {return weight;}

    public void setWeight(double weight) {this.weight = weight;}
    public boolean getTonnage() {return tonnage;}
    public void setTonnage(boolean tonnage) {this.tonnage = tonnage;}
    public static int boolToInt(boolean tonnage) {
        return Boolean.compare(tonnage, false);
    }
    public double getPrice() {return price;}

    public void setPrice(double price) {this.price= price;}
}
