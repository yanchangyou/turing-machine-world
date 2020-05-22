package org.world.machine.bfpp.v0.v1;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.world.machine.bf.v0.vx.BFMachine;

import java.util.List;

public class BFPlusPlusMachineTest {

    @BeforeTest
    public void before() {
        BFMachine.disableLog();
    }

    @Test
    public void test() {

        String code = "+65W";

        System.out.println("BF++ code:" + code);
        String result = BFPlusPlusMachine.execute(code);

        Assert.assertEquals(result, "A");

    }

    @Test
    public void test1() {

        String code = "+65>+66>+67";

        System.out.println("BF++ code:" + code);
        String result = BFPlusPlusMachine.execute(code);

        Assert.assertEquals(result, "ABC");

    }

    @Test
    public void test2() {

        String code = "(+)65>(+)66>(+)67";

        System.out.println("BF++ code:" + code);
        String result = BFPlusPlusMachine.execute(code);

        Assert.assertEquals(result, "ABC");

    }

    @Test
    public void hello() {

        String code = convertToBFPlusPlusCode("Hello World!\r\n");

        System.out.println("BF++ code:" + code);

        String result = BFPlusPlusMachine.execute(code);
        Assert.assertEquals(result, "Hello World!\r\n");

    }

    @Test
    public void testA_Z() {

        StringBuilder code = new StringBuilder();

        for (int i = 'A'; i <= 'Z'; i++) {
            code.append("+").append(i);
            code.append(">");
        }

        System.out.println("code:" + code);
        String result = BFPlusPlusMachine.execute(code.toString());

        System.out.println();

        System.out.println("result:" + result);
        Assert.assertEquals(result, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    }

    /**
     * 把普通字符串转化为 BF++的输出代码
     *
     * @param text
     * @return
     */
    String convertToBFPlusPlusCode(String text) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append("+").append((short) text.charAt(i)).append(".>");
        }
        return builder.toString();
    }

    @Test
    public void testParseTokensToTree() {

        String code = "(,.)12";
        List<Object> tokens = BFPlusPlusMachine.parseTokens(code);
        System.out.println(tokens);
        Assert.assertEquals(tokens.size(), 5);
        List<Object> tree = BFPlusPlusMachine.parseTokensToTree(tokens);
        System.out.println(tree);
        Assert.assertEquals(tree.size(), 2);
    }

    @Test
    public void testParseTokensToTreeMore() {

        String[] code = new String[] { "(,.)12", "+1", "", "+-+-", "(())", "((+))", "((++--))", "(+(+-)-)" };
        int[] tokenSize = new int[] { 5, 2, 0, 4, 4, 5, 8, 8 };
        int[] treeSize = new int[] { 2, 2, 0, 4, 0, 1, 1, 1 };
        for (int i = 0; i < code.length; i++) {
            List<Object> tokens = BFPlusPlusMachine.parseTokens(code[i]);
            System.out.println(tokens);
            Assert.assertEquals(tokens.size(), tokenSize[i]);
            List<Object> tree = BFPlusPlusMachine.parseTokensToTree(tokens);
            System.out.println(tree);
            Assert.assertEquals(tree.size(), treeSize[i]);
        }
    }

    @Test
    public void testCleanComment() {

        String code = "#commnet\r";
        String expected = "";
        String result = BFPlusPlusMachine.cleanComment(code);
        System.out.println(result);
        Assert.assertEquals(result, expected);

    }

    @Test
    public void testCleanCommentMore() {

        String[] code = new String[] { "#commnet\r", "#commnet\n++", "#commnet\n+\r\n+", "#commnet\n+\r\n+#comment" };
        String[] expected = new String[] { "", "++", "++", "++" };
        for (int i = 0; i < code.length; i++) {
            String result = BFPlusPlusMachine.cleanComment(code[i]);
            System.out.println(result);
            Assert.assertEquals(result, expected[i]);
        }
    }

    @Test
    public void testDecompile() {
        String code = "++";
        String expected = "+2";
        String result = BFPlusPlusMachine.decompile(code);
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testDecompileHelloWorld() {
        String code = ">+++++++++[<++++++++>-]<.>+++++++[<++++>-]<+.+++++++..+++.[-]>++++++++[<++++>-]<.>+++++++++++[<+++++>-]<.>++++++++[<+++>-]<.+++.------.--------.[-]>++++++++[<++++>-]<+.[-]++++++++++.";
        String expected = ">+9[<+8>-]<.>+7[<+4>-]<+.+7.2+3.[-]>+8[<+4>-]<.>+11[<+5>-]<.>+8[<+3>-]<.+3.-6.-8.[-]>+8[<+4>-]<+.[-]+10.";
        String result = BFPlusPlusMachine.decompile(code);
        System.out.println(result);
        Assert.assertEquals(result, expected);
        String oldCode = BFPlusPlusMachine.compile(result);
        System.out.println(oldCode);
        Assert.assertEquals(oldCode, code);
    }
}