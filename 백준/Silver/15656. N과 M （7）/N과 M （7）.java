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
		int[] out = new int[N];
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(in2[i]);
		Arrays.sort(arr);
		back(arr, out, 0, sb);
		System.out.println(sb.toString());
		
	}
	static void back(int[] arr, int[] out, int depth,StringBuilder sb) {
		if(depth==M) {
			for(int i=0; i<N; i++) {
				if(out[i] != 0) sb.append(out[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<arr.length; i++) {
			out[depth] = arr[i];
			back(arr, out, depth+1, sb);
		}
		
	}
}