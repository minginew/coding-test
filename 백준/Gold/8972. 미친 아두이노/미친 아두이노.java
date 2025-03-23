import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int[] dr = {0,1,1,1,0,0,0,-1,-1,-1};
    static int[] dc = {0,-1,0,1,-1,0,1,-1,0,1};

    static int N,M;
    static char[][] map;
    static int[] move;
    static int[] jongsu;
    static List<int[]> aduino = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        move = new int[101];

        for(int r=0; r<N; r++){
            String[] input2 = br.readLine().split("");
            for(int c=0; c<M; c++){
                map[r][c] = input2[c].charAt(0);
                if (map[r][c] == 'R') {
                    aduino.add(new int[] {r,c});
                }else if(map[r][c] == 'I'){
                    jongsu = new int[] {r,c};
                }
            }
        }

        String[] input3 = br.readLine().split("");
        for(int i=0; i<input3.length; i++) move[i] = Integer.parseInt(input3[i]);

        int R = jongsu[0];
        int C = jongsu[1];
        for(int i=0; i<move.length; i++){
            if(move[i] == 0) break;
            int dir = move[i];
            char[][] newMap = new char[N][M];
            for(int j=0; j<N; j++) Arrays.fill(newMap[j], '.');

            R += dr[dir];
            C += dc[dir];
            newMap[R][C] = 'I';
            if(map[R][C] == 'R') {
                System.out.println("kraj"+ " " + (i+1));
                return;
            }
            for(int j=0; j<aduino.size(); j++){
                int[] curr = aduino.get(j);
                int r = curr[0];
                int c = curr[1];
                int minLen = Integer.MAX_VALUE;
                int[] next = new int[2];

                for(int d=1; d<10; d++){
                    if(d==5) continue;
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    int len = Math.abs(R-nr) + Math.abs(C-nc);
                    if(minLen > len){
                        minLen = len;
                        next[0] = nr;
                        next[1] = nc;
                    }
                }

                if(newMap[next[0]][next[1]] == '.'){
                    newMap[next[0]][next[1]] = 'R';
                }else if(newMap[next[0]][next[1]] == 'R'){
                    newMap[next[0]][next[1]] = 'E';
                }else if(newMap[next[0]][next[1]] == 'I'){
                    System.out.println("kraj"+ " " + (i+1));
                    return;
                }
            }

            aduino.clear();
            for(int r=0; r<N; r++){
                for(int c=0; c<M; c++){
                    if(newMap[r][c] == 'E') newMap[r][c] = '.';
                    else if(newMap[r][c] == 'R') aduino.add(new int[]{r,c});
                }
            }
            map = newMap;
        }
        StringBuilder sb = new StringBuilder();
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
