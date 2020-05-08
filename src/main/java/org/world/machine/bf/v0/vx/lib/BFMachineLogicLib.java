package org.world.machine.bf.v0.vx.lib;

/**
 * 逻辑库
 * 1,结果怎么表示，怎么获取？ 第一位标识逻辑结果
 */
public class BFMachineLogicLib {

    /**
     * 判断给定位置是否是true
     *
     * @param code
     * @param index
     */
    public static void isTrue(StringBuilder code, int index) {
        isTrueOrFalse(code, index, true);
    }

    /**
     * 判断给定位置是否是false
     *
     * @param code
     * @param index
     */
    public static void isFalse(StringBuilder code, int index) {

        isTrueOrFalse(code, index, false);
    }

    public static void isTrueOrFalse(StringBuilder code, int index, boolean isTrue) {
        //清空首位
        code.append("&_");
        BFMachineBasicLib.setValue(code, isTrue ? 0 : -1);
        BFMachineBasicLib.moveRight(code, index);
        code.append("[");
        BFMachineBasicLib.moveLeft(code, index);
        code.append("+");
        code.append("^");
        code.append("]");
        code.append("&");
    }
}
