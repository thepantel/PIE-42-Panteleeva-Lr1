import java.util.Scanner;

public class Krll {
    
    static class Movie {
        private double cost;    
        private double income;  
        
        public void init(double cost, double income) {
            this.cost = cost;
            this.income = income;
        }
        
        public void read() {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Введите затраты на фильм: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Ошибка: введите число!");
                System.out.print("Введите затраты на фильм: ");
                scanner.next();
            }
            this.cost = scanner.nextDouble();
            
            System.out.print("Введите доход от проката: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Ошибка: введите число!");
                System.out.print("Введите доход от проката: ");
                scanner.next();
            }
            this.income = scanner.nextDouble();
        }
        
        public void display() {
            System.out.printf("Затраты: %.2f, Доход: %.2f, Окупаемость: %.2f%%", 
                             cost, income, calculateProfitability());
        }
        
        public double calculateProfitability() {
            if (cost == 0) {
                return 0;
            }
            return (income / cost) * 100;
        }
        
        // Геттеры
        public double getCost() {
            return cost;
        }
        
        public double getIncome() {
            return income;
        }
        
        // Сеттеры
        public void setCost(double cost) {
            this.cost = cost;
        }
        
        public void setIncome(double income) {
            this.income = income;
        }
    }
    
    static class FilmStudio {
        private String name;                   
        private Movie[] movies;                 
        private double[] foreignIncomes;        
        
        public FilmStudio() {
            movies = new Movie[3];
            foreignIncomes = new double[3];
            for (int i = 0; i < 3; i++) {
                movies[i] = new Movie();
            }
        }
        
        public void init(String name, Movie movie1, Movie movie2, Movie movie3, 
                        double foreign1, double foreign2, double foreign3) {
            this.name = name;
            this.movies[0] = movie1;
            this.movies[1] = movie2;
            this.movies[2] = movie3;
            this.foreignIncomes[0] = foreign1;
            this.foreignIncomes[1] = foreign2;
            this.foreignIncomes[2] = foreign3;
        }
        
        public void read() {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Введите название киностудии: ");
            this.name = scanner.nextLine();
            
            System.out.println("\nВведите данные о фильмах:");
            for (int i = 0; i < 3; i++) {
                System.out.println("--- Фильм " + (i + 1) + " ---");
                movies[i].read();
                
                System.out.print("Введите доход от проката в других странах для фильма " + (i + 1) + ": ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Ошибка: введите число!");
                    System.out.print("Введите доход от проката в других странах для фильма " + (i + 1) + ": ");
                    scanner.next();
                }
                foreignIncomes[i] = scanner.nextDouble();
                scanner.nextLine(); 
            }
        }
        
        public void display() {
            System.out.println("\n=== Информация о киностудии ===");
            System.out.println("Название: " + name);
            System.out.println("\nФильмы киностудии:");
            for (int i = 0; i < 3; i++) {
                System.out.print("Фильм " + (i + 1) + ": ");
                movies[i].display();
                System.out.printf(", Доход за рубежом: %.2f\n", foreignIncomes[i]);
            }
            System.out.printf("\nСуммарный доход киностудии: %.2f\n", calculateTotalIncome());
            System.out.println("Самый окупаемый фильм: " + findMostProfitableMovie());
        }
        
        public double calculateTotalIncome() {
            double total = 0;
            for (int i = 0; i < 3; i++) {
                total += movies[i].getIncome() + foreignIncomes[i];
            }
            return total;
        }
        
        public String findMostProfitableMovie() {
            int mostProfitableIndex = 0;
            double maxProfitability = 0;
            
            for (int i = 0; i < 3; i++) {
                double totalIncome = movies[i].getIncome() + foreignIncomes[i];
                double profitability = (totalIncome / movies[i].getCost()) * 100;
                
                if (profitability > maxProfitability) {
                    maxProfitability = profitability;
                    mostProfitableIndex = i;
                }
            }
            
            return "Фильм " + (mostProfitableIndex + 1) + " (окупаемость: " + 
                   String.format("%.2f", maxProfitability) + "%)";
        }
        
        public String getName() {
            return name;
        }
        
        public Movie getMovie(int index) {
            if (index >= 0 && index < 3) {
                return movies[index];
            }
            return null;
        }
        
        public double getForeignIncome(int index) {
            if (index >= 0 && index < 3) {
                return foreignIncomes[index];
            }
            return 0;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public void setMovie(int index, Movie movie) {
            if (index >= 0 && index < 3) {
                movies[index] = movie;
            }
        }
        
        public void setForeignIncome(int index, double income) {
            if (index >= 0 && index < 3) {
                foreignIncomes[index] = income;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Демонстрация работы системы Киностудия ===");
        
        System.out.println("\n--- Тестирование класса Movie ---");
        Movie movie1 = new Movie();
        movie1.init(1000000, 2500000);
        System.out.print("Фильм 1 (инициализирован через init): ");
        movie1.display();
        System.out.println();
        
        Movie movie2 = new Movie();
        System.out.println("\nВведите данные для фильма 2:");
        movie2.read();
        System.out.print("Фильм 2: ");
        movie2.display();
        System.out.println();
        
        System.out.println("\n--- Тестирование класса FilmStudio ---");
        
        FilmStudio studio1 = new FilmStudio();
        Movie m1 = new Movie();
        m1.init(5000000, 8000000);
        Movie m2 = new Movie();
        m2.init(3000000, 4500000);
        Movie m3 = new Movie();
        m3.init(7000000, 12000000);
        
        studio1.init("Paramount Pictures", m1, m2, m3, 2000000, 1500000, 3000000);
        studio1.display();
        
        System.out.println("\n--- Создание новой киностудии ---");
        FilmStudio studio2 = new FilmStudio();
        studio2.read();
        studio2.display();
        
        System.out.println("\n--- Демонстрация работы сеттеров ---");
        studio1.setName("Обновленная Paramount");
        System.out.println("Новое название студии: " + studio1.getName());
        
        Movie newMovie = new Movie();
        newMovie.init(4000000, 6000000);
        studio1.setMovie(1, newMovie);
        studio1.setForeignIncome(1, 1000000);
        
        System.out.println("\nОбновленная информация о студии:");
        studio1.display();
        
        scanner.close();
    }
}