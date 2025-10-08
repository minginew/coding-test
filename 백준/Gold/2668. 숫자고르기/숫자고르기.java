import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] adjArr;
    static boolean[] pick;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        adjArr = new int[N+1];
        pick = new boolean[N+1];

        for(int i=0; i<N; i++){
            adjArr[i+1] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=N; i++){
            if(pick[i]) continue;
            boolean[] visit = new boolean[N+1];
            dfs(i,i,visit);
        }

        int count = 0;
        for(int i=1; i<=N; i++){
            if(pick[i]){
                count++;
                sb.append(i).append('\n');
            }
        }
        System.out.println(count);
        System.out.println(sb);
    }
    public static void dfs(int start, int node,boolean[] visit){
        if(visit[node]){
            if(start == node){
                for(int i=1; i<=N; i++){
                    if(visit[i]) pick[i] = true;
                }
            }
            return;
        }
        visit[node] = true;
        dfs(start, adjArr[node], visit);
    }
}
