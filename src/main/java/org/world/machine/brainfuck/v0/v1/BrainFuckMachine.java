package org.world.machine.brainfuck.v0.v1;

public class BrainFuckMachine {

    static char[] cells = new char[100];

    public static void execute(String code) {

        char[] instructions = code.toCharArray();
        int index = 0;
        for (int i = 0; i < instructions.length; i++) {
            if ('+' == instructions[i]) {
                cells[index]++;
            } else if ('-' == instructions[i]) {
                cells[index]--;
            } else if ('.' == instructions[i]) {
                System.out.print(cells[index]);
            } else  if (',' == instructions[i]) {

            }
        }
    }
}
