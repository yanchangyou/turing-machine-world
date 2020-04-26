package org.world.machine.ideal.v0.v1;

/**
 * 理想世界:
 * 1，N维坐标系
 * 2，每个坐标位置有某种状态
 * 3，状态可读可写
 */
public abstract class IdealWorld {

    /**
     * 获取指定坐标的状态
     *
     * @param index
     * @return
     */
    public abstract String getStatusAt(int[] index);

    /**
     * 设置指定坐标的状态
     *
     * @param index
     * @param status
     */
    public abstract void setStatusAt(int[] index, String status);

    public abstract int[] getInitIndex();

}
