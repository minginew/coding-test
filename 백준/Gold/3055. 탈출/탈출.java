import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int R,C,total;
    static Queue<int[]> hog = new LinkedList<>();
    static Queue<int[]> water = new LinkedList<>();
    static char[][] map;
    static boolean[][] visit1, visit2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        map = new char[R][C];
        visit1 = new boolean[R][C];
        visit2 = new boolean[R][C];

        for(int r=0; r<R; r++){
            String[] row = br.readLine().split("");
            for(int c=0; c<C; c++){
                map[r][c] = row[c].charAt(0);
                if(map[r][c] == 'S') {
                    hog.offer(new int[]{r, c});
                    visit2[r][c] = true;
                }
                else if (map[r][c] == '*') {
                    water.offer(new int[]{r, c});
                    visit1[r][c] = true;
                }
            }
        }
        bfs();
        if(total == 0) System.out.println("KAKTUS");
        else System.out.println(total);
    }

    public static void bfs(){
        int waterSize = water.size();
        int hogSize = hog.size();
        int move = 0;
        while (!hog.isEmpty()){
            for(int i=0; i<waterSize; i++){
                int r = water.peek()[0];
                int c = water.poll()[1];

                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr<0 || nr>=R || nc<0 || nc>=C || visit1[nr][nc] || map[nr][nc] == 'X' || map[nr][nc] == 'D') continue;
                    water.offer(new int[] {nr,nc});
                    visit1[nr][nc] = true;
                    map[nr][nc] = '*';
                }
            }

            for(int i=0; i<hogSize; i++){
                int r = hog.peek()[0];
                int c = hog.poll()[1];

                if(map[r][c] == 'D'){
                    total = move;
                    return;
                }

                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr<0 || nr>=R || nc<0 || nc>=C || visit2[nr][nc] || map[nr][nc] == 'X' || map[nr][nc] == '*') continue;
                    hog.offer(new int[] {nr,nc});
                    visit2[nr][nc] = true;
                }
            }
            waterSize = water.size();
            hogSize = hog.size();
            move++;
        }
    }
}
