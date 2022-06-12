package Generics.Exercise._5_GenericCountMethodString;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Box<String> box = new Box<>();

        while (n-- > 0) {

            String input = scanner.nextLine();

            box.add(input);

        }
        String toCompare = scanner.nextLine();

        System.out.println(box.returnCompared(toCompare));
    }
}
