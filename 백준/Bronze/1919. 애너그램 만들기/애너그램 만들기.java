import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split("");
        String[] second = br.readLine().split("");
        int count = 0; //중복된 개수
        for(int i=0; i<first.length; i++){
            for(int j=0; j<second.length; j++){
                if(first[i].equals(second[j])){
                    second[j] = "#";
                    count++;
                    break;
                }
            }
        }
        System.out.println(first.length+second.length - (2*count));
    }
}