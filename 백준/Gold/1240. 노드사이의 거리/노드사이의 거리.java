import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int v;
    int w;
    public Node (int v, int w){
        this.v = v;
        this.w = w;
    }
}
public class Main {
    static int N, M, distant;
    static boolean[] visit;
    static List<Node>[] adjArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        visit = new boolean[N];
        adjArr = new ArrayList[N];
        for(int i=0; i<N; i++) adjArr[i] = new ArrayList<>();
        for(int i=0; i<N-1; i++){
            String[] input2 = br.readLine().split(" ");
            int v1 = Integer.parseInt(input2[0])-1;
            int v2 = Integer.parseInt(input2[1])-1;
            int w = Integer.parseInt(input2[2]);
            adjArr[v1].add(new Node(v2, w));
            adjArr[v2].add(new Node(v1, w));
        }

        for(int i=0; i<M; i++){
            String[] input2 = br.readLine().split(" ");
            int start = Integer.parseInt(input2[0])-1;
            int target = Integer.parseInt(input2[1])-1;
            visit = new boolean[N];
            dfs(start, target, 0);

            System.out.println(distant);
        }
    }
    public static void dfs(int node, int target, int dist){
        if(visit[node]) return;
        visit[node] = true;

        if(node ==  target) {
            distant = dist;
            return;
        }
        for(int i=0; i<adjArr[node].size(); i++){
            Node next = adjArr[node].get(i);
            int v = next.v;
            int w = next.w;
            if(visit[v]) continue;
            dfs(v, target, dist+w);
            visit[v] = true;
        }
    }
}
