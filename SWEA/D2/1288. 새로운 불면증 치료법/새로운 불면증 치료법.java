import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            int N = Integer.parseInt(br.readLine());
            Set<String> set = new HashSet<>();

            int ans=0;
            int x = 1; //N에 x배
            while(set.size()<10){
                ans = N*x;
                String[] num = String.valueOf(N*x++).split("");
                set.addAll(Arrays.asList(num));
            }
            sb.append("#").append(t).append(" ").append(ans).append('\n');
        }
        System.out.println(sb.toString());
    }
}