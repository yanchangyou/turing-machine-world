package org.world.machine.ideal.v0.v1.world;

import org.world.machine.ideal.v0.v1.IdealWorld;

import java.util.Arrays;

/**
 * 一维的世界
 */
public class IdealWorldOf1D extends IdealWorld {

    /**
     * 单元
     */
    String[] status;

    public IdealWorldOf1D(int size, String initValue) {
        status = new String[size];
        for (int i = 0; i < status.length; i++) {
            status[i] = initValue;
        }
    }

    @Override
    public String getStatusAt(int[] index) {
        return status[index[0]];
    }

    @Override
    public void setStatusAt(int[] index, String status) {
        this.status[index[0]] = status;
    }

    @Override
    public int[] getInitIndex() {
        return new int[1];
    }

    @Override
    public String toString() {
        return "IdealWorldOf1D{" +
                "status=" + Arrays.toString(status) +
                '}';
    }
}
