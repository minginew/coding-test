import java.io.BufferedReader;
import java.io.InputStreamReader;import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int N,M;
	static int[] degree;
	static int[][] matrix;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=10; t++) {
			String[] in = br.readLine().split(" ");
			String[] inEd = br.readLine().split(" ");
			N = Integer.parseInt(in[0]);
			M = Integer.parseInt(in[1]);
			degree = new int[N+1];
			matrix = new int[N+1][N+1];
			
			for(int i=0; i<2*M; i+=2) {
				int a = Integer.parseInt(inEd[i]);
				int b = Integer.parseInt(inEd[i+1]);
				matrix[a][b] = 1;
				degree[b]++;
			}
			Queue<Integer> q = new LinkedList<>();
			for(int i=1; i<degree.length; i++) {
				if(degree[i]==0) q.offer(i);
			}
			sb.append("#").append(t).append(" ");
			while(!q.isEmpty()) {
				int node = q.poll();
				sb.append(node).append(" ");
				
				for(int i=0; i<matrix.length; i++) {
					if(matrix[node][i] == 1) {
						degree[i]--;
						if(degree[i]==0) q.offer(i);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}