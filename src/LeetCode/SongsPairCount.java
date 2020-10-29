package LeetCode;

/**
 * https://leetcode.com/discuss/interview-question/861432/
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 */

import java.util.*;

public class SongsPairCount {
    public static int getSongsPairCount(List<Integer> songs) {
        int[] map = new int[61];
        int count = 0;

        for (int time : songs) {
            time %= 60;
            int comp = 60 - time == 60 ? 0 : 60 - time;

            if (map[comp] > 0)
                count += map[comp];

            map[time]++;
        }
        return count;
    }

    public static void test(List<Integer> songs, int expect) {
        System.out.println(getSongsPairCount(songs) == expect);
    }

    public static void main(String[] args) {
        List<Integer> s1 = Arrays.asList(37, 23, 60);
        List<Integer> s2 = Arrays.asList(10, 50, 90, 30);
        List<Integer> s3 = Arrays.asList(30, 20, 150, 100, 40);
        List<Integer> s4 = Arrays.asList(60, 60, 60);

        test(s1, 1);
        test(s2, 2);
        test(s3, 3);
        test(s4, 3);
    }
}