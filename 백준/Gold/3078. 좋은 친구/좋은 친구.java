import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,K;
    static int p1,p2;
    static int[] nameLen, goodFriend;
    static String[] students;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        p1 = 0;
        p2 = K;

        goodFriend = new int[21];
        nameLen = new int[N];
        students = new String[N];
        for(int i=0; i<N; i++){
            students[i] = br.readLine();
            nameLen[i] = students[i].length();
            if(i <= K) goodFriend[nameLen[i]]++;
        }

        long count = 0;
        while (p1 < p2){
            int num = goodFriend[nameLen[p1]];
            count += num-1;
            goodFriend[nameLen[p1]]--;
            p1++;
            if(p2 != N-1){
                p2++;
                goodFriend[nameLen[p2]]++;
            }
        }
        System.out.println(count);
    }
}