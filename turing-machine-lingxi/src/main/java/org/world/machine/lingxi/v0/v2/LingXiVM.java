package org.world.machine.lingxi.v0.v2;

import java.util.Map;

/**
 * 灵犀图灵机 v0.2
 */
public class LingXiVM {

    /**
     * 图灵机运行：
     *
     * @param tape
     * @param map
     */
    public static void run(int[] tape, Map<String, String> map) {

        //内部状态
        int status = 1;// 状态：0：停止，>0:运行，<0：异常状态；1：运行，-101：超过最小边界，-102：超过最大边界

        int index = 0;

        while (true) {
            int value = tape[index];
            String next = map.get(status + "," + value);
            String[] nextParts = next.split(",", -1);

            int newStatus = Integer.valueOf(nextParts[0]);

            status = newStatus;
            int outputValue = Integer.valueOf(nextParts[1]);

            tape[index] = outputValue;

            //虚拟机内部状态
            if (status == 0) {
                System.out.println("lingxivm stop!");
                break;
            }
            if (status < 0) {
                System.out.println("lingxivm error!");
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
                status = 11;
            }
            if (index == tape.length - 1) { //超过最大边界
                status = 21;
            }

            // 边界异常
            if (index < 0 || index >= tape.length) {
                System.out.println("ERROR : index out off bounder");
                break;
            }
        }
    }
}


