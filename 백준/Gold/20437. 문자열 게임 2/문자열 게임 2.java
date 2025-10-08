import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, K;
    static char[] strArr;
    static List<Integer>[] countArr = new ArrayList[26];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            strArr = br.readLine().toCharArray();
            K = Integer.parseInt(br.readLine());

            for(int i=0; i<26; i++) countArr[i] = new ArrayList<>();
            for(int i=0; i<strArr.length; i++){
                countArr[strArr[i] - 'a'].add(i);
            }

            int minLen = Integer.MAX_VALUE;
            int maxLen = 0;

            for(int i=0; i<26; i++){
                int left = 0;
                int right = K-1;
                if(countArr[i].size() >= K){
                    List<Integer> list = countArr[i];
                    while (right < list.size()){
                        int len = list.get(right)-list.get(left)+1;
                        if(len < minLen){
                            minLen = len;
                        }
                        if(len > maxLen){
                            maxLen = len;
                        }
                        left++;
                        right++;
                    }
                }
            }
            if(minLen == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(minLen + " " + maxLen);
        }
    }
}
