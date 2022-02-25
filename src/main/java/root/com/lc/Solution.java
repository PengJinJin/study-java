package root.com.lc;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
//        int[] nums = new int[]{7, 1, 5, 3, 6, 4, 100};
//        System.out.println(maxProfit(nums));
//        System.out.println(rotate(nums, 3));

//        int[] nums1 = {1, 3, 5, 7, 9, 8, 6, 4, 2};
//        int[] nums1 = {1,2,3,4};
//        System.out.println(containsDuplicate(nums1));

//        int[] nums1 = {5, 0, 1, 0, 3, 12};
//        moveZeroes(nums1);
//        System.out.println(Arrays.toString(nums1));

//        System.out.println(Arrays.toString(twoSum(new int[]{3, 0}, 3)));
        int[] nums1 = {9, 8, 9};
        int[] plusOne = plusOne(nums1);
        System.out.println(Arrays.toString(plusOne));

//        System.out.println(getLargestNumInArray2(nums1));
//        rotate(nums1, 3);
//        System.out.println(4 ^ 1);
//        System.out.println(5 ^ 2);
//        System.out.println(7 ^ 1);
//        System.out.println(6 ^ 2);
// nums1 = [4,9,5], nums2 = [9,4,9,8,4]

//        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 6, 6, 6, 6};
//        int i = removeDuplicates(nums2);
//        System.out.println(i);
    }

    /**
     * 输入：digits = [4,3,2,1]
     * 输出：[4,3,2,2]
     * 解释：输入数组表示数字 4321。
     * <p>
     * digits = [9,9,9,9]
     * 输出：[1,0,0,0,0]
     * <p/>
     */
    static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    /**
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     */
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), i};
            } else {
                map.put(num, i);
            }
        }
        return new int[0];
    }

    static void moveZeroes(int[] nums) {
//        int j = 0;
        // i - j = 第一个0的位置
        for (int i = 0, j = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num != 0) {
                nums[i] = 0;
                nums[i - j] = num;
            } else {
                j++;
            }
        }
    }

    static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.add(i)) {
                return true;
            }
        }
        return false;
    }

    static void rotate(int[] nums1, int k) {
        int length = nums1.length;
        k %= length;
        rotateAll(nums1, 0, nums1.length);
        rotateAll(nums1, 0, k);
        rotateAll(nums1, k, nums1.length);
    }

    static void rotateAll(int[] nums, int start, int end) {
        int length = nums.length;
        if (length < start || length < end) {
            return;
        }
        for (int i = start; i < end; i++) {
            int num = nums[i];
            nums[i] = nums[end - 1];
            nums[--end] = num;
        }
        System.out.println(Arrays.toString(nums));
    }

    static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[right] != nums[left]) {
                nums[++left] = nums[right];
            }
        }
        System.out.println(Arrays.toString(nums));
        return ++left;
    }

    static int getLargestNumInArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // 取中间数
        int mid = nums.length / 2;
        while (true) {
            int num = nums[mid];
            if (nums[mid - 1] < num && num > nums[mid + 1]) {
                return num;
            } else if (nums[mid - 1] > num) {
                // 左边更大，右移一位比较
                mid = mid - 1;
            } else {
                // 右边更大
                mid = mid + 1;
            }
        }
    }

    static int getLargestNumInArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return nums[left];
    }

    static int rotate(int[] nums) {
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
