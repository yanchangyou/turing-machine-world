package org.world.machine.bf.v0.v1.lib;

/**
 * 语言库
 * 1，前提：每次操作index都复位到0
 */
public class BFMachineLib {

    /**
     * 移动相对位置
     *
     * @param code
     * @param shift 偏移量
     */
    public static void move(StringBuilder code, int shift) {

        char direction = shift < 0 ? '<' : '>';
        //移动到指定位置
        int length = Math.abs(shift);
        for (int i = 0; i < length; i++) {
            code.append(direction);
        }
    }

    /**
     * 向右移动length位
     *
     * @param code
     * @param length
     */
    public static void moveRight(StringBuilder code, int length) {
        move(code, length);
    }

    /**
     * 向左移动length位
     *
     * @param code
     * @param length
     */
    public static void moveLeft(StringBuilder code, int length) {
        move(code, -length);
    }

    /**
     * 设置值
     *
     * @param value
     */
    public static void setValue(StringBuilder code, int value) {
        //设置值
        for (int i = 0; i < value; i++) {
            code.append("+");
        }
    }

    /**
     * 设置某个位置为某值的代码片段
     *
     * @param index
     * @param value
     */
    public static void set(StringBuilder code, int index, int value) {

        moveRight(code, index);
        setValue(code, value);
        moveLeft(code, index);

    }

    /**
     * 输出指定位置的值
     *
     * @param index
     */
    public static void output(StringBuilder code, int index) {

        moveRight(code, index);
        code.append(".");
        moveLeft(code, index);

    }

    /**
     * 复制值：从一个位置复制到另外一个位置，由于BF特性，通过先复制两个值，然后在交换的方式来实现
     *
     * @param code
     * @param fromIndex
     * @param toIndex
     */
    public static void copy(StringBuilder code, int fromIndex, int toIndex) {

        swap(code, fromIndex, toIndex, 0);

        swap(code, 0, fromIndex);
    }

    /**
     * 两个单元格同时逐步-1，当第一个减到0时终止
     *
     * @param code
     * @param index1
     * @param index2
     */
    public static void doubleSub(StringBuilder code, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        moveRight(code, index1);
        code.append("[-");
        move(code, index2 - index1);
        code.append("-");
        move(code, index1 - index2);
        code.append("]");
        moveLeft(code, index1);
    }

    /**
     * 交换两个位置的值
     *
     * @param code
     * @param fromIndex
     * @param toIndex
     */
    public static void swap(StringBuilder code, int fromIndex, int toIndex) {
        swap(code, fromIndex, toIndex, toIndex);
    }

    /**
     * 交换两个位置的值
     *
     * @param code
     * @param fromIndex
     * @param toIndex1
     * @param toIndex2
     */
    public static void swap(StringBuilder code, int fromIndex, int toIndex1, int toIndex2) {

        if (fromIndex == toIndex1) {
            return;
        }
        moveRight(code, fromIndex);
        code.append("[-");
        move(code, toIndex1 - fromIndex);
        code.append("+");
        if (toIndex2 != toIndex1) {
            move(code, toIndex2 - toIndex1);
            code.append("+");
        }

        move(code, fromIndex - toIndex2);
        code.append("]");
        moveLeft(code, fromIndex);

    }

    /**
     * 实现两个位置相加 : result = number1 + number2
     *
     * @param code
     * @param numberIndex1
     * @param numberIndex2
     * @param resultIndex
     */
    public static void add(StringBuilder code, int numberIndex1, int numberIndex2, int resultIndex, int maxCellLength) {

        copy(code, numberIndex1, maxCellLength - 1);
        copy(code, numberIndex2, maxCellLength - 2);

        swap(code, maxCellLength - 1, resultIndex);
        swap(code, maxCellLength - 2, resultIndex);
    }

    /**
     * 实现两个位置相减 : result = number1 - number2
     *
     * @param code
     * @param numberIndex1
     * @param numberIndex2
     * @param resultIndex
     */
    public static void sub(StringBuilder code, int numberIndex1, int numberIndex2, int resultIndex, int maxCellLength) {

        copy(code, numberIndex1, resultIndex);
        copy(code, numberIndex2, maxCellLength - 1);

        doubleSub(code, maxCellLength - 1, resultIndex);

    }

    /**
     * 两个数相乘
     *
     * @param code
     * @param numberIndex1
     * @param numberIndex2
     * @param resultIndex
     * @param maxCellLength
     */
    public static void mul(StringBuilder code, int numberIndex1, int numberIndex2, int resultIndex, int maxCellLength) {

        copy(code, numberIndex1, maxCellLength - 1);
        moveRight(code, maxCellLength - 1);
        code.append("[-");
        moveLeft(code, maxCellLength - 1);
        copy(code, numberIndex2, resultIndex);
        moveRight(code, maxCellLength - 1);
        code.append("]");
        moveLeft(code, maxCellLength - 1);
    }

    /**
     * 两个数除
     *
     * @param code
     * @param numberIndex1
     * @param numberIndex2
     * @param resultIndex
     * @param maxCellLength
     */
    public static void div(StringBuilder code, int numberIndex1, int numberIndex2, int resultIndex, int maxCellLength) {

        copy(code, numberIndex1, maxCellLength - 1);

        moveRight(code, maxCellLength - 1);
        code.append("[");
        moveLeft(code, maxCellLength - 1);

        copy(code, numberIndex2, maxCellLength - 2);

        moveRight(code, maxCellLength - 2);
        code.append("[");
        code.append("->-");

        code.append("!");

        code.append("<");
        code.append("]");
        moveLeft(code, maxCellLength - 2);

        moveRight(code, resultIndex);
        code.append("+");
        moveLeft(code, resultIndex);

        moveRight(code, maxCellLength - 1);
        code.append("]");
        moveLeft(code, maxCellLength - 1);

    }
}
