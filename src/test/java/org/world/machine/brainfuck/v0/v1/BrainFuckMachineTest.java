package org.world.machine.brainfuck.v0.v1;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 实现 brain fuck 语言
 * 语法：+、-、<、>、[]、.、,
 */
public class BrainFuckMachineTest {

    @Test
    public void test() {

        String code = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.";

        String result = BrainFuckMachine.execute(code);

        System.out.println();

        System.out.println("result:" + result);
        Assert.assertEquals(result, "A");

    }

}
