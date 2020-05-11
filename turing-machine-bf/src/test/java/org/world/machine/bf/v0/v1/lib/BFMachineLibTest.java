package org.world.machine.bf.v0.v1.lib;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BFMachineLibTest {

    @Test
    public void testSwap() {
        StringBuilder code = new StringBuilder();

        int index1 = 1;
        int index2 = 2;
        BFMachineLib.swap(code, index1, index2);

        System.out.println(code);
        Assert.assertEquals(code.toString(), ">[->+<]<");
    }

    @Test
    public void testSwapSameIndex() {
        StringBuilder code = new StringBuilder();

        int a = 1;
        int b = 1;
        BFMachineLib.swap(code, a, b);

        System.out.println(code);
        Assert.assertEquals(code.toString(), "");
    }

    @Test
    public void testSet() {
        StringBuilder code = new StringBuilder();
        int index = 1;
        int value = 2;
        BFMachineLib.set(code, index, value);

        Assert.assertEquals(code.toString(), ">++<");
    }

    @Test
    public void testOutput() {
        StringBuilder code = new StringBuilder();
        int index = 1;
        BFMachineLib.output(code, index);
        Assert.assertEquals(code.toString(), ">.<");
    }

    @Test
    public void testTestSwap() {
        StringBuilder code = new StringBuilder();

        int fromIndex = 1;
        int toIndex1 = 2;
        int toIndex2 = 3;
        BFMachineLib.swap(code, fromIndex, toIndex1, toIndex2);

        System.out.println(code);
        Assert.assertEquals(code.toString(), ">[->+>+<<]<");
    }

    @Test
    public void testCopy() {
        StringBuilder code = new StringBuilder();

        int fromIndex = 1;
        int toIndex = 2;
        BFMachineLib.copy(code, fromIndex, toIndex);

        System.out.println(code);
        Assert.assertEquals(code.toString(), ">[->+<<+>]<[->+<]");
    }

    @Test
    public void testDoubleSubOne() {
        StringBuilder code = new StringBuilder();
        int index1 = 1;
        int index2 = 2;
        BFMachineLib.doubleSub(code, index1, index2);

        System.out.println(code);
        Assert.assertEquals(code.toString(), ">[->-<]<");
    }

    @Test
    public void testDoubleSubOneSameIndex() {
        StringBuilder code = new StringBuilder();
        int index1 = 1;
        int index2 = 1;
        BFMachineLib.doubleSub(code, index1, index2);

        System.out.println(code);
        Assert.assertEquals(code.toString(), "");
    }
}