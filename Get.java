package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

class Get {

    public static String getString() {
        boolean flag = true;
        String input = "";
        while (flag) {
            try {
                Scanner s = new Scanner(System.in);
                input = s.next();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("!please enter a valid String!");
            }
        }
        return input;
    }

    public static int getInt() {
        boolean flag = true;
        int input = 0;
        while (flag) {
            try {
                Scanner s = new Scanner(System.in);
                input = s.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("!please enter a valid Integer!");
            }
        }
        return input;
    }
}
