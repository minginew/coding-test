import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static boolean[][] visit;
    static Queue<int[]> q = new LinkedList<>();
    static Queue<int[]> cheeze = new LinkedList<>();

    public static void main(String[]  args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        visit = new boolean[N][M];

        for(int r=0; r<N; r++){
            String[] col = br.readLine().split(" ");
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(col[c]);
            }
        }

        int cheezeNum = 0;
        int meltingTIme = 0;

        q.offer(new int[] {0,0});
        bfs();

        while (!cheeze.isEmpty()){
            int len = cheeze.size();
            for(int i=0; i<len; i++){
                int r = cheeze.peek()[0];
                int c = cheeze.poll()[1];

                map[r][c] = 0;
                visit[r][c] = true;

                q.offer(new int[] {r,c});
            }
            bfs();
            meltingTIme++;
            cheezeNum = len;
        }

        System.out.println(meltingTIme);
        System.out.println(cheezeNum);
    }

    static void bfs (){
        while (!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];

            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr<0 || nr>=N || nc<0 || nc>=M || visit[nr][nc]) continue;
                visit[nr][nc] = true;
                if(map[nr][nc] == 0) q.offer(new int[] {nr,nc});
                else cheeze.offer(new int[] {nr,nc});
            }
        }
    }


}