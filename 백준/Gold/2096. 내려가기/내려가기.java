import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[2][3][2]; //0번은 최대값 기록, 1번은 최소값 기록
        
        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            
            if(r==0){
                for(int k=0; k<2; k++){
                    dp[0][0][k] = L;
                    dp[0][1][k] = M;
                    dp[0][2][k] = R;

                    dp[1][0][k] = L;
                    dp[1][1][k] = M;
                    dp[1][2][k] = R;
                }
                continue;
            }
            for(int c=0; c<3; c++){
                if(c==0){
                    dp[1][0][0] = Math.max(dp[0][c][0], dp[0][c+1][0]) + L;
                    dp[1][0][1] = Math.min(dp[0][c][1], dp[0][c+1][1]) + L;
                }else if(c==1){
                    dp[1][1][0] = Math.max(dp[0][c][0],Math.max(dp[0][c-1][0], dp[0][c+1][0])) + M;
                    dp[1][1][1] = Math.min(dp[0][c][1],Math.min(dp[0][c-1][1], dp[0][c+1][1])) + M;
                } else {// c==2
                    dp[1][2][0] = Math.max(dp[0][c-1][0], dp[0][c][0]) + R;
                    dp[1][2][1] = Math.min(dp[0][c-1][1], dp[0][c][1]) + R;
                }
            }
            for(int c=0; c<3; c++){
                dp[0][c][0] = dp[1][c][0];
                dp[0][c][1] = dp[1][c][1];
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            if(dp[1][i][0] > max) max = dp[1][i][0];
            if(dp[1][i][1] < min) min = dp[1][i][1];
        }
        System.out.println(max + " " + min);
    }
}