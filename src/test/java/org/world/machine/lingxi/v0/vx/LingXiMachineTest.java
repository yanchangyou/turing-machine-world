package org.world.machine.lingxi.v0.vx;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 借鉴bf语言，并做简单改进，使语义更简明，
 * 1，带子是char
 * 2，指令字符：+，-，<，>，i,o,[,]
 * 2.1
 */
public class LingXiMachineTest {

    @Test
    public void run() {

        // 带子
        String tape = "0,0,0,0";
        //规则
        String rule = LingXiMachineLib.getResetRule("1");

        String excepted = "1,1,1,1";

        printRule(rule);
        printTape(tape);
        String result = LingXiMachine.run(tape, rule);
        printTape(result);

        Assert.assertEquals(result, excepted);
    }

    @Test
    public void runMore() {

        // 带子
        String tape = "0,0,0,0";
        //规则
        String[] rule = { LingXiMachineLib.getResetRule("1"), LingXiMachineLib.getResetRule("0") };

        String[] excepted = { "1,1,1,1", "0,0,0,0" };

        for (int i = 0; i < rule.length; i++) {
            printRule(rule[i]);
            printTape(tape);
            String result = LingXiMachine.run(tape, rule[i]);
            printTape(result);

            Assert.assertEquals(result, excepted[i]);
            System.out.println();
        }

    }

    @Test
    public void runIndex() {

        // 带子
        String tape = "0,0,0,0";
        String[] excepted = { "1,0,0,0", "0,1,0,0", "0,0,1,0", "0,0,0,1" };
        for (int i = 0; i < excepted.length; i++) {
            //规则
            String rule = LingXiMachineLib.getResetRule(i, "1");

            printRule(rule);
            printTape(tape);
            String result = LingXiMachine.run(tape, rule);
            printTape(result);

            Assert.assertEquals(result, excepted[i]);
        }

    }

    @Test
    public void runIndexMore() {

        // 带子
        String tape = LingXiMachineLib.initTape(4);
        String excepted = LingXiMachineLib.initTape(4, "1");
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < tape.split("\\s*,\\s*").length; i++) {
            //规则
            String rule = LingXiMachineLib.getResetRule(i, "1");
            buf.append(rule + "\r\n");
        }
        printRule(buf.toString());
        printTape(tape);
        String result = LingXiMachine.run(tape, buf.toString());
        printTape(result);

        Assert.assertEquals(result, excepted);

    }

    @Test
    public void runRange() {

        // 带子
        String tape = "0,0,0,0";
        String[] excepted = { "1,0,0,0", "1,1,0,0", "1,1,1,0", "1,1,1,1" };
        for (int i = 0; i < excepted.length; i++) {
            //规则
            String rule = LingXiMachineLib.getResetRule(0, i, "1");

            printRule(rule);
            printTape(tape);
            String result = LingXiMachine.run(tape, rule);
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