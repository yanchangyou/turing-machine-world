package org.lingxivm.v0.vx;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LingXiVmTest {

    @Test
    public void run() {

        // 带子
        String tape = "0,0,0,0";
        //规则
        String rule = LingXiVmLib.getResetRule("1");

        String excepted = "1,1,1,1";

        printRule(rule);
        printTape(tape);
        String result = LingXiVm.run(tape, rule);
        printTape(result);

        Assert.assertEquals(result, excepted);
    }

    @Test
    public void runMore() {

        // 带子
        String tape = "0,0,0,0";
        //规则
        String[] rule = { LingXiVmLib.getResetRule("1"), LingXiVmLib.getResetRule("0") };

        String[] excepted = { "1,1,1,1", "0,0,0,0" };

        for (int i = 0; i < rule.length; i++) {
            printRule(rule[i]);
            printTape(tape);
            String result = LingXiVm.run(tape, rule[i]);
            printTape(result);

            Assert.assertEquals(result, excepted[i]);
            System.out.println();
        }

    }

    private static void printTape(String tape) {
        System.out.println("tape : " + tape);
    }

    private static void printRule(String rule) {
        System.out.println("rule:\r\n" + rule);
    }
}