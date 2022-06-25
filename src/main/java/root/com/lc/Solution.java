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
//        int[] nums1 = {9, 8, 9};
//        int[] plusOne = plusOne(nums1);
//        System.out.println(Arrays.toString(plusOne));

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

//        String[] str = reverse(new String[]{"h", "e", "l", "l", "o", "~"});
//        System.out.println(Arrays.toString(str));

//        boolean palindrome = isPalindrome("0P");
//        System.out.println(palindrome);

//        int[] nums = new int[]{3, 2, 2, 3};
//        int i = removeElement(nums, 3);

//        ListNode head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        // [1, 1, 2, 3, 3]
//        deleteDuplicates(head);

//        double v = myPow(2.0, -2);
//        System.out.println(v);

//        ListNode listNode = addTwoNumbers(new ListNode(2, new ListNode(4, new ListNode(3))), new ListNode(5, new ListNode(6, new ListNode(4))));
//        rotates(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
//        moveZeroes1(new int[]{0, 1, 0, 5, 12});
//        int reverse = reverse(12345);
//        System.out.println(reverse);
//        maopao(new int[]{4, 3, 2, 5, 8});
//        int f = firstUniqChar("loveleetcode");
//        System.out.println(f);
//        System.out.println(isAnagram("aaccdd","ccaadd"));
//        System.out.println(isPalindrome1("race a car"));
//        System.out.println(myAtoi("+1"));

//        System.out.println(strStr("abcabeabcabcmn", "abcabcmn"));
//        System.out.println(strStr("ABA", "ABCABXYABCABATDM"));
//        String s = "ABAB";
//        int[] next = new int[s.length()];
//        getNextArray(s, next);
//        System.out.println(Arrays.toString(next));
//        System.out.println(indexOf("ABCABXYABCABATDM", "ABA"));
//        String s = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
//        System.out.println(s);

//        String s = countAndSay(10);
        // 3 11 3 1 1 111 3 1 22 1
