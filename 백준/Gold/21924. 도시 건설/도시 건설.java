import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Node {
    int v;
    int w;
    public Node(int v, int w){
        this.v = v;
        this.w = w;
    }
}
public class Main {
    static int N,M;
    static boolean[] visit;
    static long cost, answer;
    static List<Node>[] adjArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        visit = new boolean[N+1];
        adjArr = new ArrayList[N+1];
        for(int i=0; i<=N; i++) adjArr[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            String[] input2 = br.readLine().split(" ");
            int st = Integer.parseInt(input2[0]);
            int ed = Integer.parseInt(input2[1]);
            int w = Integer.parseInt(input2[2]);
            cost+=w;
            adjArr[st].add(new Node(ed,w));
            adjArr[ed].add(new Node(st,w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.w, o2.w);
            }
        });

        pq.addAll(adjArr[1]);
        visit[1] = true;
        int pick = 1;
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if(visit[curr.v]) continue;
            visit[curr.v] = true;
            answer += curr.w;
            pick++;
            if(pick == N) break;
            for(int i=0; i<adjArr[curr.v].size(); i++){
                Node next = adjArr[curr.v].get(i);
                if(visit[next.v]) continue;
                pq.offer(new Node(next.v, next.w));
            }
        }
        if(pick == N)  System.out.println(cost-answer);
        else System.out.println(-1);
    }
}
