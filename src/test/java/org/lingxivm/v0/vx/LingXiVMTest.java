package org.lingxivm.v0.vx;

import org.testng.annotations.Test;

public class LingXiVMTest {

    @Test
    public void run() {

        // 带子
        String tape = "0,0,0,0";
        //规则
        String rule = ""
                + "0,0->1,1,+\r\n"
                + "0,1->1,1,+\r\n"
                + "1,0->1,1,+\r\n"
                + "1,1->1,1,+\r\n"
                + "21,0->0,1,\r\n"
                + "21,1->0,1,\r\n";

        printRule(rule);
        printTape(tape);
        String result = LingXiVM.run(tape, rule);
        printTape(result);
    }

    private static void printTape(String tape) {
        System.out.println("tape : " + tape);
    }

    private static void printRule(String rule) {
        System.out.println("rule:\r\n" + rule);
    }
}