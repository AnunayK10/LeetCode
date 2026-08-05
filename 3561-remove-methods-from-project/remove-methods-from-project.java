public class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            graph[inv[0]].add(inv[1]);
        }
        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        suspicious[k] = true;        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : graph[curr]) {
                if (!suspicious[neighbor]) {
                    suspicious[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        boolean cannotRemove = false;
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!suspicious[u] && suspicious[v]) {
                cannotRemove = true;
                break;
            }
        }
        List<Integer> result = new ArrayList<>();
        if (cannotRemove) {
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (!suspicious[i]) {
                    result.add(i);
                }
            }
        }
        return result;
    }
}
