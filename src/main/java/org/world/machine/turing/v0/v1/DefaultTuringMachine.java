package org.world.machine.turing.v0.v1;

/**
 * 图灵机默认实现
 */
public class DefaultTuringMachine implements TuringMachine {

    String MACHINE_STOPPED_STATUS = "0";

    /**
     * 带子上面的单元格
     */
    String[] tapeCells;

    /**
     * 当前位置
     */
    int currentIndex;

    String machineStatus;

    public DefaultTuringMachine(String[] tapeCells) {
        this.tapeCells = tapeCells;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    @Override
    public String[] getTape() {
        return tapeCells;
    }

    @Override
    public String readTapeCell() {
        return tapeCells[currentIndex];
    }

    @Override
    public void writeTapeCell(String value) {
        tapeCells[currentIndex] = value;
    }

    @Override
    public String getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(String newMachineStatus) {
        this.machineStatus = newMachineStatus;
    }

    @Override
    public Boolean isMachineStopped() {
        return MACHINE_STOPPED_STATUS.equalsIgnoreCase(machineStatus);
    }

    @Override
    public void execute(String programCode) {

        while (true) {
            String machineStatus = getMachineStatus();
            String cellValue = readTapeCell();

            String result = executeProgramCode(programCode, machineStatus, cellValue);

            String[] resultParts = result.split("\\s*,\\s*");
            String newMachineStatus = resultParts[0];
            String newCellValue = resultParts[1];
            String forward = resultParts[2];

            setMachineStatus(newMachineStatus);
            writeTapeCell(newCellValue);

            doForward(forward);
        }
    }

    private void doForward(String forward) {

        if ("+".equalsIgnoreCase(forward)) {
            setCurrentIndex(getCurrentIndex() + 1);
        } else if ("-".equalsIgnoreCase(forward)) {
            setCurrentIndex(getCurrentIndex() - 1);
        }
    }

    private String executeProgramCode(String programCode, String machineStatus, String cellValue) {
        String result = null;

        result = "1,1,+";

        return result;
    }
}
