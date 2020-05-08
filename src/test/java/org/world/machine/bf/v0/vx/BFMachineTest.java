package org.world.machine.bf.v0.vx;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

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

        StringBuilder code = new StringBuilder();

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

        StringBuilder code = new StringBuilder();
        for (int i = 0; i < a; i++) {
            code.append("+");
        }

        System.out.println("code:" + code);
        String result = BFMachine.execute(code.toString());

        System.out.println();

        System.out.println("result:" + result);
        Assert.assertEquals(result, "\u0002");

        code.append("[->+>+<<]>>[-<<+>>]");

        int[] cells = new int[10];

        BFMachine.execute(code.toString(), cells);

        Assert.assertEquals(cells[0], 2);
        Assert.assertEquals(cells[1], 2);
        Assert.assertEquals(cells[2], 0);
        Assert.assertEquals(cells[3], 0);

    }

    @Test
    public void testSet_B_equal_A_temp_zero() {

        int a = 2;

        StringBuilder code = new StringBuilder();
        code.append(">");
        for (int i = 0; i < a; i++) {
            code.append("+");
        }
        int[] cells = new int[10];

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

        StringBuilder code = new StringBuilder();
        code.append(">");
        for (int i = 0; i < a; i++) {
            code.append("+");
        }
        code.append(">");
        for (int i = 0; i < b; i++) {
            code.append("+");
        }

        System.out.println("code:" + code);

        int[] cells = BFMachine.execute(code.toString(), 16);

        System.out.println();

        int[] excepted = new int[] { 0, 1, 2, 0 };

        for (int i = 0; i < excepted.length; i++) {
            Assert.assertEquals(cells[i], excepted[i], "index:" + i);
        }

    }

    @Test
    public void testRead() {

        ByteArrayInputStream bio = new ByteArrayInputStream("A".getBytes());
        System.setIn(bio);
        StringBuilder code = new StringBuilder();
        code.append(",");
        System.out.println(code);
        int[] cells = BFMachine.execute(code.toString(), 1);
        Assert.assertEquals(cells[0], 'A');

    }

    @Test(expectedExceptions = Exception.class)
    public void testReadException() {

        ByteArrayInputStream bio = null;
        System.setIn(bio);
        StringBuilder code = new StringBuilder();
        code.append(",");
        System.out.println(code);
        BFMachine.execute(code.toString(), 1);

    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testOutOffBoundException() {

        StringBuilder code = new StringBuilder();
        code.append(">>");
        System.out.println(code);
        BFMachine.execute(code.toString(), 1);

    }

    @Test
    public void testFirstBoundIsZero() {

        StringBuilder code = new StringBuilder();
        code.append("[++[++]]>++");
        System.out.println(code);
        int[] cells = BFMachine.execute(code.toString(), 2);
        Assert.assertEquals(cells[0], 0);
        Assert.assertEquals(cells[1], 2);

    }

    @Test
    public void testHelloWorld() {
        StringBuilder code = new StringBuilder();

        code.append(">+++++++++[<++++++++>-]<.>+++++++[<++++>-]<+.+++++++..+++.[-]>++++++++[<++++>-]\n"
                + "<.>+++++++++++[<++++++++>-]<-.--------.+++.------.--------.[-]>++++++++[<++++>-\n"
                + "]<+.[-]++++++++++. ");
        BFMachine.disableLog();
        String result = BFMachine.execute(code.toString());
        System.out.println(result);
        BFMachine.enableLog();
    }
}
