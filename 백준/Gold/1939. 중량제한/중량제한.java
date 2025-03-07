import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int v1, v2;
    long w;
    public Edge (int v1, int v2, int w){
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
}
public class Main {
    static int N,M;
    static int st, ed;
    static int[] parent, rank;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        rank = new int[N+1];
        parent = new int[N+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(Edge e1, Edge e2){
                return Long.compare(e2.w,e1.w);
            }
        });

        for(int i=0; i<M; i++){
            String[] input2 = br.readLine().split(" ");
            int v1 = Integer.parseInt(input2[0]);
            int v2 = Integer.parseInt(input2[1]);
            int w = Integer.parseInt(input2[2]);
            pq.offer(new Edge(v1,v2,w));
        }

        String[] input3 = br.readLine().split(" ");
        st = Integer.parseInt(input3[0]);
        ed = Integer.parseInt(input3[1]);

        makeup();
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int f1 = find(edge.v1);
            int f2 = find(edge.v2);
            long w = edge.w;
            if(f1 == f2) continue;
            union(f1,f2);
            if (find(st) == find(ed)) {
                System.out.println(w);
                break;
            }
        }
    }

    public static void makeup(){
        for(int i=0; i<parent.length; i++) parent[i] = i;
    }

    public static int find(int node){
        if(parent[node] == node) return node;
        else return parent[node] = find(parent[node]);
    }

    public static void union(int f1, int f2){
        if(rank[f1] < rank[f2]){
            parent[f1] = f2;
        }else{
            parent[f2] = f1;
            if(rank[f1] == rank[f2]) rank[f1]++;
        }
    }
}