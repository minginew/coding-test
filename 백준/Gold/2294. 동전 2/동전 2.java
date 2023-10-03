import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int K = Integer.parseInt(in[1]);
        int[] dp = new int[K+1];
        int[] coin = new int[N];

        for(int i=0; i<N; i++){
            String c = br.readLine();
            coin[i] = Integer.parseInt(c);
        }

        for(int i=N-1; i>=0; i--){
            for(int j=1; j<=K; j++){ //j는 동전의 합
                if(j<coin[i]) continue;
                if(j%coin[i] == 0){
                    if(dp[j]==0) dp[j] = j/coin[i]; //j를 만들기위해 해당 코인을 사용한 횟수 (딱 나누어 떨어져야 함)
                    else dp[j] = Math.min(dp[j],j/coin[i]); //j를 만들 때, 이미 값이 들어가 있다면 더 작은 값을 택한다.
                }
                if(dp[j-coin[i]] != 0){
                    if(dp[j]==0) dp[j] = dp[j-coin[i]]+1; //나누어 떨어지지 않는 경우, 해당 동전을 1개 사용
                    else dp[j] = Math.min(dp[j],dp[j-coin[i]]+1);
                }
            }
        }
        if(dp[K]==0){
            System.out.println(-1);
            return;
        }
        System.out.println(dp[K]);
    }
}