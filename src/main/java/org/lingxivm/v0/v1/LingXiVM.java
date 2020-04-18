package org.lingxivm.v0.v1;

import java.util.HashMap;
import java.util.Map;

/**
 * 灵犀图灵机 v0.1
 */
public class LingXiVM {
    public static void main(String[] args) {

        // 带子
        int[] tape = new int[3];

        //program
        Map<String, String> code = new HashMap();
        // key: status,inputValue
        // value: status, outputValue，forwardOrBackward
        code.put("0,0", "1,1,+");
        code.put("0,1", "1,1,+");
        code.put("1,0", "1,1,+");
        code.put("1,1", "1,1,+");

        code.put("-21,0", "0,,");
        code.put("-21,1", "0,,");

        printTape(tape);
        run(tape, code);
        printTape(tape);
    }

    private static void printTape(int[] tape) {
        System.out.print("tape : ");
        for (int i = 0; i < tape.length; i++) {
            if (i != 0) {
                System.out.print(",");
            }
            System.out.print(tape[i]);
        }
        System.out.println();
    }

    private static void run(int[] tape, Map<String, String> code) {
        //内部状态
        int status = 1;// 状态：0：停止，>0:运行，<0：异常状态；1：运行，-11：超过最小边界，-21：超过最大边界

        int index = 0;

        while (true) {
            int value = tape[index];
            String next = code.get(status + "," + value);
            String[] nextParts = next.split(",", -1);

            int newStatus = Integer.valueOf(nextParts[0]);

            status = newStatus;

            //虚拟机内部状态
            if (status == 0) {
                System.out.println("lingxivm stop!");
                break;
            }
            if (status < 0) {
                System.out.println("lingxivm error!");
                break;
            }

            int outputValue = Integer.valueOf(nextParts[1]);

            tape[index] = outputValue;

            String forwardOrBackward = nextParts[2];
            //移动方向：向前、向后、不动
            if ("+".equalsIgnoreCase(forwardOrBackward)) {//向前，否则向后
                index++;
            } else if ("-".equalsIgnoreCase(forwardOrBackward)) {
                index--;
            }

            //边界状态设置
            if (index < 0) {// 超过最小边界
                status = -11;
            }
            if (index >= tape.length) { //超过最大边界
                status = -21;
            }

            // 边界异常
            if (index < 0 || index >= tape.length) {
                System.out.println("ERROR : index out off bounder");
                break;
            }
        }
    }
}


