import java.util.Scanner;

public class Main {
    
    static class Interval {
        private double x1;
        private double x2;
        
        public void init(double x1, double x2) {
            this.x1 = x1;
            this.x2 = x2;
        }
        
        public void read() {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Введите левый конец интервала (x1): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Ошибка: введите вещественное число!");
                System.out.print("Введите левый конец интервала (x1): ");
                scanner.next();
            }
            this.x1 = scanner.nextDouble();
            
            System.out.print("Введите правый конец интервала (x2): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Ошибка: введите вещественное число!");
                System.out.print("Введите правый конец интервала (x2): ");
                scanner.next();
            }
            this.x2 = scanner.nextDouble();
            
            if (this.x1 > this.x2) {
                System.out.println("Предупреждение: левый конец больше правого, интервал будет пустым");
            }
        }
        
        public void display() {
            System.out.printf("[%.2f, %.2f]", x1, x2);
        }
        
        public double length() {
            if (x1 <= x2) {
                return x2 - x1;
            } else {
                return 0; 
            }
        }
        
        public static Interval add(Interval a, Interval b) {
            double newX1 = Math.min(a.x1, b.x1);
            double newX2 = Math.max(a.x2, b.x2);
            return new Interval(newX1, newX2);
        }
        
        public double getX1() {
            return x1;
        }
        
        public void setX1(double x1) {
            this.x1 = x1;
        }
        
        public double getX2() {
            return x2;
        }
        
        public void setX2(double x2) {
            this.x2 = x2;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Демонстрация работы класса Interval ===");
        
        System.out.println("\n--- Первый интервал ---");
        Interval interval1 = new Interval();
        interval1.read();
        
        System.out.println("\n--- Второй интервал ---");
        Interval interval2 = new Interval();
        System.out.print("Введите левый конец интервала: ");
        double x1 = scanner.nextDouble();
        System.out.print("Введите правый конец интервала: ");
        double x2 = scanner.nextDouble();
        interval2.init(x1, x2);
        
        System.out.println("\n--- Введенные интервалы ---");
        System.out.print("Интервал 1: ");
        interval1.display();
        System.out.printf(" (длина: %.2f)\n", interval1.length());
        
        System.out.print("Интервал 2: ");
        interval2.display();
        System.out.printf(" (длина: %.2f)\n", interval2.length());
        
        System.out.println("\n--- Суммирование интервалов ---");
        Interval sumInterval = Interval.add(interval1, interval2);
        System.out.print("Результат суммирования: ");
        sumInterval.display();
        System.out.printf(" (длина: %.2f)\n", sumInterval.length());
        
        System.out.println("\n--- Демонстрация геттеров и сеттеров ---");
        System.out.printf("Текущее значение x1 первого интервала: %.2f\n", interval1.getX1());
        
        System.out.print("Введите новое значение x1 для первого интервала: ");
        double newX1 = scanner.nextDouble();
        interval1.setX1(newX1);
        
        System.out.print("Обновленный первый интервал: ");
        interval1.display();
        System.out.printf(" (длина: %.2f)\n", interval1.length());
        
        System.out.println("\n--- Дополнительный пример ---");
        Interval interval3 = new Interval(2.5, 7.8);
        System.out.print("Интервал 3 (создан через конструктор): ");
        interval3.display();
        System.out.printf(" (длина: %.2f)\n", interval3.length());
        
        Interval totalInterval = Interval.add(Interval.add(interval1, interval2), interval3);
        System.out.print("\nСумма всех трех интервалов: ");
        totalInterval.display();
        System.out.printf(" (длина: %.2f)\n", totalInterval.length());
        
        scanner.close();
    }
}