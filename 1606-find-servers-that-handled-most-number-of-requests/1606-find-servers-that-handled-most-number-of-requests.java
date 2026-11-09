class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] count = new int[k];
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) free.add(i);

        // {endTime, serverId}
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        for (int i = 0; i < arrival.length; i++) {
            // Release servers that finished by the time request i arrives
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                free.add((int) busy.poll()[1]);
            }

            if (free.isEmpty()) continue; // all busy -> drop

            Integer server = free.ceiling(i % k);
            if (server == null) server = free.first(); // wrap around

            free.remove(server);
            count[server]++;
            busy.offer(new long[]{(long) arrival[i] + load[i], server});
        }

        int max = 0;
        for (int c : count) max = Math.max(max, c);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == max) result.add(i);
        }
        return result;
    }
}