package org.world.machine.lingxi.v0.v5.rule;

import java.util.Objects;

/**
 * 开始方向
 */
public class LingXiMachineRuleItemFrom {

    public LingXiMachineRuleItemFrom(String status, String cellValue) {
        setStatus(status);
        setCellValue(cellValue);
    }

    //虚拟机状态
    private String status;

    //带子当前单元格的值
    private String cellValue;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LingXiMachineRuleItemFrom))
            return false;
        LingXiMachineRuleItemFrom that = (LingXiMachineRuleItemFrom) o;
        return Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getCellValue(), that.getCellValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, cellValue);
    }
}
