import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] count = new int[k];
        
        // TreeSet to store indices of currently available servers
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        // Min-heap storing [endTime, serverId]
        // Sorted by endTime ascending
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int time = arrival[i];
            int duration = load[i];
            
            // Free up servers whose tasks have finished by the current arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                available.add(busy.poll()[1]);
            }
            
            // If all servers are occupied, drop request
            if (available.isEmpty()) {
                continue;
            }
            
            // Find server >= (i % k)
            Integer server = available.ceiling(i % k);
            if (server == null) {
                // Wrap around to the first available server
                server = available.first();
            }
            
            // Assign request
            count[server]++;
            available.remove(server);
            busy.offer(new int[]{time + duration, server});
        }
        
        // Determine maximum handled requests
        int maxCount = 0;
        for (int c : count) {
            maxCount = Math.max(maxCount, c);
        }
        
        // Collect all servers that handled maxCount requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxCount) {
                result.add(i);
            }
        }
        
        return result;
    }
}