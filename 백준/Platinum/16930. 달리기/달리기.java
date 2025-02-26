import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,K;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static char[][] map;
    static int[][] dist;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        map = new char[N][M];
        visit = new boolean[N][M];
        dist = new int[N][M];
        for(int r=0; r<N; r++){
            String[] col = br.readLine().split("");
            for(int c=0; c<M; c++){
                char n = col[c].charAt(0);
                map[r][c] = n;
                dist[r][c] = Integer.MAX_VALUE;
            }
        }

        String[] input2 = br.readLine().split(" ");
        int x1 = Integer.parseInt(input2[0])-1;
        int y1 = Integer.parseInt(input2[1])-1;
        int x2 = Integer.parseInt(input2[2])-1;
        int y2 = Integer.parseInt(input2[3])-1;

        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        q.offer(new int[] {x1,y1,0});

        while (!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.peek()[1];
            int t = q.poll()[2];

            if(visit[r][c]) continue;
            visit[r][c] = true;
            if(r==x2 && c==y2){
                System.out.println(t);
                return;
            }
            for(int d=0; d<4; d++){
                for(int k=1; k<=K; k++){
                    int nr = r + k*dr[d];
                    int nc = c + k*dc[d];
                    if(nr<0 || nc<0 || nr>=N || nc>=M  || visit[nr][nc] || map[nr][nc] == '#') break;
                    if(dist[nr][nc] < t+1) break;
                    q.offer(new int[] {nr, nc, t+1});
                    dist[nr][nc] = t+1;
                }
            }
        }
        System.out.println(-1);
    }
}