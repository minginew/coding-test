import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main { 
	static int N,M;
	static boolean[] visit;
	static int[][] adj;
	
	static class Edge{
		int st;
		int ed;
		int w;
		
		public Edge(int st, int ed, int w) {
			this.st = st;
			this.ed = ed;
			this.w = w;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] in1 = br.readLine().split(" ");
		N = Integer.parseInt(in1[0]);
		M = Integer.parseInt(in1[1]);
		
		visit = new boolean[N];
		adj = new int[N][N];
		
		for(int i=0; i<M; i++) {
		String[] in2 = br.readLine().split(" ");
		int r = Integer.parseInt(in2[0])-1;
		int c = Integer.parseInt(in2[1])-1;
		
		adj[r][c] = -1;
		adj[c][r] = -1;
		}
		
		for(int r=0; r<N; r++) {
			String[] row = br.readLine().split(" ");
			for(int c=0; c<N; c++) {
				if(adj[r][c]==-1) continue;
				adj[r][c] = adj[c][r] = Integer.parseInt(row[c]);
			}
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});
		
		for(int i=2; i<N; i++) {
			pq.offer(new Edge(1, i, adj[1][i]));
		}
		visit[0] = true;
		visit[1] = true;
		
		int total = 0; //추가로 연결한 네트워크 최소 비용 
		Queue<int[]> net = new LinkedList<>();
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int st = edge.st;
			int ed = edge.ed;
			int w = edge.w;
			
			if(visit[ed]) continue;
			
			for(int i=1; i<N; i++) {
				if(i==ed) continue;
				pq.offer(new Edge(ed, i, adj[ed][i]));
			}
			visit[ed]=true;
			
			if(w!=-1) {
				total += w;
				net.offer(new int[] {st,ed});
			}
		}
		sb.append(total).append(" ").append(net.size()).append("\n");
		while(!net.isEmpty()) {
			sb.append(net.peek()[0]+1)
				.append(" ")
				.append(net.poll()[1]+1)
				.append("\n");
		}
		System.out.println(sb);
	}
}