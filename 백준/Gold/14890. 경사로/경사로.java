import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,L;
    static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        map = new int[N][N];
        for(int r=0; r<N; r++){
            String[] col = br.readLine().split(" ");
            for(int c=0; c<N; c++){
                int n = Integer.parseInt(col[c]);
                map[r][c] = n;
            }
        }

        int count = 0;
        for(int r=0; r<N; r++){
            boolean[] visit = new boolean[N];
            for(int c=0; c<N; c++){
                if(c==N-1) {
                    count++;
                    break;
                }
                if(map[r][c]-map[r][c+1] == 1){
                    if(c+L >= N) break;
                    int h = map[r][c+1];
                    boolean possible = true;
                    for(int i=1; i<=L; i++){
                        if(visit[c+i]){
                            possible = false;
                            break;
                        }
                        visit[c+i] = true;
                        if(h != map[r][c+i]){
                            possible = false;
                            break;
                        }
                    }
                    if(possible){
                        c = c+L-1;
                    }else break;
                } else if (map[r][c]-map[r][c+1] == -1) {
                    if(c-(L-1) < 0) break;
                    int h = map[r][c];
                    boolean possible = true;
                    for(int i=0; i<L; i++){
                        if(visit[c-i]){
                            possible = false;
                            break;
                        }
                        visit[c-i] = true;
                        if(h != map[r][c-i]){
                            possible = false;
                            break;
                        }
                    }
                    if(!possible) break;
                } else if (Math.abs(map[r][c]-map[r][c+1]) > 1) {
                    break;
                }
            }
        }

        for(int c=0; c<N; c++){
            boolean[] visit = new boolean[N];
            for(int r=0; r<N; r++){
                if(r==N-1) {
                    count++;
                    break;
                } else if(map[r][c]-map[r+1][c] == 1){
                    if(r+L >= N) break;
                    int h = map[r+1][c];
                    boolean possible = true;
                    for(int i=1; i<=L; i++){
                        if(visit[r+i]){
                            possible = false;
                            break;
                        }
                        visit[r+i] = true;
                        if(h != map[r+i][c]){
                            possible = false;
                            break;
                        }
                    }
                    if(possible) {
                        r=r+L-1;
                    }else break;
                } else if (map[r][c]-map[r+1][c] == -1) {
                    if(r-(L-1) < 0) break;
                    int h = map[r][c];
                    boolean possible = true;
                    for(int i=0; i<L; i++){
                        if (visit[r-i]){
                            possible = false;
                            break;
                        }
                        visit[r-i] = true;
                        if(h != map[r-i][c]){
                            possible = false;
                            break;
                        }
                    }
                    if(!possible) break;
                } else if (Math.abs(map[r][c]-map[r+1][c]) > 1) {
                    break;
                }
            }
        }
        System.out.println(count);
    }
}