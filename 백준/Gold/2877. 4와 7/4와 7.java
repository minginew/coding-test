import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static List<Integer> bits = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        toBit(N+1);
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<bits.size(); i++){
            sb.append(bits.get(i)==0? "4" : "7");
        }
        System.out.println(sb);
    }

    public static void toBit(int n){
        if (n==1 || n==0){
            bits.add(n);
            return;
        }
        toBit(n/2);
        bits.add(n%2);
    }
}
