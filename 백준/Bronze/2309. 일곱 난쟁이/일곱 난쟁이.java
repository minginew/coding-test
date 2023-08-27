import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] hobbit = new int[9];
        int sum = 0;
        for(int i=0; i<hobbit.length; i++){
            hobbit[i] = Integer.parseInt(br.readLine());
            sum += hobbit[i];
        }
        Arrays.sort(hobbit);
        int left=0;
        int right=8;
        while (left<right){
            if(sum-(hobbit[left]+hobbit[right])<100) right--;
            else if (sum-(hobbit[left]+hobbit[right])>100) left++;
            else if (sum-(hobbit[left]+hobbit[right])==100) break;
        }
        for(int i=0; i<hobbit.length; i++){
            if(i==left || i==right) continue;
            sb.append(hobbit[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}