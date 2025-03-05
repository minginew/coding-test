import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,d,k,c;
    static int[] shisi,pick;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        c = Integer.parseInt(input[3]);
        shisi = new int[N];
        pick = new int[d+1];

        int st = 0;
        int ed = k-1;
        int count = 0;
        int maxCount = 0;
        for(int i=0; i<N; i++){
            shisi[i] = Integer.parseInt(br.readLine());
            if(i<k){
                if(pick[shisi[i]] != 0){
                    pick[shisi[i]]++;
                    continue;
                }
                pick[shisi[i]]++;
                count++;
                maxCount++;
            }
        }
        if(pick[c] == 0){
            count++;
            maxCount++;
        }
        pick[c]++;
        while (st<N){
            pick[shisi[st]]--;
            if(pick[shisi[st]] == 0) count--;
            pick[shisi[ed+1 == N ? 0 : ed+1]]++;
            if(pick[shisi[ed+1 == N ? 0 : ed+1]] == 1) count++;
            if(maxCount < count)  maxCount = count;
            st++;
            ed++;
            if(ed == N) ed = 0;
        }
        System.out.println(maxCount);
    }
}