import java.util.Arrays;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] array = inputArray(scanner);
        
        int K = inputValidNumber(scanner, "Введите K: ", 0, array.length - 2);
        int L = inputValidNumber(scanner, "Введите L: ", K + 1, array.length - 1);
        
        if (K >= L || L >= array.length) {
            System.out.println("Ошибка: должно выполняться условие 0 ≤ K < L < N");
            return;
        }
        
        System.out.println("Исходный массив: " + Arrays.toString(array));
        
        reverseBetween(array, K, L);
        
        System.out.println("Результат: " + Arrays.toString(array));
        
        scanner.close();
    }
    
    public static int[] inputArray(Scanner scanner) {
        int[] array = null;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("Введите элементы массива через пробел: ");
            String input = scanner.nextLine().trim();
            
            String[] elements = input.split("\\s+");
            
            array = new int[elements.length];
            validInput = true;
            
            for (int i = 0; i < elements.length; i++) {
                try {
                    array[i] = Integer.parseInt(elements[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: '" + elements[i] + "' не является целым числом.");
                    System.out.println("Пожалуйста, введите заново все элементы массива.");
                    validInput = false;
                    break;
                }
            }
        }
        
        return array;
    }
    
    public static int inputValidNumber(Scanner scanner, String prompt, int min, int max) {
        int number = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            try {
                number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    validInput = true;
                } else {
                    System.out.println("Ошибка: число должно быть в диапазоне от " + min + " до " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
        
        return number;
    }
    
    public static void reverseBetween(int[] array, int K, int L) {
        int left = K;
        int right = L;
        
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            
            left++;
            right--;
        }
    }
}