import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int N,M,Q;
    static int iceCount, total;
    static int[] L;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        Q = Integer.parseInt(input[1]);
        M = (int)Math.pow(2, N);
        L = new int[Q];
        map = new int[M][M];
        visit = new boolean[M][M];

        for(int r=0; r<M; r++){
            String[] col = br.readLine().split(" ");
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(col[c]);
            }
        }

        String[] input2 = br.readLine().split(" ");
        for(int i=0; i<Q; i++){
            L[i] = Integer.parseInt(input2[i]);
        }

        for(int q=0; q<Q; q++){
            int l = (int)Math.pow(2,L[q]);
            if(l != 1){
                for(int r=0; r<M; r+=l){
                    for(int c=0; c<M; c+=l){
                        spin(r,c,r+l,c+l);
                    }
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            for(int r=0; r<M; r++){
                for(int c=0; c<M; c++){
                    if(map[r][c] == 0) continue;
                    int iceNum = 0;
                    for(int d=0; d<4; d++){
                        int nr = r+dr[d];
                        int nc = c+dc[d];
                        if(nr<0 || nc<0 || nr>=M || nc>=M || map[nr][nc] == 0) continue;
                        iceNum++;
                    }
                    if(iceNum < 3){
                        queue.offer(new int[] {r,c});
                    }
                }
            }

            while (!queue.isEmpty()){
                int r = queue.peek()[0];
                int c = queue.poll()[1];
                map[r][c]--;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for(int r=0; r<M; r++){
            for(int c=0; c<M; c++){
                total += map[r][c];
                if(map[r][c]==0 || visit[r][c]) continue;
                queue.offer(new int[] {r,c});
                visit[r][c] = true;
                bfs(queue);
            }
        }
        if(iceCount < 2){
            System.out.println(total);
            System.out.println(0);
        }else {
            System.out.println(total);
            System.out.println(iceCount);
        }
    }
    public static void spin (int Sr, int Sc, int Er, int Ec){
        int[][] newMap = new int[M][M];
        for(int r=Sr; r<Er; r++){
            for(int c=Sc; c<Ec; c++) {
                newMap[Sr+(c-Sc)][Ec-1-(r-Sr)] = map[r][c];
            }
        }
        for(int r=Sr; r<Er; r++){
            for(int c=Sc; c<Ec; c++) {
                map[r][c] = newMap[r][c];
            }
        }
    }

    public static void bfs(Queue<int[]> queue ){
        int count = 0;
        while (!queue.isEmpty()){
            int r = queue.peek()[0];
            int c = queue.poll()[1];
            count++;
            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];
                if(nr<0 || nc<0 || nr>=M || nc>=M || map[nr][nc] == 0 || visit[nr][nc]) continue;
                queue.offer(new int[] {nr,nc});
                visit[nr][nc] = true;
            }
        }
        if(count > iceCount){
            iceCount = count;
        }
    }
}
