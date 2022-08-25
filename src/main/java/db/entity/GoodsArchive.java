package db.entity;

/**
 * RU: сущность представляет информацию о продукта уже созданных чеков
 * ENG: entity represents information about the product of already created checks
 */
public class GoodsArchive {

    private int idreceipt;
    private int idproduct;
    private String name;
    private int quantity;
    private double weight;
    private boolean tonnage;
    private double price;
    private double total_sum;

    public int getIdreceipt() {
        return idreceipt;
    }

    public void setIdreceipt(int idreceipt) {
        this.idreceipt = idreceipt;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isTonnage() {
        return tonnage;
    }

    public void setTonnage(boolean tonnage) {
        this.tonnage = tonnage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(double total_sum) {
        this.total_sum = total_sum;
    }
}
