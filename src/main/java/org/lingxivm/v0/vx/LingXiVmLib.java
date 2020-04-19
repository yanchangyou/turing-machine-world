package org.lingxivm.v0.vx;

/**
 * 开发库
 */
public class LingXiVmLib {

    /**
     * 获取重置的规则
     *
     * @param value
     * @return
     */
    public static String getResetRule(String value) {
        String rule = ""
                + "0,0->1,__RESET_VALUE__,+;\r\n"
                + "0,1->1,__RESET_VALUE__,+;\r\n"
                + "1,0->1,__RESET_VALUE__,+;\r\n"
                + "1,1->1,__RESET_VALUE__,+;\r\n"
                + "21,0->0,__RESET_VALUE__,;\r\n"
                + "21,1->0,__RESET_VALUE__,;\r\n";
        return rule.replaceAll("__RESET_VALUE__", value);
    }

    /**
     * 获取重置的规则
     *
     * @param value
     * @return
     */
    public static String getResetRule(int beginIndex, int endIndex, String value) {
        String rule = "[" + beginIndex + "," + endIndex + "]"
                + "0,0->1,__RESET_VALUE__,+;\r\n"
                + "0,1->1,__RESET_VALUE__,+;\r\n"
                + "1,0->1,__RESET_VALUE__,+;\r\n"
                + "1,1->1,__RESET_VALUE__,+;\r\n"
                + "21,0->0,__RESET_VALUE__,;\r\n"
                + "21,1->0,__RESET_VALUE__,;\r\n";
        return rule.replaceAll("__RESET_VALUE__", value);
    }

}
