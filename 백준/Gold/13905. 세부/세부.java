import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Edge {
    int v1, v2;
    int w;
    public Edge (int v1, int v2, int w){
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
}
public class Main {
    static int N, M;
    static int start, target;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        parent = new int[N+1];
        rank = new int[N+1];

        String[] input2 = br.readLine().split(" ");
        start = Integer.parseInt(input2[0]);
        target = Integer.parseInt(input2[1]);

        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o2.w, o1.w);
            }
        });

        for(int i=0; i<M; i++){
            String[] input3 = br.readLine().split(" ");
            int v1 = Integer.parseInt(input3[0]);
            int v2 = Integer.parseInt(input3[1]);
            int w = Integer.parseInt(input3[2]);
            pq.offer(new Edge(v1,v2,w));
        }

        makeUp(parent);
        while (!pq.isEmpty()){
            int v1 = pq.peek().v1;
            int v2 = pq.peek().v2;
            int w = pq.poll().w;
            if(parent[v1] == parent[v2]) continue;

            union(v1,v2);
            if(find(start) == find(target)){
                System.out.println(w);
                return;
            }
        }
        System.out.println(0);
    }

    public static void makeUp(int[] arr){
        for(int i=0; i<arr.length; i++) arr[i] = i;
    }
    public static int find(int node){
        if(parent[node] == node) return node;
        else return parent[node] = find(parent[node]);
    }

    public static void union(int v1, int v2){
        int f1 = find(v1);
        int f2 = find(v2);
        if(f1 == f2) return;

        if(rank[f1] < rank[f2]){
            parent[f1] = f2;
        }else{
            parent[f2] = f1;
            if(rank[f1] == rank[f2]) rank[f1]++;
        }
    }
}
