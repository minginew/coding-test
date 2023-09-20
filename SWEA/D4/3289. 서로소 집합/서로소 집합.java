import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int N,M;
	static int[] p;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] in = br.readLine().split(" ");
			N = Integer.parseInt(in[0]);
			M = Integer.parseInt(in[1]);
			p = new int[N+1];
			for(int i=0; i<p.length; i++) makeSet(i);
			
			sb.append("#").append(t).append(" ");
			for(int i=0; i<M; i++) {
				String[] input = br.readLine().split(" ");
				int C = Integer.parseInt(input[0]);
				int x = Integer.parseInt(input[1]);
				int y = Integer.parseInt(input[2]);
				
				int findX = findSet(x);
				int findY = findSet(y);
				
				if(C==0) {
					unionSet(findX, findY);
				}else if(C==1) {
					if(findX != findY) sb.append(0);
					else sb.append(1);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void makeSet(int i) {
		p[i] = i;
	}
	
	static int findSet(int x) {
		if(x != p[x]) 
			p[x] = findSet(p[x]);
		return p[x];
	}
	
	static void unionSet(int x, int y) {
		p[y] = x;
	}
	
}