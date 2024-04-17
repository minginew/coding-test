import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int T,N,total;
    static boolean[] visit, finish;
    static int[] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            total = 0;
            map = new int[N+1];
            visit = new boolean[N+1];
            finish = new boolean[N+1];

            String[] input = br.readLine().split(" ");
            for(int i=0; i<N; i++){
                map[i+1] = Integer.parseInt(input[i]);
            }

            for(int i=1; i<=N; i++){
                if(finish[i]) continue;
                dfs(i);
            }
            sb.append(N-total).append('\n');
        }
        System.out.println(sb.toString());
    }
    static void dfs(int curr){
        if(visit[curr]){
            finish[curr] = true;
            total++;
        }else {
            visit[curr] = true;
        }

        int next = map[curr];
        if(!finish[next]){
            dfs(next);
        }

        visit[curr] = false;
        finish[curr] = true;
    }
}