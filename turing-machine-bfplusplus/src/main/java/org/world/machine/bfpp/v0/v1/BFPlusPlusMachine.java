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
        List<Object> tokens = parseTokens(cleanComment(code));
        List<Object> tokensInTree = parseTokensToTree(tokens);
        return convertTokenToBfCode(tokensInTree);
    }

    /**
     * 清除注释
     *
     * @param code
     * @return
     */
    static String cleanComment(String code) {
        String[] lines = code.split("\\\r|\\\n");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            builder.append(lines[i].split("#")[0]);
        }
        return builder.toString();
    }

    /**
     * 转换token，成bf语法
     *
     * @param tokens
     * @return
     */
    static String convertTokenToBfCode(String[] tokens) {
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
     * 转换token，成bf语法
     *
     * @param tokens
     * @return
     */
    static String convertTokenToBfCode(List<Object> tokens) {
        StringBuilder builder = new StringBuilder();

        String lastCode = null;
        for (int i = 0; i < tokens.size(); i++) {
            String currentCode = "";
            Object token = tokens.get(i);
            if (token instanceof List) { //嵌套
                String subCode = convertTokenToBfCode((List) token);
                currentCode = subCode;
            } else if (token instanceof Number) { //重复
                int number = ((Integer) token) - 1;
                for (int j = 0; j < number; j++) {
                    builder.append(lastCode);
                }
            } else { //普通token
                currentCode = (String) token;
            }
            builder.append(currentCode);
            lastCode = currentCode;
        }

        return builder.toString();
    }

    /**
     * 解析成语法树
     *
     * @param tokens
     * @return
     */
    static List<Object> parseTokensToTree(List<Object> tokens) {
        List<Object> tokenList = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            Object token = tokens.get(i);
            if ("(".equals(token)) {
                int zero = -1;
                int endIndex = -1;
                for (int j = i + 1; j < tokens.size(); j++) {
                    Object innerToken = tokens.get(j);
                    if (")".equals(innerToken)) {
                        zero += 1;
                    } else if ("(".equals(innerToken)) {
                        zero += -1;
                    }
                    if (zero == 0) {
                        endIndex = j;
                        break;
                    }
                }
                if (endIndex == -1) {
                    throw new RuntimeException("语法错误：缺少右括号");
                }
                List subTokens = new ArrayList();
                for (int j = i + 1; j < endIndex; j++) {
                    subTokens.add(tokens.get(j));
                }
                List subList = parseTokensToTree(subTokens);
                if (subList.size() > 0) {
                    tokenList.add(subList);
                }
                i = endIndex;
            } else if (")".equals(token)) {
                throw new RuntimeException("语法错误：缺少左括号");
            } else {
                tokenList.add(token);
            }
        }
        return tokenList;
    }

    /**
     * 解析token
     *
     * @param code
     * @return
     */
    static List<Object> parseTokens(String code) {

        List<Object> tokens = new ArrayList<>();
        for (int i = 0; i < code.length(); i++) {
            int beginIndex = i, endIndex = i;
            while (endIndex < code.length() && Character.isDigit(code.charAt(endIndex))) {
                endIndex++;
            }

            if (beginIndex == endIndex) {
                char instruction = code.charAt(i);
                tokens.add(instruction + "");
            } else {
                tokens.add(Integer.parseInt(code.substring(beginIndex, endIndex)));
                i += (endIndex - beginIndex - 1);
            }
        }

        return tokens;
    }
}
