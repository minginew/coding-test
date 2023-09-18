import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N,M;
	static int[][] matrix;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		matrix = new int[N+1][N+1];
		boolean[] visit = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		for(int i=0; i<M; i++) {
			String[] in = br.readLine().split(" ");
			int a = Integer.parseInt(in[0]);
			int b = Integer.parseInt(in[1]);
			
			matrix[a][b] = matrix[b][a] = 1;
		}
		
		q.offer(1);
		visit[1] = true;
		System.out.println(bfs(q, visit));
		
	}
	public static int bfs(Queue<Integer> q, boolean[] visit) {
		while(!q.isEmpty()) {
			int n = q.poll();			
			for(int i=1; i<=N; i++) {
				if(!visit[i] && matrix[n][i]==1) {
					q.offer(i);
					visit[i] = true;
				}
			}
		}
		int count=0;
		for(int i=2; i<=N; i++) {
			if(visit[i]) count++;
		}
		return count;
	}
}