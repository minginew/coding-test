import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,Num,minVirus;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static boolean[][] visit;
    static List<int[]> zeroList = new LinkedList<>();
    static List<int[]> startList = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        minVirus = Integer.MAX_VALUE;
        map = new int[N][M];

        for(int r=0; r<N; r++) {
            String[] row = br.readLine().split(" ");
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(row[c]);
                if(map[r][c] == 0){
                    zeroList.add(new int[] {r,c});
                    Num++;
                } else if(map[r][c] == 2){ //바이러스 방문체크
                    startList.add(new int[] {r,c});
                    Num++;
                }
            }
        }
        List<int[]> out = new ArrayList<>();
        com(0,0, out);
        System.out.println(Num - minVirus-3);

    }

    static void com(int idx, int start, List<int[]> out) {
        if(idx == 3){
            Queue<int[]> q = new LinkedList<>();
            for(int[] n : startList) q.offer(n);

            visit = new boolean[N][M];
            for(int i=0; i<out.size(); i++) visit[out.get(i)[0]][out.get(i)[1]] = true;
            bfs(q);
            return;
        }
        for(int i=start; i<zeroList.size(); i++){
            out.add(zeroList.get(i));
            com(idx+1, start+1, out);
            out.remove(out.size()-1);
        }

    }

    static void bfs(Queue<int[]> q){
        int count = 0;
        while (!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];
            count++;

            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];
                if(nr<0 || nc<0 || nr>=N || nc>=M || visit[nr][nc] || map[nr][nc]!=0) continue;

                q.offer(new int[] {nr,nc});
                visit[nr][nc] = true;
            }
        }
        if(minVirus > count) minVirus = count;
    }
}