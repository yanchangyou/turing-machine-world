package org.world.machine.brainfuck.v0.v1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BrainFuckPlusPlusMachineTest {

    @Test
    public void test() {

        String code = "+65W";

        System.out.println("BrainFuck++ code:" + code);
        String result = BrainFuckPlusPlusMachine.execute(code);

        Assert.assertEquals(result, "A");

    }

    @Test
    public void test1() {

        String code = "+65W>+66W>+67W";

        System.out.println("BrainFuck++ code:" + code);
        String result = BrainFuckPlusPlusMachine.execute(code);

        Assert.assertEquals(result, "ABC");

    }

    @Test
    public void test2() {

        String code = convertToBrainFuckPlusPlusCode("hello,world!");

        System.out.println("BrainFuck++ code:" + code);

        String result = BrainFuckPlusPlusMachine.execute(code);

        Assert.assertEquals(result, "hello,world!");

    }

    /**
     * 把普通字符串转化为 BrainFuck++的输出代码
     *
     * @param text
     * @return
     */
    String convertToBrainFuckPlusPlusCode(String text) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append("+").append((short) text.charAt(i)).append("W>");
        }
        return builder.toString();
    }

}