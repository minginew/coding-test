import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int N,H,answer;
    static int[] height; //null 값 들어가있는 상태
    static boolean[] visit;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]); //째간이들 수
            H = Integer.parseInt(input[1]); //선반 높이
            height = new int[N];
            visit = new boolean[N];
            answer = Integer.MAX_VALUE;
            String[] in = br.readLine().split(" ");
            for(int i=0; i<N; i++){//째깐이들 키 초기화
                height[i] = Integer.parseInt(in[i]);
            }


            //부분집합을 지정 -> 뽑은 놈들의 키를 합함 -> H를 넘지 않으면 컷 -> 넘으면 저장 그중 작은놈들 -> 다끝나면
            // 그값에서 - H
            subSet(0,0);
            sb.append("#").append(t).append(" ").append(answer-H).append("\n");
        }
        System.out.println(sb);
    }
    public static void subSet(int idx, int sum){
        if(idx == N){
            if(sum < H) return;
            if(answer>sum) answer = sum;
            return;
        }
        subSet(idx+1, sum);
        visit[idx] = true;
        subSet(idx+1, sum+height[idx]);

    }
}