package org.world.machine.ideal.v0.v1;

/**
 * 坐标系:N维坐标
 */
public class Coordinate {

    int[] index;

    public Coordinate(int dimension) {
        this.index = new int[dimension];
    }

    public int[] getIndex() {
        return index;
    }

    public void setIndex(int[] index) {
        this.index = index;
    }
}
