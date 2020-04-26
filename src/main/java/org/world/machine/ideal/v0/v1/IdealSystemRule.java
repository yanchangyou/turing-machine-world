package org.world.machine.ideal.v0.v1;

/**
 * 理想系统规则： 内外时间状态映射规则
 */
public class IdealSystemRule {

    public IdealSystemRule(String ruleCode) {

    }

    /**
     * @param status
     * @return
     */
    public int[][] convert(String[] status) {

        //        String[] newStatus = new String[oldStatus.length];

        status[0] = "1";
        status[1] = "1";

        int[][] indexDiff = new int[2][];
        return indexDiff;
    }

    public boolean isStopped(String innerStatus, String outerStatus) {
        return false;
    }
}
