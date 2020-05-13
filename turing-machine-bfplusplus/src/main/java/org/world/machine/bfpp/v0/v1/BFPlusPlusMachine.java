package org.world.machine.bfpp.v0.v1;

import org.world.machine.bf.v0.vx.BFMachine;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * BF++: BF的扩展
 * 1，指令替换：&:重置到首位，_单元格置0，^无条件break，!有条件break(条件和[一样)
 * 2，语法扩展：指令后面带数字，表示重复多少次，减少输入
 * 比如：输出A， +65.
 */
public class BFPlusPlusMachine {

    /**
     * 先编译，然后执行
     *
     * @param code
     * @return
     */
    public static String execute(String code) {
        return execute(code, System.in, System.out);
    }

    /**
     * 先编译，然后执行
     *
     * @param code
     * @return
     */
    public static String execute(String code, InputStream input, OutputStream output) {

        String brainFuckCode = compile(code);

        System.out.println("BF code:" + brainFuckCode);
        return BFMachine.execute(brainFuckCode, input, output);

    }

    /**
     * 编译： 解析token，翻译token
     *
     * @param code
     * @return
     */
    private static String compile(String code) {
        String[] tokens = parseToken(code);
        return convertToken(tokens);
    }

    /**
     * 转换token，成bf语法
     *
     * @param tokens
     * @return
     */
    private static String convertToken(String[] tokens) {
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

    /**
     * 解析token
     *
     * @param code
     * @return
     */
    private static String[] parseToken(String code) {

        List<String> tokens = new ArrayList<String>();
        for (int i = 0; i < code.length(); i++) {
            int beginIndex = i, endIndex = i;
            while (endIndex < code.length() && Character.isDigit(code.charAt(endIndex))) {
                endIndex++;
            }

            if (beginIndex == endIndex) {
                char instruction = code.charAt(i);
                tokens.add(instruction + "");
            } else {
                tokens.add(code.substring(beginIndex, endIndex));
                i += (endIndex - beginIndex - 1);
            }
        }

        return tokens.toArray(new String[0]);
    }
}
