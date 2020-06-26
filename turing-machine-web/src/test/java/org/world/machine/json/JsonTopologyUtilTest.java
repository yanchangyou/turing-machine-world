package org.world.machine.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonTopologyUtilTest {

    @Test
    public void testTopology1() {
        JSONObject json = JSONObject.parseObject(
                "{\"d0\":{\"c0\":{\"b0\":{\"a0\":0,\"a1\":[1,2,3],\"a2\":true},\"b1\":{\"a0\":3,\"a1\":4,\"a2\":5},\"b2\":{\"a0\":6,\"a1\":7,\"a2\":8}},\"c1\":{\"b0\":{\"a0\":9,\"a1\":10,\"a2\":11},\"b1\":{\"a0\":12,\"a1\":13,\"a2\":14},\"b2\":{\"a0\":15,\"a1\":16,\"a2\":17}},\"c2\":{\"b0\":{\"a0\":18,\"a1\":19,\"a2\":20},\"b1\":{\"a0\":21,\"a1\":22,\"a2\":23},\"b2\":{\"a0\":24,\"a1\":25,\"a2\":26}}},\"d1\":{\"c0\":{\"b0\":{\"a0\":27,\"a1\":28,\"a2\":29},\"b1\":{\"a0\":30,\"a1\":31,\"a2\":32},\"b2\":{\"a0\":33,\"a1\":34,\"a2\":35}},\"c1\":{\"b0\":{\"a0\":36,\"a1\":37,\"a2\":38},\"b1\":{\"a0\":39,\"a1\":40,\"a2\":41},\"b2\":{\"a0\":42,\"a1\":43,\"a2\":44}},\"c2\":{\"b0\":{\"a0\":45,\"a1\":46,\"a2\":47},\"b1\":{\"a0\":48,\"a1\":49,\"a2\":50},\"b2\":{\"a0\":51,\"a1\":52,\"a2\":53}}},\"d2\":{\"c0\":{\"b0\":{\"a0\":54,\"a1\":55,\"a2\":56},\"b1\":{\"a0\":57,\"a1\":58,\"a2\":59},\"b2\":{\"a0\":60,\"a1\":61,\"a2\":62}},\"c1\":{\"b0\":{\"a0\":63,\"a1\":64,\"a2\":65},\"b1\":{\"a0\":66,\"a1\":67,\"a2\":68},\"b2\":{\"a0\":69,\"a1\":70,\"a2\":71}},\"c2\":{\"b0\":{\"a0\":72,\"a1\":73,\"a2\":74},\"b1\":{\"a0\":75,\"a1\":76,\"a2\":77},\"b2\":{\"a0\":78,\"a1\":79,\"a2\":80}}}}");

        System.out.println(JsonTopologyUtil.getJsonTopologyString(json));

    }

    @Test
    public void testCompareJsonTopology() {
        JSONObject json1 = getJson1();
        JSONObject json2 = getJson2();

        boolean isSame = JsonTopologyUtil.compareJsonTopology(json1, json2);

        if (isSame) {
            System.out.println("拓扑结构相同");
        } else {
            System.out.println("拓扑结构不相同");
        }

        System.out.println(JsonTopologyUtil.getJsonTopologyString(json1));
        System.out.println(JsonTopologyUtil.getJsonTopologyString(json2));

        Assert.assertTrue(isSame);
    }

    private static JSONObject getJson1() {
        return JSONObject.parseObject("{"
                + "'a0':1,"
                + "'a1':1.0,"
                + "'a2':true,"
                + "'a3':'1',"
                + "'a4':null,"
                + "'a5':[1],"
                + "'a6':[1.0],"
                + "'a7':[true],"
                + "'a8':['1'],"
                + "'a9':[null],"
                + "'a10':{"
                + "'    b0':1,"
                + "'    b1':1.0,"
                + "'    b2':true,"
                + "'    b3':'1',"
                + "'    b4':null,"
                + "'    b5':[1],"
                + "'    b6':[1.0],"
                + "'    b7':[true],"
                + "'    b8':['1'],"
                + "'    b9':[null],"
                + "'    b10':{'b0':1},"
                + "'    b11':[{'b0':1}]"
                + "     },"
                + "'a11':[{"
                + "     'b0':1,"
                + "     'b1':1.0,"
                + "     'b2':true,"
                + "     'b3':'1',"
                + "     'b4':null,"
                + "     'b5':[1],"
                + "     'b6':[1.0],"
                + "     'b7':[true],"
                + "     'b8':['1'],"
                + "     'b9':[null],"
                + "     'b10':{'b0':1},"
                + "     'b11':[{'b0':1}]"
                + "     }]"
                + "}", Feature.OrderedField);
    }

    private static JSONObject getJson2() {
        return JSONObject.parseObject("{"
                + "'a1':1.0,"
                //                + "'aa1':1.0,"
                + "'a0':1,"
                + "'a2':true,"
                + "'a3':'1',"
                + "'a4':null,"
                + "'a5':[1],"
                + "'a6':[1.0],"
                + "'a7':[true],"
                + "'a8':['1'],"
                + "'a9':[null],"
                + "'a10':{"
                + "'    b0':1,"
                + "'    b1':1.0,"
                + "'    b2':true,"
                + "'    b3':'1',"
                + "'    b4':null,"
                + "'    b5':[1],"
                + "'    b6':[1.0],"
                + "'    b7':[true],"
                + "'    b8':['1'],"
                + "'    b9':[null],"
                + "'    b10':{'b0':1},"
                + "'    b11':[{'b0':1}]"
                + "     },"
                + "'a11':[{"
                + "     'b0':1,"
                + "     'b1':1.0,"
                + "     'b2':true,"
                + "     'b3':'1',"
                + "     'b4':null,"
                + "     'b5':[1],"
                + "     'b6':[1.0],"
                + "     'b7':[true],"
                + "     'b8':['1'],"
                + "     'b9':[null],"
                + "     'b10':{'b0':1},"
                + "     'b11':[{'b0':1}]"
                + "     }]"
                + "}", Feature.OrderedField);
    }

    static void makeJson() {
        Map json = new LinkedHashMap();
        int fieldLength = 3;
        int level = 3;
        make(json, fieldLength, level);
        System.out.println(JSONObject.toJSON(json));
    }

    static int index = 0;

    private static void make(Map json, int fieldLength, int level) {
        for (int i = 0; i < fieldLength; i++) {
            if (level > 0) {
                Map child = new LinkedHashMap();
                make(child, fieldLength, level - 1);
                json.put(((char) ('a' + level)) + "" + i, child);
            } else {
                json.put(((char) ('a' + level)) + "" + i, index++);
            }
        }
    }
}