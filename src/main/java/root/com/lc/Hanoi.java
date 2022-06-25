package root.com.lc;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 汉诺塔。A B C三个柱子，A上面放n个盘子，底下的盘子大，上面的盘子小，把A全部放到C上面，需要保持原样，每次只能移动一个盘子，并且每次移动都要保持底下大、上面小
 */
public class Hanoi {

    static int times = 0;

    public static void main(String[] args) throws InterruptedException {
        hanoi(3, "A", "B", "C");
    }

    static void log(int n, String x, String y) {
        System.out.println("第 " + ++times + " 次移动，盘子 " + n + " 从 " + x + " 移动到 " + y);
    }


    static void hanoi(int n, String a, String b, String c) throws InterruptedException {
//        new ThreadPoolExecutor();
        if (n == 1) {
            log(n, a, c);
        } else {
            hanoi(n - 1, a, c, b);
            log(n, a, c);
            hanoi(n - 1, b, a, c);
        }
//        while (true) {
//            TimeUnit.SECONDS.sleep(60);
//            break;
//        }
    }
}
