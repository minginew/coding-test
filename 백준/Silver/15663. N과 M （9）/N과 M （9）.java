import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
	static int N;
	static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] in1 = br.readLine().split(" ");
		String[] in2 = br.readLine().split(" ");
		N = Integer.parseInt(in1[0]);
		M = Integer.parseInt(in1[1]);
		int[] arr = new int[N];
		int[] out = new int[N];
		boolean[] visit = new boolean[N];
		List<String> list = new ArrayList<>();
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(in2[i]);
		Arrays.sort(arr);
		back(arr, out, visit, list, 0);
		
		for(String s : list) System.out.println(s);
	}
	static void back(int[] arr, int[] out, boolean[] visit, List<String> list, int idx) {
		if(idx == M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<M; i++) {
				sb.append(out[i]).append(" ");
			}
			if(!list.contains(sb.toString())) list.add(sb.toString());
			return;
		}else if(idx>M) return;
		
		for(int i=0; i<N; i++) {
			if(visit[i]) continue;
			out[idx] = arr[i];
			visit[i] = true;
			back(arr, out, visit, list, idx+1);
			visit[i] = false;
		}
	}
	
}