import java.util.Scanner;

public class DequeCompare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of N: ");
        int N = scanner.nextInt();

        scanner.close();

        Deque<Integer> deque = new Deque<>();
        Deque1<Integer> deque1 = new Deque1<>();

        // Add N elements to each deque
        for (int i = 0; i < N; i++) {
            deque.addLast(i);
            deque1.addLast(i);
        }

        // Print the counts of addLast method calls for each deque
        System.out.println("addLast method calls for Deque: " + deque.getAccessCount());
        System.out.println("addLast method calls for Deque1: " + deque1.getAccessCount());
    }
}
