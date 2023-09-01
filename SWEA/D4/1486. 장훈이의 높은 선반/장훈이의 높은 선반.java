import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int N;
	static int H;
	static int[] height;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] in1 = br.readLine().split(" ");
			N = Integer.parseInt(in1[0]);
			H = Integer.parseInt(in1[1]);
			min = Integer.MAX_VALUE;
			height = new int[N];
			String[] in2 = br.readLine().split(" ");
			for(int i=0; i<N; i++) {
				height[i] = Integer.parseInt(in2[i]);
			}
			combi(0,0);
			sb.append("#").append(t).append(" ").append(min-H).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void combi(int idx, int h) {
		if(idx == N) {
			if(H<=h && min>h) {
				min=h;
			}
			return;
		}
		combi(idx+1, h);
		combi(idx+1, h+height[idx]);
	}
}