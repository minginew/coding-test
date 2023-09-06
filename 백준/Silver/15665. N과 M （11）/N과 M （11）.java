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
		int temp = arr[0];
		for(int i=1; i<N; i++) {
			if(temp==arr[i]) {
				temp = arr[i];
				arr[i] = -1;
				continue;
			}
			temp = arr[i];
		}
		back(0, arr, out, sb);
		System.out.println(sb.toString());

	}
	
	static void back(int idx, int[] arr, int[] out, StringBuilder sb) {
		if(idx==M) {
			for(int i=0; i<M; i++) {
				sb.append(out[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<N; i++) {
			if(arr[i]==-1) continue;
			out[idx] = arr[i];
			back(idx+1, arr, out, sb);
		}
		
	}
	
}