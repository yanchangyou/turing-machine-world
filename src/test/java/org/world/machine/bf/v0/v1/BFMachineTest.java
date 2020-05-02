package org.world.machine.bf.v0.v1;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 实现 bf 语言
 * 语法：+、-、<、>、[]、.、,
 */
public class BFMachineTest {

    @Test
    public void test() {

        String code = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.";

        String result = BFMachine.execute(code);

        System.out.println();

        System.out.println("result:" + result);
        Assert.assertEquals(result, "A");

    }

    @Test
    public void testA_Z() {

        String A = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";

        StringBuilder code = new StringBuilder("");

        for (int i = 0; i <= 'Z' - 'A'; i++) {
            code.append(A);
            for (int j = 0; j < i; j++) {
                code.append("+");
            }
            code.append(".>");
        }

        System.out.println("code:" + code);
        String result = BFMachine.execute(code.toString());

        System.out.println();

        System.out.println("result:" + result);
        Assert.assertEquals(result, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    }

    @Test
    public void testSet_B_equal_A() {

        int a = 2;

        StringBuilder code = new StringBuilder("");
        for (int i = 0; i < a; i++) {
            code.append("+");
        }

        System.out.println("code:" + code);
        String result = BFMachine.execute(code.toString());

        System.out.println();

        System.out.println("result:" + result);
        Assert.assertEquals(result, "\u0002");

        code.append("[->+>+<<]>>[-<<+>>]");

        char[] cells = new char[10];

        BFMachine.execute(code.toString(), cells);

        Assert.assertEquals(cells[0], 2);
        Assert.assertEquals(cells[1], 2);
        Assert.assertEquals(cells[2], 0);
        Assert.assertEquals(cells[3], 0);

    }

    @Test
    public void testSet_B_equal_A_temp_zero() {

        int a = 2;

        StringBuilder code = new StringBuilder("");
        code.append(">");
        for (int i = 0; i < a; i++) {
            code.append("+");
        }
        char[] cells = new char[10];

        code.append("[->+<<+>]<[->+<]");

        System.out.println("code:" + code);

        BFMachine.execute(code.toString(), cells);

        Assert.assertEquals(cells[0], 0);
        Assert.assertEquals(cells[1], 2);
        Assert.assertEquals(cells[2], 2);
        Assert.assertEquals(cells[3], 0);
        Assert.assertEquals(cells[4], 0);

    }

    @Test
    public void testA_ADD_B() {

        int a = 1;
        int b = 2;

        StringBuilder code = new StringBuilder("");
        for (int i = 0; i < a; i++) {
            code.append("+");
        }
        code.append(">");
        for (int i = 0; i < b; i++) {
            code.append("+");
        }

        System.out.println("code:" + code);
        String result = BFMachine.execute(code.toString());

        System.out.println();

        System.out.println("result:" + result);
        Assert.assertEquals(result, "\u0001\u0002");

        code.append(">");
        for (int i = 0; i < a; i++) {
            code.append("+");
        }
        code.append(">");
        for (int i = 0; i < b; i++) {
            code.append("+");
        }

    }

}
