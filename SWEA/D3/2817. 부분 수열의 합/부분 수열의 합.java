import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int N;
	static int K;
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] in = br.readLine().split(" ");
			N = Integer.parseInt(in[0]); //원소의 수
			K = Integer.parseInt(in[1]); //부분 수열의 합
			count=0;
			int[] seq = new int[N];
			boolean[] visit = new boolean[N];
			String[] seqIn = br.readLine().split(" ");
			for(int i=0; i<N; i++) seq[i] = Integer.parseInt(seqIn[i]);
			rec(seq, visit, 0, 0);
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void rec(int[] seq, boolean[] visit, int n, int S) {
		if(S>K) return;
		if(n==N) {
			int sum = 0;
			for(int i=0; i<N; i++){
				if(visit[i]) sum += seq[i];
			}
			if(sum==K) count++;
			return;
		}
		visit[n]=true;
		rec(seq, visit, n+1, S+seq[n]);
		visit[n]=false;
		rec(seq, visit, n+1, S);
	}
}