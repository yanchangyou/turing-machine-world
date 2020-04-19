package org.lingxivm.v0.vx;

import org.lingxivm.v0.vx.vm.rule.LingXiVmRule;
import org.lingxivm.v0.vx.vm.rule.LingXiVmRuleItemFrom;
import org.lingxivm.v0.vx.vm.rule.LingXiVmRuleItemTo;

/**
 * 灵犀图灵机
 */
public class LingXiVm {

    /**
     * 图灵机运行
     * 全字符串入参
     *
     * @param tape 逗号分割
     * @param rule oldStatus,oldValue->newStatus,newValue,forward\r\n next
     */
    public static String run(String tape, String rule) {

        String[] cells = tape.split(",", -1);


        LingXiVmRule vmRule = LingXiVmUtil.parseRule(rule);
        run(cells, vmRule);

        return LingXiVmUtil.cellsToString(cells);
    }

    /**
     * 图灵机运行
     *
     * @param cells
     * @param rule
     */
    private static void run(String[] cells, LingXiVmRule rule) {

        //内部状态
        String status = "1";// 状态：0：停止，>0:运行，<0：异常状态；1：运行，-101：超过最小边界，-102：超过最大边界

        int index = 0;

        System.out.println("LingXiVM begin run:");
        while (true) {
            String value = cells[index];

            LingXiVmRuleItemTo to = rule.getRuleTo(new LingXiVmRuleItemFrom(status, value));

            status = to.getStatus();

            cells[index] = to.getCellValue();

            //虚拟机内部状态
            if ("0".equals(status)) {
                System.out.println("LingXiVM stop!");
                break;
            }
            if (status.startsWith("-")) {
                System.out.println("LingXiVM error!");
                break;
            }

            //移动方向：向前、向后、不动
            if ("+".equalsIgnoreCase(to.getForward())) {//向前，否则向后
                index++;
            } else if ("-".equalsIgnoreCase(to.getForward())) {
                index--;
            }

            //边界状态设置
            if (index == 0) {// 超过最小边界
                status = "11";
            }
            if (index == cells.length - 1) { //超过最大边界
                status = "21";
            }

            // 边界异常
            if (index < 0 || index >= cells.length) {
                System.out.println("ERROR : index out off bounder");
                break;
            }
        }
    }
}


