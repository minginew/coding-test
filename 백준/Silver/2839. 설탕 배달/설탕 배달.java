import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int five = N/5;
        int three = 1;
        if(five*5 == N) {
            System.out.println(five);
            return;
        }
        while (five>=0){
            int sum = five*5 + three*3;
            if(sum == N){
                System.out.println(five + three);
                return;
            }else if(sum<N){
                three++;
            } else if (sum>N) {
                five--;
                three = 1;
            }
        }
        System.out.println(-1);
    }
}