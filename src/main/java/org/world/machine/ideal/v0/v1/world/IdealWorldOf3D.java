package org.world.machine.ideal.v0.v1.world;

import org.world.machine.ideal.v0.v1.IdealWorld;

/**
 * 三维的世界
 */
public class IdealWorldOf3D extends IdealWorld {

    /**
     * 单元
     */
    String[][][] status;

    public IdealWorldOf3D(int sizeX, int sizeY, int sizeZ) {
        status = new String[sizeX][sizeY][sizeZ];
    }

    @Override
    public String getStatusAt(int[] index) {
        return status[index[0]][index[1]][index[2]];
    }

    @Override
    public void setStatusAt(int[] index, String status) {
        this.status[index[0]][index[1]][index[2]] = status;
    }

    @Override
    public int[] getInitIndex() {
        return new int[3];
    }
}
