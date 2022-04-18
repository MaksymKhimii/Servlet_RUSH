package db.entity;

/** RU: сущность представляет информацию об отчетах
 * ENG: entity represents information about reports
 */
public class Report {

    private int idreport;
    private int quantityOfReceipts;
    private int lastReceiptId;
    private String time;
    private double totalSum;

    public int getIdreport() {return idreport;}

    public void setIdreport(int idreport) {this.idreport = idreport;}

    public int getQuantityOfReceipts() {return quantityOfReceipts;}

    public void setQuantityOfReceipts(int quantityOfReceipts) {this.quantityOfReceipts = quantityOfReceipts;}

    public int getLastReceiptId() {return lastReceiptId;}

    public void setLastReceiptId(int lastReceiptId) {this.lastReceiptId = lastReceiptId;}

    public String getTime() {return time;}

    public void setTime(String time) {this.time = time;}

    public double getTotalSum() {return totalSum;}

    public void setTotalSum(double totalSum) {this.totalSum = totalSum;}
}
