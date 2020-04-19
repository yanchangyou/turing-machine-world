package org.lingxivm.v0.vx;

import org.lingxivm.v0.vx.vm.rule.LingXiVmRule;
import org.lingxivm.v0.vx.vm.rule.LingXiVmRuleItem;
import org.lingxivm.v0.vx.vm.rule.LingXiVmRuleItemFrom;
import org.lingxivm.v0.vx.vm.rule.LingXiVmRuleItemTo;

import java.util.HashMap;
import java.util.Map;

/**
 * 灵犀图灵机 工具类
 */
public class LingXiVmUtil {

    /**
     * 初始化tape的值
     *
     * @param tape
     * @param initValue
     */
    static void initCells(String[] tape, String initValue) {
        for (int i = 0; i < tape.length; i++) {
            tape[i] = initValue;
        }
    }

    /**
     * 初始化tape的值
     *
     * @param tape
     * @param initValue
     */
    static void initTape(String[] tape, String initValue) {
        for (int i = 0; i < tape.length; i++) {
            tape[i] = initValue;
        }
    }

    static String cellsToString(String[] cells) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < cells.length; i++) {
            if (i != 0) {
                buffer.append(",");
            }
            buffer.append(cells[i]);
        }
        return buffer.toString();
    }

    /**
     * 解析规则： [begin,end]itemFrom->itemTo;itemFrom->itemTo;
     *
     * @param rule
     * @return
     */
    public static LingXiVmRule parseRule(String rule) {

        LingXiVmRule vmRule = new LingXiVmRule();

        int beginBeginIndex = rule.indexOf('[');
        int endEndIndex = rule.indexOf(']');

        if (beginBeginIndex > -1 && endEndIndex > -1) {
            String[] beginEnd = rule.substring(beginBeginIndex + 1, endEndIndex - 1).split("\\s*,\\s*");

            int ruleBeginIndex = Integer.valueOf(beginEnd[0]);
            int ruleEndIndex = Integer.valueOf(beginEnd[1]);

            vmRule.setTapeBeginIndex(ruleBeginIndex);
            vmRule.setTapeEndIndex(ruleEndIndex);
        }

        String[] rules = rule.substring(endEndIndex + 1).split("\\s*;\\s*");

        Map<String, String> map = new HashMap();
        for (int i = 0; i < rules.length; i++) {
            String[] ruleItem = rules[i].split("\\s*(,|(->))\\s*", -1);
            LingXiVmRuleItem vmRuleItem = new LingXiVmRuleItem();
            vmRuleItem.setFrom(new LingXiVmRuleItemFrom(ruleItem[0], ruleItem[1]));
            vmRuleItem.setTo(new LingXiVmRuleItemTo(ruleItem[2], ruleItem[3], ruleItem[4]));
            vmRule.addRuleItem(vmRuleItem);
        }

        return vmRule;
    }
}


