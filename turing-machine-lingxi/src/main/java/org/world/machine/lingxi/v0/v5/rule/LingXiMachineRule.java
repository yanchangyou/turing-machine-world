package org.world.machine.lingxi.v0.v5.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * [begin,end]1,0->1,1,+;1,1->1,1,+;
 * [begin,end]1,0->1,1,+;1,1->1,1,+;
 */
public class LingXiMachineRule {

    public LingXiMachineRule() {
        ruleItems = new ArrayList();
    }

    //带子开始位置
    Integer tapeBeginIndex;

    //带子开始位置
    Integer tapeEndIndex;

    /**
     * 规则项
     */
    List<LingXiMachineRuleItem> ruleItems;

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

    public void addRuleItem(LingXiMachineRuleItem ruleItem) {
        this.ruleItems.add(ruleItem);
    }

    public LingXiVmRuleItemTo getRuleTo(LingXiMachineRuleItemFrom from) {

        LingXiVmRuleItemTo result = null;
        for (LingXiMachineRuleItem ruleItem : ruleItems) {

            if (ruleItem.getFrom().equals(from)) {
                result = ruleItem.getTo();
                break;
            }
        }
        return result;
    }
}
