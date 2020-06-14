package org.world.machine.web;

import java.util.Arrays;

public class Test {

    @org.testng.annotations.Test
    public void test() {

        final int MAX_NUM = 1000;
        final int DIV1 = 6;
        final int MODE1 = 2;
        final int DIV2 = 11;
        final int MODE2 = 5;
        for (int i = 0; i < MAX_NUM; i++) {
            if (i % (DIV1 * DIV2) == 0) {
                System.out.println();
            }
            if (i % DIV1 == MODE1) {
                System.out.print("*");
            }
            if (i % DIV2 == MODE2) {
                System.out.print("*");
            }

            System.out.print(((10000 + i) + "").substring(1, 5) + "\t");
        }
    }

    @org.testng.annotations.Test
    public void test3() {

        final int[][][] ALL = {
                { { 6, 2 }, { 11, 5 } },
        };
        for (int i = 0; i < ALL.length; i++) {
            for (int j = 0; j < ALL[i].length; j++) {
                System.out.print(Arrays.toString(ALL[i][j]));
                System.out.print(",");
            }

            System.out.println();
            call2(ALL[i]);
            System.out.println();
            System.out.println();
        }
    }

    private void call2(int[][] ALL) {
        int div_all = 1;
        for (int i = 0; i < ALL.length; i++) {
            div_all *= ALL[i][0];
        }
        final int MAX_NUM = 1000;
        for (int i = 0; i < div_all; i++) {
            if (i % div_all == 0) {
                System.out.println();
            }
//            for (int j = 0; j < ALL.length; j++) {
                if (i % ALL[0][0] == ALL[0][1]) {
                    System.out.print("*");
                }
//            }
//            for (int j = 0; j < ALL.length; j++) {
                int column = (i / (ALL[0][0])) + (ALL[1][0]) * (i % (ALL[0][0]));
                if (column % ALL[1][0] == ALL[1][1]) {
                    System.out.print("*");
                }
//            }

            System.out.print(((10000 + i) + "").substring(1, 5) + "\t");
        }
    }

    @org.testng.annotations.Test
    public void test2() {

        final int[][][] ALL = {
                { { 2, 1 }, { 3, 2 } },
                { { 6, 2 }, { 11, 5 } },
                { { 3, 2 }, { 5, 3 }, { 7, 5 } },
        };
        for (int i = 0; i < ALL.length; i++) {
            for (int j = 0; j < ALL[i].length; j++) {
                System.out.print(Arrays.toString(ALL[i][j]));
                System.out.print(",");
            }

            System.out.println();
            call(ALL[i]);
            System.out.println();
            System.out.println();
        }
    }

    private void call(int[][] ALL) {
        int div_all = 1;
        for (int i = 0; i < ALL.length; i++) {
            div_all *= ALL[i][0];
        }
        final int MAX_NUM = 1000;
        for (int i = 0; i < div_all; i++) {
            if (i % div_all == 0) {
                System.out.println();
            }
            for (int j = 0; j < ALL.length; j++) {
                if (i % ALL[j][0] == ALL[j][1]) {
                    System.out.print("*");
                }
            }
//            for (int j = 0; j < ALL.length; j++) {
//                int column = (i / (ALL[(j + 1) % 2][0])) + (ALL[j][0]) * (i % (ALL[(j + 1) % 2][0]));
//                if (column % ALL[j][0] == ALL[j][1]) {
//                    System.out.print("*");
//                }
//            }

            System.out.print(((10000 + i) + "").substring(1, 5) + "\t");
        }
    }
}
