import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int count0,count1;
    static int[] dp0 = new int[41];
    static int[] dp1 = new int[41];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Arrays.fill(dp0, -1);
        Arrays.fill(dp1, -1);

        dp0[0] = 1;
        dp0[1] = 0;
        dp1[0] = 0;
        dp1[1] = 1;
        for(int t=0; t<T; t++){
            count0 = 0;
            count1 = 0;
            int n = Integer.parseInt(br.readLine());

            for(int i=2; i<=n; i++){
                dp0[i] = dp0[i-1] + dp0[i-2];
                dp1[i] = dp1[i-1] + dp1[i-2];
            }
            System.out.println(dp0[n]+" "+dp1[n]);
        }
    }

}