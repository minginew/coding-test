import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            Stack<Integer> st = new Stack<>();
            int N = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            for(int i=0; i<N; i++){
                st.push(Integer.parseInt(input[i]));
            }
            int max = -1;
            long sum = 0;
            while (!st.isEmpty()){
                if(max==-1){
                    max = st.pop();
                    continue;
                }
                int top = st.pop();
                if(top < max){
                    sum += max-top;
                } else if (top > max){
                    max = top;
                }
            }
            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.println(sb.toString());
    }
}