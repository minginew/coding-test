import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] S = br.readLine().split("");
        int zero =0;
        int one = 0;
        for(int i=0; i<S.length; i++){
            if(S[i].equals("0")) zero++;
            else one++;
        }

        zero /= 2;
        one /= 2;
        for(int i=0; i<S.length; i++){
            if(one > 0 && S[i].equals("1")){
                one--;
                S[i] = "";
            }else if(zero > 0 && S[i].equals("0")){
                zero--;
            }else if(zero == 0 && S[i].equals("0")){
                S[i] = "";
            }
        }
        System.out.println(String.join("",S));
    }
}
