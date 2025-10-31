import java.util.Scanner;

public class Hjk {
    
    static class IntervalClass {
        private double x1;
        private double x2;
        
        public IntervalClass() {}
        
        public IntervalClass(double x1, double x2) {
            this.x1 = x1;
            this.x2 = x2;
        }
        
        public void init(double x1, double x2) {
            this.x1 = x1;
            this.x2 = x2;
        }
        
        public void read() {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Введите левый конец интервала (x1): ");
            this.x1 = scanner.nextDouble();
            
            System.out.print("Введите правый конец интервала (x2): ");
            this.x2 = scanner.nextDouble();
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
        
        public static IntervalClass add(IntervalClass a, IntervalClass b) {
            double newX1 = Math.min(a.x1, b.x1);
            double newX2 = Math.max(a.x2, b.x2);
            return new IntervalClass(newX1, newX2);
        }
        
        public double getX1() { return x1; }
        public void setX1(double x1) { this.x1 = x1; }
        public double getX2() { return x2; }
        public void setX2(double x2) { this.x2 = x2; }
    }
    
    record IntervalRecord(double x1, double x2) {
        public double length() {
            if (x1 <= x2) {
                return x2 - x1;
            } else {
                return 0;
            }
        }
        
        public static IntervalRecord add(IntervalRecord a, IntervalRecord b) {
            double newX1 = Math.min(a.x1, b.x1);
            double newX2 = Math.max(a.x2, b.x2);
            return new IntervalRecord(newX1, newX2);
        }
        
