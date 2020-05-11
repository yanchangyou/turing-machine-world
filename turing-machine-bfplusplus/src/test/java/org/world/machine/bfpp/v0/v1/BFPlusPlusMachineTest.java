package org.world.machine.bfpp.v0.v1;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.world.machine.bf.v0.vx.BFMachine;

public class BFPlusPlusMachineTest {

    @BeforeTest
    public void before() {
        BFMachine.disableLog();
    }

    @Test
    public void test() {

        String code = "+65W";

        System.out.println("BF++ code:" + code);
        String result = BFPlusPlusMachine.execute(code);

        Assert.assertEquals(result, "A");

    }

    @Test
    public void test1() {

        String code = "+65>+66>+67";

        System.out.println("BF++ code:" + code);
        String result = BFPlusPlusMachine.execute(code);

        Assert.assertEquals(result, "ABC");

    }

    @Test
    public void hello() {

        String code = convertToBFPlusPlusCode("hello,world!");

        System.out.println("BF++ code:" + code);

        String result = BFPlusPlusMachine.execute(code);
        Assert.assertEquals(result, "hello,world!");

    }

    @Test
    public void testA_Z() {

        StringBuilder code = new StringBuilder();

        for (int i = 'A'; i <= 'Z'; i++) {
            code.append("+").append(i);
            code.append(">");
        }

        System.out.println("code:" + code);
        String result = BFPlusPlusMachine.execute(code.toString());

        System.out.println();

        System.out.println("result:" + result);
        Assert.assertEquals(result, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    }

    /**
     * 把普通字符串转化为 BF++的输出代码
     *
     * @param text
     * @return
     */
    String convertToBFPlusPlusCode(String text) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append("+").append((short) text.charAt(i)).append(".>");
        }
        return builder.toString();
    }

}