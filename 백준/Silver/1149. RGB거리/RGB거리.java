import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][3];

        for(int r=0; r<N; r++) {
            String[] input = br.readLine().split(" ");
            int R = Integer.parseInt(input[0]);
            int G = Integer.parseInt(input[1]);
            int B = Integer.parseInt(input[2]);

            if(r==0){
                dp[0][0] = R;
                dp[0][1] = G;
                dp[0][2] = B;
                continue;
            }
            for (int c = 0; c < 3; c++) {
                if (c == 0) {
                    dp[r][0] = Math.min(dp[r-1][1],dp[r-1][2]) + R;
                } else if (c == 1) {
                    dp[r][1] = Math.min(dp[r-1][0],dp[r-1][2]) + G;
                } else if (c == 2) {
                    dp[r][2] = Math.min(dp[r-1][0],dp[r-1][1]) + B;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i=0; i<3; i++){
                if(dp[N-1][i]<min) min = dp[N-1][i];
        }
        System.out.println(min);
    }
}