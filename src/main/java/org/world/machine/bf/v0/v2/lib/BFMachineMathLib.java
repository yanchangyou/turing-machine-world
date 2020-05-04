package org.world.machine.bf.v0.v2.lib;

/**
 * 数学库
 */
public class BFMachineMathLib {

    public static enum OperateType {
        ADD("+"), SUB("-"), MUL("*"), DIV("/");

        String code;

        OperateType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 集成计算
     *
     * @param code
     * @param operateType
     * @param numberIndex1
     * @param numberIndex2
     * @param resultIndex
     * @param maxCellLength
     */
    public static void calculate(StringBuilder code, OperateType operateType, int numberIndex1, int numberIndex2,
            int resultIndex, int maxCellLength) {

        switch (operateType) {
            case ADD:
                add(code, numberIndex1, numberIndex2, resultIndex, maxCellLength);
                break;
            case SUB:
                sub(code, numberIndex1, numberIndex2, resultIndex, maxCellLength);
                break;
            case MUL:
                mul(code, numberIndex1, numberIndex2, resultIndex, maxCellLength);
                break;
            case DIV:
                div(code, numberIndex1, numberIndex2, resultIndex, maxCellLength);
                break;
        }
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

        BFMachineBasicLib.copy(code, numberIndex1, maxCellLength - 1);
        BFMachineBasicLib.copy(code, numberIndex2, maxCellLength - 2);

        BFMachineBasicLib.swap(code, maxCellLength - 1, resultIndex);
        BFMachineBasicLib.swap(code, maxCellLength - 2, resultIndex);
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

        BFMachineBasicLib.copy(code, numberIndex1, resultIndex);
        BFMachineBasicLib.copy(code, numberIndex2, maxCellLength - 1);

        BFMachineBasicLib.doubleSub(code, maxCellLength - 1, resultIndex);

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

        BFMachineBasicLib.copy(code, numberIndex1, maxCellLength - 1);
        BFMachineBasicLib.moveRight(code, maxCellLength - 1);
        code.append("[-");
        BFMachineBasicLib.moveLeft(code, maxCellLength - 1);
        BFMachineBasicLib.copy(code, numberIndex2, resultIndex);
        BFMachineBasicLib.moveRight(code, maxCellLength - 1);
        code.append("]");
        BFMachineBasicLib.moveLeft(code, maxCellLength - 1);
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

        BFMachineBasicLib.copy(code, numberIndex1, maxCellLength - 1);

        BFMachineBasicLib.moveRight(code, maxCellLength - 1);
        code.append("[");
        BFMachineBasicLib.moveLeft(code, maxCellLength - 1);

        BFMachineBasicLib.copy(code, numberIndex2, maxCellLength - 2);

        BFMachineBasicLib.moveRight(code, maxCellLength - 2);
        code.append("[");
        code.append("->-");

        code.append("<");
        code.append("]");
        BFMachineBasicLib.moveLeft(code, maxCellLength - 2);

        BFMachineBasicLib.moveRight(code, resultIndex);
        code.append("+");
        BFMachineBasicLib.moveLeft(code, resultIndex);

        BFMachineBasicLib.moveRight(code, maxCellLength - 1);
        code.append("]");
        BFMachineBasicLib.moveLeft(code, maxCellLength - 1);

    }
}
