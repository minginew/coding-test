import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

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
			for(int i=0; i<p.length; i++) make(i);
			
			sb.append("#").append(t).append(" ");
			for(int i=0; i<M; i++) {
				String[] input = br.readLine().split(" ");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				
				int findX = find(x);
				int findY = find(y);
				
				union(findX, findY);
			}
			for(int i=0; i<p.length; i++) find(i);
			
			int count = 0;
			for(int i=1; i<p.length; i++) {
				if(i==p[i]) count++;
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void make(int i) {
		p[i] = i;
	}
	public static int find(int x) {
		if(x != p[x]) 
			p[x] = find(p[x]);
		return p[x];
	}
	public static void union(int x, int y) {
		p[y] = x;
	}
}