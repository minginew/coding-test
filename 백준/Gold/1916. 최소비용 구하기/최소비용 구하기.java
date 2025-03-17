import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int v;
    long w;
    public Node(int v, long w){
        this.v = v;
        this.w = w;
    }
}
public class Main {
    static int N,M,start,target;
    static long[] dist;
    static boolean[] visit;
    static List<Node> adjArr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visit = new boolean[N+1];
        dist = new long[N+1];
        adjArr = new ArrayList[N+1];

        for(int i=0; i<=N; i++){
            dist[i] = Long.MAX_VALUE;
            adjArr[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            String[] input2 = br.readLine().split(" ");
            int st = Integer.parseInt(input2[0]);
            int ed = Integer.parseInt(input2[1]);
            long w = Integer.parseInt(input2[2]);
            adjArr[st].add(new Node(ed,w));
        }

        String[] input3 = br.readLine().split(" ");
        start = Integer.parseInt(input3[0]);
        target = Integer.parseInt(input3[1]);

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Long.compare(o1.w, o2.w);
            }
        });

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if(visit[curr.v]) continue;
            visit[curr.v] = true;

            if(curr.v == target) break;

            for(int i=0; i<adjArr[curr.v].size(); i++){
                Node next = adjArr[curr.v].get(i);
                if(!visit[next.v] && dist[next.v] > curr.w + next.w){
                    dist[next.v] = curr.w + next.w;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
        System.out.println(dist[target]);
    }
}
