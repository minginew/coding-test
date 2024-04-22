import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    static int N,M,score,block;
    static int[] dr = {-1,1,0, 0};
    static int[] dc = {0,0,-1, 1};
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][N];
        visit = new boolean[N][N];

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split(" ");
            for (int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(row[c]);
                if(map[r][c] > 0) block++;
            }
        }

        while (true){
            int maxBlock = Integer.MIN_VALUE;
            int maxRainbow = Integer.MIN_VALUE;
            int maxRow = Integer.MIN_VALUE;
            int maxCol = Integer.MIN_VALUE;
            int[] distroy = new int[2];
            visit = new boolean[N][N];

            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    if(visit[r][c] || map[r][c] < 1 || map[r][c] > M) continue;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[] {r,c});
                    visit[r][c] = true;

                    int[] result = bfs(q, map[r][c]);
                    int count = result[0];
                    int rainbow = result[1];

                    if(count > maxBlock){
                        maxBlock = count;
                        maxRainbow = rainbow;
                        maxRow = r;
                        maxCol = c;
                        distroy[0] = r;
                        distroy[1] = c;
                    } else if (count == maxBlock && rainbow > maxRainbow)  {
                        maxRainbow = rainbow;
                        maxRow = r;
                        maxCol = c;
                        distroy[0] = r;
                        distroy[1] = c;
                    } else if (count == maxBlock && rainbow == maxRainbow && r > maxRow) {
                        maxRow = r;
                        maxCol = c;
                        distroy[0] = r;
                        distroy[1] = c;
                    } else if (count == maxBlock && rainbow == maxRainbow && r == maxRow && c > maxCol) {
                        maxCol = c;
                        distroy[0] = r;
                        distroy[1] = c;
                    }
                }
            }

            if (maxBlock < 2) break;
            int r = distroy[0];
            int c = distroy[1];

            visit = new boolean[N][N];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {r,c});
            visit[r][c] = true;
            score += (int)Math.pow(destroyBfs(q, map[r][c]),2);

            gravity();
            turn();
            gravity();
        }
        System.out.println(score);
    }

    static int[] bfs(Queue<int[]> q, int target){
        int count = 0;
        int rainbow = 0;
        Queue<int[]> zero = new LinkedList<>();
        while (!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            count++;
            if(map[r][c] == 0) rainbow++;

            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr<0 || nr>=N || nc<0 || nc>=N || visit[nr][nc]) continue;
                if(map[nr][nc] == target || map[nr][nc] == 0) {
                    if(map[nr][nc] == 0) zero.offer(new int[] {nr,nc});
                    q.offer(new int[] {nr,nc});
                    visit[nr][nc] = true;
                }
            }
        }
        while (!zero.isEmpty()){
            int r = zero.peek()[0];
            int c = zero.poll()[1];
            visit[r][c] = false;
        }

        return new int[] {count, rainbow};
    }

    static int destroyBfs(Queue<int[]> q, int target){
        int count = 0;
        while (!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            map[r][c] = M+1;
            count++;
            block--;
            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr<0 || nr>=N || nc<0 || nc>=N || visit[nr][nc]) continue;
                if(map[nr][nc] == target || map[nr][nc] == 0) {
                    q.offer(new int[] {nr,nc});
                    visit[nr][nc] = true;
                }
            }
        }
        return count;
    }

    static void gravity(){
        for(int c=0; c<N; c++){
            for(int r=N-1; r>=0; r--){
                if(map[r][c] == M+1 || map[r][c] == -1) continue;

                int nr = r;
                for(int k=r+1; k<N; k++){
                    if(map[k][c] != M+1) break;
                    else {
                        nr = k;
                    }
                }
                if(nr == r) continue;
                map[nr][c] = map[r][c];
                map[r][c] = M+1;
            }
        }
    }

    static void turn(){
        // 반시계로 돌린다면 map(r,c) == newMap(N-1-c, r)
        int[][] newMap = new int[N][N];

        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                newMap[N-1-c][r] = map[r][c];
            }
        }
        map = newMap;
    }
}