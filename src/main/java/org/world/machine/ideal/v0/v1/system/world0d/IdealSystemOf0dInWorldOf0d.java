package org.world.machine.ideal.v0.v1.system.world0d;

import org.world.machine.ideal.v0.v1.world.IdealWorldOf0D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 0D世界的0D系统
 */
public class IdealSystemOf0dInWorldOf0d {

    IdealWorldOf0D outerWorld;
    String innerStatus;

    public IdealSystemOf0dInWorldOf0d(IdealWorldOf0D outerWorld) {
        this.outerWorld = outerWorld;
        innerStatus = "0";
    }

    public void run(Map<String, String> innerRule, Map<String, String> outerRule) {

        String outerStatus = outerWorld.getStatusAt(null);

        innerStatus = innerRule.get(innerStatus + "," + outerStatus);
        outerStatus = outerRule.get(innerStatus + "," + outerStatus);

        outerWorld.setStatusAt(null, outerStatus);

    }

    public static void main(String[] args) {

        for (int j1 = 0; j1 < 2; j1++) {
            for (int j2 = 0; j2 < 2; j2++) {
                for (int j3 = 0; j3 < 2; j3++) {
                    for (int j4 = 0; j4 < 2; j4++) {

                        for (int i1 = 0; i1 < 2; i1++) {
                            for (int i2 = 0; i2 < 2; i2++) {
                                for (int i3 = 0; i3 < 2; i3++) {
                                    for (int i4 = 0; i4 < 2; i4++) {

                                        IdealWorldOf0D outerWorld = new IdealWorldOf0D("0");

                                        IdealSystemOf0dInWorldOf0d systemOf0dInWorldOf0d = new IdealSystemOf0dInWorldOf0d(
                                                outerWorld);
                                        Map<String, String> innerRule = new HashMap();
                                        Map<String, String> outerRule = new HashMap();
                                        innerRule.put("0,0", "" + j1);
                                        innerRule.put("0,1", "" + j2);
                                        innerRule.put("1,0", "" + j3);
                                        innerRule.put("1,1", "" + j4);

                                        outerRule.put("0,0", "" + i1);
                                        outerRule.put("0,1", "" + i2);
                                        outerRule.put("1,0", "" + i3);
                                        outerRule.put("1,1", "" + i4);

                                        System.out.println("innerRule:" + innerRule);
                                        System.out.println("outerRule:" + outerRule);
                                        Set<String> statusSet = new HashSet();
                                        statusSet.add("0,0");
                                        while (true) {

                                            systemOf0dInWorldOf0d.run(innerRule, outerRule);

                                            String innerStatus = systemOf0dInWorldOf0d.getInnerStatus();
                                            String outerStatus = outerWorld.getStatusAt(null);

                                            String newStatus = innerStatus + "," + outerStatus;
                                            if (!statusSet.contains(newStatus)) {
                                                statusSet.add(newStatus);
                                            } else {
                                                break;
                                            }
                                        }
                                        System.out.println(statusSet);
                                        System.out.println();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public String getInnerStatus() {
        return innerStatus;
    }
}