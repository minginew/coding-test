import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    static int N,totalScore;
    static int[][] map;
    static int[][] scoreArr;
    static  int[][] like;
    static int [] dr = {-1,1,0,0};
    static int [] dc = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        scoreArr = new int[N+1][N+1];
        like = new int[N*N+1][5];

        for(int i=0; i<N*N; i++){
            String[] input = br.readLine().split(" ");
            int me = Integer.parseInt(input[0]);
            int f1 = Integer.parseInt(input[1]);
            int f2 = Integer.parseInt(input[2]);
            int f3 = Integer.parseInt(input[3]);
            int f4 = Integer.parseInt(input[4]);

            like[i][0] = me;
            like[i][1] = f1;
            like[i][2] = f2;
            like[i][3] = f3;
            like[i][4] = f4;
        }

        for(int i=0; i<N*N; i++){
            int me = like[i][0];
            int maxBlank = -1;
            int maxFriend = -1;
            int[] position = new int[2];

            for(int r=1; r<=N; r++){
                for(int c=1; c<=N; c++){
                    if(map[r][c] != 0) continue;
                    int blank = 0;
                    int friend = 0;
                    for (int d=0; d<4; d++){
                        int nr = r+dr[d];
                        int nc = c+dc[d];

                        if(nr==0 || nr>N || nc==0 || nc>N) continue;
                        if(map[nr][nc] == 0) blank++;
                        else{
                            for(int k=1; k<=4; k++){
                                if(map[nr][nc] == like[i][k]) friend++;
                            }
                        }
                    }
                    if(maxFriend < friend){
                        maxFriend = friend;
                        maxBlank = blank;
                        position[0] = r;
                        position[1] = c;
                    }else if(maxFriend == friend && maxBlank < blank){
                        maxBlank = blank;
                        position[0] = r;
                        position[1] = c;
                    }
                }
            }

            map[position[0]][position[1]] = me;
        }


        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                int me = map[r][c];
                for(int i=0; i<N*N; i++){
                    if(like[i][0] != me) continue;
                    int friend = 0;
                    for (int d=0; d<4; d++){
                        int nr = r+dr[d];
                        int nc = c+dc[d];

                        if(nr==0 || nr>N || nc==0 || nc>N) continue;

                        for(int k=1; k<=4; k++){
                            if(map[nr][nc] == like[i][k]) {
                                friend++;
                                break;
                            }
                        }
                    }
                    totalScore += (int)Math.pow(10, friend)/10;
                    break;
                }
            }
        }
        System.out.println(totalScore);
    }
}