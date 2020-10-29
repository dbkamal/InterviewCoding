package LeetCode;

/**
 * https://leetcode.com/discuss/interview-question/861453/
 */

import java.util.*;

public class ContainerItems {
    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
        /* pre-process step:
           1. when moving from left to right, capture the last seen '|' index position
           2. when moving from right to left, capture the last seen '|' index position
        */
        int[] left = new int[s.length()], right = new int[s.length()], prefixSum = new int[s.length()];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|')
                left[i] = i;
            else
                left[i] = i > 0 ? left[i - 1] : left[i];
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '|')
                right[i] = i;
            else
                right[i] = i < s.length() - 1 ? right[i + 1] : right[i];
        }

        /* prefix sum if '*' found then increment the count otherwise set to the previous value */
        for (int i = 0; i < s.length(); i++) {
            prefixSum[i] = i > 0 ? prefixSum[i - 1] + (s.charAt(i) == '*' ? 1 : 0) : 0;
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < startIndices.size(); i++) {
            int start_pos = startIndices.get(i) - 1, end_pos = endIndices.get(i) - 1;

            if (right[start_pos] >= left[end_pos])
                res.add(0);
            else
                res.add(prefixSum[left[end_pos]] - prefixSum[right[start_pos]]);
        }

        return res;
    }

    public static void display(List<Integer> res) {
        for (int val : res)
            System.out.println(val);
    }

    public static void main(String[] args) {
        List<Integer> res = numberOfItems("*|*|*|", new ArrayList<>(Arrays.asList(1)),
                                                         new ArrayList<>(Arrays.asList(6)));

        display(res);
    }
}
