import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M,max,maxValue;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        max = -1;
        map = new int[N][M];
        visit = new boolean[N][M];

        for(int r=0; r<N; r++) {
            String[] row = br.readLine().split(" ");
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(row[c]);
                if(maxValue<map[r][c]) maxValue = map[r][c];
            }
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                //ㅗ 모양
                exception(r,c);

                //dfs 4개짜리
                visit[r][c] = true;
                dfs(map[r][c], 0, r, c);
                visit[r][c] = false;
            }
        }
        System.out.println(max);

    }
    static void exception(int r, int c) {
        if (r+2 < N && c+1 < M)
            max = Math.max(max, map[r][c] + map[r+1][c] + map[r+1][c+1] + map[r+2][c]);
        if (r+2 < N && c > 0)
            max = Math.max(max, map[r][c] + map[r+1][c] + map[r+2][c] + map[r+1][c-1]);
        if (r+1 < N && c+2 < M)
            max = Math.max(max, map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+1]);
        if (r+1 < N && c+1 < M && c > 0)
            max = Math.max(max, map[r][c] + map[r+1][c] + map[r+1][c-1] + map[r+1][c+1]);

    }

    static void dfs(int sum, int n, int r, int c) {//합, 시행횟수, 시작좌표
        if(sum + (3-n)*maxValue <= max) return;
        if(n==3) {
            if(max<sum) max = sum;
            return;
        }
        for(int d=1; d<4; d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(nc<0 || nr>=N || nc>=M || visit[nr][nc]) continue;
            visit[nr][nc] = true;
            dfs(sum+map[nr][nc], n+1, nr, nc);
            visit[nr][nc] = false;
        }
    }
}