package org.world.machine.brainfuck.v0.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * brain fuck的扩展
 * 1，指令替换： W替换.， R替换读
 * 2，语法扩展：指令后面带数字，表示重复多少次，减少输入
 * 比如：输出A， +65W
 */
public class BrainFuckPlusPlusMachine {

    public static void execute(String code) {

        String brainFuckCode = compile(code);

        BrainFuckMachine.execute(brainFuckCode);

    }

    private static String compile(String code) {
        String[] tokens = parseToken(code);
        return dealToken(tokens);
    }

    private static String dealToken(String[] tokens) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            if (Character.isDigit(tokens[i].charAt(0))) {
                int number = Integer.parseInt(tokens[i]) - 1;
                char lastChar = builder.charAt(builder.length() - 1);
                for (int j = 0; j < number; j++) {
                    builder.append(lastChar);
                }
            } else {
                builder.append(tokens[i]);
            }
        }

        return builder.toString();
    }

    private static String[] parseToken(String code) {

        List<String> tokens = new ArrayList<String>();
        for (int i = 0; i < code.length(); i++) {
            int beginIndex = i, endIndex = i;
            while (Character.isDigit(code.charAt(endIndex))) {
                endIndex++;
            }

            if (beginIndex == endIndex) {
                char instruction = code.charAt(i);
                if ('W' == instruction || 'w' == instruction) {
                    tokens.add(".");
                }
                if ('R' == instruction || 'r' == instruction) {
                    tokens.add(".");
                } else {
                    tokens.add(instruction + "");
                }
            } else {
                tokens.add(code.substring(beginIndex, endIndex));
                i += (endIndex - beginIndex - 1);
            }
        }

        return tokens.toArray(new String[0]);
    }
}
