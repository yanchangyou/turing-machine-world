package org.world.machine.bf.v0.vx;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * brain f**k 简单实现
 */
public class BFMachine {

    private static final int DEFAULT_CELLS_LENGTH = 32;
    private static boolean logFlag = true;//日志开关

    /**
     * 禁用日志
     */
    public static void disableLog() {
        logFlag = false;
    }

    /**
     * 启用日志
     */
    public static void enableLog() {
        logFlag = false;
    }

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

        log("tuning before code:" + code + "\r\n");
        code = tuningCode(code);//优化代码
        log("tuning after code:" + code);

        int stepCount = 0;
        char[] instructions = code.toCharArray();
        int index = 0;
        for (int i = 0; i < instructions.length; i++) {
            stepCount++;
            log("steps[" + formatNumber(stepCount, cells.length) + "]"
                    + "cells[" + formatNumber(index, cells.length) + "]"
                    + instructions[i] + " : ", true);

            if ('+' == instructions[i]) {//值+1
                cells[index]++;
            } else if ('-' == instructions[i]) {// 值-1
                cells[index]--;
            } else if ('>' == instructions[i]) {// 右移
                index++;
            } else if ('<' == instructions[i]) {// 左移
                index--;
            } else if ('[' == instructions[i]) {// while开始
                if (cells[index] == 0) {
                    i = gotoPreOrNext(instructions, i, true);
                }
            } else if (']' == instructions[i]) {// while结束
                if (cells[index] != 0) {
                    i = gotoPreOrNext(instructions, i, false);
                }
            } else if ('^' == instructions[i]) {// 无条件break
                i = gotoPreOrNext(instructions, i, true);
            } else if ('!' == instructions[i]) {// 有条件break
                if (cells[index] == 0) {
                    i = gotoPreOrNext(instructions, i, true);
                }
            } else if ('&' == instructions[i]) { // 位置重置到首位
                index = 0;
            } else if ('_' == instructions[i]) {// 单元格设置为零
                cells[index] = 0;
            } else if ('.' == instructions[i]) {// 输出
                System.out.print((char) cells[index]);
            } else if (',' == instructions[i]) {// 输入一个字符
                try {
                    System.out.print("input:");
                    int ch = System.in.read();
                    cells[index] = ch;
                } catch (Exception e) {
                    throw new RuntimeException("read exception:", e);
                }
            }

            log(Arrays.toString(cells));

            if (index < 0 || index >= cells.length) {
                throw new RuntimeException("out of index :" + index);
            }
            if (!isOkCellsNumber(cells)) {
                throw new RuntimeException("out of value -1, must be >0");
            }
        }
    }

    /**
     * []中的指令，调到最前或之后
     *
     * @param instructions
     * @param index
     * @param isLeft
     * @return
     */
    private static int gotoPreOrNext(char[] instructions, int index, boolean isLeft) {
        int level = 0;
        char leftOrRight = isLeft ? '[' : ']';
        char rightOrLeft = !isLeft ? '[' : ']';

        while (instructions[(index = index + (isLeft ? 1 : -1))] != rightOrLeft || level != 0) {
            if (instructions[index] == leftOrRight) {
                level++;
            } else if (instructions[index] == rightOrLeft) {
                level--;
            }
        }
        return index;
    }

    static boolean isOkCellsNumber(int[] cells) {

        for (int i = 0; i < cells.length; i++) {
            if (cells[i] < -10000) {
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
        for (int i = 0; i <= maxLength - currentLength; i++) {
            result.append("0");
        }
        return result.append(number).toString();
    }

    static String pattern = "(\\<\\>)|(\\>\\<)";
    static int tuningCount = 0;

    /**
     * 调优代码，去掉无用操作如：<>和><
     *
     * @param code
     * @return
     */
    static String tuningCode(String code) {

        return removeDuplicateSet(removeUselessMove(code));
    }

    static String removeDuplicateSet(String code) {
        return code.replaceAll("&+", "&");
    }

    private static String removeUselessMove(String code) {
        tuningCount += Pattern.compile(pattern).matcher(code).groupCount();
        log("tuning code count:" + tuningCount);

        if (code.matches(".*(" + pattern + ").*")) {
            return removeUselessMove(code.replaceAll(pattern, ""));
        } else {
            return code;
        }
    }

    /**
     * 日志统一输出
     *
     * @param message
     */
    static void log(String message) {
        log(message, false);
    }

    /**
     * 日志统一输出
     *
     * @param message
     */
    static void log(String message, boolean inlineFlag) {
        if (logFlag) {
            System.out.print(message);
            if (!inlineFlag) {
                System.out.println();
            }
        }
    }
}
