import java.io.BufferedReader;
import java.io.InputStreamReader;;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N+1];
        int[] table = new int[N+1];
        for(int i=1; i<=N; i++) list[i] = Integer.parseInt(br.readLine());

        table[1] = list[1];
        if(N>=2) table[2] = list[1]+list[2];
        for(int i=3; i<=N; i++){
                if(i==3){
                    table[i] = Math.max(list[1],list[2]) + list[i];
                }else {
                    table[i] = Math.max(table[i-2],list[i-1]+table[i-3])+list[i];
                }
            }
        System.out.println(table[N]);
        }
}