package org.world.machine.bf.v0.v2;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.world.machine.bf.v0.v2.lib.BFMachineBasicLib;
import org.world.machine.bf.v0.v2.lib.BFMachineMathLib;

/**
 * 实现 bf 语言
 * 语法：+、-、<、>、[]、.、,
 */
public class BFMachineUseMathLibTest {

    @Test
    public void testMath() {

        int[] inputValue = { 6, 3, 0 };
        int[] inputIndex = { 1, 2, 3 };

        BFMachineMathLib.OperateType[] operateTypes = {
                BFMachineMathLib.OperateType.ADD,
                BFMachineMathLib.OperateType.SUB,
                BFMachineMathLib.OperateType.MUL,
                BFMachineMathLib.OperateType.DIV };
        int[] output = { 9, 3, 18, 2 };

        final int MAX_CELLS_LENGTH = 6;

        for (int i = 0; i < operateTypes.length; i++) {
            StringBuilder code = new StringBuilder();
            for (int j = 0; j < inputIndex.length; j++) {
                BFMachineBasicLib.set(code, inputIndex[j], inputValue[j]);
            }
            BFMachineMathLib
                    .calculate(code, operateTypes[i], inputIndex[0], inputIndex[1], inputIndex[2], MAX_CELLS_LENGTH);
            System.out.println("code:" + code);
            int[] cells = BFMachine.execute(code.toString(), MAX_CELLS_LENGTH);
            Assert.assertEquals(cells[inputIndex[inputIndex.length - 1]], output[i]);
        }

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

        BFMachineBasicLib.set(code, aIndex, a);
        BFMachineBasicLib.set(code, bIndex, b);

        int maxCellLength = 6;

        BFMachineMathLib.add(code, aIndex, bIndex, cIndex, maxCellLength);

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

        int a = 2;
        int b = 1;
        int c = 0;

        int aIndex = 1;
        int bIndex = 2;
        int cIndex = 3;

        StringBuilder code = new StringBuilder("");

        BFMachineBasicLib.set(code, aIndex, a);
        BFMachineBasicLib.set(code, bIndex, b);

        int maxCellLength = 6;

        BFMachineMathLib.sub(code, aIndex, bIndex, cIndex, maxCellLength);

        System.out.println("code:" + code);
        int[] cells = BFMachine.execute(code.toString(), 6);

        System.out.println();

        int[] excepted = new int[] { 0, 2, 1, 1, 0, 0 };

        Assert.assertEquals(cells, excepted);

        c = cells[cIndex];
        Assert.assertEquals(c, 1);
    }

    @Test
    public void testA_MUL_B() {

        int a = 2;
        int b = 3;
        int c = 0;

        int aIndex = 1;
        int bIndex = 2;
        int cIndex = 3;

        StringBuilder code = new StringBuilder("");

        BFMachineBasicLib.set(code, aIndex, a);
        BFMachineBasicLib.set(code, bIndex, b);

        int maxCellLength = 6;

        BFMachineMathLib.mul(code, aIndex, bIndex, cIndex, maxCellLength);

        System.out.println("code:" + code);
        int[] cells = BFMachine.execute(code.toString(), 6);

        System.out.println();

        int[] excepted = new int[] { 0, 2, 3, 6, 0, 0 };

        Assert.assertEquals(cells, excepted);

        c = cells[cIndex];
        Assert.assertEquals(c, 6);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testA_DIV_B_7_2() {

        int a = 7;
        int b = 2;
        int c;

        int aIndex = 1;
        int bIndex = 2;
        int cIndex = 3;

        StringBuilder code = new StringBuilder("");

        BFMachineBasicLib.set(code, aIndex, a);
        BFMachineBasicLib.set(code, bIndex, b);

        int maxCellLength = 6;

        BFMachineMathLib.div(code, aIndex, bIndex, cIndex, maxCellLength);

        System.out.println("code:" + code);
        int[] cells = BFMachine.execute(code.toString(), 6);

        System.out.println();

        int[] excepted = new int[] { 0, 7, 2, 3, 1, 0 };

        Assert.assertEquals(cells, excepted);

        c = cells[cIndex];
        Assert.assertEquals(c, 3);
        Assert.assertEquals(cells[4], 1);
    }

    @Test
    public void testA_DIV_B() {

        int a = 6;
        int b = 2;
        int c;

        int aIndex = 1;
        int bIndex = 2;
        int cIndex = 3;

        StringBuilder code = new StringBuilder("");

        BFMachineBasicLib.set(code, aIndex, a);
        BFMachineBasicLib.set(code, bIndex, b);

        int maxCellLength = 6;

        BFMachineMathLib.div(code, aIndex, bIndex, cIndex, maxCellLength);

        System.out.println("code:" + code);
        int[] cells = BFMachine.execute(code.toString(), 6);

        System.out.println();

        int[] excepted = new int[] { 0, 6, 2, 3, 0, 0 };

        Assert.assertEquals(cells, excepted);

        c = cells[cIndex];
        Assert.assertEquals(c, 3);
    }

}
