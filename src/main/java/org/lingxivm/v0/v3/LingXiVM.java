package org.lingxivm.v0.v3;

import java.util.HashMap;
import java.util.Map;

/**
 * 灵犀图灵机
 */
public class LingXiVM {

    /**
     * 图灵机运行
     * 全字符串入参
     *
     * @param tape 逗号分割
     * @param rule oldStatus,oldValue->newStatus,newValue,forward\r\n next
     */
    public static String run(String tape, String rule) {

        String[] cells = tape.split(",", -1);
        String[] rules = rule.split("\\\r\\\n|\\\r|\\\n");

        Map<String, String> map = new HashMap();
        for (int i = 0; i < rules.length; i++) {
            String[] ruleItem = rules[i].split("\\s*(,|(->))\\s*", -1);
            map.put(ruleItem[0] + "," + ruleItem[1], ruleItem[2] + "," + ruleItem[3] + "," + ruleItem[4]);
        }
        run(cells, map);

        return LingXiVMUtil.cellsToString(cells);
    }

    /**
     * 图灵机运行
     *
     * @param cells
     * @param map
     */
    private static void run(String[] cells, Map<String, String> map) {

        //内部状态
        String status = "1";// 状态：0：停止，>0:运行，<0：异常状态；1：运行，-101：超过最小边界，-102：超过最大边界

        int index = 0;

        System.out.println("LingXiVM begin run:");
        while (true) {
            String value = cells[index];
            String next = map.get(status + "," + value);
            String[] nextParts = next.split(",", -1);

            String newStatus = nextParts[0];

            status = newStatus;
            String outputValue = nextParts[1];

            cells[index] = outputValue;

            //虚拟机内部状态
            if ("0".equals(status)) {
                System.out.println("LingXiVM stop!");
                break;
            }
            if (status.startsWith("-")) {
                System.out.println("LingXiVM error!");
                break;
            }

            String forwardOrBackward = nextParts[2];
            //移动方向：向前、向后、不动
            if ("+".equalsIgnoreCase(forwardOrBackward)) {//向前，否则向后
                index++;
            } else if ("-".equalsIgnoreCase(forwardOrBackward)) {
                index--;
            }

            //边界状态设置
            if (index == 0) {// 超过最小边界
                status = "11";
            }
            if (index == cells.length - 1) { //超过最大边界
                status = "21";
            }

            // 边界异常
            if (index < 0 || index >= cells.length) {
                System.out.println("ERROR : index out off bounder");
                break;
            }
        }
    }
}


