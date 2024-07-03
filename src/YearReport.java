import java.util.ArrayList;

public class YearReport {
    FileReader fileReader = new FileReader();
    public ArrayList<YearTransaction> transactions = new ArrayList<>();


    public void loadFile(String filename) {
        ArrayList<String> lines = fileReader.readFileContents(filename);
        for (int j = 1; j < lines.size(); j++) {
            String[] parts = lines.get(j).split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YearTransaction transaction = new YearTransaction(month, amount, isExpense);
            transactions.add(transaction);
        }
    }

    public int getExpensesPerMonth(int monthNumber){
        int totalExpenses = 0;
        for (YearTransaction transaction : transactions){
            if (transaction.month == monthNumber && transaction.isExpense){
                totalExpenses += transaction.amount;
            }
        }
        return totalExpenses;
    }

    public int getIncomesPerMonth(int monthNumber){
        int totalIncomes = 0;
        for (YearTransaction transaction : transactions){
            if (transaction.month == monthNumber && !transaction.isExpense){
                totalIncomes += transaction.amount;
            }
        }
        return totalIncomes;
    }

    public int getAverageExpenses(){
        int totalExpenses = 0;
        // counter считает количество операций
        int counter = 0;
        for (YearTransaction transaction : transactions){
            if (transaction.isExpense){
                totalExpenses += transaction.amount;
                counter++;
            }
        }
        return totalExpenses / counter;
    }

    public int getAverageIncomes(){
        int totalIncomes = 0;
        // counter считает количество операций
        int counter = 0;
        for (YearTransaction transaction : transactions){
            if (!transaction.isExpense){
                totalIncomes += transaction.amount;
                counter++;
            }
        }
        return totalIncomes / counter;
    }
}
