package db.entity;

public class Receipt {

    private int idreceipt;
    private String cashier_name;
    private String closing_time;
    private double total_sum;

    public int getIdreceipt() {
        return idreceipt;
    }

    public void setIdreceipt(int idreceipt) {
        this.idreceipt = idreceipt;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    public double getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(double total_sum) {
        this.total_sum = total_sum;
    }

    public String getClosing_time() {return closing_time;}

    public void setClosing_time(String closing_time) {this.closing_time = closing_time;}
}
