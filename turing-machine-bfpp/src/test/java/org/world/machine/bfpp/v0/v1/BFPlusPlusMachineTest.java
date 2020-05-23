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

    @Test
    public void testHello() {
        String helloWorld = "Hello World!";
        System.out.print("+72.");
        for (int i = 0; i < helloWorld.length() - 1; i++) {
            int diff = helloWorld.charAt(i + 1) - helloWorld.charAt(i);
            System.out.print((diff > 0 ? "+" : "") + (diff == 0 ? "" : diff) + ".");
        }
    }

//    @Test
    public void testDecompileQuine() {
        String code = "->+>+++>>+>++>+>+++>>+>++>>>+>+>+>++>+>>>>+++>+>>++>+>+++>>++>++>>+>>+>++>++>+>>>>+++>+>>>>++>++>>>>+>>++>+>+++>>>++>>++++++>>+>>++>+>>>>+++>>+++++>>+>+++>>>++>>++>>+>>++>+>+++>>>++>>+++++++++++++>>+>>++>+>+++>+>+++>>>++>>++++>>+>>++>+>>>>+++>>+++++>>>>++>>>>+>+>++>>+++>+>>>>+++>+>>>>+++>+>>>>+++>>++>++>+>+++>+>++>++>>>>>>++>+>+++>>>>>+++>>>++>+>+++>+>+>++>>>>>>++>>>+>>>++>+>>>>+++>+>>>+>>++>+>++++++++++++++++++>>>>+>+>>>+>>++>+>+++>>>++>>++++++++>>+>>++>+>>>>+++>>++++++>>>+>++>>+++>+>+>++>+>+++>>>>>+++>>>+>+>>++>+>+++>>>++>>++++++++>>+>>++>+>>>>+++>>++++>>+>+++>>>>>>++>+>+++>>+>++>>>>+>+>++>+>>>>+++>>+++>>>+[[->>+<<]<+]+++++[->+++++++++<]>.[+]>>[<<+++++++[->+++++++++<]>-.------------------->-[-<.<+>>]<[+]<+>>>]<<<[-[-[-[>>+<++++++[->+++++<]]>++++++++++++++<]>+++<]++++++[->+++++++<]>+<<<-[->>>++<<<]>[->>.<<]<<]";
        String result = BFPlusPlusMachine.decompile(code);
        System.out.println(result);
    }

