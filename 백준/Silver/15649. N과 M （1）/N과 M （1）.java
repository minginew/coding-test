import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] arr;
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		arr = new int[M];
		visit = new boolean[N];
		reg(N, M, 0);
	}
	
	static void reg(int N, int M, int depth) {
		if(depth==M) {
			for(int a : arr) System.out.print(a+" ");
			System.out.println();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			arr[depth] = i+1;
			reg(N, M, depth+1);
			visit[i] =false;
		}
	}
}