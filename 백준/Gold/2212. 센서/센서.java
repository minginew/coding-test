import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Main {
    static int N,M;
    static int[] sensor;
    static List<Integer> diffList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        sensor = new int[N];
        diffList = new ArrayList<>();

        String[] input = br.readLine().split(" ");
        for(int i=0; i<N; i++) sensor[i] = Integer.parseInt(input[i]);
        Arrays.sort(sensor);

        // 센서간 간격 구하기
        for(int i=0; i<N-1; i++){
            int diff = sensor[i+1] - sensor[i];
            diffList.add(diff);
        }
        diffList.sort(Comparator.naturalOrder());

        int answer = 0;
        for(int i=0; i<N-M; i++){
            answer += diffList.get(i);
        }
        System.out.println(answer);
    }
}
