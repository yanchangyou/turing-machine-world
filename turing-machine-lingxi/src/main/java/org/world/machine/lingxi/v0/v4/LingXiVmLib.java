package org.world.machine.lingxi.v0.v4;

/**
 * 开发库
 */
public class LingXiVmLib {

    /**
     * 初始化定长带子
     *
     * @param length
     * @return
     */
    public static String initTape(int length) {
        return initTape(length, "0");
    }

    /**
     * 初始化定长带子
     *
     * @param length
     * @return
     */
    public static String initTape(int length, String initValue) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                buf.append(",");
            }
            buf.append(initValue);
        }
        return buf.toString();
    }

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
        String rule = ((beginIndex == null) ?
                "" :
                ("[" + beginIndex + ((beginIndex == endIndex) ? "" : "," + endIndex) + "]\r\n"))
                + "  1,0->1,__RESET_VALUE__,+;\r\n"
                + "  1,1->1,__RESET_VALUE__,+;\r\n";
        return rule.replaceAll("__RESET_VALUE__", value);
    }

}
