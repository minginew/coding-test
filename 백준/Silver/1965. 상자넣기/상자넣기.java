import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] size = br.readLine().split(" ");
        int[] box = new int[N];
        int[] dp = new int[N];

        for(int i=0; i<N; i++) box[i] = Integer.parseInt(size[i]);

        Arrays.fill(dp,1);
        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){
                if(box[j]<box[i]) {
                    int newBox = dp[j] + 1;
                    if(dp[i]<=newBox) dp[i] = newBox;
                }
            }
        }

        int max = 0;
        for(int i=0; i<N; i++){
            if(max < dp[i]) max = dp[i];
        }
        System.out.println(max);
    }
}