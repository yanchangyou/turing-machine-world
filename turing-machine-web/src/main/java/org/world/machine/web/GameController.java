package org.world.machine.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Random;

/**
 * @author yanchangyou
 */
@RestController
@RequestMapping("/api/game")
public class GameController {

    public static final char ADD = 'A';
    public static final char SUB = 'S';
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 99;
    public static final int MIN_INDEX = 0;
    public static final int MAX_INDEX = 7;

    /**
     * 当前操作cells
     */
    int[] cells = new int[8];

    /**
     * 目标cells
     */
    int[] targetCells = new int[8];

    @RequestMapping("/init")
    public String init() {
        Random random = new Random();
        for (int i = 0; i < targetCells.length; i++) {
            targetCells[i] = random.nextInt(99);
            cells[i] = 0;
        }

        String result = Arrays.toString(targetCells);

        //todo 没有完成时，不能执行初始化

        return result;
    }

    @RequestMapping("/viewTarget")
    public String viewTarget() {

        String result = Arrays.toString(targetCells);

        return result;
    }

    @RequestMapping("/view")
    public String view() {

        String result = Arrays.toString(cells);

        return result;
    }

    @RequestMapping("/update/{index}/{operate}")
    public String update(@PathVariable("index") int index, @PathVariable("operate") char operate) {

        if (MIN_INDEX <= index && index <= MAX_INDEX) {
            switch (operate) {
                case ADD:
                    cells[index]++;
                    cells[index] = cells[index] > MAX_VALUE ? MAX_VALUE : cells[index];
                    break;
                case SUB:
                    cells[index]--;
                    cells[index] = cells[index] < MIN_VALUE ? MIN_VALUE : cells[index];
                    break;
                default:
                    ;
            }
        }

        String result = Arrays.toString(cells);

        return result;
    }

    /**
     * 判断两个数组是否一样
     *
     * @param array1
     * @param array2
     * @return
     */
    private static boolean isMatched(int[] array1, int[] array2) {
        boolean result = true;

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                result = false;
            }
        }
        return result;
    }
}
