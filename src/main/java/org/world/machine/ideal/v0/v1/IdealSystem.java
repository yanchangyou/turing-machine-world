package org.world.machine.ideal.v0.v1;

/**
 * 理想系统：具备内部世界和外部世界；
 * 内部世界可以：任意读写状态；
 * 外部世界：需要移动到指定位置才能读写状态；
 * 外部世界：位置不能叠加，如果存在
 */
public class IdealSystem {

    IdealSystemRule rule;

    int[] innerIndex;//内部位置
    int[] outerIndex;//外部位置

    IdealWorld innerWorld;//内部世界
    IdealWorld outerWorld;//外部世界

    public IdealSystem(IdealWorld innerWorld, IdealWorld outerWorld, IdealSystemRule rule) {
        this.innerWorld = innerWorld;
        this.outerWorld = outerWorld;

        this.innerIndex = innerWorld.getInitIndex();
        this.outerIndex = outerWorld.getInitIndex();

        this.rule = rule;
    }

    /**
     * 系统的运行
     */
    public void run() {

        while (true) {
            String innerStatus= innerWorld.getStatusAt(innerIndex);
            String outerStatus= outerWorld.getStatusAt(outerIndex);

            String[] status = new String[] { innerStatus, outerStatus };

            //按照规则转换
            int[][] indexDiff = rule.convert(status);

            //改变状态
            innerStatus = status[0];
            outerStatus = status[1];

            innerWorld.setStatusAt(innerIndex, innerStatus);
            outerWorld.setStatusAt(outerIndex, outerStatus);

            //改变方向
            innerIndex = indexDiff[0];
            outerIndex = indexDiff[1];

            if (rule.isStopped(innerStatus, outerStatus)) {
                break;
            }
        }
    }
}