//        System.out.println(s);

        System.out.println(3%2);
    }

    static String countAndSay(int n) {
        // 递归出口
        if(n==1){
            return "1";
        }
        // 假设我们获得上一次的结果为 s1 = 112213
        String s1 = countAndSay(n - 1);
        // 定义结果
        StringBuilder result = new StringBuilder();
        // 对s1遍历处理获取值
        char local = s1.charAt(0);
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            // 设定计数器 计算同一个数字出现的次数 count
            if(s1.charAt(i) == local){
                count++;
            }else {
                // 不符合，记录下
                result.append(count);
                result.append(local);
                count = 1;
                local = s1.charAt(i);
            }
        }
        result.append(count);
        result.append(local);
        System.out.println(count + "===" + local);
        return result.toString();
    }

    static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        //
        String prefix = strs[0];
        for (String s : strs) {
            while (s.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }

    static int indexOf(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int i = 0, j = 0;
        int[] next = new int[needle.length()];
        getNextArray(needle, next);
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                // ABC, B
                j = next[j];
            }
            if (j == needle.length()) {
                return i - j;
            }
        }
        return -1;
    }

    /**
     * 获取next数组
     */
    static void getNextArray(String s, int[] next) {
        // 第一位默认为-1
        next[0] = -1;
        int i = 0, j = -1;
        int length = s.length();
        while (i < length - 1) {
            if (j == -1 || s.charAt(i) == s.charAt(j)) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }
    }

    static int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        int i = 0;
        int j = 0;
        /**
         * 数组next表示pattern指定的下标前具有相同的字符串数量，语言组织能力不好，可能不是太好理解，我举个例子吧
         * ，比如ABCABA，数组next[0]是-1，这个是固定的，因为第一个A前面是没有字符的，next[1]是0，因为B的前面就一个A，没有
         * 重复的，所以是0,同理next[2]也是,next[3]也是0,而next[4]是1，因为next[4]所指向的是第二个B，第二个B前面有一个A和
         * 第一个A相同，所以是1,next[5]是2，因为next[5]所指向的是最后一个Ａ，因为前面的Ａ对比成功，并且Ｂ也对比成功，所以是２，
         * 也就是ＡＢ两个字符串匹配成功,再举个例子，比如WABCABA，数组除了第一个为-1，其他的都是为0，因为只有第一个匹配成功之后
         * 才能匹配后面的，虽然后面的AB和前面的AB匹配成功，但是后面AB的前面是C和前面AB的前面一个W不匹配，所以后面的匹配都是0.
         * 要记住只有指定字符前面的字符和第一个字符匹配成功的时候才能往后匹配，否则后面的永远都是先和第一个匹配。
         */
        int[] next = new int[needle.length()];
        getNext(needle, next);
        while (i < haystack.length() && j < needle.length()) {
            /**
             * 这里j等于-1的时候也只有在下面next数组赋值的时候才会出现，并且只有在数组next[0]的时候才会等于-1，
             其他时候是没有的，这一点要谨记，待会下面求next数组的时候就会用到。这里可以这样来理解，如果j不等于-1，
             并且下标i和j所指向的字符相等，那么i和j分别往后移一位继续比较，这个很好理解，那么如果j==-1的时候，就表示
             就表示前面没有匹配成功的，同时i往后移一位，j置为0（j==-1的时候，j++为0），再从0开始比较。
             */
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                /**
                 * i = i - j + 1;
                 j = 0;
                 返回到指定的位置，不是返回到匹配失败的下一个位置，这里都好理解，重点是求数组next。
                 这里只要j等于0，在next[j]赋值的之后，j就会等于-1；因为next[0]等于-1
                 */
                j = next[j]; // j回到指定位置
            }
            if (j == needle.length())
                return i - j;
        }
        return -1;
    }

    static void getNext(String p, int next[]) {
        int len = p.length();
        int i = 0;
        int j = -1;
        next[0] = -1;//这个默认的，
        // needle = "hello"
        /**
         * 匹配的时候是当前字符的前一个和前面的匹配，所以最后一个是不参与匹配的，可以看strStr方法的注释，
         */
        while (i < len - 1) {
            if (j == -1 || p.charAt(i) == p.charAt(j)) {
                /**
                 * 如果j不等于-1，指定的字符相等，那么i和j要往后移一位，这点很好理解，如果j为-1的时候，i往后移移位，j置为0
                 * 重新开始匹配。next[i]是匹配成功的数量
                 */
                i++;
                j++;
                next[i] = j;
            } else
            /**
             * 关键是这里不好理解，为什么匹配失败要让next[j]等于j，要记住这里的next[j]是指匹配成功的数量，有可能为0，也有可能是其他数.比如
             * 字符串ABCABXYABCABATDM,对应的next数组为{-1	0	0	0	1	2	0	0	1	2	3	4	5	1	0	0	}
             */
                j = next[j];
        }
    }

    static int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int res = 0;
        // 记录第一个非空格的位置
        int index = 0;
        while (index < length && s.charAt(index) == ' ') {
            index++;
        }
        // " " || ""
        if (index >= length) {
            return 0;
        }
        // 正负数标识
        int p = 1;
        char c = s.charAt(index);
        if (c == '-' || c == '+') {
            if (c == '-') {
                p = -1;
            }
            index++;
        }
        // 遍历字符串
        for (; index < chars.length; index++) {
            int d = chars[index] - '0';
            if (d > 9 || d < 0) {
                break;
            }
            int rs = res * 10 + d;
            if (rs / 10 != res) {
                return p == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = rs;
        }
        return p * res;
    }

    static boolean isPalindrome1(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }

        String s1 = sb.toString();
        int i = 0, j = s1.length() - 1;
        while (i < j) {
            if (s1.charAt(i) != s1.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer d = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), d + 1);
        }
        //
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer g = map.getOrDefault(c, 0);
            if (g > 0) {
                map.put(c, g - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    static int firstUniqChar(String s) {
        // 从后往前遍历，如果两者下标一致说明是第一个唯一的
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }

        return -1;
    }

    static void maopao(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                System.out.println("第" + i + "次，第" + j + "轮，i=" + nums[i] + "，j=" + nums[j]);
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[j + 1];
                    nums[j + 1] = temp;
                }
                System.out.println(Arrays.toString(nums));
            }
