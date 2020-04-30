package org.world.machine.ideal.v0.v1.util;

import java.util.Arrays;

/**
 * 添加数字工具类
 * 方便构造映射规则
 */
public class NumberUtil {

    public static void main(String[] args) {

        for (int i = 0; i < 16; i++) {
            int[][] bits = get2DBits(i);
            printBits(bits);
        }
    }

    static void printBits(int[][] bits) {
        for (int i = 0; i < bits.length; i++) {
            System.out.println(i + ":" + Arrays.toString(bits[i]));
        }
        System.out.println();
    }

    public static int[][] get2DBits(int bitLength) {
        int[][] allBits = new int[(int) Math.pow(2, bitLength)][bitLength];
        for (int i = 0; i < allBits.length; i++) {
            int[] bits = new int[allBits[i].length];
            for (int j = 0; j < bits.length; j++) {
                bits[j] = (i >> (bits.length - j - 1)) & 1;
            }
            allBits[i] = bits;
        }
        return allBits;
    }
}
