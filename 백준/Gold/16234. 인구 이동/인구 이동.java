import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static boolean possible = true;
    static int N,L,R;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static boolean[][] visit;
    static Queue<int[]> q;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        L = Integer.parseInt(in[1]);
        R = Integer.parseInt(in[2]);

        map = new int[N][N];
        visit = new boolean[N][N];

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split(" ");
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(row[c]);
            }
        }

        int ans = 0;
        while (possible){
            possible = false;
            q = new LinkedList<>();
            visit = new boolean[N][N];

            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    if(visit[r][c]) continue;
                    boolean start = false;
                    for(int d=0; d<4; d++){
                        int nr = r+dr[d];
                        int nc = c+dc[d];

                        if(nr<0 || nc<0 || nr>=N || nc>=N ) continue;
                        int abs = Math.abs(map[nr][nc]-map[r][c]);
                        if(L<=abs || R>=abs){
                            start = true;
                            break;
                        }
                    }
                    if (!start) continue;
                    q.offer(new int[] {r,c});
                    visit[r][c] = true;
                    bfs();
                }
            }
            if (possible) ans++;
        }
        System.out.println(ans);

    }
    static void bfs(){
        Queue<int[]> adj = new LinkedList<>();
        int sum = 0;
        while (!q.isEmpty()){
            int r = q.peek()[0];
            int c= q.poll()[1];
            sum += map[r][c];
            adj.offer(new int[] {r,c});
            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr<0 || nc<0 || nr>=N || nc>=N || visit[nr][nc]) continue;
                int abs = Math.abs(map[nr][nc]-map[r][c]);
                if(L<=abs && R>=abs){
                    q.offer(new int[] {nr,nc});
                    visit[nr][nc] = true;
                }
            }
        }
        sum /= adj.size();
        if(adj.size()>1){
            while (!adj.isEmpty()){
                int r = adj.peek()[0];
                int c = adj.poll()[1];
                map[r][c] = sum;
                possible = true;
            }
        }
    }
}