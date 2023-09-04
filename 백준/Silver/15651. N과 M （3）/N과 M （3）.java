import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]); 
		M = Integer.parseInt(in[1]); // 부분집합의 크기
		int[] arr = new int[N];
		back(0, arr, sb);
		System.out.println(sb.toString());
	}
	static void back(int idx, int[] arr, StringBuilder sb) {
		if(idx == M) {
			for(int i=0; i<N; i++) {
				if(arr[i]!=0) sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		if(idx>M) return;
		for(int i=0; i<N; i++) {
			arr[idx] = i+1;
			back(idx+1, arr, sb);
		}
	}
}