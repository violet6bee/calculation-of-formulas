package HomeWork;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Enter the numbers and press \"enter\":");
        double[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();

        CompletableFuture<Double> future1 = CompletableFuture.supplyAsync(() -> sumOfSquares(numbers[0], numbers[1]));
        CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() -> logOfnum(numbers[2]));
        CompletableFuture<Double> future3 = CompletableFuture.supplyAsync(() -> squareRoot(numbers[3]));
        double result1 = future1.join();
        double result2 = future2.join();
        double result3 = future3.join();

        double result = result1 * result2 / result3;
        System.out.println("Final result of the formula: " + result);
    }

    public static double sumOfSquares(double a, double b) {
        try {
            double sumSquares = Math.pow(a, 2) + Math.pow(b, 2);
            Thread.sleep(5000);
            System.out.println("Calculating sum of squares: " + sumSquares);
            return sumSquares;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double logOfnum(double c) {
        try {
            double log = Math.log(c);
            Thread.sleep(15000);
            System.out.println("Calculating log(c):" + log);
            return log;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double squareRoot(double d) {
        try {
            double sqRoot = Math.sqrt(d);
            Thread.sleep(10000);
            System.out.println("Calculating sqrt(d): " + sqRoot);
            return sqRoot;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}





