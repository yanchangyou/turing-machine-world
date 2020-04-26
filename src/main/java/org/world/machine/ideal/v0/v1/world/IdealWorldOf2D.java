package org.world.machine.ideal.v0.v1.world;

import org.world.machine.ideal.v0.v1.IdealWorld;

import java.util.Arrays;

/**
 * 二维的世界
 */
public class IdealWorldOf2D extends IdealWorld {

    /**
     * 单元
     */
    String[][] status;

    public IdealWorldOf2D(int sizeX, int sizeY) {
        status = new String[sizeX][sizeY];
    }

    @Override
    public String getStatusAt(int[] index) {
        return status[index[0]][index[1]];
    }

    @Override
    public void setStatusAt(int[] index, String status) {
        this.status[index[0]][index[1]] = status;
    }

    @Override
    public int[] getInitIndex() {
        return new int[2];
    }

    @Override
    public String toString() {
        return "IdealWorldOf2D{" +
                "status=" + Arrays.toString(status) +
                '}';
    }
}
