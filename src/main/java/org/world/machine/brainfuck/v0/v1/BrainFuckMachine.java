package org.world.machine.brainfuck.v0.v1;

import java.io.IOException;

/**
 * fuck brain 简单实现
 */
public class BrainFuckMachine {

    /**
     * brain fuck执行
     *
     * @param code
     */
    public static String execute(String code) {

        char[] cells = new char[1024];

        char[] instructions = code.toCharArray();
        int index = 0;
        for (int i = 0; i < instructions.length; i++) {
            if ('+' == instructions[i]) {
                cells[index]++;
            } else if ('-' == instructions[i]) {
                cells[index]--;
            } else if ('.' == instructions[i]) {
                System.out.print(cells[index]);
            } else if ('>' == instructions[i]) {
                index++;
            } else if ('<' == instructions[i]) {
                index--;
            } else if (',' == instructions[i]) {
                try {
                    char ch = (char) System.in.read();
                    cells[index] = ch;
                } catch (IOException e) {
                    throw new RuntimeException("read exception:", e);
                }
            }
            if (index < 0 || index >= cells.length) {
                throw new RuntimeException("out of index :" + index);
            }
        }

        return convertToString(cells);
    }

    static String convertToString(char[] cells) {
        int endIndex = 0;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == 0) {
                endIndex = i;
                break;
            }
        }
        return new String(cells, 0, endIndex);
    }

}
