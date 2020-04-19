package org.lingxivm.v0.vx.vm.rule;

import java.util.Objects;

/**
 * 开始方向
 */
public class LingXiVmRuleItemFrom {

    public LingXiVmRuleItemFrom(String status, String cellValue) {
        this.status = status;
        this.cellValue = cellValue;
    }

    //虚拟机状态
    String status;

    //带子当前单元格的值
    String cellValue;

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
        if (!(o instanceof LingXiVmRuleItemFrom))
            return false;
        LingXiVmRuleItemFrom that = (LingXiVmRuleItemFrom) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(cellValue, that.cellValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, cellValue);
    }
}
