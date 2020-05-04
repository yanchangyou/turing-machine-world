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
        int[] output = { 9, 3, 18, 2 };

        BFMachineMathLib.OperateType[] operateTypes = {
                BFMachineMathLib.OperateType.ADD,
                BFMachineMathLib.OperateType.SUB,
                BFMachineMathLib.OperateType.MUL,
                BFMachineMathLib.OperateType.DIV };

        final int MAX_CELLS_LENGTH = 6;

        for (int i = 0; i < operateTypes.length; i++) {
            StringBuilder code = new StringBuilder();
            for (int j = 0; j < inputIndex.length; j++) {
                BFMachineBasicLib.set(code, inputIndex[j], inputValue[j]);
            }
            BFMachineMathLib
                    .calculate(code, operateTypes[i], inputIndex[0], inputIndex[1], inputIndex[2], MAX_CELLS_LENGTH);
            String message =
                    operateTypes[i] + " : " + inputValue[0] + " " + operateTypes[i].getCode() + " " + inputValue[1];
            System.out.println(message);
            System.out.println("code:" + code);
            int[] cells = BFMachine.execute(code.toString(), MAX_CELLS_LENGTH);
            Assert.assertEquals(cells[inputIndex[inputIndex.length - 1]], output[i], message);
        }

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

}
