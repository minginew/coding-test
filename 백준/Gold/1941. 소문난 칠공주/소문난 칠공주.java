import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] map = new String[5][5];
        List<int[]> student = new LinkedList<>();
        for(int r=0; r<5; r++){
            String[] row = br.readLine().split("");
            for(int c=0; c<5; c++){
                map[r][c] = row[c];
                student.add(new int[] {r,c});
            }
        }

        boolean[] visit = new boolean[25];
        com(map, visit, student,0, 0, 0);
        System.out.println(count);


    }
    public static void com(String[][] map, boolean[] visit, List<int[]> student, int countS, int select, int idx){
        if(select==7){
            if (countS<4) return;
            boolean Setting = true; //q에 bfs를 할 시작좌표를 큐에 담는 시작좌표를 방문처리 하기위한 if문의 조건
            int[][] map1 = new int[5][5];
            Queue<int[]> q = new LinkedList<>();
            for(int i=0; i<25; i++){
                if(!visit[i]) continue;
                int r = student.get(i)[0];
                int c = student.get(i)[1];
                map1[r][c] = 1; //칠공주를 1로 표시한 맵이자 방문배열
                if (Setting){
                    q.offer(new int[] {r,c});
                    map1[r][c] = 0;
                    Setting = false;
                }
            }
            if(posible(q,map1)) count++;
            return;
        }
        if (idx==25) return;
        if(7-select < 4-countS) return;

        com(map, visit, student, countS, select, idx+1);
        visit[idx] = true;
        if(map[student.get(idx)[0]][student.get(idx)[1]].equals("Y")) com(map, visit, student, countS, select+1, idx+1);
        else com(map, visit, student, countS+1, select+1, idx+1);
        visit[idx] = false;
    }

    public static boolean posible(Queue<int[]> q, int[][] map){
        while (!q.isEmpty()){
                int r = q.peek()[0];
                int c = q.poll()[1];

                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr<0 || nc<0 || nr>=5 || nc >=5 || map[nr][nc]==0) continue;
                    q.offer(new int[] {nr,nc});
                    map[nr][nc] = 0;
                }
        }
        for (int r=0; r<5; r++){
            for(int c=0; c<5; c++){
                if(map[r][c]==1) return false;
            }
        }
        return true;
    }
}