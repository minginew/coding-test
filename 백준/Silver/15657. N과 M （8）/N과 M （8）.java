import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] in1 = br.readLine().split(" ");
		String[] in2 = br.readLine().split(" ");
		N = Integer.parseInt(in1[0]);
		M = Integer.parseInt(in1[1]);
		int[] arr = new int[N];
		int[] out = new int[N];
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(in2[i]);
		Arrays.sort(arr);
		back(arr, out, 0, 0, sb);
		System.out.println(sb.toString());
		
	}
	static void back(int[] arr, int[] out, int idx, int max, StringBuilder sb) {
		if(idx == M) {
			for(int i=0; i<M; i++) {
				sb.append(out[i]).append(" ");
			}
			sb.append("\n");
			return;
		}else if(idx>M) return;
		
		for(int i=0; i<N; i++) {
			if(arr[i]>=max) {
				out[idx] = arr[i];
				back(arr, out, idx+1, arr[i], sb);
			}
		}
	}
	
}