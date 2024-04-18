import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N,M,count;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
    // 맵, 1 == 불켜짐 
    static int[][] map;
    // 방문배열
    static boolean[][] visit;
    // 스위치 정보
    static List<int[]>[][] light;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N+1][N+1];
        visit = new boolean[N+1][N+1];
        light = new ArrayList[N+1][N+1];

        for(int i=0; i<=N; i++)
            for (int j=0; j<=N; j++)
                light[i][j] = new ArrayList<>();

        for(int i=0; i<M; i++){
            String[] lightInfo = br.readLine().split(" ");
            int x = Integer.parseInt(lightInfo[0]);
            int y = Integer.parseInt(lightInfo[1]);
            int a = Integer.parseInt(lightInfo[2]);
            int b = Integer.parseInt(lightInfo[3]);
            light[x][y].add(new int[] {a,b});
        }

        q.offer(new int[] {1,1});
        visit[1][1] = true;
        map[1][1] = 1;

        bfs();
        System.out.println(count+1);
    }

    // BFS
    static void bfs(){
        while (!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            if (!light[r][c].isEmpty()){
                offer(r,c);
                for (int[] rc : light[r][c]){
                    int r1 = rc[0];
                    int c1 = rc[1];
                    if(map[r1][c1] == 1) continue;
                    map[r1][c1] = 1;
                    count++;

                    for(int d=0; d<4; d++){
                        int nr1 = r1 + dr[d];
                        int nc1 = c1 + dc[d];
                        if (nr1>0 && nr1<=N && nc1>0 && nc1<=N && visit[nr1][nc1]) {
                            q.offer(new int[]{r1, c1});
                            visit[r1][c1] = true;
                            break;
                        }
                    }
                }
                continue;
            }
            offer(r,c);
        }
    }

    // BFS 상하좌우 탐색
    static void offer(int r, int c){
        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr==0 || nr>N || nc==0 || nc>N || visit[nr][nc] || map[nr][nc]==0) continue;
            q.offer(new int[] {nr,nc});
            visit[nr][nc] = true;
        }
    }
}