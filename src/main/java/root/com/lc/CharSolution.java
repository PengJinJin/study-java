package root.com.lc;

import java.util.Arrays;

public class CharSolution {

    public static void main(String[] args) {
//        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
//        reverseString(s);
//        System.out.println(Arrays.toString(s));
//        System.out.println(reverse(-122230));
//        System.out.println(firstUniqChar2("zleetcode"));
        System.out.println(isAnagram("rat", "car"));
    }



    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] c = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            c[chars[i] - 'a']++;
        }
        System.out.println(Arrays.toString(c));

        char[] chars1 = t.toCharArray();
        for (int i = 0; i < t.length(); i++) {
            c[chars1[i] - 'a']--;
        }
        System.out.println(Arrays.toString(c));
        for (int j : c) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    static int firstUniqChar2(String s) {
        int[] c = new int[26];
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            c[aChar - 'a']++;
        }

//        System.out.println(Arrays.toString(c));
//        System.out.println(Arrays.toString(chars));
        for (int i = 0; i < chars.length; i++) {
//            System.out.println(c[chars[i] - 'a']);
            if (c[chars[i] - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (s.indexOf(chars[i]) == s.lastIndexOf(chars[i])) {
                return i;
            }
        }
        return -1;
    }

    static int reverse(int x) {
        long res = 0;
        while (x != 0) {
            int i = x % 10;
            res = res * 10 + i;
            x = x / 10;
        }
        return (int) res == res ? (int) res : 0;
    }

    static void reverseString(char[] s) {
        int length = s.length;
        for (int i = 0; i < length; i++) {
            char c = s[i];
            s[i] = s[--length];
            s[length] = c;
        }
    }
}
