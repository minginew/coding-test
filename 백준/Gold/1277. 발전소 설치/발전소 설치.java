import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int v;
    double w;
    public Node (int v, double w){
        this.v = v;
        this.w = w;
    }
}
public class Main {
    static int N,W;
    static  double M;
    static boolean[] visit;
    static double[] dist;
    static int[][] nodes;
    static boolean[][] linked;
    static List<Node>[] adjArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);
        M = Double.parseDouble(br.readLine());
        visit = new boolean[N+1];
        dist = new double[N+1];
        nodes = new int[N+1][2];
        linked = new boolean[N+1][N+1];
        adjArr = new ArrayList[N+1];

        for(int i=0; i<=N; i++){
            dist[i] = Double.MAX_VALUE;
            adjArr[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++){
            String[] input2 = br.readLine().split(" ");
            int r = Integer.parseInt(input2[0]);
            int c = Integer.parseInt(input2[1]);
            nodes[i][0] = r;
            nodes[i][1] = c;
        }

        for (int i=0; i<W; i++){
            String[] input3 = br.readLine().split(" ");
            int n1 = Integer.parseInt(input3[0]);
            int n2 = Integer.parseInt(input3[1]);
            linked[n1][n2] = true;
            linked[n2][n1] = true;
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i==j) continue;
                if(linked[i][j]){
                    adjArr[i].add(new Node(j, 0.0));
                }else {
                    int r1 = nodes[i][0];
                    int c1 = nodes[i][1];
                    int r2 = nodes[j][0];
                    int c2 = nodes[j][1];
                    double w = getDist(r1,c1,r2,c2);
                    if(w > M) continue;
                    adjArr[i].add(new Node(j, w));
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return Double.compare(n1.w, n2.w);
            }
        });

        pq.offer(new Node(1, 0.0));
        dist[1] = 0.0;
        Dijkstra(pq);
        System.out.println((int)(dist[N]*1000));
    }

    public static double getDist(int r1,int c1, int r2, int c2){
        double dr = Math.pow(r1-r2,2);
        double dc = Math.pow(c1-c2,2);
        return Math.sqrt(dr+dc);
    }

    public static void Dijkstra(PriorityQueue<Node> pq){
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if(visit[curr.v]) continue;
            visit[curr.v] = true;
            for(int i=0; i<adjArr[curr.v].size(); i++){
                Node next = adjArr[curr.v].get(i);
                if(!visit[next.v] && dist[next.v] > curr.w + next.w){
                    dist[next.v] = curr.w + next.w;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}
