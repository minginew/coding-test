import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,root;
    static boolean[] visit, parents;
    static long[] values, dp;
    static List<Integer>[] adjArr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        values = new long[N];
        dp = new long[N];
        adjArr = new ArrayList[N];
        visit = new boolean[N];
        parents = new boolean[N];

        for(int i=0; i<N; i++) adjArr[i] = new ArrayList<>();
        for(int i=0; i<N-1; i++){
            String[] input = br.readLine().split(" ");
            int p = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);
            parents[n] = true;
            adjArr[n].add(p);
            adjArr[p].add(n);
        }

        for(int i=0; i<N; i++){
            if(!parents[i]){
                root = i;
                break;
            }
        }

        String[] input = br.readLine().split(" ");
        for(int i=0; i<N; i++){
            values[i] = Integer.parseInt(input[i]);
        }

        dfs(root);
        System.out.println(dp[root]);
    }

    public static long dfs(int node){
        visit[node] = true;
        long sum = values[node];
        for(int i=0; i<adjArr[node].size(); i++){
            int next = adjArr[node].get(i);
            if(visit[next]) continue;
            sum = Math.max(sum, sum + dfs(next));
        }
        dp[node] = sum;
        return sum;
    }
}
