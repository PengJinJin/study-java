package root.com.lc;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4, 100};
//        System.out.println(maxProfit(nums));
        System.out.println(rotate(nums, 3));
    }

    static int rotate(int[] nums, int k) {
        int length = nums.length - 1;
        for (int i = 0; i < length; i++) {
            int temp = nums[length];// 获取最后一个元素
            nums[length] = nums[i];// 把最后一个元素赋值给第一个元素
            nums[i] = temp;// 把第一个元素赋给最后一个元素
            length = length - 1;
        }
        System.out.println("全部翻转：" + Arrays.toString(nums));
        return 0;
    }

    /**
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 作者：力扣 (LeetCode)
     * <p>
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
     * <p>
     * 来源：力扣（LeetCode）
     * <p>
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static int maxProfit(int[] nums) {
        int total = 0;
        for (int i = 1; i < nums.length; i++) {
            int i1 = nums[i] - nums[i - 1];
            if (i1 > 0) {
                total = total + i1;
            }
        }
        return total;
    }
}
