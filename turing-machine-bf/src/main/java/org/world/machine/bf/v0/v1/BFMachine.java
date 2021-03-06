package org.world.machine.bf.v0.v1;

import java.util.Arrays;

/**
 * brain f**k 简单实现
 */
public class BFMachine {

    private static final int DEFAULT_CELLS_LENGTH = 32;

    /**
     * bf执行
     *
     * @param code
     */
    public static String execute(String code) {

        int[] cells = new int[DEFAULT_CELLS_LENGTH];

        execute(code, cells);

        return convertToString(cells);
    }

    /**
     * 指定长度执行
     *
     * @param code
     * @param cellsLength
     * @return
     */
    public static int[] execute(String code, int cellsLength) {

        int[] cells = new int[cellsLength];
        execute(code, cells);
        return cells;
    }

    /**
     * 指定cells执行
     *
     * @param code
     * @param cells
     */
    public static void execute(String code, int[] cells) {
        int stepCount = 0;
        char[] instructions = code.toCharArray();
        int index = 0;
        for (int i = 0; i < instructions.length; i++) {
            stepCount++;
            System.out.print("steps[" + stepCount + "]cells[" + formatNumber(index, cells.length) + "]"
                    + instructions[i] + " : ");

            if ('+' == instructions[i]) {
                cells[index]++;
            } else if ('-' == instructions[i]) {
                cells[index]--;
            } else if ('.' == instructions[i]) {
                System.out.print((char) cells[index]);
            } else if ('>' == instructions[i]) {
                index++;
            } else if ('<' == instructions[i]) {
                index--;
            } else if ('[' == instructions[i]) {

                if (cells[index] == 0) {
                    int level = 0;

                    while (instructions[++i] != ']' || level != 0) {
                        if (instructions[i] == '[') {
                            level++;
                        } else if (instructions[i] == ']') {
                            level--;
                        }
                    }
                }
            } else if (']' == instructions[i]) {
                if (cells[index] != 0) {
                    int level = 0;
                    while (instructions[--i] != '[' || level != 0) {
                        if (instructions[i] == ']') {
                            level++;
                        } else if (instructions[i] == '[') {
                            level--;
                        }
                    }
                    i--;
                }
            } else if (',' == instructions[i]) {
                try {
                    System.out.print("input:");
                    int ch = (int) System.in.read();
                    cells[index] = ch;
                } catch (Exception e) {
                    throw new RuntimeException("read exception:", e);
                }
            }

            System.out.println(Arrays.toString(cells));

            if (index < 0 || index >= cells.length) {
                throw new RuntimeException("out of index :" + index);
            }
            if (!isAllGreaterZero(cells)) {
                throw new RuntimeException("out of value -1, must be >0");
            }
        }
    }

    static boolean isAllGreaterZero(int[] cells) {

        for (int i = 0; i < cells.length; i++) {
            if (cells[i] < 0) {
                return false;
            }
        }
        return true;
    }

    static String convertToString(int[] cells) {
        int endIndex = 0;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == 0) {
                endIndex = i;
                break;
            }
        }
        return new String(cells, 0, endIndex);
    }

    /**
     * 数字格式，按照最大值进行格式
     *
     * @param number
     * @param maxValue
     * @return
     */
    private static String formatNumber(int number, int maxValue) {
        int maxLength = String.valueOf(maxValue).length();
        int currentLength = String.valueOf(number).length();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxLength - currentLength; i++) {
            result.append("0");
        }
        return result.append(number).toString();
    }
}
