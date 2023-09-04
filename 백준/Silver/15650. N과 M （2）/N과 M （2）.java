import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]); 
		M = Integer.parseInt(in[1]); // 부분집합의 크기
		boolean[] visit = new boolean[N];
		back(visit, 0, 0);
	}
	static void back(boolean[] visit, int idx, int pick) {
		if(pick == M) {
			for(int i=0; i<N; i++) {
				if(visit[i]) System.out.print(i+1+" ");
			}
			System.out.println();
			return;
		}
		if(idx==N) return;
		
		visit[idx] = true;
		back(visit,idx+1,pick+1);
		visit[idx] = false;
		back(visit,idx+1,pick);
	}
}