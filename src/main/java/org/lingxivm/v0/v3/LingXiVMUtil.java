package org.lingxivm.v0.v3;

/**
 * 灵犀图灵机 工具类
 */
public class LingXiVMUtil {

    /**
     * 初始化tape的值
     *
     * @param tape
     * @param initValue
     */
    static void initCells(String[] tape, String initValue) {
        for (int i = 0; i < tape.length; i++) {
            tape[i] = initValue;
        }
    }
    /**
     * 初始化tape的值
     *
     * @param tape
     * @param initValue
     */
    static void initTape(String[] tape, String initValue) {
        for (int i = 0; i < tape.length; i++) {
            tape[i] = initValue;
        }
    }

    static String cellsToString(String[] cells) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i <cells.length ; i++) {
            if (i!=0) {
                buffer.append(",");
            }
            buffer.append(cells[i]);
        }
        return buffer.toString();
    }
}