        public void display() {
            System.out.printf("[%.2f, %.2f]", x1, x2);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== ИССЛЕДОВАНИЕ РАЗНИЦЫ МЕЖДУ CLASS И RECORD ===");
        System.out.println("На примере класса Interval из пункта 2\n");
        
        System.out.println("--- РАБОТА С CLASS (ИЗМЕНЯЕМЫЙ) ---");
        
        IntervalClass intervalClass1 = new IntervalClass(1.0, 5.0);
        IntervalClass intervalClass2 = intervalClass1; 
        
        System.out.println("До изменения:");
        System.out.print("intervalClass1: ");
        intervalClass1.display();
        System.out.printf(" (длина: %.2f)", intervalClass1.length());
        System.out.print("\nintervalClass2: ");
        intervalClass2.display();
        System.out.printf(" (длина: %.2f)", intervalClass2.length());
        
        intervalClass1.setX1(0.0);
        intervalClass1.setX2(8.0);
        
        System.out.println("\n\nПосле изменения intervalClass1 через сеттеры:");
        System.out.print("intervalClass1: ");
        intervalClass1.display();
        System.out.printf(" (длина: %.2f)", intervalClass1.length());
        System.out.print("\nintervalClass2: ");
        intervalClass2.display();
        System.out.printf(" (длина: %.2f)", intervalClass2.length());
        System.out.println("\n✓ Вывод: Оба объекта изменились, так как ссылаются на одну область памяти");
        
        System.out.println("\n--- РАБОТА С RECORD (НЕИЗМЕНЯЕМЫЙ) ---");
        
        IntervalRecord intervalRecord1 = new IntervalRecord(1.0, 5.0);
        IntervalRecord intervalRecord2 = intervalRecord1; 
        
        System.out.println("До 'изменения':");
        System.out.print("intervalRecord1: ");
        intervalRecord1.display();
        System.out.printf(" (длина: %.2f)", intervalRecord1.length());
        System.out.print("\nintervalRecord2: ");
        intervalRecord2.display();
        System.out.printf(" (длина: %.2f)", intervalRecord2.length());
        
        intervalRecord1 = new IntervalRecord(0.0, 8.0);
        
        System.out.println("\n\nПосле создания нового intervalRecord1:");
        System.out.print("intervalRecord1: ");
        intervalRecord1.display();
        System.out.printf(" (длина: %.2f)", intervalRecord1.length());
        System.out.print("\nintervalRecord2: ");
        intervalRecord2.display();
        System.out.printf(" (длина: %.2f)", intervalRecord2.length());
        System.out.println("\n✓ Вывод: intervalRecord2 не изменился, так как records неизменяемы");
        
        System.out.println("\n--- СОЗДАНИЕ НЕЗАВИСИМЫХ КОПИЙ ---");
        IntervalRecord originalRecord = new IntervalRecord(2.0, 6.0);
        IntervalRecord copiedRecord = new IntervalRecord(originalRecord.x1(), originalRecord.x2());
        
        System.out.print("originalRecord: ");
        originalRecord.display();
        System.out.print("\ncopiedRecord: ");
        copiedRecord.display();
        System.out.println("\n✓ Вывод: Это две независимые копии, изменения одной не затронут другую");
        
        System.out.println("\n--- СРАВНЕНИЕ ОБЪЕКТОВ ---");
        
        IntervalClass class1 = new IntervalClass(3.0, 7.0);
        IntervalClass class2 = new IntervalClass(3.0, 7.0);
        IntervalRecord record1 = new IntervalRecord(3.0, 7.0);
        IntervalRecord record2 = new IntervalRecord(3.0, 7.0);
        
        System.out.println("CLASS сравнение (equals и ==):");
        System.out.println("class1.equals(class2): " + class1.equals(class2));
        System.out.println("class1 == class2: " + (class1 == class2));
        
        System.out.println("\nRECORD сравнение (equals и ==):");
        System.out.println("record1.equals(record2): " + record1.equals(record2));
        System.out.println("record1 == record2: " + (record1 == record2));
        
        System.out.println("\n--- toString() МЕТОД ---");
        System.out.println("Class toString(): " + class1);
        System.out.println("Record toString(): " + record1);
        
        System.out.println("\n--- РАБОТА МЕТОДА ADD() ---");
        IntervalClass classA = new IntervalClass(1.0, 3.0);
        IntervalClass classB = new IntervalClass(2.0, 5.0);
        IntervalClass classSum = IntervalClass.add(classA, classB);
        
        IntervalRecord recordA = new IntervalRecord(1.0, 3.0);
        IntervalRecord recordB = new IntervalRecord(2.0, 5.0);
        IntervalRecord recordSum = IntervalRecord.add(recordA, recordB);
        
        System.out.print("Class add: ");
        classA.display();
        System.out.print(" + ");
        classB.display();
        System.out.print(" = ");
        classSum.display();
        
        System.out.print("\nRecord add: ");
        recordA.display();
        System.out.print(" + ");
        recordB.display();
        System.out.print(" = ");
        recordSum.display();
        
        System.out.println("\n\n=== ВЫВОДЫ О РАЗЛИЧИЯХ МЕЖДУ CLASS И RECORD ===");
        System.out.println("1. ИЗМЕНЯЕМОСТЬ (MUTABILITY):");
        System.out.println("   • CLASS: изменяемый, можно менять поля через сеттеры");
        System.out.println("   • RECORD: неизменяемый, все поля final, нельзя изменить после создания");
        
        System.out.println("\n2. ПОВЕДЕНИЕ ПРИ ПРИСВАИВАНИИ:");
        System.out.println("   • CLASS: присваивание ссылки → обе переменные работают с одним объектом");
        System.out.println("   • RECORD: присваивание ссылки, но из-за неизменяемости безопаснее");
        
        System.out.println("\n3. АВТОМАТИЧЕСКАЯ ГЕНЕРАЦИЯ МЕТОДОВ:");
        System.out.println("   • CLASS: equals(), hashCode(), toString() нужно переопределять вручную");
        System.out.println("   • RECORD: equals(), hashCode(), toString() генерируются автоматически");
        
        System.out.println("\n4. КОНСТРУКТОРЫ:");
        System.out.println("   • CLASS: нужно писать конструкторы вручную");
        System.out.println("   • RECORD: канонический конструктор генерируется автоматически");
        
        System.out.println("\n5. ГЕТТЕРЫ:");
        System.out.println("   • CLASS: нужно писать геттеры вручную (getX1(), getX2())");
        System.out.println("   • RECORD: геттеры генерируются автоматически (x1(), x2())");
        
        System.out.println("\n6. СЕМАНТИКА:");
        System.out.println("   • CLASS: ссылочная семантика, идентичность по ссылке");
        System.out.println("   • RECORD: value-семантика, идентичность по значению полей");
        
        System.out.println("\n7. РЕКОМЕНДАЦИИ ПО ИСПОЛЬЗОВАНИЮ:");
        System.out.println("   • CLASS: для объектов с изменяемым состоянием, сложной бизнес-логикой");
        System.out.println("   • RECORD: для DTO, value objects, данных-носителей, где важна неизменяемость");
        
        System.out.println("\n8. BOILERPLATE КОД:");
        System.out.println("   • CLASS: много шаблонного кода (геттеры, сеттеры, equals, hashCode, toString)");
        System.out.println("   • RECORD: минимальный boilerplate код, лаконичная запись");
    }
}