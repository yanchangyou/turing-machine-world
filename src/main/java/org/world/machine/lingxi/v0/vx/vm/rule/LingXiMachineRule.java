package org.world.machine.lingxi.v0.vx.vm.rule;

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
    Integer tapeBeginIndex;

    //带子开始位置
    Integer tapeEndIndex;

    /**
     * 规则项
     */
    List<LingXiVmRuleItem> ruleItems;

    public Integer getTapeBeginIndex() {
        return tapeBeginIndex;
    }

    public void setTapeBeginIndex(Integer tapeBeginIndex) {
        this.tapeBeginIndex = tapeBeginIndex;
    }

    public Integer getTapeEndIndex() {
        return tapeEndIndex;
    }

    public void setTapeEndIndex(Integer tapeEndIndex) {
        this.tapeEndIndex = tapeEndIndex;
    }

    public void addRuleItem(LingXiVmRuleItem ruleItem) {
        this.ruleItems.add(ruleItem);
    }

    public LingXiVmRuleItemTo getRuleTo(LingXiVmRuleItemFrom from) {

        LingXiVmRuleItemTo result = null;
        for (LingXiVmRuleItem ruleItem : ruleItems) {

            if (ruleItem.getFrom().equals(from)) {
                result = ruleItem.getTo();
                break;
            }
        }
        return result;
    }
}
