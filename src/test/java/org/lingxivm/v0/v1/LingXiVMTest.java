package org.lingxivm.v0.v1;

import org.lingxivm.v0.v1.LingXiVM;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class LingXiVMTest {

    @Test
    void run() {

        // 带子
        int[] tape = new int[3];

        //program
        Map<String, String> map = new HashMap();
        // key: status,inputValue
        // value: status, outputValue，forwardOrBackward
        map.put("0,0", "1,1,+");
        map.put("0,1", "1,1,+");
        map.put("1,0", "1,1,+");
        map.put("1,1", "1,1,+");

        map.put("-21,0", "0,,");
        map.put("-21,1", "0,,");

        printTape(tape);
        LingXiVM.run(tape, map);
        printTape(tape);
    }

    private static void printTape(int[] tape) {
        System.out.print("tape : ");
        for (int i = 0; i < tape.length; i++) {
            if (i != 0) {
                System.out.print(",");
            }
            System.out.print(tape[i]);
        }
        System.out.println();
    }
}