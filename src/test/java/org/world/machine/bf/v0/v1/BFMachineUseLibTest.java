package org.world.machine.bf.v0.v1;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.world.machine.bf.v0.v1.lib.BFMachineLib;

import java.util.Arrays;

/**
 * 实现 bf 语言
 * 语法：+、-、<、>、[]、.、,
 */
public class BFMachineUseLibTest {

    @Test
    public void test() {

        StringBuilder code = new StringBuilder("");

        BFMachineLib.set(code, 0, 65);

        System.out.println("code:" + code);

        String result = BFMachine.execute(code.toString());

        System.out.println("result:" + result);
        Assert.assertEquals(result, "A");

    }

    @Test
    public void testA_Z() {

        StringBuilder code = new StringBuilder("");

        for (int i = 0; i <= 'Z' - 'A'; i++) {

            BFMachineLib.set(code, i, i + 65);
            BFMachineLib.output(code, i);

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
        //        int b = 0;
        int[] excepted = new int[] { 0, 2, 2, 0 };

        StringBuilder code = new StringBuilder("");
        BFMachineLib.set(code, 1, a);

        BFMachineLib.copy(code, 1, 2);
        System.out.println("code:" + code);
        int[] cells = BFMachine.execute(code.toString(), excepted.length);

        System.out.println(Arrays.toString(excepted));
        System.out.println(Arrays.toString(cells));

        Assert.assertEquals(cells, excepted);

    }

    @Test
    public void testSet_B_equal_A_temp_zero() {

        int a = 2;

        StringBuilder code = new StringBuilder("");
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
        int c = 0;

        int aIndex = 1;
        int bIndex = 2;
        int cIndex = 3;

        StringBuilder code = new StringBuilder("");

        BFMachineLib.set(code, aIndex, a);
        BFMachineLib.set(code, bIndex, b);

        int maxCellLength = 6;

        BFMachineLib.add(code, aIndex, bIndex, cIndex, maxCellLength);

        System.out.println("code:" + code);
        int[] cells = BFMachine.execute(code.toString(), 6);

        System.out.println();

        int[] excepted = new int[] { 0, 1, 2, 3, 0, 0 };

        Assert.assertEquals(cells, excepted);

        c = cells[cIndex];
        Assert.assertEquals(c, 3);
    }

    @Test
    public void testA_SUB_B() {

        int a = 1;
        int b = 2;
        int c = 0;

        int aIndex = 1;
        int bIndex = 2;
        int cIndex = 3;

        StringBuilder code = new StringBuilder("");

        BFMachineLib.set(code, aIndex, a);
        BFMachineLib.set(code, bIndex, b);

        int maxCellLength = 6;

        BFMachineLib.sub(code, aIndex, bIndex, cIndex, maxCellLength);

        System.out.println("code:" + code);
        int[] cells = BFMachine.execute(code.toString(), 6);

        System.out.println();

        int[] excepted = new int[] { 0, 1, 2, -1, 0, 0 };

        Assert.assertEquals(cells, excepted);

        c = cells[cIndex];
        Assert.assertEquals(c, -1);
    }

}
