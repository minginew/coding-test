import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int L,R,C;
    static String[][][] map;
    static boolean[][][] visit;

    static int[] dr = {-1,1,0,0,0,0};

    static int[] dc = {0,0,-1,1,0,0};

    static int[] dl = {0,0,0,0,-1,1};
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            String input = br.readLine();
            if(input.equals("0 0 0")) {
                break;
            }
            String[] inputArr = input.split(" ");
            L = Integer.parseInt(inputArr[0]);
            R = Integer.parseInt(inputArr[1]);
            C = Integer.parseInt(inputArr[2]);
            map = new String[R][C][L];
            visit = new boolean[R][C][L];

            Queue<int[]> q = new LinkedList<>();
            for(int l=0; l<L; l++){
                for(int r=0; r<R; r++){
                    String[] row = br.readLine().split("");
                    for(int c=0; c<C; c++){
                        map[r][c][l] = row[c];
                        if(row[c].equals("S")){
                            q.offer(new int[] {r,c,l});
                            visit[r][c][l] = true;
                        }
                    }
                }
                // 공백제거
                br.readLine();
            }
            bfs(q);
        }
        System.out.println(sb.toString());
    }

    static void bfs(Queue<int[]> q){
        int time = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i< size; i++) {
                int r = q.peek()[0];
                int c = q.peek()[1];
                int l = q.poll()[2];
                
                if (map[r][c][l].equals("E")) {
                    sb.append("Escaped in ")
                            .append(time)
                            .append(" minute(s).")
                            .append('\n');
                    return;
                }
                for (int d = 0; d < 6; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    int nl = l + dl[d];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C || nl < 0 || nl >= L || visit[nr][nc][nl]) continue;
                    if (map[nr][nc][nl].equals(".") || map[nr][nc][nl].equals("E")) {
                        q.offer(new int[]{nr, nc, nl});
                        visit[nr][nc][nl] = true;
                    }
                }
            }
            time++;
        }
        sb.append("Trapped!")
                .append('\n');
    }
}