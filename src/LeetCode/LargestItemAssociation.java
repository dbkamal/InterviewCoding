package LeetCode;

/**
 * https://leetcode.com/discuss/interview-question/782606/
 */

import java.util.*;

public class LargestItemAssociation {
    public static List<String> largestItemAssociation(List<PairString> itemAssociation) {
        /* build graph */
        Map<String, List<String>> graph = new HashMap<>();

        for (PairString ps : itemAssociation) {
            graph.putIfAbsent(ps.first, new ArrayList<>());
            graph.get(ps.first).add(ps.second);

            graph.putIfAbsent(ps.second, new ArrayList<>());
            graph.get(ps.second).add(ps.first);
        }

        /* build list of associations using DFS call */
        List<List<String>> lists = new ArrayList<>();
        Map<String, Boolean> visited = new HashMap<>();

        for (String src : graph.keySet())
            visited.put(src, false);

        for (String src : graph.keySet()) {
            if (!visited.get(src)) {
                List<String> list = new ArrayList<>();
                list.add(src);
                dfs(graph, src, visited, list);
                lists.add(new ArrayList<>(list));
            }
        }

        /* sort the list of associations to find largest list and if multiple same size found,
        choose the smallest lexicographically sorted one */
        Collections.sort(lists, (a, b) -> {

            Collections.sort(a);
            Collections.sort(b);

            if (a.size() != b.size())
                return b.size() - a.size();
            return a.get(0).compareTo(b.get(0));
        });

        return lists.get(0);
    }

    public static void dfs(Map<String, List<String>> graph, String src, Map<String, Boolean> visited, List<String> list) {
        visited.put(src, true);

        for (String adj : graph.get(src)) {
            if (!visited.get(adj)) {
                list.add(adj);
                dfs(graph, adj, visited, list);
            }
        }
    }

    public static void main(String[] args) {
        List<PairString> list = new ArrayList<>();
        list.add(new PairString("mac", "watch_os"));
        list.add(new PairString("iphone", "ipad"));

        List<String> res = largestItemAssociation(list);

        for (String s : res)
            System.out.println(s);
    }
}

class UnionFind {
    int n;
    int[] parent, rank;

    UnionFind(int n) {
        this.n = n;
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int rx = find(x), ry = find(y);

        if (rx != ry) {
            if (rank[rx] < rank[ry])
                parent[rx] = ry;
            else if (rank[rx] > rank[ry])
                parent[ry] = rx;
            else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }
}

class PairString {
    String first;
    String second;

    public PairString(String first, String second) {
        this.first = first;
        this.second = second;
    }
}
