import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int N;
    static int M;
    static int C;
    static int[][] map;
    static int count;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            C = Integer.parseInt(input[2]); //배추 수
            map = new int[N][M];

            for(int i=0; i<C; i++) {
                String[] position = br.readLine().split(" ");
                int r = Integer.parseInt(position[0]);
                int c = Integer.parseInt(position[1]);
                map[r][c] = 1;
            }

            count=0;
            for(int r=0; r<N; r++) {
                for(int c=0; c<M; c++) {
                    if(map[r][c] != 1) continue;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[] {r,c});
                    map[r][c] = 0;
                    bfs(q);
                }
            }
            System.out.println(count);
        }
    }

    static void bfs(Queue<int[]> q) {
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int r = q.peek()[0];
                int c = q.poll()[1];
                for(int d=0; d<4; d++) {
                    int nr = r+dy[d];
                    int nc = c+dx[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]!=1) continue;
                    q.offer(new int[] {nr,nc});
                    map[nr][nc]=0;
                }
            }
        }
        count++;
    }
}