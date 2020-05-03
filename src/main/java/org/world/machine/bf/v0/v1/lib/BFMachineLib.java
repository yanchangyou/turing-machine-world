package org.world.machine.bf.v0.v1.lib;

/**
 * 语言库
 * 1，前提：每次操作index都复位到0
 */
public class BFMachineLib {

    /**
     * 移动位置位置：
     *
     * @param index
     * @param isLeft
     */
    public static void move(StringBuilder code, int index, boolean isLeft) {

        char direction = isLeft ? '<' : '>';
        //移动到指定位置
        for (int i = 0; i < index; i++) {
            code.append(direction);
        }

    }

    /**
     * 向左移动位置
     *
     * @param index
     */
    public static void moveLeft(StringBuilder code, int index) {
        move(code, index, true);
    }

    /**
     * 向右移动位置
     *
     * @param index
     */
    public static void moveRight(StringBuilder code, int index) {
        move(code, index, false);
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
}
