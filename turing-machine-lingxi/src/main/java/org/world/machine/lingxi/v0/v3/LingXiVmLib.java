package org.world.machine.lingxi.v0.v3;

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
                + "0,0->1,__RESET_VALUE__,+\r\n"
                + "0,1->1,__RESET_VALUE__,+\r\n"
                + "1,0->1,__RESET_VALUE__,+\r\n"
                + "1,1->1,__RESET_VALUE__,+\r\n"
                + "21,0->0,__RESET_VALUE__,\r\n"
                + "21,1->0,__RESET_VALUE__,\r\n";
        return rule.replaceAll("__RESET_VALUE__", value);
    }

}
