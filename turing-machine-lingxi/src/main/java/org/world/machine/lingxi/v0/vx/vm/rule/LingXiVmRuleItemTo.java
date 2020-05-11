package org.world.machine.lingxi.v0.vx.vm.rule;

/**
 * 结束方向
 */
public class LingXiVmRuleItemTo {

    public LingXiVmRuleItemTo(String status, String cellValue, String forward) {
        setStatus(status);
        setCellValue(cellValue);
        setForward(forward);
    }

    //虚拟机状态
    String status;

    //带子当前单元格的值
    String cellValue;

    /**
     * 移动方向，+:向前，-:向后
     */
    String forward;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }
}
