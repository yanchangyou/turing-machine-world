package org.lingxivm.v0.vx.vm.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * [begin,end]1,0->1,1,+;1,1->1,1,+;
 * [begin,end]1,0->1,1,+;1,1->1,1,+;
 */
public class LingXiVmRule {

    public LingXiVmRule() {
        ruleItems = new ArrayList();
    }

    //带子开始位置
    int tapeBeginIndex;

    //带子开始位置
    int tapeEndIndex;

    /**
     * 规则项
     */
    List<LingXiVmRuleItem> ruleItems;

    public int getTapeBeginIndex() {
        return tapeBeginIndex;
    }

    public void setTapeBeginIndex(int tapeBeginIndex) {
        this.tapeBeginIndex = tapeBeginIndex;
    }

    public int getTapeEndIndex() {
        return tapeEndIndex;
    }

    public void setTapeEndIndex(int tapeEndIndex) {
        this.tapeEndIndex = tapeEndIndex;
    }

    public void addRuleItem(LingXiVmRuleItem ruleItem) {
        this.ruleItems.add(ruleItem);
    }

    public LingXiVmRuleItemTo getRuleTo(LingXiVmRuleItemFrom from) {

        LingXiVmRuleItemTo result = null;
        for (LingXiVmRuleItem ruleItem : ruleItems) {

            if (ruleItem.from.equals(from)) {
                result = ruleItem.to;
                break;
            }
        }
        return result;
    }
}
