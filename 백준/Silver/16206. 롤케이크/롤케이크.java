import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static int N,M;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String[] input2 = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        for(int i=0; i<N; i++){
            int num = Integer.parseInt(input2[i]);
            list.add(num);
        }

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1%10 == 0 && o2%10 == 0){
                    return Integer.compare(o1, o2);
                }else if(o1%10 == 0){
                    return -1;
                }else if(o2%10 == 0){
                    return 1;
                }
                return 0;
            }
        });

        int cakeNum = 0;
        for(int i=0; i<N; i++){
            int len = list.get(i);
            if(len == 10){
                cakeNum++;
                continue;
            }

            int cuttingCount = (len/10)-1;
            int cake = len/10;
            if(len%10 != 0) cuttingCount++;
            if(cuttingCount <= M){
                cakeNum += cake;
                M -= cuttingCount;
            }else{
                cakeNum += M;
                M = 0;
            }
        }
        System.out.println(cakeNum);
    }
}