//            for (int j = i; j < nums.length; j++) {
//                int numI = nums[i],
//                        numJ = nums[j];
//                ++count;
//                if (numI > numJ) {
//                    nums[i] = numJ;
//                    nums[j] = numI;
//                }
//            }
        }
//        System.out.println(count);
//        System.out.println(Arrays.toString(nums));
    }

    static int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int res = 0;
        while (x != 0) {
//            s = x % 10 + s * 10;
            int t = x % 10;
            int newRes = res * 10 + t;
            System.out.println(newRes + "==" + t + "===" + res);
            //如果数字溢出，直接返回0
            if ((newRes - t) / 10 != res)
                return 0;
            res = newRes;

            x = x / 10;
        }
        return res;
    }

    static void moveZeroes1(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
//            if (nums[i] == 0) {
//                j++;
//            } else {
//                nums[i - j] = nums[i];
//                nums[i] = 0;
//            }
        }
        System.out.println(Arrays.toString(nums));
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

    static String[] reverse(String[] s) {
        int i = s.length / 2;
        for (int j = 0; j < i; j++) {
            String head = s[j];
            s[j] = s[s.length - j - 1];
            s[s.length - j - 1] = head;
        }
        return s;
    }

    static boolean isPalindrome(String s) {
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                s1.append(Character.toLowerCase(ch));
            }
        }

        System.out.println(s1.toString());
        int h = s1.length() / 2;
        for (int i = 0; i < h; i++) {
            if (s1.charAt(i) != s1.charAt(s1.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <a href='https://leetcode.cn/problems/remove-element/'>27. 移除元素</>
     */
    static int removeElement(int[] nums, int val) {
        int left = 0,
                right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[--right];
            } else {
                left++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return left;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/">83. 删除排序链表中的重复元素</a>
     */
    static ListNode deleteDuplicates(ListNode head) {
        ListNode c = head;
        while (c.next != null) {
            if (c.val == c.next.val) {
                c.next = c.next.next;
            } else {
                c = c.next;
            }
        }
        return head;
    }

    /**
     * <a href="https://leetcode.cn/problems/powx-n/">50. Pow(x, n)</a>
     */
    static double myPow(double x, int n) {
        if (n != 0) {
            int n1 = n > 0 ? n : -1 * n;
            double d = x;
            for (int i = 0; i < n1 - 1; i++) {
                d = d * x;
            }
            return n > 0 ? d : 1 / d;
        }
        return 0D;
    }

    /**
     * 两数相加
     * https://leetcode.cn/problems/add-two-numbers/
     */
    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);

        ListNode nextNode;
        nextNode = node;

        int n = 0;
        while (l1 != null || l2 != null) {
            int i1 = l1 == null ? 0 : l1.val;
            int i2 = l2 == null ? 0 : l2.val;

            int sum = i1 + i2 + n;
            n = sum > 9 ? 1 : 0;

            nextNode.next = new ListNode(sum % 10);
            nextNode = nextNode.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (n > 0) {
            nextNode.next = new ListNode(1);
        }
        return node.next;
    }

    static void rotates(int[] nums, int k) {
        rotate(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        rotate(nums, 0, k - 1);
        System.out.println(Arrays.toString(nums));
        rotate(nums, k, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    static void rotate(int[] nums, int s, int e) {
        while (s < e) {
            int temp = nums[s];
            nums[s++] = nums[e];
            nums[e--] = temp;
        }
    }
}
