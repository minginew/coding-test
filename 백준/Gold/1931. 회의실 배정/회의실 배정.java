import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static int N;
    static List<int[]> meetings;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        meetings = new ArrayList<>();

        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            int st = Integer.parseInt(input[0]);
            int ed = Integer.parseInt(input[1]);
            meetings.add(new int[] {st,ed});
        }
        meetings.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[1], o2[1]);
            }
        });

        int count = 0;
        int endTime = 0;
        for(int i = 0; i < N; i++) {
            if(endTime <= meetings.get(i)[0]) {
                endTime = meetings.get(i)[1];
                count++;
            }
        }
        System.out.println(count);
    }
}
