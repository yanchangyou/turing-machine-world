package org.world.machine.bf.v0.v1.lib;

/**
 * 语言库
 * 1，前提：每次操作index都复位到0
 */
public class BFMachineLib {

    /**
     * 移动位置
     *
     * @param code
     * @param index
     * @param isLeft
     */
    public static void moveTo(StringBuilder code, int index, boolean isLeft) {

        char direction = isLeft ? '<' : '>';
        //移动到指定位置
        for (int i = 0; i < index; i++) {
            code.append(direction);
        }

    }

    /**
     * 移动位置
     *
     * @param code
     * @param shift
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
     * 向左移动位置
     *
     * @param index
     */
    public static void moveLeft(StringBuilder code, int index) {
        moveTo(code, index, true);
    }

    /**
     * 向右移动位置
     *
     * @param index
     */
    public static void moveRight(StringBuilder code, int index) {
        moveTo(code, index, false);
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
     * 实现两个位置相加
     *
     * @param code
     * @param fromIndex1
     * @param fromIndex2
     * @param toIndex
     */
    public static void add(StringBuilder code, int fromIndex1, int fromIndex2, int toIndex, int maxCellLength) {

        copy(code, fromIndex1, maxCellLength - 1);
        copy(code, fromIndex2, maxCellLength - 2);

        swap(code, maxCellLength - 1, toIndex);
        swap(code, maxCellLength - 2, toIndex);
    }
}
