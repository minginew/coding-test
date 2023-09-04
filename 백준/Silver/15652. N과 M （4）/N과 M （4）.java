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
		back(arr, 0, 0, sb);
		System.out.println(sb.toString());
	}
	
	static void back(int[] arr, int idx, int max, StringBuilder sb) {
		if(idx==M) {
			for(int i=0; i<M; i++) {
				if(arr[i]!=0) sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}else if(idx>M) return;
		for(int i=0; i<N; i++) {
			if(i+1<max) continue;
			arr[idx]=i+1;
			back(arr, idx+1, i+1, sb);
		}
	}
}