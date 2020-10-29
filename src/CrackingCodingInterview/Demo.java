package CrackingCodingInterview;

import java.util.*;
import javafx.util.Pair;

public class Demo {
    public static void main(String[] args) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        /* count the occurances */
        for (int val : nums)
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);

        /* use min heap to store k most frequent element */
        PriorityQueue<Pair<Integer, Integer>> pq =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (int key : freqMap.keySet()) {
            pq.offer(new Pair<>(key, freqMap.get(key)));

            if (pq.size() > k)
                pq.poll();
        }

        int[] res = new int[k];
        int i = k - 1;

        while (!pq.isEmpty())
            res[i--] = pq.poll().getKey();

        System.out.println(Arrays.toString(res));
    }
}
