import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int N;
    static int count1; //일반인
    static int count2; //적록색약
    static String[][] map1; // 일반인이 보는 그림
    static String[][] map2; // 적록색약이 보는 그림

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map1 = new String[N][N]; // 일반인이 보는 그림
        map2 = new String[N][N]; // 적록색약이 보는 그림

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split("");
            for (int c=0; c<N; c++){
                map1[r][c] = row[c];
                if(row[c].equals("R")||row[c].equals("G")){//R,G이면 적록색약일 경우 C로 표현
                    map2[r][c] = "C";
                    continue;
                }
                map2[r][c] = row[c]; //파란색
            }
        }
        for(int r=0; r<N; r++){
            for (int c=0; c<N; c++){
                if(map1[r][c].equals("R")||map1[r][c].equals("G")||map1[r][c].equals("B")) count1 += bfs1(r,c);
                if(map2[r][c].equals("C")||map2[r][c].equals("B")) count2 += bfs2(r,c);
            }
        }
        sb.append(count1).append(" ").append(count2);
        System.out.println(sb.toString());

    }

    static int bfs1(int R, int C) { //일반인 bfs R,G,B로 구성
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {R,C});
        String target = map1[R][C];
        while (!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];
            for (int d=0; d<4; d++){
                int nr = r+dy[d];
                int nc = c+dx[d];
                if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                if(!map1[nr][nc].equals(target)) continue;
                q.offer(new int[] {nr,nc});
                map1[nr][nc]="#";
            }
        }
        return 1;
    }
    static int bfs2(int R, int C) { //적록색약 bfs C,B로만 구성
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {R,C});
        String target = map2[R][C];
        while (!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];
            for (int d=0; d<4; d++){
                int nr = r+dy[d];
                int nc = c+dx[d];
                if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                if(!map2[nr][nc].equals(target)) continue;
                q.offer(new int[] {nr,nc});
                map2[nr][nc]="#";
            }
        }
        return 1;
    }
}