package org.world.machine.bf.v0.v2;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.world.machine.bf.v0.v2.lib.BFMachineBasicLib;
import org.world.machine.bf.v0.v2.lib.BFMachineLogicLib;

public class BFMachineLogicTest {

    @Test
    public void testTrue() {
        int index1 = 1;
        int index2 = 2;
        int index1Value = 2;
        int index2Value = 0;
        StringBuilder code = new StringBuilder();
        BFMachineBasicLib.set(code, index1, index1Value);
        BFMachineBasicLib.set(code, index2, index2Value);
        BFMachineLogicLib.isTrue(code, index1);
        int[] cells = BFMachine.execute(code.toString(), 6);
        Assert.assertEquals(cells[0], 1);

        BFMachineLogicLib.isFalse(code, index2);
        cells = BFMachine.execute(code.toString(), 6);
        Assert.assertEquals(cells[0], -1);

    }
}
