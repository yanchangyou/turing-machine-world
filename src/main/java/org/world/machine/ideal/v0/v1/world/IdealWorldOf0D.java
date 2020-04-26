package org.world.machine.ideal.v0.v1.world;

import org.world.machine.ideal.v0.v1.IdealWorld;

/**
 * 零维的世界:语义定义不一致，没有位置index的概念，强加进去不适合，不加有不一致，是个问题
 */
public class IdealWorldOf0D extends IdealWorld {

    /**
     * 单元
     */
    String status;

    public IdealWorldOf0D(String initValue) {
        status = initValue;
    }

    @Override
    public String getStatusAt(int[] index) {
        return status;
    }

    @Override
    public void setStatusAt(int[] index, String status) {
        this.status = status;
    }

    @Override
    public int[] getInitIndex() {
        return new int[0];
    }

    @Override
    public String toString() {
        return "IdealWorldOf0D{" +
                "status='" + status + '\'' +
                '}';
    }
}
