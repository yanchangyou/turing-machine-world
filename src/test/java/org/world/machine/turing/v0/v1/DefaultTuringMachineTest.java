package org.world.machine.turing.v0.v1;

import org.testng.annotations.Test;

public class DefaultTuringMachineTest {

    @Test
    public void testExecute() {

        String[] tapeCells = new String[10];
        DefaultTuringMachine turingMachine = new DefaultTuringMachine(tapeCells);
        String programCode = "";
        turingMachine.execute(programCode);


    }
}