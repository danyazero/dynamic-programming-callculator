package org.zero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int price = 1400;
    public static int coef = 212;

    public static void main(String[] args) {
        System.out.print("Enter iteration >> ");
        var scanner = new Scanner(System.in);
        var iterations = scanner.nextInt();
        System.out.print("Enter price >> ");
        price = scanner.nextInt();
        System.out.print("Enter coefficient >> ");
        coef = scanner.nextInt();
        Main main = new Main();

        List<List<List<Double>>> array = new ArrayList<>();

        for (int i = iterations; i > 1; i--) {
            List<List<Double>> list = new ArrayList<>();
            System.out.println();
            System.out.println();
            System.out.println("Step: " + i);
            System.out.println();

            for (int j = 1; j < i; j++) {
                if (i == iterations) {
                    List<Double> temp = new ArrayList<>();
                    temp.add(main.calcA(j, -1));
                    System.out.print("        ");
                    temp.add(main.calcB(j, -1));
                    list.add(temp);
                } else {
                    List<Double> temp = new ArrayList<>();
                    temp.add(main.calcA(j, array.getLast().get(j).getFirst()));
                    System.out.print("        ");
                    var row = array.getLast().getFirst().getFirst();
                    temp.add(main.calcB(j, row));
                    list.add(temp);
                }

            }
            array.add(list);
        }
        Double xLast = array.getLast().getFirst().getFirst();
        var result = price + coef + xLast;
        System.out.println();
        System.out.println("Z1(0) = " + price + " + " + coef + "(1) + " + xLast + " = " + result);

    }

    public static void printMatrix(List<List<Double>> matrix) {
        System.out.println();
        for (List<Double> row : matrix) {
            for (Double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private double calcA(int t, double num) {
        var leftPart = coef * (t + 1);
        var pow = Math.pow(2, -(t + 1));

        var res = 0.0;
        double rightPart = 0;
        if (num != -1) {
            rightPart = num;
            res = leftPart + rightPart;
        } else {
            rightPart = price * pow;
            res = leftPart - rightPart;
        }


        System.out.print(t + ": " + leftPart + getSymbol(num) + rightPart + " = " + res);
        return res;
    }

    public static String getSymbol(double num) {
        if (num == -1) return " - ";
        return " + ";
    }

    private double calcB(int t, double num) {
        var leftPart = -price * Math.pow(2, -t) + (price + coef);
        var res = 0.0;
        double rightPart = 0;
        if (num != -1) {
            rightPart = num;
            res = leftPart + rightPart;
        } else {
            rightPart = price * Math.pow(2, -1);
            res = leftPart - rightPart;
        }
        System.out.println(t + ": " + leftPart + getSymbol(num) + rightPart + " = " + res);
        return res;
    }
}

