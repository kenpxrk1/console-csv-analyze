public class MonthTransaction {
    public String itemName;
    public Boolean isExpense;
    public int quantity;
    public int unitPrice;

    public MonthTransaction(String itemName, Boolean isExpense, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}