package org.world.machine.bf.v0.v2.lib;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BFMachineLogicLibTest {

    @Test
    public void testIsTrue() {
        StringBuilder code = new StringBuilder();
        int index = 1;
        BFMachineLogicLib.isTrue(code, index);
        System.out.println(code);
        Assert.assertEquals(code.toString(), "&_>[<+^]&");
    }

    @Test
    public void testIsFalse() {
        StringBuilder code = new StringBuilder();
        int index = 1;
        BFMachineLogicLib.isFalse(code, index);
        System.out.println(code);
        Assert.assertEquals(code.toString(), "&_->[<+^]&");
    }
}