//    @Test
    public void testBFFactor() {
        String code = ""
                + "* factor an arbitrarily large positive integer\n "
                + "*\n"
                + "* Copyright (C) 1999 by Brian Raiter\n"
                + "* under the GNU General Public License\n"
                + "\n"
                + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>-\n"
                + "\n"
                + "*\n"
                + "* read in the number\n"
                + "*\n"
                + "\n"
                + "<<<<<<<<<+\n"
                + "[-[>>>>>>>>>>][-]<<<<<<<<<<[[->>>>>>>>>>+<<<<<<<<<<]<<<<<<<<<<]\n"
                + "  >>>>>>>>>>,----------]\n"
                + ">>>>>>>>>>[------------------------------------->>>>>>>>>->]\n"
                + "<[+>[>>>>>>>>>+>]<-<<<<<<<<<<]-\n"
                + "\n"
                + "*\n"
                + "* display the number and initialize the loop variable to two\n"
                + "*\n"
                + "\n"
                + "[>++++++++++++++++++++++++++++++++++++++++++++++++.\n"
                + "  ------------------------------------------------<<<<<<<<<<<]\n"
                + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.\n"
                + "--------------------------.[-]\n"
                + ">>>>>>>>>>>>++<<<<+\n"
                + "\n"
                + "*\n"
                + "* the main loop\n"
                + "*\n"
                + "\n"
                + "[ [-]>>\n"
                + "\n"
                + "  *\n"
                + "  * make copies of the number and the loop variable\n"
                + "  *\n"
                + "\n"
                + "  [>>>>[-]>[-]>[-]>[-]\n"
                + "    >[-]>[-]\n"
                + "    <<<<<<<[->>>+>+<<<<]>>>>>>>>]\n"
                + "  <<<<<<<<<<[>>>>>>[-<<<<+>>>>]<<<<<<<<<<<<<<<<]>>>>>>>>>>\n"
                + "  [>[->>>+>>+<<<<<]>>>>>>>>>]\n"
                + "  <<<<<<<<<<[>>>>>>[-<<<<<+>>>>>]<<<<<<<<<<<<<<<<]>>>>>>>>>>\n"
                + "\n"
                + "  *\n"
                + "  * divide the number by the loop variable\n"
                + "  *\n"
                + "\n"
                + "  [>>>[-]>>>[-]>[-]>>>]                                  initialize\n"
                + "  <<<<<<<<<<[<<<<<<<<<<]\n"
                + "  >>>>>>>>>[-]>>>>>>>+<<<<<<<<[+]+\n"
                + "  [ ->>                               double divisor until above dividend\n"
                + "    [>>>>>>[->++<]>>>>]<<<<<<<<<<\n"
                + "    [>>>>>>>>[-]>[-]\n"
                + "       <<<<[->>>++<<<]<<<<<<<<<<<<<<<]>>>>>>>>>>\n"
                + "    [>>>>>>>>[->+<[->+<[->+<[->+<[->+<[->+<[->+<[->+<[->+<\n"
                + "            [->--------->>>>>>>>>+<<<<<<<<<<[->+<]]]]]]]]]]]>>]\n"
                + "    <<<<<<<<<<[>>>>>>>>>[-<+<<<+>>>>]<<<<<<<<<<<<<<<<<<<]>>>>>>>>>>\n"
                + "    [>>>>>>>[-<+>[-<+>[-<+>[-<+>[-<+>[-<+>[-<+>[-<+>[-<+>\n"
                + "            [-<--------->>>>>>>>>>>+<<<<<<<<<<[-<+>]]]]]]]]]]]>>>]\n"
                + "    <<<<<<<<<<\n"
                + "    [>>>>[->>>+>>+<<<<<]<<<<<<<<<<<<<<]\n"
                + "    >>>>>>>>>>[>>>>>>>[-<<<+>>>]>>>]<<<<<<<<<<\n"
                + "    [>>>>>>>>[->-<]>\n"
                + "      [<<<<<<<<<[<[-]>>>>>>>>>>[-<<<<<<<<<<+>>>>>>>>>>]<<<<<<<<<<<<<<<<<<<]\n"
                + "        >>>>>>>>>>>>>>>>>>>]\n"
                + "      <<<<<<<<<<<<<<<<<<<]\n"
                + "    >>>>>>>>>[+[+[+[+[+[+[+[+[+[+[[-]<+>]]]]]]]]]]]<\n"
                + "  ]\n"
                + "  >>>>>>>>\n"
                + "  [                                   subtract divisor from dividend\n"
                + "    <<<<<<\n"
                + "    [>>>>>>>>[-]>[-]<<<<<[->>>+>+<<<<]>>>>>>]<<<<<<<<<<\n"
                + "    [>>>>>>>>[-<<<<+>>>>]<<<[->>>+>+<<<<]<<<<<<<<<<<<<<<]>>>>>>>>>>\n"
                + "    [>>>>>>>>>[-<<<<+>>>>]>]<<<<<<<<<<\n"
                + "    [>>>>>>>>[-<->]<<<<<<<<<<<<<<<<<<]>>>>>>>>>>\n"
                + "    [>>>>>>>[->+<[->+<[->+<[->+<[->+<[->+<[->+<[->+<[->+<[->+<\n"
                + "            [++++++++++[+>-<]>>>>>>>>>>-<<<<<<<<<<]]]]]]]]]]]>>>]\n"
                + "    >>>>>>>+\n"
                + "    [                                 if difference is nonnegative then\n"
                + "      [-]<<<<<<<<<<<<<<<<<            replace dividend and increment quotient\n"
                + "      [>>>>[-]>>>>[-<<<<+>>>>]<<[->>+<<]<<<<<<<<<<<<<<<<]>>>>>>>>>>\n"
                + "      [>>>>>>>>[->+<<<+>>]>>]<<<<<<<<<<\n"
                + "      [>>>[->>>>>>+<<<<<<]<<<<<<<<<<<<<]>>>>>>>>>>\n"
                + "      [>>>>>>>>>[-<<<<<<+>>>>>>[-<<<<<<+>>>>>>\n"
                + "                [-<<<<<<+>>>>>>[-<<<<<<+>>>>>>\n"
                + "                [-<<<<<<+>>>>>>[-<<<<<<+>>>>>>\n"
                + "                [-<<<<<<+>>>>>>[-<<<<<<+>>>>>>\n"
                + "                [-<<<<<<+>>>>>>[-<<<<<<--------->>>>>>>>>>>>>>>>+<<<<<<<<<<\n"
                + "                [-<<<<<<+>>>>>>]]]]]]]]]]]>]\n"
                + "      >>>>>>>\n"
                + "    ]                                 halve divisor and loop until zero\n"
                + "    <<<<<<<<<<<<<<<<<[<<<<<<<<<<]>>>>>>>>>>\n"
                + "    [>>>>>>>>[-]<<[->+<]<[->>>+<<<]>>>>>]<<<<<<<<<<\n"
                + "    [+>>>>>>>[-<<<<<<<+>>>>>>>[-<<<<<<<->>>>>>+>\n"
                + "             [-<<<<<<<+>>>>>>>[-<<<<<<<->>>>>>+>\n"
                + "             [-<<<<<<<+>>>>>>>[-<<<<<<<->>>>>>+>\n"
                + "             [-<<<<<<<+>>>>>>>[-<<<<<<<->>>>>>+>\n"
                + "             [-<<<<<<<+>>>>>>>]]]]]]]]]<<<<<<<\n"
                + "             [->>>>>>>+<<<<<<<]-<<<<<<<<<<]\n"
                + "    >>>>>>>\n"
                + "    [-<<<<<<<<<<<+>>>>>>>>>>>]\n"
                + "      >>>[>>>>>>>[-<<<<<<<<<<<+++++>>>>>>>>>>>]>>>]<<<<<<<<<<\n"
                + "    [+>>>>>>>>[-<<<<<<<<+>>>>>>>>[-<<<<<<<<->>>>>+>>>\n"
                + "              [-<<<<<<<<+>>>>>>>>[-<<<<<<<<->>>>>+>>>\n"
                + "              [-<<<<<<<<+>>>>>>>>[-<<<<<<<<->>>>>+>>>\n"
                + "              [-<<<<<<<<+>>>>>>>>[-<<<<<<<<->>>>>+>>>\n"
                + "              [-<<<<<<<<+>>>>>>>>]]]]]]]]]<<<<<<<<\n"
                + "              [->>>>>>>>+<<<<<<<<]-<<<<<<<<<<]\n"
                + "    >>>>>>>>[-<<<<<<<<<<<<<+>>>>>>>>>>>>>]>>\n"
                + "    [>>>>>>>>[-<<<<<<<<<<<<<+++++>>>>>>>>>>>>>]>>]<<<<<<<<<<\n"
                + "    [<<<<<<<<<<]>>>>>>>>>>\n"
                + "    >>>>>>\n"
                + "  ]\n"
                + "  <<<<<<\n"
                + "\n"
                + "  *\n"
                + "  * make copies of the loop variable and the quotient\n"
                + "  *\n"
                + "\n"
                + "  [>>>[->>>>+>+<<<<<]>>>>>>>]\n"
                + "  <<<<<<<<<<\n"
                + "  [>>>>>>>[-<<<<+>>>>]<<<<<[->>>>>+>>+<<<<<<<]<<<<<<<<<<<<]\n"
                + "  >>>>>>>>>>[>>>>>>>[-<<<<<+>>>>>]>>>]<<<<<<<<<<\n"
                + "\n"
                + "  *\n"
                + "  * break out of the loop if the quotient is larger than the loop variable\n"
                + "  *\n"
                + "\n"
                + "  [>>>>>>>>>[-<->]<\n"
                + "    [<<<<<<<<\n"
                + "      [<<[-]>>>>>>>>>>[-<<<<<<<<<<+>>>>>>>>>>]<<<<<<<<<<<<<<<<<<]\n"
                + "    >>>>>>>>>>>>>>>>>>]<<<<<<<<<<<<<<<<<<]\n"
                + "  >>>>>>>>[>-<[+[+[+[+[+[+[+[+[+[[-]>+<]]]]]]]]]]]>+\n"
                + "\n"
                + "  [ [-]\n"
                + "\n"
                + "    *\n"
                + "    * partially increment the loop variable\n"
                + "    *\n"
                + "\n"
                + "    <[-]+>>>>+>>>>>>>>[>>>>>>>>>>]<<<<<<<<<<\n"
                + "\n"
                + "    *\n"
                + "    * examine the remainder for nonzero digits\n"
                + "    *\n"
                + "\n"
                + "    [<<<<<<[<<<<[<<<<<<<<<<]>>>>+<<<<<<<<<<]<<<<]\n"
                + "    >>>>>>>>>>>>>>>>>>>>[>>>>>>>>>>]<<<<<<<<<<[<<<<<<<<<<]\n"
                + "    >>>>-\n"
                + "\n"
                + "    [ [+]\n"
                + "\n"
                + "      *\n"
                + "      * decrement the loop variable and replace the number with the quotient\n"
                + "      *\n"
                + "\n"
                + "      >>>>>>>>-<<[>[-]>>[-<<+>>]>>>>>>>]<<<<<<<<<<\n"
                + "\n"
                + "      *\n"
                + "      * display the loop variable\n"
                + "      *\n"
                + "\n"
                + "      [+>>[>>>>>>>>+>>]<<-<<<<<<<<<<]-\n"
                + "      [>>++++++++++++++++++++++++++++++++++++++++++++++++.\n"
                + "         ------------------------------------------------<<<<<<<<<<<<]\n"
                + "      ++++++++++++++++++++++++++++++++.[-]>>>>\n"
                + "\n"
                + "    ]\n"
                + "\n"
                + "    *\n"
                + "    * normalize the loop variable\n"
                + "    *\n"
                + "\n"
                + "    >>>>>>\n"
                + "    [>>[->>>>>+<<<<<[->>>>>+<<<<<\n"
                + "       [->>>>>+<<<<<[->>>>>+<<<<<\n"
                + "       [->>>>>+<<<<<[->>>>>+<<<<<\n"
                + "       [->>>>>+<<<<<[->>>>>+<<<<<\n"
                + "       [->>>>>+<<<<<[->>>>>--------->>>>>+<<<<<<<<<<\n"
                + "       [->>>>>+<<<<<]]]]]]]]]]]>>>>>>>>]\n"
                + "    <<<<<<<<<<[>>>>>>>[-<<<<<+>>>>>]<<<<<<<<<<<<<<<<<]\n"
                + "    >>>>>>>>>\n"
                + "\n"
                + "  ]<\n"
                + "\n"
                + "]>>\n"
                + "\n"
                + "*\n"
                + "* display the number and end\n"
                + "*\n"
                + "\n"
                + "[>>>>>>>>>>]<<<<<<<<<<[+>[>>>>>>>>>+>]<-<<<<<<<<<<]-\n"
                + "[>++++++++++++++++++++++++++++++++++++++++++++++++.<<<<<<<<<<<]\n"
                + "++++++++++.";

        String result = BFPlusPlusMachine.execute(code, "9");
        System.out.println(result);

    }
}