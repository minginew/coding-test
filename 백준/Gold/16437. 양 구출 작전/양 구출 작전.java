import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    String type;
    long w;
    public Node(String type, long w){
        this.type = type;
        this.w = w;
    }
}
public class Main {
    static int N;
    static long total;
    static Node[] island;
    static boolean[] visit;
    static List<Integer>[] adjArr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        island = new Node[N];
        visit = new boolean[N];
        adjArr = new ArrayList[N];
        for(int i=0; i<N; i++) adjArr[i] = new ArrayList<>();

        for(int i=1; i<N; i++){
            String[] input = br.readLine().split(" ");
            String type = input[0];
            long w = Long.parseLong(input[1]);
            int p = Integer.parseInt(input[2])-1;

            island[i] = new Node(type,w);
            adjArr[i].add(p);
            adjArr[p].add(i);
        }
        visit[0] = true;
        dfs(0,0);
        System.out.println(total);
    }
    public static long dfs(int node, long wolf){
        for(int i=0; i<adjArr[node].size(); i++){
            int next = adjArr[node].get(i);
            if(visit[next]) continue;
            visit[next] = true;
            if(island[next].type.equals("S")){
                long alive = island[next].w - wolf;
                if(alive > 0){
                    total += alive;
                    wolf = 0;
                    wolf = Math.min(dfs(next, wolf),wolf);
                }else {
                    wolf = Math.abs(alive);
                    wolf = Math.min(dfs(next, wolf),wolf);
                }
            }else if (island[next].type.equals("W")){
                wolf = Math.min(dfs(next, wolf + island[next].w),wolf);
            }
        }
        return wolf;
    }
}
