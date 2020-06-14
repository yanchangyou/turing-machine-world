package org.world.machine.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.world.machine.bf.v0.vx.BFMachine;
import org.world.machine.bfpp.v0.v1.BFPlusPlusMachine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author yanchangyou
 */
@RestController
@RequestMapping("/api/index")
public class IndexController {

    static {
        BFMachine.disableLog();
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/execute")
    public String execute(@RequestParam("code") String code, @RequestParam("input") String input) {
        System.out.println("code:" + code);
        System.out.println("input:" + input);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        String result = BFPlusPlusMachine.execute(code, inputStream, outputStream);
        System.out.println("result:" + result);

        return outputStream.toString();
    }
}
