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
        return getResetRule(null, null, value);
    }

    /**
     * 获取单元格重置规则
     *
     * @param index
     * @param value
     * @return
     */
    public static String getResetRule(Integer index, String value) {
        return getResetRule(index, index, value);
    }

    /**
     * 获取单元格范围重置的规则
     *
     * @param value
     * @return
     */
    public static String getResetRule(Integer beginIndex, Integer endIndex, String value) {
        String rule = ((beginIndex == null) ? "" : ("[" + beginIndex + "," + endIndex + "]\r\n"))
                + "  1,0->1,__RESET_VALUE__,+;\r\n"
                + "  1,1->1,__RESET_VALUE__,+;\r\n";
        return rule.replaceAll("__RESET_VALUE__", value);
    }

}
