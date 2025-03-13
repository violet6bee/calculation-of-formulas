package HomeWork;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main2 {
    static class Transaction {
        final int fromId;
        final int toId;
        final int amount;

        Transaction(int fromId, int toId, int amount) {
            this.fromId = fromId;
            this.toId = toId;
            this.amount = amount;
        }

        public static void main(String[] args) {
            ExecutorService executor = Executors.newCachedThreadPool();

            Scanner scanner = new Scanner(System.in);

            int numOfUsers = Integer.parseInt(scanner.nextLine());

            List<Integer> balanceOfUsers = Collections.synchronizedList(
                    Arrays.stream(scanner.nextLine().split(" "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
            );


            int countOftransactions = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < countOftransactions; i++) {
                int[] transactionsOfUsers = Arrays.stream(scanner.nextLine().split(" - "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                Runnable task = () -> transaction(transactionsOfUsers[0], transactionsOfUsers[1], transactionsOfUsers[2], balanceOfUsers);
                executor.execute(task);
            }

            shutdownProcessing(executor, numOfUsers, balanceOfUsers);
        }

        private static void shutdownProcessing(ExecutorService executor, int numOfUsers, List<Integer> balanceOfUsers) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Не все задачи успели завершиться в течение 5 секунд.");
                } else {
                    System.out.println("Все задачи были выполнены успешно.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                executor.shutdownNow();
            }

            for (int i = 0; i < numOfUsers; i++) {
                System.out.printf("User %d final balance: %d\n", i, balanceOfUsers.get(i));
            }
        }
    }

    private static void transaction(int fromId, int amount, int toId, List<Integer> balanceOfUsers) {
        if (amount > balanceOfUsers.get(fromId)) {
            System.out.println("Not enough money");
        } else {
            balanceOfUsers.set(fromId, balanceOfUsers.get(fromId) - amount);
            balanceOfUsers.set(toId, balanceOfUsers.get(toId) + amount);
        }
    }
}
