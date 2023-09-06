import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String[] input = br.readLine().split(" ");
			if(input[0].equals("0")) break;
			int N = Integer.parseInt(input[0]);
			int[] arr = new int[N];
			int[] out = new int[6];
			
			for(int i=1; i<=N; i++) arr[i-1] = Integer.parseInt(input[i]);
			back(0, -1, 0, arr, out, sb);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	static void back(int idx, int last, int n, int[] arr, int[] out, StringBuilder sb) {
		if(n==6) {
			for(int i=0; i<6; i++) sb.append(out[i]).append(" ");
			sb.append("\n");
			return;
		}else if(idx==arr.length) return;
		
		for(int i=0; i<arr.length; i++) {
			if(i<=last) continue;
			out[idx] = arr[i];
			back(idx+1, i, n+1, arr, out, sb);
		}
	}
}