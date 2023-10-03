import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int K = Integer.parseInt(in[1]);

        int[] dp = new int[K+1];
        dp[0] = 1;

        int[] coin = new int[N];
        for(int i=0; i<N; i++){
            String c = br.readLine();
            coin[i] = Integer.parseInt(c);
        }//초인 코기화

        for(int i=0; i<N; i++){ //코인을 순차적으로 사용하면서 dp에 새로운 값을 누적 시킴
            for(int j=1; j<=K; j++){ //j는 동전의 합
                if(j-coin[i] >= 0) dp[j] = dp[j-coin[i]] + dp[j];
            }
        }
        System.out.println(dp[K]);
    }
}