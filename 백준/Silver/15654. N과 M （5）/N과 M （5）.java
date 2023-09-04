import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] in1 = br.readLine().split(" ");
		String[] in2 = br.readLine().split(" ");
		N = Integer.parseInt(in1[0]);
		M = Integer.parseInt(in1[1]);
		int[] arr = new int[N];
		int[] per = new int[M];
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(in2[i]);
		Arrays.sort(arr);
		boolean[] visit = new boolean[arr[arr.length-1]+1];
		perm(arr, per, visit, 0, sb);
		System.out.println(sb.toString());
		
	}
	static void perm(int[] arr, int[] per, boolean[] visit, int idx, StringBuilder sb) {
		if(idx==M) {
			for(int i=0; i<M; i++) {
				sb.append(per[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int n : arr) {
			if(visit[n]) continue;
			per[idx]=n;
			visit[n] = true;
			perm(arr, per, visit, idx+1, sb);
			visit[n] = false;
		}
	}
}