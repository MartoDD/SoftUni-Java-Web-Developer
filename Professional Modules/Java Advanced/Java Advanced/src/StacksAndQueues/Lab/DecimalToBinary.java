package StacksAndQueues.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        if (input == 0) {
            System.out.println(0);
        } else {
            while (input != 0) {
                stack.push(input % 2);
                input /= 2;
            }
            while (stack.size() > 0) {
                System.out.print(stack.pop());
            }
        }
    }
}
