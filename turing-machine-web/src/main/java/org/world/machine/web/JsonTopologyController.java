package org.world.machine.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.world.machine.json.JsonTopologyUtil;

/**
 * @author yanchangyou
 */
@RestController
@RequestMapping("/api/json/topology")
public class JsonTopologyController {

    @RequestMapping("/getTopology")
    public String getTopology(String json) {

        return JsonTopologyUtil.getJsonTopologyString(JSONObject.parseObject(json));
    }

    @RequestMapping("/getStandardJson")
    public String getStandardJson(String json) {

        return JsonTopologyUtil.getStandardJson(JSONObject.parseObject(json))
                .toString(SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping("/compare")
    public Boolean getTopology(String json1, String json2) {
        return JsonTopologyUtil.compareJsonTopology(JSONObject.parseObject(json1), JSONObject.parseObject(json2));
    }

}
