import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int[][] map;
    static int pictureSize=0;
    static int count=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] size = br.readLine().split(" ");
        int N = Integer.parseInt(size[0]);
        int M = Integer.parseInt(size[1]);
        map = new int[N][M];

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split(" ");
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(row[c]);
            }
        }
        Queue<int[]> queue = new LinkedList<>();

        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(map[r][c] == 0) continue;
                map[r][c] = 0;
                bfs(r,c,N,M,queue);
            }
        }
        System.out.println(count);
        System.out.println(pictureSize);
    }
    static void bfs(int R, int C, int N, int M, Queue<int[]> queue) {
        int size = 0;
        queue.offer(new int[]{R, C});
        while (!queue.isEmpty()) {
            int r = queue.peek()[0];
            int c = queue.poll()[1];
            size++;
            for (int d=0; d<4; d++){
                if(r+dy[d]>=0 && r+dy[d]<N && c+dx[d]>=0 && c+dx[d]<M){
                    if(map[r+dy[d]][c+dx[d]]==1){
                        map[r+dy[d]][c+dx[d]]=0;
                        queue.offer(new int[] {r+dy[d],c+dx[d]});
                    }
                }
            }
        }
        count++;
        if(pictureSize < size) pictureSize = size;
    }
}