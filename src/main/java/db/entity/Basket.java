package db.entity;

public class Basket {
    private int idreceipt;
    private int idproduct;
    private String name;
    private int quantity;
    private double weight;
    private boolean tonnage;
    private double price;

    public int getIdreceipt() {return idreceipt;}

    public void setIdreceipt(int idreceipt) {this.idreceipt = idreceipt;}

    public int getIdproduct() {return idproduct;}

    public void setIdproduct(int idproduct) {this.idproduct = idproduct;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public double getWeight() {return weight;}

    public void setWeight(double weight) {this.weight = weight;}

    public boolean isTonnage() {return tonnage;}

    public void setTonnage(boolean tonnage) {this.tonnage = tonnage;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}
}
