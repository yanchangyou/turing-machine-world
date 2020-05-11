package org.world.machine.turing.v0.v1;

/**
 * 图灵机的理论模型
 * 一个无限长的存储带，带子有一个个连续的存储格子组成，每个格子可以存储一个数字或符号
 * 一个读写头，读写头可以在存储带上左右移动，并可以读、修改存储格上的数字或符号
 * 内部状态存储器，该存储器可以记录图灵机的当前状态，并且有一种特殊状态为停机状态
 * 控制程序指令，指令可以根据当前状态以及当前读写头所指的格子上的符号来确定读写头下一步的动作（左移还是右移），并改变状态存储器的值，令机器进入一个新的状态或保持状态不变。
 * <p>
 * 作者：jerry的技术与思维
 * 链接：https://www.jianshu.com/p/c07d83c4f3a1
 * 来源：简书
 */
public interface TuringMachine {

    /**
     * 获取带子
     *
     * @return
     */
    String[] getTape();

    /**
     * 读带子当前单元格的值
     *
     * @return
     */
    String readTapeCell();

    /**
     * 写当前单元格带子的值
     *
     * @param value
     */
    void writeTapeCell(String value);

    /**
     * 获取图灵机当前状态
     *
     * @return
     */
    String getMachineStatus();

    /**
     * 是否停机
     *
     * @return
     */
    Boolean isMachineStopped();

    /**
     * 执行程序代码
     */
    void execute(String programCode);

}
