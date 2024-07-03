import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

public class Checker {

    String startFilename = "m.20210";
    String fileExtension = ".csv";
    String yearFilename = "y.2021.csv";
    HashMap<Integer, MonthReport> monthData = new HashMap<>();
    private boolean isMonthLoaded = false;
    private boolean isYearLoaded = false;
    YearReport yearReport = new YearReport();


    void checkMonthAndYear() {
        if (!isMonthLoaded || !isYearLoaded) {
            System.out.println("Сперва нужно отсканировать отчеты!");
            return;
        }
        for (Integer monthNumber : monthData.keySet()) {
            int monthExpenses = monthData.get(monthNumber).getExpenses();
            int monthIncomes = monthData.get(monthNumber).getIncomes();
            int monthExpensesInYearData = yearReport.getExpensesPerMonth(monthNumber);
            int monthIncomesInYearData = yearReport.getIncomesPerMonth(monthNumber);

            if (monthExpenses != monthExpensesInYearData ||
                    monthIncomes != monthIncomesInYearData) {
                System.out.println("Ошибка в отчете! Отчет по " + monthNumber + "-му месяцу " +
                        "не сходится!");
                return;
            }
        }
        System.out.println("Отчеты сходятся!");

    }

    void scanMonthFiles() {
        for (int i = 1; i < 4; i++) {
            MonthReport monthReport = new MonthReport();
            String filename = startFilename + i + fileExtension;
            monthReport.loadFile(filename);
            if (!monthReport.transactions.isEmpty()) {
                monthData.put(i, monthReport);
            } else {
                System.out.println("Ошибка при чтении файла");
                return;
            }
            if (!monthData.isEmpty()) {
                isMonthLoaded = true;
            }
            System.out.println("Месячные отчеты успешно считаны!");
        }
    }

    void scanYearFile() {
        yearReport.loadFile(yearFilename);
        if (!yearReport.transactions.isEmpty()) {
            isYearLoaded = true;
            System.out.println("Годовые отчеты успешно считаны!");
        }
    }

    void printMonthInfo(){
        if (!isMonthLoaded){
            System.out.println("Сначала загрузите месячные отчеты");
            return;
        }
        for (Integer monthNumber : monthData.keySet()){
            MonthTransaction profitMonth = monthData.get(monthNumber).getMostProfitTransaction();
            int totalProfit = profitMonth.quantity * profitMonth.unitPrice;
            MonthTransaction unProfitMonth = monthData.get(monthNumber).getMostUnProfitTransaction();
            int totalUnProfit = unProfitMonth.quantity * unProfitMonth.unitPrice;
            System.out.println(
                    "Номер месяца: " + monthNumber + "\n" +
                    "Самый прибыльный товар: " + profitMonth.itemName + " - " + totalProfit + "\n" +
                    "Самая большая трата: " + unProfitMonth.itemName + " - " + totalUnProfit
            );
        }
    }

    void printYearInfo(){
        if (!isYearLoaded){
            System.out.println("Сначала загрузите годовой отчет!");
            return;
        }
        System.out.println("2021 год");
        for (Integer monthNumber : monthData.keySet()){
            System.out.println("Месяц номер: " + monthNumber);
            System.out.println("Прибыль: " + monthData.get(monthNumber).getExpenses());
        }
        System.out.println("Cредний расход за все операции в этом году: " + yearReport.getAverageExpenses());
        System.out.println("Cредний доход за все операции в этом году: " + yearReport.getAverageIncomes());
    }

}

