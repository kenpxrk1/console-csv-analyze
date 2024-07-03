import java.util.ArrayList;


public class MonthReport {
    FileReader fileReader = new FileReader();

    public ArrayList<MonthTransaction> transactions = new ArrayList<>();


    public void loadFile(String filename) {
        ArrayList<String> lines = fileReader.readFileContents(filename);
        for (int j = 1; j < lines.size(); j++) {
            String[] parts = lines.get(j).split(",");
            String itemName = parts[0];
            Boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int unitPrice = Integer.parseInt(parts[3]);
            MonthTransaction transaction = new MonthTransaction(itemName, isExpense, quantity, unitPrice);
            transactions.add(transaction);
        }
    }

    public int getExpenses(){
        int totalExpense = 0;
        for (MonthTransaction transaction : transactions){
            if (transaction.isExpense){
                totalExpense += transaction.quantity * transaction.unitPrice;
            }
        }
        return totalExpense;
    }

    public int getIncomes(){
        int totalIncomes = 0;
        for (MonthTransaction transaction : transactions){
            if (!transaction.isExpense){
                totalIncomes += transaction.quantity * transaction.unitPrice;
            }
        }
        return totalIncomes;
    }

    public MonthTransaction getMostProfitTransaction(){
        MonthTransaction maxProfit = null;
        for (MonthTransaction month : transactions){
            if (!month.isExpense) {
                if (maxProfit == null){
                    maxProfit = month;
                }
                int monthProfit = month.unitPrice * month.quantity;

                if (monthProfit > maxProfit.unitPrice * maxProfit.quantity){
                    maxProfit = month;
                }

            }
        }
        return maxProfit;
    }

    public MonthTransaction getMostUnProfitTransaction(){
        MonthTransaction minProfit = null;
        for (MonthTransaction month : transactions){
            if (month.isExpense) {
                if (minProfit == null){
                    minProfit = month;
                }
                int monthProfit = month.unitPrice * month.quantity;

                if (monthProfit < minProfit.unitPrice * minProfit.quantity){
                    minProfit = month;
                }

            }
        }
        return minProfit;
    }

}
