package org.world.machine.brainfuck.v0.v1;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 实现 brain fuck 语言
 * 语法：+、-、<、>、[]、.、,
 */
public class BrainFuckMachineTest {

    PrintStream outerConsole = System.out;
    ByteArrayOutputStream innerConsole = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(innerConsole);

    {
        System.setOut(printStream);
    }

    @Test
    public void test() {

        String code = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.";

        BrainFuckMachine.execute(code);

        String result = innerConsole.toString();
        outerConsole.println(result);
        Assert.assertEquals(result, "A");

    }


}
