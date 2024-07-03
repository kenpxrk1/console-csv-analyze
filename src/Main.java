import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MonthReport monthReport = new MonthReport();
        Checker checker = new Checker();

        while (true){

        printMenu();
        int command = sc.nextInt();

        if (command == 0){
                System.out.println("Завершаем программу...");
                break;
            }
        else if (command == 1){
            System.out.println("Считываем отчеты по месяцам...");
            checker.scanMonthFiles();
        }
        else if (command == 2){
            System.out.println("Считываем годовой отчет...");
            checker.scanYearFile();
        }
        else if (command == 3){
            System.out.println("Выполняем сверку отчетов...");
            checker.checkMonthAndYear();
        }
        else if (command == 4){
            System.out.println("Пожалуйста, вот информация обо всех месячных отчетах:");
            checker.printMonthInfo();
        }
        else if (command == 5){
            System.out.println("Пожалуйста, вот информация о годовом отчете:");
            checker.printYearInfo();
        }
        else {
            System.out.println("Выбрана некорректная операция. Попробуйте снова" );
            continue;
        }


        }
    }
    static void printMenu(){
        // Вывод пользовательского меню:
        System.out.println("Выберите действие: \n" +
                "1 - Считать все месячные отчёты \n" +
                "2 - Считать годовой отчёт \n" +
                "3 - Сверить отчёты \n" +
                "4 - Вывести информацию обо всех месячных отчётах \n" +
                "5 - Вывести информацию о годовом отчёте \n" +
                "0 - Завершить выполнение программы");
    }

}


