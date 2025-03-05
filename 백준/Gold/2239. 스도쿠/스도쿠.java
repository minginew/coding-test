import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] square;
    static boolean[][] rows;
    static boolean[][] cols;
    static int[][] map;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        square = new boolean[9][10];
        rows = new boolean[9][10];
        cols = new boolean[9][10];
        map = new int[9][9];
        for (int r=0; r<9; r++){
            String[] col = br.readLine().split("");
            for(int c=0; c<9; c++){
                int num = Integer.parseInt(col[c]);
                if(num == 0) continue;
                map[r][c] = num;
                rows[r][num] = true;
                cols[c][num] = true;
                square[(r/3)*3+(c/3)][num] = true;
            }
        }
        dfs(0,0);
    }
    public static void dfs(int r, int c){
        if (map[r][c] != 0){
            if(c<8) c+=1;
            else if(r<8){
                r+=1;
                c=0;
            }else {
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<9; i++){
                    for(int j=0; j<9; j++){
                        sb.append(map[i][j]);
                    }
                    sb.append('\n');
                }
                System.out.println(sb);
                System.exit(0);
            }
            dfs(r,c);
        }else{
            for(int num=1; num<=9; num++){
                if(rows[r][num] || cols[c][num] || square[(r/3)*3+(c/3)][num]) continue;
                rows[r][num] = true;
                cols[c][num] = true;
                square[(r/3)*3+(c/3)][num] = true;
                map[r][c] = num;
                dfs(r,c);
                rows[r][num] = false;
                cols[c][num] = false;
                square[(r/3)*3+(c/3)][num] = false;
                map[r][c] = 0;
            }
        }
    }
}