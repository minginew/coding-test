import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[] dr = {0,-1,-1};
    static int[] dc = {-1,0,-1};
    static int[][] map,dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        map = new int[N][M];
        dp = new int[N][M];

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split(" ");
            for(int c=0; c<M; c++){
                map[r][c] = dp[r][c] = Integer.parseInt(row[c]);
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {N-1,M-1});
        boolean[][] visit = new boolean[N][M];
        visit[N-1][M-1] = true;

        while (!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];

            for(int d=0; d<3; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];
                if(nr<0 || nc<0) continue;
                int maxNum = map[nr][nc]+dp[r][c];
                if(dp[nr][nc] <= maxNum) dp[nr][nc] = maxNum;
                if(!visit[nr][nc]){
                    q.offer(new int[] {nr,nc});
                    visit[nr][nc] = true;
                }
            }
        }
        System.out.println(dp[0][0]);
    }
}