import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static int N,M;
	static boolean[] visit;
	static int[][] coor; //좌표
	static double[][] cost; // 비용
	
	static class Edge{
		int st;
		int ed;
		double w;
		
		public Edge(int st, int ed, double w) {
			super();
			this.st = st;
			this.ed = ed;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in1 = br.readLine().split(" ");
		N = Integer.parseInt(in1[0]);
		M = Integer.parseInt(in1[1]);
		visit = new boolean[N];
		coor = new int[N][2];
		cost = new double[N][N];
		
		for(int i=0; i<N; i++) {
			String[] in2 = br.readLine().split(" ");
			int r = Integer.parseInt(in2[0]);
			int c = Integer.parseInt(in2[1]);		
			coor[i] = new int[] {r,c};
		}
		
		for(int r=0; r<N; r++) {
			for(int c=r+1; c<N; c++) {
				int x1 = coor[r][0];
				int y1 = coor[r][1];
				int x2 = coor[c][0];
				int y2 = coor[c][1];
				double w = Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
				cost[r][c] = cost[c][r] = w;
			}
		}
		
		for(int i=0; i<M; i++) {
			String[] in3 = br.readLine().split(" ");
			int r = Integer.parseInt(in3[0])-1;
			int c = Integer.parseInt(in3[1])-1;
			cost[r][c] = cost[c][r] = -1;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Double.compare(o1.w, o2.w);
			}
		});
		
		for(int i=1; i<N; i++) {
			pq.offer(new Edge(0, i, cost[0][i]));
		}
		visit[0] = true;
		
		double total = 0.0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int st = e.st;
			int ed = e.ed;
			double w = e.w;
			
			if(visit[ed]) continue;
			for(int i=0; i<N; i++) {
				if(ed==i) continue;
				pq.offer(new Edge(ed, i, cost[ed][i]));
			}
			visit[ed] = true;
			if(w>0) {
				total += w;
			}
		}
		System.out.println(String.format("%.2f", total));
	}
}